package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoringModelCount implements Serializable {

	private static final long serialVersionUID = -7362953837063029056L;
	private Integer savedModelCount;
	private Integer sentToCheckerModelCount;
	private Integer sendBackByCheckerModelCount;
	private Integer receivedFromMakerModelCount;
	private Integer sentBackToMakerModelCount;
	private Integer activeModelCount;
	private Integer inactiveModelCount;
}
