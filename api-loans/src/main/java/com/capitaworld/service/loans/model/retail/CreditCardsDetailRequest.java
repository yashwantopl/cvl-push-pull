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
	
	

}
