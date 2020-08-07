package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "accountNumber", "disputeComments", "status", "resolvedDate","type" })
public class DisputeDetails {

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("disputeComments")
	private String disputeComments;

	@JsonProperty("status")
	private String status;

	@JsonProperty("resolvedDate")
	private String resolvedDate;

	@JsonProperty("type")
	private String type;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the disputeComments
	 */
	public String getDisputeComments() {
		return disputeComments;
	}

	/**
	 * @param disputeComments
	 *            the disputeComments to set
	 */
	public void setDisputeComments(String disputeComments) {
		this.disputeComments = disputeComments;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the resolvedDate
	 */
	public String getResolvedDate() {
		return resolvedDate;
	}

	/**
	 * @param resolvedDate
	 *            the resolvedDate to set
	 */
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

}
