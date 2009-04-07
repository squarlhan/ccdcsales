package com.dcsh.market;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XSfahuomx", schema = "dbo", catalog = "test")
public class XSfahuomx implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6335107423694345972L;
	
	private int id;//id
	private XSfahuoxx xsfahuoxx;//所属的发货信息单
	private Canku canku;//从该仓库出库
	private Products product;//出货某产品
	private Specifications specification;//规格
	private int number;//数量
	private BigDecimal price;//单价
	private byte status;//状态
	
	
	
	
	public XSfahuomx() {
		super();
	}
	public XSfahuomx(XSfahuoxx xsfahuoxx, Canku canku, Products product,
			Specifications specification, int number, BigDecimal price,
			byte status) {
		super();
		this.xsfahuoxx = xsfahuoxx;
		this.canku = canku;
		this.product = product;
		this.specification = specification;
		this.number = number;
		this.price = price;
		this.status = status;
	}
	public XSfahuomx(int id, XSfahuoxx xsfahuoxx, Canku canku,
			Products product, Specifications specification, int number,
			BigDecimal price, byte status) {
		super();
		this.id = id;
		this.xsfahuoxx = xsfahuoxx;
		this.canku = canku;
		this.product = product;
		this.specification = specification;
		this.number = number;
		this.price = price;
		this.status = status;
	}
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xsfahuoxx", nullable = false)
	public XSfahuoxx getXsfahuoxx() {
		return xsfahuoxx;
	}
	public void setXsfahuoxx(XSfahuoxx xsfahuoxx) {
		this.xsfahuoxx = xsfahuoxx;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "canku", nullable = false)
	public Canku getCanku() {
		return canku;
	}
	public void setCanku(Canku canku) {
		this.canku = canku;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product", nullable = false)
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specification", nullable = false)
	public Specifications getSpecification() {
		return specification;
	}
	public void setSpecification(Specifications specification) {
		this.specification = specification;
	}
	
	@Column(name = "number", nullable = false, length = 4)
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Column(name="price",nullable = false)
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "status", length = 2)
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	

}
