package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HLOneformRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	//================ START RETAILS APPLICANT DETAILS ========================
	private Long applicationId;
	private Long coAppId;
	private Long userId;
	private Integer titleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	private String pan;
	private Integer genderId;
	private Integer category;
	private String mobile;
	private Date birthDate;
	private Integer residenceType;
	private Integer residenceSinceYear;
	private Integer residentialStatus;
	private Double networth;
	
	//======CONTACT DETAILS
	private String addressPremiseName;
	private String addressStreetName;
	private String addressLandmark;
	private Long addressDistrictMappingId;
	private Long addressCity;
	private Long addressState;
	private Integer addressCountry;
	private Long addressPincode;
	
	//======EMPLOYMENT DETAILS  
	private Integer employmentType;
	private Integer employmentStatus;
	private String employedWithOther;
	private String nameOfEmployer;
	private Integer currentEmploymentStatus;
	private Integer currentJobMonth;
	private Integer currentJobYear;
	private Integer totalExperienceMonth;
	private Integer totalExperienceYear;
	private Date businessStartDate;
	private Double grossMonthlyIncome;
	private Double monthlyIncome;
	
	//======OTHER INFORMATION
	private Integer educationQualification;
	private Integer statusId;
	private Integer spouseEmployment;
	private Double annualIncomeOfSpouse;
	private Integer noOfDependent;
	//================ END RETAILS APPLICANT DETAILS ========================
	
	//CO-APPLICANT 
	private String nameOfEntity;
	
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
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
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public Integer getResidentialStatus() {
		return residentialStatus;
	}
	public void setResidentialStatus(Integer residentialStatus) {
		this.residentialStatus = residentialStatus;
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
	public Long getAddressDistrictMappingId() {
		return addressDistrictMappingId;
	}
	public void setAddressDistrictMappingId(Long addressDistrictMappingId) {
		this.addressDistrictMappingId = addressDistrictMappingId;
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
	public Long getAddressPincode() {
		return addressPincode;
	}
	public void setAddressPincode(Long addressPincode) {
		this.addressPincode = addressPincode;
	}
	public Integer getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}
	public Integer getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
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
	public Date getBusinessStartDate() {
		return businessStartDate;
	}
	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}
	public Double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public Integer getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(Integer educationQualification) {
		this.educationQualification = educationQualification;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	public Integer getNoOfDependent() {
		return noOfDependent;
	}
	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}
	public String getEmployedWithOther() {
		return employedWithOther;
	}
	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}
	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}
	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
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
	public Double getAnnualIncomeOfSpouse() {
		return annualIncomeOfSpouse;
	}
	public void setAnnualIncomeOfSpouse(Double annualIncomeOfSpouse) {
		this.annualIncomeOfSpouse = annualIncomeOfSpouse;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Double getNetworth() {
		return networth;
	}
	public void setNetworth(Double networth) {
		this.networth = networth;
	}
	public String getNameOfEmployer() {
		return nameOfEmployer;
	}
	public void setNameOfEmployer(String nameOfEmployer) {
		this.nameOfEmployer = nameOfEmployer;
	}
	public Integer getCurrentEmploymentStatus() {
		return currentEmploymentStatus;
	}
	public void setCurrentEmploymentStatus(Integer currentEmploymentStatus) {
		this.currentEmploymentStatus = currentEmploymentStatus;
	}
	public Integer getSpouseEmployment() {
		return spouseEmployment;
	}
	public void setSpouseEmployment(Integer spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}
	public String getNameOfEntity() {
		return nameOfEntity;
	}
	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	

}
