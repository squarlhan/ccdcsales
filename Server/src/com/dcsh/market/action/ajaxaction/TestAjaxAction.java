package com.dcsh.market.action.ajaxaction;

import com.opensymphony.xwork2.Action;

public class TestAjaxAction implements Action{
	
	public String start;
	
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public TestAjaxAction()
    {
	   System.out.println("Enter Constructor");
	}
	
	public String execute() {
		System.out.println("Enter TestAjaxAction");
		System.out.println("start:"+start);
		return "try";
	}

}
