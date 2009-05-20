package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class admincfrManagerAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    private List<Canku> resultList;
    

	public admincfrManagerAction(AdminService service) {    
        this.service = service;
    }
	
	public String execute() throws Exception {    
        this.resultList = service.getAllCYC();        
        return "show";
      
    }
    
    public List<Canku> getResultList() {
		return resultList;
	}

	public void setResultList(List<Canku> resultList) {
		this.resultList = resultList;
	}

	public void prepare() throws Exception {

    }

}