package com.dcsh.market.action.xiaoshou;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.Products;
import com.dcsh.market.SalesPrintInfo;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.action.chuyunchu.saveReportAction;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class salesNotificationAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(saveReportAction.class.getName());
	private XiaoshouService service;
	
	private String domains;
	private Date mydate;
	private String bno;
	private String orgin;
	private String cno;
	private int customer;
	private int delivertype;
	private int jstype;
	private String memo;
	private String myshr;
	private String mynhr;
	private List<Integer> deli_canku;
	private List<Integer> product;
	private List<Integer> specification;
	private List<Integer> deli_num;
	private List<String> price;
	private int saletype;
	private List<BigDecimal> sumweight;
	private String delivertypeName;
	private String jstypeName;
	private List<SalesPrintInfo> resultList;
	private String customerName;
	private String saleTypeName;
	private SalesPrintInfo spi;
	private String date;

	public String getDelivertypeName() {
		return delivertypeName;
	}

	public void setDelivertypeName(String delivertypeName) {
		this.delivertypeName = delivertypeName;
	}

	public String getJstypeName() {
		return jstypeName;
	}

	public void setJstypeName(String jstypeName) {
		this.jstypeName = jstypeName;
	}

	public List<SalesPrintInfo> getResultList() {
		return resultList;
	}

	public void setResultList(List<SalesPrintInfo> resultList) {
		this.resultList = resultList;
	}

	private Set<XSfahuomx> xsfahuomxs = new HashSet();
	private XSfahuoxx xsfahuoxx;
	
	 public salesNotificationAction(XiaoshouService service)
	    {
		   System.out.println("Enter Constructor");
	        this.service = service;
		}
	 
	 public String execute() throws Exception
	 {    
		 this.setMynhr(service.loadmynhr().getName());
		 this.setMyshr(service.loadmyshr().getName());
		  for(int i=0;i<this.getDeli_canku().size();i++)
		  {
				Canku fahuocanku = new Canku(this.getDeli_canku().get(i),null,(byte)0);
	        	Products products = new Products(this.getProduct().get(i),null);
	        	Specifications sp = new Specifications(this.getSpecification().get(i),null,BigDecimal.valueOf(0),null);
	        	XSfahuomx tempfahuomx = new XSfahuomx(null,fahuocanku,products,sp,this.getDeli_num().get(i),
	        			BigDecimal.valueOf(Double.parseDouble(this.getPrice().get(i))),(byte)0);
	        	this.getXsfahuomxs().add(tempfahuomx);
		  }
		  
		  Custom newcustom = new Custom(this.getCustomer());
		  Users  tempshr = new Users(9);
		  Users  tempnhr = new Users(10);
		  PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
		  
		  this.xsfahuoxx = new XSfahuoxx(this.getDomains(),new Date(),this.getBno(),this.getCno(),
				  this.getOrgin(),newcustom,(byte)this.getDelivertype(),(byte)this.getJstype(),
				  this.getMemo(),tempshr,auth.getPrincipal(),tempnhr,(byte)0,(byte)this.getSaletype(),this.getXsfahuomxs());
		  service.doXsfahuo(xsfahuoxx);
		 return print();
	 }
	 
	 public String print() throws Exception{

			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日"); 
			Date d = new Date(); 
			date= bartDateFormat.format(d); 
			resultList=new ArrayList(); 
			switch(this.getSaletype()){
			case 1:saleTypeName="内销";break;
			case 2:saleTypeName="定向";break;
			case 3:saleTypeName="外销";break;
			case 4:saleTypeName="不合格";break;
			}
			
			switch(this.getDelivertype()){   
			case 0:delivertypeName="公路运输";break;
			case 1:delivertypeName="铁路运输";break;
			case 2:delivertypeName="海运";break;
			case 3:delivertypeName="自提";break;
			case 4:delivertypeName="其他";break;
			}
			switch(this.getJstype()){
			case 0:jstypeName="现金";break;
			case 1:jstypeName="电汇/转账";break;
			case 2:jstypeName="承兑汇票";break;
			}
			Custom custom = service.getCustomerById(this.getCustomer());
			customerName = custom.getCustomName();
			for(int i=0;i<this.getDeli_canku().size();i++){
				List<Products> product = service.getProductNameById(this.getProduct().get(i));
				List<Specifications> specification = service.getSpecificationNameById(this.getSpecification().get(i));
				Canku canku = service.getCangkuById(this.getDeli_canku().get(i));
				this.spi = 
		    		new SalesPrintInfo(canku.getName(),product.get(0).getName(),specification.get(0).getDisplayName(),this.getSumweight().get(i).toString(),this.getDeli_num().get(i).toString(),this.getPrice().get(i).toString());
				resultList.add(spi);
			}
			return "print";
		}
	 
	public String getDomains() {
		return domains;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public int getDelivertype() {
		return delivertype;
	}

	public void setDelivertype(int delivertype) {
		this.delivertype = delivertype;
	}

	public int getJstype() {
		return jstype;
	}

	public void setJstype(int jstype) {
		this.jstype = jstype;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	

	public String getMyshr() {
		return myshr;
	}

	public void setMyshr(String myshr) {
		this.myshr = myshr;
	}

	public String getMynhr() {
		return mynhr;
	}

	public void setMynhr(String mynhr) {
		this.mynhr = mynhr;
	}

	public List<Integer> getDeli_canku() {
		return deli_canku;
	}

	public void setDeli_canku(List<Integer> deli_canku) {
		this.deli_canku = deli_canku;
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

	public List<Integer> getDeli_num() {
		return deli_num;
	}

	public void setDeli_num(List<Integer> deli_num) {
		this.deli_num = deli_num;
	}

	public List<String> getPrice() {
		return price;
	}

	public void setPrice(List<String> price) {
		this.price = price;
	}

	public Set<XSfahuomx> getXsfahuomxs() {
		return xsfahuomxs;
	}

	public void setXsfahuomxs(Set<XSfahuomx> xsfahuomxs) {
		this.xsfahuomxs = xsfahuomxs;
	}

	public XSfahuoxx getXsfahuoxx() {
		return xsfahuoxx;
	}

	public void setXsfahuoxx(XSfahuoxx xsfahuoxx) {
		this.xsfahuoxx = xsfahuoxx;
	}

	public int getSaletype() {
		return saletype;
	}

	public void setSaletype(int saletype) {
		this.saletype = saletype;
	}

	public void prepare() throws Exception {
		

	}

	public void setSumweight(List<BigDecimal> sumweight) {
		this.sumweight = sumweight;
	}

	public List<BigDecimal> getSumweight() {
		return sumweight;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setSaleTypeName(String saleTypeName) {
		this.saleTypeName = saleTypeName;
	}

	public String getSaleTypeName() {
		return saleTypeName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

}
