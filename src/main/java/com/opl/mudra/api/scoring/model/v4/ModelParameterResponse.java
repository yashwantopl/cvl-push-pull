package com.opl.mudra.api.scoring.model.v4;

import java.util.ArrayList;
import java.util.List;


public class ModelParameterResponse {

	private Long id;
	private Long fieldMasterId;
	private Long riskMakerId;
	private Double maxScore;
	private Integer yearId;
	private String name;
	private Boolean isYearDisplay;
	private String addivalues;
	private List<ModelParameterOptionRequest> modelParameterOptions = new ArrayList<ModelParameterOptionRequest>();
	private List<FieldMasterRequest> FieldMasterRequestList;

	public ModelParameterResponse(Long fieldMasterId, String name, Integer yearId, Double maxScore, String addivalues) {
		super();
		this.fieldMasterId = fieldMasterId;
		this.name = name;
		this.yearId = yearId;
		this.maxScore = maxScore;
		this.addivalues = addivalues;
	}

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

	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsYearDisplay() {
		return isYearDisplay;
	}

	public void setIsYearDisplay(Boolean isYearDisplay) {
		this.isYearDisplay = isYearDisplay;
	}

	public String getAddivalues() {
		return addivalues;
	}

	public void setAddivalues(String addivalues) {
		this.addivalues = addivalues;
	}

	public List<ModelParameterOptionRequest> getModelParameterOptions() {
		return modelParameterOptions;
	}

	public void setModelParameterOptions(List<ModelParameterOptionRequest> modelParameterOptions) {
		this.modelParameterOptions = modelParameterOptions;
	}

	public List<FieldMasterRequest> getFieldMasterRequestList() {
		return FieldMasterRequestList;
	}

	public void setFieldMasterRequestList(List<FieldMasterRequest> fieldMasterRequestList) {
		FieldMasterRequestList = fieldMasterRequestList;
	}
}
