package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DirectorProfile {

	@JsonProperty("director-name")
	private String directorName;
	
	private String  address;
	
	@JsonProperty("address-map")
	private String addressMap;
	
	@JsonProperty("director-father-name")
	private String directorFatherName;
	
	@JsonProperty("director-type")
	private String directorType;
	
	@JsonProperty("date-of-Birth")
	private String dateOfBirth;
	
	private String pan;
	
	private String age;
	
	private String city;
	
	private String state;
	
	private String din;
	
	private String is_tracked;

	public String getDirectorName() {
		return directorName;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressMap() {
		return addressMap;
	}

	public String getDirectorFatherName() {
		return directorFatherName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPan() {
		return pan;
	}

	public String getAge() {
		return age;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getDin() {
		return din;
	}

	public String getIs_tracked() {
		return is_tracked;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressMap(String addressMap) {
		this.addressMap = addressMap;
	}

	public void setDirectorFatherName(String directorFatherName) {
		this.directorFatherName = directorFatherName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public void setIs_tracked(String is_tracked) {
		this.is_tracked = is_tracked;
	}

	public String getDirectorType() {
		return directorType;
	}

	public void setDirectorType(String directorType) {
		this.directorType = directorType;
	}
	
	
	
	
}
