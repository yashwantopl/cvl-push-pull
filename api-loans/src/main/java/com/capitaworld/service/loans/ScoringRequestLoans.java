package com.capitaworld.service.loans;

public class ScoringRequestLoans {

    Long applicationId;

    Long scoringModelId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getScoringModelId() {
        return scoringModelId;
    }

    public void setScoringModelId(Long scoringModelId) {
        this.scoringModelId = scoringModelId;
    }
}
