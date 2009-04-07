package com.dcsh.market.action.admin;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.UserGroup;
import com.dcsh.market.Users;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class adminShowGroupMemberAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(adminGroupManagerAction.class.getName());
	private AdminService service;
	private Integer groupId;
	private List<Users> userlist;
	private List<Boolean> selectedUserIdlist;

	public adminShowGroupMemberAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	

	public List<Boolean> getSelectedUserIdlist() {
		return selectedUserIdlist;
	}



	public void setSelectedUserIdlist(List<Boolean> selectedUserIdlist) {
		this.selectedUserIdlist = selectedUserIdlist;
	}



	public Integer getGroupId() {
		return groupId;
	}





	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}





	public List<Users> getUserlist() {
		return userlist;
	}


	public void setUserlist(List<Users> userlist) {
		this.userlist = userlist;
	}


	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unchecked")
	public String execute() {		
    	System.out.println("Enter Excute");
    	Map session = ActionContext.getContext().getSession();
    	session.put("groupId", groupId);
    	this.userlist=service.getAllUsers();
    	selectedUserIdlist=service.getSelectedUserlist(this.getGroupId());
    	
    	
        return "list";
       
    }

}
