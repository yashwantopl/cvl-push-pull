package com.capitaworld.service.loans.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fs_multibank_7days_scheduler_data")
public class SchedulerDataMultipleBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name="user_id")
	private Long  userId;
	@Column(name="application_id")
	private Long applicationId;
	@Column(name="proposal_id")
	private Long proposalId;
	@Column(name="inpriciple_date")
	private Timestamp inpricipleDate;
	@Column(name="mail_status")
	private Integer mailStatus;
	@Column(name="sms_status")
	private Integer smsStatus;
	@Column(name="sys_notification_status")
	private Integer sysNotificationStatus;
	@Column(name="day_diffrence")
	private Integer dayDiffrence;
	@Column(name="created_date")
	private Timestamp createdDate;
	@Column(name="modified_date")
	private Timestamp modifiedDate;
	@Column(name="is_active")
	private Integer isActive;
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
		return "SchedulerDataMultipleBank [id=" + id + ", userId=" + userId + ", applicationId=" + applicationId
				+ ", proposalId=" + proposalId + ", inpricipleDate=" + inpricipleDate + ", mailStatus=" + mailStatus
				+ ", smsStatus=" + smsStatus + ", sysNotificationStatus=" + sysNotificationStatus + ", dayDiffrence="
				+ dayDiffrence + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", isActive="
				+ isActive + "]";
	}
	
}
