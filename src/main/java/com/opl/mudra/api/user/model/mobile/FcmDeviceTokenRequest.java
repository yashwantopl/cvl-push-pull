/**
 * 
 */
package com.opl.mudra.api.user.model.mobile;

/**
 * @author vijay.chauhan
 *
 */

public class FcmDeviceTokenRequest  {


	private Long userId;
	private String userEmail;
	private String apiCode;
	private Long apiCodeId;
	private String deviceToken;
	private String serverKey;
	private String apiType;
	private Boolean isDeviceTokenActive;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public Long getApiCodeId() {
		return apiCodeId;
	}
	public void setApiCodeId(Long apiCodeId) {
		this.apiCodeId = apiCodeId;
	}
	public String getServerKey() {
		return serverKey;
	}
	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	public Boolean getIsDeviceTokenActive() {
		return isDeviceTokenActive;
	}
	public void setIsDeviceTokenActive(Boolean isDeviceTokenActive) {
		this.isDeviceTokenActive = isDeviceTokenActive;
	}
	@Override
	public String toString() {
		return "FcmDeviceTokenRequest [userId=" + userId + ", userEmail=" + userEmail + ", apiCode=" + apiCode
				+ ", apiCodeId=" + apiCodeId + ", deviceToken=" + deviceToken + ", serverKey=" + serverKey
				+ ", apiType=" + apiType + ", isDeviceTokenActive=" + isDeviceTokenActive + "]";
	}

	
}
