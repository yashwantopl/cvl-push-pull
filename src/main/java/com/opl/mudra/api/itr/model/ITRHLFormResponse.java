package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 29-March-2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRHLFormResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long coAppId;
	private Boolean isSuccess;
	private Boolean isSkip;
	private String name;
	
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
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsSkip() {
		return isSkip;
	}
	public void setIsSkip(Boolean isSkip) {
		this.isSkip = isSkip;
	}

	

}
