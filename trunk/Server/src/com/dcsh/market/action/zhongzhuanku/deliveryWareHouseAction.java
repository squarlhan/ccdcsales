package com.dcsh.market.action.zhongzhuanku;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.dcsh.market.EntryPrintInfo;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class deliveryWareHouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(deliveryWareHouseAction.class.getName());
	private ZhongZhuanKuService service;
	
	private int cankuorgin;
	private int cankuaim;
	private int users;
	private int custom;
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
    private int index;
    private int tnumber;//移库总数
    private List<EntryPrintInfo> resultList;
    private String date;
    private String printCankuaim;
    private String printCustom;
    private EntryPrintInfo epi;
    private List<BigDecimal> sumweight;
    private int ismerge;

	public List<EntryPrintInfo> getResultList() {
		return resultList;
	}


	public int getIsmerge() {
		return ismerge;
	}


	public void setIsmerge(int ismerge) {
		this.ismerge = ismerge;
	}


	public void setResultList(List<EntryPrintInfo> resultList) {
		this.resultList = resultList;
	}


	public ZhongZhuanKuService getService() {
		return service;
	}


	public void setService(ZhongZhuanKuService service) {
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


	public deliveryWareHouseAction(ZhongZhuanKuService service) {

        this.service = service;
    }

    
	public String execute() throws Exception {
    
    	for(int i=0;i<=this.getProduct().size()-1;i++){
    	    this.newproduct = new Products(this.getProduct().get(i),null);
    	    this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
    	    this.chukumx = 
    		    new Chukumx(newproduct,null,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i));
    	    this.getChukumxes().add(chukumx);
    	}
    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	
    	  this.chuku = new Chuku(
          		user.get(0).getCanku(),
          		new Canku(this.getCankuaim(),null,(byte) 0,null,null,null,null),
          		user.get(0).getUser(),
          		new Custom(this.getCustom(),null,null),
          		this.getBno().trim(),
          		new Date(),
          		this.getChukumxes());

          service.doDeliveryWareHouse(chuku);
    	
    	
        return "ok";
    }
	
	public String yiku() throws Exception {
   
    	for(int i=0;i<=this.getProduct().size()-1;i++){
    	    this.newproduct = new Products(this.getProduct().get(i),null);
    	    this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
    	    this.chukumx = 
    		    new Chukumx(newproduct,null,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i));
    	    this.getChukumxes().add(chukumx);
    	}
    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	
    	  this.chuku = new Chuku(
          		user.get(0).getCanku(),
          		new Canku(this.getCankuaim(),null,(byte) 0,null,null,null,null),
          		user.get(0).getUser(),
          		new Custom(this.getCustom(),null,null),
          		this.getBno().trim(),
          		new Date(),
          		this.getChukumxes());
       
          Integer tmp = 0;
          for(int i=0;i<this.getNumber().size();i++){
        	  tmp+=this.getNumber().get(i);
          }
          if((tmp==this.getTnumber())||(0!=this.getIsmerge())){
    		service.doDeliveryWareHouse(chuku);
    		service.resetXsykxxStatus(index, tmp);
    		return printxsyk();
    	}
        
    	return "unequal";
        
    }
	public String printxsyk() throws Exception{
	
		resultList=new ArrayList();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日"); 
		Date d = new Date(); 
		setDate(bartDateFormat.format(d)); 
		printCustom=service.getCustomerById(custom).getCustomName();
		this.setPrintCankuaim(service.getCangkuById(cankuaim).getName());
		for(int i=0;i<=this.getProduct().size()-1;i++){
			List<Products> product = service.getProductNameById(this.getProduct().get(i));
			List<Specifications> specification = service.getSpecificationNameById(this.getSpecification().get(i));
			
			this.epi = 
	    		new EntryPrintInfo(product.get(0).getName(),specification.get(0).getName(),specification.get(0).getPackType(),this.getNumber().get(i),this.getSumweight().get(i).toString(),this.getPch().get(i),"");
			resultList.add(epi);
		}
		return "printxsyk";
	}
	public String sale() throws Exception {
				
		int salecangku = 11;//销售仓库id 11

    	for(int i=0;i<=this.getProduct().size()-1;i++){
    	    this.newproduct = new Products(this.getProduct().get(i),null);
    	    this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
    	    this.chukumx = 
    		    new Chukumx(newproduct,null,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i));
    	    this.getChukumxes().add(chukumx);
    	}
    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	
    	  this.chuku = new Chuku(
          		user.get(0).getCanku(),
          		new Canku(salecangku,null,(byte) 3,null,null,null,null),//销售仓库类型3
          		user.get(0).getUser(),
          		new Custom(this.getCustom(),null,null),
          		this.getBno().trim(),
          		new Date(),
          		this.getChukumxes());
          
          Integer tmp = 0;
          for(int i=0;i<this.getNumber().size();i++){
        	  tmp+=this.getNumber().get(i);
          }
          if((tmp==this.getTnumber())||(0!=this.getIsmerge())){
    		service.doDeliveryWareHouse(chuku);
    		service.resetXsfhxxStatus(index, tmp);
    		return printfh();
    	}
        
    	return "unequal";
    }

	public String printfh() throws Exception{
		resultList=new ArrayList();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日"); 
		Date d = new Date(); 
		setDate(bartDateFormat.format(d)); 
		setPrintCustom(service.getCustomerById(custom).getCustomName());
		for(int i=0;i<=this.getProduct().size()-1;i++){
			List<Products> product = service.getProductNameById(this.getProduct().get(i));
			List<Specifications> specification = service.getSpecificationNameById(this.getSpecification().get(i));
			
			this.epi = 
	    		new EntryPrintInfo(product.get(0).getName(),specification.get(0).getName(),specification.get(0).getPackType(),this.getNumber().get(i),this.getSumweight().get(i).toString(),this.getPch().get(i),"");
			resultList.add(epi);
		}
		return "printfh";
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


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getTnumber() {
		return tnumber;
	}


	public void setTnumber(int tnumber) {
		this.tnumber = tnumber;
	}


	public void prepare() throws Exception {

    }


	public void setDate(String date) {
		this.date = date;
	}


	public String getDate() {
		return date;
	}


	public void setPrintCustom(String printCustom) {
		this.printCustom = printCustom;
	}


	public String getPrintCustom() {
		return printCustom;
	}


	public void setSumweight(List<BigDecimal> sumweight) {
		this.sumweight = sumweight;
	}


	public List<BigDecimal> getSumweight() {
		return sumweight;
	}


	public void setPrintCankuaim(String printCankuaim) {
		this.printCankuaim = printCankuaim;
	}


	public String getPrintCankuaim() {
		return printCankuaim;
	}

}
