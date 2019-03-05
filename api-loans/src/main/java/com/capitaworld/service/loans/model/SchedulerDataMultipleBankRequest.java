package com.capitaworld.service.loans.model;

import java.sql.Timestamp;
import java.util.Date;

public class SchedulerDataMultipleBankRequest {
	
	private Long id;
	private Long  userId;
	private Long applicationId;
	private Long proposalId;
	private Timestamp inpricipleDate;
	private Date inpricipleUtilDate;
	private Integer mailStatus;
	private Integer smsStatus;
	private Integer sysNotificationStatus;
	private Integer dayDiffrence;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private Integer isActive;
	
	
	public Date getInpricipleUtilDate() {
		return inpricipleUtilDate;
	}
	public void setInpricipleUtilDate(Date inpricipleUtilDate) {
		this.inpricipleUtilDate = inpricipleUtilDate;
	}
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
	public Timestamp getInpricipleDate() {
		return inpricipleDate;
	}
	public void setInpricipleDate(Timestamp inpricipleDate) {
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
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "SchedulerDataMultipleBankRequest [id=" + id + ", userId=" + userId + ", applicationId=" + applicationId
				+ ", proposalId=" + proposalId + ", inpricipleDate=" + inpricipleDate + ", mailStatus=" + mailStatus
				+ ", smsStatus=" + smsStatus + ", sysNotificationStatus=" + sysNotificationStatus + ", dayDiffrence="
				+ dayDiffrence + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", isActive="
				+ isActive + "]";
	}
	
	 

}
