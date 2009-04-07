package com.dcsh.market.action.chuyunchu;

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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class loginChuYunChuAdminAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginChuYunChuAdminAction.class.getName());
    private List<CankuPriv> user;

    public loginChuYunChuAdminAction() {
    	System.out.println("Enter Constructor");
    }

	public String execute() {
    	System.out.println("Enter Excute");
        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);
	    System.out.println("list.size()="+list.size());
	    for(int i=0; i<list.size();i++){
	    	System.out.println("Type="+((Canku)(list.get(i).getResource())).getType());
	    	if(((Canku)(list.get(i).getResource())).getType()==(byte)0){
	    		return "success";
	    	}
	    }
	    return "input";
        
    }

	
	public List<CankuPriv> getUser() {
		return user;
	}

	public void setUser(List<CankuPriv> user) {
		this.user = user;
	}

	public void prepare() throws Exception {

    }
	
}
