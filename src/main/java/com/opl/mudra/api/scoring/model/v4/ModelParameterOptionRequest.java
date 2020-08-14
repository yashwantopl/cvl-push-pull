package com.opl.mudra.api.scoring.model.v4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelParameterOptionRequest {

	private Long id;
	
	private Double minScore;
	
	private Double maxScore;
	
	private Long value;
	
	private String code;
	
	private Double score;
	
	private String description;
	
    private Boolean isActive;
    
    private Integer type;

}
