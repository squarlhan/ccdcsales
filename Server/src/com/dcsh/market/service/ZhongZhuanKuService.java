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
	 * ��½��ȡ�û�
	 * @param username �û���
	 * @param Password ����
	 * @return �û����߿�
	 */
	List<CankuPriv> loginZhongZhuanKu(String username,byte[] Password);
	
    /**
     * �õ����з����òֿ����;��Ʒ
     * @param ckid�òֿ��id
     * @return
     */
	List<Chukumx> listUnCheckProducts(int ckid);
	/**
	 * ȷ�ϵ�����Ʒ���
	 * @param rkxx ���ɵ������ϸ
	 */			
	void doentryZhongzhuanku(List<Chukumx> chukumxes,Rkxx rkxx);
	/**
	 * ����Ʒ�����棨���ˣ��ձ�
	 * ��Ʒ�б�
	 * @param canku �ֿ�id
	 * @param date ��������
	 */
	List<ReportPmx> getDayReportPmx(Canku canku,Date date);

	List<ReportCmx> getDayReportCmx(Canku canku,Date date);
	

	
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
	List<KcxxCheck> geProductsBySaletype(Canku canku,int type); 
	/**
	 * ��ȡ���еĹ��
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	List<Canku> getAllCankus();

	/**
	 * ���ⵥ�ύ�����ݿ�
	 * @return
	 */
	void doDeliveryWareHouse(Chuku chuku);

    /**
     * ��������Ϣ
     * @param canku Ҫ��ѯ�Ĳֿ���Ϣ
     * @param date  Ҫ��ѯ������
     */   
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

    List<Kcxx> listZhongZhuanKu(int ckid);
    /**
     * ������Ϣ
     * @param ckid Ҫ��ѯ�Ĳֿ�id
     */  
    List<Kcxx> listZhongZhuanKuAll(int ckid);
    /**
     * �鱨����Ϣ
     * @param ckid Ҫ��ѯ�Ĳֿ�id
     * @param date  Ҫ��ѯ������
     */  
    List<ReportPmx> listZhongZhuanKuOther(int ckid,Date date);
    /**
     * ������еĿͻ���Ϣ
     * @return
     */
    List<Custom> getAllCustom();
    /**
     * ��øÿ����еĲ�Ʒ��Ϣ
     * @param ��ѯ�Ĳֿ�
     * @return
     */
    List<Kcxx> getAllProducts(Canku canku);
    /**
     * ��òֿ���Ϣ
     * @param cangkuId �ֿ�ID
     * @return �ֿ����
     */
    Canku getCangkuById(Integer cangkuId);
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
    void resetXsykxxStatus(int index);
    void resetXsfhxxStatus(int index);
    /**
	 * ��õ�ǰ�ֿ�İ�Ҫ���Ʒ��ϸ
	 * @return
	 */
	List<KcxxCheck> getCheckedProducts(Canku canku,XSfahuomx xsfhmx);
	List<KcxxCheck> getCheckedProducts(Canku canku,XSyikumx xsykmx);
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
	 * �г�������ֿⷢ������;����
	 * @param cankuid
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int cankuid);
}
