package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_ownership_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_ownership_details")
public class OwnershipDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="application_id")
	private Long applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_Date;

	@Lob
	private String remarks;

	@Column(name="share_holding_category_id")
	private Integer shareHoldingCategoryId;

	@Column(name="stack_percentage")
	private Double stackPercentage;

	public OwnershipDetail() {
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

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModified_Date() {
		return this.modified_Date;
	}

	public void setModified_Date(Date modified_Date) {
		this.modified_Date = modified_Date;
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