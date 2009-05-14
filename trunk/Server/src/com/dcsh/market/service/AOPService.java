package com.dcsh.market.service;

import java.util.ArrayList;
import java.util.HashSet;
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
	 * @param canku 要查的仓库
	 * @return 管理员列表
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> getusersbycanku(Canku canku) {
		Set<Users> result =  new HashSet();
		List<UserPriv> ups = (List<UserPriv>) hibernateTemplate
				.find("from UserPriv where resource='canku:"
						+ String.valueOf(canku.getId()) + "'");
		if(ups.size()>0){
		    for(UserPriv up:ups){
		    	Set<UserGroup> ugs = up.getUser().getGroup();
		    	for(UserGroup ug:ugs){
		    		switch(canku.getType()){
		    		case 0:if(ug.getName().indexOf("储运处")!=-1)result.add(up.getUser());break;
		    		case 1:if(ug.getName().indexOf("驻厂库")!=-1)result.add(up.getUser());break;
		    		case 2:if(ug.getName().indexOf("中转库")!=-1)result.add(up.getUser());break;
		    		default: break;
		    		}
		    	}
		    }
		}
		return result;
	}
	
	/**
	 * 根据产品获得计划员
	 * @param canku 要查的产品
	 * @return 计划员列表
	 */
	@SuppressWarnings("unchecked")
	public Set<Users> getusersbyprd(Products product) {
		Set<Users> result =  new HashSet();
		List<UserPriv> ups = (List<UserPriv>) hibernateTemplate
				.find("from UserPriv where resource='prd:"
						+ String.valueOf(product.getId()) + "'");
		if(ups.size()>0){
		    for(UserPriv up:ups){
		    	Set<UserGroup> ugs = up.getUser().getGroup();
		    	for(UserGroup ug:ugs){
		    		if(ug.getName().indexOf("销售")!=-1)result.add(up.getUser());
		    	}
		    }
		}
		return result;
	}

	/**
	 * 开具销售移库通知单后执行发送信息
	 * 
	 * @param jp
	 *            切入点
	 * @param xsyikuxx
	 *            销售移库通知单内容
	 */
	public void doAfterxsyiku(JoinPoint jp,XSyikuxx xsyikuxx) {   
		System.out.println("销售移库");
		List<MyMessages> megs = new ArrayList();
		
		for(XSyikumx mx:xsyikuxx.getXsyikumxes()){
			String content = "计划规格为"+mx.getSpecification().getDisplayName()
			  +"的"+mx.getProduct().getName()
			  +mx.getSpecification().getWeight().floatValue()*mx.getNumber()
			  +"吨从"+mx.getCanku().getName()
			  +"移库到"+xsyikuxx.getAimcanku().getName();
			Set<Users> users =  new HashSet();
			users.addAll(this.getusersbycanku(mx.getCanku()));
			users.addAll(this.getusersbycanku(xsyikuxx.getAimcanku()));
			users.addAll(this.getusersbyprd(mx.getProduct()));
			
			MyMessages mm = new MyMessages(users,content);
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:xsfahuoxx="
				+ xsfahuoxx.getCustomer().getCustomName());
		System.out.println("AOP:***********");
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku=" + chuku.getCankuByRkId().getName());
		System.out.println("AOP:***********");
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku=" + rkxx.getCanku().getName());
		System.out.println("AOP:***********");
	}

}
