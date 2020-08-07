package com.opl.mudra.api.mca.cubictree.api;
/**
 * @Author : Maaz Shaikh
 * Time :  12:12:00 PM
 **/
public class CubictreeReportRequest {

	private String url;
	private String jobId;
	private String token;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
