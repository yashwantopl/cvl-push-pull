package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Maaz Shaikh
 * Time :  2:39:01 pm
 **/
public class DealerUploadedFileResultResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String fileName;

	private Long storageId;
	
	private String resultIds;

	private Date createdDate;

	private boolean isActive;

	private Long createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

	public String getResultIds() {
		return resultIds;
	}

	public void setResultIds(String resultIds) {
		this.resultIds = resultIds;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
}