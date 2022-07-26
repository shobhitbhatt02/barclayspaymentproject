package com.barclays.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accounts_Transaction {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bill_ref_num;
	private  Integer sequence_id;
	private Date date;
	private Integer amount;
	private  String card_type;
	private String description;
	public Integer getBill_ref_num() {
		return bill_ref_num;
	}
	public void setBill_ref_num(Integer bill_ref_num) {
		this.bill_ref_num = bill_ref_num;
	}
	public Integer getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(Integer sequence_id) {
		this.sequence_id = sequence_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Accounts_Transaction [bill_ref_num=" + bill_ref_num + ", sequence_id=" + sequence_id + ", date=" + date
				+ ", amount=" + amount + ", card_type=" + card_type + ", description=" + description + "]";
	}
	
	
}



















/*Sequence Id --> FK from Accounts
Transaction Reference-->Unique
Date 
Ammount
Debiy/Credit
Description
Bill Reference Number--> PK */
