package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FamilyDetails {

	private String seq;
	
	@JsonProperty("AdditionalNameType")
	private String additionalNameType;
	
	@JsonProperty("AdditionalName")
	private String additionalName;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getAdditionalNameType() {
		return additionalNameType;
	}

	public void setAdditionalNameType(String additionalNameType) {
		this.additionalNameType = additionalNameType;
	}

	public String getAdditionalName() {
		return additionalName;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}
	
	
}
