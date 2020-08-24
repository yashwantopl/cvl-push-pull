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
}
