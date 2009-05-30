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
	 * 产品相关的增删改查
	 * @return
	 */
	List<Products> getAllProducts();
	void updateProduct(Products product);
	void delProduct(Products product);
	void addProduct(Products product);
	/**
	 * 规格相关的增删改查
	 * @return
	 */
	List<Specifications> getAllSpecifications();
	void delSpecification(Specifications specification);
	void addSpecification(Specifications specification);
	void updateSpecification(Specifications specification);
	/**
	 * 仓库相关的增删改查
	 * @return
	 */
	List<Canku> getAllCankus();
	void delCanku(Canku canku);
	void addCanku(Canku canku);
	void updateCanku(Canku canku);
	/**
	 * 用户相关的增删改查
	 * flag==0的时候，密码才改变
	 * @return
	 */
	List<Users> getAllUsers();
	void delUser(Users user);
	void addUser(Users user);
	void updateUser(Users user, int flag);
	/**
	 * 登陆获取用户
	 * @param username 用户名
	 * @param Password 密码
	 * @return 用户或者空
	 */

	List<CankuPriv> loginAdmin(String username,byte[] Password);

	/**
	 * 组相关的增删改查
	 * @return
	 */
	List<UserGroup> getAllGroups();
	void delUserGroup(UserGroup usergroup);
	void addUserGroup(UserGroup usergroup);
	void updateUserGroup(UserGroup usergroup);

	/**
	 * 管理员库存查询
	 * 销售总量和明细查询
	 */
	List<ReportPmx> listStorage(int cankuid,Date date);

    /**
     * 查询组内成员
     * @param groupId 组ID
     * @return 是否在组内的布尔型List
     */
	List<Boolean> getSelectedUserlist(int groupId);
	/**
	 * 
	 * @param isgroupmember 是否在组内的布尔型List
	 * @param groupId 组ID
	 */
	void saveGroupMember(List<Boolean> isgroupmember,Integer groupId);

	/**
	 * 得到所有的组及其权限
	 * @return
	 */
	List<URLGPriv> getgrouppriv();
	/**
	 * 得到所有的用户及其权限
	 * @return
	 */
	List<UserPriv> getuserprivByUserId(int userid); 
	/**
	 * 设置相应组的管理某仓库的权限
	 * @param gid 组的id
	 * @param res 获得的资源标号
	 */
	void setgrouppriv(int gid, int res);
    /**
     * 获得销售总量和明细
     * @param begindate开始日期
     * @param enddate截至日期
     * @return
     */
	List<ReportCmx> listSales(Date begindate,Date enddate);
	List<Chukumx> listSalesmx(Date begindate,Date enddate);
	/**
	 * 获得销毁总量和明细
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<ReportCmx> listDestroy(Date begindate,Date enddate);
	List<Chukumx> listDestroymx(Date begindate,Date enddate);
	/**
	 * 获得损耗总量和明细
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<ReportCmx> listLoss(Date begindate,Date enddate);
	List<Chukumx> listLossmx(Date begindate,Date enddate);
	/**
	 * 客户相关的增删改查
	 * @return
	 */
	List<Custom> getAllCustoms();
	void delCustom(Custom custom);
	void addCustom(Custom custom);
	void updateCustom(Custom custom);
	/**
	 * 发货单位相关的增删改查
	 * @return
	 */
	List<Fahuo> getAllFahuos();
	void updateFahuo(Fahuo fahuo);
	void delFahuo(Fahuo fahuo);
	void addFahuo(Fahuo fahuo);
	/**
	 * 存储用户的权限
	 * @param cankupriv 仓库权限
	 * @param prdpriv   产品权限
	 * @param userId    用户ID
	 * @return
	 */
	void saveUserpriv(List<Boolean> cankupriv,List<Boolean> prdpriv,int userId);
	/**
	 * 获取短信订阅列表
	 * @param user 传入的用户
	 * @return
	 */
	List<Smmdy> loadSmmdybyuser(Users user);
	/**
	 * 更新短信订阅信息
	 * @param sdys 传入的信息
	 */
	void updateSmmdy(List<Smmdingyue> sdys);

	/**
	 * 储运处和工厂关联中得到所有的储运处
	 * @return
	 */
	List<Canku> getAllCYC();
	/**
	 * 储运处和工厂关联中得到所有的工厂
	 * @return
	 */
	List<Canku> getAllF();
	/**
	 * 判断储运处和工厂对应关系
	 * @param cycid
	 * @param fid
	 * @return
	 */
	boolean adjcfr(int cycid,int fid);
	/**
	 * 保存储运处和工厂对应关系
	 * @param cankupriv
	 * @param cycId
	 */
	void savecfr(List<Boolean> cankupriv,int cycId);
	/**
	 * 通过id获得仓库信息
	 * @param id
	 * @return
	 */
	List<Canku> getCankuById(int id);

	/**
	 * 列出在途产品信息
	 * @param org 源仓库id
	 * @param aim 目标库id
	 * @return
	 */
	List<Chukumx> listOnwayProducts(int org, int aim);

}
