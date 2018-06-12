package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;
import com.capitaworld.service.loans.utils.AuditActivity;

public class LoanDisbursementRequest extends AuditActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	private Long disbursedAmount;
	private String mode;
	private Date disbursementDate; 
	
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
	
	
	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	@Override
	public String toString() {
		return "LoanDisbursementRequest [applicationId=" + applicationId + ", disbursedAmount=" + disbursedAmount
				+ ", mode=" + mode + ", disbursementDate=" + disbursementDate + "]";
	}

	
}
