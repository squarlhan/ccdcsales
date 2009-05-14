package com.dcsh.market;

import java.util.Set;

/**
 * 短信息的实体类
 * @author Woden
 *
 */
public class MyMessages {
	
	Set<Users> users;//收信人
	String content;//短信内容
	byte type;//短信类型
	byte status;//短信状态
	
	public MyMessages() {
	}
	public MyMessages(Set<Users> users, String content, byte type, byte status) {
		this.users = users;
		this.content = content;
		this.type = type;
		this.status = status;
	}
	public MyMessages(Set<Users> users, String content) {
		this.users = users;
		this.content = content;
	}
	
	public String toString(){
		String ustr = "";
		for(Users user:this.getUsers()){
			ustr = ustr + user.getName() + ",";
		}
		return "发给"+ustr+"内容是："+this.getContent()+"!";
	}
	
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
}
