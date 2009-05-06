package com.dcsh.market.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.dcsh.market.ReportPmx;

public class AOPTest2 {

	public void doAfter(JoinPoint jp,Object retVal) {   
		System.out.println("AOP:"+(String)jp.getTarget().getClass().getName());
		System.out.println("AOP:returnsize="+((List<ReportPmx>)retVal).size());
		System.out.println("AOP:***********");
    }   

}
