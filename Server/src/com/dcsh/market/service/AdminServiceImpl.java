package com.dcsh.market.service;


import java.util.ArrayList;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.dcsh.market.Canku;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Kcxx;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.Reportxx;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Smmdingyue;
import com.dcsh.market.Smmdy;
import com.dcsh.market.Specifications;
import com.dcsh.market.UserGroup;
import com.dcsh.market.UserGroupPriv;
import com.dcsh.market.UserPriv;
import com.dcsh.market.Users;
import com.dcsh.market.cycrelgck;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.URLGPriv;


public class AdminServiceImpl implements AdminService {

    private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;  
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	@Transactional
	public void addProduct(Products product) {
		
		if(hibernateTemplate.find("from Products where name ='"+ String.valueOf(product.getName())+"'").size()==0){
			hibernateTemplate.save(product);
		}else{
			System.out.println("该产品已有！");
		}
		
	}

	@Override
	@Transactional
	public void delProduct(Products product) {
		
		List<UserPriv> ups= (List<UserPriv>)hibernateTemplate.find("from UserPriv where resource='prd:"+String.valueOf(product.getId())+"'");
		for(UserPriv up:ups){
			hibernateTemplate.delete(up);
		}
		hibernateTemplate.delete(hibernateTemplate.load(Products.class, product.getId()));
	}

	@Override
	public List<Products> getAllProducts() {
		return hibernateTemplate.find("from Products");
	}

	@Override
	@Transactional
	public void updateProduct(Products product) {
	
		Products temp = (Products) hibernateTemplate.load(Products.class, product.getId());
		temp.setName(product.getName());
		hibernateTemplate.update(temp);
	}

	@Override
	@Transactional
	public void addCanku(Canku canku) {
		//
		if(hibernateTemplate.find("from Canku where name ='"+ String.valueOf(canku.getName())+"'").size()==0){
			hibernateTemplate.save(canku);
		}else{
			System.out.println("该产品已有！");
			throw new IllegalArgumentException("该产品已有！");
		}
	}

	@Override
	@Transactional
	public void addSpecification(Specifications specification) {
		hibernateTemplate.save(specification);
	}

	@Override
	@Transactional
	public void delCanku(Canku canku) {
		// 
		List<UserPriv> ups= (List<UserPriv>)hibernateTemplate.find("from UserPriv where resource='canku:"+String.valueOf(canku.getId())+"'");
		for(UserPriv up:ups){
			hibernateTemplate.delete(up);
		}
		hibernateTemplate.delete(hibernateTemplate.load(Canku.class, canku.getId()));
	}

	@Override
	@Transactional
	public void delSpecification(Specifications specification) {
		// 
		hibernateTemplate.delete(hibernateTemplate.load(Specifications.class, specification.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
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

	@Override
	public List<Specifications> getAllSpecifications() {
		
		return hibernateTemplate.find("from Specifications");
	}

	@Override
	@Transactional
	public void updateCanku(Canku canku) {
		// 
		Canku temp = (Canku) hibernateTemplate.load(Canku.class, canku.getId());
		temp.setName(canku.getName());
		temp.setType(canku.getType());
		temp.setAddress(canku.getAddress());
		hibernateTemplate.update(temp);
	}

	@Override
	@Transactional
	public void updateSpecification(Specifications specification) {
		// 
		Specifications temp = (Specifications) hibernateTemplate.load(Specifications.class, specification.getId());
		temp.setName(specification.getName());
		temp.setPackType(specification.getPackType());
		temp.setWeight(specification.getWeight());
		hibernateTemplate.update(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CankuPriv> loginAdmin(String username, byte[] Password) {
		
		List<CankuPriv> adminp = new ArrayList();
		List<UserPriv> uprivs = new ArrayList();
		List<Users> users = 
			hibernateTemplate.find("from Users where name = '" +  username.trim() + "'");
		if (users.size() == 0) throw new IllegalArgumentException("错误的用户名！");
		
		Users user = users.iterator().next();
	
		//验证用户名,以后可能需要考虑加密版本
		
		if (Arrays.equals(Password, user.getPassword())){
			uprivs = hibernateTemplate.find("from UserPriv where UID = " +  user.getId());
			for(UserPriv upriv:uprivs){
				String res = upriv.getResource();			   
			    if(res.trim().equals("admin")){
			    	adminp.add(new CankuPriv(user,null));
			    }
			}
			return adminp;
		}
		else return null;
	}
	
	@Override
	@Transactional
	public List<ReportPmx> listStorage(int cankuid,Date date){
		
		List<ReportPmx> list = new ArrayList<ReportPmx>();
		
		List<Kcxx> kcxxs = new ArrayList<Kcxx>();
		Date nowdate = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(date);
	    String nowdatestr =bartDateFormat.format(nowdate);
	
	    
	    BigDecimal total_kcwt = new BigDecimal(0);
		BigDecimal total_rkwt = new BigDecimal(0);
		BigDecimal total_ckwt = new BigDecimal(0);
		BigDecimal nxt = new BigDecimal(0);
		BigDecimal wxt = new BigDecimal(0);
		BigDecimal djt = new BigDecimal(0);
		BigDecimal dxt = new BigDecimal(0);
		BigDecimal bhgt = new BigDecimal(0);
		
	    if((nowdatestr.equals(datestr))==true){
	 
	    	if(cankuid==0){
//				全库 当前
	    		kcxxs = hibernateTemplate.find("from Kcxx");
	    		list = todaylist(cankuid);//cankuid==0
	 
	    	}
	    	else{
//				某库 当前
	    		list = todaylist(cankuid);
	    	}
	    	return list;
	    }
	    else{
//	    	查表
	    	if(cankuid==0){
//	    		全库 某天
	    		list.clear();
	    		List<ReportPmx> listall = hibernateTemplate.find("from ReportPmx as rp where convert(varchar(10),rp.rxxid.date,120) = '"+datestr+"'");
	    		List<Products> productall = hibernateTemplate.find("select distinct prdid from ReportPmx as rp where convert(varchar(10),rp.rxxid.date,120) = '"+datestr+"'");
	    		int pall = productall.size();
	    
	    		for(int i=0;i<pall;i++){
	    			total_kcwt = new BigDecimal(0);
    				total_rkwt = new BigDecimal(0);
    				total_ckwt = new BigDecimal(0);
    				nxt = new BigDecimal(0);
    				wxt = new BigDecimal(0);
    				djt = new BigDecimal(0);
    				dxt = new BigDecimal(0);
    				bhgt = new BigDecimal(0);
	    			for(int j=0;j<listall.size();j++){
	    				if(productall.get(i).getId()==listall.get(j).getPrdid().getId()){
	    					total_kcwt = total_kcwt.add(listall.get(j).getKct());
		    				total_rkwt = total_rkwt.add(listall.get(j).getRkt());
		    				total_ckwt = total_ckwt.add(listall.get(j).getCkt());
		    				nxt = nxt.add(listall.get(j).getNxt());
		    				wxt = wxt.add(listall.get(j).getWxt());
		    				djt = djt.add(listall.get(j).getDjt());
		    				dxt = dxt.add(listall.get(j).getDxt());
		    				bhgt = bhgt.add(listall.get(j).getBhgt());
	    				}
	    			}
	    			list.add(new ReportPmx(new Reportxx(),new Canku(),productall.get(i),
	    					total_rkwt,total_ckwt,total_kcwt,nxt,
	    					wxt,djt,dxt,bhgt));
	    		}
	    	
	    	}
	    	else{
//	    		某库 某天
	    	
	    		list.clear();
	    		list = hibernateTemplate.find("from ReportPmx as rp where rp.ckid = "+cankuid
					+" and convert(varchar(10),rp.rxxid.date,120) = '"+datestr+"'");
	    	}
	    	return list;
	    }
	}
	public List<ReportPmx> todaylist(int cankuid){
		Canku canku = new Canku();
		canku.setId(cankuid);
//		代表销售的仓库id
//		int saleck = 99999;
		
		Date nowdate = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String nowdatestr =bartDateFormat.format(nowdate);
		
		List<ReportPmx> list = new ArrayList<ReportPmx>();
		
		List<Products> pkucun = hibernateTemplate.find("select distinct products from Kcxx");
			
		List<Rkmx> listrkmx = hibernateTemplate.find("from Rkmx as rk where convert(varchar(10),rk.rkxx.rksj,120) = '"+nowdatestr+"'");
			
		List<Chukumx> listchuku = hibernateTemplate.find("from Chukumx as ck where convert(varchar(10),ck.chuku.cksj,120) = '"+nowdatestr+"'");
			
		List<Products> pchuku = hibernateTemplate.find("select distinct products from Chukumx");

		if(cankuid!=0){
		    pkucun = hibernateTemplate.find("select distinct products from Kcxx as kc where kc.id.cid = "+canku.getId());
			
			listrkmx = hibernateTemplate.find("from Rkmx as rk where convert(varchar(10),rk.rkxx.rksj,120) = '"+nowdatestr+"' and rk.rkxx.canku = "+canku.getId());
				
			listchuku = hibernateTemplate.find("from Chukumx as ck where convert(varchar(10),ck.chuku.cksj,120) = '"+nowdatestr+"' and ck.chuku.cankuByCankuId = "+canku.getId());
				
			pchuku = hibernateTemplate.find("select distinct products from Chukumx as ck where ck.chuku.cankuByCankuId = "+canku.getId());
			
		}
		List<Kcxx> listkcxx =new ArrayList<Kcxx>();
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
			listkcxx = hibernateTemplate.find("from Kcxx as kc where kc.products.id="+pkucun.get(i).getId());
			if(cankuid!=0){
				listkcxx = hibernateTemplate.find("from Kcxx as kc where kc.products.id="+pkucun.get(i).getId()+" and kc.id.cid="+canku.getId());
			}
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
			list.add(new ReportPmx(new Reportxx(),new Canku(), pkucun.get(i), total_rkwt, total_ckwt, total_kcwt, nxt, wxt, djt, dxt,bhgt));
		}
		
		return list;
	}

	@Override
	@Transactional
	public void addUser(Users user) {
		// 
		if(hibernateTemplate.find("from Users where name ='"+ String.valueOf(user.getName())+"'").size()==0){
			hibernateTemplate.save(user);
		}else{
			System.out.println("该产品已有！");
			throw new IllegalArgumentException("该产品已有！");
			
		}
	}

	@Override
	@Transactional
	public void delUser(Users user) {
		// 
		hibernateTemplate.delete(hibernateTemplate.load(Users.class, user.getId()));
	}

	@Override
	public List<Users> getAllUsers() {
		
		return hibernateTemplate.find("from Users");
	}

	@Override
	@Transactional
	public void updateUser(Users user) {
		
		Users temp = (Users) hibernateTemplate.load(Users.class, user.getId());
		temp.setName(user.getName());
		temp.setPassword(user.getPassword());
		temp.setPhone(user.getPhone());
		temp.setDescription(user.getDescription());
		hibernateTemplate.update(temp);
	}
	
	@Override
	@Transactional
	public List<UserGroup> getAllGroups(){
		return hibernateTemplate.find("from UserGroup");
	}
	
	@Override
	@Transactional
	public void delUserGroup(UserGroup usergroup){
		hibernateTemplate.delete(hibernateTemplate.load(UserGroup.class, usergroup.getId()));
	}
	@Override
	@Transactional
	public void  addUserGroup(UserGroup usergroup){
		if(hibernateTemplate.find("from UserGroup where name ='"+ String.valueOf(usergroup.getName())+"'").size()==0){
			hibernateTemplate.save(usergroup);
		}else{
			System.out.println("该产品已有！");
			throw new IllegalArgumentException("该产品已有！");
		}
	}
	@Override
	@Transactional
	public void updateUserGroup(UserGroup usergroup){
	
		UserGroup temp = (UserGroup) hibernateTemplate.load(UserGroup.class, usergroup.getId());
	
		temp.setName(usergroup.getName());

		temp.setDescription(usergroup.getDescription());

		hibernateTemplate.update(temp);
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Boolean> getSelectedUserlist(int groupId) {
		UserGroup group = new UserGroup();
		List<UserGroup> grouplist = hibernateTemplate.find("from UserGroup where id='"+groupId+"'");
    	group = grouplist.get(0);
    	
    	List<Boolean> selectedUserlist = new ArrayList<Boolean>();
    	List<Users> userlist=hibernateTemplate.find("from Users");
    	for(Users user:userlist)
    	{
    		if(user.getGroup().contains(group)){
    			
    			selectedUserlist.add(true);
    		
    		}
    		else{
    			selectedUserlist.add(false);
    			
    			}
    		
    	}
    	return selectedUserlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void saveGroupMember(List<Boolean> isgroupmember,Integer groupId) {
		
		List<Users> users = hibernateTemplate.find("from Users");
		List<UserGroup> group = new ArrayList<UserGroup>();
		group = hibernateTemplate.find("from UserGroup where id='"+groupId+"'");
		
		for(int i=0;i<isgroupmember.size();i++)
		{
			 if(isgroupmember.get(i)&&!users.get(i).getGroup().contains(group.get(0)))
			 {
				 users.get(i).getGroup().add(group.get(0));
				
			 }
			 else if(!isgroupmember.get(i)&&users.get(i).getGroup().contains(group.get(0)))
			 {
				 users.get(i).getGroup().remove(group.get(0));
				
			 }
			
		}
		
	}

	
	@Override
	public List<URLGPriv> getgrouppriv(){
		List<URLGPriv> result = new ArrayList();
		List<UserGroup> groups = hibernateTemplate.find("from UserGroup");
		for(UserGroup group:groups ){
			result.add(new URLGPriv(group,null));
		}
		for(URLGPriv griv:result){
			List<UserGroupPriv> groupprivs = (List<UserGroupPriv>)hibernateTemplate.find("from UserGroupPriv where GID="+String.valueOf(griv.getUsergroup().getId()));
			if(groupprivs.size()!=0){
				for(UserGroupPriv grouppriv:groupprivs){
					String[] resources = grouppriv.getResource().split(":");
					if(resources[0].equals("url")){
						if(resources[1].contains("system"))griv.setUrl("管理员");
						else if(resources[1].contains("cyc"))griv.setUrl("储运处");
						else if(resources[1].contains("zzk"))griv.setUrl("中转库");
						else if(resources[1].contains("zck"))griv.setUrl("驻厂库");
						else if(resources[1].contains("xs"))griv.setUrl("销售中心");
						else griv.setUrl("无限制");
					}
				
				}
			}
		}
	
		return result;
	}
	
	@Override
	@Transactional
	public void setgrouppriv(int gid, int res){
		UserGroupPriv newgpriv = new UserGroupPriv();
		List<UserGroupPriv> ugps = 
			(List<UserGroupPriv>)hibernateTemplate.find("from UserGroupPriv where GID="+String.valueOf(gid));
		hibernateTemplate.deleteAll(ugps);
		UserGroup group = (UserGroup)hibernateTemplate.get(UserGroup.class, gid);
		newgpriv.setType(1);
		newgpriv.setUsergroup(group);
		switch (res){
		case 0: break;
		case 1: newgpriv.setResource("url:.*(/systemadmin/|/admin|/index|/user).*");hibernateTemplate.save(newgpriv);break;
		case 2: newgpriv.setResource("url:.*");hibernateTemplate.save(newgpriv);break;
		case 3: newgpriv.setResource("url:.*(/warehouseadmin/|/cyc|/index|/user).*");hibernateTemplate.save(newgpriv);break;
		case 4: newgpriv.setResource("url:.*(/zhongzhuanku/|/zzk|/index|/user).*");hibernateTemplate.save(newgpriv);break;
		case 5: newgpriv.setResource("url:.*(/zhuchangku/|/zck|/index|/user).*");hibernateTemplate.save(newgpriv);break;
		case 6: newgpriv.setResource("url:.*(/xiaoshou/|/xs|/index|/user).*");hibernateTemplate.save(newgpriv);break;
		default: break;
		}
		
	}
	
	@Override
	@Transactional
	public List<ReportCmx> listSales(Date begindate,Date enddate){
		List<ReportCmx> list = new ArrayList<ReportCmx>();
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		List<Products> pchuku = new ArrayList<Products>();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal total_ckwt = new BigDecimal(0);
		
		if(begindate.compareTo(enddate)<=0){
			
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type = 3");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");
			
			pchuku = hibernateTemplate.find("select distinct products from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
			
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
	@Override
	@Transactional
	public List<Chukumx> listSalesmx(Date begindate,Date enddate){
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		
		if(begindate.compareTo(enddate)<=0){
				
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type = 3");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
		}
		return listchuku;
	}
	@Override
	@Transactional
	public List<ReportCmx> listDestroy(Date begindate,Date enddate){
		List<ReportCmx> list = new ArrayList<ReportCmx>();
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		List<Products> pchuku = new ArrayList<Products>();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal total_ckwt = new BigDecimal(0);
		
		if(begindate.compareTo(enddate)<=0){
			
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=5");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
		    
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");
			
			pchuku = hibernateTemplate.find("select distinct products from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
			
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
	@Override
	@Transactional
	public List<Chukumx> listDestroymx(Date begindate,Date enddate){
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		
		if(begindate.compareTo(enddate)<=0){
				
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=5");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId = "+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
		}
		return listchuku;
	}
	@Override
	@Transactional
	public List<ReportCmx> listLoss(Date begindate,Date enddate){
		List<ReportCmx> list = new ArrayList<ReportCmx>();
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		List<Products> pchuku = new ArrayList<Products>();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		BigDecimal total_ckwt = new BigDecimal(0);
		
		if(begindate.compareTo(enddate)<=0){
			
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=4");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
			listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId ="+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");
			
			pchuku = hibernateTemplate.find("select distinct products from Chukumx as ck where ck.chuku.cankuByRkId ="+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
			
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
	@Override
	@Transactional
	public List<Chukumx> listLossmx(Date begindate,Date enddate){
		List<Chukumx> listchuku = new ArrayList<Chukumx>();
		
		if(begindate.compareTo(enddate)<=0){
				
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String date1 =bartDateFormat.format(begindate);
		    String date2 =bartDateFormat.format(enddate);
		    String cid = null;
		    List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=4");
		    if(cankus.size()!=0){cid = String.valueOf(cankus.get(0).getId());
		    listchuku = hibernateTemplate.find("from Chukumx as ck where ck.chuku.cankuByRkId ="+cid
					+" and convert(varchar(10),ck.chuku.cksj,120) >= '"+date1+"' " +
					"and convert(varchar(10),ck.chuku.cksj,120) <= '"+date2+"' ");}
		}
		return listchuku;
	}
	@Override
	@Transactional
	public List<Custom> getAllCustoms(){
		return hibernateTemplate.find("from Custom");
	}
	
	@Override
	@Transactional
	public void delCustom(Custom custom){
		hibernateTemplate.delete(hibernateTemplate.load(Custom.class, custom.getId()));
	}
	@Override
	@Transactional
	public void addCustom(Custom custom){
		if(hibernateTemplate.find("from Custom where customName ='"+ String.valueOf(custom.getCustomName())+"'").size()==0){
			hibernateTemplate.save(custom);
		}else{
			System.out.println("该客户已有！");
			throw new IllegalArgumentException("该客户已有！");
		}
	}
	@Override
	@Transactional
	public void updateCustom(Custom custom){
		Custom temp = (Custom) hibernateTemplate.get(Custom.class, custom.getId());
		temp.setCustomName(custom.getCustomName());
		temp.setPhone(custom.getPhone());
		temp.setShdz(custom.getShdz());
		hibernateTemplate.update(temp);
	}
	
	@Override
	@Transactional
	public void addFahuo(Fahuo fahuo) {
		
		if(hibernateTemplate.find("from Fahuo where name ='"+ String.valueOf(fahuo.getName())+"'").size()==0){
			hibernateTemplate.save(fahuo);
		}else{
			System.out.println("该发货单位已有！");
		}
		
	}

	@Override
	@Transactional
	public void delFahuo(Fahuo fahuo) {
		
		hibernateTemplate.delete(hibernateTemplate.load(Fahuo.class, fahuo.getId()));
	}

	@Override
	public List<Fahuo> getAllFahuos() {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from Fahuo");
	}

	@Override
	@Transactional
	public void updateFahuo(Fahuo fahuo) {
	
		Fahuo temp = (Fahuo) hibernateTemplate.load(Fahuo.class, fahuo.getId());
		temp.setName(fahuo.getName());
		hibernateTemplate.update(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPriv> getuserprivByUserId(int userid){
	    
		return hibernateTemplate.find("from UserPriv where user.id ='"+userid+"'"); 
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void saveUserpriv(List<Boolean> cankupriv, List<Boolean> prdpriv,
			int userId) {
		List<UserPriv> userpriv = 
		(List<UserPriv>)hibernateTemplate.find("from UserPriv where user.id = '"+userId+"'");
		
		hibernateTemplate.deleteAll(userpriv);
		Users user = (Users)hibernateTemplate.get(Users.class, userId);
		List<Canku> cankus = this.getAllCankus();
		List<Products> products =
			(List<Products>)hibernateTemplate.find("from Products");
		for(int i=0;i<cankupriv.size();i++)
		
			if(cankupriv.get(i))
							
				hibernateTemplate.save(new UserPriv(user,"canku:"+cankus.get(i).getId(),1));
		
		for(int j=0;j<prdpriv.size();j++)
			
			if(prdpriv.get(j))
				
				hibernateTemplate.save(new UserPriv(user,"prd:"+products.get(j).getId(),1));
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Smmdy> loadSmmdybyuser(Users user){
		List<Smmdy> result = new ArrayList();
		List<Smmdingyue> temp = (List<Smmdingyue>)hibernateTemplate.find("from Smmdingyue where user.id="+String.valueOf(user.getId()));
		for(Smmdingyue sdy:temp){
			Smmdy smmdy = new Smmdy(sdy);
			result.add(smmdy);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void updateSmmdy(List<Smmdingyue> sdys){
		
		for(int i=0;i<sdys.size();i++){
			List<Smmdingyue> tempsmmdy = (List<Smmdingyue>) hibernateTemplate.find("from Smmdingyue where user.id="+String .valueOf(sdys.get(i).getUser().getId())+" and product.id="+
				String.valueOf(sdys.get(i).getProduct().getId()));
		if(tempsmmdy.size()>0){	
		tempsmmdy.get(0).setXsyk1(sdys.get(i).getXsyk1());
		tempsmmdy.get(0).setXsyk2(sdys.get(i).getXsyk2());
		tempsmmdy.get(0).setXsyk3(sdys.get(i).getXsyk3());
		tempsmmdy.get(0).setXsfh1(sdys.get(i).getXsfh1());
		tempsmmdy.get(0).setXsfh2(sdys.get(i).getXsfh2());
		tempsmmdy.get(0).setCycyk1(sdys.get(i).getCycyk1());
		tempsmmdy.get(0).setCycyk2(sdys.get(i).getCycyk2());
		hibernateTemplate.save(tempsmmdy.get(0));
		}
		else
			hibernateTemplate.save(sdys.get(i));
		}
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Canku> getAllCYC() {
		List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=0");	
		return cankus;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Canku> getAllF(){
		List<Canku> cankus = (List<Canku>)hibernateTemplate.find("from Canku where type=6");	
		return cankus;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean adjcfr(int cycid,int fid){
		List<cycrelgck> gckList = hibernateTemplate.find("from cycrelgck as crg where crg.cyc.id = "+cycid+" and crg.gck.id = "+fid);
		if(gckList.size()>0)
			return true;
		else
			return false;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Canku> getCankuById(int id){
		return hibernateTemplate.find("from Canku where id="+id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void savecfr(List<Boolean> cankupriv,int cycId){
		List<cycrelgck> gckList = 
			hibernateTemplate.find("from cycrelgck as crg where crg.cyc.id = '"+cycId+"'");
			
			hibernateTemplate.deleteAll(gckList);
			List<Canku> cankus = this.getAllF();
			List<Products> products =
				(List<Products>)hibernateTemplate.find("from Products");
			Canku cyc = this.getCankuById(cycId).get(0);
			for(int i=0;i<cankupriv.size();i++){
			
				if(cankupriv.get(i)){
					hibernateTemplate.save(new cycrelgck(cyc,cankus.get(i)));
				}
			}					
					
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Chukumx> listOnwayProducts(int org, int aim) {

		List<Chukumx> result = new ArrayList<Chukumx>();

		if ((org != 0) && (aim != 0)) {
			result = (List<Chukumx>) hibernateTemplate
					.find("from Chukumx where Status=0 and chuku.cankuByCankuId.id = "
							+ String.valueOf(org)
							+ " and chuku.cankuByRkId.id ="
							+ String.valueOf(aim));
		} else if ((org != 0) && (aim == 0)) {
			result = (List<Chukumx>) hibernateTemplate
					.find("from Chukumx where Status=0 and chuku.cankuByCankuId.id = "
							+ String.valueOf(org)
							+ " and chuku.cankuByRkId.type!=3 and chuku.cankuByRkId.type!=4 and chuku.cankuByRkId.type!=5");
		} else if ((org == 0) && (aim != 0)) {
			result = (List<Chukumx>) hibernateTemplate
					.find("from Chukumx where Status=0 and chuku.cankuByRkId.id = "
							+ String.valueOf(aim)
							+ " and chuku.cankuByCankuId.type!=3 and chuku.cankuByCankuId.type!=4 and chuku.cankuByCankuId.type!=5 and chuku.cankuByCankuId.type!=6");
		} else {
			result = (List<Chukumx>) hibernateTemplate
					.find("from Chukumx where Status=0 "
							+" and chuku.cankuByCankuId.type!=3 and chuku.cankuByCankuId.type!=4 and chuku.cankuByCankuId.type!=5 and chuku.cankuByCankuId.type!=6 "
							+" and chuku.cankuByRkId.type!=3 and chuku.cankuByRkId.type!=4 and chuku.cankuByRkId.type!=5");
		}
		return result;
	}

}
