package com.dcsh.market;

// Generated 2009-3-2 20:48:10 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Products generated by hbm2java
 */
@Entity
@Table(name = "Products", schema = "dbo", catalog = "test")
public class Products implements java.io.Serializable {

	private int id;
	private String name;
	private Set<Kcxx> kcxxes = new HashSet<Kcxx>(0);
	private Set<Chukumx> chukumxes = new HashSet<Chukumx>(0);
	private Set<Rkmx> rkmxes = new HashSet<Rkmx>(0);
	private Set<Specifications> specifications = new HashSet<Specifications>(0);

	public Products() {
	}

	public Products(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Products(String name) {
		this.name = name;
	}

	public Products(int id, String name, Set<Kcxx> kcxxes,
			Set<Chukumx> chukumxes, Set<Rkmx> rkmxes) {
		this.id = id;
		this.name = name;
		this.kcxxes = kcxxes;
		this.chukumxes = chukumxes;
		this.rkmxes = rkmxes;
	}
	
	

	public Products(int id, String name, Set<Kcxx> kcxxes,
			Set<Chukumx> chukumxes, Set<Rkmx> rkmxes,
			Set<Specifications> specifications) {
		super();
		this.id = id;
		this.name = name;
		this.kcxxes = kcxxes;
		this.chukumxes = chukumxes;
		this.rkmxes = rkmxes;
		this.specifications = specifications;
	}

	public Products(String name, Set<Kcxx> kcxxes, Set<Chukumx> chukumxes,
			Set<Rkmx> rkmxes, Set<Specifications> specifications) {
		super();
		this.name = name;
		this.kcxxes = kcxxes;
		this.chukumxes = chukumxes;
		this.rkmxes = rkmxes;
		this.specifications = specifications;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "products")
	public Set<Kcxx> getKcxxes() {
		return this.kcxxes;
	}

	public void setKcxxes(Set<Kcxx> kcxxes) {
		this.kcxxes = kcxxes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "products")
	public Set<Chukumx> getChukumxes() {
		return this.chukumxes;
	}

	public void setChukumxes(Set<Chukumx> chukumxes) {
		this.chukumxes = chukumxes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "products")
	public Set<Rkmx> getRkmxes() {
		return this.rkmxes;
	}

	public void setRkmxes(Set<Rkmx> rkmxes) {
		this.rkmxes = rkmxes;
	}

	@ManyToMany(targetEntity = Specifications.class)
	@JoinTable(name = "proRelspe", joinColumns = @JoinColumn(name = "product"), inverseJoinColumns = @JoinColumn(name = "specification"))
	public Set<Specifications> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<Specifications> specifications) {
		this.specifications = specifications;
	}
	

	
    public int hashCode() {
    	return this.getId();
    }
    
    @Override
    public boolean equals(Object obj){
    	if(obj instanceof Products){
    		Products prd = (Products)obj;
    		return this.getId() == prd.getId();
    	}
    	return false;
    }

	


}
