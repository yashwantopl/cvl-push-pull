package com.opl.mudra.api.scoring.model.v4;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelParameterRequest {

	private Long id;

	private FieldMasterRequest fieldMasterRequest;

	private RiskMsmeRequest riskMsmeRequest;

	private List<ModelParameterOptionRequest> modelParameterOptions;

	private Double maxScore;

}
