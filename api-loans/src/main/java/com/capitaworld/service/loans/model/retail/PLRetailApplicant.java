package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;

import java.io.Serializable;

public class PLRetailApplicant implements Serializable {
    // Common Fields
    private Long id;
    private Long clientId;
    private Long applicationId;

    // Primary Fields
    private Integer titleId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer statusId;
    private Integer occupationId;
    private String pan;
    private String aadharNumber;
    private String nameAsPerAadharCard;
    private Double monthlyIncome;
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
    private Integer currencyId;
    private Integer genderId;
    private Integer date;
    private Integer month;
    private Integer year;

    //new requirement for SB
    private Integer modeOfReceipt;
    //new requirement cw-384
    private Double monthlyLoanObligation;
    private Double patPreviousYear;
    private Double patCurrentYear;
    private Double depreciationPreviousYear;
    private Double depreciationCurrentYear;
    private Double remunerationPreviousYear;
    private Double remunerationCurrentYear;
    private Double annualTurnover;

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


    private Boolean isApplicantDetailsFilled;
    private String detailsFilledCount;

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
    private Integer educationQualification;
    private Integer employmentType;
    private String nameOfEmployer;
    private Integer totalExperience;
    private Integer experienceInPresentJob;
    private Integer employmentStatus;
    private Long keyVerticalFunding;
    private Long keyVerticalSector;
    private Long keyVerticalSubSector;
    private String email;
    private String mobile;
    private Long permanentdistrictMappingId;
    private Long addressDistrictMappingId;
    private Integer employmentWith;
    private Integer centralGovId;
    private Integer stateGovId;
    private Integer psuId;
    private Integer corporateId;
    private Integer eduInstId;
    private Double loanAmountRequired;
    private Integer loanPurpose;
    private String loanPurposeOther;
    private Integer tenureRequired;
    private Integer repayment;

    public PLRetailApplicant() {

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

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
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

    public Integer getModeOfReceipt() {
        return modeOfReceipt;
    }

    public void setModeOfReceipt(Integer modeOfReceipt) {
        this.modeOfReceipt = modeOfReceipt;
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

    public Boolean getIsApplicantDetailsFilled() {
        return isApplicantDetailsFilled;
    }

    public void setIsApplicantDetailsFilled(Boolean applicantDetailsFilled) {
        isApplicantDetailsFilled = applicantDetailsFilled;
    }

    public String getDetailsFilledCount() {
        return detailsFilledCount;
    }

    public void setDetailsFilledCount(String detailsFilledCount) {
        this.detailsFilledCount = detailsFilledCount;
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

    public Boolean getSpouseEmployed() {
        return isSpouseEmployed;
    }

    public void setSpouseEmployed(Boolean spouseEmployed) {
        isSpouseEmployed = spouseEmployed;
    }

    public Integer getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(Integer educationQualification) {
        this.educationQualification = educationQualification;
    }

    public Integer getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Integer employmentType) {
        this.employmentType = employmentType;
    }

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

    public Integer getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Integer totalExperience) {
        this.totalExperience = totalExperience;
    }

    public Integer getExperienceInPresentJob() {
        return experienceInPresentJob;
    }

    public void setExperienceInPresentJob(Integer experienceInPresentJob) {
        this.experienceInPresentJob = experienceInPresentJob;
    }

    public Integer getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Integer employmentStatus) {
        this.employmentStatus = employmentStatus;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getPermanentdistrictMappingId() {
        return permanentdistrictMappingId;
    }

    public void setPermanentdistrictMappingId(Long permanentdistrictMappingId) {
        this.permanentdistrictMappingId = permanentdistrictMappingId;
    }

    public Long getAddressDistrictMappingId() {
        return addressDistrictMappingId;
    }

    public void setAddressDistrictMappingId(Long addressDistrictMappingId) {
        this.addressDistrictMappingId = addressDistrictMappingId;
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

    public Double getLoanAmountRequired() {
        return loanAmountRequired;
    }

    public void setLoanAmountRequired(Double loanAmountRequired) {
        this.loanAmountRequired = loanAmountRequired;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLoanPurposeOther() {
        return loanPurposeOther;
    }

    public void setLoanPurposeOther(String loanPurposeOther) {
        this.loanPurposeOther = loanPurposeOther;
    }

    public Integer getTenureRequired() {
        return tenureRequired;
    }

    public void setTenureRequired(Integer tenureRequired) {
        this.tenureRequired = tenureRequired;
    }

    public Integer getRepayment() {
        return repayment;
    }

    public void setRepayment(Integer repayment) {
        this.repayment = repayment;
    }
}
