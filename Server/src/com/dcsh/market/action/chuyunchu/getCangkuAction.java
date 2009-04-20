package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class getCangkuAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getCangkuAction.class.getName());
    private WareHouseService service;
    private List<Canku> cangkusList;

    public getCangkuAction(WareHouseService service) {
        this.service = service;
    }
    
	public String execute() {

        this.cangkusList = service.getAllCankus();

        return "show_report";
      
    }

    public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
	}

	
	public List<Canku> getCangkusList() {
		return cangkusList;
	}

	public void setCangkusList(List<Canku> cangkusList) {
		this.cangkusList = cangkusList;
	}

	public void prepare() throws Exception {

    }
}