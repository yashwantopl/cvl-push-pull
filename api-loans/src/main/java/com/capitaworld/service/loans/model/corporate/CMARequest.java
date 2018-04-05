package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CMARequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private Long applicationId;
	private List<LiabilitiesDetailsRequest> liabilitiesRequestList;
	private List<AssetsDetailsRequest> assetsRequestList;
	private List<OperatingStatementDetailsRequest> operatingStatementRequestList;
	
	
	public CMARequest() {
		liabilitiesRequestList = new ArrayList<LiabilitiesDetailsRequest>();
		assetsRequestList = new ArrayList<AssetsDetailsRequest>();
		operatingStatementRequestList = new ArrayList<OperatingStatementDetailsRequest>();
	}
	
	public CMARequest(Long userId,Long applicationId) {
		this();
		this.userId = userId;
		this.applicationId = applicationId;
	}
	
	
	public List<LiabilitiesDetailsRequest> getLiabilitiesRequestList() {
		return liabilitiesRequestList;
	}
	public void setLiabilitiesRequestList(List<LiabilitiesDetailsRequest> liabilitiesRequestList) {
		this.liabilitiesRequestList = liabilitiesRequestList;
	}
	public List<AssetsDetailsRequest> getAssetsRequestList() {
		return assetsRequestList;
	}
	public void setAssetsRequestList(List<AssetsDetailsRequest> assetsRequestList) {
		this.assetsRequestList = assetsRequestList;
	}
	public List<OperatingStatementDetailsRequest> getOperatingStatementRequestList() {
		return operatingStatementRequestList;
	}
	public void setOperatingStatementRequestList(List<OperatingStatementDetailsRequest> operatingStatementRequestList) {
		this.operatingStatementRequestList = operatingStatementRequestList;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	
	
	
	
	
	
}
