package com.opl.mudra.api.analyzer.model.common;

import java.util.Date;

public class StatementUploadHistoryRequest {

	private Long id;
	
	private Long applicationId;
	
	private String bankId;
	
	private String api;
	
	private String errorType;
	
	private String error;
	
	private String txnId;
	
	private String perfiosTransactionId;
	
	private String fileName;

	private Date createdDate;
	
	private Date modifiedDate;
	
	private Boolean isSuccess;
	
	private String remark;
	
	private Long dmsId;
	
	private String mobile;
	
	private String email;

	public Long getId() {
		return id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public String getBankId() {
		return bankId;
	}

	public String getApi() {
		return api;
	}

	public String getErrorType() {
		return errorType;
	}

	public String getError() {
		return error;
	}

	public String getTxnId() {
		return txnId;
	}

	public String getPerfiosTransactionId() {
		return perfiosTransactionId;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public String getRemark() {
		return remark;
	}

	public Long getDmsId() {
		return dmsId;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public void setPerfiosTransactionId(String perfiosTransactionId) {
		this.perfiosTransactionId = perfiosTransactionId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setDmsId(Long dmsId) {
		this.dmsId = dmsId;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
