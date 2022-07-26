package com.barclays.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class  MasterBiller {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billerName;
	private Integer billerCode;
	
	
	public Integer getBillerName() {
		return billerName;
	}
	public void setBillerName(Integer billerName) {
		this.billerName = billerName;
	}
	public Integer getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(Integer billerCode) {
		this.billerCode = billerCode;
	}
	
	
}