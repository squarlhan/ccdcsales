package com.dcsh.market.priv;

import java.util.List;

import com.dcsh.market.Canku;
import com.dcsh.market.Users;

public class CankuPrivManage {
	
	/**
	 * 获取用户对于仓库的权限，此权限包括从用户组继承来的权限
	 * @param users
	 * @return
	 */
	public static List<Canku> getCankuPriv(Users users){
		return null;
		
	}
	
	/**
	 * 获取用户对于URL的权限，此权限包括从用户组继承来的权限
	 * 是否支持统配符。
	 * @param users
	 * @return
	 */
	public static List<String> getURLPriv(Users users){
		return null;
	}
	

}
