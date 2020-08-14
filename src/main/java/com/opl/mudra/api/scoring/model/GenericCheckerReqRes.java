package com.opl.mudra.api.scoring.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericCheckerReqRes implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -93321449016462267L;
	private Long id;
    private Long jobId;
    private Long currentStep;
    private Long toStep;
    private Long actionId;
    private Long userId;
    private String code;
    private String remark;
    private Boolean actionFlag;
    private Boolean isEdit;
}
