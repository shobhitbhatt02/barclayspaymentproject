package com.barclays.dto;

public class BillsDTO {
	
	private Integer billSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer amount;
	private Integer dueDate;
	
	
	public BillsDTO() {
		
	}
	public BillsDTO(Integer billSequenceId, Integer billerCode, String consumerNumber, Integer amount,
			Integer dueDate ) {
		super();
		this.billSequenceId = billSequenceId;
		this.billerCode = billerCode;
		this.consumerNumber = consumerNumber;
		this.amount = amount;
		this.dueDate = dueDate;
	}
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
	public Integer getamount() {
		return amount;
	}
	public void setSequenceId(Integer amount) {
		this.amount = amount;
	}
	public Integer getdueDate() {
		return dueDate;
	}
	public void setAutopay(Integer dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() {
		return "Bills [billSequenceId=" + billSequenceId + ", billerCode=" + billerCode
				+ ", consumerNumber=" + consumerNumber + ", amount=" + amount + ", dueDate=" + dueDate
				+"]";
	}
	
	

}
