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
	 * ���ݲֿ��ù���Ա
	 * @param canku Ҫ��Ĳֿ�
	 * @return ����Ա�б�
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
		    		case 0:if(ug.getName().indexOf("���˴�")!=-1)result.add(up.getUser());break;
		    		case 1:if(ug.getName().indexOf("פ����")!=-1)result.add(up.getUser());break;
		    		case 2:if(ug.getName().indexOf("��ת��")!=-1)result.add(up.getUser());break;
		    		default: break;
		    		}
		    	}
		    }
		}
		return result;
	}
	
	/**
	 * ���ݲ�Ʒ��üƻ�Ա
	 * @param canku Ҫ��Ĳ�Ʒ
	 * @return �ƻ�Ա�б�
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
		    		if(ug.getName().indexOf("����")!=-1)result.add(up.getUser());
		    	}
		    }
		}
		return result;
	}

	/**
	 * ���������ƿ�֪ͨ����ִ�з�����Ϣ
	 * 
	 * @param jp
	 *            �����
	 * @param xsyikuxx
	 *            �����ƿ�֪ͨ������
	 */
	public void doAfterxsyiku(JoinPoint jp,XSyikuxx xsyikuxx) {   
		System.out.println("�����ƿ�");
		List<MyMessages> megs = new ArrayList();
		
		for(XSyikumx mx:xsyikuxx.getXsyikumxes()){
			String content = "�ƻ����Ϊ"+mx.getSpecification().getDisplayName()
			  +"��"+mx.getProduct().getName()
			  +mx.getSpecification().getWeight().floatValue()*mx.getNumber()
			  +"�ִ�"+mx.getCanku().getName()
			  +"�ƿ⵽"+xsyikuxx.getAimcanku().getName();
			Set<Users> users =  new HashSet();
			users.addAll(this.getusersbycanku(mx.getCanku()));
			users.addAll(this.getusersbycanku(xsyikuxx.getAimcanku()));
			users.addAll(this.getusersbyprd(mx.getProduct()));
			
			MyMessages mm = new MyMessages(users,content);
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:xsfahuoxx="
				+ xsfahuoxx.getCustomer().getCustomName());
		System.out.println("AOP:***********");
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku=" + chuku.getCankuByRkId().getName());
		System.out.println("AOP:***********");
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
		System.out.println("AOP:"
				+ (String) jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku=" + rkxx.getCanku().getName());
		System.out.println("AOP:***********");
	}

}
