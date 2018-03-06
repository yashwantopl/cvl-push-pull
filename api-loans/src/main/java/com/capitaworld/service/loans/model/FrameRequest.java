package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FrameRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	
	private Long userId;
	
	private Long clientId;
	
	private Long ddrFormId;
	
	private List<Map<String, Object>> dataList;
	
	private int applicantType;
	
	private List<String> campaignCodes;
	

	public int getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(int applicantType) {
		this.applicantType = applicantType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public List<String> getCampaignCodes() {
		return campaignCodes;
	}

	public void setCampaignCodes(List<String> campaignCodes) {
		this.campaignCodes = campaignCodes;
	}

}
