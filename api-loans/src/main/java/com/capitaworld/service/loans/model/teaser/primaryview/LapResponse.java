package com.capitaworld.service.loans.model.teaser.primaryview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.AddressResponse;

public class LapResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanPurpose;
	private String loanPurposeOther;
	private String loanAmount;
	private String currency;
	private String tenure;
	
	private String propertyType;
	private String propertyTypeOther;
	private String occupationStatus;
	private String occupationStatusOther;
	private String propertyAgeInYears;
	private String propertyAgeInMonths;
	private String totalArea;
	private String builtUpArea;
	private String propertyValue;
	private String propertyOwnerName;
	private String propertyPremiseNumber;
	private String propertyStreetName;
	private String propertyLandmark;
	private String propertyCity;
	private String propertyState;
	private String propertyCountry;
	private String propertyPincode;
	private String dateOfProposal;
	private AddressResponse permanentAddress;
    private AddressResponse officeAddress;
    
    private List<Object> profileImage;
    
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<Object> getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(List<Object> profileImage) {
		this.profileImage = profileImage;
	}
	public String getDateOfProposal() {
		return dateOfProposal;
	}
	public void setDateOfProposal(String dateOfProposal) {
		this.dateOfProposal = dateOfProposal;
	}
	public AddressResponse getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(AddressResponse permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public AddressResponse getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(AddressResponse officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}
	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyTypeOther() {
		return propertyTypeOther;
	}
	public void setPropertyTypeOther(String propertyTypeOther) {
		this.propertyTypeOther = propertyTypeOther;
	}
	public String getOccupationStatus() {
		return occupationStatus;
	}
	public void setOccupationStatus(String occupationStatus) {
		this.occupationStatus = occupationStatus;
	}
	public String getOccupationStatusOther() {
		return occupationStatusOther;
	}
	public void setOccupationStatusOther(String occupationStatusOther) {
		this.occupationStatusOther = occupationStatusOther;
	}
	public String getPropertyAgeInYears() {
		return propertyAgeInYears;
	}
	public void setPropertyAgeInYears(String propertyAgeInYears) {
		this.propertyAgeInYears = propertyAgeInYears;
	}
	public String getPropertyAgeInMonths() {
		return propertyAgeInMonths;
	}
	public void setPropertyAgeInMonths(String propertyAgeInMonths) {
		this.propertyAgeInMonths = propertyAgeInMonths;
	}
	public String getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}
	public String getBuiltUpArea() {
		return builtUpArea;
	}
	public void setBuiltUpArea(String builtUpArea) {
		this.builtUpArea = builtUpArea;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getPropertyOwnerName() {
		return propertyOwnerName;
	}
	public void setPropertyOwnerName(String propertyOwnerName) {
		this.propertyOwnerName = propertyOwnerName;
	}
	public String getPropertyPremiseNumber() {
		return propertyPremiseNumber;
	}
	public void setPropertyPremiseNumber(String propertyPremiseNumber) {
		this.propertyPremiseNumber = propertyPremiseNumber;
	}
	public String getPropertyStreetName() {
		return propertyStreetName;
	}
	public void setPropertyStreetName(String propertyStreetName) {
		this.propertyStreetName = propertyStreetName;
	}
	public String getPropertyLandmark() {
		return propertyLandmark;
	}
	public void setPropertyLandmark(String propertyLandmark) {
		this.propertyLandmark = propertyLandmark;
	}
	public String getPropertyCity() {
		return propertyCity;
	}
	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	public String getPropertyState() {
		return propertyState;
	}
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	public String getPropertyCountry() {
		return propertyCountry;
	}
	public void setPropertyCountry(String propertyCountry) {
		this.propertyCountry = propertyCountry;
	}
	public String getPropertyPincode() {
		return propertyPincode;
	}
	public void setPropertyPincode(String propertyPincode) {
		this.propertyPincode = propertyPincode;
	}

}
