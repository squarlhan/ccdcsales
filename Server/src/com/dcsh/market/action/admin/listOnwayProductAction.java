package com.dcsh.market.action.admin;

import java.util.Iterator;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class listOnwayProductAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listOnwayProductAction.class.getName());
    private AdminService service;
    private List<Chukumx> resultList;
    private List<Canku> cangkusList;
    private List<Canku> cangkusList1;
    private int org;
    private int aim;

    public listOnwayProductAction(AdminService service) {

        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		cangkusList = service.getAllCankus();
		for(Iterator it = cangkusList.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getType()==(byte)6)||(tmpck.getType()==(byte)1)){
    			it.remove();
    		}
    	}
		cangkusList.add(0, new Canku(0,"全部",(byte)0));
		cangkusList1 = service.getAllCankus();
		for(Iterator it = cangkusList1.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if(tmpck.getType()==(byte)6){
    			it.remove();
    		}
    	}
		cangkusList1.add(0, new Canku(0,"全部",(byte)0));
    	this.resultList = service.listOnwayProducts(0,0);
        return "listonway";  
    }
	
	public String execute1() throws Exception {
		cangkusList = service.getAllCankus();
		for(Iterator it = cangkusList.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getType()==(byte)6)||(tmpck.getType()==(byte)1)){
    			it.remove();
    		}
    	}
		cangkusList.add(0, new Canku(0,"全部",(byte)0));
		cangkusList1 = service.getAllCankus();
		for(Iterator it = cangkusList1.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if(tmpck.getType()==(byte)6){
    			it.remove();
    		}
    	}
		cangkusList1.add(0, new Canku(0,"全部",(byte)0));
    	this.resultList = service.listOnwayProducts(this.getOrg(),this.getAim());
        return "listonway";  
    }


    public List<Chukumx> getResultList() {
		return resultList;
	}


	public void setResultList(List<Chukumx> resultList) {
		this.resultList = resultList;
	}  



	public List<Canku> getCangkusList() {
		return cangkusList;
	}


	public void setCangkusList1(List<Canku> cangkusList1) {
		this.cangkusList1 = cangkusList1;
	}
	
	public List<Canku> getCangkusList1() {
		return cangkusList1;
	}


	public void setCangkusList(List<Canku> cangkusList) {
		this.cangkusList = cangkusList;
	}



	public int getOrg() {
		return org;
	}


	public void setOrg(int org) {
		this.org = org;
	}


	public int getAim() {
		return aim;
	}


	public void setAim(int aim) {
		this.aim = aim;
	}


	public void prepare() throws Exception {

    }

}
