package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NTBRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3620949917123205350L;
	
	private Long directorId;
	
	private Long applicationId;
	
	private Integer busineeTypeId;
	
	private Integer apiFlag;
	
	private Boolean isLast;
	
	private Long userId;

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public Integer getBusineeTypeId() {
		return busineeTypeId;
	}

	public void setBusineeTypeId(Integer busineeTypeId) {
		this.busineeTypeId = busineeTypeId;
	}

	public Integer getApiFlag() {
		return apiFlag;
	}

	public void setApiFlag(Integer apiFlag) {
		this.apiFlag = apiFlag;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "NTBRequest [directorId=" + directorId + ", applicationId=" + applicationId + ", busineeTypeId="
				+ busineeTypeId + ", apiFlag=" + apiFlag + ", isLast=" + isLast + ", userId=" + userId + "]";
	}
}
