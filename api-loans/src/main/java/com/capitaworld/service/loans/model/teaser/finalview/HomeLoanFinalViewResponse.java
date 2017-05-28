package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;

public class HomeLoanFinalViewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse;

	private RetailFinalViewResponse finalViewResponse;
	
	private String propPremiseNo;
	private String propStreetName;
	private String propLandmark;
	private String propPinCode;
	private String propCity;
	private String propState;
	private String propCountry;
	private String builtUpArea;
	private String carpetArea;
	private String superBuiltuparea;
	private String sellerName;
	private String sellerPremiseNo;
	private String sellerStreetName;
	private String sellerLandmark;
	private String sellerPinCode;
	private String sellerCity;
	private String sellerState;
	private String sellerCountry;
	private String propertyUse;
	private String rentalIncome;

	public String getPropPremiseNo() {
		return propPremiseNo;
	}

	public void setPropPremiseNo(String propPremiseNo) {
		this.propPremiseNo = propPremiseNo;
	}

	public String getPropStreetName() {
		return propStreetName;
	}

	public void setPropStreetName(String propStreetName) {
		this.propStreetName = propStreetName;
	}

	public String getPropLandmark() {
		return propLandmark;
	}

	public void setPropLandmark(String propLandmark) {
		this.propLandmark = propLandmark;
	}

	public String getPropPinCode() {
		return propPinCode;
	}

	public void setPropPinCode(String propPinCode) {
		this.propPinCode = propPinCode;
	}

	public String getPropCity() {
		return propCity;
	}

	public void setPropCity(String propCity) {
		this.propCity = propCity;
	}

	public String getPropState() {
		return propState;
	}

	public void setPropState(String propState) {
		this.propState = propState;
	}

	public String getPropCountry() {
		return propCountry;
	}

	public void setPropCountry(String propCountry) {
		this.propCountry = propCountry;
	}

	public String getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(String builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public String getCarpetArea() {
		return carpetArea;
	}

	public void setCarpetArea(String carpetArea) {
		this.carpetArea = carpetArea;
	}

	public String getSuperBuiltuparea() {
		return superBuiltuparea;
	}

	public void setSuperBuiltuparea(String superBuiltuparea) {
		this.superBuiltuparea = superBuiltuparea;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerPremiseNo() {
		return sellerPremiseNo;
	}

	public void setSellerPremiseNo(String sellerPremiseNo) {
		this.sellerPremiseNo = sellerPremiseNo;
	}

	public String getSellerStreetName() {
		return sellerStreetName;
	}

	public void setSellerStreetName(String sellerStreetName) {
		this.sellerStreetName = sellerStreetName;
	}

	public String getSellerLandmark() {
		return sellerLandmark;
	}

	public void setSellerLandmark(String sellerLandmark) {
		this.sellerLandmark = sellerLandmark;
	}

	public String getSellerPinCode() {
		return sellerPinCode;
	}

	public void setSellerPinCode(String sellerPinCode) {
		this.sellerPinCode = sellerPinCode;
	}

	public String getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}

	public String getSellerState() {
		return sellerState;
	}

	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}

	public String getSellerCountry() {
		return sellerCountry;
	}

	public void setSellerCountry(String sellerCountry) {
		this.sellerCountry = sellerCountry;
	}

	public String getPropertyUse() {
		return propertyUse;
	}

	public void setPropertyUse(String propertyUse) {
		this.propertyUse = propertyUse;
	}

	public String getRentalIncome() {
		return rentalIncome;
	}

	public void setRentalIncome(String rentalIncome) {
		this.rentalIncome = rentalIncome;
	}

	public RetailFinalViewResponse getFinalViewResponse() {
		return finalViewResponse;
	}

	public void setFinalViewResponse(RetailFinalViewResponse finalViewResponse) {
		this.finalViewResponse = finalViewResponse;
	}

	public HomeLoanPrimaryViewResponse getHomeLoanPrimaryViewResponse() {
		return homeLoanPrimaryViewResponse;
	}

	public void setHomeLoanPrimaryViewResponse(HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse) {
		this.homeLoanPrimaryViewResponse = homeLoanPrimaryViewResponse;
	}
	
	

}
