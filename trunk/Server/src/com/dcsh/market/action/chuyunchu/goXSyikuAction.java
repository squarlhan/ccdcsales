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
import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goXSyikuAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goXSyikuAction.class.getName());
    private WareHouseService service;
    private List<XSyikumx> xsyklist;
    private int index;
    private XSyikumx xsykmx;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<KcxxCheck> resultList;
    private List<Canku> cankusList;
    private List<Custom> customList;
    private List<String> pchList;
    
    public goXSyikuAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	Set<String> pchset = new HashSet();
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> list = (List<CankuPriv>)session.get("tempuser");
  
	    this.xsyklist = service.getXSyikumx(((List<CankuPriv>)session.get("tempuser")).get(0).getCanku());
		
		for(int i=0;i<xsyklist.size();i++){
			if(xsyklist.get(i).getId()==index)
				xsykmx = xsyklist.get(i);
		}
		if(list.size()!=0)

	    	{
				this.pchList = new ArrayList();
	            this.productsList = service.getAllProducts();
	            this.specificationsList = service.getAllSpecifications();
	            this.customList = service.getAllCustom();
	            this.resultList = service.getCheckedProducts(((Canku)list.get(0).getCanku()),xsykmx);
	            for(int i=0; i<resultList.size();i++){
	            	pchset.add(this.resultList.get(i).getId().getPch());
	            }
	            this.pchList.addAll(pchset);
	  
	            cankusList = service.getAllCankus();
	        }
		cankusList.clear();
		cankusList.add(xsykmx.getXsyikuxx().getAimcanku());
		for(int i=0;i<specificationsList.size();i++){
			if(specificationsList.get(i).getId()==xsykmx.getSpecification().getId()){
				Specifications tp = new Specifications();
				tp = specificationsList.get(i);
				specificationsList.clear();
				specificationsList.add(tp);
				break;
			}
		}
		for(int i=0;i<customList.size();i++){
			if(customList.get(i).getId()==xsykmx.getXsyikuxx().getCustomer().getId()){
				Custom tp = new Custom();
				tp = customList.get(i);
				customList.clear();
				customList.add(tp);
				break;
			}
		}
		for(int i=0;i<productsList.size();i++){
			if(productsList.get(i).getId()==xsykmx.getProduct().getId()){
				Products tp = new Products();
				tp = productsList.get(i);
				productsList.clear();
				productsList.add(tp);
				break;
			}
		}
		return "show_report";
      
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

	public XSyikumx getXsykmx() {
		return xsykmx;
	}

	public void setXsykmx(XSyikumx xsykmx) {
		this.xsykmx = xsykmx;
	}

	public List<XSyikumx> getXsyklist() {
		return xsyklist;
	}

	public void setXsyklist(List<XSyikumx> xsyklist) {
		this.xsyklist = xsyklist;
	}

	public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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