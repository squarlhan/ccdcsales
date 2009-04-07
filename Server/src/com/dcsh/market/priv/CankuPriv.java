package com.dcsh.market.priv;

import com.dcsh.market.Canku;
import com.dcsh.market.Users;

/**
 * 
 * @author wangkp
 *
 */
public class CankuPriv {
	public static final String DESCRIBE = "canku";
	
	Users user;  //用户
	Canku canku; //仓库
	byte type;   //类型
	
	
	
	public CankuPriv(Users user, Canku canku, byte type) {
		super();
		this.user = user;
		this.canku = canku;
		this.type = type;
	}
	public CankuPriv(Users user, Canku canku) {
		super();
		this.user = user;
		this.canku = canku;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Canku getCanku() {
		return canku;
	}
	public void setCanku(Canku canku) {
		this.canku = canku;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
}
