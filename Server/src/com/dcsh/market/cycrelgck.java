package com.dcsh.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cycrelgck", schema = "dbo", catalog = "test")
public class cycrelgck implements java.io.Serializable{
	
	private int id;
	private Canku cyc;
	private Canku gck;
	
	
	public cycrelgck() {
		super();
	}
	public cycrelgck(Canku cyc, Canku gck) {
		super();
		this.cyc = cyc;
		this.gck = gck;
	}
	public cycrelgck(int id, Canku cyc, Canku gck) {
		super();
		this.id = id;
		this.cyc = cyc;
		this.gck = gck;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "cyc", nullable = false)
	public Canku getCyc() {
		return cyc;
	}
	public void setCyc(Canku cyc) {
		this.cyc = cyc;
	}
	
	@ManyToOne
	@JoinColumn(name = "gck", nullable = false)
	public Canku getGck() {
		return gck;
	}
	public void setGck(Canku gck) {
		this.gck = gck;
	}
	
	

}
