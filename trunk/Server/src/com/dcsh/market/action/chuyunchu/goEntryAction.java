package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class goEntryAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goEntryAction.class.getName());
    private WareHouseService service;
    private List<Products> productsList;
    private List<Specifications> specificationsList;

    public goEntryAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	public String execute() {
    	System.out.println("Enter Excute");
        this.productsList = service.getAllProducts();
        this.specificationsList = service.getAllSpecifications();
        System.out.println(productsList);
        System.out.println(specificationsList);
        return "show";
        //return Action.SUCCESS;
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

