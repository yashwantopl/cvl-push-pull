package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

public class BranchMasterRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer cityId;

	private String code;

	private String contactPersonEmail;

	private String contactPersonName;

	private Integer countryId;

	private String faxNo;

	private Boolean isHo;

	private String landMark;

	private String name;

	private Long orgId;

	private Integer parentBranchId;

	private int pincode;

	private String premisesNo;

	private String remarks;

	private Integer stateId;

	private String streetName;

	private String telephoneNo;

	private Long applicationId;

	private Long fpProductId;

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

	private Boolean isRecordUpdated;

	private Date modifiedDate;

	private String branchCode;

	private String branchAddress;

	private Long branchROID;

	private Long branchZOID;

	private Long branchHOID;

	private Boolean isEmailExist;

	private String transferReason;

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

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Boolean getIsHo() {
		return isHo;
	}

	public void setIsHo(Boolean isHo) {
		this.isHo = isHo;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
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

	public Integer getParentBranchId() {
		return parentBranchId;
	}

	public void setParentBranchId(Integer parentBranchId) {
		this.parentBranchId = parentBranchId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getPremisesNo() {
		return premisesNo;
	}

	public void setPremisesNo(String premisesNo) {
		this.premisesNo = premisesNo;
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

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public Long getBranchROID() {
		return branchROID;
	}

	public void setBranchROID(Long branchROID) {
		this.branchROID = branchROID;
	}

	public Long getBranchZOID() {
		return branchZOID;
	}

	public void setBranchZOID(Long branchZOID) {
		this.branchZOID = branchZOID;
	}

	public Long getBranchHOID() {
		return branchHOID;
	}

	public void setBranchHOID(Long branchHOID) {
		this.branchHOID = branchHOID;
	}

	public Boolean getIsEmailExist() {
		return isEmailExist;
	}

	public void setIsEmailExist(Boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	@Override
	public String toString() {
		return "BranchMasterRequest [id=" + id + ", cityId=" + cityId + ", code=" + code + ", contactPersonEmail="
				+ contactPersonEmail + ", contactPersonName=" + contactPersonName + ", countryId=" + countryId
				+ ", faxNo=" + faxNo + ", isHo=" + isHo + ", landMark=" + landMark + ", name=" + name + ", orgId="
				+ orgId + ", parentBranchId=" + parentBranchId + ", pincode=" + pincode + ", premisesNo=" + premisesNo
				+ ", remarks=" + remarks + ", stateId=" + stateId + ", streetName=" + streetName + ", telephoneNo="
				+ telephoneNo + ", applicationId=" + applicationId + ", fpProductId=" + fpProductId + ", branchId="
				+ branchId + "]";
	}

}
