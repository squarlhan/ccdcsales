package com.dcsh.market.action.admin;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dcsh.market.Specifications;
import com.dcsh.market.UserGroup;
import com.dcsh.market.Users;
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
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() {
    	System.out.println("Enter Excute");
//        this.resultList = service.getAllUsers();
    	this.resultList = service.getAllGroups();
        System.out.println(resultList);
        return "list";
        //return Action.SUCCESS;
    }
	
    public String modify(){
    	System.out.println("Enter Excute");
//	    service.updateUser(new Users(Integer.valueOf(this.getId()),this.getName(),this.getPassword().getBytes(),this.getDescription(),this.getPhone()));
    	service.updateUserGroup(new UserGroup(Integer.valueOf(this.getId().trim()),this.getName().trim(),this.getDescription().trim()));
		return "modify";
	}
	public String delete(){
		System.out.println("Enter Excute");
//		service.delUser(new Users(Integer.valueOf(this.getId())));
		service.delUserGroup(new UserGroup(Integer.valueOf(this.getId().trim())));
		return "delete";
	}
	public String add(){
		System.out.println("Enter Excute");
//		service.addUser(new Users(this.getNewuname(),this.getNewupass().getBytes(),this.getNewudes(),this.getNewuphone()));
		service.addUserGroup(new UserGroup(this.getNewuname().trim(),this.getNewudes().trim()));
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}

