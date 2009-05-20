package com.dcsh.market.service;

import java.util.Date;
import java.util.List;

import com.dcsh.market.Canku;
import com.dcsh.market.CheckInTable;
import com.dcsh.market.CheckOutTable;
import com.dcsh.market.Chuku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.Products;
import com.dcsh.market.Reportxx;
import com.dcsh.market.Rkmx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Specifications;
import com.dcsh.market.Users;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSyikumx;

import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.priv.CankuPriv;


/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */


public interface ZhongZhuanKuService {
	/**
	 * 登陆获取用户
	 * @param username 用户名
	 * @param Password 密码
	 * @return 用户或者空
	 */
	List<CankuPriv> loginZhongZhuanKu(String username,byte[] Password);
	
    /**
     * 得到所有发给该仓库的在途产品
     * @param ckid该仓库的id
     * @return
     */
	List<Chukumx> listUnCheckProducts(int ckid);
	/**
	 * 确认到货产品入库
	 * @param rkxx 生成的入库明细
	 */			
	void doentryZhongzhuanku(List<Chukumx> chukumxes,Rkxx rkxx);
	/**
	 * 产成品进销存（发运）日报
	 * 产品列表
	 * @param canku 仓库id
	 * @param date 所查日期
	 */
	List<ReportPmx> getDayReportPmx(Canku canku,Date date);

	List<ReportCmx> getDayReportCmx(Canku canku,Date date);
	

	
	/**
	 * 获得所有的产品和规格仓库
	 * @return
	 */
	List<Products> getAllProducts();
	/**
	 * 获得当前仓库的可用产品明细
	 * @return
	 */
	List<KcxxCheck> getValiProducts(Canku canku);
	List<KcxxCheck> geProductsBySaletype(Canku canku,int type); 
	/**
	 * 获取所有的规格
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	List<Canku> getAllCankus();

	/**
	 * 出库单提交到数据库
	 * @return
	 */
	void doDeliveryWareHouse(Chuku chuku);

    /**
     * 查出库表信息
     * @param canku 要查询的仓库信息
     * @param date  要查询的日期
     */   
	/**
	 * 保存日报信息到数据库中
	 * @param reportcmxlist 仓库明细列表
	 * @param reportpmxlist 产品明细列表
	 * @param users         用户
	 * @param bno           日报编号
	 * @param bno           日报日期
	 * @param cankuid       仓库id
	 */
	    void saveDayReportxx(List<ReportCmx> reportcmxlist,List<ReportPmx> reportpmxlist,
	    		Users users, String bno,Date today,int cankuid);
	    /**
	     * 查出库表信息
	     * @param canku 要查询的仓库信息
	     * @param date  要查询的日期
	     */   
    List<CheckOutTable> getCheckOutTable(Canku canku,Date date);
    /**
     * 查入库表信息
     * @param canku 要查询的仓库信息
     * @param date  要查询的日期
     */  
    List<CheckInTable> getCheckInTable(Canku canku,Date date);

    List<Kcxx> listZhongZhuanKu(int ckid);
    /**
     * 查库存信息
     * @param ckid 要查询的仓库id
     */  
    List<Kcxx> listZhongZhuanKuAll(int ckid);
    /**
     * 查报表信息
     * @param ckid 要查询的仓库id
     * @param date  要查询的日期
     */  
    List<ReportPmx> listZhongZhuanKuOther(int ckid,Date date);
    /**
     * 获得所有的客户信息
     * @return
     */
    List<Custom> getAllCustom();
    /**
     * 获得该库所有的产品信息
     * @param 查询的仓库
     * @return
     */
    List<Kcxx> getAllProducts(Canku canku);
    /**
     * 获得仓库信息
     * @param cangkuId 仓库ID
     * @return 仓库对象
     */
    Canku getCangkuById(Integer cangkuId);
    /**
     * 取得某仓库销售移库和销售发货明细
     * @param canku
     * @return
     */
    List<XSyikumx> getXSyikumx(Canku canku);
    List<XSfahuomx> getXSfahuomx(Canku canku);
    /**
     * 更改移库信息和发货信息状态为已完成 status=1
     * @param index
     */
    void resetXsykxxStatus(int index);
    void resetXsfhxxStatus(int index);
    /**
	 * 获得当前仓库的按要求产品明细
	 * @return
	 */
	List<KcxxCheck> getCheckedProducts(Canku canku,XSfahuomx xsfhmx);
	List<KcxxCheck> getCheckedProducts(Canku canku,XSyikumx xsykmx);
	List<Products> getProductNameById(int id);
	List<Specifications> getSpecificationNameById(int id);
	Custom getCustomerById(Integer customerId);
	/**
	 * 查询特定日期的日报信息
	 * @param mydate 日期
	 */
	List<ReportPmx> searchDayReportPmx(Date mydate,Canku canku);
	/**
	 * 查询特定日期的日报信息
	 * @param mydate 日期
	 */
	List<ReportCmx> searchDayReportCmx(Date mydate,Canku canku);
	/**
	 * 列出从这个仓库发出的在途货物
	 * @param cankuid
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int cankuid);
}
