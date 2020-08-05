package com.opl.mudra.api.loans.model.micro_finance;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MfiReqResponse {

	// AADHARDetailsReq. Response
	private String aadharNumber;
    private String nameAsPerAadharCard;
    private String firstName;
    private String mobile;
    private String email;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private Integer genderId;
    private Integer maritalStatusId;
    private Boolean addressSameAsAadhar;
    private String currentDistrict;
    private String aadharDistrict;
    private String currentHouse;
    private String aadharHouse;
    private String currentLandmark;
    private String aadharLandmark;
    private String currentLocation;
    private String aadharLocation;
    private String currentState;
    private String aadharState;
    private String currentStreet;
    private String aadharStreet;
    private String currentVtc;
    private String aadharVtc;
    private String aadharSubdist;
    private String currentSubdist;
    private String aadharPo;
    private String currentPo;
    private String aadharCareOf;
    private String addressPincode;
    private String aadharPincode;
    private Integer addressProfType;
    private String fatherName;
    private String motherName;
    private Long userId;
    private Integer addressProofType;
    private Integer businessTypeId;
    private  byte[] profilePic;
    private  byte[] addressProofImg;
    // ENDS HERE 	// AADHARDetailsReq. Response
    
    // PERSONAL DETAILS STARTS HERE  
/*	private String fatherName;
	private String motherName;*/
	private String spouseName;
	private Date spouseBirthDate;
	private Integer noDependent;
	private String spouseMobile;

	private String nomineeName;
	private Date nomineeBirthDate;
	private Integer relationWithNomineeId;
	private String nomineeAddress;
	private String nomineePincode;

	private Integer educationQualification;
	private String religion;
	private String cast;
	private Double landHolding;
	private Integer houseType;

	private String nameOfFirm;
	private Integer businessType;
	private Boolean lifeInsurance;
	private String sumInsured;

	private String nomineeState;
	private String nomineeCity;
	private String nomineeDistrict;
	private String nomineeLocation;
	private String nomineeHouseNo;
	private String nomineeLandmark;
	private String academicReligion;
	private String academicCaste;
	private Boolean isAcademicLifeInsurance;
	private String houseOwnership;
	private String areaType;
	private String businessPremises;
	private String expInSameLine;
	private String academicSumInsured;
	private Boolean isPersonalDetailsFilled;
	// PERSONAL DETAILS ENDS HERE ========================>
	
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public Integer getMaritalStatusId() {
		return maritalStatusId;
	}
	public void setMaritalStatusId(Integer maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}
	public Boolean getAddressSameAsAadhar() {
		return addressSameAsAadhar;
	}
	public void setAddressSameAsAadhar(Boolean addressSameAsAadhar) {
		this.addressSameAsAadhar = addressSameAsAadhar;
	}
	public String getCurrentDistrict() {
		return currentDistrict;
	}
	public void setCurrentDistrict(String currentDistrict) {
		this.currentDistrict = currentDistrict;
	}
	public String getAadharDistrict() {
		return aadharDistrict;
	}
	public void setAadharDistrict(String aadharDistrict) {
		this.aadharDistrict = aadharDistrict;
	}
	public String getCurrentHouse() {
		return currentHouse;
	}
	public void setCurrentHouse(String currentHouse) {
		this.currentHouse = currentHouse;
	}
	public String getAadharHouse() {
		return aadharHouse;
	}
	public void setAadharHouse(String aadharHouse) {
		this.aadharHouse = aadharHouse;
	}
	public String getCurrentLandmark() {
		return currentLandmark;
	}
	public void setCurrentLandmark(String currentLandmark) {
		this.currentLandmark = currentLandmark;
	}
	public String getAadharLandmark() {
		return aadharLandmark;
	}
	public void setAadharLandmark(String aadharLandmark) {
		this.aadharLandmark = aadharLandmark;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getAadharLocation() {
		return aadharLocation;
	}
	public void setAadharLocation(String aadharLocation) {
		this.aadharLocation = aadharLocation;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getAadharState() {
		return aadharState;
	}
	public void setAadharState(String aadharState) {
		this.aadharState = aadharState;
	}
	public String getCurrentStreet() {
		return currentStreet;
	}
	public void setCurrentStreet(String currentStreet) {
		this.currentStreet = currentStreet;
	}
	public String getAadharStreet() {
		return aadharStreet;
	}
	public void setAadharStreet(String aadharStreet) {
		this.aadharStreet = aadharStreet;
	}
	public String getCurrentVtc() {
		return currentVtc;
	}
	public void setCurrentVtc(String currentVtc) {
		this.currentVtc = currentVtc;
	}
	public String getAadharVtc() {
		return aadharVtc;
	}
	public void setAadharVtc(String aadharVtc) {
		this.aadharVtc = aadharVtc;
	}
	public String getAadharSubdist() {
		return aadharSubdist;
	}
	public void setAadharSubdist(String aadharSubdist) {
		this.aadharSubdist = aadharSubdist;
	}
	public String getCurrentSubdist() {
		return currentSubdist;
	}
	public void setCurrentSubdist(String currentSubdist) {
		this.currentSubdist = currentSubdist;
	}
	public String getAadharPo() {
		return aadharPo;
	}
	public void setAadharPo(String aadharPo) {
		this.aadharPo = aadharPo;
	}
	public String getCurrentPo() {
		return currentPo;
	}
	public void setCurrentPo(String currentPo) {
		this.currentPo = currentPo;
	}
	public String getAadharCareOf() {
		return aadharCareOf;
	}
	public void setAadharCareOf(String aadharCareOf) {
		this.aadharCareOf = aadharCareOf;
	}
	public String getAddressPincode() {
		return addressPincode;
	}
	public void setAddressPincode(String addressPincode) {
		this.addressPincode = addressPincode;
	}
	public String getAadharPincode() {
		return aadharPincode;
	}
	public void setAadharPincode(String aadharPincode) {
		this.aadharPincode = aadharPincode;
	}
	public Integer getAddressProfType() {
		return addressProfType;
	}
	public void setAddressProfType(Integer addressProfType) {
		this.addressProfType = addressProfType;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getAddressProofType() {
		return addressProofType;
	}
	public void setAddressProofType(Integer addressProofType) {
		this.addressProofType = addressProofType;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public byte[] getAddressProofImg() {
		return addressProofImg;
	}
	public void setAddressProofImg(byte[] addressProofImg) {
		this.addressProofImg = addressProofImg;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public Date getSpouseBirthDate() {
		return spouseBirthDate;
	}
	public void setSpouseBirthDate(Date spouseBirthDate) {
		this.spouseBirthDate = spouseBirthDate;
	}
	public Integer getNoDependent() {
		return noDependent;
	}
	public void setNoDependent(Integer noDependent) {
		this.noDependent = noDependent;
	}
	public String getSpouseMobile() {
		return spouseMobile;
	}
	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Date getNomineeBirthDate() {
		return nomineeBirthDate;
	}
	public void setNomineeBirthDate(Date nomineeBirthDate) {
		this.nomineeBirthDate = nomineeBirthDate;
	}
	public Integer getRelationWithNomineeId() {
		return relationWithNomineeId;
	}
	public void setRelationWithNomineeId(Integer relationWithNomineeId) {
		this.relationWithNomineeId = relationWithNomineeId;
	}
	public String getNomineeAddress() {
		return nomineeAddress;
	}
	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}
	public String getNomineePincode() {
		return nomineePincode;
	}
	public void setNomineePincode(String nomineePincode) {
		this.nomineePincode = nomineePincode;
	}
	public Integer getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(Integer educationQualification) {
		this.educationQualification = educationQualification;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public Double getLandHolding() {
		return landHolding;
	}
	public void setLandHolding(Double landHolding) {
		this.landHolding = landHolding;
	}
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	public String getNameOfFirm() {
		return nameOfFirm;
	}
	public void setNameOfFirm(String nameOfFirm) {
		this.nameOfFirm = nameOfFirm;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Boolean getLifeInsurance() {
		return lifeInsurance;
	}
	public void setLifeInsurance(Boolean lifeInsurance) {
		this.lifeInsurance = lifeInsurance;
	}
	public String getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getNomineeState() {
		return nomineeState;
	}
	public void setNomineeState(String nomineeState) {
		this.nomineeState = nomineeState;
	}
	public String getNomineeCity() {
		return nomineeCity;
	}
	public void setNomineeCity(String nomineeCity) {
		this.nomineeCity = nomineeCity;
	}
	public String getNomineeDistrict() {
		return nomineeDistrict;
	}
	public void setNomineeDistrict(String nomineeDistrict) {
		this.nomineeDistrict = nomineeDistrict;
	}
	public String getNomineeLocation() {
		return nomineeLocation;
	}
	public void setNomineeLocation(String nomineeLocation) {
		this.nomineeLocation = nomineeLocation;
	}
	public String getNomineeHouseNo() {
		return nomineeHouseNo;
	}
	public void setNomineeHouseNo(String nomineeHouseNo) {
		this.nomineeHouseNo = nomineeHouseNo;
	}
	public String getNomineeLandmark() {
		return nomineeLandmark;
	}
	public void setNomineeLandmark(String nomineeLandmark) {
		this.nomineeLandmark = nomineeLandmark;
	}
	public String getAcademicReligion() {
		return academicReligion;
	}
	public void setAcademicReligion(String academicReligion) {
		this.academicReligion = academicReligion;
	}
	public String getAcademicCaste() {
		return academicCaste;
	}
	public void setAcademicCaste(String academicCaste) {
		this.academicCaste = academicCaste;
	}
	public Boolean getIsAcademicLifeInsurance() {
		return isAcademicLifeInsurance;
	}
	public void setIsAcademicLifeInsurance(Boolean isAcademicLifeInsurance) {
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
	}
	public String getHouseOwnership() {
		return houseOwnership;
	}
	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getBusinessPremises() {
		return businessPremises;
	}
	public void setBusinessPremises(String businessPremises) {
		this.businessPremises = businessPremises;
	}
	public String getExpInSameLine() {
		return expInSameLine;
	}
	public void setExpInSameLine(String expInSameLine) {
		this.expInSameLine = expInSameLine;
	}
	public String getAcademicSumInsured() {
		return academicSumInsured;
	}
	public void setAcademicSumInsured(String academicSumInsured) {
		this.academicSumInsured = academicSumInsured;
	}
	public Boolean getIsPersonalDetailsFilled() {
		return isPersonalDetailsFilled;
	}
	public void setIsPersonalDetailsFilled(Boolean isPersonalDetailsFilled) {
		this.isPersonalDetailsFilled = isPersonalDetailsFilled;
	}
	@Override
	public String toString() {
		return "MfiReqResponse [aadharNumber=" + aadharNumber + ", nameAsPerAadharCard=" + nameAsPerAadharCard
				+ ", firstName=" + firstName + ", mobile=" + mobile + ", email=" + email + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", birthDate=" + birthDate + ", genderId=" + genderId
				+ ", maritalStatusId=" + maritalStatusId + ", addressSameAsAadhar=" + addressSameAsAadhar
				+ ", currentDistrict=" + currentDistrict + ", aadharDistrict=" + aadharDistrict + ", currentHouse="
				+ currentHouse + ", aadharHouse=" + aadharHouse + ", currentLandmark=" + currentLandmark
				+ ", aadharLandmark=" + aadharLandmark + ", currentLocation=" + currentLocation + ", aadharLocation="
				+ aadharLocation + ", currentState=" + currentState + ", aadharState=" + aadharState
				+ ", currentStreet=" + currentStreet + ", aadharStreet=" + aadharStreet + ", currentVtc=" + currentVtc
				+ ", aadharVtc=" + aadharVtc + ", aadharSubdist=" + aadharSubdist + ", currentSubdist=" + currentSubdist
				+ ", aadharPo=" + aadharPo + ", currentPo=" + currentPo + ", aadharCareOf=" + aadharCareOf
				+ ", addressPincode=" + addressPincode + ", aadharPincode=" + aadharPincode + ", addressProfType="
				+ addressProfType + ", fatherName=" + fatherName + ", motherName=" + motherName + ", userId=" + userId
				+ ", addressProofType=" + addressProofType + ", businessTypeId=" + businessTypeId + ", profilePic="
				+ Arrays.toString(profilePic) + ", addressProofImg=" + Arrays.toString(addressProofImg)
				+ ", spouseName=" + spouseName + ", spouseBirthDate=" + spouseBirthDate + ", noDependent=" + noDependent
				+ ", spouseMobile=" + spouseMobile + ", nomineeName=" + nomineeName + ", nomineeBirthDate="
				+ nomineeBirthDate + ", relationWithNomineeId=" + relationWithNomineeId + ", nomineeAddress="
				+ nomineeAddress + ", nomineePincode=" + nomineePincode + ", educationQualification="
				+ educationQualification + ", religion=" + religion + ", cast=" + cast + ", landHolding=" + landHolding
				+ ", houseType=" + houseType + ", nameOfFirm=" + nameOfFirm + ", businessType=" + businessType
				+ ", lifeInsurance=" + lifeInsurance + ", sumInsured=" + sumInsured + ", nomineeState=" + nomineeState
				+ ", nomineeCity=" + nomineeCity + ", nomineeDistrict=" + nomineeDistrict + ", nomineeLocation="
				+ nomineeLocation + ", nomineeHouseNo=" + nomineeHouseNo + ", nomineeLandmark=" + nomineeLandmark
				+ ", academicReligion=" + academicReligion + ", academicCaste=" + academicCaste
				+ ", isAcademicLifeInsurance=" + isAcademicLifeInsurance + ", houseOwnership=" + houseOwnership
				+ ", areaType=" + areaType + ", businessPremises=" + businessPremises + ", expInSameLine="
				+ expInSameLine + ", academicSumInsured=" + academicSumInsured + ", isPersonalDetailsFilled="
				+ isPersonalDetailsFilled + "]";
	}
	
}
