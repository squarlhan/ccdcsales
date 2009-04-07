package com.dcsh.market;

public class CheckOutTable {
	private Products products;
	private Chuku chuku;
	private Specifications specifications;
	private String pch;
	private int number;
	private Canku rkid;//Èë¿âµÄID
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public Chuku getChuku() {
		return chuku;
	}
	public void setChuku(Chuku chuku) {
		this.chuku = chuku;
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
	public Canku getRkid() {
		return rkid;
	}
	public void setRkid(Canku rkid) {
		this.rkid = rkid;
	}
	public CheckOutTable(Products products, Chuku chuku,
			Specifications specifications, String pch, int number, Canku rkid) {
		super();
		this.products = products;
		this.chuku = chuku;
		this.specifications = specifications;
		this.pch = pch;
		this.number = number;
		this.rkid = rkid;
	}
	
}
