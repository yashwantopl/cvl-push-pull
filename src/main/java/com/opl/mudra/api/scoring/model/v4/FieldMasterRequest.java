package com.opl.mudra.api.scoring.model.v4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldMasterRequest {

	private Long id;

	private String fMstName;

	private String fMstDislplayName;

	private Double fMstMin;

	private Double fMstMax;

	private String fMstSymbol;

	private String fMstTooltip;

	private String fMstConfigurationJson;

	private Boolean isYearDisplay;
	private Integer fMstType;
	private Long sourceId;
}
