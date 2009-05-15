package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.opensymphony.xwork2.Preparable;

public class loginChuYunChuAdminAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginChuYunChuAdminAction.class.getName());
    private List<CankuPriv> user;

    public loginChuYunChuAdminAction() {
    
    }

	public String execute() throws Exception {
    	
        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);
	 
	    for(int i=0; i<list.size();i++){
	    	
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
