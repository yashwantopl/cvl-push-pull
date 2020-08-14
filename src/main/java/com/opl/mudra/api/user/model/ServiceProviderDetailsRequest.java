package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceProviderDetailsRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String aboutMe;

	private String contactNo;

	private String contactPersonDsgntn;

	private String contactPersonName;

	private Integer estdMonth;

	private Integer estdYear;

	private String latitude;

	private String longitude;

	private String officeAddress;

	private Integer officeCityId;
	private String officeCityName;

	private Integer officeCountryId;
	private String officeCountryName;
	private String officeLandmark;

	private Integer officePincode;

	private Integer officeStateId;
	private String officeStateName;
	private String officeStreetAddress;

	private String organizationName;

	private String website;
	private String profileImagePath;
	private boolean profileFilled;
	

	public boolean isProfileFilled() {
		return profileFilled;
	}

	public void setProfileFilled(boolean profileFilled) {
		this.profileFilled = profileFilled;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getOfficeCityName() {
		return officeCityName;
	}

	public void setOfficeCityName(String officeCityName) {
		this.officeCityName = officeCityName;
	}

	public String getOfficeCountryName() {
		return officeCountryName;
	}

	public void setOfficeCountryName(String officeCountryName) {
		this.officeCountryName = officeCountryName;
	}

	public String getOfficeStateName() {
		return officeStateName;
	}

	public void setOfficeStateName(String officeStateName) {
		this.officeStateName = officeStateName;
	}

	private List<SpOrgSpecialityMapping> spOrgMappingList = Collections.emptyList();
	
	private Integer businessTypeMaster;
	private String businessTypeName;

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getContactPersonDsgntn() {
		return contactPersonDsgntn;
	}

	public void setContactPersonDsgntn(String contactPersonDsgntn) {
		this.contactPersonDsgntn = contactPersonDsgntn;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
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

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Integer getOfficeCityId() {
		return officeCityId;
	}

	public void setOfficeCityId(Integer officeCityId) {
		this.officeCityId = officeCityId;
	}

	public Integer getOfficeCountryId() {
		return officeCountryId;
	}

	public void setOfficeCountryId(Integer officeCountryId) {
		this.officeCountryId = officeCountryId;
	}

	public String getOfficeLandmark() {
		return officeLandmark;
	}

	public void setOfficeLandmark(String officeLandmark) {
		this.officeLandmark = officeLandmark;
	}

	public Integer getOfficePincode() {
		return officePincode;
	}

	public void setOfficePincode(Integer officePincode) {
		this.officePincode = officePincode;
	}

	public Integer getOfficeStateId() {
		return officeStateId;
	}

	public void setOfficeStateId(Integer officeStateId) {
		this.officeStateId = officeStateId;
	}

	public String getOfficeStreetAddress() {
		return officeStreetAddress;
	}

	public void setOfficeStreetAddress(String officeStreetAddress) {
		this.officeStreetAddress = officeStreetAddress;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getBusinessTypeMaster() {
		return businessTypeMaster;
	}

	public void setBusinessTypeMaster(Integer businessTypeMaster) {
		this.businessTypeMaster = businessTypeMaster;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public List<SpOrgSpecialityMapping> getSpOrgMappingList() {
		return spOrgMappingList;
	}

	public void setSpOrgMappingList(List<SpOrgSpecialityMapping> spOrgMappingList) {
		this.spOrgMappingList = spOrgMappingList;
	}

}
