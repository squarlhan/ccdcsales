package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.dcsh.market.Fahuo;
import com.dcsh.market.Products;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminfahuomanagerAction implements Preparable{
	 
	private List<Fahuo> resultList;
	private Fahuo fahuo;
	private AdminService service;
	private String newfname;
	private String name;
	private String id;
	

	
	private static final Logger log = LogManager.getLogManager().getLogger(adminfahuomanagerAction.class.getName());

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewfname() {
		return newfname;
	}
	public void setNewfname(String newfname) {
		this.newfname = newfname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Fahuo> getResultList() {
		return resultList;
	}
	public void setResultList(List<Fahuo> resultList) {
		this.resultList = resultList;
	}
	public Fahuo getFahuo() {
		return fahuo;
	}
	public void setFahuo(Fahuo fahuo) {
		this.fahuo = fahuo;
	}
	public adminfahuomanagerAction(AdminService service) {
	    	System.out.println("Enter Constructor");
	        this.service = service;
	    }
	public String execute() {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllFahuos();
        System.out.println(resultList);
        return "list";
    }
	public String modify(){
		System.out.println("Enter Excute");
		System.out.println("modify");
		System.out.println(this.getId().trim()+this.getName().trim());
		fahuo = new Fahuo(Integer.valueOf(this.getId().trim()), this.getName().trim());
	    service.updateFahuo(fahuo);
		return "modify";
	}
	public String delete(){
		System.out.println("delete");
		System.out.println(this.getId().trim());
		fahuo = new Fahuo();
		fahuo.setId(Integer.valueOf(this.getId().trim()));
		service.delFahuo(fahuo);
		return "delete";
	}
	public String add(){
		System.out.println("add!!"+newfname);
		fahuo = new Fahuo();
		fahuo.setName(newfname.trim());
		service.addFahuo(fahuo);
		return "add";
	}
	public void prepare() throws Exception {

    }
}
