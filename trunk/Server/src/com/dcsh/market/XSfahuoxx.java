package com.dcsh.market;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "XSfahuoxx", schema = "dbo", catalog = "test")
public class XSfahuoxx implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -260174187986843389L;
	
	private int id;//id
	private String domains;//销售区域
	private Date fahuosj;//发货时间
	private String bno;//销售编号
	private String cno;//合同号
	private String orgin;//发货公司
	private Custom customer;//客户
	private byte delivertype;//运输类型
	private byte jstype;//结算类型
	private String memo;//备注
	private Users shr;//审核人
	private Users zdr;//制单人
	private Users nhr;//内核人
	private byte status;//状态
	private byte type;//销售类型
	
	private Set<XSfahuomx> xsfahuomxes = new HashSet<XSfahuomx>(0);
	
	
	
	public XSfahuoxx(String domains, Date fahuosj, String bno, String cno,
			String orgin, Custom customer, byte delivertype, byte jstype,
			String memo, Users shr, Users zdr, Users nhr, byte status,
			byte type,Set<XSfahuomx> xsfahuomxes) {
		super();
		this.domains = domains;
		this.fahuosj = fahuosj;
		this.bno = bno;
		this.cno = cno;
		this.orgin = orgin;
		this.customer = customer;
		this.delivertype = delivertype;
		this.jstype = jstype;
		this.memo = memo;
		this.shr = shr;
		this.zdr = zdr;
		this.nhr = nhr;
		this.status = status;
		this.xsfahuomxes = xsfahuomxes;
		this.type = type;
	}
	public XSfahuoxx(int id, String domains, Date fahuosj, String bno,
			String cno, String orgin, Custom customer, byte delivertype,
			byte jstype, String memo, Users shr, Users zdr, Users nhr,
			byte type,byte status, Set<XSfahuomx> xsfahuomxes) {
		super();
		this.id = id;
		this.domains = domains;
		this.fahuosj = fahuosj;
		this.bno = bno;
		this.cno = cno;
		this.orgin = orgin;
		this.customer = customer;
		this.delivertype = delivertype;
		this.jstype = jstype;
		this.memo = memo;
		this.shr = shr;
		this.zdr = zdr;
		this.nhr = nhr;
		this.status = status;
		this.xsfahuomxes = xsfahuomxes;
		this.type = type;
	}
	public XSfahuoxx(String domains, Date fahuosj, String bno, String cno,
			String orgin, Custom customer, byte delivertype, byte jstype,
			byte type,String memo, Users shr, Users zdr, Users nhr, byte status) {
		super();
		this.domains = domains;
		this.fahuosj = fahuosj;
		this.bno = bno;
		this.cno = cno;
		this.orgin = orgin;
		this.customer = customer;
		this.delivertype = delivertype;
		this.jstype = jstype;
		this.memo = memo;
		this.shr = shr;
		this.zdr = zdr;
		this.nhr = nhr;
		this.status = status;
		this.type = type;
	}
	public XSfahuoxx(int id, String domains, Date fahuosj, String bno,
			String cno, String orgin, Custom customer, byte delivertype,
			byte jstype, String memo, Users shr, Users zdr, Users nhr,
			byte type,byte status) {
		super();
		this.id = id;
		this.domains = domains;
		this.fahuosj = fahuosj;
		this.bno = bno;
		this.cno = cno;
		this.orgin = orgin;
		this.customer = customer;
		this.delivertype = delivertype;
		this.jstype = jstype;
		this.memo = memo;
		this.shr = shr;
		this.zdr = zdr;
		this.nhr = nhr;
		this.status = status;
		this.type = type;
	}
	public XSfahuoxx() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "domains", nullable = false, length = 20)
	public String getDomains() {
		return domains;
	}
	public void setDomains(String domains) {
		this.domains = domains;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fahuosj", nullable = false, length = 23)
	public Date getFahuosj() {
		return fahuosj;
	}
	public void setFahuosj(Date fahuosj) {
		this.fahuosj = fahuosj;
	}
	
	@Column(name = "bno", nullable = false, length = 16)
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	
	@Column(name = "cno", nullable = false, length = 12)
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	
	@Column(name = "orgin", nullable = false, length = 50)
	public String getOrgin() {
		return orgin;
	}
	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	public Custom getCustomer() {
		return customer;
	}
	public void setCustomer(Custom customer) {
		this.customer = customer;
	}
	
	@Column(name = "delivertype", length = 1)
	public byte getDelivertype() {
		return delivertype;
	}
	public void setDelivertype(byte delivertype) {
		this.delivertype = delivertype;
	}
	
	@Column(name = "jstype", length = 1)
	public byte getJstype() {
		return jstype;
	}
	public void setJstype(byte jstype) {
		this.jstype = jstype;
	}
	
	@Column(name = "memo", length = 200)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shr")
	public Users getShr() {
		return shr;
	}
	public void setShr(Users shr) {
		this.shr = shr;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zdr")
	public Users getZdr() {
		return zdr;
	}
	public void setZdr(Users zdr) {
		this.zdr = zdr;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nhr")
	public Users getNhr() {
		return nhr;
	}
	public void setNhr(Users nhr) {
		this.nhr = nhr;
	}
	
	@Column(name = "status", length = 2)
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xsfahuoxx")
	public Set<XSfahuomx> getXsfahuomxes() {
		return xsfahuomxes;
	}
	public void setXsfahuomxes(Set<XSfahuomx> xsfahuomxes) {
		this.xsfahuomxes = xsfahuomxes;
	}
	
	@Column(name = "type", length = 1)
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	
	
	

}
