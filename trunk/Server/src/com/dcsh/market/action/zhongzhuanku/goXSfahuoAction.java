package com.dcsh.market.action.zhongzhuanku;

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
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goXSfahuoAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goXSfahuoAction.class.getName());
    private ZhongZhuanKuService service;
    private List<XSfahuomx> xsfhlist;
    private int index;
    private XSfahuomx xsfhmx;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<KcxxCheck> resultList;
    private List<Canku> cankusList;
    private List<Custom> customList;
    private List<String> pchList;
    public goXSfahuoAction(ZhongZhuanKuService service) {

        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	Set<String> pchset = new HashSet();
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> list = (List<CankuPriv>)session.get("zhongzhuanuser");

		this.xsfhlist = service.getXSfahuomx(((List<CankuPriv>)session.get("zhongzhuanuser")).get(0).getCanku());
		
		for(int i=0;i<xsfhlist.size();i++){
			if(xsfhlist.get(i).getId()==index)
				xsfhmx = xsfhlist.get(i);
		}
		if(list.size()!=0)
	    	{
				this.pchList = new ArrayList();
	            this.productsList = service.getAllProducts();
	            this.specificationsList = service.getAllSpecifications();
	            this.customList = service.getAllCustom();
	            this.resultList = service.getCheckedProducts(((Canku)list.get(0).getCanku()),xsfhmx);
	            for(int i=0; i<resultList.size();i++){
	            	pchset.add(this.resultList.get(i).getId().getPch());
	            }
	            this.pchList.addAll(pchset);
	            cankusList = service.getAllCankus();
	        }
		cankusList.clear();
		for(int i=0;i<specificationsList.size();i++){
			if(specificationsList.get(i).getId()==xsfhmx.getSpecification().getId()){
				Specifications tp = new Specifications();
				tp = specificationsList.get(i);
				specificationsList.clear();
				specificationsList.add(tp);
				break;
			}
		}
		for(int i=0;i<customList.size();i++){
			if(customList.get(i).getId()==xsfhmx.getXsfahuoxx().getCustomer().getId()){
				Custom tp = new Custom();
				tp = customList.get(i);
				customList.clear();
				customList.add(tp);
				break;
			}
		}
		for(int i=0;i<productsList.size();i++){
			if(productsList.get(i).getId()==xsfhmx.getProduct().getId()){
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


	public List<XSfahuomx> getXsfhlist() {
		return xsfhlist;
	}

	public void setXsfhlist(List<XSfahuomx> xsfhlist) {
		this.xsfhlist = xsfhlist;
	}

	public XSfahuomx getXsfhmx() {
		return xsfhmx;
	}

	public void setXsfhmx(XSfahuomx xsfhmx) {
		this.xsfhmx = xsfhmx;
	}

	public ZhongZhuanKuService getService() {
		return service;
	}

	public void setService(ZhongZhuanKuService service) {
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