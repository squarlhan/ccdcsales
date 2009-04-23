package com.dcsh.market.action.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Specifications;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminspemanagerAction implements Preparable {
	private List<Specifications> resultList;
	private Specifications specifications;
	private AdminService service;
	private String newsname;
	private String newsweight;
	private String newswrapp;
	
	private String id;
	private String name;
	private String weight;
	private String packtype;


	private static final Logger log = LogManager.getLogManager().getLogger(productsManagerAction.class.getName());
	public List<Specifications> getResultList() {
		return resultList;
	}
	public void setResultList(List<Specifications> resultList) {
		this.resultList = resultList;
	}
	
	
	public String getNewsname() {
		return newsname;
	}
	public void setNewsname(String newsname) {
		this.newsname = newsname;
	}
	public String getNewsweight() {
		return newsweight;
	}
	public void setNewsweight(String newsweight) {
		this.newsweight = newsweight;
	}
	public String getNewswrapp() {
		return newswrapp;
	}
	public void setNewswrapp(String newswrapp) {
		this.newswrapp = newswrapp;
	}
	
	public Specifications getSpecifications() {
		return specifications;
	}
	public void setSpecifications(Specifications specifications) {
		this.specifications = specifications;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPacktype() {
		return packtype;
	}
	public void setPacktype(String packtype) {
		this.packtype = packtype;
	}
	public adminspemanagerAction(AdminService service) {
	
	        this.service = service;
	    }
	public String execute() {
   
        this.resultList = service.getAllSpecifications();
      
        return "list";
    }
	public String modify(){
	
		specifications = new Specifications();
		specifications.setId(Integer.valueOf(this.getId().trim()));
		specifications.setName(this.getName().trim());
		specifications.setWeight(new BigDecimal(this.getWeight().trim()));
		specifications.setPackType(this.getPacktype().trim());
	    service.updateSpecification(specifications);
		return "modify";
	}
	public String delete(){

		specifications = new Specifications();
		specifications.setId(Integer.valueOf(this.getId().trim()));
		service.delSpecification(specifications);
		return "delete";
	}
	public String add(){
	
		specifications = new Specifications();
		specifications.setName(newsname.trim());
		specifications.setWeight(new BigDecimal(newsweight.trim()));
		specifications.setPackType(newswrapp.trim());
		service.addSpecification(specifications);
		return "add";
	}
	public void prepare() throws Exception {

    }
}