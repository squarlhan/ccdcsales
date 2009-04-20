package com.dcsh.market.action.xiaoshou;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Products;
import com.dcsh.market.XSKcxx;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class listStorageAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(listStorageAction.class.getName());
	private XiaoshouService service;
	private List<XSKcxx> resultList;

	 public List<XSKcxx> getResultList() {
		return resultList;
	}

	public void setResultList(List<XSKcxx> resultList) {
		this.resultList = resultList;
	}

	public listStorageAction(XiaoshouService service){

	     this.service = service;
     }
	 
	 public String execute() {

		 PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	     List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.PRD);
		 if(list.size() > 0){
	     List<Products> plist = new ArrayList();
			for (ResourceGrantedAuthorityImpl res : list) {
				plist.add((Products) res.getResource());
			}
			resultList = service.listStorageByPrd(plist);

			return "list";
	     }else return "index";
		 
	 }
	
	
    public void prepare() throws Exception {
	}

}
