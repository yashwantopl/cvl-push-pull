package com.opl.mudra.api.scoring.model.scoringmodel;

public class ModelParameterOptionReqRes {


    private Long id;

	private Double minSubRange;
	
	private Double maxSubRange;
    
    private Double minRange;

    private Double maxRange;

    private Long value;

    private Long score;

    private String code;

    private String description;

    private Boolean isActive;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMinRange() {
        return minRange;
    }

    public void setMinRange(Double minRange) {
        this.minRange = minRange;
    }

    public Double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
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

	public Double getMinSubRange() {
		return minSubRange;
	}

	public void setMinSubRange(Double minSubRange) {
		this.minSubRange = minSubRange;
	}

	public Double getMaxSubRange() {
		return maxSubRange;
	}

	public void setMaxSubRange(Double maxSubRange) {
		this.maxSubRange = maxSubRange;
	}
}
