package com.opl.mudra.api.thirdparty.model;

import java.io.Serializable;

public class ThirdPartyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uadNo;

	private String userName;

	private String itPanNumber;

	private Long applicationId;

	private Long userId;
	
	private Boolean isMultipleBorrower;
	
	private Double amount;
	
	private Long productId;
	
	private Boolean isFirstCall;
	
	private Boolean isAverageCall;
	
	private Integer financialType;
	
	
	
	
	
	
	
	
	public Boolean getIsAverageCall() {
		return isAverageCall;
	}

	public void setIsAverageCall(Boolean isAverageCall) {
		this.isAverageCall = isAverageCall;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getIsFirstCall() {
		return isFirstCall;
	}

	public void setIsFirstCall(Boolean isFirstCall) {
		this.isFirstCall = isFirstCall;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the isMultipleBorrower
	 */
	public Boolean getIsMultipleBorrower() {
		return isMultipleBorrower;
	}

	/**
	 * @param isMultipleBorrower the isMultipleBorrower to set
	 */
	public void setIsMultipleBorrower(Boolean isMultipleBorrower) {
		this.isMultipleBorrower = isMultipleBorrower;
	}

	public String getUadNo() {
		return uadNo;
	}

	public void setUadNo(String uadNo) {
		this.uadNo = uadNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItPanNumber() {
		return itPanNumber;
	}

	public void setItPanNumber(String itPanNumber) {
		this.itPanNumber = itPanNumber;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFinancialType() {
		return financialType;
	}

	public void setFinancialType(Integer financialType) {
		this.financialType = financialType;
	}

	@Override
	public String toString() {
		return "ThirdPartyRequest [uadNo=" + uadNo + ", userName=" + userName + ", itPanNumber=" + itPanNumber
				+ ", applicationId=" + applicationId + ", userId=" + userId + ", isMultipleBorrower="
				+ isMultipleBorrower + ", amount=" + amount + ", productId=" + productId + ", isFirstCall="
				+ isFirstCall + ", isAverageCall=" + isAverageCall + "]";
	}

	
   
	
}
