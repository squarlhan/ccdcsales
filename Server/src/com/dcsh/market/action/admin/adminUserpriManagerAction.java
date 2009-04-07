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
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllUsers();        
        System.out.println(resultList);
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
