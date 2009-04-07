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
@Table(name = "UserGroupPriv", schema = "dbo", catalog = "test")
public class UserGroupPriv implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1875967902866740898L;
	private int id;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private UserGroup usergroup;
	private String resource;
	private int type;
	
	
	public UserGroupPriv() {
		super();
	}


	public UserGroupPriv(UserGroup usergroup, String resource, int type) {
		super();
		this.usergroup = usergroup;
		this.resource = resource;
		this.type = type;
	}
	
	

	public UserGroupPriv(int id, UserGroup usergroup, String resource, int type) {
		super();
		this.id = id;
		this.usergroup = usergroup;
		this.resource = resource;
		this.type = type;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GID", nullable = false)
    public UserGroup getUsergroup() {
		return usergroup;
	}


	public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
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
