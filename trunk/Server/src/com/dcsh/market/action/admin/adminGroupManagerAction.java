package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.UserGroup;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminGroupManagerAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(adminGroupManagerAction.class.getName());
	private AdminService service;
	private List<UserGroup> resultList;
	private String newid;
	private String newuname;
	private String newudes;
	
	private String id;
	private String name;
	private String description;


	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public List<UserGroup> getResultList() {
		return resultList;
	}

	public void setResultList(List<UserGroup> resultList) {
		this.resultList = resultList;
	}

	public String getNewid() {
		return newid;
	}

	public void setNewid(String newid) {
		this.newid = newid;
	}

	public String getNewuname() {
		return newuname;
	}

	public void setNewuname(String newuname) {
		this.newuname = newuname;
	}

	public String getNewudes() {
		return newudes;
	}

	public void setNewudes(String newudes) {
		this.newudes = newudes;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public adminGroupManagerAction(AdminService service) {
        this.service = service;
    }
	
	public String execute() {

    	this.resultList = service.getAllGroups();

        return "list";

    }
	
    public String modify(){
    	service.updateUserGroup(new UserGroup(Integer.valueOf(this.getId().trim()),this.getName().trim(),this.getDescription().trim()));
		return "modify";
	}
	public String delete(){
		service.delUserGroup(new UserGroup(Integer.valueOf(this.getId().trim())));
		return "delete";
	}
	public String add(){
		service.addUserGroup(new UserGroup(this.getNewuname().trim(),this.getNewudes().trim()));
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}

