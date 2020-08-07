package com.opl.mudra.api.notification.model;

import java.sql.Timestamp;


/**
 * @author maaz.shaikh
 *
 */ 
public class NotificationTemplateRequest {

	private Long id;

	private Long createdBy;

	private Timestamp createdDate;

	private Boolean isWorking;
	
	private String isActive;

	private Long modifiedBy;

	private Timestamp modifiedDate;

	private String templateName;
	
	private String notificationTemplate;

	private Long notificationTypeId;
	
	private Long organizationId;
	
	private String alias;
	
	private Boolean isBccActive;
	
	private String bccEmail;
	
	private Boolean isSinglePageTemplate;
	
	private Boolean isWorkWithOldFlow;

	private UserOrganisationMasterRequest userOrg;
	
	private LoanTypeRequest loanTypeId; 
	
	private NotificationMasterRequest masterReq;
	
	private String notificationDescription;

	
	public String getNotificationDescription() {
		return notificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
	}

	public NotificationMasterRequest getMasterReq() {
		return masterReq;
	}

	public void setMasterReq(NotificationMasterRequest masterReq) {
		this.masterReq = masterReq;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getNotificationTemplate() {
		return notificationTemplate;
	}

	public void setNotificationTemplate(String notificationTemplate) {
		this.notificationTemplate = notificationTemplate;
	}

	public Long getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getIsBccActive() {
		return isBccActive;
	}

	public void setIsBccActive(Boolean isBccActive) {
		this.isBccActive = isBccActive;
	}

	public String getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

	public Boolean getIsSinglePageTemplate() {
		return isSinglePageTemplate;
	}

	public void setIsSinglePageTemplate(Boolean isSinglePageTemplate) {
		this.isSinglePageTemplate = isSinglePageTemplate;
	}

	public Boolean getIsWorkWithOldFlow() {
		return isWorkWithOldFlow;
	}

	public void setIsWorkWithOldFlow(Boolean isWorkWithOldFlow) {
		this.isWorkWithOldFlow = isWorkWithOldFlow;
	}


	public UserOrganisationMasterRequest getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(UserOrganisationMasterRequest userOrg) {
		this.userOrg = userOrg;
	}

	public LoanTypeRequest getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(LoanTypeRequest loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Boolean getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

	@Override
	public String toString() {
		return "NotificationTemplateRequest [id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", isWorking=" + isWorking + ", isActive=" + isActive + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", templateName=" + templateName + ", notificationTemplate="
				+ notificationTemplate + ", notificationTypeId=" + notificationTypeId + ", organizationId="
				+ organizationId + ", alias=" + alias + ", isBccActive=" + isBccActive + ", bccEmail=" + bccEmail
				+ ", isSinglePageTemplate=" + isSinglePageTemplate + ", isWorkWithOldFlow=" + isWorkWithOldFlow
				+ ", userOrg=" + userOrg + ", loanTypeId=" + loanTypeId + ", masterReq=" + masterReq
				+ ", notificationDescription=" + notificationDescription + "]";
	}

	 
	
	
}
