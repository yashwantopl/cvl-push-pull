package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicAddressType {

	@JsonProperty("AddressLine")
	private String addressLine;
	
	@JsonProperty("State")
	private String state;

	@JsonProperty("Postal")
	private String postal;

	public BasicAddressType(){
		
	}
	public BasicAddressType(String addressLine, String state, String postal){
		this.addressLine = addressLine;
		this.state = state;
		this.postal = postal;
	}
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	
}
