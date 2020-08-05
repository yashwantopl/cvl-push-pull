package com.opl.mudra.api.loans.model.common;

import java.io.Serializable;
import java.util.Date;

public class BankBureauRequest implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private Long fpProductId;
    
    private Long scoringModelId;
    
    private Long fieldMasterId;
    
    private String minScore;
    
    private String mainDirScore;
    
    private String scoreFlag;
    
    private String cmr;
    
    private String cmrFlag;
    
    private Double totalComEmi;
    
    private Double totalDirEmi;
    
    private Double existingLoanAmount;
    
    private Double existingLoanAmountByLoanType;
    
    private Double existingLoanCollateralAmount;

    private Integer type;

    private Long createdBy;

    private Long modifiedBy;

    private Date createdDate;

    private Date modifiedDate;

    private Boolean isActive;
    
    private Boolean scoreMatch;
    
    private Boolean mainDirScoreMatch;
    
    private Boolean dpdMatch12Month;
    
    private Boolean dpdMatch3Month;
    
    private String loanType;
    
    private Date loanDate;
    
    private Double emi;
    
    private Double outstanding;
    
    private Double sanction;
    
    private Double collateral;
    
    private Double score;
    
    private String description;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getScoringModelId() {
		return scoringModelId;
	}

	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}

	public Long getFieldMasterId() {
		return fieldMasterId;
	}

	public void setFieldMasterId(Long fieldMasterId) {
		this.fieldMasterId = fieldMasterId;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getScoreFlag() {
		return scoreFlag;
	}

	public void setScoreFlag(String scoreFlag) {
		this.scoreFlag = scoreFlag;
	}

	public String getCmr() {
		return cmr;
	}

	public void setCmr(String cmr) {
		this.cmr = cmr;
	}

	public String getCmrFlag() {
		return cmrFlag;
	}

	public void setCmrFlag(String cmrFlag) {
		this.cmrFlag = cmrFlag;
	}

	public Double getTotalComEmi() {
		return totalComEmi;
	}

	public void setTotalComEmi(Double totalComEmi) {
		this.totalComEmi = totalComEmi;
	}

	public Double getTotalDirEmi() {
		return totalDirEmi;
	}

	public void setTotalDirEmi(Double totalDirEmi) {
		this.totalDirEmi = totalDirEmi;
	}

	public Double getExistingLoanAmount() {
		return existingLoanAmount;
	}

	public void setExistingLoanAmount(Double existingLoanAmount) {
		this.existingLoanAmount = existingLoanAmount;
	}

	public Double getExistingLoanAmountByLoanType() {
		return existingLoanAmountByLoanType;
	}

	public void setExistingLoanAmountByLoanType(Double existingLoanAmountByLoanType) {
		this.existingLoanAmountByLoanType = existingLoanAmountByLoanType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getScoreMatch() {
		return scoreMatch;
	}

	public void setScoreMatch(Boolean scoreMatch) {
		this.scoreMatch = scoreMatch;
	}

	public Boolean getMainDirScoreMatch() {
		return mainDirScoreMatch;
	}

	public void setMainDirScoreMatch(Boolean mainDirScoreMatch) {
		this.mainDirScoreMatch = mainDirScoreMatch;
	}

	public Boolean getDpdMatch12Month() {
		return dpdMatch12Month;
	}

	public void setDpdMatch12Month(Boolean dpdMatch12Month) {
		this.dpdMatch12Month = dpdMatch12Month;
	}

	public Boolean getDpdMatch3Month() {
		return dpdMatch3Month;
	}

	public void setDpdMatch3Month(Boolean dpdMatch3Month) {
		this.dpdMatch3Month = dpdMatch3Month;
	}

	public Double getExistingLoanCollateralAmount() {
		return existingLoanCollateralAmount;
	}

	public void setExistingLoanCollateralAmount(Double existingLoanCollateralAmount) {
		this.existingLoanCollateralAmount = existingLoanCollateralAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public Double getSanction() {
		return sanction;
	}

	public void setSanction(Double sanction) {
		this.sanction = sanction;
	}

	public Double getCollateral() {
		return collateral;
	}

	public void setCollateral(Double collateral) {
		this.collateral = collateral;
	}

	public String getMainDirScore() {
		return mainDirScore;
	}

	public void setMainDirScore(String mainDirScore) {
		this.mainDirScore = mainDirScore;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
