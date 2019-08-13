package com.capitaworld.service.loans.model.mfi;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fp_personal_loan_details database table.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFIFinancialArrangementRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7879231352352788315L;
	
	private Long id;
	private Long applicationId;
	private String financialInstitutionName;
	private String otherInstitutionName;
	private Double amount;
	private Date loanDate;
	private Date reportedDate;
	private String loanType;
	private Double emi;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Double outstandingAmount;
	private Boolean isManuallyAdded;
	private Double bureauOutstandingAmount;
	private Double bankerOutstandingAmount;
	private Double bureauOrCalculatedEmi;
	private Boolean isBureauEmi;
	private Boolean isActive;
	private Integer provider;
	private Integer isLoanConsidered;
	private Long applicantId;

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