package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditLogRequest extends MasterRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6032627760505326946L;

	private String request;

	private String response;
	
	private String requestType;

	private String status;

	private String failureReason;

	private String cmrFlag;

	private String cmr;

	private String cmrReason;

	private String cmrScoreName;

	private Long orgId;

	private String cibilApplicationId;
	
	private String cibilDocumentId;
	
	private String contentType;
	
	private String responseCode;
	
	private String bureauReference;

	public AuditLogRequest() {
		super();
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getCmr() {
		return cmr;
	}

	public void setCmr(String cmr) {
		this.cmr = cmr;
	}

	public String getCmrReason() {
		return cmrReason;
	}

	public void setCmrReason(String cmrReason) {
		this.cmrReason = cmrReason;
	}

	public String getCmrScoreName() {
		return cmrScoreName;
	}

	public void setCmrScoreName(String cmrScoreName) {
		this.cmrScoreName = cmrScoreName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCmrFlag() {
		return cmrFlag;
	}

	public void setCmrFlag(String cmrFlag) {
		this.cmrFlag = cmrFlag;
	}

	public String getCibilApplicationId() {
		return cibilApplicationId;
	}

	public void setCibilApplicationId(String cibilApplicationId) {
		this.cibilApplicationId = cibilApplicationId;
	}

	public String getCibilDocumentId() {
		return cibilDocumentId;
	}

	public void setCibilDocumentId(String cibilDocumentId) {
		this.cibilDocumentId = cibilDocumentId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getBureauReference() {
		return bureauReference;
	}

	public void setBureauReference(String bureauReference) {
		this.bureauReference = bureauReference;
	}
	
}
