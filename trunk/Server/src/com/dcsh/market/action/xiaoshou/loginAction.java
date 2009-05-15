package com.dcsh.market.action.xiaoshou;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.action.zhuchangku.loginZhuChangKuAdminAction;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.opensymphony.xwork2.Preparable;

public class loginAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginZhuChangKuAdminAction.class.getName());

    public loginAction() {

    }

	public String execute() throws Exception {

        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.PRD);

	    if(list.size()!=0)return "go";
	    return "index";

    }

	public void prepare() throws Exception {

    }
}
