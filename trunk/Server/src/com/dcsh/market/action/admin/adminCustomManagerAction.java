package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Custom;
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
    
        this.service = service;
    }
	
	public String execute()  throws Exception{
    
        this.resultList = service.getAllCustoms();
     
        return "list";
      
    }
	public String modify() throws Exception{
    
	    service.updateCustom(new Custom((Integer.valueOf(this.getId())),this.getCustomName().trim(),this.getShdz().trim(),this.getPhone().trim()));
		return "modify";
	}
	public String delete() throws Exception{
		
		service.delCustom(new Custom(Integer.valueOf(this.getId())));
		return "delete";
	}
	
	public String add() throws Exception{
	
		service.addCustom(new Custom(this.getNewname().trim(),this.getNewshdz().trim(),this.getNewuphone().trim()));
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}

