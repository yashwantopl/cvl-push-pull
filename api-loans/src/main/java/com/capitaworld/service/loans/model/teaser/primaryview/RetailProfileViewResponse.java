package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.List;

import com.capitaworld.service.loans.model.AddressResponse;

/**
 * @author Sanket
 *
 */
public class RetailProfileViewResponse {

	private String firstName;
	private String lastName;
	private String middleName;
	private String title;
	private String gender;
	private String pan;
	private String aadharNumber;
	private String birthDate;
	private String maritalStatus;
	private String natureOfOccupation;
	private Integer natureOfOccupationId;
	private String occupation;
	private String monthlyIncome;
	private String companyName;
	private String employeeWith;
	private List<Object> panCardList;
	private List<Object> aadharCardList;
	private String age;
	private String relationshipWithApplicant;
	private String entityName;
	private String industryType;
	private String landSize;
	private String alliedActivity;
	private String contactNo;
	private String otherIncome;
	private String taxPaid;
	private String otherInvestment;
	private String bonusPerAnnum;
	private String incentivePerAnnum;

	//new requirement cw-384
	private String yearsInCurrentJob;
	private String monthsInCurrentJob;
	private String totalExperienceInYears;
	private String totalExperienceInMonths;
	private String previousExperienceInYears;
	private String previousExperienceInMonths;
	private String previousEmployerName;
	private String previousEmployerAddress;
	private String businessExperience;
	private String annualTurnover;
	private String monthlyLoanObligation;
	private String patPreviousYear;
	private String patCurrentYear;
	private String depreciationPreviousYear;
	private String depreciationCurrentYear;
	private String remunerationPreviousYear;
	private String remunerationCurrentYear;
	private AddressResponse firstAddress;
	private AddressResponse secondAddress;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNatureOfOccupation() {
		return natureOfOccupation;
	}

	public void setNatureOfOccupation(String natureOfOccupation) {
		this.natureOfOccupation = natureOfOccupation;
	}

	public Integer getNatureOfOccupationId() {
		return natureOfOccupationId;
	}

	public void setNatureOfOccupationId(Integer natureOfOccupationId) {
		this.natureOfOccupationId = natureOfOccupationId;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmployeeWith() {
		return employeeWith;
	}

	public void setEmployeeWith(String employeeWith) {
		this.employeeWith = employeeWith;
	}

	public List<Object> getPanCardList() {
		return panCardList;
	}

	public void setPanCardList(List<Object> panCardList) {
		this.panCardList = panCardList;
	}

	public List<Object> getAadharCardList() {
		return aadharCardList;
	}

	public void setAadharCardList(List<Object> aadharCardList) {
		this.aadharCardList = aadharCardList;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(String relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getLandSize() {
		return landSize;
	}

	public void setLandSize(String landSize) {
		this.landSize = landSize;
	}

	public String getAlliedActivity() {
		return alliedActivity;
	}

	public void setAlliedActivity(String alliedActivity) {
		this.alliedActivity = alliedActivity;
	}

	public String getYearsInCurrentJob() {
		return yearsInCurrentJob;
	}

	public void setYearsInCurrentJob(String yearsInCurrentJob) {
		this.yearsInCurrentJob = yearsInCurrentJob;
	}

	public String getMonthsInCurrentJob() {
		return monthsInCurrentJob;
	}

	public void setMonthsInCurrentJob(String monthsInCurrentJob) {
		this.monthsInCurrentJob = monthsInCurrentJob;
	}

	public String getTotalExperienceInYears() {
		return totalExperienceInYears;
	}

	public void setTotalExperienceInYears(String totalExperienceInYears) {
		this.totalExperienceInYears = totalExperienceInYears;
	}

	public String getTotalExperienceInMonths() {
		return totalExperienceInMonths;
	}

	public void setTotalExperienceInMonths(String totalExperienceInMonths) {
		this.totalExperienceInMonths = totalExperienceInMonths;
	}

	public String getPreviousExperienceInYears() {
		return previousExperienceInYears;
	}

	public void setPreviousExperienceInYears(String previousExperienceInYears) {
		this.previousExperienceInYears = previousExperienceInYears;
	}

	public String getPreviousExperienceInMonths() {
		return previousExperienceInMonths;
	}

	public void setPreviousExperienceInMonths(String previousExperienceInMonths) {
		this.previousExperienceInMonths = previousExperienceInMonths;
	}

	public String getPreviousEmployerName() {
		return previousEmployerName;
	}

	public void setPreviousEmployerName(String previousEmployerName) {
		this.previousEmployerName = previousEmployerName;
	}

	public String getPreviousEmployerAddress() {
		return previousEmployerAddress;
	}

	public void setPreviousEmployerAddress(String previousEmployerAddress) {
		this.previousEmployerAddress = previousEmployerAddress;
	}

	public String getBusinessExperience() {
		return businessExperience;
	}

	public void setBusinessExperience(String businessExperience) {
		this.businessExperience = businessExperience;
	}

	public String getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public String getMonthlyLoanObligation() {
		return monthlyLoanObligation;
	}

	public void setMonthlyLoanObligation(String monthlyLoanObligation) {
		this.monthlyLoanObligation = monthlyLoanObligation;
	}

	public String getPatPreviousYear() {
		return patPreviousYear;
	}

	public void setPatPreviousYear(String patPreviousYear) {
		this.patPreviousYear = patPreviousYear;
	}

	public String getPatCurrentYear() {
		return patCurrentYear;
	}

	public void setPatCurrentYear(String patCurrentYear) {
		this.patCurrentYear = patCurrentYear;
	}

	public String getDepreciationPreviousYear() {
		return depreciationPreviousYear;
	}

	public void setDepreciationPreviousYear(String depreciationPreviousYear) {
		this.depreciationPreviousYear = depreciationPreviousYear;
	}

	public String getDepreciationCurrentYear() {
		return depreciationCurrentYear;
	}

	public void setDepreciationCurrentYear(String depreciationCurrentYear) {
		this.depreciationCurrentYear = depreciationCurrentYear;
	}

	public String getRemunerationPreviousYear() {
		return remunerationPreviousYear;
	}

	public void setRemunerationPreviousYear(String remunerationPreviousYear) {
		this.remunerationPreviousYear = remunerationPreviousYear;
	}

	public String getRemunerationCurrentYear() {
		return remunerationCurrentYear;
	}

	public void setRemunerationCurrentYear(String remunerationCurrentYear) {
		this.remunerationCurrentYear = remunerationCurrentYear;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public AddressResponse getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(AddressResponse firstAddress) {
		this.firstAddress = firstAddress;
	}

	public AddressResponse getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(AddressResponse secondAddress) {
		this.secondAddress = secondAddress;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(String taxPaid) {
		this.taxPaid = taxPaid;
	}

	public String getOtherInvestment() {
		return otherInvestment;
	}

	public void setOtherInvestment(String otherInvestment) {
		this.otherInvestment = otherInvestment;
	}

	public String getBonusPerAnnum() {
		return bonusPerAnnum;
	}

	public void setBonusPerAnnum(String bonusPerAnnum) {
		this.bonusPerAnnum = bonusPerAnnum;
	}

	public String getIncentivePerAnnum() {
		return incentivePerAnnum;
	}

	public void setIncentivePerAnnum(String incentivePerAnnum) {
		this.incentivePerAnnum = incentivePerAnnum;
	}
	
}
