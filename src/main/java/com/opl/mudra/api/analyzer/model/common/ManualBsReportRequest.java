package com.opl.mudra.api.analyzer.model.common;

public class ManualBsReportRequest {

	private String applicationId;
	private Long bsMasterId;
	private Long profileId;
	private Long userId;
	private String name;
	private String ifsc;
	private String accountNumber;
	private String confirmAccountNumber;
	private Integer sinceYear;
	private Integer sinceMonth;
	private String accountHolderName;
	private String manualIfscCode;
	private String manualBankAccNo;
	private String manualConfBankAccNo;
	private Integer manualSinceYear;
	private Integer manualSinceMonth;
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Long getBsMasterId() {
		return bsMasterId;
	}
	public void setBsMasterId(Long bsMasterId) {
		this.bsMasterId = bsMasterId;
	}
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getConfirmAccountNumber() {
		return confirmAccountNumber;
	}
	public void setConfirmAccountNumber(String confirmAccountNumber) {
		this.confirmAccountNumber = confirmAccountNumber;
	}
	public Integer getSinceYear() {
		return sinceYear;
	}
	public void setSinceYear(Integer sinceYear) {
		this.sinceYear = sinceYear;
	}
	public Integer getSinceMonth() {
		return sinceMonth;
	}
	public void setSinceMonth(Integer sinceMonth) {
		this.sinceMonth = sinceMonth;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getManualIfscCode() {
		return manualIfscCode;
	}
	public void setManualIfscCode(String manualIfscCode) {
		this.manualIfscCode = manualIfscCode;
	}
	public String getManualBankAccNo() {
		return manualBankAccNo;
	}
	public void setManualBankAccNo(String manualBankAccNo) {
		this.manualBankAccNo = manualBankAccNo;
	}
	public String getManualConfBankAccNo() {
		return manualConfBankAccNo;
	}
	public void setManualConfBankAccNo(String manualConfBankAccNo) {
		this.manualConfBankAccNo = manualConfBankAccNo;
	}
	public Integer getManualSinceYear() {
		return manualSinceYear;
	}
	public void setManualSinceYear(Integer manualSinceYear) {
		this.manualSinceYear = manualSinceYear;
	}
	public Integer getManualSinceMonth() {
		return manualSinceMonth;
	}
	public void setManualSinceMonth(Integer manualSinceMonth) {
		this.manualSinceMonth = manualSinceMonth;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
