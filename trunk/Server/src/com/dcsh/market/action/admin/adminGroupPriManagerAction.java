package com.dcsh.market.action.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.UserGroup;

import com.dcsh.market.ReportPmx;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.CankuPrivG;
import com.dcsh.market.priv.URLGPriv;

import com.dcsh.market.service.AdminService;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts2.ServletActionContext;

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
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() {
    	System.out.println("Enter Excute");

    	this.urlGPrivList = service.getgrouppriv();

        return "list";
        //return Action.SUCCESS;
    }
	public String reset(){
		System.out.println("selectvalue="+this.getSelectvalue().trim()+"gid="+this.getGid().trim());
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
