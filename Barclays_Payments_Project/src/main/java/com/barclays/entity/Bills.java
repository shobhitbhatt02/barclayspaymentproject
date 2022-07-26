package com.barclays.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class  Bills {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer amount;
	private Integer dueDate;
	
	public Integer getBillSequenceId() {
		return billSequenceId;
	}
	public void setBillSequenceId(Integer billSequenceId) {
		this.billSequenceId = billSequenceId;
	}
	public Integer getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(Integer billerCode) {
		this.billerCode = billerCode;
	}
	public String getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getDueDate() {
		return dueDate;
	}
	public void setDueDate(Integer dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
	
	
	
	
	