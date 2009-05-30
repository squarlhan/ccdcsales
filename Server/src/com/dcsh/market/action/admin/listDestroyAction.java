package com.dcsh.market.action.admin;

import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Chukumx;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class listDestroyAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listStorageAction.class.getName());
    private AdminService service;
    private List<ReportCmx> cmxList;
    private List<Chukumx> listchukumx;
    private Date begindate;
    private Date enddate;

	public listDestroyAction(AdminService service) {
        this.service = service;
    }

	public String execute() throws Exception {
    	Date today = new Date();
    	this.cmxList = service.listDestroy(today, today);
    	this.listchukumx = service.listDestroymx(today, today);
        return "list";
    }
	
	public String listBetween() throws Exception {

    	this.cmxList = service.listDestroy(begindate, enddate);
    	this.listchukumx = service.listDestroymx(begindate, enddate);
        return "list";
    }
	
    public AdminService getService() {
		return service;
	}

	public void setService(AdminService service) {
		this.service = service;
	}

	public List<ReportCmx> getCmxList() {
		return cmxList;
	}

	public void setCmxList(List<ReportCmx> cmxList) {
		this.cmxList = cmxList;
	}

	public List<Chukumx> getListchukumx() {
		return listchukumx;
	}

	public void setListchukumx(List<Chukumx> listchukumx) {
		this.listchukumx = listchukumx;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public void prepare() throws Exception {

    }
}
