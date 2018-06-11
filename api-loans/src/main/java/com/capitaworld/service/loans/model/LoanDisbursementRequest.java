package com.capitaworld.service.loans.model;

import java.io.Serializable;

public class LoanDisbursementRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	private Long disbursedAmount;
	private String mode;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getDisbursedAmount() {
		return disbursedAmount;
	}

	public void setDisbursedAmount(Long disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "LoanDisbursementDomain [applicationId=" + applicationId + ", disbursedAmount=" + disbursedAmount
				+ ", mode=" + mode + "]";
	}
}
