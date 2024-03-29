package com.dcsh.market.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.dcsh.market.Canku;
import com.dcsh.market.CheckInTable;
import com.dcsh.market.CheckOutTable;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.KcxxId;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.Reportxx;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Specifications;
import com.dcsh.market.UserPriv;
import com.dcsh.market.Users;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.CankuPriv;

public class ZhuChangKuServiceImpl implements ZhuChangKuService {
private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;  
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getAllProducts() {
		return hibernateTemplate.find("from Products");
	}

	@SuppressWarnings("unchecked")
	public List<Specifications> getAllSpecifications() {
		return hibernateTemplate.find("from Specifications");
	}

	@SuppressWarnings("unchecked")
	public List<Canku> getAllCankus() {
		List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku");	
    	for(Iterator it = cankus.iterator();it.hasNext();){
    		Canku tmpck = (Canku)it.next();
    		if((tmpck.getType()==(byte)7)||(tmpck.getType()==(byte)8)||(tmpck.getType()==(byte)3)||(tmpck.getType()==(byte)4)||(tmpck.getType()==(byte)5)){
    			it.remove();
    		}
    	}
		return cankus;	
	}
	 
	public List<KcxxCheck> getValiProducts(Canku canku) {
		// TODO Auto-generated method stub
		List<KcxxCheck> result = new ArrayList();
		List<Kcxx> tempresult = new ArrayList();
		tempresult = (List<Kcxx>)hibernateTemplate.find("from Kcxx where cid ="+ canku.getId()+"and status = 1");
		for(Kcxx kcxx:tempresult){
			if(kcxx.getSaleType()==1)result.add(new KcxxCheck("内销",kcxx));
			else if(kcxx.getSaleType()==2)result.add(new KcxxCheck("定向",kcxx));
			else if(kcxx.getSaleType()==3)result.add(new KcxxCheck("外销",kcxx));
			else result.add(new KcxxCheck("未定",kcxx));
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	 
	public List<Chukumx> listUnCheckProducts(int ckid) {
		// TODO Auto-generated method stub
		List<Chuku> chukus = new ArrayList();
		List<Chukumx> result = new ArrayList();
		chukus = (List<Chuku>)hibernateTemplate.find("from Chuku where RkID="+String.valueOf(ckid));
		for(Chuku chuku:chukus){
			result.addAll((List<Chukumx>)hibernateTemplate.find("from Chukumx where Ckid="+String.valueOf(chuku.getId())+"and Status=0"));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<CankuPriv> loginZhuChangKu(String username,byte[] Password){
		List<CankuPriv> ckus = new ArrayList();
	    List<UserPriv> uprivs;
		List<Users> users = 
			hibernateTemplate.find("from Users where name = '" +  username.trim() + "'");
		if (users.size() == 0) throw new IllegalArgumentException("错误的用户名！");
		Users user = users.iterator().next();
		//验证用户名,以后可能需要考虑加密版本
		
		if (Arrays.equals(Password, user.getPassword())) {
			uprivs = hibernateTemplate.find("from UserPriv where UID = " +  user.getId());
			for(UserPriv upriv:uprivs){
				String res = upriv.getResource();
			    String[] newres = res.split(":");
			    if(newres[0].equals("canku")){
			    	Canku tmpck = (Canku)hibernateTemplate.get(Canku.class, Integer.parseInt(newres[1]));
			    	if(tmpck.getType()==(byte)1){
			    	ckus.add(new CankuPriv(user,tmpck));
			        }
			    }
			}
			
			return ckus;
		}
		else return null;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ReportPmx> getDayReportPmx(Canku canku,Date date){

		List<ReportPmx> list = new ArrayList<ReportPmx>();
		/*
		 * 当日入库产品类别psort
		 * 得到入库明细中的	当日该仓库	的入库明细listrkmx
		 */
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
		
	    List<Products> pkucun = hibernateTemplate.find("select distinct products from Kcxx as kc where kc.id.cid = "+canku.getId());

		
		List<Rkmx> listrkmx = hibernateTemplate.find("from Rkmx as rk where rk.rkxx.canku = "+canku.getId()
				+" and convert(varchar(10),rk.rkxx.rksj,120) = '"+datestr+"'");
		
		List<Chukumx> listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId()
				+" and convert(varchar(10),ck.chuku.cksj,120) = '"+datestr+"'");
		
		List<Products> pchuku = hibernateTemplate.find("select distinct products from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId()
				+" and convert(varchar(10),ck.chuku.cksj,120) = '"+datestr+"'");
		
		List<Kcxx> listkcxx =new ArrayList();
		BigDecimal total_kcwt = new BigDecimal(0);
		BigDecimal total_rkwt = new BigDecimal(0);
		BigDecimal total_ckwt = new BigDecimal(0);
		BigDecimal nxt = new BigDecimal(0);
		BigDecimal wxt = new BigDecimal(0);
		BigDecimal djt = new BigDecimal(0);
		BigDecimal dxt = new BigDecimal(0);
		BigDecimal bhgt = new BigDecimal(0);
		BigDecimal tmp = new BigDecimal(0);
		
//		int sruku = pruku.size();
		int skucun = pkucun.size();
		
		
		for(int i=0;i<skucun;i++){
			
	
			total_kcwt = new BigDecimal(0);
			total_rkwt = new BigDecimal(0);
			total_ckwt = new BigDecimal(0);
			nxt = new BigDecimal(0);
			wxt = new BigDecimal(0);
			djt = new BigDecimal(0);
			dxt = new BigDecimal(0);
			bhgt = new BigDecimal(0);
			//入库明细总重
			for(int i1=0;i1<listrkmx.size();i1++){
				
				if(listrkmx.get(i1).getProducts().getId()==(pkucun.get(i).getId()))
				{
					total_rkwt = total_rkwt.add((listrkmx.get(i1).getSpecifications().getWeight()).multiply(new BigDecimal(listrkmx.get(i1).getNumber())));
				}
			}
			//日销量
			for(int i2=0;i2<listchuku.size();i2++){
				if(listchuku.get(i2).getProducts().getId()==(pkucun.get(i).getId()))
				{
				
					total_ckwt = total_ckwt.add((listchuku.get(i2).getSpecifications().getWeight()).multiply(new BigDecimal(listchuku.get(i2).getNumber())));
				}
				else
				System.out.println();
			}
		
			//内销.外销.待检.定向--库存
			listkcxx.clear();
			listkcxx = hibernateTemplate.find("from Kcxx as kc where kc.products.id="+pkucun.get(i).getId()+" and kc.id.cid="+canku.getId());
			for(int i3=0;i3<listkcxx.size();i3++){
				tmp=new BigDecimal(0);
				tmp=(listkcxx.get(i3).getSpecifications().getWeight()).multiply
					(new BigDecimal(listkcxx.get(i3).getNumber()));
		
				total_kcwt = total_kcwt.add(tmp);
				switch(listkcxx.get(i3).getSaleType())
				{
					case 1:nxt=nxt.add(tmp);break;//内销
					case 3:wxt=wxt.add(tmp);break;//外销
					case 0:djt=djt.add(tmp);break;//待检
					case 2:dxt=dxt.add(tmp);break;//定向
					case 4:bhgt=bhgt.add(tmp);break;//不合格
				}
			}
	
			/*
			 * 查询当日只有出库且无库存的产品种类
			 */
			for(int i4=0;i4<pchuku.size();i4++){
				if(pchuku.get(i4).getId()==(pkucun.get(i).getId()))
					pchuku.remove(i4);
			}
			
			
			list.add(new ReportPmx(new Reportxx(), canku, pkucun.get(i), total_rkwt, total_ckwt, total_kcwt, nxt, wxt, djt, dxt,bhgt,null,null,null));//TODO
		}
		
		int schuku = pchuku.size();
		if (schuku>0){
			listchuku.clear();
			listrkmx.clear();
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId()
					+" and convert(varchar(10),ck.chuku.cksj,120) = '"+datestr+"'");
			listrkmx = hibernateTemplate.find("from Rkmx as rk where rk.rkxx.canku = "+canku.getId()
					+" and convert(varchar(10),rk.rkxx.rksj,120) = '"+datestr+"'");
			
			for(int i=0;i<schuku;i++){
				total_kcwt = new BigDecimal(0);
				total_rkwt = new BigDecimal(0);
				total_ckwt = new BigDecimal(0);
				nxt = new BigDecimal(0);
				wxt = new BigDecimal(0);
				djt = new BigDecimal(0);
				dxt = new BigDecimal(0);
				bhgt = new BigDecimal(0);
				
				//入库明细总重
				for(int i1=0;i1<listrkmx.size();i1++){
					
					if(listrkmx.get(i1).getProducts().getId()==(pkucun.get(i).getId()))
					{
						total_rkwt = total_rkwt.add((listrkmx.get(i1).getSpecifications().getWeight()).multiply(new BigDecimal(listrkmx.get(i1).getNumber())));
					}
				}
				//日销量
				for(int i2=0;i2<listchuku.size();i2++){
					if(listchuku.get(i2).getProducts().getId()==(pchuku.get(i).getId()))
					{
						total_ckwt = total_ckwt.add((listchuku.get(i2).getSpecifications().getWeight()).multiply(new BigDecimal(listchuku.get(i2).getNumber())));
					}
				}
				
				
				list.add(new ReportPmx(new Reportxx(), canku, pchuku.get(i), total_rkwt, total_ckwt, total_kcwt, nxt, wxt, djt, dxt,bhgt,null,null,null));//TODO
			}
		}
	
		return list;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<CheckOutTable> getCheckOutTable(Canku canku,Date date){
		
		List<CheckOutTable> list = new ArrayList();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
	    
		/*
		 * 得到出库明细中的	当日该仓库	的出库明细listchuku
		 */
	
		List<Chukumx> listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId()
				+" and convert(varchar(10),ck.chuku.cksj,120) = '"+datestr+"'");
		
		int schuku = listchuku.size();

		BigDecimal total_ckwt = new BigDecimal(0);
		for(int i=0;i<schuku;i++){
			total_ckwt = total_ckwt.add((listchuku.get(i).getSpecifications().getWeight()).multiply(new BigDecimal(listchuku.get(i).getNumber())));
			list.add(new CheckOutTable(listchuku.get(i).getProducts(), listchuku.get(i).getChuku(),listchuku.get(i).getSpecifications(),listchuku.get(i).getPch(),listchuku.get(i).getNumber(),listchuku.get(i).getChuku().getCankuByRkId()));	
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<CheckInTable> getCheckInTable(Canku canku,Date date){
		
		List<CheckInTable> list = new ArrayList();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
	    
		/*
		 * 当日入库产品类别psort
		 * 得到入库明细中的	当日该仓库	的入库明细listrkmx
		 */

		
		List<Rkmx> listrkmx = hibernateTemplate.find("from Rkmx as rk where rk.rkxx.canku = "+canku.getId()
				+" and convert(varchar(10),rk.rkxx.rksj,120) = '"+datestr+"'");
		for(int i=0;i<listrkmx.size();i++){
			list.add(new CheckInTable(listrkmx.get(i).getProducts(),listrkmx.get(i).getSpecifications(),listrkmx.get(i).getPch(),listrkmx.get(i).getNumber(),listrkmx.get(i).getMemo()));
		}
		
		return list;
	}
	@SuppressWarnings("unchecked")
	 
	public List<Kcxx> listZhuChangKuAll(int ckid) {
	
		List<Kcxx> list = hibernateTemplate.find("from Kcxx where cid ="+ String.valueOf(ckid));

		return list;
	}

	public List<ReportPmx> listZhuChangKuOther(int ckid,Date date){
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
	    List<Reportxx> listrpxx = hibernateTemplate.find("from Reportxx as rp where rp.ckid = "+ckid
				+" and convert(varchar(10),rp.date,120) = '"+datestr+"'");
	    if(listrpxx.size()==0){
	    	List<ReportPmx> listrpmx = new ArrayList();
	    	return listrpmx;
	    }
	    	
	    else
	    {
	    	List<ReportPmx> listrpmx = hibernateTemplate.find("from ReportPmx as rp where rp.rxxid = "+listrpxx.get(0).getId());
	    	if(listrpmx.size()==0)
	    		return listrpmx;
	    	else
	    		return listrpmx;
	    }
	}


	 
	@Transactional
	public void doentryZhuchangku(List<Chukumx> chukumxes,Rkxx rkxx){
		ArrayList<Rkmx> rkmx = new ArrayList();
		hibernateTemplate.save(rkxx);
		for(Chukumx chukumx:chukumxes){
			List<Products> product = (List<Products>) hibernateTemplate.find("from Products where name='"+chukumx.getProducts().getName().trim()+"'");
			String[] temp = chukumx.getSpecifications().getName().trim().split(" | ");
			List<Specifications> specification = (List<Specifications>) hibernateTemplate.find("from Specifications where name='"+temp[0].trim()+"' and packType='"+temp[2].trim()+"'");
			if((product.size()!=0)&&(specification.size()!=0)){
				Rkmx temprkmx = new Rkmx(rkxx,product.get(0),specification.get(0),chukumx.getPch().trim(),chukumx.getNumber(),(byte)1,(byte)1,null);
			    hibernateTemplate.save(temprkmx);
			
			
			    KcxxId id = new KcxxId(temprkmx.getProducts().getId(), temprkmx.getPch(),
					rkxx.getCanku().getId());
			    Kcxx kcxx = (Kcxx) hibernateTemplate.get(Kcxx.class, id);
			    if (kcxx == null) {
				    kcxx = new Kcxx();
				    kcxx.setId(id);
				    kcxx.setSpecifications(temprkmx.getSpecifications());
				    kcxx.setSaleType((byte)1);
				    kcxx.setStatus((byte)1);
				    kcxx.setNumber(temprkmx.getNumber());
				    hibernateTemplate.save(kcxx);
			    } else {
				    kcxx.entryNumber(temprkmx.getNumber());
				    hibernateTemplate.update(kcxx);
			    }
			    
			    
			    Chukumx tempchukumx = (Chukumx)hibernateTemplate.get(Chukumx.class, chukumx.getId());
			    tempchukumx.setStatus((byte)1);
			    hibernateTemplate.update(tempchukumx);
			    
		    }else{
		    	System.out.println("产品名或者规格错误！");
				throw new IllegalArgumentException("产品名或者规格错误！");
		    }
		}
		
	}
	


	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public void doDeliveryWareHouse(Chuku ck) {
	
        if(ck.getCankuByRkId().getType()==(byte)4)//损耗
			
			{
        
        	ck.setCankuByRkId(((List<Canku>)hibernateTemplate.find("from Canku where type=4")).get(0)); 
			}
		else if(ck.getCankuByRkId().getType()==(byte)3)//销售
			{
		
			ck.setCankuByRkId(((List<Canku>)hibernateTemplate.find("from Canku where type=3")).get(0)); 
			}
		else{
			ck.setCankuByRkId((Canku)hibernateTemplate.load(Canku.class, ck.getCankuByRkId().getId()));//移库
		}
		
		
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

	@SuppressWarnings("unchecked")
	@Transactional
	public void saveDayReportxx(List<ReportCmx> reportcmxlist,
			List<ReportPmx> reportpmxlist,Users users, String bno, Date today,int cankuid) {
		//判断数据库中是否存在当天的日报
		SimpleDateFormat bartDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	    String datetemp =bartDateFormat1.format(today);
		List<Reportxx> isreportxxexist = hibernateTemplate.find("from Reportxx as rx where rx.ckid='"
				+cankuid+"' and "+"convert(varchar(10),rx.date,120) = '"+datetemp+"'");
		
	
	
		
		List<Canku> cankulist= hibernateTemplate.find("from Canku as ck where ck.id = '"+cankuid+"'");//从数据库中读取该仓库信息
		Canku canku = new Canku();
		canku = cankulist.get(0);
		
		Reportxx tempreportxx = new Reportxx();
		tempreportxx.setBno(bno);
		tempreportxx.setDate(today);
		tempreportxx.setCkid(canku);
		tempreportxx.setReporter(users);
	
		//判断数据库中是否存在改天的日报，如果不存在则保存当天的日报信息，如果存在则更新已存在的日报信息（根据时间和仓库来查）
		if(isreportxxexist.size()==0){
			
			hibernateTemplate.save(tempreportxx);//存reportxx
			
		}		
		
		else
		{
			
			
			tempreportxx.setId(isreportxxexist.get(0).getId());
				
			hibernateTemplate.merge(tempreportxx);
		}
			

	
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(tempreportxx.getDate());
	    
	     
	     List<Reportxx> tempreportxx_data= hibernateTemplate.find("from Reportxx as rx where " +
	    		"convert(varchar(10),rx.date,120) = '"+datestr+"'"+" and rx.ckid='"+cankuid+"'");//取reportxx
  
	    
	    tempreportxx.setId(tempreportxx_data.get(0).getId());//取出当天的reportxx.id只有一行数据时适用
		
	    if(isreportxxexist.size()==0){
	    
	    //存储ReportPmx
		for(int i=0;i<reportpmxlist.size();i++)
		{
			ReportPmx tempreportpmx = new ReportPmx();
			tempreportpmx.setRxxid(tempreportxx);
			tempreportpmx.setCkid(canku);
			tempreportpmx.setCkt(reportpmxlist.get(i).getCkt().setScale(3));
			tempreportpmx.setDjt(reportpmxlist.get(i).getDjt().setScale(3));
			tempreportpmx.setDxt(reportpmxlist.get(i).getDxt().setScale(3));
			tempreportpmx.setKct(reportpmxlist.get(i).getKct().setScale(3));
			tempreportpmx.setNxt(reportpmxlist.get(i).getNxt().setScale(3));
			tempreportpmx.setPrdid(reportpmxlist.get(i).getPrdid());
			tempreportpmx.setRkt(reportpmxlist.get(i).getRkt().setScale(3));
			tempreportpmx.setWxt(reportpmxlist.get(i).getWxt().setScale(3));
			tempreportpmx.setBhgt(reportpmxlist.get(i).getBhgt().setScale(3)); 
			
			hibernateTemplate.save(tempreportpmx);
					
		}
	    }
	    else
	    {

	    	List<ReportPmx> temp = hibernateTemplate.find("from ReportPmx as rpmx where rpmx.rxxid.id='"+isreportxxexist.get(0).getId()+"'");

	    	hibernateTemplate.deleteAll(temp);
	    	
	    	for(int i=0;i<reportpmxlist.size();i++)
			{
				ReportPmx tempreportpmx = new ReportPmx();
				tempreportpmx.setRxxid(tempreportxx);
				tempreportpmx.setCkid(canku);
				tempreportpmx.setCkt(reportpmxlist.get(i).getCkt().setScale(3));
				tempreportpmx.setDjt(reportpmxlist.get(i).getDjt().setScale(3));
				tempreportpmx.setDxt(reportpmxlist.get(i).getDxt().setScale(3));
				tempreportpmx.setKct(reportpmxlist.get(i).getKct().setScale(3));
				tempreportpmx.setNxt(reportpmxlist.get(i).getNxt().setScale(3));
				tempreportpmx.setPrdid(reportpmxlist.get(i).getPrdid());
				tempreportpmx.setRkt(reportpmxlist.get(i).getRkt().setScale(3));
				tempreportpmx.setWxt(reportpmxlist.get(i).getWxt().setScale(3));
				tempreportpmx.setBhgt(reportpmxlist.get(i).getBhgt().setScale(3)); 
	
				
				hibernateTemplate.save(tempreportpmx);
						
			}
	    }
	    if(isreportxxexist.size()==0){
		//存储ReportCmx
		for(int i=0;i<reportcmxlist.size();i++)
		{
			ReportCmx tempreportcmx = new ReportCmx();
			tempreportcmx.setRxxid(tempreportxx);
			tempreportcmx.setCkid(canku);
			tempreportcmx.setCkt(reportcmxlist.get(i).getCkt().setScale(3)); 
			tempreportcmx.setPrdid(reportcmxlist.get(i).getPrdid());
			tempreportcmx.setRkid(reportcmxlist.get(i).getRkid());			
			
			hibernateTemplate.saveOrUpdate(tempreportcmx);
		}

	}

	
	    else
	    {
            
	    	List<ReportCmx> temp = hibernateTemplate.find("from ReportCmx as rcmx where rcmx.rxxid.id='"+isreportxxexist.get(0).getId()+"'");
	    	hibernateTemplate.deleteAll(temp);
	    	
	    	for(int i=0;i<reportcmxlist.size();i++)
			{
				ReportCmx tempreportcmx = new ReportCmx();
				tempreportcmx.setRxxid(tempreportxx);
				tempreportcmx.setCkid(canku);
				tempreportcmx.setCkt(reportcmxlist.get(i).getCkt().setScale(3)); 
				tempreportcmx.setPrdid(reportcmxlist.get(i).getPrdid());
				tempreportcmx.setRkid(reportcmxlist.get(i).getRkid());
			
				hibernateTemplate.save(tempreportcmx);
			}
	    }

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ReportCmx> getDayReportCmx(Canku canku,Date date){
		
		List<ReportCmx> list = new ArrayList();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
	    
		/*
		 * 得到出库明细中的	当日该仓库	的出库明细listchuku
		 */
		List<Chukumx> listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId()
				+" and convert(varchar(10),ck.chuku.cksj,120) = '"+datestr+"'");
		
		int schuku = listchuku.size();
		BigDecimal total_ckwt = new BigDecimal(0);
		for(int i=0;i<schuku;i++){
			total_ckwt = new BigDecimal(0);
			total_ckwt = total_ckwt.add((listchuku.get(i).getSpecifications().getWeight()).multiply(new BigDecimal(listchuku.get(i).getNumber())));
			list.add(new ReportCmx(i, new Reportxx(), canku , listchuku.get(i).getProducts(), listchuku.get(i).getChuku().getCankuByRkId(), total_ckwt));
			
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	 
	@Transactional
	public Canku getCangkuById(Integer cangkuId) {
		
		List<Canku> cankulist = hibernateTemplate.find("from Canku where id='"+cangkuId+"'");
		Canku canku = cankulist.get(0);
		return canku;
	}

	 
	public List<Kcxx> getAllProducts(Canku canku) {
		// TODO Auto-generated method stub
		List<Kcxx> result = new ArrayList();
		result = (List<Kcxx>)hibernateTemplate.find("from Kcxx where cid ="+ canku.getId());

		return result;

	}	

	 
	public List<Custom> getAllCustom(){
		return (List<Custom>) hibernateTemplate.find("from Custom");
	}
	 
	@Transactional
	public List<XSyikumx> getXSyikumx(Canku canku){
		List<XSyikumx> result = new ArrayList<XSyikumx>();
		result = hibernateTemplate.find("from XSyikumx as mx where mx.canku = "+canku.getId()+" and mx.status = 0");
		return result;
	}
	 
	@Transactional
	public void resetXsykxxStatus(int index, int num){
		XSyikumx temp = (XSyikumx)hibernateTemplate.get(XSyikumx.class, index);
		temp.setStatus((byte)1);
		temp.setNumber(num);
		hibernateTemplate.update(temp);
	}

	@SuppressWarnings("unchecked")
	 
	@Transactional
	public List<XSfahuomx> getXSfahuomx(Canku canku){
		List<XSfahuomx> result = new ArrayList<XSfahuomx>();
		result = hibernateTemplate.find("from XSfahuomx as mx where mx.canku = "+canku.getId()+" and mx.status = 0");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	 
	@Transactional
	public void resetXsfhxxStatus(int index, int num){
		XSfahuomx temp = (XSfahuomx)hibernateTemplate.get(XSfahuomx.class, index);
		temp.setStatus((byte)1);
		temp.setNumber(num);
		hibernateTemplate.update(temp);
    }
	 
	public List<KcxxCheck> getCheckedProducts(Canku canku,XSfahuomx xsfhmx) {
		// TODO Auto-generated method stub
		List<KcxxCheck> result = new ArrayList();
		List<Kcxx> tempresult = new ArrayList();
		tempresult = (List<Kcxx>)hibernateTemplate.find("from Kcxx where cid ="+ canku.getId()+" and saleType ="+ xsfhmx.getXsfahuoxx().getType()+" and specifications ="+ xsfhmx.getSpecification().getId()+" and products ="+ xsfhmx.getProduct().getId());
		for(Kcxx kcxx:tempresult){
			if(kcxx.getSaleType()==1)result.add(new KcxxCheck("内销",kcxx));
			else if(kcxx.getSaleType()==2)result.add(new KcxxCheck("定向",kcxx));
			else if(kcxx.getSaleType()==3)result.add(new KcxxCheck("外销",kcxx));
			else result.add(new KcxxCheck("未定",kcxx));
		}
		return result;

	}
	 
	public List<KcxxCheck> getCheckedProducts(Canku canku,XSyikumx xsykmx) {
		// TODO Auto-generated method stub
		List<KcxxCheck> result = new ArrayList();
		List<Kcxx> tempresult = new ArrayList();
		tempresult = (List<Kcxx>)hibernateTemplate.find("from Kcxx where cid ="+ canku.getId()+" and saleType ="+ xsykmx.getXsyikuxx().getType()+" and specifications ="+ xsykmx.getSpecification().getId()+" and products ="+ xsykmx.getProduct().getId());
		for(Kcxx kcxx:tempresult){
			if(kcxx.getSaleType()==1)result.add(new KcxxCheck("内销",kcxx));
			else if(kcxx.getSaleType()==2)result.add(new KcxxCheck("定向",kcxx));
			else if(kcxx.getSaleType()==3)result.add(new KcxxCheck("外销",kcxx));
			else result.add(new KcxxCheck("未定",kcxx));
		}
		return result;

	}
	@SuppressWarnings("unchecked")
	public Custom getCustomerById(Integer customerId){

		List<Custom> customerList = hibernateTemplate.find("from Custom where id='"+customerId+"'");
		return customerList.get(0);
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
	public List<ReportCmx> searchDayReportCmx(Date begindate,Date enddate,Canku canku) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String begindatestr =bartDateFormat.format(begindate);
	    String enddatestr =bartDateFormat.format(enddate);
	    String nowstr = bartDateFormat.format(new Date());
	    List<Reportxx> listrpxx = new ArrayList();
	    if(enddatestr.equals(nowstr))
	    {
	    	listrpxx = hibernateTemplate.find("from Reportxx as rp where rp.ckid = "+canku.getId()
	 				+" and convert(varchar(10),rp.date,120) >= '"+begindatestr+"' and convert(varchar(10),rp.date,120) < '"+enddatestr+"'");
	    }
	    else
	    {
	    	listrpxx = hibernateTemplate.find("from Reportxx as rp where rp.ckid = "+canku.getId()
	 				+" and convert(varchar(10),rp.date,120) >= '"+begindatestr+"' and convert(varchar(10),rp.date,120) <= '"+enddatestr+"'");
	    }
	   
	    if(listrpxx.size()==0)
	    	return null;
	    List<ReportCmx> listreportcmx = new ArrayList();
	    for(int i=0;i<listrpxx.size();i++){    	
	    	listreportcmx.addAll(hibernateTemplate.find("from ReportCmx where rxxid = "+listrpxx.get(i).getId()));
	    }
	    if(enddatestr.equals(nowstr))
	    	listreportcmx.addAll(this.getDayReportCmx(canku, new Date()));
	    return listreportcmx;
	}

	@SuppressWarnings("unchecked")
	public List<ReportPmx> searchDayReportPmx(Date begindate,Date enddate,Canku canku) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String begindatestr =bartDateFormat.format(begindate);
	    String enddatestr =bartDateFormat.format(enddate);
	    String nowstr = bartDateFormat.format(new Date());
	    List<ReportPmx> reportpmxlist_return = new ArrayList();//最终返回的Pmx
	    List<ReportPmx> listrppmx = new ArrayList();//查询日期区间所有的pmx
	    List<ReportPmx> listrppmx_end = new ArrayList();//截止日期的pmx
	    List<Products> listprd = new ArrayList();//所有产品
	    if(!enddatestr.equals(nowstr)){
	    
	    listrppmx= hibernateTemplate.find("from ReportPmx as rp where rp.rxxid.ckid = "+canku.getId()
				+" and convert(varchar(10),rp.rxxid.date,120) >= '"+begindatestr+"' and convert(varchar(10),rp.rxxid.date,120) <= '"+enddatestr+"'");
	    listrppmx_end = hibernateTemplate.find("from ReportPmx as rp where rp.rxxid.ckid = "+canku.getId()
	    		+" and convert(varchar(10),rp.rxxid.date,120) = '"+enddatestr+"'");
	    listprd = hibernateTemplate.find("select distinct prdid from ReportPmx as rpp where rpp.rxxid.ckid = "+canku.getId()
	    		+" and convert(varchar(10),rpp.rxxid.date,120) >= '"+begindatestr+"' and convert(varchar(10),rpp.rxxid.date,120) <= '"+enddatestr+"'");
	    }
	    else
	    {
	    	listrppmx.addAll(hibernateTemplate.find("from ReportPmx as rp where rp.rxxid.ckid = "+canku.getId()
					+" and convert(varchar(10),rp.rxxid.date,120) >= '"+begindatestr+"' and convert(varchar(10),rp.rxxid.date,120) < '"+enddatestr+"'"));
	    	listrppmx.addAll(this.getDayReportPmx(canku, new Date()));
	    	listrppmx_end.addAll(this.getDayReportPmx(canku, new Date()));
	    	Set<Products> tempprd = new HashSet<Products>();
	    	tempprd.addAll(hibernateTemplate.find("select distinct prdid from ReportPmx as rpp where rpp.rxxid.ckid = "+canku.getId()
	    		+" and convert(varchar(10),rpp.rxxid.date,120) >= '"+begindatestr+"' and convert(varchar(10),rpp.rxxid.date,120) < '"+enddatestr+"'"));
	    	for(int i=0;i<this.getDayReportPmx(canku, new Date()).size();i++)
	    	tempprd.add(this.getDayReportPmx(canku, new Date()).get(i).getPrdid());
	    	listprd.addAll(tempprd);
	    }
	    
	    if(listprd.size()==0)
	    	return null;
	    else
	    {  		
	    	for(int i=0;i<listprd.size();i++){
	    		ReportPmx temp = new ReportPmx();
	    		temp.setCkt(new BigDecimal(0));
	    		temp.setRkt(new BigDecimal(0));
	    		for(int j=0;j<listrppmx.size();j++){
	    			if(listrppmx.get(j).getPrdid().getId()==listprd.get(i).getId()){
	    				temp.setPrdid(listrppmx.get(j).getPrdid());    				
	    				temp.setCkt(temp.getCkt().add(listrppmx.get(j).getCkt()));
	    				temp.setRkt(temp.getRkt().add(listrppmx.get(j).getRkt()));	
	    				temp.setBhgt(new BigDecimal(0));
						temp.setNxt(new BigDecimal(0));
						temp.setWxt(new BigDecimal(0));
						temp.setDjt(new BigDecimal(0));
						temp.setDxt(new BigDecimal(0));
						temp.setKct(new BigDecimal(0));
	    				for(int k=0;k<listrppmx_end.size();k++){
	    					if(listprd.get(i).equals(listrppmx_end.get(k).getPrdid())){
	    						temp.setBhgt(listrppmx_end.get(k).getBhgt());
	    						temp.setNxt(listrppmx_end.get(k).getNxt());
	    						temp.setWxt(listrppmx_end.get(k).getWxt());
	    						temp.setDjt(listrppmx_end.get(k).getDjt());
	    						temp.setDxt(listrppmx_end.get(k).getDxt());
	    						temp.setKct(listrppmx_end.get(k).getKct());
	    					}
	    				}
	    			}
	    		}
	    		reportpmxlist_return.add(temp);
	    	}
	    	
	    }
	   
	    	return reportpmxlist_return;
	}
	
	 
	@Transactional
	public void doEntryWareHouse(Rkxx rkxx) {
		rkxx.setCanku((Canku)hibernateTemplate.load(Canku.class, rkxx.getCanku().getId()));
		rkxx.setRkczy((Users)hibernateTemplate.load(Users.class, rkxx.getRkczy().getId()));	
	
		hibernateTemplate.save(rkxx);
		Set<Rkmx> rkmxs = rkxx.getRkmxes();
	
		// 对应于入库信息，逐条的更改库存信息。
		for (Rkmx rkmx : rkmxs) {
			rkmx.setRkxx(rkxx);
			rkmx.setProducts((Products)hibernateTemplate.load(Products.class, rkmx.getProducts().getId()));			
			if (rkmx.getSpecifications().getId() != 0)
			rkmx.setSpecifications((Specifications)hibernateTemplate.get(Specifications.class, rkmx.getSpecifications().getId()));
			else{
				List<Specifications> s = hibernateTemplate.find("from Specifications where name = '液体' and packType = '大罐'");
			    if(s.size()>0)rkmx.setSpecifications(s.get(0));
			}
			
			hibernateTemplate.save(rkmx);
			
			KcxxId id = new KcxxId(rkmx.getProducts().getId(), rkmx.getPch(),
					rkxx.getCanku().getId());
			Kcxx kcxx = (Kcxx) hibernateTemplate.get(Kcxx.class, id);
			if (kcxx == null) {
				kcxx = new Kcxx();
				kcxx.setId(id);
				kcxx.setSpecifications(rkmx.getSpecifications());
				kcxx.setNumber(rkmx.getNumber());
				kcxx.setSaleType((byte)1);
				kcxx.setStatus((byte)1);
				hibernateTemplate.save(kcxx);
			} else {
				kcxx.entryNumber(rkmx.getNumber());
				hibernateTemplate.update(kcxx);
			}
		}
	}
}

