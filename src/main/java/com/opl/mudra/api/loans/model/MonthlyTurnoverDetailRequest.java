package com.opl.mudra.api.loans.model;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class MonthlyTurnoverDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private Boolean isActive = true;

	private String monthName;
	
	private String amountString;

	public MonthlyTurnoverDetailRequest() {

	}

	public MonthlyTurnoverDetailRequest(String monthName) {
		this.monthName = monthName;
	}

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getAmountString() {
		return amountString;
	}

	public void setAmountString(String amountString) {
		this.amountString = amountString;
	}

	

}
