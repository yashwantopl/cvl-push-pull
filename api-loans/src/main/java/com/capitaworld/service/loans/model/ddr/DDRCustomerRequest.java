package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCustomerRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String customerName;
	private Boolean isFilled;
	private Long applicationId;
	private Long proposalMappingId;
	private Long ddrFormId;
	private Long userId;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Boolean getIsFilled() {
		return isFilled;
	}
	public void setIsFilled(Boolean isFilled) {
		this.isFilled = isFilled;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getDdrFormId() {
		return ddrFormId;
	}
	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}

	@Override
	public String toString() {
		return "DDRCustomerRequest [customerId=" + customerId + ", customerName=" + customerName + ", isFilled="
				+ isFilled + ", applicationId=" + applicationId + ", ddrFormId=" + ddrFormId + ", userId=" + userId
				+ "]";
	}
	
	
}
