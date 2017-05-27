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
	
	
}
