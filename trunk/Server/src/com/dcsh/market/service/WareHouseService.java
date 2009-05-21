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
import com.dcsh.market.Reportxx;
import com.dcsh.market.priv.CankuPriv;

import java.util.Date;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface WareHouseService {
	/**
	 * 列出库存信息
	 * @param ck 库存信息，现有的库存明细
	 */
	List<Kcxx> listWarehouse(int id);
	/**
     * 查报表信息
     * @param ckid 要查询的仓库id
     * @param date  要查询的日期
     */  
	List<ReportPmx>listWarehouseOther(int ckid,Date date);
	/**
	 * 登陆获取用户
	 * @param username 用户名
	 * @param Password 密码
	 * @return 用户或者空
	 */
	List<CankuPriv> loginWarehouse(String username,byte[] Password);
	/**
	 * 获得未检查的入库单列表
	 * @return
	 */
	List<Rkmx> listUnCheckProducts(int id);
	/**
	 * 检查产品入库
	 * @param rkmxes 未检查的产品
	 */
	void doCheckProducts(List<Rkmx> rkmxes);
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
	/**
	 * 获得当前仓库的按要求产品明细
	 * @return
	 */
	List<KcxxCheck> getCheckedProducts(Canku canku,XSfahuomx xsfhmx);
	List<KcxxCheck> getCheckedProducts(Canku canku,XSyikumx xsykmx);
	/**
	 * 获得当前仓库的不合格产品明细
	 * @return
	 */
	List<Kcxx> getUnqualifiedProducts(Canku canku);
	/**
	 * 获得内销产品明细
	 * @param canku
	 * @return
	 */
	List<KcxxCheck> getNxProducts(Canku canku);
	/**
	 * 获取所有的规格
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	List<Canku> getAllCankus();
	List<Canku> getCangkuByType(int type);
	
	/**
	 * 入库单提交到数据库
	 * @param rkxx 保存入库信息
	 * @return
	 */
	void doEntryWareHouse(Rkxx rkxx); 
	/**
	 * 出库单提交到数据库
	 * @return
	 */
	void doDeliveryWareHouse(Chuku chuku);
	

	/**
	 * 产成品进销存（发运）日报
	 * 产品列表
	 * @param canku 仓库id
	 * @param date 所查日期
	 */
	List<ReportPmx> getDayReportPmx(Canku canku,Date date);
	List<ReportCmx> getDayReportCmx(Canku canku,Date date);
	
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

    /**
     * 获得该库所有的产品信息
     * @param 查询的仓库
     * @return
     */
	List<Kcxx> getAllProducts(Canku canku);

    /**
     * 获得所有的客户信息
     * @return
     */
    List<Custom> getAllCustom();

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
    void resetXsykxxStatus(int index, int num);
    void resetXsfhxxStatus(int index, int num);

    /**
     * 获得仓库信息
     * @param cangkuId 仓库ID
     * @return 仓库对象
     */
    Canku getCangkuById(Integer cangkuId);
    List<KcxxCheck> getallCheckedProducts(Canku canku);
    List<Chukumx> listUnentryProducts(int cankuid);
    
    /**
	 * 确认到货产品入库
	 * @param rkxx 生成的入库明细
	 */			
	void doentryChuYunchu(List<Chukumx> chukumxes,Rkxx rkxx);
	
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
	 * 删除待检产品
	 * @param id 入库明细的id
	 */
	void deleteUncheckProduct(int id);
	/**
	 * 列出从这个仓库发出的在途货物
	 * @param cankuid
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int cankuid);
	/**
	 * 查询当日工厂库的库存量
	 * @param canku
	 * @return
	 */
	List<ReportPmx> getFPmx(Canku canku);
	/**
	 * 查询工厂仓库的Report
	 * @param ckid
	 * @param date
	 * @return
	 */
	List<ReportPmx>listFReport(int ckid,Date date);
}
