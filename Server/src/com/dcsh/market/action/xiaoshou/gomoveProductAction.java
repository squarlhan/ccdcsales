package com.dcsh.market.action.xiaoshou;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.action.chuyunchu.saveReportAction;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class gomoveProductAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(
			saveReportAction.class.getName());
	private XiaoshouService service;

	private List<Canku> cankuList;
	private List<Products> productList;
	private List<Specifications> specificationList;
	private List<Custom> customList;
	private List<Fahuo> fahuoList;
	private Date mydate;

	public gomoveProductAction(XiaoshouService service) {
		System.out.println("Enter Constructor");
		this.service = service;
	}

	public String execute() {
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil
				.getLoginAuthentication();
		List<ResourceGrantedAuthorityImpl> list = auth
				.getGrantedAuthorityResource(ResourceType.PRD);

		List<Products> plist = new ArrayList();
		for (ResourceGrantedAuthorityImpl res : list) {
			plist.add((Products) res.getResource());
		}
		this.setMydate(new Date());
		this.setFahuoList(service.getAllFahuos());
		this.setCustomList(service.getAllCustom());
		this.setCankuList(service.getAllCankus());
		this.setProductList(plist);
		this.setSpecificationList(service.getAllSpecificationList());
		return "list";
	}

	public List<Canku> getCankuList() {
		return cankuList;
	}

	public void setCankuList(List<Canku> cankuList) {
		this.cankuList = cankuList;
	}

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	public List<Specifications> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<Specifications> specificationList) {
		this.specificationList = specificationList;
	}

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public List<Custom> getCustomList() {
		return customList;
	}

	public void setCustomList(List<Custom> customList) {
		this.customList = customList;
	}

	public List<Fahuo> getFahuoList() {
		return fahuoList;
	}

	public void setFahuoList(List<Fahuo> fahuoList) {
		this.fahuoList = fahuoList;
	}

	public void prepare() throws Exception {

	}

}
