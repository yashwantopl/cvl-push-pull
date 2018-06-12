package com.capitaworld.service.loans.utils;

import java.io.Serializable;
import java.util.Date;

public class AuditActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long createdBy;

	private Date createdDate;
	
	private Long modifiedBy;
	
	private Date modifiedDate;
	
	private Boolean isActive;

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "AuditActivity [createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", isActive=" + isActive + "]";
	}
	
	

}
