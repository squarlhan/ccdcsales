package com.dcsh.market.action.zhongzhuanku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goLostAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(goDeliveryAction.class.getName());
    private ZhongZhuanKuService service;
    private List<Products> productsList;
    private List<Specifications> specificationsList;
    private List<Kcxx> resultList;
    private List<Canku> cankusList;
    public goLostAction(ZhongZhuanKuService service) {

        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	if(user.size()==0) return "index";
    	else{
            this.resultList = service.getAllProducts(user.get(0).getCanku());
            return "show";
            }
    }

    public ZhongZhuanKuService getService() {
		return service;
	}

	public void setService(ZhongZhuanKuService service) {
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

	public List<Kcxx> getResultList() {
		return resultList;
	}

	public void setResultList(List<Kcxx> resultList) {
		this.resultList = resultList;
	}

	public List<Canku> getCankusList() {
		return cankusList;
	}

	public void setCankusList(List<Canku> cankusList) {
		this.cankusList = cankusList;
	}

	public void prepare() throws Exception {

    }
}
