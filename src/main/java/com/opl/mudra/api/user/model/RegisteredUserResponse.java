package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisteredUserResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String emailId;
	private String mobileNumber;
	private Long userType;
	private String userTypeName;
	private Date signUpDate;
	private Date lastLoginDate;
	private String name;
	private Boolean isOtpVerified;
	private Boolean isEmailVerified;
	private List<?> loanList = Collections.emptyList();
	private List<RegisteredUserResponse> fundSeekerList = Collections.emptyList();
	private List<RegisteredUserResponse> fundProviderList = Collections.emptyList();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getUserType() {
		return userType;
	}
	public void setUserType(Long userType) {
		this.userType = userType;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getLoanList() {
		return loanList;
	}
	public void setLoanList(List<?> loanList) {
		this.loanList = loanList;
	}
	public Boolean getIsOtpVerified() {
		return isOtpVerified;
	}
	public void setIsOtpVerified(Boolean isOtpVerified) {
		this.isOtpVerified = isOtpVerified;
	}
	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}
	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	public List<RegisteredUserResponse> getFundSeekerList() {
		return fundSeekerList;
	}
	public void setFundSeekerList(List<RegisteredUserResponse> fundSeekerList) {
		this.fundSeekerList = fundSeekerList;
	}
	public List<RegisteredUserResponse> getFundProviderList() {
		return fundProviderList;
	}
	public void setFundProviderList(List<RegisteredUserResponse> fundProviderList) {
		this.fundProviderList = fundProviderList;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	
	
	
}
