package com.dcsh.market.service;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AOPtest implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("AOP:***********");
        System.out.println("AOP:"+ (String)arg0);
	}

}
