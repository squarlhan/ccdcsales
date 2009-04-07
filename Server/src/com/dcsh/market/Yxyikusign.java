package com.dcsh.market;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name = "Yxyikusign", schema = "dbo", catalog = "test")
public class Yxyikusign implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9110374189960238028L;
	private int id;
	private XSyikuxx xsyikuxx;//移库单
	private byte type;//状态
	private Users fzr;//负责人
	private Date signtime;//签字时间 
	
	
	
	
	public Yxyikusign() {
		super();
	}
	public Yxyikusign(XSyikuxx xsyikuxx, byte type, Users fzr, Date signtime) {
		super();
		this.xsyikuxx = xsyikuxx;
		this.type = type;
		this.fzr = fzr;
		this.signtime = signtime;
	}
	public Yxyikusign(int id, XSyikuxx xsyikuxx, byte type, Users fzr,
			Date signtime) {
		super();
		this.id = id;
		this.xsyikuxx = xsyikuxx;
		this.type = type;
		this.fzr = fzr;
		this.signtime = signtime;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xsyikuxx", nullable = false)
	public XSyikuxx getXsyikuxx() {
		return xsyikuxx;
	}
	public void setXsyikuxx(XSyikuxx xsyikuxx) {
		this.xsyikuxx = xsyikuxx;
	}
	
	@Column(name = "type", nullable = false, length = 1)
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	@ManyToOne
	@JoinColumn(name = "fzr")
	public Users getFzr() {
		return fzr;
	}
	public void setFzr(Users fzr) {
		this.fzr = fzr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "signtime", nullable = false, length = 23)
	public Date getSigntime() {
		return signtime;
	}
	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}
	
	

}
