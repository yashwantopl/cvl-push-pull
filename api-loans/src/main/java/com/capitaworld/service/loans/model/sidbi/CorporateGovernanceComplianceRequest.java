package com.capitaworld.service.loans.model.sidbi;

import java.io.Serializable;
import java.util.Date;

public class CorporateGovernanceComplianceRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long applicationId;
	private Integer corporateGovernanceId;
	private Integer selectedOption;
	//	This field is used for the enum corporateGovernance value
	private String value;
	private String updatedValue;
	private Boolean isActive;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Long userId;
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
	public Integer getCorporateGovernanceId() {
		return corporateGovernanceId;
	}
	public void setCorporateGovernanceId(Integer corporateGovernanceId) {
		this.corporateGovernanceId = corporateGovernanceId;
	}
	public Integer getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(Integer selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getUpdatedValue() {
		return updatedValue;
	}
	public void setUpdatedValue(String updatedValue) {
		this.updatedValue = updatedValue;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CorporateGovernanceComplianceRequest [id=" + id + ", applicationId=" + applicationId
				+ ", corporateGovernanceId=" + corporateGovernanceId + ", selectedOption=" + selectedOption + ", value="
				+ value + ", updatedValue=" + updatedValue + ", isActive=" + isActive + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", userId=" + userId + "]";
	}


}
