package com.dcsh.market;

// Generated 2009-3-2 20:48:10 by Hibernate Tools 3.2.2.GA

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

/**
 * Specifications generated by hbm2java
 */
@Entity
@AccessType("field")
@Table(name = "Specifications", schema = "dbo", catalog = "test")
public class Specifications implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	
	@Column(name = "Weight", nullable = false, precision = 7)
	private BigDecimal weight;

	@Column(name="PackType", nullable = false, length = 50)
	private String packType;
	
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specifications")
//	private Set<Kcxx> kcxxes = new HashSet<Kcxx>(0);
//	
//	private Set<Rkmx> rkmxes = new HashSet<Rkmx>(0);
//	private Set<Chukumx> chukumxes = new HashSet<Chukumx>(0);
//	private Set<Products> products = new HashSet<Products>(0);

	public Specifications() {
	}

	public Specifications(int id, String name, BigDecimal weight, String packType) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.packType = packType;
	}

	public Specifications(String name, BigDecimal weight) {
		this.name = name;
		this.weight = weight;
	}

//	public Specifications(int id, String name, BigDecimal weight,
//			Set<Kcxx> kcxxes, Set<Rkmx> rkmxes, Set<Chukumx> chukumxes) {
//		this.id = id;
//		this.name = name;
//		this.weight = weight;
//		this.kcxxes = kcxxes;
//		this.rkmxes = rkmxes;
//		this.chukumxes = chukumxes;
//	}
//	
//	
//
//	public Specifications(String name, BigDecimal weight, String packType,
//			Set<Kcxx> kcxxes, Set<Rkmx> rkmxes, Set<Chukumx> chukumxes,
//			Set<Products> products) {
//		super();
//		this.name = name;
//		this.weight = weight;
//		this.packType = packType;
//		this.kcxxes = kcxxes;
//		this.rkmxes = rkmxes;
//		this.chukumxes = chukumxes;
//		this.products = products;
//	}
//
//	public Specifications(int id, String name, BigDecimal weight,
//			String packType, Set<Kcxx> kcxxes, Set<Rkmx> rkmxes,
//			Set<Chukumx> chukumxes, Set<Products> products) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.weight = weight;
//		this.packType = packType;
//		this.kcxxes = kcxxes;
//		this.rkmxes = rkmxes;
//		this.chukumxes = chukumxes;
//		this.products = products;
//	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getPackType(){
		return this.packType;
	}
	
	public void setPackType(String packType){
		this.packType = packType;
	}
	
//	public Set<Kcxx> getKcxxes() {
//		return this.kcxxes;
//	}
//
//	public void setKcxxes(Set<Kcxx> kcxxes) {
//		this.kcxxes = kcxxes;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specifications")
//	public Set<Rkmx> getRkmxes() {
//		return this.rkmxes;
//	}
//
//	public void setRkmxes(Set<Rkmx> rkmxes) {
//		this.rkmxes = rkmxes;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specifications")
//	public Set<Chukumx> getChukumxes() {
//		return this.chukumxes;
//	}
//
//	public void setChukumxes(Set<Chukumx> chukumxes) {
//		this.chukumxes = chukumxes;
//	}
//
//	@ManyToMany(mappedBy = "specifications", targetEntity = Products.class)
//	public Set<Products> getProducts() {
//		return products;
//	}
//
//	public void setProducts(Set<Products> products) {
//		this.products = products;
//	}
	
	public String getDisplayName(){
		return name + " | " + packType;
	}
	
//	public void setDisplayName(String s){
//		
//	}

}