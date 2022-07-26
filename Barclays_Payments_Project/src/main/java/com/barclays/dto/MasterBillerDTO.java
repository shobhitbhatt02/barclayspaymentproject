package com.barclays.dto;

public class MasterBillerDTO {
	
	private Integer billerName;
	private Integer billerCode;
	
	
	
	public MasterBillerDTO() {
		
	}
	public MasterBillerDTO(Integer billerName, Integer billerCode) {
		super();
		this.billerName = billerName;
		this.billerCode = billerCode;
		
	}
	public Integer getbillerName() {
		return billerName;
	}
	public void setbillerName(Integer billerName) {
		this.billerName = billerName;
	}
	public Integer getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(Integer billerCode) {
		this.billerCode = billerCode;
	}
	
	@Override
	public String toString() {
		return "MasterBiller [billerName=" + billerName + ", billerCode=" + billerCode
				+ "]";
	}
	
	

}
