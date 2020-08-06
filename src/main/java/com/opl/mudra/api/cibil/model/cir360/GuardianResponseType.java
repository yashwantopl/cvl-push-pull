package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GuardianResponseType {

	@JsonProperty("InquiryValue")
	private GuardianDetails inquiryValue;
	
	@JsonProperty("ResponseValue")
	private GuardianDetails repsonseValue;

	public GuardianDetails getInquiryValue() {
		return inquiryValue;
	}

	public void setInquiryValue(GuardianDetails inquiryValue) {
		this.inquiryValue = inquiryValue;
	}

	public GuardianDetails getRepsonseValue() {
		return repsonseValue;
	}

	public void setRepsonseValue(GuardianDetails repsonseValue) {
		this.repsonseValue = repsonseValue;
	}
	
	
}
