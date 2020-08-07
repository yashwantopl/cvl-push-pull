package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MFIDetails {

	@JsonProperty("AdditionalSearchField")
	private String additionalSearchField;
	
	@JsonProperty("BranchID")
	private String branchID;
	
	@JsonProperty("KendraID")
	private String kendraID;
	
	@JsonProperty("FamilyDetails")
	private List<FamilyDetails> familyDetails;

	public String getAdditionalSearchField() {
		return additionalSearchField;
	}

	public void setAdditionalSearchField(String additionalSearchField) {
		this.additionalSearchField = additionalSearchField;
	}

	

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getKendraID() {
		return kendraID;
	}

	public void setKendraID(String kendraID) {
		this.kendraID = kendraID;
	}

	public List<FamilyDetails> getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(List<FamilyDetails> familyDetails) {
		this.familyDetails = familyDetails;
	}
	
	
}
