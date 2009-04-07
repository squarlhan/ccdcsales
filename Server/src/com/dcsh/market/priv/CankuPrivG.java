package com.dcsh.market.priv;

import com.dcsh.market.Canku;
import com.dcsh.market.UserGroup;

public class CankuPrivG {
	
	private UserGroup group;
	private Canku canku;
	private byte type;
	
	
	
	public CankuPrivG(UserGroup group, Canku canku) {
		super();
		this.group = group;
		this.canku = canku;
	}
	public CankuPrivG(UserGroup group, Canku canku, byte type) {
		super();
		this.group = group;
		this.canku = canku;
		this.type = type;
	}
	public UserGroup getGroup() {
		return group;
	}
	public void setGroup(UserGroup group) {
		this.group = group;
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
