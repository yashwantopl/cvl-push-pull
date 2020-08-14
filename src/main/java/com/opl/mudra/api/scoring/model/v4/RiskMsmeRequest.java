package com.opl.mudra.api.scoring.model.v4;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.scoring.model.ModelParameterResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskMsmeRequest {

	private Long id;
	private Integer itrTypeId;
	private String riskName;
	private Double riskWeight;
	private Double scoreWithRiskWeight;
	private Integer riskTypeId;
	private Integer numOfParameter;
	private Double totalMaxScore;
	private List<ModelParameterResponse> ModelParameterResponse;
	private List<FieldMasterRequest> FieldMasterRequestList;
}
