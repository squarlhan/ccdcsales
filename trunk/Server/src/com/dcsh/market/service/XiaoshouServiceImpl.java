package com.dcsh.market.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.dcsh.market.Canku;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxId;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSKcxx;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.XSyikuxx;
import com.dcsh.market.Yxyikusign;


public class XiaoshouServiceImpl implements XiaoshouService {
	
	
	@SuppressWarnings("unchecked")
	 
	public List<XSKcxx> listStorageByPrd(List<Products> productList, List<Canku> cankuList){
		List<XSKcxx> result = new ArrayList();
		List<Integer> cids = new ArrayList();
		for(Canku canku:cankuList){
			cids.add(canku.getId());
		}
		for(Products product:productList){
//		List<Integer> cids = (List<Integer>) hibernateTemplate
//					.find("select distinct id.cid from Kcxx as kc where kc.id.pid = "
//							+ String.valueOf(product.getId()));
		
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
    		if((tmpck.getType()==(byte)7)||(tmpck.getType()==(byte)8)||(tmpck.getType()==(byte)3)||(tmpck.getType()==(byte)4)||(tmpck.getType()==(byte)5)||(tmpck.getType()==(byte)6)){
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
	 
	public List<Fahuo> getAllFahuos(){
		return (List<Fahuo>) hibernateTemplate.find("from Fahuo");
	}

	 
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
		
	@SuppressWarnings("unchecked")
	public List<Products> getProductNameById(int id){
		List<Products> productName;
		productName = hibernateTemplate.find("from Products where id='"+id+"'");
		return productName;
	}
	
	@SuppressWarnings("unchecked")
	public List<Specifications> getSpecificationNameById(int id){
		List<Specifications> specificationName;
		specificationName = hibernateTemplate.find("from Specifications where id='"+id+"'");
		return specificationName;
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public Canku getCangkuById(Integer cangkuId) {
		
		List<Canku> cankulist = hibernateTemplate.find("from Canku where id='"+cangkuId+"'");
		Canku canku = cankulist.get(0);
		return canku;
	}
	@SuppressWarnings("unchecked")
	public Custom getCustomerById(Integer customerId){
		List<Custom> customerList = hibernateTemplate.find("from Custom where id='"+customerId+"'");
		return customerList.get(0);
	}
	 
	public Users loadmyshr(){
		return (Users) hibernateTemplate.get(Users.class, 9);
	}
	
	 
    public Users loadmynhr(){
		return  (Users) hibernateTemplate.get(Users.class, 10);
    }
	
	 
	@Transactional
	public List<ReportCmx> listSales(List<Products> products,Date begindate,Date enddate){
		List<ReportCmx> list = new ArrayList<ReportCmx>();
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		List<Products> pchuku = products;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal total_ckwt = new BigDecimal(0);
		
		if(begindate.compareTo(enddate)<=0){
			
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type = 3");
		    if(cankus.size()!=0){
		    	cid = String.valueOf(cankus.get(0).getId());
		        for(Products product:products){
			      listchuku.addAll(hibernateTemplate.find("from Chukumx as ck where ck.products = "+String.valueOf(product.getId())+" and ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					" and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' "));
			    }
		    }
			
			for(int i=0;i<pchuku.size();i++){
				total_ckwt = new BigDecimal(0);
				//出库明细总重
				for(int i1=0;i1<listchuku.size();i1++){
					if(listchuku.get(i1).getProducts().getId()==(pchuku.get(i).getId()))
					{
						total_ckwt = total_ckwt.add((listchuku.get(i1).getSpecifications().getWeight()).multiply(new BigDecimal(listchuku.get(i1).getNumber())));
					}
				}
				list.add(new ReportCmx(pchuku.get(i),total_ckwt));
			}
		}
		return list;
	}
	 
	@Transactional
	public List<Chukumx> listSalesmx(List<Products> products,Date begindate,Date enddate){
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		
		if(begindate.compareTo(enddate)<=0){
				
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type = 3");
		    if(cankus.size()!=0){
		    	cid = String.valueOf(cankus.get(0).getId());
		        for(Products product:products){
			      listchuku.addAll(hibernateTemplate.find("from Chukumx as ck where ck.products = "+String.valueOf(product.getId())+" and ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					" and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' "));
			    }
		    }
		}
		return listchuku;
	}
	@SuppressWarnings("unchecked")
	 
	public List<XSyikumx> getXSyikumx(Users zbr){
		List<XSyikumx> result = hibernateTemplate.find("from XSyikumx where status = 0 and xsyikuxx.zbr.id ="+String.valueOf(zbr.getId()));
		return result;
	}
	@SuppressWarnings("unchecked")
	 
	public List<XSfahuomx> getXSfahuomx(Users zbr){
		List<XSfahuomx> result = hibernateTemplate.find("from XSfahuomx where status = 0 and xsfahuoxx.zdr.id ="+String.valueOf(zbr.getId()));
		return result;
	}
	 
	@Transactional
	public void delXSyikumx(int id){
		hibernateTemplate.delete(hibernateTemplate.get(XSyikumx.class, id));
	}
	 
	@Transactional
    public void delXSfahuomx(int id){
		hibernateTemplate.delete(hibernateTemplate.get(XSfahuomx.class, id));
	}
	
	//自动生成出库明细单
	@SuppressWarnings("unchecked")
	public Set<Chukumx> autochukumxs(Canku canku, Products products, 
			Specifications specifications, byte saletype, int sumnum, Chuku chuku){
		Set<Chukumx> result = new HashSet();
		List<Kcxx> kcxxs = (List<Kcxx>)hibernateTemplate.find("from Kcxx where id.cid =" + canku.getId()
				+ " and id.pid =" + products.getId() + " and specifications.id =" + specifications.getId()
				+ " and saleType =" + saletype);
		int avi = 0;//记录一共有多少货
		for(Kcxx kcxx : kcxxs){
			avi+=kcxx.getNumber();
		}
		if (avi >= sumnum) {
			int i = 0;// 记录一共有几个明细
			int sum = 0;
			int aa = sumnum;
			while (sum < sumnum) {
				int t = kcxxs.get(i).getNumber();
				sum += t;
				i++;
			}
			for (int j = 0; j <= i - 1; j++) {
				if (j != i - 1) {
					aa -= kcxxs.get(j).getNumber();
					result.add(new Chukumx(products, chuku, specifications, kcxxs.get(j).getId().getPch(), 
							kcxxs.get(j).getNumber(), (byte)0));
					//System.out.println(j + ":" + kcxxs.get(j).getId().getPch().trim()+" : " + kcxxs.get(j).getNumber());
				} else{
					result.add(new Chukumx(products, chuku, specifications, kcxxs.get(j).getId().getPch(), 
							aa, (byte)0));
					//System.out.println(j + ":" + kcxxs.get(j).getId().getPch().trim()+" : " + aa);
				}
			}
			return result;
		}else throw new IllegalArgumentException("库存不足！");
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public void doDeliveryWareHouse(Chuku ck) {
		
		    if(ck.getCankuByRkId().getType()==(byte)4)//损耗
				
				ck.setCankuByRkId(((List<Canku>)hibernateTemplate.find("from Canku where type=4")).get(0)); 
			else if(ck.getCankuByRkId().getType()==(byte)3)//销售
				ck.setCankuByRkId(((List<Canku>)hibernateTemplate.find("from Canku where type=3")).get(0)); 
			else
			ck.setCankuByRkId((Canku)hibernateTemplate.load(Canku.class, ck.getCankuByRkId().getId()));//移库
		
		
		ck.setCankuByCankuId((Canku)hibernateTemplate.load(Canku.class, ck.getCankuByCankuId().getId()));
		ck.setUsers((Users)hibernateTemplate.load(Users.class, ck.getUsers().getId()));
		ck.setCustom((Custom)hibernateTemplate.get(Custom.class, ck.getCustom().getId()));
		hibernateTemplate.save(ck);
		Set<Chukumx> ckmxs = ck.getChukumxes();

		for (Chukumx ckmx : ckmxs) {
			ckmx.setChuku(ck);
			ckmx.setProducts((Products)hibernateTemplate.load(Products.class, ckmx.getProducts().getId()));
			ckmx.setSpecifications((Specifications)hibernateTemplate.load(Specifications.class, ckmx.getSpecifications().getId()));
			ckmx.setStatus((byte)0);
			hibernateTemplate.save(ckmx);
			hibernateTemplate.flush();
			
			KcxxId cid = new KcxxId(ckmx.getProducts().getId(), ckmx.getPch(),
					ck.getCankuByCankuId().getId());
			Kcxx ckcxx = (Kcxx) hibernateTemplate.get(Kcxx.class, cid);
			if(ckcxx.getNumber()>ckmx.getNumber()){
				ckcxx.entryNumber(-ckmx.getNumber());
				hibernateTemplate.update(ckcxx);
			}else if(ckcxx.getNumber()==ckmx.getNumber()){
				hibernateTemplate.delete(ckcxx);
			}else {
				System.out.println("库存不足！");
				throw new IllegalArgumentException("库存不足！");
			}

			hibernateTemplate.flush();
		}
		
	}

}
