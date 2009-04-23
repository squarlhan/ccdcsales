package com.dcsh.market.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimeListener implements ServletContextListener {
	
	private Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		
		now.set(Calendar.HOUR_OF_DAY, 21);
		now.set(Calendar.MINUTE, 51);
		now.set(Calendar.SECOND, 0);
		
		timer = new Timer(true);
		System.out.println("开始时间："+now.getTime());
		timer.schedule(new Mytask(), now.getTime(), 20000);
	    //timer.schedule(new Mytask(), now.getTime(), 24*60*60*1000);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
			timer.cancel();
		
	}

}
