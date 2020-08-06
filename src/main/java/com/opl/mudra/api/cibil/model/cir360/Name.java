package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Name {
	
	@JsonProperty("FullName")
	private String fullName;
	
	@JsonProperty("FirstName")
	private String firstName;
	
	@JsonProperty("MiddleName")
	private String middleName;
	
	@JsonProperty("LastName")
	private String lastName;
	
	@JsonProperty("AdditionalMiddleName")
	private String additionalMiddleName;
	
	@JsonProperty("Suffix")
	private String suffix;
	
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getAdditionalMiddleName() {
		return additionalMiddleName;
	}

	public void setAdditionalMiddleName(String additionalMiddleName) {
		this.additionalMiddleName = additionalMiddleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
