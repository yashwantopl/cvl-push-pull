package com.opl.mudra.api.user.model;

import java.util.List;

public class LoginUsersResponse {
	
	private String access_token;
	private String token_type;
	private String expires_in;
	private String refresh_token;
	private String scope;
	private Long userType;
	private String message;
	private Integer status;
	private String email;
	private List<String> campaignCode;
	private Integer loginToken;
	private Long applicationId;
	private Boolean isFpUserFillProfile;
	private Boolean isSpUserFillProfile;
	private Boolean isNpUserFillProfile;
	private Long userOrgId;
	private Long isEmailVerified;
	private Long userRoleId;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Long getUserType() {
		return userType;
	}
	public void setUserType(Long userType) {
		this.userType = userType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(Integer loginToken) {
		this.loginToken = loginToken;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Boolean getIsFpUserFillProfile() {
		return isFpUserFillProfile;
	}
	public void setIsFpUserFillProfile(Boolean isFpUserFillProfile) {
		this.isFpUserFillProfile = isFpUserFillProfile;
	}
	public Boolean getIsSpUserFillProfile() {
		return isSpUserFillProfile;
	}
	public void setIsSpUserFillProfile(Boolean isSpUserFillProfile) {
		this.isSpUserFillProfile = isSpUserFillProfile;
	}
	public Boolean getIsNpUserFillProfile() {
		return isNpUserFillProfile;
	}
	public void setIsNpUserFillProfile(Boolean isNpUserFillProfile) {
		this.isNpUserFillProfile = isNpUserFillProfile;
	}
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	
	public List<String> getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(List<String> campaignCode) {
		this.campaignCode = campaignCode;
	}
	public Long getIsEmailVerified() {
		return isEmailVerified;
	}
	public void setIsEmailVerified(Long isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	
	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}	

}
