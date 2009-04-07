package com.dcsh.market.action.xiaoshou;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.XSyikuxx;
import com.dcsh.market.Yxyikusign;
import com.dcsh.market.action.chuyunchu.saveReportAction;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class moveProductAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(saveReportAction.class.getName());
	private XiaoshouService service;
	
	private Date mydate;
	private String bno;
	private int custom;
	private String orgin;
	private String aim;
	private Integer aimcanku;
	private Date sendtime;
	private int delivertype;
	private String memo;
	private List<Integer> deli_canku;
	private List<Integer> product;
	private List<Integer> specification;
	private List<Integer> deli_num;
	private String price;
	private int saletype;
	
	private Set<XSyikumx> xsyikumxs = new HashSet();;
	private XSyikuxx xsyikuxx;
	private Set<Yxyikusign> yikusigns = new HashSet();
	
	
	 public moveProductAction(XiaoshouService service)
	    {
		   System.out.println("Enter Constructor");
	        this.service = service;
		}
	 
 public String execute() 
 
        {      
	        
	        for(int i=0;i<this.getDeli_canku().size();i++)
	        {
	        	Canku fahuocanku = new Canku(this.getDeli_canku().get(i),null,(byte)0);
	        	Products products = new Products(this.getProduct().get(i),null);
	        	Specifications sp = new Specifications(this.getSpecification().get(i),null,BigDecimal.valueOf(0),null);
	        	XSyikumx tempykmx = new XSyikumx(fahuocanku,products,sp,null,
	        			this.getDeli_num().get(i),(byte)0);
	        	this.getXsyikumxs().add(tempykmx);
	        }
	        
	        this.getYikusigns().add(new Yxyikusign(null,(byte)0,null,new Date()));//状态，负责人，签字时间待定;多个签名待定
	        
	        Custom newcustom = new Custom(this.getCustom());
	        Canku newcanku = new Canku(this.getAimcanku(),null,(byte)0); 
	        
	        PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	  
	        this.xsyikuxx = new XSyikuxx(new Date(),this.getBno(),newcustom,this.getOrgin(),(byte)0,auth.getPrincipal(),
	        		this.getAim(),this.getSendtime(),(byte)this.getDelivertype(),
	        		this.getMemo(),newcanku,(byte)this.getSaletype(),BigDecimal.valueOf(Double.parseDouble(this.getPrice())),this.getXsyikumxs(),this.getYikusigns());
	        service.doYiku(xsyikuxx);
	        return "save_yiku";
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

public int getCustom() {
	return custom;
}

public void setCustom(int custom) {
	this.custom = custom;
}

public String getOrgin() {
	return orgin;
}

public void setOrgin(String orgin) {
	this.orgin = orgin;
}

public String getAim() {
	return aim;
}

public void setAim(String aim) {
	this.aim = aim;
}

public Integer getAimcanku() {
	return aimcanku;
}

public void setAimcanku(Integer aimcanku) {
	this.aimcanku = aimcanku;
}

public Date getSendtime() {
	return sendtime;
}

public void setSendtime(Date sendtime) {
	this.sendtime = sendtime;
}

public int getDelivertype() {
	return delivertype;
}

public void setDelivertype(int delivertype) {
	this.delivertype = delivertype;
}

public String getMemo() {
	return memo;
}

public void setMemo(String memo) {
	this.memo = memo;
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


public Set<XSyikumx> getXsyikumxs() {
	return xsyikumxs;
}

public void setXsyikumxs(Set<XSyikumx> xsyikumxs) {
	this.xsyikumxs = xsyikumxs;
}

public XSyikuxx getXsyikuxx() {
	return xsyikuxx;
}

public void setXsyikuxx(XSyikuxx xsyikuxx) {
	this.xsyikuxx = xsyikuxx;
}

	public Set<Yxyikusign> getYikusigns() {
	return yikusigns;
}

public void setYikusigns(Set<Yxyikusign> yikusigns) {
	this.yikusigns = yikusigns;
}

	public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

	public int getSaletype() {
	return saletype;
}

public void setSaletype(int saletype) {
	this.saletype = saletype;
}

	public void prepare() throws Exception {

	}

}
