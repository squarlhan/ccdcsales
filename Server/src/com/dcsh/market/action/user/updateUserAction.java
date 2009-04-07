package com.dcsh.market.action.user;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Users;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class updateUserAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(
			updateUserAction.class.getName());
	private String username;
	private String phone;
	private String newpassword;
	private String repassword;
	private AdminService service;
	
	public updateUserAction(AdminService service){
		System.out.println("Enter Constructor");
		this.service = service;
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

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String execute() {
		System.out.println("Enter Execute:");
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil.getLoginAuthentication();
		Users user = auth.getPrincipal();
		user.setPhone(this.getPhone().trim());
		if(this.getNewpassword().trim().equals(this.getRepassword().trim()))
			user.setPassword(this.getNewpassword().trim().getBytes());
		service.updateUser(user);
		return "success";
	}

	public void prepare() throws Exception {

	}


}
