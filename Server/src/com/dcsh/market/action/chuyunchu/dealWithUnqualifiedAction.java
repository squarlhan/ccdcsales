package com.dcsh.market.action.chuyunchu;

import java.math.BigDecimal;
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
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class dealWithUnqualifiedAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(deliveryWareHouseAction.class.getName());
	private WareHouseService service;
	
	private int cankuorgin;
	private int cankuaim;
	private int users;
	private int customer;
	private Chuku chuku;
	private String bno;
	private Set<Chukumx> chukumxes = new HashSet();
	private List<Integer> product;
    private List<Integer> specification;
    private List<String> pch;
    private List<Integer> number;
    private Chukumx chukumx;
    private Products newproduct;
    private Specifications newspecification;
    private String dealmethod;
    
	public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
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


	public List<String> getPch() {
		return pch;
	}


	public void setPch(List<String> pch) {
		this.pch = pch;
	}


	public List<Integer> getNumber() {
		return number;
	}


	public void setNumber(List<Integer> number) {
		this.number = number;
	}


	public dealWithUnqualifiedAction(WareHouseService service) {

        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {   

    	for(int i=0;i<=this.getProduct().size()-1;i++){
    	    this.newproduct = new Products(this.getProduct().get(i),null);
    	    this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
    	    this.chukumx = 
    		    new Chukumx(newproduct,null,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i));
    	    this.getChukumxes().add(chukumx);
    	}
    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	 if(Integer.valueOf(dealmethod)==1)
    	  this.chuku = new Chuku(
          		user.get(0).getCanku(),
          		new Canku(12,null,(byte) 5,null,null,null,null),
          		user.get(0).getUser(),
          		null,//销毁时客户作废
          		this.getBno().trim(),
          		new Date(),
          		this.getChukumxes());
    	 else
    		 if(Integer.valueOf(dealmethod)==2)
    			 this.chuku = new Chuku(
    		          		user.get(0).getCanku(),
    		          		new Canku(11,null,(byte) 3,null,null,null,null),
    		          		user.get(0).getUser(),
    		          		new Custom(this.getCustomer(),null,null),
    		          		this.getBno().trim(),
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




	public int getCustomer() {
		return customer;
	}


	public void setCustomer(int customer) {
		this.customer = customer;
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
