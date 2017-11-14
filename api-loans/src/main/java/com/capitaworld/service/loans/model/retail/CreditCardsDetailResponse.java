package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

public class CreditCardsDetailResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cardNumber;
	private String creditCardTypes;
	private String issuerName;
	private String outstandingBalance;
	
	//UNSECURED LOAN	
	private String issuingBank;
	
	private String yearOfIssue;
	
	private String yearOfExpiry;
	
	private String cardLimit;

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCreditCardTypes() {
		return creditCardTypes;
	}
	public void setCreditCardTypes(String creditCardTypes) {
		this.creditCardTypes = creditCardTypes;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getOutstandingBalance() {
		return outstandingBalance;
	}
	public void setOutstandingBalance(String outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getYearOfIssue() {
		return yearOfIssue;
	}
	public void setYearOfIssue(String yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}
	public String getYearOfExpiry() {
		return yearOfExpiry;
	}
	public void setYearOfExpiry(String yearOfExpiry) {
		this.yearOfExpiry = yearOfExpiry;
	}
	public String getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}
	
	
}
