package com.dcsh.market.priv;

import com.dcsh.market.UserGroup;
import com.dcsh.market.Users;

public class URLGPriv {

public static final String DESCRIBE = "url";
	
	UserGroup usergroup;  //用户
	String url; //URL，可能包括统配符
	byte type;   //类型
	
	
	
	
	public URLGPriv(UserGroup usergroup, String url) {
		super();
		this.usergroup = usergroup;
		this.url = url;
	}
	public URLGPriv(UserGroup usergroup, String url, byte type) {
		super();
		this.usergroup = usergroup;
		this.url = url;
		this.type = type;
	}
	public UserGroup getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
}
