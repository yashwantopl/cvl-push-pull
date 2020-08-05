package com.opl.mudra.api.loans.model.retail;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_final_home_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalHomeLoanDetailRequestOld implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long clientId;
	private Long applicationId;


	private Double builtUpArea;

	private Double carpetArea;

	private Double estimatedRentalIncome;

	private String projectCityState;

	private Integer propertyAddressCity;

	private Integer propertyAddressCountry;

	private String propertyAddressLandmark;

	private String propertyAddressPincode;

	private String propertyAddressPremise;

	private Integer propertyAddressState;

	private String propertyAddressStreet;

	private Integer propertyUsed;

	private String sellerName;

	private Integer sellersAddressCity;

	private Integer sellersAddressCountry;

	private String sellersAddressLandmark;

	private String sellersAddressPincode;

	private String sellersAddressPremise;

	private Integer sellersAddressState;

	private String sellersAddressStreet;

	private Double superBuiltUpArea;
	
	private String currencyValue;
	
	private Boolean isFinalInformationFilled;
	
	private String finalFilledCount;
	
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

	public Double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Double getCarpetArea() {
		return carpetArea;
	}

	public void setCarpetArea(Double carpetArea) {
		this.carpetArea = carpetArea;
	}

	public Double getEstimatedRentalIncome() {
		return estimatedRentalIncome;
	}

	public void setEstimatedRentalIncome(Double estimatedRentalIncome) {
		this.estimatedRentalIncome = estimatedRentalIncome;
	}

	public String getProjectCityState() {
		return projectCityState;
	}

	public void setProjectCityState(String projectCityState) {
		this.projectCityState = projectCityState;
	}

	public Integer getPropertyAddressCity() {
		return propertyAddressCity;
	}

	public void setPropertyAddressCity(Integer propertyAddressCity) {
		this.propertyAddressCity = propertyAddressCity;
	}

	public Integer getPropertyAddressCountry() {
		return propertyAddressCountry;
	}

	public void setPropertyAddressCountry(Integer propertyAddressCountry) {
		this.propertyAddressCountry = propertyAddressCountry;
	}

	public String getPropertyAddressLandmark() {
		return propertyAddressLandmark;
	}

	public void setPropertyAddressLandmark(String propertyAddressLandmark) {
		this.propertyAddressLandmark = propertyAddressLandmark;
	}

	public String getPropertyAddressPincode() {
		return propertyAddressPincode;
	}

	public void setPropertyAddressPincode(String propertyAddressPincode) {
		this.propertyAddressPincode = propertyAddressPincode;
	}

	public String getPropertyAddressPremise() {
		return propertyAddressPremise;
	}

	public void setPropertyAddressPremise(String propertyAddressPremise) {
		this.propertyAddressPremise = propertyAddressPremise;
	}

	public Integer getPropertyAddressState() {
		return propertyAddressState;
	}

	public void setPropertyAddressState(Integer propertyAddressState) {
		this.propertyAddressState = propertyAddressState;
	}

	public String getPropertyAddressStreet() {
		return propertyAddressStreet;
	}

	public void setPropertyAddressStreet(String propertyAddressStreet) {
		this.propertyAddressStreet = propertyAddressStreet;
	}

	public Integer getPropertyUsed() {
		return propertyUsed;
	}

	public void setPropertyUsed(Integer propertyUsed) {
		this.propertyUsed = propertyUsed;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getSellersAddressCity() {
		return sellersAddressCity;
	}

	public void setSellersAddressCity(Integer sellersAddressCity) {
		this.sellersAddressCity = sellersAddressCity;
	}

	public Integer getSellersAddressCountry() {
		return sellersAddressCountry;
	}

	public void setSellersAddressCountry(Integer sellersAddressCountry) {
		this.sellersAddressCountry = sellersAddressCountry;
	}

	public String getSellersAddressLandmark() {
		return sellersAddressLandmark;
	}

	public void setSellersAddressLandmark(String sellersAddressLandmark) {
		this.sellersAddressLandmark = sellersAddressLandmark;
	}

	public String getSellersAddressPincode() {
		return sellersAddressPincode;
	}

	public void setSellersAddressPincode(String sellersAddressPincode) {
		this.sellersAddressPincode = sellersAddressPincode;
	}

	public String getSellersAddressPremise() {
		return sellersAddressPremise;
	}

	public void setSellersAddressPremise(String sellersAddressPremise) {
		this.sellersAddressPremise = sellersAddressPremise;
	}

	public Integer getSellersAddressState() {
		return sellersAddressState;
	}

	public void setSellersAddressState(Integer sellersAddressState) {
		this.sellersAddressState = sellersAddressState;
	}

	public String getSellersAddressStreet() {
		return sellersAddressStreet;
	}

	public void setSellersAddressStreet(String sellersAddressStreet) {
		this.sellersAddressStreet = sellersAddressStreet;
	}

	public Double getSuperBuiltUpArea() {
		return superBuiltUpArea;
	}

	public void setSuperBuiltUpArea(Double superBuiltUpArea) {
		this.superBuiltUpArea = superBuiltUpArea;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Boolean getIsFinalInformationFilled() {
		return isFinalInformationFilled;
	}

	public void setIsFinalInformationFilled(Boolean isFinalInformationFilled) {
		this.isFinalInformationFilled = isFinalInformationFilled;
	}

	public String getFinalFilledCount() {
		return finalFilledCount;
	}

	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}

	
}