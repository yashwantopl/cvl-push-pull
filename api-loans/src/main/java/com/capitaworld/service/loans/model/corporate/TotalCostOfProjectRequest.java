package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;

/**
 * The persistent class for the fs_corporate_achievement_details database table.
 * 
 */
public class TotalCostOfProjectRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Boolean isActive = true;

	private Double toBeIncurred;

	private Double alreadyIncurred;

	private Double totalCost;

	private Integer particularsId;

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

	}