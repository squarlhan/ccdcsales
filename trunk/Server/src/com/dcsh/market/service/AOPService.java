package com.dcsh.market.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSyikuxx;

public class AOPService {

	/**
	 * ���������ƿ�֪ͨ����ִ�з�����Ϣ
	 * @param jp �����
	 * @param xsyikuxx �����ƿ�֪ͨ������
	 */
	public void doAfterxsyiku(JoinPoint jp,XSyikuxx xsyikuxx) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:xsyikuxx="+xsyikuxx.getAimcanku().getName());
		System.out.println("AOP:***********");
    } 
	
	/**
	 * �������۷���֪ͨ����ִ�з�����Ϣ
	 * @param jp �����
	 * @param xsfahuoxx ���۷���֪ͨ������
	 */
	public void doAfterxsfahuo(JoinPoint jp,XSfahuoxx xsfahuoxx) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:xsfahuoxx="+xsfahuoxx.getCustomer().getCustomName());
		System.out.println("AOP:***********");
    }
	
	/**
	 * �����ֿ��г��������ִ�з�����Ϣ
	 * @param jp �����
	 * @param chuku �������ݣ������ֿ��ƿ⡢��������ġ����پ�������
	 */
	public void doAfterchuku(JoinPoint jp,Chuku chuku) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku="+chuku.getCankuByRkId().getName());
		System.out.println("AOP:***********");
    }
	
	/**
	 * �����ֿ�δ������Ʒ����ִ�з�����Ϣ
	 * @param jp �����
	 * @param chukumxes ������Ʒ��ϸ
	 * @param rkxx ����ȫ�������Ϣ
	 */
	public void doAfterdaohuo(JoinPoint jp,List<Chukumx> chukumxes,Rkxx rkxx){
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku="+rkxx.getCanku().getName());
		System.out.println("AOP:***********");
	}

}
