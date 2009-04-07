package com.dcsh.market;

import java.math.BigDecimal;

public class XSKcxx {
	
	private Canku canku;
	private Specifications specification;
	private int number;
	private BigDecimal weight;
	private Products product;
	private String saletype;
	
	
	
	public XSKcxx() {
	}
	
	public XSKcxx(Canku canku, Specifications specification, int number,
			BigDecimal weight, Products product,String saletype) {
		super();
		this.canku = canku;
		this.specification = specification;
		this.number = number;
		this.weight = weight;
		this.product = product;
		this.saletype = saletype;
	}

	public Canku getCanku() {
		return canku;
	}
	public void setCanku(Canku canku) {
		this.canku = canku;
	}
	public Specifications getSpecification() {
		return specification;
	}
	public void setSpecification(Specifications specification) {
		this.specification = specification;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}

	public String getSaletype() {
		return saletype;
	}

	public void setSaletype(String saletype) {
		this.saletype = saletype;
	}
	
	

}
