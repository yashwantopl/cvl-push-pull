/**
 * 
 */
package com.opl.mudra.api.fraudanalytics.model;

/**
 * @author sanket
 *
 */
public class AnalyticsRequest {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnalyticsRequest [userId=" + userId + ", applicationId=" + applicationId + ", data=" + data + "]";
	}

	private Long userId;
	
	private Long applicationId;
	
	private Object data;
	
	private Boolean isNtb;
	
	/*private String generalConfig;*/
	private String credentialUserName;
	private String credentialpassword;
	private Boolean isHunterOn;
	private String url;
	private String controlBlockMsme;
	private String controlBlockNtb;
	
	
	
	

	public Boolean getIsNtb() {
		return isNtb;
	}

	public void setIsNtb(Boolean isNtb) {
		this.isNtb = isNtb;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}



	

	public String getCredentialUserName() {
		return credentialUserName;
	}

	public void setCredentialUserName(String credentialUserName) {
		this.credentialUserName = credentialUserName;
	}

	public String getCredentialpassword() {
		return credentialpassword;
	}

	public void setCredentialpassword(String credentialpassword) {
		this.credentialpassword = credentialpassword;
	}

	

	public Boolean getIsHunterOn() {
		return isHunterOn;
	}

	public void setIsHunterOn(Boolean isHunterOn) {
		this.isHunterOn = isHunterOn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getControlBlockMsme() {
		return controlBlockMsme;
	}

	public void setControlBlockMsme(String controlBlockMsme) {
		this.controlBlockMsme = controlBlockMsme;
	}

	public String getControlBlockNtb() {
		return controlBlockNtb;
	}

	public void setControlBlockNtb(String controlBlockNtb) {
		this.controlBlockNtb = controlBlockNtb;
	}
	
	
}
