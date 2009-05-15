package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class goEntryAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goEntryAction.class.getName());
    private WareHouseService service;
    private List<Products> productsList;
    private List<Specifications> specificationsList;

    public goEntryAction(WareHouseService service) {
    	
        this.service = service;
    }
    
	public String execute() throws Exception {
    
    	
    	PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil
		.getLoginAuthentication();
        List<ResourceGrantedAuthorityImpl> list = auth
		.getGrantedAuthorityResource(ResourceType.PRD);

        List<Products> plist = new ArrayList();
        for (ResourceGrantedAuthorityImpl res : list) {
	          plist.add((Products) res.getResource());
             }
    	
    	
        this.productsList = plist;
        this.specificationsList = service.getAllSpecifications();
      
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

	public List<Specifications> getSpecificationsList() {
		return specificationsList;
	}

	public void setSpecificationsList(List<Specifications> specificationsList) {
		this.specificationsList = specificationsList;
	}

	public void prepare() throws Exception {

    }
}

