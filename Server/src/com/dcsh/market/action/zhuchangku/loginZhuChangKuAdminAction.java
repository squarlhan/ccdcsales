package com.dcsh.market.action.zhuchangku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Users;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class loginZhuChangKuAdminAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginZhuChangKuAdminAction.class.getName());
    private ZhuChangKuService service;
    private String username ;
    private String password ;
    private List<CankuPriv> user;

    public loginZhuChangKuAdminAction(ZhuChangKuService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

	public String execute() {
    	System.out.println("Enter Excute");
        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);
	    System.out.println("list.size()="+list.size());
	    for(int i=0; i<list.size();i++){
	    	System.out.println("Type="+((Canku)(list.get(i).getResource())).getType());
	    	if(((Canku)(list.get(i).getResource())).getType()==(byte)1){
	    		return "success";
	    	}
	    }
	    return "input";
        //return Action.SUCCESS;
    }

  

    public ZhuChangKuService getService() {
		return service;
	}

	public void setService(ZhuChangKuService service) {
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
