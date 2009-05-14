package com.dcsh.market;

/**
 * @author Woden
 *
 */
public class Smmdy {
	
	private Smmdingyue smm;
	private boolean xsyk1;
	private boolean xsyk2;
	private boolean xsyk3;
	private boolean xsfh1;
	private boolean xsfh2;
	private boolean cycyk1;
	private boolean cycyk2;
	
	public Smmdy(Smmdingyue smm){
		this.smm = smm;
		this.xsyk1 = smm.getXsyk1()==1?true:false;
		this.xsyk2 = smm.getXsyk2()==1?true:false;
		this.xsyk3 = smm.getXsyk3()==1?true:false;
		this.xsfh1 = smm.getXsfh1()==1?true:false;
		this.xsfh2 = smm.getXsfh2()==1?true:false;
		this.cycyk1 = smm.getCycyk1()==1?true:false;
		this.cycyk2 = smm.getCycyk2()==1?true:false;
	}
	
	public Smmdingyue getSmm() {
		return smm;
	}
	public void setSmm(Smmdingyue smm) {
		this.smm = smm;
	}

	public boolean isXsyk1() {
		return xsyk1;
	}

	public void setXsyk1(boolean xsyk1) {
		this.xsyk1 = xsyk1;
	}

	public boolean isXsyk2() {
		return xsyk2;
	}

	public void setXsyk2(boolean xsyk2) {
		this.xsyk2 = xsyk2;
	}

	public boolean isXsyk3() {
		return xsyk3;
	}

	public void setXsyk3(boolean xsyk3) {
		this.xsyk3 = xsyk3;
	}

	public boolean isXsfh1() {
		return xsfh1;
	}

	public void setXsfh1(boolean xsfh1) {
		this.xsfh1 = xsfh1;
	}

	public boolean isXsfh2() {
		return xsfh2;
	}

	public void setXsfh2(boolean xsfh2) {
		this.xsfh2 = xsfh2;
	}

	public boolean isCycyk1() {
		return cycyk1;
	}

	public void setCycyk1(boolean cycyk1) {
		this.cycyk1 = cycyk1;
	}

	public boolean isCycyk2() {
		return cycyk2;
	}

	public void setCycyk2(boolean cycyk2) {
		this.cycyk2 = cycyk2;
	}
	
	
	

}
