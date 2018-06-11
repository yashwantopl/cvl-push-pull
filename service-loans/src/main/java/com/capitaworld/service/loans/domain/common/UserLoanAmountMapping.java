package com.capitaworld.service.loans.domain.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author harshit
 * Date : 09-Jun-2018
 * About :- USER LOAN AMOUNT MAPPING STOARE MIN AND MAX AMOUNT BASED ON FP USERID AND PRODUCT ID FOR DISBURSEMENT
 */
@Entity
@Table(name = "user_loan_amount_mapping")
public class UserLoanAmountMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "min_amount")
	private Double minAmount;
	
	@Column(name = "max_amount")
	private Double maxAmount;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLoanAmountMapping [id=" + id + ", userId=" + userId + ", productId=" + productId + ", minAmount="
				+ minAmount + ", maxAmount=" + maxAmount + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", isActive=" + isActive + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}	
	
	
	
	
	
	
	
	
}
