package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;


/**
 * @author Sanket
 *
 */
public class CreditCardsDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String cardNumber;

	private int creditCardTypesId;

	private Boolean isActive = true;

	private String issuerName;

	private Double outstandingBalance;
	
	//UNSECURED LOAN	
	private String issuingBank;
	
	private Integer yearOfIssue;
	
	private Integer yearOfExpiry;
	
	private Long cardLimit;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCreditCardTypesId() {
		return creditCardTypesId;
	}

	public void setCreditCardTypesId(int creditCardTypesId) {
		this.creditCardTypesId = creditCardTypesId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public Double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(Double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public Integer getYearOfIssue() {
		return yearOfIssue;
	}

	public void setYearOfIssue(Integer yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}

	public Integer getYearOfExpiry() {
		return yearOfExpiry;
	}

	public void setYearOfExpiry(Integer yearOfExpiry) {
		this.yearOfExpiry = yearOfExpiry;
	}

	public Long getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(Long cardLimit) {
		this.cardLimit = cardLimit;
	}
	
	

}
