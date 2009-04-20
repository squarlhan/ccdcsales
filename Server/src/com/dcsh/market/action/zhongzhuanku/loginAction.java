package com.dcsh.market.action.zhongzhuanku;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.action.zhuchangku.loginZhuChangKuAdminAction;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.Preparable;

public class loginAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginZhuChangKuAdminAction.class.getName());
    private ZhongZhuanKuService service;
    private String username ;
    private String password ;
    private List<CankuPriv> user;

    public loginAction(ZhongZhuanKuService service) {
        this.service = service;
    }

	public String execute() {

        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);

	    for(int i=0; i<list.size();i++){

	    	if(((Canku)(list.get(i).getResource())).getType()==(byte)2){
	    		return "success";
	    	}
	    }
	    return "input";

    }

  

    public ZhongZhuanKuService getService() {
		return service;
	}

	public void setService(ZhongZhuanKuService service) {
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

    }
}
