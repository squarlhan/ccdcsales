package com.dcsh.market.service;

import java.util.Date;
import java.util.TimerTask;

public class Mytask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("call at " + (new Date()));
	}

}
