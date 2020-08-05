package com.opl.mudra.api.scoring.model.scoringmodel;

import java.util.Collections;
import java.util.List;

public class FieldMappingReqRes {

    private Long modelParameterId;

    private Long fieldId;

    private String fieldName;

    private Integer type;

    private Double maxScore;

    private Boolean isActive;
    
    private Boolean isConsiderCoApplicant;
    
    private FieldMappingReqRes parentField;
    
    private String addiValues;
    
    private Integer employmentTypeId;

    private Integer yearId;

    private List<ModelParameterOptionReqRes> modelParameterOptionReqResList;


    public Long getModelParameterId() {
        return modelParameterId;
    }

    public void setModelParameterId(Long modelParameterId) {
        this.modelParameterId = modelParameterId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<ModelParameterOptionReqRes> getModelParameterOptionReqResList() {
    	if(modelParameterOptionReqResList == null) {
    		modelParameterOptionReqResList = Collections.emptyList();
    	}
        return modelParameterOptionReqResList;
    }

    public void setModelParameterOptionReqResList(List<ModelParameterOptionReqRes> modelParameterOptionReqResList) {
        this.modelParameterOptionReqResList = modelParameterOptionReqResList;
    }

	public Boolean getIsConsiderCoApplicant() {
		return isConsiderCoApplicant;
	}

	public void setIsConsiderCoApplicant(Boolean isConsiderCoApplicant) {
		this.isConsiderCoApplicant = isConsiderCoApplicant;
	}

	public FieldMappingReqRes getParentField() {
		return parentField;
	}

	public void setParentField(FieldMappingReqRes parentField) {
		this.parentField = parentField;
	}

	public String getAddiValues() {
		return addiValues;
	}

	public void setAddiValues(String addiValues) {
		this.addiValues = addiValues;
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
