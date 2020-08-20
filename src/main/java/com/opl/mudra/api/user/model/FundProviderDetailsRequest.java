package com.opl.mudra.api.user.model;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundProviderDetailsRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private Long clientId;

	private String aboutUs;

	private String address;

	private Integer cityId;

	private String contactPersonDesignation;

	private String contactPersonName;

	private Integer countryId;

	private Integer estdMonth;

	private Integer estdYear;

	private String landmark;

	private String latitude;

	private String longitude;

	private String organizationName;
	
	private String organisationName;

	private String pan;

	private Integer pincode;

	private String remarks;

	private Integer stateId;

	private String streetAddress;

	private String website;
	
	private Integer businessTypeMaster;
	private String businessTypeName;
	
	private String imagePath;
	private String cityName;
	private String stateName;
	private String countryName;
	private boolean profileFilled;

	//added by Dhaval
	private Integer titleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;
	private String contactNumber;

	private String email;
	private String mobile;
	private boolean isProfileLocked;
	
	private String generalFieldsJson;
	
	
	public FundProviderDetailsRequest() {
		
	}
	
	public FundProviderDetailsRequest(Long userId) {
		this.userId = userId;
	}

	public boolean isProfileFilled() {
		return profileFilled;
	}

	public void setProfileFilled(boolean profileFilled) {
		this.profileFilled = profileFilled;
	}
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getBusinessTypeMaster() {
		return businessTypeMaster;
	}

	public void setBusinessTypeMaster(Integer businessTypeMaster) {
		this.businessTypeMaster = businessTypeMaster;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}

	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
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

	public Integer getEstdMonth() {
		return estdMonth;
	}

	public void setEstdMonth(Integer estdMonth) {
		this.estdMonth = estdMonth;
	}

	public Integer getEstdYear() {
		return estdYear;
	}

	public void setEstdYear(Integer estdYear) {
		this.estdYear = estdYear;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	//added by Dhaval
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean isProfileLocked() {
		return isProfileLocked;
	}

	public void setProfileLocked(boolean profileLocked) {
		isProfileLocked = profileLocked;
	}

	public String getGeneralFieldsJson() {
		return generalFieldsJson;
	}

	public void setGeneralFieldsJson(String generalFieldsJson) {
		this.generalFieldsJson = generalFieldsJson;
	}
	
	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	@Override
	public String toString() {
		return "FundProviderDetailsRequest [userId=" + userId + ", clientId=" + clientId + ", aboutUs=" + aboutUs
				+ ", address=" + address + ", cityId=" + cityId + ", contactPersonDesignation="
				+ contactPersonDesignation + ", contactPersonName=" + contactPersonName + ", countryId=" + countryId
				+ ", estdMonth=" + estdMonth + ", estdYear=" + estdYear + ", landmark=" + landmark + ", latitude="
				+ latitude + ", longitude=" + longitude + ", organisationName=" + organisationName + ", pan=" + pan
				+ ", pincode=" + pincode + ", remarks=" + remarks + ", stateId=" + stateId + ", streetAddress="
				+ streetAddress + ", website=" + website + ", businessTypeMaster=" + businessTypeMaster
				+ ", businessTypeName=" + businessTypeName + ", imagePath=" + imagePath + ", cityName=" + cityName
				+ ", stateName=" + stateName + ", countryName=" + countryName + ", profileFilled=" + profileFilled
				+ ", titleId=" + titleId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", birthDate=" + birthDate + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", mobile=" + mobile + ", isProfileLocked=" + isProfileLocked + ", generalFieldsJson="
				+ generalFieldsJson + "]";
	}
	
	
}
