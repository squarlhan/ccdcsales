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

public class goDeliveryAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goDeliveryAction.class.getName());
    private WareHouseService service;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<KcxxCheck> resultList;
    private List<Canku> cankusList;
    private List<Custom> customList;
    private List<String> pchList;
    public goDeliveryAction(WareHouseService service) {
    
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	
    	Set<Products> proset = new HashSet();
    	Set<Specifications> speset = new HashSet();
    	Set<String> pchset = new HashSet();
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0) return "index";
    	else{
    		this.productsList = new ArrayList();
            this.specificationsList = new ArrayList();
            this.pchList = new ArrayList();
            this.customList = service.getAllCustom();
            this.resultList = service.getValiProducts(user.get(0).getCanku());
            for(int i=0; i<resultList.size();i++){
            	proset.add(this.resultList.get(i).getProducts());
            	speset.add(this.resultList.get(i).getSpecifications());
            	pchset.add(this.resultList.get(i).getId().getPch());
            }
            this.specificationsList.addAll(speset);
            this.productsList.addAll(proset);
            this.pchList.addAll(pchset);
        
            cankusList = service.getAllCankus();
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
	public void prepare() throws Exception {

    }
}