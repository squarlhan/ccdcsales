package com.dcsh.market.action.ajaxaction;

import com.opensymphony.xwork2.Action;

public class TestAction implements Action{
	private String name;
	
	private int age;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String execute() {
		age = 1000;
		setName("Hello");
		return SUCCESS;
	}

}
