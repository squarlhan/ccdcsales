package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.dcsh.market.Products;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class productsManagerAction implements Preparable{
	 
	private List<Products> resultList;
	private Products products;
	private AdminService service;
	private String newpname;
	private String name;
	private String id;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewpname() {
		return newpname;
	}
	public void setNewpname(String newpname) {
		this.newpname = newpname;
	}
	private static final Logger log = LogManager.getLogManager().getLogger(productsManagerAction.class.getName());
	public List<Products> getResultList() {
		return resultList;
	}
	public void setResultList(List<Products> resultList) {
		this.resultList = resultList;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public productsManagerAction(AdminService service) {
	    	System.out.println("Enter Constructor");
	        this.service = service;
	    }
	public String execute() {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllProducts();
        System.out.println(resultList);
        return "list";
        //return Action.SUCCESS;
    }
	public String modify(){
//		System.out.println("Enter Excute");
//		HttpServletRequest request = ServletActionContext.getRequest();
//		System.out.println("modify");
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		System.out.println(this.getId().trim()+this.getName().trim());
		products = new Products(Integer.valueOf(this.getId().trim()), this.getName().trim());
	    service.updateProduct(products);
		return "modify";
	}
	public String delete(){
	//	HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("delete");
	//	String id = request.getParameter("id");
		System.out.println(this.getId().trim());
		products = new Products();
		products.setId(Integer.valueOf(this.getId().trim()));
		service.delProduct(products);
		return "delete";
	}
	public String add(){
		System.out.println("add!!"+newpname);
		products = new Products();
		products.setName(newpname.trim());
		service.addProduct(products);
		return "add";
	}
	public void prepare() throws Exception {

    }
}
