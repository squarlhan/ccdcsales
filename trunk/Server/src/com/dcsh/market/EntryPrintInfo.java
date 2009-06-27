package com.dcsh.market;

public class EntryPrintInfo {
	
	private String productsName;
	private String specificationName;
	private int number;
	private String pchName;
	private String memo;
	private String sum;
	private String packType;
	private String fahuocanku;
	public EntryPrintInfo(String productsName, String specificationName,String packType,
			 int number, String sum,String pchName, String memo) {
		super();
		this.productsName = productsName;
		this.specificationName = specificationName;
		this.packType = packType;
		this.number = number;
		this.sum = sum;
		this.pchName = pchName;
		this.memo = memo;
	}
	public EntryPrintInfo(String productsName, String specificationName,String packType,
			 int number, String sum,String pchName, String memo,String fahuocanku) {
		super();
		this.productsName = productsName;
		this.specificationName = specificationName;
		this.packType = packType;
		this.number = number;
		this.sum = sum;
		this.pchName = pchName;
		this.memo = memo;
		this.fahuocanku = fahuocanku;
	}
	public String getProductsName() {
		return productsName;
	}
	public void setProductsName(String productsName) {
		this.productsName = productsName;
	}
	public String getSpecificationName() {
		return specificationName;
	}
	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPchName() {
		return pchName;
	}
	public void setPchName(String pchName) {
		this.pchName = pchName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}
	public String getPackType() {
		return packType;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getSum() {
		return sum;
	}
	public String getFahuocanku() {
		return fahuocanku;
	}
	public void setFahuocanku(String fahuocanku) {
		this.fahuocanku = fahuocanku;
	}
	
}
