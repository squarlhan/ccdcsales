package com.dcsh.market;

public class CheckInTable {
	private Products products;
	private Chuku chuku;
	private Specifications specifications;
	private String pch;
	private int number;
	private String memo;
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public Specifications getSpecifications() {
		return specifications;
	}
	public void setSpecifications(Specifications specifications) {
		this.specifications = specifications;
	}
	public String getPch() {
		return pch;
	}
	public void setPch(String pch) {
		this.pch = pch;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public CheckInTable(Products products,
			Specifications specifications, String pch, int number, String memo) {
		super();
		this.products = products;
		this.specifications = specifications;
		this.pch = pch;
		this.number = number;
		this.memo = memo;
	}
	
}
