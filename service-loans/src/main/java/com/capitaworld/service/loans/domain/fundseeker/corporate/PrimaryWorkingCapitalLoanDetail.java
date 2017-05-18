package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

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
	private Integer creditRatingId;

	@Lob
	@Column(name = "enhancement_of_limit")
	private String enhancementOfLimit;

	@Column(name = "have_existing_limit")
	private Boolean haveExistingLimit;

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

	@Column(name = "collateral_security_amt_total")
	private Double totalCollateralDetails;

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

	
	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public String getEnhancementOfLimit() {
		return this.enhancementOfLimit;
	}

	public void setEnhancementOfLimit(String enhancementOfLimit) {
		this.enhancementOfLimit = enhancementOfLimit;
	}

	public Boolean getHaveExistingLimit() {
		return haveExistingLimit;
	}

	public void setHaveExistingLimit(Boolean haveExistingLimit) {
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

	public Double getTotalCollateralDetails() {
		return totalCollateralDetails;
	}

	public void setTotalCollateralDetails(Double totalCollateralDetails) {
		this.totalCollateralDetails = totalCollateralDetails;
	}

}