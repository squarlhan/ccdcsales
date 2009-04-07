package com.dcsh.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fahuo", schema = "dbo", catalog = "test")
public class Fahuo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -350782068476584869L;
	private int id;
	private String name;
	
	
	
	
	public Fahuo(String name) {
		super();
		this.name = name;
	}
	public Fahuo() {
		super();
	}
	public Fahuo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
