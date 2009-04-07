package com.dcsh.market.action.zhuchangku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class logoutAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(logoutAction.class.getName());

    public logoutAction() {
    	System.out.println("Enter Constructor");
    }

	public String execute() {
    	System.out.println("Enter Excute");
        Map session = ActionContext.getContext().getSession();
        session.put("zhuchanguser", null);
        return "index";
  
        //return Action.SUCCESS;
    }
	public void prepare() throws Exception {

    }
}