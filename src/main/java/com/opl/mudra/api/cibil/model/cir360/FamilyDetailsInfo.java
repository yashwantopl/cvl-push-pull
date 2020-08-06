package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FamilyDetailsInfo {
	@JsonProperty("numberOfDependents")
	private String numberOfDependents;
	@JsonProperty("relatives")
	private List<FamilyDetails> relatives;

	public String getNumberOfDependents() {
		return numberOfDependents;
	}

	public void setNumberOfDependents(String numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}

	public List<FamilyDetails> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<FamilyDetails> relatives) {
		this.relatives = relatives;
	}

}
