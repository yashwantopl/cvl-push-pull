package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.model.Address;

/**
 * The persistent class for the fs_retail_applicant_details database table.
 * 
 */
public class RetailApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	// Common Fields
	private Long id;
	private Long clientId;
	private Long applicationId;
	private Long userId;

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
	private Double grossIncome;
	private Address firstAddress;
	private Address secondAddress;
	private Boolean addressSameAs;
	private String contactNo;
	private List<CoApplicantRequest> coApplicants = Collections.emptyList();
	private List<GuarantorRequest> guarantors = Collections.emptyList();
	private String companyName;
	private Integer employmentType;
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
	private Date dob;

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
	private Integer businessTypeId;
	private Integer businessStartMonth;
	private Integer currentJobMonth;
	private Integer currentJobYear;
	private String previousEmployersAddress;
	private String previousEmployersName;
	private Integer previousJobMonth;
	private Integer previousJobYear;
	private Integer totalExperienceMonth;
	private Integer totalExperienceYear;
	private Integer designation;

	
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
	
	private String addressPremiseName;
	private String addressStreetName;
	private String addressLandmark;
	private Long addressCity;
	private Long addressState;
	private Integer addressCountry;
	private Double loanAmountRequired;

	private String email;
	private String lanLineNo;
	private Integer residenceType;
	private Integer residenceSinceYear;
	private Integer residenceSinceMonth;
	private Double networth;
	private String nationality;

	private List<RetailApplicantIncomeRequest> incomeDetailsList;
	

	public RetailApplicantRequest() {
		// Do nothing because of X and Y.
	}

	
	public RetailApplicantRequest(Long id) {
		super();
		this.id = id;
	}

	
	public Long getId() {
		return id;
	}

	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public List<CoApplicantRequest> getCoApplicants() {
		return coApplicants;
	}

	public void setCoApplicants(List<CoApplicantRequest> coApplicants) {
		this.coApplicants = coApplicants;
	}

	public List<GuarantorRequest> getGuarantors() {
		return guarantors;
	}

	public void setGuarantors(List<GuarantorRequest> guarantors) {
		this.guarantors = guarantors;
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

	public Boolean getIsApplicantDetailsFilled() {
		return isApplicantDetailsFilled;
	}

	public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
		this.isApplicantDetailsFilled = isApplicantDetailsFilled;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
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

	public Integer getModeOfReceipt() {
		return modeOfReceipt;
	}

	public void setModeOfReceipt(Integer modeOfReceipt) {
		this.modeOfReceipt = modeOfReceipt;
	}

	public List<RetailApplicantIncomeRequest> getIncomeDetailsList() {
		return incomeDetailsList;
	}

	public void setIncomeDetailsList(List<RetailApplicantIncomeRequest> incomeDetailsList) {
		this.incomeDetailsList = incomeDetailsList;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getAddressPremiseName() {
		return addressPremiseName;
	}


	public void setAddressPremiseName(String addressPremiseName) {
		this.addressPremiseName = addressPremiseName;
	}


	public String getAddressStreetName() {
		return addressStreetName;
	}


	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}


	public String getAddressLandmark() {
		return addressLandmark;
	}


	public void setAddressLandmark(String addressLandmark) {
		this.addressLandmark = addressLandmark;
	}


	public Long getAddressCity() {
		return addressCity;
	}


	public void setAddressCity(Long addressCity) {
		this.addressCity = addressCity;
	}


	public Long getAddressState() {
		return addressState;
	}


	public void setAddressState(Long addressState) {
		this.addressState = addressState;
	}


	public Integer getAddressCountry() {
		return addressCountry;
	}


	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Double getLoanAmountRequired() {
		return loanAmountRequired;
	}


	public void setLoanAmountRequired(Double loanAmountRequired) {
		this.loanAmountRequired = loanAmountRequired;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLanLineNo() {
		return lanLineNo;
	}


	public void setLanLineNo(String lanLineNo) {
		this.lanLineNo = lanLineNo;
	}


	public Double getGrossIncome() {
		return grossIncome;
	}


	public void setGrossIncome(Double grossIncome) {
		this.grossIncome = grossIncome;
	}


	public Integer getBusinessTypeId() {
		return businessTypeId;
	}


	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}

	public Integer getResidenceSinceYear() {
		return residenceSinceYear;
	}

	public void setResidenceSinceYear(Integer residenceSinceYear) {
		this.residenceSinceYear = residenceSinceYear;
	}

	public Integer getResidenceSinceMonth() {
		return residenceSinceMonth;
	}

	public void setResidenceSinceMonth(Integer residenceSinceMonth) {
		this.residenceSinceMonth = residenceSinceMonth;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}
}