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
	 * 开具销售移库通知单后执行发送信息
	 * @param jp 切入点
	 * @param xsyikuxx 销售移库通知单内容
	 */
	public void doAfterxsyiku(JoinPoint jp,XSyikuxx xsyikuxx) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:xsyikuxx="+xsyikuxx.getAimcanku().getName());
		System.out.println("AOP:***********");
    } 
	
	/**
	 * 开具销售发货通知单后执行发送信息
	 * @param jp 切入点
	 * @param xsfahuoxx 销售发货通知单内容
	 */
	public void doAfterxsfahuo(JoinPoint jp,XSfahuoxx xsfahuoxx) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:xsfahuoxx="+xsfahuoxx.getCustomer().getCustomName());
		System.out.println("AOP:***********");
    }
	
	/**
	 * 各个仓库有出库操作后执行发送信息
	 * @param jp 切入点
	 * @param chuku 出库内容（各个仓库移库、发货、损耗、销毁均触发）
	 */
	public void doAfterchuku(JoinPoint jp,Chuku chuku) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku="+chuku.getCankuByRkId().getName());
		System.out.println("AOP:***********");
    }
	
	/**
	 * 各个仓库未到货产品入库后执行发送信息
	 * @param jp 切入点
	 * @param chukumxes 到货产品明细
	 * @param rkxx 不完全的入库信息
	 */
	public void doAfterdaohuo(JoinPoint jp,List<Chukumx> chukumxes,Rkxx rkxx){
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:chuku="+rkxx.getCanku().getName());
		System.out.println("AOP:***********");
	}

}
