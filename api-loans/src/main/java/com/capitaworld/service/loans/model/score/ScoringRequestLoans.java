package com.capitaworld.service.loans.model.score;

public class ScoringRequestLoans {

    /*private Long scoringModelId;*/

    private Long fsDigit;

    private Long fpProductId;

    /*private Long applicationId;*/

    private Long scoringModelId;
	private Long applicationId;

	private ScoreParameterRequestLoans scoreParameterRequestLoans;

	private ScoreParameterNTBRequest scoreParameterNTBRequest;


	public Long getFsDigit() {
		return fsDigit;
	}
	public void setFsDigit(Long fsDigit) {
		this.fsDigit = fsDigit;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public Long getScoringModelId() {
		return scoringModelId;
	}
	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public ScoreParameterRequestLoans getScoreParameterRequestLoans() {
		return scoreParameterRequestLoans;
	}

	public void setScoreParameterRequestLoans(ScoreParameterRequestLoans scoreParameterRequestLoans) {
		this.scoreParameterRequestLoans = scoreParameterRequestLoans;
	}


	public ScoreParameterNTBRequest getScoreParameterNTBRequest() {
		return scoreParameterNTBRequest;
	}
	public void setScoreParameterNTBRequest(ScoreParameterNTBRequest scoreParameterNTBRequest) {
		this.scoreParameterNTBRequest = scoreParameterNTBRequest;
	}
	@Override
	public String toString() {
		return "ScoringRequestLoans [fsDigit=" + fsDigit + ", fpProductId=" + fpProductId + ", scoringModelId="
				+ scoringModelId + ", applicationId=" + applicationId + ", scoreParameterRequestLoans="
				+ scoreParameterRequestLoans + ", scoreParameterNTBRequest=" + scoreParameterNTBRequest + "]";
	}

}
