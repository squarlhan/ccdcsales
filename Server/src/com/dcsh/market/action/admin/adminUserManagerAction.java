package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Users;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminUserManagerAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(adminUserManagerAction.class.getName());
	private AdminService service;
	private List<Users> resultList;
	
	private String newuname;
	private String newupass;
	private String newuphone;
	private String newudes;
	
	private String id;
	private String name;
	private String password;
	private String phone;
	private String description;
	
	
	
	
	public List<Users> getResultList() {
		return resultList;
	}

	public void setResultList(List<Users> resultList) {
		this.resultList = resultList;
	}

	public String getNewuname() {
		return newuname;
	}

	public void setNewuname(String newuname) {
		this.newuname = newuname;
	}

	public String getNewupass() {
		return newupass;
	}

	public void setNewupass(String newupass) {
		this.newupass = newupass;
	}

	public String getNewuphone() {
		return newuphone;
	}

	public void setNewuphone(String newuphone) {
		this.newuphone = newuphone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public adminUserManagerAction(AdminService service) {
        this.service = service;
    }
	
	public String execute() throws Exception {
    
        this.resultList = service.getAllUsers();
     
        return "list";
      
    }
	
    public String modify() throws Exception{
    
    	if(this.getName().trim().equals(this.getPassword().trim())){
    		service.updateUser(new Users(Integer.valueOf(this.getId()),this.getName().trim(),this.getPassword().trim().getBytes(),this.getDescription().trim(),this.getPhone().trim()), 0);
    	}
	    service.updateUser(new Users(Integer.valueOf(this.getId()),this.getName().trim(),this.getPassword().trim().getBytes(),this.getDescription().trim(),this.getPhone().trim()), 1);
		return "modify";
	}
	public String delete() throws Exception{
			
		service.delUser(new Users(Integer.valueOf(this.getId())));
		return "delete";
	}
	public String add() throws Exception{	
		service.addUser(new Users(this.getNewuname().trim(),this.getNewupass().trim().getBytes(),this.getNewudes().trim(),this.getNewuphone().trim()));
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}

