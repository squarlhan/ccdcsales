package com.dcsh.market.action.chuyunchu;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Users;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class allDeliveryWareHouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(allDeliveryWareHouseAction.class.getName());
	private WareHouseService service;
	public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
		this.service = service;
	}


	private Set<Chukumx> chukumxes = new HashSet();
	public Set<Chukumx> getChukumxes() {
		return chukumxes;
	}


	public void setChukumxes(Set<Chukumx> chukumxes) {
		this.chukumxes = chukumxes;
	}


	private int cankuorgin;
	private int cankuaim;
	private int users;
	private int custom;
	private Chuku chuku;
	public Chuku getChuku() {
		return chuku;
	}


	public void setChuku(Chuku chuku) {
		this.chuku = chuku;
	}


	public int getCankuorgin() {
		return cankuorgin;
	}


	public void setCankuorgin(int cankuorgin) {
		this.cankuorgin = cankuorgin;
	}


	public int getCankuaim() {
		return cankuaim;
	}


	public void setCankuaim(int cankuaim) {
		this.cankuaim = cankuaim;
	}


	public int getUsers() {
		return users;
	}


	public void setUsers(int users) {
		this.users = users;
	}


	public int getCustom() {
		return custom;
	}


	public void setCustom(int custom) {
		this.custom = custom;
	}


	public String getBno() {
		return bno;
	}


	public void setBno(String bno) {
		this.bno = bno;
	}


	private String bno;


	public allDeliveryWareHouseAction(WareHouseService service) {
        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() {
    	
    	Map session = ActionContext.getContext().getSession();
    	List<Chukumx> tempchukumxs = (List<Chukumx>) session.get("tempchukumxs");
    	this.chukumxes.addAll(tempchukumxs);
        this.chuku = new Chuku(
        		new Canku(this.getCankuorgin(),null,(byte) 0,null,null,null,null),
        		new Canku(this.getCankuaim(),null,(byte) 0,null,null,null,null),
        		new Users(this.getUsers()),
        		new Custom(this.getCustom(),null,null),
        		this.getBno().trim(),
        		new Date(),
        		this.getChukumxes());

        service.doDeliveryWareHouse(chuku);
        session.put("tempchukumxs", null);
        return "gock";
    }
	


	public void prepare() throws Exception {

    }

}

