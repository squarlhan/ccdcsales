package com.dcsh.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;



@Entity
@Table(name = "Reportxx", schema = "dbo", catalog = "test")

public class Reportxx implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1769018977990417259L;
	/**
	 * 
	 */

	private int id;
	private String bno;
	private Date date;
	private Users reporter;
	private Canku ckid;//²Ö¿âid

	
    public Reportxx(){};
    
    public Reportxx(int id, String bno, Date date, Users reporter, Canku ckid) {
		this.id = id;
		this.bno = bno;
		this.date = date;
		this.reporter = reporter;
		this.ckid = ckid;
	}
    
	public Reportxx(String bno, Date date, Users reporter, Canku ckid) {
		super();
		this.bno = bno;
		this.date = date;
		this.reporter = reporter;
		this.ckid = ckid;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "BNO", nullable = false, length = 10)
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Reportsj", nullable = false, length = 23)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@ManyToOne
	@JoinColumn(name = "Reporter", nullable = false)
	public Users getReporter() {
		return reporter;
	}
	public void setReporter(Users reporter) {
		this.reporter = reporter;
	}
	@ManyToOne
	@JoinColumn(name = "CkID", nullable = false)
	public Canku getCkid() {
		return ckid;
	}
	public void setCkid(Canku ckid) {
		this.ckid = ckid;
	}
		
}