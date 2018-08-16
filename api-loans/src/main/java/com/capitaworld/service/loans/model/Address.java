package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	private Long cityId;

	private Integer countryId;

	private String landMark;

	private Long pincode;

	private String premiseNumber;

	private Integer stateId;

	private String streetName;

	private String district;
	private String subDistrict;
	private String village;
	private Long districtMappingId;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Long getDistrictMappingId() {
		return districtMappingId;
	}

	public void setDistrictMappingId(Long districtMappingId) {
		this.districtMappingId = districtMappingId;
	}

	@Override
	public String toString() {
		return "Address{" +
				"cityId=" + cityId +
				", countryId=" + countryId +
				", landMark='" + landMark + '\'' +
				", pincode=" + pincode +
				", premiseNumber='" + premiseNumber + '\'' +
				", stateId=" + stateId +
				", streetName='" + streetName + '\'' +
				", district='" + district + '\'' +
				", subDistrict='" + subDistrict + '\'' +
				", village='" + village + '\'' +
				", districtMappingId=" + districtMappingId +
				'}';
	}
}
