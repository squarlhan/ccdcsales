package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goEntrygckAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goEntrygckAction.class.getName());
    private WareHouseService service;
    private List<Products> productsList;
    private List<Canku> orginsList;

    public goEntrygckAction(WareHouseService service) {
    	
        this.service = service;
    }
    
	public String execute() throws Exception {
    
    	
    	PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil
		.getLoginAuthentication();
        List<ResourceGrantedAuthorityImpl> list = auth
		.getGrantedAuthorityResource(ResourceType.PRD);

        Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	
        List<Products> plist = new ArrayList();
        for (ResourceGrantedAuthorityImpl res : list) {
	          plist.add((Products) res.getResource());
             }
    	
    	
        this.productsList = plist;
        this.orginsList = service.getGckbycyc(user.get(0).getCanku());
        return "show";
     
    }

    public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
	}

	public List<Products> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Products> productsList) {
		this.productsList = productsList;
	}

	public List<Canku> getOrginsList() {
		return orginsList;
	}

	public void setOrginsList(List<Canku> orginsList) {
		this.orginsList = orginsList;
	}

	public void prepare() throws Exception {

    }
}
