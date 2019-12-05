package com.capitaworld.service.loans.model.score;

import java.io.Serializable;

public class ScoringCibilRequest  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long scoreModelId;
	private Double minRange;
	private Double maxRange;
	private Long score;
	private String description;
	private Long fieldMasterId;
	
	public ScoringCibilRequest() {
		super();
	}
	public ScoringCibilRequest(Long scoreModelId, Double minRange, Double maxRange, Long score, String description) {
		super();
		this.scoreModelId = scoreModelId;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.score = score;
		this.description = description;
	}
	
	public Long getScoreModelId() {
		return scoreModelId;
	}
	public void setScoreModelId(Long scoreModelId) {
		this.scoreModelId = scoreModelId;
	}
	public Double getMinRange() {
		return minRange;
	}
	public void setMinRange(Double minRange) {
		this.minRange = minRange;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(Double maxRange) {
		this.maxRange = maxRange;
	}
	public Long getFieldMasterId() {
		return fieldMasterId;
	}
	public void setFieldMasterId(Long fieldMasterId) {
		this.fieldMasterId = fieldMasterId;
	}
	
	

	
}
