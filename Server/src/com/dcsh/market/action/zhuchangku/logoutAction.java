package com.dcsh.market.action.zhuchangku;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class logoutAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(logoutAction.class.getName());

    public logoutAction() {
    }

	public String execute() throws Exception {

        Map session = ActionContext.getContext().getSession();
        session.put("zhuchanguser", null);
        return "index";
    }
	public void prepare() throws Exception {

    }
}