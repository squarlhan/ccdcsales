package com.dcsh.market.action.admin;

import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Kcxx;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class listLossAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listStorageAction.class.getName());
    private AdminService service;
    private List<ReportCmx> cmxList;
    private List<Chukumx> listchukumx;
    private Date begindate;
    private Date enddate;

	public listLossAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

	public String execute() {
    	System.out.println("Enter Excute");
    	Date today = new Date();
    	this.cmxList = service.listLoss(today, today);
    	this.listchukumx = service.listLossmx(today, today);
        return "list";
        //return Action.SUCCESS;
    }
	
	public String listBetween() {
    	System.out.println("Enter Excute");
    	this.cmxList = service.listLoss(begindate, enddate);
    	this.listchukumx = service.listLossmx(begindate, enddate);
        return "list";
        //return Action.SUCCESS;
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
