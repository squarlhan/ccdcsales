package com.dcsh.market.action.chuyunchu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.CheckOutTable;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.ReportCmx;

import com.dcsh.market.ReportPmx;

import com.dcsh.market.priv.CankuPriv;

import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.WareHouseServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;

public class checkoutAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private WareHouseService service;
    private List<CheckOutTable> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private List<ReportPmx> pmxList;
    private Date mydate;
    private boolean flag;
    
    public checkoutAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

    public Kcxx getKcxx() {
		return kcxx;
	}

	public void setKcxx(Kcxx kcxx) {
		this.kcxx = kcxx;
	}
	
	



	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}



	public List<CheckOutTable> getResultList() {
		return resultList;
	}

	public void setResultList(List<CheckOutTable> resultList) {
		this.resultList = resultList;
	}

	public String execute() {
    	System.out.println("Enter Excute");
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		this.resultList = service.getCheckOutTable(user.get(0).getCanku(), mydate);
    		System.out.println("tempuser: "+user.get(0).getCanku().getId());
            return "list";
    	}
        
        //return Action.SUCCESS;
    }

	public String getInfoByDate(){
		System.out.println("CheckoutAction");
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0) return "input";
    	else{
    		if(mydate==null)
    			mydate = new Date();
    		this.resultList = service.getCheckOutTable(user.get(0).getCanku(), mydate);
    		System.out.println("tempuser: "+user.get(0).getCanku().getId());
    		return "infoList";
    	}
   
	}
	

    public Integer getCanku() {
        return canku;
    }

    public void setCanku(Integer canku) {
        this.canku = canku;
    }
    
    public List<ReportPmx> getPmxList() {
		return pmxList;
	}

	public void setPmxList(List<ReportPmx> pmxList) {
		this.pmxList = pmxList;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public void prepare() throws Exception {

    }
}
