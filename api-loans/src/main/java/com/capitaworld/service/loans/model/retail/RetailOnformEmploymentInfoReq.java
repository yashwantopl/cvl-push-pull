package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

/**
 * @author harshit
 * 03/06/2019
 */
public class RetailOnformEmploymentInfoReq implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long clientId;
    private Long applicationId;
    private Boolean isEmploymentInfoFilled;
    private Long coAppId;
    private Long userId;
    private Integer employmentType;
    private Integer employmentWith;
    private Integer centralGovId;
    private Integer stateGovId;
    private Integer psuId;
    private Integer corporateId;
    private Integer eduInstId;
    private Integer bankNameId;
    private Integer insuranceNameId;
    private String nameOfEmployer;
    private Integer employmentStatus;
    private String companyName;
    private Integer designation;
    private Double grossMonthlyIncome;
    private Double monthlyIncome;
    private String loanPurposeOther;
    private Long keyVerticalFunding;
    private Long keyVerticalSector;
    private Long keyVerticalSubSector;
    private Integer businessStartMonth;
    private Integer businessStartYear;
    private Integer salaryMode;
    private Integer currentJobMonth;
    private Integer currentJobYear;
    private Integer totalExperienceMonth;
    private Integer totalExperienceYear;
    private String employedWithOther;
    private Date birthDate;
    
    
    
    
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Boolean getIsEmploymentInfoFilled() {
		return isEmploymentInfoFilled;
	}
	public void setIsEmploymentInfoFilled(Boolean isEmploymentInfoFilled) {
		this.isEmploymentInfoFilled = isEmploymentInfoFilled;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}
	public Integer getEmploymentWith() {
		return employmentWith;
	}
	public void setEmploymentWith(Integer employmentWith) {
		this.employmentWith = employmentWith;
	}
	public Integer getCentralGovId() {
		return centralGovId;
	}
	public void setCentralGovId(Integer centralGovId) {
		this.centralGovId = centralGovId;
	}
	public Integer getStateGovId() {
		return stateGovId;
	}
	public void setStateGovId(Integer stateGovId) {
		this.stateGovId = stateGovId;
	}
	public Integer getPsuId() {
		return psuId;
	}
	public void setPsuId(Integer psuId) {
		this.psuId = psuId;
	}
	public Integer getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(Integer corporateId) {
		this.corporateId = corporateId;
	}
	public Integer getEduInstId() {
		return eduInstId;
	}
	public void setEduInstId(Integer eduInstId) {
		this.eduInstId = eduInstId;
	}
	public Integer getBankNameId() {
		return bankNameId;
	}
	public void setBankNameId(Integer bankNameId) {
		this.bankNameId = bankNameId;
	}
	public Integer getInsuranceNameId() {
		return insuranceNameId;
	}
	public void setInsuranceNameId(Integer insuranceNameId) {
		this.insuranceNameId = insuranceNameId;
	}
	public String getNameOfEmployer() {
		return nameOfEmployer;
	}
	public void setNameOfEmployer(String nameOfEmployer) {
		this.nameOfEmployer = nameOfEmployer;
	}
	public Integer getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getDesignation() {
		return designation;
	}
	public void setDesignation(Integer designation) {
		this.designation = designation;
	}
	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}
	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}
	public Double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}
	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}
	public Long getKeyVerticalFunding() {
		return keyVerticalFunding;
	}
	public void setKeyVerticalFunding(Long keyVerticalFunding) {
		this.keyVerticalFunding = keyVerticalFunding;
	}
	public Long getKeyVerticalSector() {
		return keyVerticalSector;
	}
	public void setKeyVerticalSector(Long keyVerticalSector) {
		this.keyVerticalSector = keyVerticalSector;
	}
	public Long getKeyVerticalSubSector() {
		return keyVerticalSubSector;
	}
	public void setKeyVerticalSubSector(Long keyVerticalSubSector) {
		this.keyVerticalSubSector = keyVerticalSubSector;
	}
	public Integer getBusinessStartMonth() {
		return businessStartMonth;
	}
	public void setBusinessStartMonth(Integer businessStartMonth) {
		this.businessStartMonth = businessStartMonth;
	}
	public Integer getBusinessStartYear() {
		return businessStartYear;
	}
	public void setBusinessStartYear(Integer businessStartYear) {
		this.businessStartYear = businessStartYear;
	}
	public Integer getSalaryMode() {
		return salaryMode;
	}
	public void setSalaryMode(Integer salaryMode) {
		this.salaryMode = salaryMode;
	}
	public Integer getCurrentJobMonth() {
		return currentJobMonth;
	}
	public void setCurrentJobMonth(Integer currentJobMonth) {
		this.currentJobMonth = currentJobMonth;
	}
	public Integer getCurrentJobYear() {
		return currentJobYear;
	}
	public void setCurrentJobYear(Integer currentJobYear) {
		this.currentJobYear = currentJobYear;
	}
	public Integer getTotalExperienceMonth() {
		return totalExperienceMonth;
	}
	public void setTotalExperienceMonth(Integer totalExperienceMonth) {
		this.totalExperienceMonth = totalExperienceMonth;
	}
	public Integer getTotalExperienceYear() {
		return totalExperienceYear;
	}
	public void setTotalExperienceYear(Integer totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}
	public String getEmployedWithOther() {
		return employedWithOther;
	}
	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}
    
    
    
    
}
