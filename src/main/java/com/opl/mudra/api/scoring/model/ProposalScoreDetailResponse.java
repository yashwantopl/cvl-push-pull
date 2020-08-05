package com.opl.mudra.api.scoring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalScoreDetailResponse {

    private String parameterName;

    private String parameterOption;

    private Double obtainedScore;

    private Double maxScore;

    private Double answer;
    
	private String addiValues;

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer(Double answer) {
        this.answer = answer;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterOption() {
        return parameterOption;
    }

    public void setParameterOption(String parameterOption) {
        this.parameterOption = parameterOption;
    }

    public Double getObtainedScore() {
        return obtainedScore;
    }

    public void setObtainedScore(Double obtainedScore) {
        this.obtainedScore = obtainedScore;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

	public String getAddiValues() {
		return addiValues;
	}

	public void setAddiValues(String addiValues) {
		this.addiValues = addiValues;
	}
}
