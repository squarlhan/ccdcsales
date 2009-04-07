package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class getCangkuAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getCangkuAction.class.getName());
    private AdminService service;
    private List<Canku> cangkusList;

    public getCangkuAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	public String execute() {
    	System.out.println("Enter Excute");
        this.cangkusList = service.getAllCankus();
        System.out.println(cangkusList);
        return "show_report";
      
    }

    public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
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