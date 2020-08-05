package com.opl.mudra.api.scoring.model;

public class ModelParameterResponse {

	private Long fieldMasterId;
	private String name;
	private Boolean isConsiderCoApplicant;
	private Integer yearId;
	private Double maxScore;
	private String addivalues;
	private Integer employmentTypeId;
	
	
	
	public ModelParameterResponse() {
		
	}
	
	public ModelParameterResponse(Long fieldMasterId, String name, Boolean isConsiderCoApplicant, Integer yearId,
			Double maxScore, String addivalues, Integer employmentTypeId) {
		super();
		this.fieldMasterId = fieldMasterId;
		this.name = name;
		this.isConsiderCoApplicant = isConsiderCoApplicant;
		this.yearId = yearId;
		this.maxScore = maxScore;
		this.addivalues = addivalues;
		this.employmentTypeId = employmentTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFieldMasterId() {
		return fieldMasterId;
	}
	public void setFieldMasterId(Long fieldMasterId) {
		this.fieldMasterId = fieldMasterId;
	}
	public Boolean getIsConsiderCoApplicant() {
		return isConsiderCoApplicant;
	}
	public void setIsConsiderCoApplicant(Boolean isConsiderCoApplicant) {
		this.isConsiderCoApplicant = isConsiderCoApplicant;
	}
	public Double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}
	public String getAddivalues() {
		return addivalues;
	}
	public void setAddivalues(String addivalues) {
		this.addivalues = addivalues;
	}
	public Integer getEmploymentTypeId() {
		return employmentTypeId;
	}
	public void setEmploymentTypeId(Integer employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}

	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}
}
