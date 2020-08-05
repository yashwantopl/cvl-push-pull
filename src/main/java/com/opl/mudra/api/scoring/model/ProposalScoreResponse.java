package com.opl.mudra.api.scoring.model;

public class ProposalScoreResponse {

    private Long applicationId;

    private Long fpProductId;

    private Double totalScore;

    private Double scale;

    private String interpretation;

    private Double foir;

    private Double roi;

    private Double tenure;
    
    private Double spreadRoi;
    private Double mclrRoi;

    private Double pf;  // processing fess
    
    private Double minPf;  // min processing fess
    
    private Double maxPf;  // max processing fess
    
    private Double ltv;

    private Double managementRiskScore;

    private Double financialRiskScore;

    private Double businessRiskScore;

    private Double managementRiskWeight;

    private Double financialRiskWeight;

    private Double businessRiskWeight;

    private Long scoringModelId;

    private String scoringModelName;

    private Double scoringTotalScore;

    private Double managementRiskMaxTotalScore;

    private Double financialRiskMaxTotalScore;

    private Double businessRiskMaxTotalScore;

    private Double managementRiskScoreAvg;

    private Double financialRiskScoreAvg;

    private Double businessRiskScoreAvg;

    /////////

    private Double managementRiskWeightOfScoring;

    private Double financialRiskWeightOfScoring;

    private Double businessRiskWeightOfScoring;

    private Long directorId;

    private Boolean isWeightConsider;

    private Double managementScoreWithRiskWeight;

    private Double financialScoreWithRiskWeight;

    private Double businessScoreWithRiskWeight;


    private Double managementRiskMaxTotalWeight;

    private Double financialRiskMaxTotalWeight;

    private Double businessRiskMaxTotalWeight;


    private Boolean isProportionateScoreConsider;

    private Double proportionateScore;

    private Double proportionateScoreFS;

    private Long marginScaling;
    
    private Double finalMaxConcessionRateOfInterest;

    private Integer scoringModelBasedOn;
    
    private String concessionBasedOnType;
    
    
	public String getConcessionBasedOnType() {
		return concessionBasedOnType;
	}

	public void setConcessionBasedOnType(String concessionBasedOnType) {
		this.concessionBasedOnType = concessionBasedOnType;
	}

	public Double getSpreadRoi() {
		return spreadRoi;
	}

	public void setSpreadRoi(Double spreadRoi) {
		this.spreadRoi = spreadRoi;
	}

	public Double getMclrRoi() {
		return mclrRoi;
	}

	public void setMclrRoi(Double mclrRoi) {
		this.mclrRoi = mclrRoi;
	}

	public Double getMinPf() {
		return minPf;
	}

	public void setMinPf(Double minPf) {
		this.minPf = minPf;
	}

	public Double getMaxPf() {
		return maxPf;
	}

	public void setMaxPf(Double maxPf) {
		this.maxPf = maxPf;
	}

	public Double getFinalMaxConcessionRateOfInterest() {
		return finalMaxConcessionRateOfInterest;
	}

	public void setFinalMaxConcessionRateOfInterest(Double finalMaxConcessionRateOfInterest) {
		this.finalMaxConcessionRateOfInterest = finalMaxConcessionRateOfInterest;
	}

	public Long getMarginScaling() {
        return marginScaling;
    }

    public void setMarginScaling(Long marginScaling) {
        this.marginScaling = marginScaling;
    }

    public String getScoringModelName() {
        return scoringModelName;
    }

    public void setScoringModelName(String scoringModelName) {
        this.scoringModelName = scoringModelName;
    }

    public Boolean getIsProportionateScoreConsider() {
        return isProportionateScoreConsider;
    }

    public void setIsProportionateScoreConsider(Boolean proportionateScoreConsider) {
        isProportionateScoreConsider = proportionateScoreConsider;
    }

    public Double getProportionateScore() {
        return proportionateScore;
    }

    public void setProportionateScore(Double proportionateScore) {
        this.proportionateScore = proportionateScore;
    }

    public Double getProportionateScoreFS() {
        return proportionateScoreFS;
    }

    public void setProportionateScoreFS(Double proportionateScoreFS) {
        this.proportionateScoreFS = proportionateScoreFS;
    }

    public Double getManagementRiskMaxTotalWeight() {
        return managementRiskMaxTotalWeight;
    }

    public void setManagementRiskMaxTotalWeight(Double managementRiskMaxTotalWeight) {
        this.managementRiskMaxTotalWeight = managementRiskMaxTotalWeight;
    }

    public Double getFinancialRiskMaxTotalWeight() {
        return financialRiskMaxTotalWeight;
    }

    public void setFinancialRiskMaxTotalWeight(Double financialRiskMaxTotalWeight) {
        this.financialRiskMaxTotalWeight = financialRiskMaxTotalWeight;
    }

    public Double getBusinessRiskMaxTotalWeight() {
        return businessRiskMaxTotalWeight;
    }

    public void setBusinessRiskMaxTotalWeight(Double businessRiskMaxTotalWeight) {
        this.businessRiskMaxTotalWeight = businessRiskMaxTotalWeight;
    }

    public Double getManagementScoreWithRiskWeight() {
        return managementScoreWithRiskWeight;
    }

    public void setManagementScoreWithRiskWeight(Double managementScoreWithRiskWeight) {
        this.managementScoreWithRiskWeight = managementScoreWithRiskWeight;
    }

    public Double getFinancialScoreWithRiskWeight() {
        return financialScoreWithRiskWeight;
    }

    public void setFinancialScoreWithRiskWeight(Double financialScoreWithRiskWeight) {
        this.financialScoreWithRiskWeight = financialScoreWithRiskWeight;
    }

    public Double getBusinessScoreWithRiskWeight() {
        return businessScoreWithRiskWeight;
    }

    public void setBusinessScoreWithRiskWeight(Double businessScoreWithRiskWeight) {
        this.businessScoreWithRiskWeight = businessScoreWithRiskWeight;
    }

    public Boolean getWeightConsider() {
        return isWeightConsider;
    }

    public void setWeightConsider(Boolean weightConsider) {
        isWeightConsider = weightConsider;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public Double getManagementRiskWeightOfScoring() {
        return managementRiskWeightOfScoring;
    }

    public void setManagementRiskWeightOfScoring(Double managementRiskWeightOfScoring) {
        this.managementRiskWeightOfScoring = managementRiskWeightOfScoring;
    }

    public Double getFinancialRiskWeightOfScoring() {
        return financialRiskWeightOfScoring;
    }

    public void setFinancialRiskWeightOfScoring(Double financialRiskWeightOfScoring) {
        this.financialRiskWeightOfScoring = financialRiskWeightOfScoring;
    }

    public Double getBusinessRiskWeightOfScoring() {
        return businessRiskWeightOfScoring;
    }

    public void setBusinessRiskWeightOfScoring(Double businessRiskWeightOfScoring) {
        this.businessRiskWeightOfScoring = businessRiskWeightOfScoring;
    }

    public Double getManagementRiskScoreAvg() {
        return managementRiskScoreAvg;
    }

    public void setManagementRiskScoreAvg(Double managementRiskScoreAvg) {
        this.managementRiskScoreAvg = managementRiskScoreAvg;
    }

    public Double getFinancialRiskScoreAvg() {
        return financialRiskScoreAvg;
    }

    public void setFinancialRiskScoreAvg(Double financialRiskScoreAvg) {
        this.financialRiskScoreAvg = financialRiskScoreAvg;
    }

    public Double getBusinessRiskScoreAvg() {
        return businessRiskScoreAvg;
    }

    public void setBusinessRiskScoreAvg(Double businessRiskScoreAvg) {
        this.businessRiskScoreAvg = businessRiskScoreAvg;
    }

    public Double getManagementRiskMaxTotalScore() {
        return managementRiskMaxTotalScore;
    }

    public void setManagementRiskMaxTotalScore(Double managementRiskMaxTotalScore) {
        this.managementRiskMaxTotalScore = managementRiskMaxTotalScore;
    }

    public Double getFinancialRiskMaxTotalScore() {
        return financialRiskMaxTotalScore;
    }

    public void setFinancialRiskMaxTotalScore(Double financialRiskMaxTotalScore) {
        this.financialRiskMaxTotalScore = financialRiskMaxTotalScore;
    }

    public Double getBusinessRiskMaxTotalScore() {
        return businessRiskMaxTotalScore;
    }

    public void setBusinessRiskMaxTotalScore(Double businessRiskMaxTotalScore) {
        this.businessRiskMaxTotalScore = businessRiskMaxTotalScore;
    }

    public Double getScoringTotalScore() {
        return scoringTotalScore;
    }

    public void setScoringTotalScore(Double scoringTotalScore) {
        this.scoringTotalScore = scoringTotalScore;
    }

    public Long getScoringModelId() {
        return scoringModelId;
    }

    public void setScoringModelId(Long scoringModelId) {
        this.scoringModelId = scoringModelId;
    }

    public Double getManagementRiskScore() {
        return managementRiskScore;
    }

    public void setManagementRiskScore(Double managementRiskScore) {
        this.managementRiskScore = managementRiskScore;
    }

    public Double getFinancialRiskScore() {
        return financialRiskScore;
    }

    public void setFinancialRiskScore(Double financialRiskScore) {
        this.financialRiskScore = financialRiskScore;
    }

    public Double getBusinessRiskScore() {
        return businessRiskScore;
    }

    public void setBusinessRiskScore(Double businessRiskScore) {
        this.businessRiskScore = businessRiskScore;
    }

    public Double getManagementRiskWeight() {
        return managementRiskWeight;
    }

    public void setManagementRiskWeight(Double managementRiskWeight) {
        this.managementRiskWeight = managementRiskWeight;
    }

    public Double getFinancialRiskWeight() {
        return financialRiskWeight;
    }

    public void setFinancialRiskWeight(Double financialRiskWeight) {
        this.financialRiskWeight = financialRiskWeight;
    }

    public Double getBusinessRiskWeight() {
        return businessRiskWeight;
    }

    public void setBusinessRiskWeight(Double businessRiskWeight) {
        this.businessRiskWeight = businessRiskWeight;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public Double getFoir() {
        return foir;
    }

    public void setFoir(Double foir) {
        this.foir = foir;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public Double getTenure() {
        return tenure;
    }

    public void setTenure(Double tenure) {
        this.tenure = tenure;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

	public Double getLtv() {
		return ltv;
	}

	public void setLtv(Double ltv) {
		this.ltv = ltv;
	}

    public Integer getScoringModelBasedOn() {
        return scoringModelBasedOn;
    }

    public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
        this.scoringModelBasedOn = scoringModelBasedOn;
    }
}
