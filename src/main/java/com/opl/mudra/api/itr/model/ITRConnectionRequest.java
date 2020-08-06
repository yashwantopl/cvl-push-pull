package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRConnectionRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pan;
	private String redirectUrl;
	private String dob;
	private String fetchForm26AS;
	private String fetchITR;
	private String noOfYearsForForm26AS;
	private String noOfYearsForITR;
	private String type;
	private String clientTxnId;

	public ITRConnectionRequest() {
		super();
	}
	
	public ITRConnectionRequest(String pan,String dob) {
		super();
		this.pan = pan;
		this.dob = dob;
	}
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getFetchForm26AS() {
		return fetchForm26AS;
	}
	public void setFetchForm26AS(String fetchForm26AS) {
		this.fetchForm26AS = fetchForm26AS;
	}
	public String getFetchITR() {
		return fetchITR;
	}
	public void setFetchITR(String fetchITR) {
		this.fetchITR = fetchITR;
	}
	public String getNoOfYearsForForm26AS() {
		return noOfYearsForForm26AS;
	}
	public void setNoOfYearsForForm26AS(String noOfYearsForForm26AS) {
		this.noOfYearsForForm26AS = noOfYearsForForm26AS;
	}
	public String getNoOfYearsForITR() {
		return noOfYearsForITR;
	}
	public void setNoOfYearsForITR(String noOfYearsForITR) {
		this.noOfYearsForITR = noOfYearsForITR;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClientTxnId() {
		return clientTxnId;
	}
	public void setClientTxnId(String clientTxnId) {
		this.clientTxnId = clientTxnId;
	}
	
	public String toJson() {
		return "{ 'pan' :" + pan + ", 'redirectUrl':'" + redirectUrl + "', 'dob':" + dob + ", 'fetchForm26AS':"
				+ fetchForm26AS + ", 'fetchITR':" + fetchITR + ", 'noOfYearsForForm26AS':" + noOfYearsForForm26AS
				+ ", 'noOfYearsForITR': " + noOfYearsForITR + ", 'type':" + type + ", 'clientTxnId':" + clientTxnId + "}";
	}
	
	@Override
	public String toString() {
		return "StartAPIRequest [pan=" + pan + ", redirectUrl=" + redirectUrl + ", dob=" + dob + ", fetchForm26AS="
				+ fetchForm26AS + ", fetchITR=" + fetchITR + ", noOfYearsForForm26AS=" + noOfYearsForForm26AS
				+ ", noOfYearsForITR=" + noOfYearsForITR + ", type=" + type + ", clientTxnId=" + clientTxnId + "]";
	}
	
	
	
	
	
}
