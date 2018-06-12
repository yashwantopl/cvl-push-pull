package com.capitaworld.service.loans.domain.sanction;

import java.io.Serializable;
import java.util.Date;

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
	private Date disbursementDate;
	
	@Column(name="disbursed_amount")
	private Double disbursedAmount;
	
	private Long mode;

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

	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
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

	@Override
	public String toString() {
		return "LoanDisbursementDomain [id=" + id + ",  applicationId=" + applicationId
				+ ", disbursementDate=" + disbursementDate + ", disbursedAmount=" + disbursedAmount + ", mode=" + mode
				+ "]";
	}

	
}
