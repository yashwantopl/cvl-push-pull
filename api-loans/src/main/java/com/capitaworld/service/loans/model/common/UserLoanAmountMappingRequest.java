package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit Date : 11-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoanAmountMappingRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;

	private Long productId;
	
	private Double amount;

	private Double minAmount;

	private Double maxAmount;

	private Long createdBy;

	private Date createdDate;

	private Boolean isActive;

	private Long modifiedBy;

	private Date modifiedDate;
	

	public UserLoanAmountMappingRequest() {
		
	}
	
	/**
	 * @param userId
	 * @param productId
	 */
	public UserLoanAmountMappingRequest(Long userId, Long productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	/**
	 * @param userId
	 * @param productId
	 * @param amount
	 */
	public UserLoanAmountMappingRequest(Long userId, Long productId, Double amount) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the minAmount
	 */
	public Double getMinAmount() {
		return minAmount;
	}

	/**
	 * @param minAmount the minAmount to set
	 */
	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @return the maxAmount
	 */
	public Double getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount the maxAmount to set
	 */
	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the modifiedBy
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLoanAmountMappingRequest [id=" + id + ", userId=" + userId + ", productId=" + productId
				+ ", amount=" + amount + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", isActive=" + isActive + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	
	
}
