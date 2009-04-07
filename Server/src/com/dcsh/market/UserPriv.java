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
@Table(name = "UserPriv", schema = "dbo", catalog = "test")
public class UserPriv implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5751575627556843672L;
	/*
	 * 
	 */
	private int id;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private Users user;
	private String resource;
	private int type;
	
	
	public UserPriv() {
		super();
	}


	public UserPriv(Users user, String resource, int type) {
		super();
		this.user = user;
		this.resource = resource;
		this.type = type;
	}
	
	

	public UserPriv(int id, Users user, String resource, int type) {
		super();
		this.id = id;
		this.user = user;
		this.resource = resource;
		this.type = type;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UID", nullable = false)
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}

	@Column(name = "Resource", length = 300)
	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}

	@Column(name = "Type", nullable = false)
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	
	
	

}
