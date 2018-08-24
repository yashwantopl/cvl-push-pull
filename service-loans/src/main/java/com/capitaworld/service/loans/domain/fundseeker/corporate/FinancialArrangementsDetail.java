package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * The persistent class for the
 * fs_corporate_current_financial_arrangements_details database table.
 * 
 */
@Entity
@Table(name = "fs_corporate_current_financial_arrangements_details")
public class FinancialArrangementsDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loan_date")
	private Date loanDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reported_date")
	private Date reportedDate;

	@Column(name = "loan_type")
	private String loanType;

	private Double emi;

	@Column(name="relationship_since") 
	private Integer relationshipSince;

	@ManyToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	 @Column(name="facility_nature_id") private Integer facilityNatureId;

	@Column(name = "financial_institution_name")
	private String financialInstitutionName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "outstanding_amount")
	private Double outstandingAmount;

	@Column(name = "security_details")
	private String securityDetails;
	
	@ManyToOne
	@JoinColumn(name = "director_id")
	private DirectorBackgroundDetail directorBackgroundDetail;

	/*
	 * SBI MSME Integration related fields
	 * By Ravina
	 * */
	@Column(name = "lc_bg_status")
	private Integer lcBgStatus;

	public FinancialArrangementsDetail() {
	}
	
	public FinancialArrangementsDetail(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
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

	 public Integer getFacilityNatureId() { return this.facilityNatureId; }

	 public void setFacilityNatureId(Integer facilityNatureId) {
	 this.facilityNatureId = facilityNatureId; }

	public String getFinancialInstitutionName() {
		return this.financialInstitutionName;
	}

	public void setFinancialInstitutionName(String financialInstitutionName) {
		this.financialInstitutionName = financialInstitutionName;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
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

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	/*
	 * public Integer getLenderType() { return lenderType; }
	 * 
	 * public void setLenderType(Integer lenderType) { this.lenderType = lenderType;
	 * }
	 */

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(String securityDetails) {
		this.securityDetails = securityDetails;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public DirectorBackgroundDetail getDirectorBackgroundDetail() {
		return directorBackgroundDetail;
	}

	public void setDirectorBackgroundDetail(DirectorBackgroundDetail directorBackgroundDetail) {
		this.directorBackgroundDetail = directorBackgroundDetail;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Integer getRelationshipSince() {
		return relationshipSince;
	}

	public void setRelationshipSince(Integer relationshipSince) {
		this.relationshipSince = relationshipSince;
	}

	public Integer getLcBgStatus() {
		return lcBgStatus;
	}

	public void setLcBgStatus(Integer lcBgStatus) {
		this.lcBgStatus = lcBgStatus;
	}

}