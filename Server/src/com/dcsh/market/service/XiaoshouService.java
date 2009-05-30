package com.dcsh.market.service;


import java.util.Date;
import java.util.List;

import com.dcsh.market.Canku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSKcxx;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSfahuoxx;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.XSyikuxx;


public interface XiaoshouService {

	/**
	 * 返回某产品的库存信息
	 * @param pid 产品的id
	 * @return
	 */
	List<XSKcxx> listStorageByPrd(List<Products> productList, List<Canku> cankuList);

	List<Fahuo> getAllFahuos();
	List<Custom> getAllCustom();
	List<Canku> getAllCankus();
	List<Products> getAllProductList();
	List<Specifications> getAllSpecificationList();
    void doYiku(XSyikuxx xsyikuxx);
    void doXsfahuo(XSfahuoxx xsfahuoxx);
    
    List<Products> getProductNameById(int id);
	List<Specifications> getSpecificationNameById(int id);
	Canku getCangkuById(Integer cangkuId);
	Custom getCustomerById(Integer customerId);
	Users loadmyshr();
    Users loadmynhr();
    
    /**
     * 取得某计划员销售移库和销售发货明细
     * @param zbr
     * @return
     */
    List<XSyikumx> getXSyikumx(Users zbr);
    List<XSfahuomx> getXSfahuomx(Users zbr);
    
    /**
     * 删除某计划员销售移库和销售发货明细
     * @param id
     * @return
     */
    void delXSyikumx(int id);
    void delXSfahuomx(int id);
    
    /**
     * 获得销售总量和明细
     * @param begindate开始日期
     * @param enddate截至日期
     * @return
     */
	List<ReportCmx> listSales(List<Products> products,Date begindate,Date enddate);
	List<Chukumx> listSalesmx(List<Products> products,Date begindate,Date enddate);
}
