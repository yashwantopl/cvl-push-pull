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
		this.setStatus(status);
		this.message = message;
	}
}
