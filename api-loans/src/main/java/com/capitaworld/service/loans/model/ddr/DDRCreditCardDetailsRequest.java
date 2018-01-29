package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCreditCardDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String bankName;
	
	private Double creditCard;

	private String anyOtherShowroom;
	
	private String referenceNo;
	
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Double creditCard) {
		this.creditCard = creditCard;
	}

	public String getAnyOtherShowroom() {
		return anyOtherShowroom;
	}

	public void setAnyOtherShowroom(String anyOtherShowroom) {
		this.anyOtherShowroom = anyOtherShowroom;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRCreditCardDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", creditCard=" + creditCard + ", anyOtherShowroom=" + anyOtherShowroom + ", referenceNo="
				+ referenceNo + ", isActive=" + isActive + "]";
	}
	
	
	 

}
