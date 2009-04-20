package com.dcsh.market.action.user;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.opensymphony.xwork2.Preparable;

public class goUserAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(
			goUserAction.class.getName());
	private String username;
	private String phone;
	
	public goUserAction(){
	
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String execute() {
	
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil.getLoginAuthentication();
		this.username = auth.getPrincipal().getName();
		this.phone = auth.getPrincipal().getPhone();
		return "go";
	}

	public void prepare() throws Exception {

	}

}
