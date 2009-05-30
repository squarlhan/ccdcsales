package com.dcsh.market.service;

import java.util.Date;
import java.util.List;

import com.dcsh.market.Canku;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Custom;
import com.dcsh.market.Fahuo;
import com.dcsh.market.Products;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.Smmdingyue;
import com.dcsh.market.Smmdy;
import com.dcsh.market.Specifications;
import com.dcsh.market.UserGroup;
import com.dcsh.market.UserPriv;
import com.dcsh.market.Users;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.URLGPriv;

/**
 * Test by SVN
 * @author wangkp
 *
 */
public interface AdminService {
	/**
	 * ��Ʒ��ص���ɾ�Ĳ�
	 * @return
	 */
	List<Products> getAllProducts();
	void updateProduct(Products product);
	void delProduct(Products product);
	void addProduct(Products product);
	/**
	 * �����ص���ɾ�Ĳ�
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	void delSpecification(Specifications specification);
	void addSpecification(Specifications specification);
	void updateSpecification(Specifications specification);
	/**
	 * �ֿ���ص���ɾ�Ĳ�
	 * @return
	 */
	List<Canku> getAllCankus();
	void delCanku(Canku canku);
	void addCanku(Canku canku);
	void updateCanku(Canku canku);
	/**
	 * �û���ص���ɾ�Ĳ�
	 * flag==0��ʱ������Ÿı�
	 * @return
	 */
	List<Users> getAllUsers();
	void delUser(Users user);
	void addUser(Users user);
	void updateUser(Users user, int flag);
	/**
	 * ��½��ȡ�û�
	 * @param username �û���
	 * @param Password ����
	 * @return �û����߿�
	 */

	List<CankuPriv> loginAdmin(String username,byte[] Password);

	/**
	 * ����ص���ɾ�Ĳ�
	 * @return
	 */
	List<UserGroup> getAllGroups();
	void delUserGroup(UserGroup usergroup);
	void addUserGroup(UserGroup usergroup);
	void updateUserGroup(UserGroup usergroup);

	/**
	 * ����Ա����ѯ
	 * ������������ϸ��ѯ
	 */
	List<ReportPmx> listStorage(int cankuid,Date date);

    /**
     * ��ѯ���ڳ�Ա
     * @param groupId ��ID
     * @return �Ƿ������ڵĲ�����List
     */
	List<Boolean> getSelectedUserlist(int groupId);
	/**
	 * 
	 * @param isgroupmember �Ƿ������ڵĲ�����List
	 * @param groupId ��ID
	 */
	void saveGroupMember(List<Boolean> isgroupmember,Integer groupId);

	/**
	 * �õ����е��鼰��Ȩ��
	 * @return
	 */
	List<URLGPriv> getgrouppriv();
	/**
	 * �õ����е��û�����Ȩ��
	 * @return
	 */
	List<UserPriv> getuserprivByUserId(int userid); 
	/**
	 * ������Ӧ��Ĺ���ĳ�ֿ��Ȩ��
	 * @param gid ���id
	 * @param res ��õ���Դ���
	 */
	void setgrouppriv(int gid, int res);
    /**
     * ���������������ϸ
     * @param begindate��ʼ����
     * @param enddate��������
     * @return
     */
	List<ReportCmx> listSales(Date begindate,Date enddate);
	List<Chukumx> listSalesmx(Date begindate,Date enddate);
	/**
	 * ���������������ϸ
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<ReportCmx> listDestroy(Date begindate,Date enddate);
	List<Chukumx> listDestroymx(Date begindate,Date enddate);
	/**
	 * ��������������ϸ
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<ReportCmx> listLoss(Date begindate,Date enddate);
	List<Chukumx> listLossmx(Date begindate,Date enddate);
	/**
	 * �ͻ���ص���ɾ�Ĳ�
	 * @return
	 */
	List<Custom> getAllCustoms();
	void delCustom(Custom custom);
	void addCustom(Custom custom);
	void updateCustom(Custom custom);
	/**
	 * ������λ��ص���ɾ�Ĳ�
	 * @return
	 */
	List<Fahuo> getAllFahuos();
	void updateFahuo(Fahuo fahuo);
	void delFahuo(Fahuo fahuo);
	void addFahuo(Fahuo fahuo);
	/**
	 * �洢�û���Ȩ��
	 * @param cankupriv �ֿ�Ȩ��
	 * @param prdpriv   ��ƷȨ��
	 * @param userId    �û�ID
	 * @return
	 */
	void saveUserpriv(List<Boolean> cankupriv,List<Boolean> prdpriv,int userId);
	/**
	 * ��ȡ���Ŷ����б�
	 * @param user ������û�
	 * @return
	 */
	List<Smmdy> loadSmmdybyuser(Users user);
	/**
	 * ���¶��Ŷ�����Ϣ
	 * @param sdys �������Ϣ
	 */
	void updateSmmdy(List<Smmdingyue> sdys);

	/**
	 * ���˴��͹��������еõ����еĴ��˴�
	 * @return
	 */
	List<Canku> getAllCYC();
	/**
	 * ���˴��͹��������еõ����еĹ���
	 * @return
	 */
	List<Canku> getAllF();
	/**
	 * �жϴ��˴��͹�����Ӧ��ϵ
	 * @param cycid
	 * @param fid
	 * @return
	 */
	boolean adjcfr(int cycid,int fid);
	/**
	 * ���洢�˴��͹�����Ӧ��ϵ
	 * @param cankupriv
	 * @param cycId
	 */
	void savecfr(List<Boolean> cankupriv,int cycId);
	/**
	 * ͨ��id��òֿ���Ϣ
	 * @param id
	 * @return
	 */
	List<Canku> getCankuById(int id);

	/**
	 * �г���;��Ʒ��Ϣ
	 * @param org Դ�ֿ�id
	 * @param aim Ŀ���id
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int org, int aim);

}
