package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fs_corporate_collateral_security_details")
public class CollateralSecurityDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "collateral_type")
	private String collateralType;
	
	@Column(name = "other_collateral")
	private String otherCollateral;
	
	@Column(name = "collateral_amount")
	private Double collateralAmount;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_active")
	private Boolean isActive;

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

	public Long getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Boolean getIsActive() {
		return isActive;
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

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
}
