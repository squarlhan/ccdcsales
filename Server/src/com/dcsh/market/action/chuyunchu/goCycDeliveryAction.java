package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goCycDeliveryAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goZzkDeliveryAction.class.getName());
    private WareHouseService service;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<KcxxCheck> resultList;
    private List<Canku> cankusList;
    private List<Custom> customList;
    private List<String> pchList;
    private int pageNo = 0;
    private int pageCount;
    public final Integer ItemCount = 10;
    public goCycDeliveryAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() {
    
    	Set<Products> proset = new HashSet();
    	Set<Specifications> speset = new HashSet();
    	Set<String> pchset = new HashSet();
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0) return "index";
    	else{
    		this.productsList = new ArrayList();
            this.specificationsList = new ArrayList();
            this.customList = service.getAllCustom();
            this.pchList = new ArrayList();
            List<KcxxCheck> tempResultlist = new ArrayList();
            tempResultlist = service.getallCheckedProducts(user.get(0).getCanku());
            
            if((this.pageNo+1)*this.ItemCount<=tempResultlist.size())
                this.resultList = tempResultlist.subList(this.pageNo*this.ItemCount,(this.pageNo+1)*this.ItemCount);
            else
            	this.resultList = tempResultlist.subList(this.pageNo*this.ItemCount, tempResultlist.size());
            
            this.pageCount = (int) Math.ceil(tempResultlist.size()/Double.parseDouble(this.ItemCount.toString()));
            System.out.println(this.pageCount+"pagecount"+tempResultlist.size()+"result_size");
            
            for(int i=0; i<resultList.size();i++){
            	proset.add(this.resultList.get(i).getProducts());
            	speset.add(this.resultList.get(i).getSpecifications());
            	pchset.add(this.resultList.get(i).getId().getPch());
            }
            this.specificationsList.addAll(speset);
            this.productsList.addAll(proset);
            this.pchList.addAll(pchset);        
            cankusList = service.getCangkuByType(0);//储运处类型0
            return "show";
            }
    
    }

    public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
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

	public List<KcxxCheck> getResultList() {
		return resultList;
	}

	public void setResultList(List<KcxxCheck> resultList) {
		this.resultList = resultList;
	}

	public List<Canku> getCankusList() {
		return cankusList;
	}

	public void setCankusList(List<Canku> cankusList) {
		this.cankusList = cankusList;
	}
    
	public List<Custom> getCustomList() {
		return customList;
	}

	public void setCustomList(List<Custom> customList) {
		this.customList = customList;
	}

	public List<String> getPchList() {
		return pchList;
	}

	public void setPchList(List<String> pchList) {
		this.pchList = pchList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void prepare() throws Exception {

    }
}