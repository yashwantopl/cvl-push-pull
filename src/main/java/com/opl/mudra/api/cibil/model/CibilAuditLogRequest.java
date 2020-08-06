package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilAuditLogRequest implements Serializable {
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
	
	private Integer provider;
	
	private String pan;
	
	private String bureauReference;
	
	private String cibilApplicationId;
	
	private String cibilDocumentId;
	
	
//	private String bureauReference;

	public CibilAuditLogRequest() {
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

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getBureauReference() {
		return bureauReference;
	}

	public void setBureauReference(String bureauReference) {
		this.bureauReference = bureauReference;
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

	@Override
	public String toString() {
		return "CibilAuditLogRequest [request=" + request + ", response=" + response + ", requestType=" + requestType
				+ ", status=" + status + ", failureReason=" + failureReason + ", cmrFlag=" + cmrFlag + ", cmr=" + cmr
				+ ", cmrReason=" + cmrReason + ", cmrScoreName=" + cmrScoreName + ", orgId=" + orgId + "]";
	}
}
