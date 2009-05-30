package com.dcsh.market;

// Generated 2009-3-2 20:48:10 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "UserGroup", schema = "dbo", catalog = "test")
public class UserGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private Set<Users> users = new HashSet<Users>(0);

	public UserGroup() {
	}

	public UserGroup(int id) {
		this.id = id;
	}

	public UserGroup(int id, String name, String desc) {
		this.id = id;
		this.name = name;

		this.description = desc;
	}

	public UserGroup(String name, String desc) {

		this.name = name;

		this.description = desc;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(mappedBy = "group", targetEntity = Users.class)
//	@ManyToMany(targetEntity = Users.class)
//	@JoinTable(name = "UserRelGroup", joinColumns = @JoinColumn(name = "GID"), inverseJoinColumns = @JoinColumn(name = "UID"))
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

    public int hashCode() {
    	return this.getId();
    }
    
    @Override
    public boolean equals(Object obj){
    	if(obj instanceof UserGroup){
    		UserGroup ug = (UserGroup)obj;
    		return this.getId() == ug.getId();
    	}
    	return false;
    }
	
}
