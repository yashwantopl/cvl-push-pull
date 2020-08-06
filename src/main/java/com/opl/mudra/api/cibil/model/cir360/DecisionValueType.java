package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DecisionValueType {

	@JsonProperty("EligibleAmount")
	private String eligibleAmount;
	
	
	@JsonProperty("Reasons")
	private List<ReasonType> reason;


	public String getEligibleAmount() {
		return eligibleAmount;
	}


	public void setEligibleAmount(String eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}


	public List<ReasonType> getReason() {
		if (null == reason) {
			reason = new ArrayList<ReasonType>();
		}
		return reason;
	}


	public void setReason(List<ReasonType> reason) {
		this.reason = reason;
	}
	
}
