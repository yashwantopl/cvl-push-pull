package com.opl.mudra.api.notification.model;

import java.io.Serializable;
import java.util.Date;

public class SchedulerDataMultipleBankRequest  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long  userId;
	private Long applicationId;
	private Long proposalId;
	private Date inpricipleDate;
	private Integer mailStatus;
	private Integer smsStatus;
	private Integer sysNotificationStatus;
	private Integer dayDiffrence;
	private Date createdDate;
	private Date modifiedDate;
	private Integer isActive;
	private Long orgId;
	private Integer emailType;
	private Integer loanTypeId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public Date getInpricipleDate() {
		return inpricipleDate;
	}
	public void setInpricipleDate(Date inpricipleDate) {
		this.inpricipleDate = inpricipleDate;
	}
	public Integer getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}
	public Integer getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(Integer smsStatus) {
		this.smsStatus = smsStatus;
	}
	public Integer getSysNotificationStatus() {
		return sysNotificationStatus;
	}
	public void setSysNotificationStatus(Integer sysNotificationStatus) {
		this.sysNotificationStatus = sysNotificationStatus;
	}
	public Integer getDayDiffrence() {
		return dayDiffrence;
	}
	public void setDayDiffrence(Integer dayDiffrence) {
		this.dayDiffrence = dayDiffrence;
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
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getEmailType() {
		return emailType;
	}
	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}
	public Integer getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	@Override
	public String toString() {
		return "SchedulerDataMultipleBankRequest [id=" + id + ", userId=" + userId + ", applicationId=" + applicationId
				+ ", proposalId=" + proposalId + ", inpricipleDate=" + inpricipleDate + ", mailStatus=" + mailStatus
				+ ", smsStatus=" + smsStatus + ", sysNotificationStatus=" + sysNotificationStatus + ", dayDiffrence="
				+ dayDiffrence + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", isActive="
				+ isActive + ", orgId=" + orgId + ", emailType=" + emailType + ", loanTypeId=" + loanTypeId + "]";
	}

	
}
