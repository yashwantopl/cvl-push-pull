package com.opl.mudra.api.user.model.loans;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the application_status_master database table.
 * 
 */
public class ApplicationStatusMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String code;

	private Long createdBy;

	private Date createdDate;

	private Boolean isActive;

	private Long modifiedBy;

	private Date modifiedDate;

	private String status;

	public ApplicationStatusMaster() {
		// Do nothing because of X and Y.
	}

	public ApplicationStatusMaster(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}