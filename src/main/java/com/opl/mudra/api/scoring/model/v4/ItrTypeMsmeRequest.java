package com.opl.mudra.api.scoring.model.v4;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown =  true)
public class ItrTypeMsmeRequest {
	private Long id;
	
	private Integer itrTypeId;
	
	private String itrType;
	
	private Boolean isWeightConsider;
	
	private Boolean isProportionateScoreConsider;
	
	private Double proportionateScore;
	
	private Double totalScore;
	
	private List<RiskMsmeRequest> riskMsmeRequestList;
	
}
