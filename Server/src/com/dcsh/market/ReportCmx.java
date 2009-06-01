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
@Table(name = "ReportCmx", schema = "dbo", catalog = "test")
public class ReportCmx implements java.io.Serializable {
	//销售明细
	private int id;
	private Reportxx rxxid;//属于哪个日报
	private Canku ckid;//出库id
	private Products prdid;//产品ID
	private Canku rkid;//入库的ID
	private BigDecimal ckt;//总量
	private String memo;//备注
	
	public ReportCmx(){
		
	}
	
	public ReportCmx(int id, Reportxx rxxid, Canku ckid, 
			Products prdid, Canku rkid, BigDecimal ckt){
		this.id = id;
		this.rxxid = rxxid;
		this.ckid =ckid;
		this.prdid = prdid;
		this.rkid = rkid;
		this.ckt = ckt;
	}
	
	public ReportCmx(Reportxx rxxid, Canku ckid, 
			Products prdid, Canku rkid, BigDecimal ckt){
		this.rxxid = rxxid;
		this.ckid =ckid;
		this.prdid = prdid;
		this.rkid = rkid;
		this.ckt = ckt;
	}
	
	public ReportCmx(Products prdid, BigDecimal ckt){
		this.prdid = prdid;
		this.ckt = ckt;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Rxxid", nullable = false)
	public Reportxx getRxxid() {
		return rxxid;
	}
	
	public void setRxxid(Reportxx rxxid) {
		this.rxxid = rxxid;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Ckid", nullable = false)
	public Canku getCkid() {
		return ckid;
	}

	public void setCkid(Canku ckid) {
		this.ckid = ckid;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Prdid", nullable = false)
	public Products getPrdid() {
		return prdid;
	}

	public void setPrdid(Products prdid) {
		this.prdid = prdid;
	}
	
	@ManyToOne
	@JoinColumn(name = "Rkid", nullable = false)
	public Canku getRkid() {
		return rkid;
	}

	public void setRkid(Canku rkid) {
		this.rkid = rkid;
	}
	
	@Column(name = "Ckt")
	public BigDecimal getCkt() {
		return ckt;
	}

	public void setCkt(BigDecimal ckt) {
		this.ckt = ckt;
	}
	@Column(name = "Memo", length=50)
	public String getMemo()
	{
		return memo;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
}
