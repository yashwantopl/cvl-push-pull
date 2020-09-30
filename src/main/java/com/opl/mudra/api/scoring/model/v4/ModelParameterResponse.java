package com.opl.mudra.api.scoring.model.v4;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
