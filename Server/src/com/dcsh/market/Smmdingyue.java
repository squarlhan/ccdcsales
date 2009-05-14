package com.dcsh.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "smmdingyue", schema = "dbo", catalog = "test")
public class Smmdingyue implements java.io.Serializable {
	
	private int id;
	private Users user;
	private Canku canku;
	private Products product;
	private byte xsyk1;
	private byte xsyk2;
	private byte xsyk3;
	private byte xsfh1;
	private byte xsfh2;
	private byte cycyk1;
	private byte cycyk2;
	
	
	
	public Smmdingyue() {
		super();
	}
	public Smmdingyue(int id, Users user, Products product, byte xsyk1, byte xsyk2,
			byte xsyk3, byte xsfh1, byte xsfh2, byte cycyk1, byte cycyk2) {
		super();
		this.user = user;
		this.id = id;
		this.product = product;
		this.xsyk1 = xsyk1;
		this.xsyk2 = xsyk2;
		this.xsyk3 = xsyk3;
		this.xsfh1 = xsfh1;
		this.xsfh2 = xsfh2;
		this.cycyk1 = cycyk1;
		this.cycyk2 = cycyk2;
	}
	public Smmdingyue(int id, Users user, Canku canku, Products product, byte xsyk1,
			byte xsyk2, byte xsyk3, byte xsfh1, byte xsfh2, byte cycyk1,
			byte cycyk2) {
		super();
		this.id = id;
		this.user = user;
		this.canku = canku;
		this.product = product;
		this.xsyk1 = xsyk1;
		this.xsyk2 = xsyk2;
		this.xsyk3 = xsyk3;
		this.xsfh1 = xsfh1;
		this.xsfh2 = xsfh2;
		this.cycyk1 = cycyk1;
		this.cycyk2 = cycyk2;
	}
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "userid")
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name = "canku")
	public Canku getCanku() {
		return canku;
	}
	public void setCanku(Canku canku) {
		this.canku = canku;
	}
	@ManyToOne
	@JoinColumn(name = "product")
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	@Column(name = "xsyk1", nullable = false, length = 1)
	public byte getXsyk1() {
		return xsyk1;
	}
	public void setXsyk1(byte xsyk1) {
		this.xsyk1 = xsyk1;
	}
	@Column(name = "xsyk2", nullable = false, length = 1)
	public byte getXsyk2() {
		return xsyk2;
	}
	public void setXsyk2(byte xsyk2) {
		this.xsyk2 = xsyk2;
	}
	@Column(name = "xsyk3", nullable = false, length = 1)
	public byte getXsyk3() {
		return xsyk3;
	}
	public void setXsyk3(byte xsyk3) {
		this.xsyk3 = xsyk3;
	}
	@Column(name = "xsfh1", nullable = false, length = 1)
	public byte getXsfh1() {
		return xsfh1;
	}
	public void setXsfh1(byte xsfh1) {
		this.xsfh1 = xsfh1;
	}
	@Column(name = "xsfh2", nullable = false, length = 1)
	public byte getXsfh2() {
		return xsfh2;
	}
	public void setXsfh2(byte xsfh2) {
		this.xsfh2 = xsfh2;
	}
	@Column(name = "cycyk1", nullable = false, length = 1)
	public byte getCycyk1() {
		return cycyk1;
	}
	public void setCycyk1(byte cycyk1) {
		this.cycyk1 = cycyk1;
	}
	@Column(name = "cycyk2", nullable = false, length = 1)
	public byte getCycyk2() {
		return cycyk2;
	}
	public void setCycyk2(byte cycyk2) {
		this.cycyk2 = cycyk2;
	}
	
	

}
