package com.capitaworld.service.loans.model.retail;

import java.util.Date;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CoApplicantRequest {

	private Long id;
	private Long clientId;
	private Long applicationId;
	private String firstName;
	private Integer relationshipWithApplicant;
	private Boolean isActive;
	private Integer titleId;
	private String middleName;
	private String lastName;
	private Integer statusId;
	private Integer occupationId;
	private String pan;
	private String aadharNumber;
	private String nameAsPerAadharCard;
	private Double monthlyIncome;
	private Integer currencyId;
	private Address firstAddress;
	private Address secondAddress;
	private Boolean addressSameAs;
	private String contactNo;
	private String companyName;
	private Integer employedWithId;
	private String employedWithOther;
	private String entityName;
	private Integer industryTypeId;
	private String industryTypeOther;
	private Integer selfEmployedOccupationId;
	private String selfEmployedOccupationOther;
	private Double landSize;
	private Integer alliedActivityId;

	private Integer genderId;
	private Integer date;
	private Integer month;
	private Integer year;

	private Boolean isCoApp1DetailsFilled;
	private Boolean isCoApp2DetailsFilled;

	private String detailsFilledCount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date businessStartDate;
	private Integer currentJobMonth;
	private Integer currentJobYear;
	private String previousEmployersAddress;
	private String previousEmployersName;
	private Integer previousJobMonth;
	private Integer previousJobYear;
	private Integer totalExperienceMonth;
	private Integer totalExperienceYear;
	
	private Double monthlyLoanObligation;
	private Double patPreviousYear;
	private Double patCurrentYear;
	private Double depreciationPreviousYear;
	private Double depreciationCurrentYear;
	private Double remunerationPreviousYear;
	private Double remunerationCurrentYear;
	private Double annualTurnover;
	
	private Double bonusPerAnnum;
	private Double incentivePerAnnum;
	private Double otherIncome;
	private Double otherInvestment;
	private Double taxPaidLastYear;
	
	private Integer highestQualification;
	private String institute;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date qualifyingYear;
	private Double residingYear;
	private Double residingMonth;
	private String spouseName;
	private Boolean isSpouseEmployed;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(Integer relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getNameAsPerAadharCard() {
		return nameAsPerAadharCard;
	}

	public void setNameAsPerAadharCard(String nameAsPerAadharCard) {
		this.nameAsPerAadharCard = nameAsPerAadharCard;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Address getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}

	public Address getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(Address secondAddress) {
		this.secondAddress = secondAddress;
	}

	public Boolean getAddressSameAs() {
		return addressSameAs;
	}

	public void setAddressSameAs(Boolean addressSameAs) {
		this.addressSameAs = addressSameAs;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEmployedWithId() {
		return employedWithId;
	}

	public void setEmployedWithId(Integer employedWithId) {
		this.employedWithId = employedWithId;
	}

	public String getEmployedWithOther() {
		return employedWithOther;
	}

	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeOther() {
		return industryTypeOther;
	}

	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}

	public Integer getSelfEmployedOccupationId() {
		return selfEmployedOccupationId;
	}

	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}

	public String getSelfEmployedOccupationOther() {
		return selfEmployedOccupationOther;
	}

	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}

	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}

	public Integer getAlliedActivityId() {
		return alliedActivityId;
	}

	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getIsCoApp1DetailsFilled() {
		return isCoApp1DetailsFilled;
	}

	public void setIsCoApp1DetailsFilled(Boolean isCoApp1DetailsFilled) {
		this.isCoApp1DetailsFilled = isCoApp1DetailsFilled;
	}

	public Boolean getIsCoApp2DetailsFilled() {
		return isCoApp2DetailsFilled;
	}

	public void setIsCoApp2DetailsFilled(Boolean isCoApp2DetailsFilled) {
		this.isCoApp2DetailsFilled = isCoApp2DetailsFilled;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
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

	public String getPreviousEmployersAddress() {
		return previousEmployersAddress;
	}

	public void setPreviousEmployersAddress(String previousEmployersAddress) {
		this.previousEmployersAddress = previousEmployersAddress;
	}

	public String getPreviousEmployersName() {
		return previousEmployersName;
	}

	public void setPreviousEmployersName(String previousEmployersName) {
		this.previousEmployersName = previousEmployersName;
	}

	public Integer getPreviousJobMonth() {
		return previousJobMonth;
	}

	public void setPreviousJobMonth(Integer previousJobMonth) {
		this.previousJobMonth = previousJobMonth;
	}

	public Integer getPreviousJobYear() {
		return previousJobYear;
	}

	public void setPreviousJobYear(Integer previousJobYear) {
		this.previousJobYear = previousJobYear;
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

	public Double getMonthlyLoanObligation() {
		return monthlyLoanObligation;
	}

	public void setMonthlyLoanObligation(Double monthlyLoanObligation) {
		this.monthlyLoanObligation = monthlyLoanObligation;
	}

	public Double getPatPreviousYear() {
		return patPreviousYear;
	}

	public void setPatPreviousYear(Double patPreviousYear) {
		this.patPreviousYear = patPreviousYear;
	}

	public Double getPatCurrentYear() {
		return patCurrentYear;
	}

	public void setPatCurrentYear(Double patCurrentYear) {
		this.patCurrentYear = patCurrentYear;
	}

	public Double getDepreciationPreviousYear() {
		return depreciationPreviousYear;
	}

	public void setDepreciationPreviousYear(Double depreciationPreviousYear) {
		this.depreciationPreviousYear = depreciationPreviousYear;
	}

	public Double getDepreciationCurrentYear() {
		return depreciationCurrentYear;
	}

	public void setDepreciationCurrentYear(Double depreciationCurrentYear) {
		this.depreciationCurrentYear = depreciationCurrentYear;
	}

	public Double getRemunerationPreviousYear() {
		return remunerationPreviousYear;
	}

	public void setRemunerationPreviousYear(Double remunerationPreviousYear) {
		this.remunerationPreviousYear = remunerationPreviousYear;
	}

	public Double getRemunerationCurrentYear() {
		return remunerationCurrentYear;
	}

	public void setRemunerationCurrentYear(Double remunerationCurrentYear) {
		this.remunerationCurrentYear = remunerationCurrentYear;
	}

	public Double getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAnnualTurnover(Double annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public Double getBonusPerAnnum() {
		return bonusPerAnnum;
	}

	public void setBonusPerAnnum(Double bonusPerAnnum) {
		this.bonusPerAnnum = bonusPerAnnum;
	}

	public Double getIncentivePerAnnum() {
		return incentivePerAnnum;
	}

	public void setIncentivePerAnnum(Double incentivePerAnnum) {
		this.incentivePerAnnum = incentivePerAnnum;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getOtherInvestment() {
		return otherInvestment;
	}

	public void setOtherInvestment(Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}

	public Double getTaxPaidLastYear() {
		return taxPaidLastYear;
	}

	public void setTaxPaidLastYear(Double taxPaidLastYear) {
		this.taxPaidLastYear = taxPaidLastYear;
	}

	public Integer getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(Integer highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Date getQualifyingYear() {
		return qualifyingYear;
	}

	public void setQualifyingYear(Date qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public Double getResidingYear() {
		return residingYear;
	}

	public void setResidingYear(Double residingYear) {
		this.residingYear = residingYear;
	}

	public Double getResidingMonth() {
		return residingMonth;
	}

	public void setResidingMonth(Double residingMonth) {
		this.residingMonth = residingMonth;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Boolean getIsSpouseEmployed() {
		return isSpouseEmployed;
	}

	public void setIsSpouseEmployed(Boolean isSpouseEmployed) {
		this.isSpouseEmployed = isSpouseEmployed;
	}
	

	

}
