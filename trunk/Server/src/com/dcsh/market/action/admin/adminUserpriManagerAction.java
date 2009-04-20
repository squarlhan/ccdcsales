package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Users;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminUserpriManagerAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    private List<Users> resultList;
    

	public adminUserpriManagerAction(AdminService service) {    
        this.service = service;
    }
	
	public String execute() {    
        this.resultList = service.getAllUsers();        
        return "show";
      
    }
    
    public List<Users> getResultList() {
		return resultList;
	}

	public void setResultList(List<Users> resultList) {
		this.resultList = resultList;
	}

	public void prepare() throws Exception {

    }

}
