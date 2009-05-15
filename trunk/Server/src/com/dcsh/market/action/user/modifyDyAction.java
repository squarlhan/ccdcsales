package com.dcsh.market.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Products;
import com.dcsh.market.Smmdingyue;
import com.dcsh.market.Smmdy;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class modifyDyAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(
			modifyDyAction.class.getName());
	
	private AdminService service;
	private List<Boolean> xsyk1;
	private List<Boolean> xsyk2;
	private List<Boolean> xsyk3;
	private List<Boolean> xsfh1;
	private List<Boolean> xsfh2;
	private List<Boolean> cycyk1;
	private List<Boolean> cycyk2;
	private List<Smmdingyue> smmdy;
	
	public modifyDyAction(AdminService service) {

		this.service = service;
	}
	
	public String execute()  throws Exception
	{
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl) PrivUtil
		.getLoginAuthentication();
		
		List<ResourceGrantedAuthorityImpl> plist = auth
		.getGrantedAuthorityResource(ResourceType.PRD);
		
	    this.smmdy = new ArrayList<Smmdingyue>();
		for(int i=0;i<this.getXsyk1().size();i++){
			
			System.out.println(this.getXsyk1().get(i));
			
			smmdy.add(new Smmdingyue(auth.getPrincipal(),
					(Products)plist.get(i).getResource(),
					this.getXsyk1().get(i)==true?(byte)1:(byte)0,
					this.getXsyk2().get(i)==true?(byte)1:(byte)0,
					this.getXsyk3().get(i)==true?(byte)1:(byte)0,
					this.getXsfh1().get(i)==true?(byte)1:(byte)0,
					this.getXsfh2().get(i)==true?(byte)1:(byte)0,
					this.getCycyk1().get(i)==true?(byte)1:(byte)0,
					this.getCycyk2().get(i)==true?(byte)1:(byte)0));
		}
		this.service.updateSmmdy(smmdy);
		return "save_dy";
	}
	
	public List<Boolean> getXsyk1() {
		return xsyk1;
	}

	public void setXsyk1(List<Boolean> xsyk1) {
		this.xsyk1 = xsyk1;
	}

	public List<Boolean> getXsyk2() {
		return xsyk2;
	}

	public void setXsyk2(List<Boolean> xsyk2) {
		this.xsyk2 = xsyk2;
	}

	public List<Boolean> getXsyk3() {
		return xsyk3;
	}

	public void setXsyk3(List<Boolean> xsyk3) {
		this.xsyk3 = xsyk3;
	}

	public List<Boolean> getXsfh1() {
		return xsfh1;
	}

	public void setXsfh1(List<Boolean> xsfh1) {
		this.xsfh1 = xsfh1;
	}

	public List<Boolean> getXsfh2() {
		return xsfh2;
	}

	public void setXsfh2(List<Boolean> xsfh2) {
		this.xsfh2 = xsfh2;
	}

	public List<Boolean> getCycyk1() {
		return cycyk1;
	}

	public void setCycyk1(List<Boolean> cycyk1) {
		this.cycyk1 = cycyk1;
	}

	public List<Boolean> getCycyk2() {
		return cycyk2;
	}

	public void setCycyk2(List<Boolean> cycyk2) {
		this.cycyk2 = cycyk2;
	}

	public List<Smmdingyue> getSmmdy() {
		return smmdy;
	}

	public void setSmmdy(List<Smmdingyue> smmdy) {
		this.smmdy = smmdy;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
