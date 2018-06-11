package com.capitaworld.service.loans.domain.sanction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;


@Entity
@Table(name="disbursement_detail")
public class LoanDisbursementDomain extends AuditActivity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private Long id;
	
	@Column(name="application_id") 
	private Long applicationId;
	
	@Column(name="disbursement_date")
	private Long disbursementDate;
	
	@Column(name="disbursed_amount")
	private Long disbursedAmount;
	
	private String mode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Long disbursementDate) {
		this.disbursementDate = disbursementDate;
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
		return "LoanDisbursementDomain [id=" + id + ",  applicationId=" + applicationId
				+ ", disbursementDate=" + disbursementDate + ", disbursedAmount=" + disbursedAmount + ", mode=" + mode
				+ "]";
	}

	
}
