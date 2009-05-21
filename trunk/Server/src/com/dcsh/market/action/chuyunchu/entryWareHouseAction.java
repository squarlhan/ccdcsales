package com.dcsh.market.action.chuyunchu;

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
import com.dcsh.market.EntryPrintInfo;
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
    private List<Integer> orgin;
    private List<Integer> product;
    private List<Integer> specification;
    private List<String> pch;
    private List<Integer> number;
	private List<String> memo;
	private List<BigDecimal> sumweight;
	private int rkczy;
	private Set<Rkmx> rkmxes = new HashSet();
	private EntryPrintInfo epi;
	private String date;
	
	private List<EntryPrintInfo> resultList;
	private Rkxx rkxx;
	
	
	public List<EntryPrintInfo> getResultList() {
		return resultList;
	}


	public void setResultList(List<EntryPrintInfo> resultList) {
		this.resultList = resultList;
	}


	public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
		this.service = service;
	}


	public List<Integer> getOrgin() {
		return orgin;
	}


	public void setOrgin(List<Integer> orgin) {
		this.orgin = orgin;
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

        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		List<Rkmx> temprumxs;
    	
		Map session = ActionContext.getContext().getSession();
//    	if(session.containsKey("temprumxs")){
//    		temprumxs = (List<Rkmx>) session.get("temprumxs");
//    	}else{
    		temprumxs = new ArrayList();
    		session.put("temprumxs", temprumxs);
//    	}
		for(int i=0;i<=this.getProduct().size()-1;i++){
			this.newproduct = new Products(this.getProduct().get(i),null);
			this.newspecification = new Specifications(this.getSpecification().get(i),null,new BigDecimal(0.025),null);
			this.rkmx = 
	    		new Rkmx(null,newproduct,newspecification,this.getPch().get(i).trim(),this.getNumber().get(i),(byte)0,(byte)0,this.getMemo().get(i).trim());
			temprumxs.add(rkmx);
		}
        this.cankusList = service.getAllCankus();
        
    	this.rkmxes.addAll(temprumxs);
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
        this.rkxx = new Rkxx(user.get(0).getCanku(),user.get(0).getUser(),
        		new Users(this.getRkfzr()),
        		this.getBno().trim(),
        		new Date(),
        		this.getRkmxes());
        service.doEntryWareHouse(rkxx);
        if(this.getOrgin()!=null){
        	SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd"); 
    		Date d = new Date(); 
    		for(int a=0;a<=this.getOrgin().size()-1;a++){
    		    String cbno= bartDateFormat.format(d)+this.getOrgin().get(a); 
        	    Chuku chuku = new Chuku();
        	    chuku.setBno(cbno);
        	    chuku.setCankuByCankuId(new Canku(this.getOrgin().get(a),null,(byte)6));
        	    chuku.setCankuByRkId(user.get(0).getCanku());
        	    chuku.setCksj(d);
        	    chuku.setUsers(user.get(0).getUser());
        	    Chukumx chukumx = new Chukumx();
        	    chukumx.setChuku(chuku);
        	    chukumx.setNumber(this.getNumber().get(a));
        	    chukumx.setPch("zzzzzzzzz");
        	    chukumx.setProducts(new Products(this.getProduct().get(a),null));
        	    chukumx.setSpecifications(new Specifications(0,"液体",null,"大罐"));
        	    chukumx.setStatus((byte)1);
        	    chuku.getChukumxes().add(chukumx);
        	    service.doDeliveryWareHouse(chuku);
        	}
        }
        return print();
    }
	
	public String execute1() throws Exception{
    	
		Map session = ActionContext.getContext().getSession();
        List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");

		for(int i=0;i<=this.getProduct().size()-1;i++){
			this.newproduct = new Products(this.getProduct().get(i),null);
			this.newspecification = new Specifications(0,"液体",null,"大罐");
			this.rkmx = 
	    		new Rkmx(null,newproduct,newspecification,"zzzzzzzzz",this.getNumber().get(i),(byte)0,(byte)0,this.getMemo().get(i).trim());
			 this.rkxx = new Rkxx(new Canku(this.getOrgin().get(i),null,(byte)6),
					    user.get(0).getUser(),
		        		new Users(this.getRkfzr()),
		        		this.getBno().trim(),
		        		new Date(),
		        		this.getRkmxes());
			 rkxx.getRkmxes().add(rkmx);
			 service.doEntryWareHouse(rkxx);
		}
        
        return "ok";
    }

	public String print() throws Exception{

		resultList=new ArrayList();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日"); 
		Date d = new Date(); 
		date= bartDateFormat.format(d); 
		for(int i=0;i<=this.getProduct().size()-1;i++){
			List<Products> product = service.getProductNameById(this.getProduct().get(i));
			List<Specifications> specification = service.getSpecificationNameById(this.getSpecification().get(i));
			BigDecimal sum = specification.get(0).getWeight().multiply(new BigDecimal(this.getNumber().get(i)));
			this.epi = 
	    		new EntryPrintInfo(product.get(0).getName(),specification.get(0).getName(),specification.get(0).getPackType(),this.getNumber().get(i),sum.toString(),this.getPch().get(i),this.getMemo().get(i));
			resultList.add(epi);
		}
		
		return "print";
	}
	public void prepare() throws Exception {

    }

	

	public List<BigDecimal> getSumweight() {
		return sumweight;
	}


	public void setSumweight(List<BigDecimal> sumweight) {
		this.sumweight = sumweight;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDate() {
		return date;
	}

}
