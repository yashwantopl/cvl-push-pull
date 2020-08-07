package com.opl.mudra.api.mca.cubictree.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author : Maaz Shaikh
 * Time :  5:03:01 PM
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class MatchTableIndividual {

	@JsonProperty(value="name")
	private String name;
	
	@JsonProperty(value="pan")
	private String pan;
	
	@JsonProperty(value="mobile")
	private String mobile;
	
	@JsonProperty(value="district")
	private String district;
	
	@JsonProperty(value="state")
	private String state;
	
	@JsonProperty(value="pin")
	private String pin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "MatchTableIndividual [name=" + name + ", pan=" + pan + ", mobile=" + mobile + ", district=" + district
				+ ", state=" + state + ", pin=" + pin + "]";
	}
	
	
}
