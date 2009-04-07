package com.dcsh.market.action.chuyunchu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.CheckInTable;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;

import com.dcsh.market.ReportPmx;

import com.dcsh.market.priv.CankuPriv;

import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;

public class checkinAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private WareHouseService service;
    private List<CheckInTable> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private Date mydate;
    private boolean flag;
    
    public checkinAction(WareHouseService service) {
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



	public List<CheckInTable> getResultList() {
		return resultList;
	}

	public void setResultList(List<CheckInTable> resultList) {
		this.resultList = resultList;
	}

	public String execute() {
    	System.out.println("Enter Excute");
    	
        return "list";
    	
        
        //return Action.SUCCESS;
    }

	public String getInfoByDate(){
		System.out.println("CheckinAction");
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		if(mydate==null)
    			mydate = new Date();
    		this.resultList = service.getCheckInTable(user.get(0).getCanku(), mydate);
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
    

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public void prepare() throws Exception {

    }

}
