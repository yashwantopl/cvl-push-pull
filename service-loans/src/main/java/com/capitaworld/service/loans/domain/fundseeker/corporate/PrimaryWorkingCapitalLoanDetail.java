package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import java.util.Date;

/**
 * The persistent class for the fs_corporate_primary_wc_loan_details database
 * table.
 * 
 */
@Entity
@Table(name = "fs_corporate_primary_wc_loan_details")
@NamedQuery(name = "PrimaryWorkingCapitalLoanDetail.findAll", query = "SELECT f FROM PrimaryWorkingCapitalLoanDetail f")
public class PrimaryWorkingCapitalLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "credit_rating_id")
	private int creditRatingId;

	@Lob
	@Column(name = "enhancement_of_limit")
	private String enhancementOfLimit;

	@Column(name = "have_existing_limit")
	private boolean haveExistingLimit;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Lob
	@Column(name = "project_brief")
	private String projectBrief;

	@Column(name = "total_collteral_details")
	private double totalCollteralDetails;

	public PrimaryWorkingCapitalLoanDetail() {
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreditRatingId() {
		return this.creditRatingId;
	}

	public void setCreditRatingId(int creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public String getEnhancementOfLimit() {
		return this.enhancementOfLimit;
	}

	public void setEnhancementOfLimit(String enhancementOfLimit) {
		this.enhancementOfLimit = enhancementOfLimit;
	}

	public boolean getHaveExistingLimit() {
		return this.haveExistingLimit;
	}

	public void setHaveExistingLimit(boolean haveExistingLimit) {
		this.haveExistingLimit = haveExistingLimit;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getProjectBrief() {
		return this.projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public double getTotalCollteralDetails() {
		return this.totalCollteralDetails;
	}

	public void setTotalCollteralDetails(double totalCollteralDetails) {
		this.totalCollteralDetails = totalCollteralDetails;
	}

}