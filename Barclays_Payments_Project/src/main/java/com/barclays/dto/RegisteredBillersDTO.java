package com.barclays.dto;

public class RegisteredBillersDTO {
	
	private Integer billerSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer sequenceId;
	private Integer autopay;
	private Integer autopayLimit;
	
	
	public RegisteredBillersDTO() {
		
	}
	public RegisteredBillersDTO(Integer billerSequenceId, Integer billerCode, String consumerNumber, Integer sequenceId,
			Integer autopay, Integer autopayLimit) {
		super();
		this.billerSequenceId = billerSequenceId;
		this.billerCode = billerCode;
		this.consumerNumber = consumerNumber;
		this.sequenceId = sequenceId;
		this.autopay = autopay;
		this.autopayLimit = autopayLimit;
	}
	public Integer getBillerSequenceId() {
		return billerSequenceId;
	}
	public void setBillerSequenceId(Integer billerSequenceId) {
		this.billerSequenceId = billerSequenceId;
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
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	public Integer getAutopay() {
		return autopay;
	}
	public void setAutopay(Integer autopay) {
		this.autopay = autopay;
	}
	public Integer getAutopayLimit() {
		return autopayLimit;
	}
	public void setAutopayLimit(Integer autopayLimit) {
		this.autopayLimit = autopayLimit;
	}
	@Override
	public String toString() {
		return "RegisteredBillers [billerSequenceId=" + billerSequenceId + ", billerCode=" + billerCode
				+ ", consumerNumber=" + consumerNumber + ", sequenceId=" + sequenceId + ", autopay=" + autopay
				+ ", autopayLimit=" + autopayLimit + "]";
	}
	
	

}
