package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.cibil.utils.CibilUtils.AddressTypeEnum;
import com.opl.mudra.api.cibil.utils.CibilUtils.ResidenceTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRequest implements Serializable {

	private static final long serialVersionUID = 6232712419796541868L;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	private String addressLine5;// Required
	private String region;// Required
	private AddressTypeEnum addressType;
	private ResidenceTypeEnum residenceType;
	private String city;
	private String pinCode;
	private String stateCode;
	// public String registeredAddress;
	// public String registeredAddressCity;
	// public String registeredAddressStateCode;
	// public String registeredAddressPincode;
	// public String corporateAddress;
	// public String corporateAddressCity;
	// public String corporateAddressStateCode;
	// public String corporateAddressPincode;

	public AddressRequest() {
		// Do nothing because of X and Y.
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return addressLine5;
	}

	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public AddressTypeEnum getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressTypeEnum addressType) {
		this.addressType = addressType;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public ResidenceTypeEnum getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(ResidenceTypeEnum residenceType) {
		this.residenceType = residenceType;
	}
}
