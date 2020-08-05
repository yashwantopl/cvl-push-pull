package com.opl.mudra.api.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixedDepositsDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Double amount;

	private String bankName;

	private String fdNumber;

	private Boolean isActive = true;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date maturityDate;
	
	private String maturityDateInString;

	private Double rate;
	
	//For cam
	
	private String amountString;
	private String rateString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getFdNumber() {
		return fdNumber;
	}

	public void setFdNumber(String fdNumber) {
		this.fdNumber = fdNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getMaturityDateInString() {
		return maturityDateInString;
	}

	public void setMaturityDateInString(String maturityDateInString) {
		this.maturityDateInString = maturityDateInString;
	}

	public String getAmountString() {
		return amountString;
	}

	public void setAmountString(String amountString) {
		this.amountString = amountString;
	}

	public String getRateString() {
		return rateString;
	}

	public void setRateString(String rateString) {
		this.rateString = rateString;
	}
	

}
