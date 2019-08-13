package com.capitaworld.service.loans.domain.fundseeker.mfi;

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

@Entity
@Table(name = "fs_mfi_current_financial_arrangements_details")
public class MfiFinancialArrangementsDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;
	
	@Column(name = "financial_institution_name")
	private String financialInstitutionName;
	
	@Column(name = "other_institution_name")
	private String otherInstitutionName;

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

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "outstanding_amount")
	private Double outstandingAmount;

	@Column(name = "is_manually_added")
	private Boolean isManuallyAdded;
	
	@Column(name = "bureau_outstanding_amount")
	private Double bureauOutstandingAmount;
	
	@Column(name = "banker_outstanding_amount")
	private Double bankerOutstandingAmount;
	
	@Column(name = "bureau_or_calculated_emi")
	private Double bureauOrCalculatedEmi;
	
	@Column(name = "is_bureau_emi")
	private Boolean isBureauEmi;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "provider")
	private Integer provider;
	
	@Column(name = "is_loan_considered")
	private Integer isLoanConsidered;
	
	@Column(name = "applicant_id")
	private Long applicantId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public String getFinancialInstitutionName() {
		return financialInstitutionName;
	}

	public void setFinancialInstitutionName(String financialInstitutionName) {
		this.financialInstitutionName = financialInstitutionName;
	}

	public String getOtherInstitutionName() {
		return otherInstitutionName;
	}

	public void setOtherInstitutionName(String otherInstitutionName) {
		this.otherInstitutionName = otherInstitutionName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Boolean getIsManuallyAdded() {
		return isManuallyAdded;
	}

	public void setIsManuallyAdded(Boolean isManuallyAdded) {
		this.isManuallyAdded = isManuallyAdded;
	}

	public Double getBureauOutstandingAmount() {
		return bureauOutstandingAmount;
	}

	public void setBureauOutstandingAmount(Double bureauOutstandingAmount) {
		this.bureauOutstandingAmount = bureauOutstandingAmount;
	}

	public Double getBankerOutstandingAmount() {
		return bankerOutstandingAmount;
	}

	public void setBankerOutstandingAmount(Double bankerOutstandingAmount) {
		this.bankerOutstandingAmount = bankerOutstandingAmount;
	}

	public Double getBureauOrCalculatedEmi() {
		return bureauOrCalculatedEmi;
	}

	public void setBureauOrCalculatedEmi(Double bureauOrCalculatedEmi) {
		this.bureauOrCalculatedEmi = bureauOrCalculatedEmi;
	}

	public Boolean getIsBureauEmi() {
		return isBureauEmi;
	}

	public void setIsBureauEmi(Boolean isBureauEmi) {
		this.isBureauEmi = isBureauEmi;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public Integer getIsLoanConsidered() {
		return isLoanConsidered;
	}

	public void setIsLoanConsidered(Integer isLoanConsidered) {
		this.isLoanConsidered = isLoanConsidered;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

}