package com.dcsh.market.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;
import com.dcsh.market.Smmdingyue;
import com.dcsh.market.Smmdy;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class goSmmdyAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(
			goSmmdyAction.class.getName());
	private List<Smmdy> resultList;
	private String flag;
	private AdminService service;

	public goSmmdyAction(AdminService service) {

		this.service = service;
	}

	public List<Smmdy> getResultList() {
		return resultList;
	}

	public void setResultList(List<Smmdy> resultList) {
		this.resultList = resultList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String execute() throws Exception {

		PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil
				.getLoginAuthentication();
		List<Smmdy> tempList = service.loadSmmdybyuser(auth.getPrincipal());
		resultList = new ArrayList();
		List<ResourceGrantedAuthorityImpl> clist = auth
				.getGrantedAuthorityResource(ResourceType.CANKU);
		List<ResourceGrantedAuthorityImpl> plist = auth
				.getGrantedAuthorityResource(ResourceType.PRD);
		flag = "display:none";
		for (ResourceGrantedAuthorityImpl canku : clist) {
			if (((Canku) canku.getResource()).getType() == (byte) 0)
				flag = "";
		}
		if (plist.size() > 0) {
			for (ResourceGrantedAuthorityImpl prd : plist) {
				Smmdingyue smmdy = new Smmdingyue(0, auth.getPrincipal(),
						(Products) prd.getResource(), (byte) 0, (byte) 0,
						(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
				if (tempList.size() > 0) {
					for (Smmdy sdy : tempList) {
						if (sdy.getSmm().getProduct().getId() == ((Products) prd
								.getResource()).getId()) {
							smmdy.setXsyk1(sdy.getSmm().getXsyk1());
							smmdy.setXsyk2(sdy.getSmm().getXsyk2());
							smmdy.setXsyk3(sdy.getSmm().getXsyk3());
							smmdy.setXsfh1(sdy.getSmm().getXsfh1());
							smmdy.setXsfh2(sdy.getSmm().getXsfh2());
							smmdy.setCycyk1(sdy.getSmm().getCycyk1());
							smmdy.setCycyk2(sdy.getSmm().getCycyk2());
							System.out.println(smmdy.getProduct().getName()
									+ ":" + smmdy.getXsyk1() + ","
									+ smmdy.getXsyk2() + "," + smmdy.getXsyk3()
									+ "," + smmdy.getXsfh1() + ","
									+ smmdy.getXsfh2() + ","
									+ smmdy.getCycyk1() + ","
									+ smmdy.getCycyk2() + ",");
						}
					}
				}
				resultList.add(new Smmdy(smmdy));
			}
			return "go";
		} else
			return "error";
	}

	public void prepare() throws Exception {

	}

}
