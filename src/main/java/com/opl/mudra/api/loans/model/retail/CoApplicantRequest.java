package com.opl.mudra.api.loans.model.retail;

import com.opl.mudra.api.loans.model.Address;

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
	private Double grossMonthlyIncome;
	private Integer currencyId;
	private Address firstAddress;
	private Address secondAddress;
	private Boolean addressSameAs;
	private String contactNo;
	private String companyName;
	private Integer employedWithId;
	private Integer employmentType;
	private String employedWithOther;
	private String nameOfEntity;
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
	private Integer businessTypeId;
	private Integer productId;

	private Boolean isCoApp1DetailsFilled;
	private Boolean isCoApp2DetailsFilled;

	private String detailsFilledCount;

	private Integer businessStartYear;
	private Integer businessStartMonth;
	private Integer currentJobMonth;
	private Integer currentJobYear;
	private String previousEmployersAddress;
	private String previousEmployersName;
	private Integer previousJobMonth;
	private Integer previousJobYear;
	private Integer totalExperienceMonth;
	private Integer totalExperienceYear;

	//new requirement for SB
	private Integer modeOfReceipt;

	private Double monthlyLoanObligation;
	private Double patPreviousYear;
	private Double patCurrentYear;
	private Double depreciationPreviousYear;
	private Double depreciationCurrentYear;
	private Double remunerationPreviousYear;
	private Double remunerationCurrentYear;
	private Double annualTurnover;
	private Double loanAmountRequired;
	
	private Double bonusPerAnnum;
	private Double incentivePerAnnum;
	private Double otherIncome;
	private Double otherInvestment;
	private Double taxPaidLastYear;
	
	private Integer highestQualification;
	private String institute;
	private Integer qualifyingYear;
	private Integer qualifyingMonth;
	private Double residingYear;
	private Double residingMonth;
	private String spouseName;
	private Boolean isSpouseEmployed;
	private Boolean isBankStatementCompleted;
	
	private Boolean addressSameAsApplicant;
	private String fatherName;
	private String mobile;
	private Integer category;
	private Integer nationality;
	private Double networth;
	private Boolean isItrCompleted;
	private Boolean isItrSkip;
	private Boolean isItrManual;
	private Boolean isOneFormCompleted;
	private Boolean isBasicInfoFilled;
	private Boolean isEmploymentInfoFilled;
	private Boolean isContactInfoFilled;
	private Boolean isCreditInfoFilled;
	private Boolean isCibilCompleted;
	private Integer designation;
	private Integer educationQualification;
	private Boolean isIncomeConsider;
	private Boolean isOwnedProp;
	private String nameEditedByCoApp;
	
	
	

	public String getNameEditedByCoApp() {
		return nameEditedByCoApp;
	}

	public void setNameEditedByCoApp(String nameEditedByCoApp) {
		this.nameEditedByCoApp = nameEditedByCoApp;
	}

	public Boolean getIsOwnedProp() {
		return isOwnedProp;
	}

	public void setIsOwnedProp(Boolean isOwnedProp) {
		this.isOwnedProp = isOwnedProp;
	}

	public CoApplicantRequest() {
	}

	public CoApplicantRequest(Long id,String firstName, String middleName, String lastName, Integer relationshipWithApplicant, Long applicationId,Boolean isItrCompleted,Boolean isItrSkip,Boolean isItrManual,Boolean isCibilCompleted,Boolean isBankStatementCompleted,Boolean isOneFormCompleted,Boolean isBasicInfoFilled,Boolean isEmploymentInfoFilled,Boolean isContactInfoFilled,Boolean isCreditInfoFilled) {
		new CoApplicantRequest(id, firstName, middleName, lastName, relationshipWithApplicant, applicationId, isItrCompleted, isItrSkip, isItrManual, isCibilCompleted, isBankStatementCompleted, isOneFormCompleted, isBasicInfoFilled, isEmploymentInfoFilled, isContactInfoFilled, isCreditInfoFilled, null,null);
	}
	
	public CoApplicantRequest(Long id,String firstName, String middleName, String lastName, Integer relationshipWithApplicant, Long applicationId,Boolean isItrCompleted,Boolean isItrSkip,Boolean isItrManual,Boolean isCibilCompleted,Boolean isBankStatementCompleted,Boolean isOneFormCompleted,Boolean isBasicInfoFilled,Boolean isEmploymentInfoFilled,Boolean isContactInfoFilled,Boolean isCreditInfoFilled,String pan,Boolean isIncomeConsider) {
		this.id = id;
		this.applicationId = applicationId;
		this.firstName = firstName;
		this.relationshipWithApplicant = relationshipWithApplicant;
		this.middleName = middleName;
		this.lastName = lastName;
		this.isItrCompleted = isItrCompleted;
		this.isItrSkip = isItrSkip;
		this.isItrManual = isItrManual;
		this.isCibilCompleted = isCibilCompleted;
		this.isBankStatementCompleted = isBankStatementCompleted;
		this.isOneFormCompleted = isOneFormCompleted;
		this.isBasicInfoFilled = isBasicInfoFilled;
		this.isEmploymentInfoFilled = isEmploymentInfoFilled;
		this.isContactInfoFilled = isContactInfoFilled;
		this.isCreditInfoFilled = isCreditInfoFilled;
		this.pan = pan;
		this.isIncomeConsider = isIncomeConsider;
	}

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

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
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

	

	public Integer getBusinessStartYear() {
		return businessStartYear;
	}

	public void setBusinessStartYear(Integer businessStartYear) {
		this.businessStartYear = businessStartYear;
	}

	public Integer getBusinessStartMonth() {
		return businessStartMonth;
	}

	public void setBusinessStartMonth(Integer businessStartMonth) {
		this.businessStartMonth = businessStartMonth;
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

	
	public Integer getQualifyingYear() {
		return qualifyingYear;
	}

	public void setQualifyingYear(Integer qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public Integer getQualifyingMonth() {
		return qualifyingMonth;
	}

	public void setQualifyingMonth(Integer qualifyingMonth) {
		this.qualifyingMonth = qualifyingMonth;
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

	public Boolean getAddressSameAsApplicant() {
		return addressSameAsApplicant;
	}

	public void setAddressSameAsApplicant(Boolean addressSameAsApplicant) {
		this.addressSameAsApplicant = addressSameAsApplicant;
	}

	public Integer getModeOfReceipt() {
		return modeOfReceipt;
	}

	public void setModeOfReceipt(Integer modeOfReceipt) {
		this.modeOfReceipt = modeOfReceipt;
	}

	public Boolean getIsBankStatementCompleted() {
		return isBankStatementCompleted;
	}

	public void setIsBankStatementCompleted(Boolean isBankStatementCompleted) {
		this.isBankStatementCompleted = isBankStatementCompleted;
	}
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}


	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Double getLoanAmountRequired() {
		return loanAmountRequired;
	}

	public void setLoanAmountRequired(Double loanAmountRequired) {
		this.loanAmountRequired = loanAmountRequired;
	}

	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	public Integer getEducationQualification() {
		return educationQualification;
	}

	public void setEducationQualification(Integer educationQualification) {
		this.educationQualification = educationQualification;
	}



	public Boolean getIsItrCompleted() {
		return isItrCompleted;
	}

	public void setIsItrCompleted(Boolean isItrCompleted) {
		this.isItrCompleted = isItrCompleted;
	}

	public Boolean getIsItrSkip() {
		return isItrSkip;
	}

	public void setIsItrSkip(Boolean isItrSkip) {
		this.isItrSkip = isItrSkip;
	}

	public Boolean getIsItrManual() {
		return isItrManual;
	}

	public void setIsItrManual(Boolean isItrManual) {
		this.isItrManual = isItrManual;
	}

	public Boolean getIsOneFormCompleted() {
		return isOneFormCompleted;
	}

	public void setIsOneFormCompleted(Boolean isOneFormCompleted) {
		this.isOneFormCompleted = isOneFormCompleted;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Boolean getIsBasicInfoFilled() {
		return isBasicInfoFilled;
	}

	public void setIsBasicInfoFilled(Boolean isBasicInfoFilled) {
		this.isBasicInfoFilled = isBasicInfoFilled;
	}

	public Boolean getIsEmploymentInfoFilled() {
		return isEmploymentInfoFilled;
	}

	public void setIsEmploymentInfoFilled(Boolean isEmploymentInfoFilled) {
		this.isEmploymentInfoFilled = isEmploymentInfoFilled;
	}

	public Boolean getIsContactInfoFilled() {
		return isContactInfoFilled;
	}

	public void setIsContactInfoFilled(Boolean isContactInfoFilled) {
		this.isContactInfoFilled = isContactInfoFilled;
	}

	public Boolean getIsCreditInfoFilled() {
		return isCreditInfoFilled;
	}

	public void setIsCreditInfoFilled(Boolean isCreditInfoFilled) {
		this.isCreditInfoFilled = isCreditInfoFilled;
	}

	public Boolean getIsCibilCompleted() {
		return isCibilCompleted;
	}

	public void setIsCibilCompleted(Boolean isCibilCompleted) {
		this.isCibilCompleted = isCibilCompleted;
	}

	public Boolean getIsIncomeConsider() {
		return isIncomeConsider;
	}

	public void setIsIncomeConsider(Boolean isIncomeConsider) {
		this.isIncomeConsider = isIncomeConsider;
	}
		public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}
}
