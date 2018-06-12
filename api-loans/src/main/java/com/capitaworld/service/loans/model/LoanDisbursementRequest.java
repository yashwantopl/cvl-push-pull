package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.common.AuditActivityRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanDisbursementRequest extends AuditActivityRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	private Double disbursedAmount;
	private Long mode;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy") 
	private Date disbursementDate; 
	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getDisbursedAmount() {
		return disbursedAmount;
	}

	public void setDisbursedAmount(Double disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}

	public Long getMode() {
		return mode;
	}

	public void setMode(Long mode) {
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
