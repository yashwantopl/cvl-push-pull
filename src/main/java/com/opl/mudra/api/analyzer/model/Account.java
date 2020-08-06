package com.opl.mudra.api.analyzer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

	

	@XmlElement(name = "accountPattern", required = true)
	private String accountPattern;
	
	@XmlElement(name = "transactionEndDate", required = true)
	private String transactionEndDate;
	
	@XmlElement(name = "transactionStartDate", required = true)
	private String transactionStartDate;

	public String getAccountPattern() {
		return accountPattern;
	}

	public void setAccountPattern(String accountPattern) {
		this.accountPattern = accountPattern;
	}

	public String getTransactionEndDate() {
		return transactionEndDate;
	}

	public void setTransactionEndDate(String transactionEndDate) {
		this.transactionEndDate = transactionEndDate;
	}

	public String getTransactionStartDate() {
		return transactionStartDate;
	}

	public void setTransactionStartDate(String transactionStartDate) {
		this.transactionStartDate = transactionStartDate;
	}
	
	
	
}
