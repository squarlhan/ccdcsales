package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.UserGroup;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class getAllGroupsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    private List<UserGroup> resultList;
    
	public List<UserGroup> getResultList() {
		return resultList;
	}

	public void setResultList(List<UserGroup> resultList) {
		this.resultList = resultList;
	}

	public getAllGroupsAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() throws Exception {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllGroups();
        return "show";
      
    }
    
    public void prepare() throws Exception {

    }

}
