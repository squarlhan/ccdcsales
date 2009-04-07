package com.dcsh.market.service;

import java.util.regex.Pattern;

public class Regtry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String reg = ".*(/warehouseadmin/|/cyc|/index).*";
		String str = "http://localhost:8080/Server/cycloginchuyunchuAdmin.action";
		
		Pattern p = Pattern.compile(reg);
		if(p.matcher(str).matches())System.out.println("000000000000");
		else System.out.println("************");

	}

}
