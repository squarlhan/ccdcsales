package com.dcsh.market;

// Generated 2009-3-2 20:48:10 by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rkmx generated by hbm2java
 */
@Entity
@Table(name = "Rkmx", schema = "dbo", catalog = "test")
public class Rkmx implements java.io.Serializable {

	private int id;
	private Rkxx rkxx;
	private Products products;
	private Specifications specifications;
	private String pch;
	private int number;
	private byte saleType;
	private byte status;
	private String memo;

	public Rkmx() {
	}

	public Rkmx(Rkxx rkxx, Products products,
			Specifications specifications, String pch, int number,
			byte saleType, byte status, String memo) {
		this.rkxx = rkxx;
		this.products = products;
		this.specifications = specifications;
		this.pch = pch;
		this.number = number;
		this.saleType = saleType;
		this.status = status;
		this.memo = memo;
	}

	public Rkmx(int id, Rkxx rkxx, Products products,
			Specifications specifications, String pch, int number,
			byte saleType, byte status, String memo) {
		this.id = id;
		this.rkxx = rkxx;
		this.products = products;
		this.specifications = specifications;
		this.pch = pch;
		this.number = number;
		this.saleType = saleType;
		this.status = status;
		this.memo = memo;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Rkid", nullable = false)
	public Rkxx getRkxx() {
		return this.rkxx;
	}

	public void setRkxx(Rkxx rkxx) {
		this.rkxx = rkxx;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PrdID", nullable = false)
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SID", nullable = false)
	public Specifications getSpecifications() {
		return this.specifications;
	}

	public void setSpecifications(Specifications specifications) {
		this.specifications = specifications;
	}

	@Column(name = "Pch", nullable = false, length = 9)
	public String getPch() {
		return this.pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	@Column(name = "Number", nullable = false)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "SaleType", nullable = false)
	public byte getSaleType() {
		return this.saleType;
	}

	public void setSaleType(byte saleType) {
		this.saleType = saleType;
	}

	@Column(name = "Status", nullable = false)
	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "memo", length = 100)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
