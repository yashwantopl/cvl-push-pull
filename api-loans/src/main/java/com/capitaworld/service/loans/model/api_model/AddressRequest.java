package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;

/**
 * The persistent class for the address database table.
 * 
 */
public class AddressRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String city;

	private String country;

	private String landMark;

	private String pincode;

	private String premiseNumber;

	private String state;

	private String streetName;

	// start ankit

	private Long cityId;

	private Integer countryId;

	private Integer  stateId;

	private Long pinCode;
	
	private String district;
	
	private String subDistrict;
	
	private String townVillageTaluka;

	// end

	public AddressRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLandMark() {
		return this.landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPremiseNumber() {
		return this.premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer  getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Long  getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long  pinCode) {
		this.pinCode = pinCode;
	}
	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getTownVillageTaluka() {
		return townVillageTaluka;
	}

	public void setTownVillageTaluka(String townVillageTaluka) {
		this.townVillageTaluka = townVillageTaluka;
	}

	@Override
	public String toString() {
		return "AddressRequest [id=" + id + ", city=" + city + ", country=" + country + ", landMark=" + landMark
				+ ", pincode=" + pincode + ", premiseNumber=" + premiseNumber + ", state=" + state + ", streetName="
				+ streetName + ", cityId=" + cityId + ", countryId=" + countryId + ", stateId=" + stateId + ", pinCode="
				+ pinCode + ", district=" + district + ", subDistrict=" + subDistrict + ", townVillageTaluka="
				+ townVillageTaluka + "]";
	}
}