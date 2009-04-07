package com.dcsh.market.priv;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;

public enum ResourceType {
	URL(String.class,"url"),
	PRD(Products.class,"prd"),
	CANKU(Canku.class,"canku");
	
	private final Class clasz;
	private final String prefix;
	
	ResourceType(Class clasz, String prefix){
		this.clasz = clasz;
		this.prefix = prefix;
	}
	public Class getResClass() {
		return clasz;
	}
	public String getPrefix() {
		return prefix;
	}
	
	/*
	 * 从资源描述字符串中构建出一个ResourceType对象
	 * 比如 "url:/image/*" 就会构建出一个URL对象。
	 */
	static public ResourceType buildFromString(String resource){

		for(ResourceType type: ResourceType.values()){
			if(resource.startsWith(type.getPrefix()+":")) return type;
		}
		return null;
	}
}
