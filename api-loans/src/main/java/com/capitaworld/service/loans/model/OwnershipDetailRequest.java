package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_ownership_details database table.
 * 
 */
public class OwnershipDetailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private String remarks;

	private Integer shareHoldingCategoryId;

	private Double stackPercentage;
	
	private Boolean isActive = true;
	
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public OwnershipDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getShareHoldingCategoryId() {
		return this.shareHoldingCategoryId;
	}

	public void setShareHoldingCategoryId(Integer shareHoldingCategoryId) {
		this.shareHoldingCategoryId = shareHoldingCategoryId;
	}

	public Double getStackPercentage() {
		return this.stackPercentage;
	}

	public void setStackPercentage(Double stackPercentage) {
		this.stackPercentage = stackPercentage;
	}

}