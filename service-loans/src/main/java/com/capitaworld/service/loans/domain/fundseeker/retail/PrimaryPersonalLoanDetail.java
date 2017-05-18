package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_personal_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_personal_loan_details")
public class PrimaryPersonalLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="loan_purpose")
	private Long loanPurpose;

	@Column(name="loan_purpose_other")
	private String loanPurposeOther;


	public PrimaryPersonalLoanDetail() {
	}

	

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}



	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getLoanPurpose() {
		return this.loanPurpose;
	}

	public void setLoanPurpose(Long loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeOther() {
		return this.loanPurposeOther;
	}

	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}

}