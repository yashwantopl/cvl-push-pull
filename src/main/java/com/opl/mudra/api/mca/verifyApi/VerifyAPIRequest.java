package com.opl.mudra.api.mca.verifyApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.mca.CommonRequest;
import com.opl.mudra.api.mca.McaCommonUtils;
import com.opl.mudra.api.mca.verifyApi.directorDetail.DirectorDetailRequest;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class VerifyAPIRequest extends CommonRequest {
	
	private VerifyAPIDINPANRequest verifyAPIDINPANRequest;
	private Long applicationId;
	
	private String applicationType;
	
	@JsonProperty("reference-id")
	private String referenceId;
	
	private String din;
	private String status;
	private String message;
	private String webhook_token;
	private String referenseId;
	
	@JsonProperty("reference-id-status")
	private String referenceIdStatus;
	
	@JsonProperty("status-description")
	private String statusDescription;
	
	private Long userId;
	
	private DirectorDetailRequest directorDetailRequest;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public String getReferenseId() {
		return referenseId;
	}

	public void setReferenseId(String referenseId) {
		this.referenseId = referenseId;
	}

	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the verifyAPIDINPANRequest
	 */
	public VerifyAPIDINPANRequest getVerifyAPIDINPANRequest() {
		return verifyAPIDINPANRequest;
	}

	/**
	 * @param verifyAPIDINPANRequest the verifyAPIDINPANRequest to set
	 */
	public void setVerifyAPIDINPANRequest(VerifyAPIDINPANRequest verifyAPIDINPANRequest) {
		this.verifyAPIDINPANRequest = verifyAPIDINPANRequest;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWebhook_token() {
		return webhook_token;
	}

	public void setWebhook_token(String webhook_token) {
		this.webhook_token = webhook_token;
	}

	public String getReferenceIdStatus() {
		return referenceIdStatus;
	}

	public void setReferenceIdStatus(String referenceIdStatus) {
		this.referenceIdStatus = referenceIdStatus;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public DirectorDetailRequest getDirectorDetailRequest() {
		return directorDetailRequest;
	}

	public void setDirectorDetailRequest(
			DirectorDetailRequest directorDetailRequest) {
		this.directorDetailRequest = directorDetailRequest;
	}

	@Override
	public String toString() {
		return McaCommonUtils.convertObjectToString(this);
	}
	
	

}
