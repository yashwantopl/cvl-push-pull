package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoringModelResponse extends TrackingManageFieldsProxy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4828821746448403764L;

	private String name;
	
	private String code;
	
	private String scoringType;
	
	private String requestType;
	
	private String checkerDecision;
	
	private String modifiedDateStr;
	
	private List<ItrTypeMsmeRequest> itrTypeMsmeRequestList;
	
	private List<ScoringVersionWithProduct> scoringVersionWithProducts;
	
	private Boolean isEditable;
	
	private String action;
	
	public ScoringModelResponse(ScoringModelRequest scoringModelRequest) {
		this.setId(scoringModelRequest.getId());
		this.name = scoringModelRequest.getName();
		this.code = scoringModelRequest.getCode();
		this.setVersion( scoringModelRequest.getVersion());
		this.setModifiedDate(scoringModelRequest.getModifiedDate());
		this.setJobId(scoringModelRequest.getJobId()) ;
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

	public String getModifiedDateStr() {
		return modifiedDateStr;
	}

	public void setModifiedDateStr(String modifiedDateStr) {
		this.modifiedDateStr = modifiedDateStr;
	}

	public List<ItrTypeMsmeRequest> getItrTypeMsmeRequestList() {
		return itrTypeMsmeRequestList;
	}

	public void setItrTypeMsmeRequestList(List<ItrTypeMsmeRequest> itrTypeMsmeRequestList) {
		this.itrTypeMsmeRequestList = itrTypeMsmeRequestList;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
		
}
