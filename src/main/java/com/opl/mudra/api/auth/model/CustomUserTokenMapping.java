package com.opl.mudra.api.auth.model;

import java.io.Serializable;

public class CustomUserTokenMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private String userName;
	private String refreshToken;
	private String accessToken;
	private String expiresIn;
	private boolean active;
	private Integer loginToken;
	private String userIp;
	private String userBrowser;
	private boolean isDomainLogin;
	private String modelNo;

	private String imeiNo;

	private String mobileOs;

	private String osVersion;

	private String appVersion;
	
	public CustomUserTokenMapping(){
		
	}
	
	public CustomUserTokenMapping(String userName, String accessToken){
		this.userName = userName;
		this.accessToken = accessToken;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(Integer loginToken) {
		this.loginToken = loginToken;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserBrowser() {
		return userBrowser;
	}

	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}

	public boolean isDomainLogin() {
		return isDomainLogin;
	}

	public void setDomainLogin(boolean isDomainLogin) {
		this.isDomainLogin = isDomainLogin;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getMobileOs() {
		return mobileOs;
	}

	public void setMobileOs(String mobileOs) {
		this.mobileOs = mobileOs;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}
