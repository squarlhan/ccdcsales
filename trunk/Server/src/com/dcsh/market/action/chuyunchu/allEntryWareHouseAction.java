package com.dcsh.market.action.chuyunchu;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Users;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class allEntryWareHouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(allEntryWareHouseAction.class.getName());
	private WareHouseService service;
	public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
		this.service = service;
	}


	private Set<Rkmx> rkmxes = new HashSet();
	public Set<Rkmx> getRkmxes() {
		return rkmxes;
	}


	public void setRkmxes(Set<Rkmx> rkmxes) {
		this.rkmxes = rkmxes;
	}


	private int canku;
	private int rkczy;
	private int rkfzr;
	private Rkxx rkxx;
	public Rkxx getRkxx() {
		return rkxx;
	}


	public void setRkxx(Rkxx rkxx) {
		this.rkxx = rkxx;
	}


	public int getCanku() {
		return canku;
	}


	public void setCanku(int canku) {
		this.canku = canku;
	}


	public int getRkczy() {
		return rkczy;
	}


	public void setRkczy(int rkczy) {
		this.rkczy = rkczy;
	}


	public int getRkfzr() {
		return rkfzr;
	}


	public void setRkfzr(int rkfzr) {
		this.rkfzr = rkfzr;
	}


	public String getBno() {
		return bno;
	}


	public void setBno(String bno) {
		this.bno = bno;
	}


	private String bno;


	public allEntryWareHouseAction(WareHouseService service) {
        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	
    	Map session = ActionContext.getContext().getSession();
    	List<Rkmx> temprumxs = (List<Rkmx>) session.get("temprumxs");
    	session.put("temprumxs", null);
    	this.rkmxes.addAll(temprumxs);
        this.rkxx = new Rkxx(new Canku(this.getCanku(),null,(byte) 0),
        		new Users(this.getRkczy()),
        		new Users(this.getRkfzr()),
        		this.getBno().trim(),
        		new Date(),
        		this.getRkmxes());
        service.doEntryWareHouse(rkxx);
        
        return "gork";
    }
	


	public void prepare() throws Exception {

    }

}

