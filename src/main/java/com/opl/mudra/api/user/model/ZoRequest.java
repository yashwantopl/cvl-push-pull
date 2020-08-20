package com.opl.mudra.api.user.model;

import java.util.Date;

/**
 * Created by dhaval.panchal on 01-Apr-19.
 */
public class ZoRequest {
	
	private Long id;

	private Integer cityId;

	private String code;

	private String contactPersonEmail;

	private String contactPersonName;

	private Integer countryId;

	private String name;

	private Long orgId;

	private int pincode;

	private String remarks;

	private Integer stateId;

	private String streetName;

	private Long applicationId;

	private Long branchId;
	
	private String ifscCode;
	
	private String branchRegion;
	
	private Long userId;
	
	private String location;
	
	private Long locationId;
	
	private String locationName;
	
	private String locationCode;
	
	private String content;

	private Boolean isBranchCodeExists;
	
	private Boolean isIfscCodeExists;

	private Boolean isRecordUpdated;

	private Boolean isEmailExist;

	private Date modifiedDate;
	
	private String branchCode;
	
	private String branchAddress;
	
	private String mobile;

	private Long businessTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranchRegion() {
		return branchRegion;
	}

	public void setBranchRegion(String branchRegion) {
		this.branchRegion = branchRegion;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsBranchCodeExists() {
		return isBranchCodeExists;
	}

	public void setIsBranchCodeExists(Boolean isBranchCodeExists) {
		this.isBranchCodeExists = isBranchCodeExists;
	}

	public Boolean getIsRecordUpdated() {
		return isRecordUpdated;
	}

	public void setIsRecordUpdated(Boolean isRecordUpdated) {
		this.isRecordUpdated = isRecordUpdated;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public Boolean getEmailExist() {
		return isEmailExist;
	}

	public void setEmailExist(Boolean emailExist) {
		isEmailExist = emailExist;
	}

	public Boolean getIsIfscCodeExists() {
		return isIfscCodeExists;
	}

	public void setIsIfscCodeExists(Boolean isIfscCodeExists) {
		this.isIfscCodeExists = isIfscCodeExists;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsEmailExist() {
		return isEmailExist;
	}

	public void setIsEmailExist(Boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
}
