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
	 * ���ݲֿ��ù���Ա
	 * 
	 * @param canku
	 *            Ҫ��Ĳֿ�
	 * @return ����Ա�б�
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
						if (ug.getName().indexOf("���˴�") != -1)
							result.add(up.getUser());
						break;
					case 1:
						if (ug.getName().indexOf("פ����") != -1)
							result.add(up.getUser());
						break;
					case 2:
						if (ug.getName().indexOf("��ת��") != -1)
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
	 * ���ݲ�Ʒ��üƻ�Ա
	 * 
	 * @param canku
	 *            Ҫ��Ĳ�Ʒ
	 * @return �ƻ�Ա�б�
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
					if (ug.getName().indexOf("Ӫ��") != -1)
						result.add(up.getUser());
				}
			}
		}
		return result;
	}

	/**
	 * �鿴�û��Ƿ���ĳ��Ʒ��ĳ����
	 * 
	 * @param users
	 *            �û�����
	 * @param product
	 *            ��Ʒ
	 * @param type
	 *            ���Ͷ�������
	 * @return ���˺���û�����
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
	 * doAfterchuku���ӷ������ڲ�Ʒ�ƿ�װ���󴥷�
	 * @param chuku ������Ϣ
	 */
	public void doAfterykzc(Chuku chuku){
		System.out.println(chuku.getCankuByCankuId().getName()+"�ƿ�");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chuku.getChukumxes()) {
			String content = "���Ϊ" + mx.getSpecifications().getDisplayName()
					+ "��" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "�ִ�"
					+ chuku.getCankuByCankuId().getName() + "�ƿ⵽"
					+ chuku.getCankuByRkId().getName() + "����װ��";
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
	 * doAfterchuku���ӷ������ڲ�Ʒ����װ���󴥷�
	 * @param chuku ������Ϣ
	 */
	public void doAfterxszc(Chuku chuku){
		System.out.println(chuku.getCankuByCankuId().getName()+"����");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chuku.getChukumxes()) {
			String content = "���Ϊ" + mx.getSpecifications().getDisplayName()
					+ "��" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "�ִ�"
					+ chuku.getCankuByCankuId().getName() + "���۵�"
					+ chuku.getCustom().getCustomName() + "����װ��";
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
	 * doAfterdaohuo���ӷ������ڲ�Ʒ�ƿ⵽���󴥷�
	 * @param chuku ������Ϣ
	 */
	public void doAfterykdh(List<Chukumx> chukumxes, Rkxx rkxx){
		System.out.println(rkxx.getCanku().getName()+"�ƿ⵽��");
		List<MyMessages> megs = new ArrayList();

		for (Chukumx mx : chukumxes) {
			mx = (Chukumx) hibernateTemplate.get(Chukumx.class, mx.getId());
			String content = "���Ϊ" + mx.getSpecifications().getDisplayName()
					+ "��" + mx.getProducts().getName()
					+ mx.getSpecifications().getWeight().floatValue()
					* mx.getNumber() + "�ִ�"
					+ mx.getChuku().getCankuByCankuId().getName() + "�ƿ⵽"
					+ mx.getChuku().getCankuByRkId().getName() + "���ѵ���";
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
	 * ���������ƿ�֪ͨ����ִ�з�����Ϣ
	 * 
	 * @param jp
	 *            �����
	 * @param xsyikuxx
	 *            �����ƿ�֪ͨ������
	 */
	public void doAfterxsyiku(JoinPoint jp, XSyikuxx xsyikuxx) {
		System.out.println("�����ƿ�");
		List<MyMessages> megs = new ArrayList();

		for (XSyikumx mx : xsyikuxx.getXsyikumxes()) {
			String content = "�ƻ����Ϊ" + mx.getSpecification().getDisplayName()
					+ "��" + mx.getProduct().getName()
					+ mx.getSpecification().getWeight().floatValue()
					* mx.getNumber() + "�ִ�" + mx.getCanku().getName() + "�ƿ⵽"
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
	 * �������۷���֪ͨ����ִ�з�����Ϣ
	 * 
	 * @param jp
	 *            �����
	 * @param xsfahuoxx
	 *            ���۷���֪ͨ������
	 */
	public void doAfterxsfahuo(JoinPoint jp, XSfahuoxx xsfahuoxx) {
		System.out.println("���۷���");
		List<MyMessages> megs = new ArrayList();

		for (XSfahuomx mx : xsfahuoxx.getXsfahuomxes()) {
			String content = "�ƻ����Ϊ" + mx.getSpecification().getDisplayName()
					+ "��" + mx.getProduct().getName()
					+ mx.getSpecification().getWeight().floatValue()
					* mx.getNumber() + "�ִ�" + mx.getCanku().getName() + "���۸�"
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
	 * �����ֿ��г��������ִ�з�����Ϣ
	 * 
	 * @param jp
	 *            �����
	 * @param chuku
	 *            �������ݣ������ֿ��ƿ⡢��������ġ����پ�������
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
	 * �����ֿ�δ������Ʒ����ִ�з�����Ϣ
	 * 
	 * @param jp
	 *            �����
	 * @param chukumxes
	 *            ������Ʒ��ϸ
	 * @param rkxx
	 *            ����ȫ�������Ϣ
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
