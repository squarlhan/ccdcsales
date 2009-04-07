package com.dcsh.market.action.zhuchangku;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dcsh.market.Canku;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Products;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class dealWithLostAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(deliveryWareHouseAction.class.getName());
	private ZhuChangKuService service;
	
	private int cankuorgin;
	private int cankuaim;
	private int users;
	private int custom;
	private Chuku chuku;
	private String bno;
	private Set<Chukumx> chukumxes = new HashSet();
	private List<Integer> product;
    private List<Integer> specification;
    private List<Integer> number;
    private Chukumx chukumx;
    private Products newproduct;
    private Specifications newspecification;
    private String dealmethod;
    private String pch;
    private String pid;
    private String spid;
    private String lostvalue;
	public ZhuChangKuService getService() {
		return service;
	}


	public void setService(ZhuChangKuService service) {
		this.service = service;
	}


	
    public Products getNewproduct() {
		return newproduct;
	}


	public void setNewproduct(Products newproduct) {
		this.newproduct = newproduct;
	}


	public Specifications getNewspecification() {
		return newspecification;
	}


	public void setNewspecification(Specifications newspecification) {
		this.newspecification = newspecification;
	}


	

	public Chukumx getChukumx() {
		return chukumx;
	}


	public void setChukumx(Chukumx chukumx) {
		this.chukumx = chukumx;
	}

	public List<Integer> getProduct() {
		return product;
	}


	public void setProduct(List<Integer> product) {
		this.product = product;
	}


	public List<Integer> getSpecification() {
		return specification;
	}


	public void setSpecification(List<Integer> specification) {
		this.specification = specification;
	}

	



	public String getPch() {
		return pch;
	}


	public void setPch(String pch) {
		this.pch = pch;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getSpid() {
		return spid;
	}


	public void setSpid(String spid) {
		this.spid = spid;
	}


	public String getLostvalue() {
		return lostvalue;
	}


	public void setLostvalue(String lostvalue) {
		this.lostvalue = lostvalue;
	}


	public List<Integer> getNumber() {
		return number;
	}


	public void setNumber(List<Integer> number) {
		this.number = number;
	}


	public dealWithLostAction(ZhuChangKuService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

    
	public String execute() {
    	System.out.println("Enter Excute");
    	
    	this.newproduct = new Products(Integer.valueOf(this.getPid()),null);
    	this.newspecification = new Specifications(Integer.valueOf(this.getSpid()),null,new BigDecimal(0.025),null);
    	this.chukumx = new Chukumx(newproduct,null,newspecification,this.getPch(),Integer.valueOf(this.getLostvalue()));
    	this.getChukumxes().add(chukumx);

    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhuchanguser");
    	this.chuku = new Chuku(
          		user.get(0).getCanku(),
          		new Canku(0,null,(byte) 4,null,null,null,null),
          		user.get(0).getUser(),
          		new Custom(1,null,null),
          		"000",
          		new Date(),
          		this.getChukumxes());
         service.doDeliveryWareHouse(chuku);
    	
        return "ok";
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


	public Chuku getChuku() {
		return chuku;
	}


	public void setChuku(Chuku chuku) {
		this.chuku = chuku;
	}


	public String getBno() {
		return bno;
	}


	public void setBno(String bno) {
		this.bno = bno;
	}


	public Set<Chukumx> getChukumxes() {
		return chukumxes;
	}


	public void setChukumxes(Set<Chukumx> chukumxes) {
		this.chukumxes = chukumxes;
	}


	public void setDealmethod(String dealmethod) {
		this.dealmethod = dealmethod;
	}


	public String getDealmethod() {
		return dealmethod;
	}


	public void prepare() throws Exception {

    }
}
