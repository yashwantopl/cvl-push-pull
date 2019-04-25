package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HLOnefromResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private Long applicationId;
	private Long coAppId;
	private Boolean isOneformComplete;
	private Boolean isCibilComplete;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	public Boolean getIsOneformComplete() {
		return isOneformComplete;
	}
	public void setIsOneformComplete(Boolean isOneformComplete) {
		this.isOneformComplete = isOneformComplete;
	}
	public Boolean getIsCibilComplete() {
		return isCibilComplete;
	}
	public void setIsCibilComplete(Boolean isCibilComplete) {
		this.isCibilComplete = isCibilComplete;
	}
	
	
}
