package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class loginSystemAdminAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginSystemAdminAction.class.getName());
    private AdminService service;
    private String username ;
    private String password ;
    private List<CankuPriv> user;

    public loginSystemAdminAction(AdminService service) {
        this.service = service;
    }

	public String execute()  throws Exception{
    	
        	return "success";
        
    }


	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public List<CankuPriv> getUser() {
		return user;
	}

	public void setUser(List<CankuPriv> user) {
		this.user = user;
	}

	public void prepare() throws Exception {

    }}

