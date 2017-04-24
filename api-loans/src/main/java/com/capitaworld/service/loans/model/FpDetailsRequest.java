package com.capitaworld.service.loans.model;

import java.io.Serializable;






public class FpDetailsRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String aboutUs;

	private Address address;

	private String contactPersonName;

	private String designation;

	private Integer establishmentMonth;

	private Integer establishmentYearId;

	private Double lattitude;

	private Double longitude;

	private String organisationName;

	private String pan;

	private String remark;

	private Long unIntegererestedIndustry;

	private String websiteAddress;

	public FpDetailsRequest() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getEstablishmentMonth() {
		return establishmentMonth;
	}

	public void setEstablishmentMonth(Integer establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}

	public Integer getEstablishmentYearId() {
		return establishmentYearId;
	}

	public void setEstablishmentYearId(Integer establishmentYearId) {
		this.establishmentYearId = establishmentYearId;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUnIntegererestedIndustry() {
		return unIntegererestedIndustry;
	}

	public void setUnIntegererestedIndustry(Long unIntegererestedIndustry) {
		this.unIntegererestedIndustry = unIntegererestedIndustry;
	}

	public String getWebsiteAddress() {
		return websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

	}