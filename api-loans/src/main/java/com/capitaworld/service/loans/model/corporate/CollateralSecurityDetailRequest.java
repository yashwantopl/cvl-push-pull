package com.capitaworld.service.loans.model.corporate;

public class CollateralSecurityDetailRequest {

	private Long id;
	
	private Long applicationId;
	
	private String collateralType;
	
	private String otherCollateral;
	
	private Double collateralAmount;
	

	public Long getId() {
		return id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public String getOtherCollateral() {
		return otherCollateral;
	}

	public Double getCollateralAmount() {
		return collateralAmount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public void setOtherCollateral(String otherCollateral) {
		this.otherCollateral = otherCollateral;
	}

	public void setCollateralAmount(Double collateralAmount) {
		this.collateralAmount = collateralAmount;
	}
	
	
	
	
}
