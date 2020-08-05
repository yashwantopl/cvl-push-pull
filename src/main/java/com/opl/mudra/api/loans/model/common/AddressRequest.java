package com.opl.mudra.api.loans.model.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRequest implements Serializable {

	private static final long serialVersionUID = 6232712419796541868L;
	private String streetAddress;// Required
	private String premiseNo;// Required
	private String landMark;// Required
	private String region;// Required
	private Long regionId;
	private String addressType;// Required
	private String city;
	private String postalCode;

	public AddressRequest() {
		// Do nothing because of X and Y.
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getPremiseNo() {
		return premiseNo;
	}

	public void setPremiseNo(String premiseNo) {
		this.premiseNo = premiseNo;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	
	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "AddressRequest [streetAddress=" + streetAddress + ", premiseNo=" + premiseNo + ", landMark=" + landMark
				+ ", region=" + region + ", addressType=" + addressType + ", city=" + city + ", postalCode="
				+ postalCode + "]";
	}
}
