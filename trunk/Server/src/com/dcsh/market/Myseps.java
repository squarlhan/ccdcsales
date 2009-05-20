package com.dcsh.market;

public class Myseps {
	
	private Specifications spf;
	private String ismerge;
		
	public Myseps(Specifications spf, String ismerge) {
		super();
		this.spf = spf;
		this.ismerge = ismerge;
	}
	
	public Myseps(Specifications spf) {
		super();
		this.spf = spf;
		this.setIsmerge((spf.getIsmerge()==(byte)0)?"不可":"可以");
	}
	
	public Myseps() {
		super();
	}
	
	public Specifications getSpf() {
		return spf;
	}
	public void setSpf(Specifications spf) {
		this.spf = spf;
	}
	public String getIsmerge() {
		return ismerge;
	}
	public void setIsmerge(String ismerge) {
		this.ismerge = ismerge;
	}
	
	

}
