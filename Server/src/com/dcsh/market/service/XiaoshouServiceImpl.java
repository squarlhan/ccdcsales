package com.dcsh.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dcsh.market.Canku;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Kcxx;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSKcxx;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSyikuxx;


import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.dcsh.market.Canku;
import com.dcsh.market.Custom;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.Yxyikusign;


public class XiaoshouServiceImpl implements XiaoshouService {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<XSKcxx> listStorageByPrd(List<Products> productList){
		List<XSKcxx> result = new ArrayList();
		for(Products product:productList){
		List<Integer> cids = (List<Integer>) hibernateTemplate
					.find("select distinct id.cid from Kcxx as kc where kc.id.pid = "
							+ String.valueOf(product.getId()));
			for (Integer cid : cids) {
				List<Specifications> spes = (List<Specifications>) hibernateTemplate
						.find("select distinct specifications from Kcxx as kc where kc.id.pid = "
								+ String.valueOf(product.getId())
								+ " and kc.id.cid=" + String.valueOf(cid));
				for (Specifications spe : spes) {
					 List<Byte> saletypes = (List<Byte>) hibernateTemplate
						.find("select distinct saleType from Kcxx as kc where kc.id.pid = "
								+ String.valueOf(product.getId())
								+ " and kc.id.cid=" + String.valueOf(cid)+" and kc.specifications.id="
									+ String.valueOf(spe.getId()));
					 for(byte saletype : saletypes)
					 {
						 List<Kcxx> kresult = (List<Kcxx>) hibernateTemplate
							.find("from Kcxx as kc where kc.id.pid="
									+ String.valueOf(product.getId())
									+ " and kc.id.cid=" + String.valueOf(cid)
									+ " and kc.specifications.id="
									+ String.valueOf(spe.getId())
									+" and kc.saleType='"
									+ String.valueOf(saletype)+"'");
					int number = 0;
				
					for (Kcxx kcxx : kresult) {
						number += kcxx.getNumber();
						System.out.println("产品：" + number);
					}
					switch(saletype)
					{
					case 1:result.add(new XSKcxx((Canku) hibernateTemplate.get(
							Canku.class, cid), spe, number, spe.getWeight()
							.multiply(new BigDecimal(number)), product,"内销"));break;
					case 2:result.add(new XSKcxx((Canku) hibernateTemplate.get(
							Canku.class, cid), spe, number, spe.getWeight()
							.multiply(new BigDecimal(number)), product,"定向"));break;
					case 3:result.add(new XSKcxx((Canku) hibernateTemplate.get(
							Canku.class, cid), spe, number, spe.getWeight()
							.multiply(new BigDecimal(number)), product,"外销"));break;
					case 4:result.add(new XSKcxx((Canku) hibernateTemplate.get(
							Canku.class, cid), spe, number, spe.getWeight()
							.multiply(new BigDecimal(number)), product,"不合格"));break;
					}
					
					 }
				}
			}
		}		
		return result;
	}

    private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;  
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Canku> getAllCankus() {
		
		List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku");	
    	for(Iterator it = cankus.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getType()==(byte)3)||(tmpck.getType()==(byte)4)||(tmpck.getType()==(byte)5)){
    			it.remove();
    		}
    	}
		return cankus;	
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Products> getAllProductList() {
		
		return hibernateTemplate.find("from Products");
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Specifications> getAllSpecificationList() {
		
		return hibernateTemplate.find("from Specifications");
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Custom> getAllCustom() {
		
		return (List<Custom>) hibernateTemplate.find("from Custom");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fahuo> getAllFahuos(){
		return (List<Fahuo>) hibernateTemplate.find("from Fahuo");
	}

	@Override
	@Transactional
	public void doYiku(XSyikuxx xsyikuxx) {
		
		xsyikuxx.setCustomer((Custom)hibernateTemplate.get(Custom.class, xsyikuxx.getCustomer().getId()));
		xsyikuxx.setAimcanku((Canku)hibernateTemplate.get(Canku.class, xsyikuxx.getAimcanku().getId()));
		
		hibernateTemplate.save(xsyikuxx);
		
		////////////////
		Set<XSyikumx> yikumxs = xsyikuxx.getXsyikumxes();
		for(XSyikumx yikumx:yikumxs)
		{
			yikumx.setXsyikuxx(xsyikuxx);
			yikumx.setCanku((Canku)hibernateTemplate.get(Canku.class, yikumx.getCanku().getId()));
			yikumx.setProduct((Products)hibernateTemplate.get(Products.class, yikumx.getProduct().getId()));
			yikumx.setSpecification((Specifications)hibernateTemplate.get(Specifications.class, yikumx.getSpecification().getId()));
			hibernateTemplate.save(yikumx);
			hibernateTemplate.flush();//????????
		}
		
		///////////////
		Set<Yxyikusign> yikusigns = xsyikuxx.getSxyikusigns();
		for(Yxyikusign yikusign:yikusigns)
		{
			yikusign.setXsyikuxx(xsyikuxx);
			hibernateTemplate.save(yikusign);
			hibernateTemplate.flush();
		}
	}

	@Override
	@Transactional
	public void doXsfahuo(XSfahuoxx xsfahuoxx) {
		
		xsfahuoxx.setCustomer((Custom)hibernateTemplate.get(Custom.class, xsfahuoxx.getCustomer().getId()));
		xsfahuoxx.setShr((Users)hibernateTemplate.get(Users.class, xsfahuoxx.getShr().getId()));
		xsfahuoxx.setNhr((Users)hibernateTemplate.get(Users.class, xsfahuoxx.getNhr().getId()));
		hibernateTemplate.save(xsfahuoxx);
		
		Set<XSfahuomx> xsfahuomxs = xsfahuoxx.getXsfahuomxes() ;
		
		for(XSfahuomx xsfahuomx:xsfahuomxs)
		{ 
			xsfahuomx.setXsfahuoxx(xsfahuoxx);
			xsfahuomx.setCanku((Canku)hibernateTemplate.get(Canku.class, xsfahuomx.getCanku().getId()));
			xsfahuomx.setProduct((Products)hibernateTemplate.get(Products.class, xsfahuomx.getProduct().getId()));
			xsfahuomx.setSpecification((Specifications)hibernateTemplate.get(Specifications.class, xsfahuomx.getSpecification().getId()));
			hibernateTemplate.save(xsfahuomx);
			hibernateTemplate.flush();
		}
		
		
	}
	
	@Override
	public Users loadmyshr(){
		return (Users) hibernateTemplate.get(Users.class, 9);
	}
	
	@Override
    public Users loadmynhr(){
		return  (Users) hibernateTemplate.get(Users.class, 10);
    }

}
