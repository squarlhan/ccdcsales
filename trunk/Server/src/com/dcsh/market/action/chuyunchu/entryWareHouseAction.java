package com.dcsh.market.action.chuyunchu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class entryWareHouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(entryWareHouseAction.class.getName());
	private WareHouseService service;
	private List<Canku> cankusList;
	private Rkmx rkmx;
    private Products newproduct;
    private Specifications newspecification;
    private int rkfzr;
    private String bno;
    private int canku;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<Integer> product;
    private List<Integer> specification;
    private List<String> pch;
    private List<Integer> number;
	private List<String> memo;
	private int rkczy;
	private Set<Rkmx> rkmxes = new HashSet();
	private Rkxx rkxx;
	
	public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
		this.service = service;
	}


	public List<Canku> getCankusList() {
		return cankusList;
	}


	public void setCankusList(List<Canku> cankusList) {
		this.cankusList = cankusList;
	}


	public Rkmx getRkmx() {
		return rkmx;
	}


	public void setRkmx(Rkmx rkmx) {
		this.rkmx = rkmx;
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


	public int getCanku() {
		return canku;
	}


	public void setCanku(int canku) {
		this.canku = canku;
	}


	public List<Products> getProductsList() {
		return productsList;
	}


	public void setProductsList(List<Products> productsList) {
		this.productsList = productsList;
	}


	public List<Specifications> getSpecificationsList() {
		return specificationsList;
	}


	public void setSpecificationsList(List<Specifications> specificationsList) {
		this.specificationsList = specificationsList;
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


	public List<String> getMemo() {
		return memo;
	}


	public void setMemo(List<String> memo) {
		this.memo = memo;
	}


	public int getRkczy() {
		return rkczy;
	}


	public void setRkczy(int rkczy) {
		this.rkczy = rkczy;
	}


	public Set<Rkmx> getRkmxes() {
		return rkmxes;
	}


	public void setRkmxes(Set<Rkmx> rkmxes) {
		this.rkmxes = rkmxes;
	}


	public Rkxx getRkxx() {
		return rkxx;
	}


	public void setRkxx(Rkxx rkxx) {
		this.rkxx = rkxx;
	}


	public entryWareHouseAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

    
	public String execute() {
		List<Rkmx> temprumxs;
    	System.out.println("Enter Excute");
    	System.out.println("%%%%%"+this.getSpecification().size());
		System.out.println("%%%%%"+this.getProduct().size());
		Map session = ActionContext.getContext().getSession();
    	if(session.containsKey("temprumxs")){
    		temprumxs = (List<Rkmx>) session.get("temprumxs");
    	}else{
    		temprumxs = new ArrayList();
    		session.put("temprumxs", temprumxs);
    	}
		for(int i=0;i<=this.getProduct().size()-1;i++){
			this.newproduct = new Products(this.getProduct().get(i),null);
			this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
			this.rkmx = 
	    		new Rkmx(null,newproduct,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i),(byte)0,(byte)0,this.getMemo().get(i).trim());
			temprumxs.add(rkmx);
		}
        this.cankusList = service.getAllCankus();
        
        
//        Map session = ActionContext.getContext().getSession();
//    	 temprumxs = (List<Rkmx>) session.get("temprumxs");
//    	session.put("temprumxs", null);
    	System.out.println(temprumxs);
    	System.out.println("220*****************");
    	this.rkmxes.addAll(temprumxs);
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
        this.rkxx = new Rkxx(user.get(0).getCanku(),user.get(0).getUser(),
//        		new Users(this.getRkczy()),
        		new Users(this.getRkfzr()),
        		this.getBno().trim(),
        		new Date(),
        		this.getRkmxes());
    	//return null;
        System.out.println("229*****************");
        service.doEntryWareHouse(rkxx);
        System.out.println("231*****************");
//        return "gork";
        return "ok";
    }


	public void prepare() throws Exception {

    }

}