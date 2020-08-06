package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author lxb170
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "disputeDetails", "createdDate", "status" })
public class ConsumerDisputes {
	@JsonProperty("disputeDetails")
	private List<DisputeDetails> disputeDetails;
	@JsonProperty("createdDate")
	private String createdDate;
	@JsonProperty("status")
	private String status;

	@JsonProperty("disputeDetails")
	public List<DisputeDetails> getDisputeDetails() {
		return disputeDetails;
	}

	@JsonProperty("disputeDetails")
	public void setDisputeDetails(List<DisputeDetails> disputeDetails) {
		this.disputeDetails = disputeDetails;
	}

	@JsonProperty("createdDate")
	public String getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDate")
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

}
