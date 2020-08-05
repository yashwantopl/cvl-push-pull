package com.opl.mudra.api.matchengine.model;


import java.io.Serializable;

public class ApplicationProductAuditRequest implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7871387702962931650L;

	private Long applicationId;

    private Long fpProductId;

    private Long stageId;

    private Long typeId;

    private Long statusId;

    private Double additionalLoanAmount;

    private Double existingLoanAmount;

    private Double maxLoanAmount;

    private Double maxTenure;

    private Double minTenure;

    private Double offeredRoi;

    private Double minRoi;

    private Double processingFee;

    private Double fpMinInvestment;

    private Double fpMaxInvestment;

    private Double fpMinTenure;

    private Double fpMaxTenure;

    private Double foir;

    private Double emi;

    private Long fpUserId;

    private Long npOrgId;

    private String name;

    private String imagePath;

    private String productName;

    private Boolean isExistingBank = false;
    
    private Double rank;

    private Boolean isSecondPriority;

    private Double minPf;

    private Double maxPf;

    private Double spreadRoi;

    private Double mclrRoi;

    private Double consessionRoi;
    
    private String concessionBasedOnType;

    private Integer scoringModelBasedOn;
    
    private String matchesTooltip;
    
    
    public String getConcessionBasedOnType() {
		return concessionBasedOnType;
	}

	public void setConcessionBasedOnType(String concessionBasedOnType) {
		this.concessionBasedOnType = concessionBasedOnType;
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

    public Boolean getIsSecondPriority() {
        return isSecondPriority;
    }

    public void setIsSecondPriority(Boolean isSecondPriority) {
        this.isSecondPriority = isSecondPriority;
    }

    public Boolean getIsExistingBank() {
        return isExistingBank;
    }

    public void setIsExistingBank(Boolean existingBank) {
        isExistingBank = existingBank;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNpOrgId() {
        return npOrgId;
    }

    public void setNpOrgId(Long npOrgId) {
        this.npOrgId = npOrgId;
    }

    public Long getFpUserId() {
        return fpUserId;
    }

    public void setFpUserId(Long fpUserId) {
        this.fpUserId = fpUserId;
    }

    public Double getEmi() {
        return emi;
    }

    public void setEmi(Double emi) {
        this.emi = emi;
    }

    public Double getFoir() {
        return foir;
    }

    public void setFoir(Double foir) {
        this.foir = foir;
    }

    public Double getFpMinInvestment() {
        return fpMinInvestment;
    }

    public void setFpMinInvestment(Double fpMinInvestment) {
        this.fpMinInvestment = fpMinInvestment;
    }

    public Double getFpMaxInvestment() {
        return fpMaxInvestment;
    }

    public void setFpMaxInvestment(Double fpMaxInvestment) {
        this.fpMaxInvestment = fpMaxInvestment;
    }

    public Double getFpMinTenure() {
        return fpMinTenure;
    }

    public void setFpMinTenure(Double fpMinTenure) {
        this.fpMinTenure = fpMinTenure;
    }

    public Double getFpMaxTenure() {
        return fpMaxTenure;
    }

    public void setFpMaxTenure(Double fpMaxTenure) {
        this.fpMaxTenure = fpMaxTenure;
    }

    public Double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(Double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public Double getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(Double maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Double getOfferedRoi() {
        return offeredRoi;
    }

    public void setOfferedRoi(Double offeredRoi) {
        this.offeredRoi = offeredRoi;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
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

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

    public Double getAdditionalLoanAmount() {
        return additionalLoanAmount;
    }

    public void setAdditionalLoanAmount(Double additionalLoanAmount) {
        this.additionalLoanAmount = additionalLoanAmount;
    }

    public Double getExistingLoanAmount() {
        return existingLoanAmount;
    }

    public void setExistingLoanAmount(Double existingLoanAmount) {
        this.existingLoanAmount = existingLoanAmount;
    }

    public Double getMinTenure() {
        return minTenure;
    }

    public void setMinTenure(Double minTenure) {
        this.minTenure = minTenure;
    }

    public Double getMinRoi() {
        return minRoi;
    }

    public void setMinRoi(Double minRoi) {
        this.minRoi = minRoi;
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

    public Double getConsessionRoi() {
        return consessionRoi;
    }

    public void setConsessionRoi(Double consessionRoi) {
        this.consessionRoi = consessionRoi;
    }

    public Integer getScoringModelBasedOn() {
        return scoringModelBasedOn;
    }

    public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
        this.scoringModelBasedOn = scoringModelBasedOn;
    }

	public String getMatchesTooltip() {
		return matchesTooltip;
	}

	public void setMatchesTooltip(String matchesTooltip) {
		this.matchesTooltip = matchesTooltip;
	}
    
    
}
