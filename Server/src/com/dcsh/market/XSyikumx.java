package com.dcsh.market;

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
@Table(name = "XSyikumx", schema = "dbo", catalog = "test")
public class XSyikumx implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3545554516810170223L;
	private int id;
	private Canku canku;//出库的仓库
	private Products product;//出库的产品
	private Specifications specification;//出库的产品规格
	private XSyikuxx xsyikuxx;//属于哪个移库信息表
	private int number;//出库多少袋
	private byte status;//状态是否已出库
	
	
	
	public XSyikumx() {
		super();
	}
	
	
	
	public XSyikumx(int id, Canku canku, Products product,
			Specifications specification, XSyikuxx xsyikuxx, int number,byte status) {
		super();
		this.id = id;
		this.canku = canku;
		this.product = product;
		this.specification = specification;
		this.xsyikuxx = xsyikuxx;
		this.number = number;
		this.status = status;
	}
	
	



	public XSyikumx(Canku canku, Products product,
			Specifications specification, XSyikuxx xsyikuxx, int number,byte status) {
		super();
		this.canku = canku;
		this.product = product;
		this.specification = specification;
		this.xsyikuxx = xsyikuxx;
		this.number = number;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xsyikuxx", nullable = false)
	public XSyikuxx getXsyikuxx() {
		return xsyikuxx;
	}
	public void setXsyikuxx(XSyikuxx xsyikuxx) {
		this.xsyikuxx = xsyikuxx;
	}
	
	@Column(name = "number", nullable = false, length = 4)
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}


	@Column(name = "status", length = 2)
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	

}
