package com.opl.mudra.api.cibil_integration.msme.request.sbi;

import java.io.Serializable;

public class SBIReportRequestConsumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer requestId;
	private String userId;
	private String applicationId;

	public SBIReportRequestConsumer() {}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

}
