package com.capitaworld.service.loans.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_cw_audit_trail")
public class BankCWAuditTrailDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="org_id")
	private Long orgId;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="bank_request")
	private String bankRequest;
	
	@Column(name="cw_response")
	private String cwResponse;
	
	private String status;
	
	private String msg;
	
	@Column(name="statement_type")
	private Integer statementType;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="is_active")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getBankRequest() {
		return bankRequest;
	}

	public void setBankRequest(String bankRequest) {
		this.bankRequest = bankRequest;
	}

	public String getCwResponse() {
		return cwResponse;
	}

	public void setCwResponse(String cwResponse) {
		this.cwResponse = cwResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatementType() {
		return statementType;
	}

	public void setStatementType(Integer statementType) {
		this.statementType = statementType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BankCWAuditTrailDomain [id=" + id + ", orgId=" + orgId + ", applicationId=" + applicationId
				+ ", bankRequest=" + bankRequest + ", cwResponse=" + cwResponse + ", status=" + status + ", msg=" + msg
				+ ", statementType=" + statementType + ", createdDate=" + createdDate + ", isActive=" + isActive + "]";
	}

}
