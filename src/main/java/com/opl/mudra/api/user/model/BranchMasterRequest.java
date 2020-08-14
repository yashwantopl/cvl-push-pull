package com.opl.mudra.api.user.model;

import java.io.Serializable;

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
