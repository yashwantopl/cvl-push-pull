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
public class ScoringModelRequest extends TrackingManageFieldsProxy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8826685093925871121L;

	private String name;

	private String code;

	private Integer itrTypeId;
	
	private Boolean itrThreeYear;

	private Boolean itrLessThanThreeYear;

	private Boolean itrPresumptive;

	private Long removeJobId;

	private List<ItrTypeMsmeRequest> itrTypeMsmeRequestList;

	private Integer httpStatus;

	private String message;

	private Long scoringModelId;
	
	private Long copyScoringModelId;
	
	private Boolean isMaker;
	
	private String requestType;
	
	private String checkerDecision;
	
	private Integer copyFromItrId;
	
	public ScoringModelRequest(String message, Integer httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
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

	public Integer getItrTypeId() {
		return itrTypeId;
	}

	public void setItrTypeId(Integer itrTypeId) {
		this.itrTypeId = itrTypeId;
	}

	public Boolean getItrThreeYear() {
		return itrThreeYear;
	}

	public void setItrThreeYear(Boolean itrThreeYear) {
		this.itrThreeYear = itrThreeYear;
	}

	public Boolean getItrLessThanThreeYear() {
		return itrLessThanThreeYear;
	}

	public void setItrLessThanThreeYear(Boolean itrLessThanThreeYear) {
		this.itrLessThanThreeYear = itrLessThanThreeYear;
	}

	public Boolean getItrPresumptive() {
		return itrPresumptive;
	}

	public void setItrPresumptive(Boolean itrPresumptive) {
		this.itrPresumptive = itrPresumptive;
	}

	public Long getRemoveJobId() {
		return removeJobId;
	}

	public void setRemoveJobId(Long removeJobId) {
		this.removeJobId = removeJobId;
	}

	public List<ItrTypeMsmeRequest> getItrTypeMsmeRequestList() {
		return itrTypeMsmeRequestList;
	}

	public void setItrTypeMsmeRequestList(List<ItrTypeMsmeRequest> itrTypeMsmeRequestList) {
		this.itrTypeMsmeRequestList = itrTypeMsmeRequestList;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getScoringModelId() {
		return scoringModelId;
	}

	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}

	public Long getCopyScoringModelId() {
		return copyScoringModelId;
	}

	public void setCopyScoringModelId(Long copyScoringModelId) {
		this.copyScoringModelId = copyScoringModelId;
	}

	public Boolean getIsMaker() {
		return isMaker;
	}

	public void setIsMaker(Boolean isMaker) {
		this.isMaker = isMaker;
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

	public Integer getCopyFromItrId() {
		return copyFromItrId;
	}

	public void setCopyFromItrId(Integer copyFromItrId) {
		this.copyFromItrId = copyFromItrId;
	}
	
	
}
