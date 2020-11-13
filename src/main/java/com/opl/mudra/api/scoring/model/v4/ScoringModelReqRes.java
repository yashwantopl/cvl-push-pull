package com.opl.mudra.api.scoring.model.v4;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false) 
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoringModelReqRes extends TrackingManageFieldsProxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3721143918755983770L;

	private Long scoringModelTempId;

	private String message;

	private Long fpProductId;

	private Long applicationId;

	private Integer scoringModelBasedOn;

	private Long scoringModelMasterId;

	private Long scoringModelId;

	private Long copyScoringModelId;

	private Long copyEmploymentTypeId;

	private String scoringModelName;

	private Double totalScore;

	private List<RiskMsmeRequest> RiskMsmeRequestList;
	
	private Integer itrTypeId;

	private ScoringResponse scoringModelResponse;

	private List<ScoringResponse> scoringModelResponseList;

	private Integer requestType;
	private Double minMargin;
	private Double maxMargin;

	private List<Long> scoringModelIdList;
	private Boolean isWeightConsider;
	private Boolean isProportionateScoreConsider;
	private Double proportionateScore;
	private List<Long> deletedGroupIds;
	private Boolean isSaveSendApproved;

	public ScoringModelReqRes(String message, Integer status) {
		super();
//		this.setStatus(status);
		this.message = message;
	}

	public Long getScoringModelTempId() {
		return scoringModelTempId;
	}

	public void setScoringModelTempId(Long scoringModelTempId) {
		this.scoringModelTempId = scoringModelTempId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}

	public Long getScoringModelMasterId() {
		return scoringModelMasterId;
	}

	public void setScoringModelMasterId(Long scoringModelMasterId) {
		this.scoringModelMasterId = scoringModelMasterId;
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

	public Long getCopyEmploymentTypeId() {
		return copyEmploymentTypeId;
	}

	public void setCopyEmploymentTypeId(Long copyEmploymentTypeId) {
		this.copyEmploymentTypeId = copyEmploymentTypeId;
	}

	public String getScoringModelName() {
		return scoringModelName;
	}

	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public List<RiskMsmeRequest> getRiskMsmeRequestList() {
		return RiskMsmeRequestList;
	}

	public void setRiskMsmeRequestList(List<RiskMsmeRequest> riskMsmeRequestList) {
		RiskMsmeRequestList = riskMsmeRequestList;
	}

	public Integer getItrTypeId() {
		return itrTypeId;
	}

	public void setItrTypeId(Integer itrTypeId) {
		this.itrTypeId = itrTypeId;
	}

	public ScoringResponse getScoringModelResponse() {
		return scoringModelResponse;
	}

	public void setScoringModelResponse(ScoringResponse scoringModelResponse) {
		this.scoringModelResponse = scoringModelResponse;
	}

	public List<ScoringResponse> getScoringModelResponseList() {
		return scoringModelResponseList;
	}

	public void setScoringModelResponseList(List<ScoringResponse> scoringModelResponseList) {
		this.scoringModelResponseList = scoringModelResponseList;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public Double getMinMargin() {
		return minMargin;
	}

	public void setMinMargin(Double minMargin) {
		this.minMargin = minMargin;
	}

	public Double getMaxMargin() {
		return maxMargin;
	}

	public void setMaxMargin(Double maxMargin) {
		this.maxMargin = maxMargin;
	}

	public List<Long> getScoringModelIdList() {
		return scoringModelIdList;
	}

	public void setScoringModelIdList(List<Long> scoringModelIdList) {
		this.scoringModelIdList = scoringModelIdList;
	}

	public Boolean getIsWeightConsider() {
		return isWeightConsider;
	}

	public void setIsWeightConsider(Boolean isWeightConsider) {
		this.isWeightConsider = isWeightConsider;
	}

	public Boolean getIsProportionateScoreConsider() {
		return isProportionateScoreConsider;
	}

	public void setIsProportionateScoreConsider(Boolean isProportionateScoreConsider) {
		this.isProportionateScoreConsider = isProportionateScoreConsider;
	}

	public Double getProportionateScore() {
		return proportionateScore;
	}

	public void setProportionateScore(Double proportionateScore) {
		this.proportionateScore = proportionateScore;
	}

	public List<Long> getDeletedGroupIds() {
		return deletedGroupIds;
	}

	public void setDeletedGroupIds(List<Long> deletedGroupIds) {
		this.deletedGroupIds = deletedGroupIds;
	}

	public Boolean getIsSaveSendApproved() {
		return isSaveSendApproved;
	}

	public void setIsSaveSendApproved(Boolean isSaveSendApproved) {
		this.isSaveSendApproved = isSaveSendApproved;
	}
	
	
}
