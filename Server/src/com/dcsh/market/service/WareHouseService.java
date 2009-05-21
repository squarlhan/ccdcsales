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
	 * �г������Ϣ
	 * @param ck �����Ϣ�����еĿ����ϸ
	 */
	List<Kcxx> listWarehouse(int id);
	/**
     * �鱨����Ϣ
     * @param ckid Ҫ��ѯ�Ĳֿ�id
     * @param date  Ҫ��ѯ������
     */  
	List<ReportPmx>listWarehouseOther(int ckid,Date date);
	/**
	 * ��½��ȡ�û�
	 * @param username �û���
	 * @param Password ����
	 * @return �û����߿�
	 */
	List<CankuPriv> loginWarehouse(String username,byte[] Password);
	/**
	 * ���δ������ⵥ�б�
	 * @return
	 */
	List<Rkmx> listUnCheckProducts(int id);
	/**
	 * ����Ʒ���
	 * @param rkmxes δ���Ĳ�Ʒ
	 */
	void doCheckProducts(List<Rkmx> rkmxes);
	/**
	 * ������еĲ�Ʒ�͹��ֿ�
	 * @return
	 */
	List<Products> getAllProducts();
	/**
	 * ��õ�ǰ�ֿ�Ŀ��ò�Ʒ��ϸ
	 * @return
	 */
	List<KcxxCheck> getValiProducts(Canku canku);
	/**
	 * ��õ�ǰ�ֿ�İ�Ҫ���Ʒ��ϸ
	 * @return
	 */
	List<KcxxCheck> getCheckedProducts(Canku canku,XSfahuomx xsfhmx);
	List<KcxxCheck> getCheckedProducts(Canku canku,XSyikumx xsykmx);
	/**
	 * ��õ�ǰ�ֿ�Ĳ��ϸ��Ʒ��ϸ
	 * @return
	 */
	List<Kcxx> getUnqualifiedProducts(Canku canku);
	/**
	 * ���������Ʒ��ϸ
	 * @param canku
	 * @return
	 */
	List<KcxxCheck> getNxProducts(Canku canku);
	/**
	 * ��ȡ���еĹ��
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	List<Canku> getAllCankus();
	List<Canku> getCangkuByType(int type);
	
	/**
	 * ��ⵥ�ύ�����ݿ�
	 * @param rkxx ���������Ϣ
	 * @return
	 */
	void doEntryWareHouse(Rkxx rkxx); 
	/**
	 * ���ⵥ�ύ�����ݿ�
	 * @return
	 */
	void doDeliveryWareHouse(Chuku chuku);
	

	/**
	 * ����Ʒ�����棨���ˣ��ձ�
	 * ��Ʒ�б�
	 * @param canku �ֿ�id
	 * @param date ��������
	 */
	List<ReportPmx> getDayReportPmx(Canku canku,Date date);
	List<ReportCmx> getDayReportCmx(Canku canku,Date date);
	
/**
 * �����ձ���Ϣ�����ݿ���
 * @param reportcmxlist �ֿ���ϸ�б�
 * @param reportpmxlist ��Ʒ��ϸ�б�
 * @param users         �û�
 * @param bno           �ձ����
 * @param bno           �ձ�����
 * @param cankuid       �ֿ�id
 */
    void saveDayReportxx(List<ReportCmx> reportcmxlist,List<ReportPmx> reportpmxlist,
    		Users users, String bno,Date today,int cankuid);
    /**
     * ��������Ϣ
     * @param canku Ҫ��ѯ�Ĳֿ���Ϣ
     * @param date  Ҫ��ѯ������
     */   
    List<CheckOutTable> getCheckOutTable(Canku canku,Date date);
    /**
     * ��������Ϣ
     * @param canku Ҫ��ѯ�Ĳֿ���Ϣ
     * @param date  Ҫ��ѯ������
     */  
    List<CheckInTable> getCheckInTable(Canku canku,Date date);

    /**
     * ��øÿ����еĲ�Ʒ��Ϣ
     * @param ��ѯ�Ĳֿ�
     * @return
     */
	List<Kcxx> getAllProducts(Canku canku);

    /**
     * ������еĿͻ���Ϣ
     * @return
     */
    List<Custom> getAllCustom();

    /**
     * ȡ��ĳ�ֿ������ƿ�����۷�����ϸ
     * @param canku
     * @return
     */
    List<XSyikumx> getXSyikumx(Canku canku);
    List<XSfahuomx> getXSfahuomx(Canku canku);
    /**
     * �����ƿ���Ϣ�ͷ�����Ϣ״̬Ϊ����� status=1
     * @param index
     */
    void resetXsykxxStatus(int index, int num);
    void resetXsfhxxStatus(int index, int num);

    /**
     * ��òֿ���Ϣ
     * @param cangkuId �ֿ�ID
     * @return �ֿ����
     */
    Canku getCangkuById(Integer cangkuId);
    List<KcxxCheck> getallCheckedProducts(Canku canku);
    List<Chukumx> listUnentryProducts(int cankuid);
    
    /**
	 * ȷ�ϵ�����Ʒ���
	 * @param rkxx ���ɵ������ϸ
	 */			
	void doentryChuYunchu(List<Chukumx> chukumxes,Rkxx rkxx);
	
	List<Products> getProductNameById(int id);
	List<Specifications> getSpecificationNameById(int id);
	Custom getCustomerById(Integer customerId);
	
	/**
	 * ��ѯ�ض����ڵ��ձ���Ϣ
	 * @param mydate ����
	 */
	List<ReportPmx> searchDayReportPmx(Date mydate,Canku canku);
	/**
	 * ��ѯ�ض����ڵ��ձ���Ϣ
	 * @param mydate ����
	 */
	List<ReportCmx> searchDayReportCmx(Date mydate,Canku canku);
	/**
	 * ɾ�������Ʒ
	 * @param id �����ϸ��id
	 */
	void deleteUncheckProduct(int id);
	/**
	 * �г�������ֿⷢ������;����
	 * @param cankuid
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int cankuid);
	/**
	 * ��ѯ���չ�����Ŀ����
	 * @param canku
	 * @return
	 */
	List<ReportPmx> getFPmx(Canku canku);
	/**
	 * ��ѯ�����ֿ��Report
	 * @param ckid
	 * @param date
	 * @return
	 */
	List<ReportPmx>listFReport(int ckid,Date date);
}
