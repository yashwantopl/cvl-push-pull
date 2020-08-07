package com.opl.mudra.api.fraudanalytics.model;

public class McaFraudAnalysisRequest {

	private Long applicationId;
	
	private Long userId;
	
	private String cin;

	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	
	
}
