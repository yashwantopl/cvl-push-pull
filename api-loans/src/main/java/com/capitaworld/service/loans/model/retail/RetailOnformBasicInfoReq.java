package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

/**
 * @author harshit
 * 03/06/2019
 */
public class RetailOnformBasicInfoReq implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long applicationId;
    private Long coAppId;
    private Long userId;
    private Integer loanTypeId;
    private Integer titleId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer genderId;
    private String pan;
    private String aadharNumber;
    private String mobile;
    private Integer educationQualification;
    private Integer statusId;
    private Integer residenceType;
    private Date birthDate;
    private String contactNo;
    private String email;
    private String companyName;
    private Boolean isApplicantDetailsFilled;
    private Integer spouseEmployment;
    private Integer noOfDependent;
    private Integer category;
    private String fatherName;
    private Double annualIncomeOfSpouse;
    private String nationality;
    private Double networth;
    private Boolean isBasicInfoFilled;
    private Boolean isItrSkip;
    
    
    
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
	public Integer getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
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
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Integer getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Boolean getIsApplicantDetailsFilled() {
		return isApplicantDetailsFilled;
	}
	public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
		this.isApplicantDetailsFilled = isApplicantDetailsFilled;
	}
	public Integer getSpouseEmployment() {
		return spouseEmployment;
	}
	public void setSpouseEmployment(Integer spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}
	public Integer getNoOfDependent() {
		return noOfDependent;
	}
	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public Double getAnnualIncomeOfSpouse() {
		return annualIncomeOfSpouse;
	}
	public void setAnnualIncomeOfSpouse(Double annualIncomeOfSpouse) {
		this.annualIncomeOfSpouse = annualIncomeOfSpouse;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Double getNetworth() {
		return networth;
	}
	public void setNetworth(Double networth) {
		this.networth = networth;
	}
	public Boolean getIsBasicInfoFilled() {
		return isBasicInfoFilled;
	}
	public void setIsBasicInfoFilled(Boolean isBasicInfoFilled) {
		this.isBasicInfoFilled = isBasicInfoFilled;
	}
	public Boolean getIsItrSkip() {
		return isItrSkip;
	}
	public void setIsItrSkip(Boolean isItrSkip) {
		this.isItrSkip = isItrSkip;
	}
    


    
    
    
}
