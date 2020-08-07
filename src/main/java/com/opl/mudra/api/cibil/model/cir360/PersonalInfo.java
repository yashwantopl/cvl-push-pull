package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonalInfo {
	
	@JsonProperty("Name")
	private Name name;
	
	@JsonProperty("PreviousNames")
	private List<Name> previousNames;
	
	@JsonProperty(" AliasName")
	private AliasNameInfo aliasName;
	
	@JsonProperty("DateOfBirth")
	private String dateOfBirth;
	
	@JsonProperty("Gender")
	private String gender;
	
	@JsonProperty("Age")
	private AgeInfo age;
	
	@JsonProperty("PlaceOfBirthInfo")
	private PlaceOfBirthInfo placeOfBirthInfo;
	
	@JsonProperty("TotalIncome")
	private String totalIncome;
	
	@JsonProperty("Occupation")
	private String occupation;
	
	@JsonProperty("MaritalStatus")
	private String maritalStatus;

	
	
	public List<Name> getPreviousNames() {
		if(previousNames==null){
			previousNames = new ArrayList<Name>();
		}
		return previousNames;
	}

	public void setPreviousNames(List<Name> previousNames) {
		this.previousNames = previousNames;
	}

	public AliasNameInfo getAliasName() {
		return aliasName;
	}

	public void setAliasName(AliasNameInfo aliasName) {
		this.aliasName = aliasName;
	}

	public PlaceOfBirthInfo getPlaceOfBirthInfo() {
		return placeOfBirthInfo;
	}

	public void setPlaceOfBirthInfo(PlaceOfBirthInfo placeOfBirthInfo) {
		this.placeOfBirthInfo = placeOfBirthInfo;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public AgeInfo getAge() {
		return age;
	}

	public void setAge(AgeInfo age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


}
