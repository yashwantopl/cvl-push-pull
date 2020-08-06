package com.opl.mudra.api.notification.model;

import java.util.Date;

/**
 * @author maaz.shaikh
 *
 */
public class LoanTypeRequest {
	
	private Long id;
	private String loanTypeName;
	private Integer loanTypeId;
	private Integer buisnessTypeId;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public Integer getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Integer getBuisnessTypeId() {
		return buisnessTypeId;
	}

	public void setBuisnessTypeId(Integer buisnessTypeId) {
		this.buisnessTypeId = buisnessTypeId;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "LoanTypeRequest [id=" + id + ", loanTypeName=" + loanTypeName + ", loanTypeId=" + loanTypeId
				+ ", buisnessTypeId=" + buisnessTypeId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", isActive=" + isActive + "]";
	}

	 

}
