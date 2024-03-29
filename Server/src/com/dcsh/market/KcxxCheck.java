package com.dcsh.market;

public class KcxxCheck extends Kcxx {
	private String saleTypeName;
	private String statusName;
	private String cankuName;
	
	public KcxxCheck() {
		super();
	}
	
	public KcxxCheck(String saleTypeName, Kcxx kcxx) {
		super(kcxx.getId(),kcxx.getSpecifications(),kcxx.getProducts(),
				kcxx.getNumber(),kcxx.getSaleType(),kcxx.getStatus(),kcxx.getMemo());
		this.saleTypeName = saleTypeName;
	}
	public KcxxCheck(String saleTypeName,String cankuName, Kcxx kcxx) {
		super(kcxx.getId(),kcxx.getSpecifications(),kcxx.getProducts(),
				kcxx.getNumber(),kcxx.getSaleType(),kcxx.getStatus(),kcxx.getMemo());
		this.saleTypeName = saleTypeName;
		this.cankuName = cankuName;
	}

	public KcxxCheck(String saleTypeName, String statusName) {
		super();
		this.saleTypeName = saleTypeName;
		this.statusName = statusName;
	}

	public String getSaleTypeName() {
		return saleTypeName;
	}

	public void setSaleTypeName(String saleTypeName) {
		this.saleTypeName = saleTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setCankuName(String cankuName) {
		this.cankuName = cankuName;
	}

	public String getCankuName() {
		return cankuName;
	}
	
}
