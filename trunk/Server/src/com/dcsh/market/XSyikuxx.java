package com.dcsh.market;

import java.math.BigDecimal;
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
@Table(name = "XSyikuxx", schema = "dbo", catalog = "test")
public class XSyikuxx implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4077688625844333099L;
	private int id;
	private Date ctime;//填写表单时间
	private String bno;//编号
	private Custom customer;//客户
	private String orgin;//出发地公司名
	private String aim;//目的地站点
	private Date sendtime;//计划发货时间
	private byte delivertype;//运输方式
	private BigDecimal price;// 现价 
	private String memo;//备注
	private Canku aimcanku;//目标仓库
	private byte status;//状态
	private Users zbr;//制表人
	private byte type;//销售类型
	
    private Set<XSyikumx> xsyikumxes = new HashSet<XSyikumx>(0);
    private Set<Yxyikusign> sxyikusigns = new HashSet<Yxyikusign>(0);
    
    
    

    public XSyikuxx() {
		super();
	}
    
    

	public XSyikuxx(int id, Date ctime, String bno, Custom customer,Users zbr, byte type,
			String orgin, String aim, Date sendtime, byte delivertype,byte status,
			BigDecimal price, String memo, Canku aimcanku, Set<XSyikumx> xsyikumxes) {
		super();
		this.id = id;
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.aimcanku = aimcanku;
		this.price = price;
		this.status = status;
		this.zbr = zbr;
		this.xsyikumxes = xsyikumxes;
		this.type = type;
	}
	
	



	public XSyikuxx(Date ctime, String bno, Custom customer, String orgin,byte status,
			String aim, Date sendtime, byte delivertype, String memo,Canku aimcanku, byte type,
			BigDecimal price, Users zbr, Set<XSyikumx> xsyikumxes) {
		super();
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.price = price;
		this.status = status;
		this.aimcanku = aimcanku;
		this.zbr = zbr;
		this.xsyikumxes = xsyikumxes;
		this.type = type;
	}
	
	



	public XSyikuxx(int id, Date ctime, String bno, Custom customer,Users zbr, 
			String orgin, String aim, Date sendtime, byte delivertype,byte type,
			byte status,BigDecimal price, String memo,Canku aimcanku) {
		super();
		this.id = id;
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.price = price;
		this.status = status;
		this.zbr = zbr;
		this.aimcanku = aimcanku;
		this.type = type;
	}
	
	



	public XSyikuxx(Date ctime, String bno, Custom customer, String orgin,BigDecimal price, Users zbr,byte type, 
			byte status,String aim, Date sendtime, byte delivertype, Canku aimcanku, String memo) {
		super();
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.price = price;
		this.status = status;
		this.zbr = zbr;
		this.aimcanku = aimcanku;
		this.type = type;
	}

    

	public XSyikuxx(int id, Date ctime, String bno, Custom customer,byte status,Users zbr, 
			String orgin, String aim, Date sendtime, byte delivertype,BigDecimal price, byte type,
			Canku aimcanku, String memo, Set<XSyikumx> xsyikumxes, Set<Yxyikusign> sxyikusigns) {
		super();
		this.id = id;
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.aimcanku = aimcanku;
		this.price = price;
		this.status = status;
		this.xsyikumxes = xsyikumxes;
		this.zbr = zbr;
		this.sxyikusigns = sxyikusigns;
		this.type = type;
	}
	
	



	public XSyikuxx(Date ctime, String bno, Custom customer, String orgin,byte status,Users zbr, 
			String aim, Date sendtime, byte delivertype, String memo,Canku aimcanku, byte type,
			BigDecimal price, Set<XSyikumx> xsyikumxes, Set<Yxyikusign> sxyikusigns) {
		super();
		this.ctime = ctime;
		this.bno = bno;
		this.customer = customer;
		this.orgin = orgin;
		this.aim = aim;
		this.sendtime = sendtime;
		this.delivertype = delivertype;
		this.memo = memo;
		this.aimcanku = aimcanku;
		this.price = price;
		this.status = status;
		this.xsyikumxes = xsyikumxes;
		this.zbr = zbr;
		this.sxyikusigns = sxyikusigns;
		this.type = type;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ctime", nullable = false, length = 23)
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "bno", nullable = false, length = 16)
	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	public Custom getCustomer() {
		return customer;
	}

	public void setCustomer(Custom customer) {
		this.customer = customer;
	}

	@Column(name = "orgin", length = 50)
	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	@Column(name = "aim", length = 50)
	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sendtime", nullable = false, length = 23)
	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "delivertype", length = 1)
	public byte getDelivertype() {
		return delivertype;
	}

	public void setDelivertype(byte delivertype) {
		this.delivertype = delivertype;
	}

	@Column(name = "memo", length = 200)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "xsyikuxx")
	public Set<XSyikumx> getXsyikumxes() {
		return xsyikumxes;
	}

	public void setXsyikumxes(Set<XSyikumx> xsyikumxes) {
		this.xsyikumxes = xsyikumxes;
	}

	@OneToMany(mappedBy = "xsyikuxx")
	public Set<Yxyikusign> getSxyikusigns() {
		return sxyikusigns;
	}
	public void setSxyikusigns(Set<Yxyikusign> sxyikusigns) {
		this.sxyikusigns = sxyikusigns;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aimcanku")
	public Canku getAimcanku() {
		return aimcanku;
	}
	public void setAimcanku(Canku aimcanku) {
		this.aimcanku = aimcanku;
	}

	@Column(name = "status", length = 2)
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	@Column(name="price")
    public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zbr")
	public Users getZbr() {
		return zbr;
	}
	public void setZbr(Users zbr) {
		this.zbr = zbr;
	}
	
	@Column(name = "type", length = 1)
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	
	
    
    
}
