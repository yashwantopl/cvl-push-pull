package com.opl.mudra.api.analyzer.model.common;

public class ManualBsReportRequest {

	private String applicationId;
	private Long bsMasterId;
	private Long profileId;
	private String name;
	private String ifsc;
	private String accountNumber;
	private String confirmAccountNumber;
	private Integer sinceYear;
	private Integer sinceMonth;
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
	
}
