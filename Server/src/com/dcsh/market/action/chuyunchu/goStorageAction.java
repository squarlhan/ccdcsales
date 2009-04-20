package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class goStorageAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goStorageAction.class.getName());
    private WareHouseService service;
    private List<Canku> cankusList;

    public goStorageAction(WareHouseService service) {
   
        this.service = service;
    }
    
	public String execute() {
    
        this.cankusList = service.getAllCankus();
    
        return "show";
    
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