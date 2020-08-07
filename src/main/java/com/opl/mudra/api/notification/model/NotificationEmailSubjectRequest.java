package com.opl.mudra.api.notification.model;

import java.util.Date;
import java.util.List;


public class NotificationEmailSubjectRequest {

	private Long subjectId;
	
	private NotificationTemplateRequest template;
	
	private String subject;
	
	private String description;
	
	private Boolean isActive;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Long modifiedBy;
	
	private Long createdBy;
	
	private NotificationMasterRequest masterReuest;
	
	private List<LoanTypeRequest> loanType;
	private List<UserOrganisationMasterRequest> userOrg;
	
	
	public List<LoanTypeRequest> getLoanType() {
		return loanType;
	}

	public void setLoanType(List<LoanTypeRequest> loanType) {
		this.loanType = loanType;
	}

	public List<UserOrganisationMasterRequest> getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(List<UserOrganisationMasterRequest> userOrg) {
		this.userOrg = userOrg;
	}

	public NotificationMasterRequest getMasterReuest() {
		return masterReuest;
	}

	public void setMasterReuest(NotificationMasterRequest masterReuest) {
		this.masterReuest = masterReuest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public NotificationTemplateRequest getTemplate() {
		return template;
	}

	public void setTemplate(NotificationTemplateRequest template) {
		this.template = template;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "NotificationEmailSubjectRequest [subjectId=" + subjectId + ", template=" + template + ", subject="
				+ subject + ", description=" + description + ", isActive=" + isActive + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + ", createdBy=" + createdBy
				+ ", masterReuest=" + masterReuest + ", loanType=" + loanType + ", userOrg=" + userOrg + "]";
	}

	
}
