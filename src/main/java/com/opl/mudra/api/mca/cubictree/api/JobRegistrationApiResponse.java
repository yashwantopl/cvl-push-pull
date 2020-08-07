package com.opl.mudra.api.mca.cubictree.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author : Maaz Shaikh
 * Time :  5:03:01 PM
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class JobRegistrationApiResponse {

	@JsonProperty(value="job_id")
	private String jobId;
	
	@JsonProperty(value="status")
	private Boolean status;
	
	@JsonProperty(value="message")
	private String message;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JobRegistrationApiResponse [jobId=" + jobId + ", status=" + status + ", message=" + message + "]";
	}
	
	
	
}
