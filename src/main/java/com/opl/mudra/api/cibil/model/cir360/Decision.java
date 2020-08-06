package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Decision {
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Error")
	private ErrorDetails error;
	
	@JsonProperty("DecisionList")
	private List<DecisionType> decisionList;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	public List<DecisionType> getDecisionList() {
		if(decisionList==null){
			decisionList = new ArrayList<DecisionType>();
		}
		return decisionList;
	}

	public void setDecisionList(List<DecisionType> decisionList) {
		this.decisionList = decisionList;
	}
	
	
}
