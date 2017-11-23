package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)

public class MsmeScoreRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long applicationId;
	@JsonProperty
	private boolean isMsmeScoreRequired;
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public boolean isMsmeScoreRequired() {
		return isMsmeScoreRequired;
	}
	public void setMsmeScoreRequired(boolean isMsmeScoreRequired) {
		this.isMsmeScoreRequired = isMsmeScoreRequired;
	}
	
	@Override
	public String toString() {
		return "MsmeScoreRequest [applicationId=" + applicationId + ", isMsmeScoreRequired=" + isMsmeScoreRequired
				+ "]";
	}
	
	public MsmeScoreRequest(Long applicationId, boolean isMsmeScoreRequired) {
		super();
		this.applicationId = applicationId;
		this.isMsmeScoreRequired = isMsmeScoreRequired;
	}
	public MsmeScoreRequest() {
		super();
	}
	
}
