package com.capitaworld.service.loans.model.score;

public class ScoringRequestLoans {

    private Long scoringModelId;

    private Long fsDigit;

    private Long fpProductId;

    private Long applicationId;

    public Long getScoringModelId() {
        return scoringModelId;
    }

    public void setScoringModelId(Long scoringModelId) {
        this.scoringModelId = scoringModelId;
    }

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

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
