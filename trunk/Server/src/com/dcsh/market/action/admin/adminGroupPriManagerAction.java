package com.dcsh.market.action.admin;


import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.UserGroup;
import com.dcsh.market.priv.URLGPriv;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class adminGroupPriManagerAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(adminGroupManagerAction.class.getName());
	private AdminService service;
	private List<UserGroup> resultList;
	private List<URLGPriv> urlGPrivList;
	
	private String selectvalue;
	private String gid;
	
	
	public List<URLGPriv> getUrlGPrivList() {
		return urlGPrivList;
	}

	public void setUrlGPrivList(List<URLGPriv> urlGPrivList) {
		this.urlGPrivList = urlGPrivList;
	}

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

	

	public adminGroupPriManagerAction(AdminService service) {
        this.service = service;
    }
	
	public String execute() throws Exception {


    	this.urlGPrivList = service.getgrouppriv();

        return "list";

    }
	public String reset() throws Exception{
	
		service.setgrouppriv(Integer.valueOf(this.getGid().trim()), Integer.valueOf(this.getSelectvalue().trim()));
		return "reset";
	}
	
   
	
	public String getSelectvalue() {
		return selectvalue;
	}

	public void setSelectvalue(String selectvalue) {
		this.selectvalue = selectvalue;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public void prepare() throws Exception {

    }
}
