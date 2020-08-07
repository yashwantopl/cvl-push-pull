package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class PlacedOrderNotification {
	
/*	@JsonProperty("error-code")
	private String errorCode;

	@JsonProperty("error-status")
	private String errorStatus;

	@JsonProperty("error-description")
	private String errorDescription;*/

	@JsonProperty("reference-id")
	private String referenceId;
	
	@JsonProperty("webhook_key")
	private String webhookKey;
	
	private String status;
	
	private String message;

	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the webhookKey
	 */
	public String getWebhookKey() {
		return webhookKey;
	}

	/**
	 * @param webhookKey the webhookKey to set
	 */
	public void setWebhookKey(String webhookKey) {
		this.webhookKey = webhookKey;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlacedOrderNotification [referenceId=" + referenceId + ", webhookKey=" + webhookKey + ", status="
				+ status + ", message=" + message + "]";
	}

	
	

}
