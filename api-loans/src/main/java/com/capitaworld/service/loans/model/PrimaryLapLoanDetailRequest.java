package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_lap_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryLapLoanDetailRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer addressCity;

	private Integer addressCountry;

	private String addressLandmark;

	private String addressPremise;

	private Integer addressState;

	private String addressStreet;

	private Double builtUpArea;

	private Double landArea;

	private Integer loanPurpose;

	private String loanPurposeOther;

	private Integer occupationStatus;

	private String occupationStatusOther;

	private String ownerName;

	private Integer pincode;

	private Integer propertyAgeInMonth;

	private Integer propertyAgeInYear;

	private Integer propertyType;

	private String propertyTypeOther;

	private Double propertyValue;

	public PrimaryLapLoanDetailRequest() {
	}

	public Integer getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(Integer addressCity) {
		this.addressCity = addressCity;
	}

	public Integer getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressLandmark() {
		return addressLandmark;
	}

	public void setAddressLandmark(String addressLandmark) {
		this.addressLandmark = addressLandmark;
	}

	public String getAddressPremise() {
		return addressPremise;
	}

	public void setAddressPremise(String addressPremise) {
		this.addressPremise = addressPremise;
	}

	public Integer getAddressState() {
		return addressState;
	}

	public void setAddressState(Integer addressState) {
		this.addressState = addressState;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public Double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Double getLandArea() {
		return landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	public Integer getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}

	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}

	public Integer getOccupationStatus() {
		return occupationStatus;
	}

	public void setOccupationStatus(Integer occupationStatus) {
		this.occupationStatus = occupationStatus;
	}

	public String getOccupationStatusOther() {
		return occupationStatusOther;
	}

	public void setOccupationStatusOther(String occupationStatusOther) {
		this.occupationStatusOther = occupationStatusOther;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Integer getPropertyAgeInMonth() {
		return propertyAgeInMonth;
	}

	public void setPropertyAgeInMonth(Integer propertyAgeInMonth) {
		this.propertyAgeInMonth = propertyAgeInMonth;
	}

	public Integer getPropertyAgeInYear() {
		return propertyAgeInYear;
	}

	public void setPropertyAgeInYear(Integer propertyAgeInYear) {
		this.propertyAgeInYear = propertyAgeInYear;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyTypeOther() {
		return propertyTypeOther;
	}

	public void setPropertyTypeOther(String propertyTypeOther) {
		this.propertyTypeOther = propertyTypeOther;
	}

	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

}