package com.dcsh.market;

public class SalesPrintInfo {
	private String deli_canku;
	private String product;
	private String specification;
	private String sumweight;
	private String deli_num;
	private String price;
	
	public SalesPrintInfo(String deli_canku, String product,
			String specification, String sumweight, String deli_num,
			String price) {
		super();
		this.deli_canku = deli_canku;
		this.product = product;
		this.specification = specification;
		this.sumweight = sumweight;
		this.deli_num = deli_num;
		this.price = price;
	}
	public String getDeli_canku() {
		return deli_canku;
	}
	public void setDeli_canku(String deli_canku) {
		this.deli_canku = deli_canku;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getSumweight() {
		return sumweight;
	}
	public void setSumweight(String sumweight) {
		this.sumweight = sumweight;
	}
	public String getDeli_num() {
		return deli_num;
	}
	public void setDeli_num(String deli_num) {
		this.deli_num = deli_num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
