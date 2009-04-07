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
	 * ����Դ�����ַ����й�����һ��ResourceType����
	 * ���� "url:/image/*" �ͻṹ����һ��URL����
	 */
	static public ResourceType buildFromString(String resource){

		for(ResourceType type: ResourceType.values()){
			if(resource.startsWith(type.getPrefix()+":")) return type;
		}
		return null;
	}
}
