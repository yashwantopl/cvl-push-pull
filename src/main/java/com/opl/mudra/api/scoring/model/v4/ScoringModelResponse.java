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
		
}
