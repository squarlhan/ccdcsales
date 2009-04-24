package com.dcsh.market.action.admin;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;


public class listStorageAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listStorageAction.class.getName());
    private AdminService service;
    private List<ReportPmx> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private Date date;
    private List<Canku> cangkusList;

	public listStorageAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

    public Kcxx getKcxx() {
		return kcxx;
	}

	public void setKcxx(Kcxx kcxx) {
		this.kcxx = kcxx;
	}

	public void setresultList(List<ReportPmx> kcxxs) {
		this.resultList = kcxxs;
	}

	@SuppressWarnings("unchecked")
	public String execute1() {
		
		if(this.date==null)
			this.date = new Date();

    	this.resultList = service.listStorage(canku,date);
    	List<Canku> tempcankus = service.getAllCankus();
    	tempcankus.add(0, new Canku(0,"全部",(byte)0));
    	for(Iterator it = tempcankus.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getId()==11)||(tmpck.getId()==12)){
    			it.remove();
    		}
    	}
    	this.cangkusList = tempcankus;

        return "list";
    
    }

	
	@SuppressWarnings("unchecked")
	public String execute() {

    	this.resultList = service.listStorage(0,new Date());
    	List<Canku> tempcankus = service.getAllCankus();
    	tempcankus.add(0, new Canku(0,"全部",(byte)0));
    	for(Iterator it = tempcankus.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getId()==11)||(tmpck.getId()==12)){
    			it.remove();
    		}
    	}
    	this.cangkusList = tempcankus;
    
        this.date = new Date();
        return "list";
     
    }
     public List<ReportPmx> getresultList() {
        return resultList;
    }

    public Integer getCanku() {
        return canku;
    }

    public void setCanku(Integer canku) {
        this.canku = canku;
    }

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Canku> getCangkusList() {
		return cangkusList;
	}

	public void setCangkusList(List<Canku> cangkusList) {
		this.cangkusList = cangkusList;
	}
	
    public void prepare() throws Exception {

    }
}
