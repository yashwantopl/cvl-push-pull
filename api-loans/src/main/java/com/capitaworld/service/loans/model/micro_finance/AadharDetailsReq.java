package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AadharDetailsReq {

    private Long id;

    private Long applicationId;

    private Long applicationProposalMapping;

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

    public AadharDetailsReq() {
    }

    //    fn.applicationId.id,,fn.firstName,mf.lastName,mf.middleName,mf.birthDate,mf.genderId,mf.mobile,mf.email,mf.addressSameAsAadhar,mf.currentDistrict,mf.aadharDistrict,mf.currentHouse,mf.aadharHouse,mf.currentLandmark,mf.aadharLandmark,mf.currentLocation,mf.aadharLocation,mf.currentState,mf.aadharState,mf.currentStreet,mf.aadharStreet,mf.currentVtc,mf.aadharVtc,mf.aadharSubdist,mf.currentSubdist,mf.aadharPo,mf.currentPo,mf.aadharCareOf,mf.addressPincode,mf.aadharPincode,mf.addressProofType
    public AadharDetailsReq(Long applicationId,String firstName,String lastName,String middleName, Date birthDate,Integer genderId,String mobile,String email,Boolean addressSameAsAadhar,String currentDistrict,String aadharDistrict,String currentHouse,String aadharHouse,
                            String currentLandmark,String aadharLandmark,String currentLocation,String aadharLocation,String currentState,String aadharState,String currentStreet,String aadharStreet,String currentVtc,String aadharVtc,String aadharSubdist,String currentSubdist,String aadharPo,String currentPo,String aadharCareOf,String addressPincode,String aadharPincode,Integer addressProofType) {
        this.applicationId = applicationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.genderId = genderId;
        this.mobile = mobile;
        this.email = email;
        this.addressSameAsAadhar = addressSameAsAadhar;
        this.currentDistrict = currentDistrict;
        this.aadharDistrict = aadharDistrict;
        this.currentHouse = currentHouse;
        this.aadharHouse = aadharHouse;
        this.currentLandmark = currentLandmark;
        this.aadharLandmark = aadharLandmark;
        this.currentLocation =currentLocation;
        this.aadharLocation = aadharLocation;
        this.currentState = currentState;
        this.aadharState = aadharState;
        this.currentStreet = currentStreet;
        this.aadharStreet = aadharStreet;
        this.currentVtc = currentVtc;
        this.aadharVtc = aadharVtc;
        this.aadharSubdist = aadharSubdist;
        this.currentSubdist = currentSubdist;
        this.aadharPo = aadharPo;
        this.currentPo = currentPo;
        this.aadharCareOf = aadharCareOf;
        this.addressPincode = addressPincode;
        this.aadharPincode = aadharPincode;
        this.addressProofType = addressProofType;
    }

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

    public Long getApplicationProposalMapping() {
        return applicationProposalMapping;
    }

    public void setApplicationProposalMapping(Long applicationProposalMapping) {
        this.applicationProposalMapping = applicationProposalMapping;
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

    public Integer getAddressProofType() {
        return addressProofType;
    }

    public void setAddressProofType(Integer addressProofType) {
        this.addressProofType = addressProofType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
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
}
