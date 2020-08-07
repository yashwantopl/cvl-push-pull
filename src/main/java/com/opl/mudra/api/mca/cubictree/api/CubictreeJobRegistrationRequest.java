package com.opl.mudra.api.mca.cubictree.api;

/**
 * @Author : Maaz Shaikh
 * Time :  5:17:28 PM
 **/
public class CubictreeJobRegistrationRequest {

	Long applicationId;
	Long userId;
	JobRegistrationPayload jobRegPayload;
	String url;
	Boolean isDirector;
	
	
	public Boolean getIsDirector() {
		return isDirector;
	}
	public void setIsDirector(Boolean isDirector) {
		this.isDirector = isDirector;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public JobRegistrationPayload getJobRegPayload() {
		return jobRegPayload;
	}
	public void setJobRegPayload(JobRegistrationPayload jobRegPayload) {
		this.jobRegPayload = jobRegPayload;
	}
	@Override
	public String toString() {
		return "CubictreeJobRegistrationRequest [applicationId=" + applicationId + ", userId=" + userId
				+ ", jobRegPayload=" + jobRegPayload + ", url=" + url + ", isDirector=" + isDirector + "]";
	}
	
	
}
