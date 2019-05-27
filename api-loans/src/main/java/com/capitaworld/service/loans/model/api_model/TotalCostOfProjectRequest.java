package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalCostOfProjectRequest extends AuditorRequest  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Boolean isActive = true;

	private Double toBeIncurred;

	private Double alreadyIncurred;

	private Double totalCost;

	private Integer particularsId;
	
	private String particulars;

	public TotalCostOfProjectRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getToBeIncurred() {
		return toBeIncurred;
	}

	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}

	public Double getAlreadyIncurred() {
		return alreadyIncurred;
	}

	public void setAlreadyIncurred(Double alreadyIncurred) {
		this.alreadyIncurred = alreadyIncurred;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getParticularsId() {
		return particularsId;
	}

	public void setParticularsId(Integer particularsId) {
		this.particularsId = particularsId;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	@Override
	public String toString() {
		return "TotalCostOfProjectRequest [id=" + id + ", applicationId=" + applicationId + ", isActive=" + isActive
				+ ", toBeIncurred=" + toBeIncurred + ", alreadyIncurred=" + alreadyIncurred + ", totalCost=" + totalCost
				+ ", particularsId=" + particularsId + ", particulars=" + particulars + "]";
	}
		
	}