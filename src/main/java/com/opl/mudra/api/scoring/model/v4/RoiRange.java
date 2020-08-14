package com.opl.mudra.api.scoring.model.v4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoiRange {

	private Long productId;
	private String productName;
	private Float minRoi;
	private Float maxRoi;
	
}
