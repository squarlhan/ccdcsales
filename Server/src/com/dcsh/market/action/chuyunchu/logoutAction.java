package com.dcsh.market.action.chuyunchu;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class logoutAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(logoutAction.class.getName());

    public logoutAction() {
    	
    }

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	System.out.println("Enter Excute");
        Map session = ActionContext.getContext().getSession();
        session.put("tempuser", null);
        return "index";  
    }
	public void prepare() throws Exception {

    }
}