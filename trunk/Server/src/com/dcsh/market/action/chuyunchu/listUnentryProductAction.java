package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Chukumx;
import com.dcsh.market.Rkmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class listUnentryProductAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listUnCheckProductsAction.class.getName());
    private WareHouseService service;
    private List<Chukumx> resultList;
    private Rkmx rkmx;
    private boolean flag;
    public listUnentryProductAction(WareHouseService service) {

        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() {

    	Map session = ActionContext.getContext().getSession();
    	PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
    	List<ResourceGrantedAuthorityImpl> list = 
    		auth.getGrantedAuthorityResource(ResourceType.CANKU);
    	for(ResourceGrantedAuthorityImpl resource: list){
    	
    	}
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    	
    		this.resultList = service.listUnentryProducts(user.get(0).getCanku().getId());
    		if(resultList.size()==0)
    			this.setFlag(true);
    		else
    			this.setFlag(false);
            return "listunentry";
    	}
    
    }


    public List<Chukumx> getResultList() {
		return resultList;
	}


	public void setResultList(List<Chukumx> resultList) {
		this.resultList = resultList;
	}  


	public Rkmx getRkmx() {
		return rkmx;
	}


	public void setRcmx(Rkmx rkmx) {
		this.rkmx = rkmx;
	}


	public void prepare() throws Exception {

    }


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public boolean isFlag() {
		return flag;
	}
}