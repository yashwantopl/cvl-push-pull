package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailResponseString implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String address;

	private String networth;

	private Long applicationId;

	private Integer salutationId;
	
	private String din;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date appointmentDate;
	
	private String designation;

	private String panNo;

	private String directorsName;

	private String totalExperience;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dob;

	private String mobile;
	
	private String pincode;
	
	private String stateCode;
	
	private String city;
	
	private String gender;

	private String relationshipType;
	
	private String cibilScore;
	
	private String experianScore;
	
	private String highmarkScore;

	private String shareholding;

	private String aadhar;

	private String maritalStatus;

	private String noOfDependent;

	private String residenceType;

	private String residenceSince;

	private String isFamilyMemberInBusiness;

	private Long empDetailId;

	private String country;

	private String premiseNumber;

	private String streetName;

	private String landmark;
	
	private Boolean isMainDirector;
	
	private String firstName;

	private String lastName;

	private String middleName;

	private String title;
	
	private String qualification;
	
	private String directorBankName;
	
	private String directorBankAccount;
	
	private BigInteger timeAtAddress;
	
	private Long districtMappingId;
	
	private Object pinData;
	
	private Object personalId;
	
	private Object directorPersonalInfo;
	
	private String fatherName;
	private String educationalStatus;
	private Object nationality;
	private Object visuallyImpaired;
	private Object residentStatus;
	private Boolean isGuarantor;
	private DirectorPersonalDetailRequest directorPersonalDetailRequest;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetworth() {
		return networth;
	}

	public void setNetworth(String networth) {
		this.networth = networth;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getSalutationId() {
		return salutationId;
	}

	public void setSalutationId(Integer salutationId) {
		this.salutationId = salutationId;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getDirectorsName() {
		return directorsName;
	}

	public void setDirectorsName(String directorsName) {
		this.directorsName = directorsName;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(String cibilScore) {
		this.cibilScore = cibilScore;
	}

	public String getShareholding() {
		return shareholding;
	}

	public void setShareholding(String shareholding) {
		this.shareholding = shareholding;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNoOfDependent() {
		return noOfDependent;
	}

	public void setNoOfDependent(String noOfDependent) {
		this.noOfDependent = noOfDependent;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getResidenceSince() {
		return residenceSince;
	}

	public void setResidenceSince(String residenceSince) {
		this.residenceSince = residenceSince;
	}

	public String getIsFamilyMemberInBusiness() {
		return isFamilyMemberInBusiness;
	}

	public void setIsFamilyMemberInBusiness(String isFamilyMemberInBusiness) {
		this.isFamilyMemberInBusiness = isFamilyMemberInBusiness;
	}

	public Long getEmpDetailId() {
		return empDetailId;
	}

	public void setEmpDetailId(Long empDetailId) {
		this.empDetailId = empDetailId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Boolean getIsMainDirector() {
		return isMainDirector;
	}

	public void setIsMainDirector(Boolean isMainDirector) {
		this.isMainDirector = isMainDirector;
	}

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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDirectorBankName() {
		return directorBankName;
	}

	public void setDirectorBankName(String directorBankName) {
		this.directorBankName = directorBankName;
	}

	public String getDirectorBankAccount() {
		return directorBankAccount;
	}

	public void setDirectorBankAccount(String directorBankAccount) {
		this.directorBankAccount = directorBankAccount;
	}

	public BigInteger getTimeAtAddress() {
		return timeAtAddress;
	}

	public void setTimeAtAddress(BigInteger timeAtAddress) {
		this.timeAtAddress = timeAtAddress;
	}

	public Long getDistrictMappingId() {
		return districtMappingId;
	}

	public void setDistrictMappingId(Long districtMappingId) {
		this.districtMappingId = districtMappingId;
	}

	public Object getPinData() {
		return pinData;
	}

	public void setPinData(Object pinData) {
		this.pinData = pinData;
	}

	public Object getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Object personalId) {
		this.personalId = personalId;
	}

	public Object getDirectorPersonalInfo() {
		return directorPersonalInfo;
	}

	public void setDirectorPersonalInfo(Object directorPersonalInfo) {
		this.directorPersonalInfo = directorPersonalInfo;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEducationalStatus() {
		return educationalStatus;
	}

	public void setEducationalStatus(String educationalStatus) {
		this.educationalStatus = educationalStatus;
	}

	public Object getNationality() {
		return nationality;
	}

	public void setNationality(Object nationality) {
		this.nationality = nationality;
	}

	public Object getVisuallyImpaired() {
		return visuallyImpaired;
	}

	public void setVisuallyImpaired(Object visuallyImpaired) {
		this.visuallyImpaired = visuallyImpaired;
	}

	public Object getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(Object residentStatus) {
		this.residentStatus = residentStatus;
	}

	public Boolean getIsGuarantor() {
		return isGuarantor;
	}

	public void setIsGuarantor(Boolean isGuarantor) {
		this.isGuarantor = isGuarantor;
	}
	public DirectorPersonalDetailRequest getDirectorPersonalDetailRequest() {
		return directorPersonalDetailRequest;
	}

	public void setDirectorPersonalDetailRequest(DirectorPersonalDetailRequest directorPersonalDetailRequest) {
		this.directorPersonalDetailRequest = directorPersonalDetailRequest;
	}

	public String getExperianScore() {
		return experianScore;
	}

	public void setExperianScore(String experianScore) {
		this.experianScore = experianScore;
	}

	public String getHighmarkScore() {
		return highmarkScore;
	}

	public void setHighmarkScore(String highmarkScore) {
		this.highmarkScore = highmarkScore;
	}
}
