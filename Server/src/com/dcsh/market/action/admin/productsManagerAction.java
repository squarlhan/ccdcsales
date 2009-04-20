package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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
	 
	        this.service = service;
	    }
	public String execute() {
    	
        this.resultList = service.getAllProducts();
     
        return "list";
    
    }
	public String modify(){
		products = new Products(Integer.valueOf(this.getId().trim()), this.getName().trim());
	    service.updateProduct(products);
		return "modify";
	}
	public String delete(){
		products = new Products();
		products.setId(Integer.valueOf(this.getId().trim()));
		service.delProduct(products);
		return "delete";
	}
	public String add(){
		products = new Products();
		products.setName(newpname.trim());
		service.addProduct(products);
		return "add";
	}
	public void prepare() throws Exception {

    }
}
