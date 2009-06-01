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
@Table(name = "ReportPmx", schema = "dbo", catalog = "test")
public class ReportPmx implements java.io.Serializable {
	private int id;
	private Reportxx rxxid;//属于哪个日报
	private Canku ckid;//仓库id
	private Products prdid;//产品ID
	private BigDecimal rkt;
	private BigDecimal ckt;
	private BigDecimal kct;//当前库存总量
	private BigDecimal nxt;
	private BigDecimal wxt;
	private BigDecimal djt;
	private BigDecimal dxt;
	private BigDecimal bhgt;
	private BigDecimal ct;
	private BigDecimal gqt;
	private BigDecimal cjt;
	
	public ReportPmx() {
		
	}
	
	public ReportPmx(int id, Reportxx rxxid, Canku ckid, Products prdid,
			BigDecimal rkt, BigDecimal ckt, BigDecimal kct, BigDecimal nxt,
			BigDecimal wxt, BigDecimal djt, BigDecimal dxt, BigDecimal bhgt,BigDecimal ct,BigDecimal gqt) {
		this.id = id ;
		this.rxxid = rxxid;
		this.ckid = ckid;
		this.prdid = prdid;
		this.rkt = rkt;
		this.ckt = ckt;
		this.kct = kct;
		this.nxt = nxt;
		this.wxt = wxt;
		this.djt = djt;
		this.dxt = dxt;
		this.bhgt = bhgt;
		this.ct = ct;
		this.gqt = gqt;
	}
	
    
	
	public ReportPmx(Reportxx rxxid, Canku ckid, Products prdid,
			BigDecimal rkt, BigDecimal ckt, BigDecimal kct, BigDecimal nxt,
			BigDecimal wxt, BigDecimal djt, BigDecimal dxt, BigDecimal bhgt,BigDecimal ct,BigDecimal gqt,BigDecimal cjt) {
		super();
		this.rxxid = rxxid;
		this.ckid = ckid;
		this.prdid = prdid;
		this.rkt = rkt;
		this.ckt = ckt;
		this.kct = kct;
		this.nxt = nxt;
		this.wxt = wxt;
		this.djt = djt;
		this.dxt = dxt;
		this.bhgt = bhgt;
		this.ct = ct;
		this.gqt = gqt;
		this.cjt = cjt;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
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
	
	@Column(name = "Rkt")
	public BigDecimal getRkt() {
		return rkt;
	}

	public void setRkt(BigDecimal rkt) {
		this.rkt = rkt;
	}
	@Column(name = "Ckt")
	public BigDecimal getCkt() {
		return ckt;
	}

	public void setCkt(BigDecimal ckt) {
		this.ckt = ckt;
	}
	
	@Column(name = "Kct", nullable = false)
	public BigDecimal getKct() {
		return kct;
	}

	public void setKct(BigDecimal kct) {
		this.kct = kct;
	}

	@Column(name = "Nxt")
	public BigDecimal getNxt() {
		return nxt;
	}

	public void setNxt(BigDecimal nxt) {
		this.nxt = nxt;
	}


	@Column(name = "Wxt")
	public BigDecimal getWxt() {
		return wxt;
	}
	@Column(name = "Wxt")
	public BigDecimal setWxt() {
		return wxt;

	}

	public void setWxt(BigDecimal wxt) {
		this.wxt = wxt;
	}
	
	@Column(name = "Djt")
	public BigDecimal getDjt() {
		return djt;
	}

	public void setDjt(BigDecimal djt) {
		this.djt = djt;
	}
	
	@Column(name = "Dxt")
	public BigDecimal getDxt() {
		return dxt;
	}

	public void setDxt(BigDecimal dxt) {
		this.dxt = dxt;
	}
	
	@Column(name = "Bhgt")
	public BigDecimal getBhgt() {
		return bhgt;
	}

	public void setBhgt(BigDecimal bhgt) {
		this.bhgt = bhgt;
	}
	@Column(name = "Ct")
	public BigDecimal getCt()
	{
		return ct;
	}
	public void setCt(BigDecimal ct){
		this.ct = ct;
	}
	@Column(name = "Gqt")
	public BigDecimal getGqt(){
		return gqt;
	}
	public void setGqt(BigDecimal gqt){
		this.gqt = gqt;
	}
	@Column(name = "Cjt")
	public BigDecimal getCjt(){
		return cjt;
	}
	public void setCjt(BigDecimal cjt){
		this.cjt =  cjt;
	}
}