package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CustomerInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfo {

	@XmlAttribute(name = "name")
	private String name;

	@XmlAttribute(name = "address")
	private String address;
	
	@XmlAttribute(name = "landline")
	private String landline;
	
	@XmlAttribute(name = "mobile")
	private String mobile;
	
	@XmlAttribute(name = "email")
	private String email;
	
	@XmlAttribute(name = "pan")
	private String pan;
	
	@XmlAttribute(name = "perfiosTransactionId")
	private String perfiosTransactionId;
	
	@XmlAttribute(name = "customerTransactionId")
	private String customerTransactionId;
	
	@XmlAttribute(name = "bank")
	private String bank;
	
	@XmlAttribute(name = "instId")
	private String instId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPerfiosTransactionId() {
		return perfiosTransactionId;
	}

	public void setPerfiosTransactionId(String perfiosTransactionId) {
		this.perfiosTransactionId = perfiosTransactionId;
	}

	public String getCustomerTransactionId() {
		return customerTransactionId;
	}

	public void setCustomerTransactionId(String customerTransactionId) {
		this.customerTransactionId = customerTransactionId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}
	
	

}
