package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilFullFillOfferRequest implements Serializable {

	private static final long serialVersionUID = -2743002654750432132L;

	private String title;
	private String forName;// Required
	private String surName;// Required
	private String pan;// Required
	private String adhaar;
	private String phoneNumber;// Required
	private String gender;// Required
	private String legacyCopyStatus;// Required

	private String suffix;
	private AddressRequest address;
	private AddressRequest previousAddress;
	private Date dateOfBirth;
	private String countryCode;
	private String areaCode;
	private String extension;
	private String email;

	public CibilFullFillOfferRequest() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForName() {
		return forName;
	}

	public void setForName(String forName) {
		this.forName = forName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	public AddressRequest getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(AddressRequest previousAddress) {
		this.previousAddress = previousAddress;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLegacyCopyStatus() {
		return legacyCopyStatus;
	}

	public void setLegacyCopyStatus(String legacyCopyStatus) {
		this.legacyCopyStatus = legacyCopyStatus;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAdhaar() {
		return adhaar;
	}

	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}

	@Override
	public String toString() {
		return "CibilFullFillOfferRequest [title=" + title + ", forName=" + forName + ", surName=" + surName
				+ ", suffix=" + suffix + ", pan=" + pan + ", adhaar=" + adhaar + ", address=" + address
				+ ", previousAddress=" + previousAddress + ", dateOfBirth=" + dateOfBirth + ", countryCode="
				+ countryCode + ", areaCode=" + areaCode + ", phoneNumber=" + phoneNumber + ", extension=" + extension
				+ ", email=" + email + ", gender=" + gender + ", legacyCopyStatus=" + legacyCopyStatus + "]";
	}

}
