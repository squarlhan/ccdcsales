package com.dcsh.market.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dcsh.market.Canku;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.MyMessages;
import com.dcsh.market.Products;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Smmdingyue;
import com.dcsh.market.UserGroup;
import com.dcsh.market.UserPriv;
import com.dcsh.market.Users;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSyikuxx;
import com.dcsh.market.XSyikumx;

public class AOPService {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 根据仓库获得管理员
	 * 
	 * @param canku
	 *            要查的仓库
	 * @return 管理员列表
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> getusersbycanku(Canku canku) {
		Set<Users> result = new HashSet();
		List<UserPriv> ups = (List<UserPriv>) hibernateTemplate
				.find("from UserPriv where resource='canku:"
						+ String.valueOf(canku.getId()) + "'");
		if (ups.size() > 0) {
			for (UserPriv up : ups) {
				Set<UserGroup> ugs = up.getUser().getGroup();
				for (UserGroup ug : ugs) {
					switch (canku.getType()) {
					case 0:
						if (ug.getName().indexOf("储运处") != -1)
							result.add(up.getUser());
						break;
					case 1:
						if (ug.getName().indexOf("驻厂库") != -1)
							result.add(up.getUser());
						break;
					case 2:
						if (ug.getName().indexOf("中转库") != -1)
							result.add(up.getUser());
						break;
					default:
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 根据产品获得计划员
	 * 
	 * @param canku
	 *            要查的产品
	 * @return 计划员列表
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> getusersbyprd(Products product) {
		Set<Users> result = new HashSet();
		List<UserPriv> ups = (List<UserPriv>) hibernateTemplate
				.find("from UserPriv where resource='prd:"
						+ String.valueOf(product.getId()) + "'");
		if (ups.size() > 0) {
			for (UserPriv up : ups) {
				Set<UserGroup> ugs = up.getUser().getGroup();
				for (UserGroup ug : ugs) {
					if (ug.getName().indexOf("营销") != -1)
						result.add(up.getUser());
				}
			}
		}
		return result;
	}

	/**
	 * 查看用户是否订阅某产品的某服务
	 * 
	 * @param users
	 *            用户集合
	 * @param product
	 *            产品
	 * @param type
	 *            发送短信类型
	 * @return 过滤后的用户集合
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> checkusers(Set<Users> users, Products product, int type) {
		for (Iterator it = users.iterator(); it.hasNext();) {
			Users user = (Users) it.next();
			List<Smmdingyue> smmdys = (List<Smmdingyue>) hibernateTemplate
					.find("from Smmdingyue where user.id="
							+ String.valueOf(user.getId()) + " and product.id="
							+ String.valueOf(product.getId()));
			if (smmdys.size() > 0) {
				switch (type) {
				case 0:
					if (smmdys.get(0).getXsyk1() == (byte) 0)
						it.remove();
					break;
				case 1:
					if (smmdys.get(0).getXsyk2() == (byte) 0)
						it.remove();
					break;
				case 2:
					if (smmdys.get(0).getXsyk3() == (byte) 0)
						it.remove();
					break;
				case 3:
					if (smmdys.get(0).getXsfh1() == (byte) 0)
						it.remove();
					break;
				case 4:
					if (smmdys.get(0).getXsfh2() == (byte) 0)
						it.remove();
					break;
				case 5:
					if (smmdys.get(0).getCycyk1() == (byte) 0)
						it.remove();
					break;
				case 6:
					if (smmdys.get(0).getCycyk2() == (byte) 0)
						it.remove();
					break;
				default:
					break;
				}
			}
		}
		return users;
	}
	
	/**
	 * doAfterchuku的子方法，在产品移库装车后触发
	 * @param chuku 出库信息
	 */
	public void doAfterykzc(Chuku chuku){
		System.out.println(chuku.getCankuByCankuId().getName()+"移库");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chuku.getChukumxes()) {
			String content = "规格为" + mx.getSpecifications().getDisplayName()
					+ "的" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "吨从"
					+ chuku.getCankuByCankuId().getName() + "移库到"
					+ chuku.getCankuByRkId().getName() + "，已装车";
			Set<Users> users = new HashSet();
			users.addAll(this.getusersbycanku(chuku.getCankuByCankuId()));
			users.addAll(this.getusersbycanku(chuku.getCankuByRkId()));
			users.addAll(this.getusersbyprd(mx.getProducts()));

			MyMessages mm = new MyMessages(this.checkusers(users, mx
					.getProducts(), 0), content);
			System.out.println(mm);
		}

	}
	
	/**
	 * doAfterchuku的子方法，在产品销售装车后触发
	 * @param chuku 出库信息
	 */
	public void doAfterxszc(Chuku chuku){
		System.out.println(chuku.getCankuByCankuId().getName()+"销售");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chuku.getChukumxes()) {
			String content = "规格为" + mx.getSpecifications().getDisplayName()
					+ "的" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "吨从"
					+ chuku.getCankuByCankuId().getName() + "销售到"
					+ chuku.getCustom().getCustomName() + "，已装车";
			Set<Users> users = new HashSet();
			users.addAll(this.getusersbycanku(chuku.getCankuByCankuId()));
			users.add(new Users(chuku.getCustom().getCustomName(), null,
					chuku.getCustom().getShdz(), 
					chuku.getCustom().getPhone()));
			users.addAll(this.getusersbyprd(mx.getProducts()));

			MyMessages mm = new MyMessages(this.checkusers(users, mx
					.getProducts(), 0), content);
			System.out.println(mm);
		}

	}
	
	/**
	 * doAfterdaohuo的子方法，在产品移库到货后触发
	 * @param chuku 出库信息
	 */
	public void doAfterykdh(List<Chukumx> chukumxes, Rkxx rkxx){
		System.out.println(rkxx.getCanku().getName()+"移库到货");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chukumxes) {
			mx = (Chukumx) hibernateTemplate.get(Chukumx.class, mx.getId());
			String content = "规格为" + mx.getSpecifications().getDisplayName()
					+ "的" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "吨从"
					+ mx.getChuku().getCankuByCankuId().getName() + "移库到"
					+ mx.getChuku().getCankuByRkId().getName() + "，已到货";
			Set<Users> users = new HashSet();
			users.addAll(this.getusersbycanku(mx.getChuku().getCankuByCankuId()));
			users.addAll(this.getusersbycanku(mx.getChuku().getCankuByRkId()));
			users.addAll(this.getusersbyprd(mx.getProducts()));

			MyMessages mm = new MyMessages(this.checkusers(users, mx
					.getProducts(), 0), content);
			System.out.println(mm);
		}

	}

	/**
	 * 开具销售移库通知单后执行发送信息
	 * 
	 * @param jp
	 *            切入点
	 * @param xsyikuxx
	 *            销售移库通知单内容
	 */
	public void doAfterxsyiku(JoinPoint jp, XSyikuxx xsyikuxx) {
		System.out.println("销售移库");
		List<MyMessages> megs = new ArrayList();

		for (XSyikumx mx : xsyikuxx.getXsyikumxes()) {
			String content = "计划规格为" + mx.getSpecification().getDisplayName()
					+ "的" + mx.getProduct().getName()
					+ mx.getSpecification().getWeight().floatValue()
					* mx.getNumber() + "吨从" + mx.getCanku().getName() + "移库到"
					+ xsyikuxx.getAimcanku().getName();
			Set<Users> users = new HashSet();
			users.addAll(this.getusersbycanku(mx.getCanku()));
			users.addAll(this.getusersbycanku(xsyikuxx.getAimcanku()));
			users.addAll(this.getusersbyprd(mx.getProduct()));

			MyMessages mm = new MyMessages(this.checkusers(users, mx
					.getProduct(), 0), content);
			System.out.println(mm);
		}

	}

	/**
	 * 开具销售发货通知单后执行发送信息
	 * 
	 * @param jp
	 *            切入点
	 * @param xsfahuoxx
	 *            销售发货通知单内容
	 */
	public void doAfterxsfahuo(JoinPoint jp, XSfahuoxx xsfahuoxx) {
		System.out.println("销售发货");
		List<MyMessages> megs = new ArrayList();

		for (XSfahuomx mx : xsfahuoxx.getXsfahuomxes()) {
			String content = "计划规格为" + mx.getSpecification().getDisplayName()
					+ "的" + mx.getProduct().getName()
					+ mx.getSpecification().getWeight().floatValue()
					* mx.getNumber() + "吨从" + mx.getCanku().getName() + "销售给"
					+ xsfahuoxx.getCustomer().getCustomName();
			Set<Users> users = new HashSet();
			users.addAll(this.getusersbycanku(mx.getCanku()));
			users.add(new Users(xsfahuoxx.getCustomer().getCustomName(), null,
					xsfahuoxx.getCustomer().getShdz(), 
					xsfahuoxx.getCustomer().getPhone()));
			users.addAll(this.getusersbyprd(mx.getProduct()));

			MyMessages mm = new MyMessages(this.checkusers(users, mx
					.getProduct(), 3), content);
			System.out.println(mm);
		}
	}

	/**
	 * 各个仓库有出库操作后执行发送信息
	 * 
	 * @param jp
	 *            切入点
	 * @param chuku
	 *            出库内容（各个仓库移库、发货、损耗、销毁均触发）
	 */
	public void doAfterchuku(JoinPoint jp, Chuku chuku) {
        switch(chuku.getCankuByRkId().getType()){
        case 0: this.doAfterykzc(chuku);break;
        case 1: this.doAfterykzc(chuku);break;
        case 2: this.doAfterykzc(chuku);break;
        case 3: this.doAfterxszc(chuku);break;
        case 4: break;
        case 5: break;
        default: break;
        }
	}

	/**
	 * 各个仓库未到货产品入库后执行发送信息
	 * 
	 * @param jp
	 *            切入点
	 * @param chukumxes
	 *            到货产品明细
	 * @param rkxx
	 *            不完全的入库信息
	 */
	public void doAfterdaohuo(JoinPoint jp, List<Chukumx> chukumxes, Rkxx rkxx) {
		switch(rkxx.getCanku().getType()){
        case 0: this.doAfterykdh(chukumxes,rkxx);break;
        case 1: this.doAfterykdh(chukumxes,rkxx);break;
        case 2: this.doAfterykdh(chukumxes,rkxx);break;
        case 3: break;
        case 4: break;
        case 5: break;
        default: break;
        }
	}

}
