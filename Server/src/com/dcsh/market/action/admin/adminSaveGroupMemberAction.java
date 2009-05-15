package com.dcsh.market.action.admin;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class adminSaveGroupMemberAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(adminGroupManagerAction.class.getName());
	private AdminService service;
	
	private List<Boolean> isgroupmember;
	private Integer groupId;
	
	public adminSaveGroupMemberAction(AdminService service) {
        this.service = service;
    }
	
	
	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public List<Boolean> getIsgroupmember() {
		return isgroupmember;
	}


	public void setIsgroupmember(List<Boolean> isgroupmember) {
		this.isgroupmember = isgroupmember;
	}


	@SuppressWarnings("unchecked")
	public String execute() throws Exception {		

    	Map session = ActionContext.getContext().getSession();
    	if(session.containsKey("groupId"))
    		groupId = (Integer)session.get("groupId");
    	
    	service.saveGroupMember(isgroupmember,groupId);
    	
        return "savegroupmember_ok";
       
    }
	
	
	
	
	
	
	
	
	
	public void prepare() throws Exception {

	}

}
