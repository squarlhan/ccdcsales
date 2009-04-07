package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class goStorageAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goStorageAction.class.getName());
    private WareHouseService service;
    private List<Canku> cankusList;

    public goStorageAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	public String execute() {
    	System.out.println("Enter Excute");
        this.cankusList = service.getAllCankus();
        System.out.println(cankusList);
        return "show";
        //return Action.SUCCESS;
    }

    public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
	}

	
	public List<Canku> getCankusList() {
		return cankusList;
	}

	public void setCankusList(List<Canku> cankusList) {
		this.cankusList = cankusList;
	}

	public void prepare() throws Exception {

    }
}