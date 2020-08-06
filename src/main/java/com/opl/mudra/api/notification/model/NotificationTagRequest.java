package com.opl.mudra.api.notification.model;

import java.util.Date;

public class NotificationTagRequest {
	

	private Long tagId;
	private String tagName;
	private Long subjectId;
	private Long templateId;
	private Date createdDate;
	private Long createdBy;
	private Boolean isActive;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "NotificationTags [tagId=" + tagId + ", tagName=" + tagName + ", subjectId=" + subjectId
				+ ", templateId=" + templateId + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", isActive=" + isActive + "]";
	}
	
	

}
