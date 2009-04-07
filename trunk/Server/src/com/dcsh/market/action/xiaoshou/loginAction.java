package com.dcsh.market.action.xiaoshou;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Users;
import com.dcsh.market.action.zhuchangku.loginZhuChangKuAdminAction;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class loginAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(loginZhuChangKuAdminAction.class.getName());

    public loginAction() {
    	System.out.println("Enter Constructor");
    }

	public String execute() {
    	System.out.println("Enter Excute");
        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.PRD);
	    System.out.println("list.size()="+list.size());
	    if(list.size()!=0)return "go";
	    return "index";
        //return Action.SUCCESS;
    }

	public void prepare() throws Exception {

    }
}
