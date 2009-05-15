package com.dcsh.market.action.xiaoshou;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Chukumx;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class listSalesAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listSalesAction.class.getName());
    private XiaoshouService service;
    private List<ReportCmx> cmxList;
    private List<Chukumx> listchukumx;
    private Date begindate;
    private Date enddate;

	public listSalesAction(XiaoshouService service) {

        this.service = service;
    }
	
	public List<Products> getproducts(){
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	     List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.PRD);
	     List<Products> plist = new ArrayList();
	     if(list.size() > 0){
			for (ResourceGrantedAuthorityImpl res : list) {
				plist.add((Products) res.getResource());
			}
		}
		return plist;
	}

	public String execute() throws Exception {
        
    	Date today = new Date();
    	this.cmxList = service.listSales(this.getproducts(),today, today);
    	this.listchukumx = service.listSalesmx(this.getproducts(),today, today);
        return "list";
    }
	
	public String listBetween() throws Exception {

    	this.cmxList = service.listSales(this.getproducts(),begindate, enddate);
    	this.listchukumx = service.listSalesmx(this.getproducts(),begindate, enddate);
        return "list";
    }
	
    public XiaoshouService getService() {
		return service;
	}

	public void setService(XiaoshouService service) {
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

