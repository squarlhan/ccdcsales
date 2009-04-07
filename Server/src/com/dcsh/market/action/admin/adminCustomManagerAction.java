package com.dcsh.market.action.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dcsh.market.Custom;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminCustomManagerAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(adminCustomManagerAction.class.getName());
	private AdminService service;
	private List<Custom> resultList;
	
	private int id;
	private String customName;
	private String shdz;
	private String phone;
	
	private String newname;
	private String newshdz;
	private String newuphone;
	
	
	public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public List<Custom> getResultList() {
		return resultList;
	}

	public void setResultList(List<Custom> resultList) {
		this.resultList = resultList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getShdz() {
		return shdz;
	}

	public void setShdz(String shdz) {
		this.shdz = shdz;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNewname() {
		return newname;
	}

	public void setNewname(String newname) {
		this.newname = newname;
	}

	public String getNewshdz() {
		return newshdz;
	}

	public void setNewshdz(String newshdz) {
		this.newshdz = newshdz;
	}

	public String getNewuphone() {
		return newuphone;
	}

	public void setNewuphone(String newuphone) {
		this.newuphone = newuphone;
	}

	public adminCustomManagerAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllCustoms();
        System.out.println(resultList);
        return "list";
        //return Action.SUCCESS;
    }
	public String modify(){
    	System.out.println("Enter Excute");
    	System.out.println("id"+this.getId());
    	System.out.println("name"+this.getCustomName());
    	System.out.println("shdz"+this.getShdz());
    	System.out.println("phone"+this.getPhone());
	    service.updateCustom(new Custom((Integer.valueOf(this.getId())),this.getCustomName().trim(),this.getShdz().trim(),this.getPhone().trim()));
		return "modify";
	}
	public String delete(){
		System.out.println("Enter Excute");
		service.delCustom(new Custom(Integer.valueOf(this.getId())));
		return "delete";
	}
	
	public String add(){
		System.out.println("Enter Excute");
		service.addCustom(new Custom(this.getNewname().trim(),this.getNewshdz().trim(),this.getNewuphone().trim()));
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}

