package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundSeekerDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String address;

	private Integer cityId;

	private Integer countryId;


	private Date dob;

	private Integer estdMonth;

	private Integer estdYear;

	private FundSeekerTypeMasterRequest fsTypeId;

	private String landmark;

	private String name;

	private Integer pincode;

	private Integer stateId;

	private String streetAddress;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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

	public FundSeekerTypeMasterRequest getFsTypeId() {
		return fsTypeId;
	}

	public void setFsTypeId(FundSeekerTypeMasterRequest fsTypeId) {
		this.fsTypeId = fsTypeId;
	}
	
	
}
