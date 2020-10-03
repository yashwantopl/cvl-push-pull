package com.opl.mudra.api.scoring.model.v4;

import java.util.ArrayList;
import java.util.List;

public class ModelParameterResponse {

	private Long id;
	private Long fieldMasterId;
	private Long riskMakerId;
	private Double maxScore;
	private Integer yearId;
	private Boolean isYearDisplay;
	private List<ModelParameterOptionRequest> modelParameterOptions=new ArrayList<ModelParameterOptionRequest>();
	private List<FieldMasterRequest> FieldMasterRequestList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFieldMasterId() {
		return fieldMasterId;
	}

	public void setFieldMasterId(Long fieldMasterId) {
		this.fieldMasterId = fieldMasterId;
	}

	public Long getRiskMakerId() {
		return riskMakerId;
	}

	public void setRiskMakerId(Long riskMakerId) {
		this.riskMakerId = riskMakerId;
	}

	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	public List<ModelParameterOptionRequest> getModelParameterOptions() {
		return modelParameterOptions;
	}

	public void setModelParameterOptions(List<ModelParameterOptionRequest> modelParameterOptions) {
		this.modelParameterOptions = modelParameterOptions;
	}

	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}

	public List<FieldMasterRequest> getFieldMasterRequestList() {
		return FieldMasterRequestList;
	}

	public void setFieldMasterRequestList(List<FieldMasterRequest> fieldMasterRequestList) {
		FieldMasterRequestList = fieldMasterRequestList;
	}

	public Boolean getYearDisplay() {
		return isYearDisplay;
	}

	public void setYearDisplay(Boolean isYearDisplay) {
		this.isYearDisplay = isYearDisplay;
	}

	@Override
	public String toString() {
		return "ModelParameterResponse [id=" + id + ", fieldMasterId=" + fieldMasterId + ", riskMakerId=" + riskMakerId
				+ ", maxScore=" + maxScore + ", yearId=" + yearId + ", isYearDisplay=" + isYearDisplay
				+ ", modelParameterOptions=" + modelParameterOptions + ", FieldMasterRequestList="
				+ FieldMasterRequestList + "]";
	}

}
