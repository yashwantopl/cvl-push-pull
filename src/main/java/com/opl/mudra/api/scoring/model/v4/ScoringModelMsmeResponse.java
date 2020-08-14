package com.opl.mudra.api.scoring.model.v4;

import java.util.Date;
import java.util.List;

public class ScoringModelMsmeResponse {

	private Long id;
	
	private String name;
	
	private String code;
	
	private String scoringType;
	
	private String requestType;
	
	private String checkerDecision;
	
	private Float version;
	
	private Date modifiedDate;
	
	private String modifiedDateStr;
	
	private List<ItrTypeMsmeRequest> itrTypeMsmeRequestList;
	
	private List<ScoringVersionWithProduct> scoringVersionWithProducts;
	
	private Boolean isEditable;
	
	private Object workFlowData;
	
	private Long jobId;
	
	private String reason;
	
	private String action;
	
	
	public ScoringModelMsmeResponse(ScoringModelRequest scoringModelRequest) {
		this.id = scoringModelRequest.getId();
		this.name = scoringModelRequest.getName();
		this.code = scoringModelRequest.getCode();
		this.version = scoringModelRequest.getVersion();
		this.modifiedDate = scoringModelRequest.getModifiedDate();
		this.jobId = scoringModelRequest.getJobId();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getScoringType() {
		return scoringType;
	}

	public void setScoringType(String scoringType) {
		this.scoringType = scoringType;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getCheckerDecision() {
		return checkerDecision;
	}

	public void setCheckerDecision(String checkerDecision) {
		this.checkerDecision = checkerDecision;
	}

	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public List<ItrTypeMsmeRequest> getItrTypeMsmeRequestList() {
		return itrTypeMsmeRequestList;
	}


	public void setItrTypeMsmeRequestList(List<ItrTypeMsmeRequest> itrTypeMsmeRequestList) {
		this.itrTypeMsmeRequestList = itrTypeMsmeRequestList;
	}


	public String getModifiedDateStr() {
		return modifiedDateStr;
	}


	public void setModifiedDateStr(String modifiedDateStr) {
		this.modifiedDateStr = modifiedDateStr;
	}


	public List<ScoringVersionWithProduct> getScoringVersionWithProducts() {
		return scoringVersionWithProducts;
	}


	public void setScoringVersionWithProducts(List<ScoringVersionWithProduct> scoringVersionWithProducts) {
		this.scoringVersionWithProducts = scoringVersionWithProducts;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Object getWorkFlowData() {
		return workFlowData;
	}

	public void setWorkFlowData(Object workFlowData) {
		this.workFlowData = workFlowData;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	
}
