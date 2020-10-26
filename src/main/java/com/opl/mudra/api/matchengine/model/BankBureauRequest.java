package com.opl.mudra.api.matchengine.model;

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
    
    private String mainDirScoreFlag;
    
    private String dpdFlag;
   
    private Double dpd;
    
    private String dpd3MonthFlag;
    
    private Double dpd3Month;
    
    private String cmr;
    
    private String cmrFlag;
    
    private String loansFlag;
    
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
    
    private Boolean dpdMatch6Month;
    private String dpd6MonthFlag;
    private Double dpd6Month;
    
    private String dpd12MonthFlagIndividual;
    private String dpd12MonthIndividual;
    private String dpd6MonthFlagIndividual;
    private String dpd6MonthIndividual;
    private String dpd3MonthFlagIndividual;
    private String dpd3MonthIndividual;
    
    private Boolean dpdMatch12MonthIndividual;
    private Boolean dpdMatch6MonthIndividual;
    private Boolean dpdMatch3MonthIndividual;
    
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

	public String getMainDirScoreFlag() {
		return mainDirScoreFlag;
	}

	public void setMainDirScoreFlag(String mainDirScoreFlag) {
		this.mainDirScoreFlag = mainDirScoreFlag;
	}

	public String getDpdFlag() {
		return dpdFlag;
	}

	public void setDpdFlag(String dpdFlag) {
		this.dpdFlag = dpdFlag;
	}

	public Double getDpd() {
		return dpd;
	}

	public void setDpd(Double dpd) {
		this.dpd = dpd;
	}

	public String getDpd3MonthFlag() {
		return dpd3MonthFlag;
	}

	public void setDpd3MonthFlag(String dpd3MonthFlag) {
		this.dpd3MonthFlag = dpd3MonthFlag;
	}

	public Double getDpd3Month() {
		return dpd3Month;
	}

	public void setDpd3Month(Double dpd3Month) {
		this.dpd3Month = dpd3Month;
	}

	public String getLoansFlag() {
		return loansFlag;
	}

	public void setLoansFlag(String loansFlag) {
		this.loansFlag = loansFlag;
	}

	public Boolean getDpdMatch6Month() {
		return dpdMatch6Month;
	}

	public void setDpdMatch6Month(Boolean dpdMatch6Month) {
		this.dpdMatch6Month = dpdMatch6Month;
	}

	public String getDpd12MonthFlagIndividual() {
		return dpd12MonthFlagIndividual;
	}

	public void setDpd12MonthFlagIndividual(String dpd12MonthFlagIndividual) {
		this.dpd12MonthFlagIndividual = dpd12MonthFlagIndividual;
	}

	public String getDpd12MonthIndividual() {
		return dpd12MonthIndividual;
	}

	public void setDpd12MonthIndividual(String dpd12MonthIndividual) {
		this.dpd12MonthIndividual = dpd12MonthIndividual;
	}

	public String getDpd6MonthFlagIndividual() {
		return dpd6MonthFlagIndividual;
	}

	public void setDpd6MonthFlagIndividual(String dpd6MonthFlagIndividual) {
		this.dpd6MonthFlagIndividual = dpd6MonthFlagIndividual;
	}

	public String getDpd6MonthIndividual() {
		return dpd6MonthIndividual;
	}

	public void setDpd6MonthIndividual(String dpd6MonthIndividual) {
		this.dpd6MonthIndividual = dpd6MonthIndividual;
	}

	public String getDpd3MonthFlagIndividual() {
		return dpd3MonthFlagIndividual;
	}

	public void setDpd3MonthFlagIndividual(String dpd3MonthFlagIndividual) {
		this.dpd3MonthFlagIndividual = dpd3MonthFlagIndividual;
	}

	public String getDpd3MonthIndividual() {
		return dpd3MonthIndividual;
	}

	public void setDpd3MonthIndividual(String dpd3MonthIndividual) {
		this.dpd3MonthIndividual = dpd3MonthIndividual;
	}

	public Boolean getDpdMatch12MonthIndividual() {
		return dpdMatch12MonthIndividual;
	}

	public void setDpdMatch12MonthIndividual(Boolean dpdMatch12MonthIndividual) {
		this.dpdMatch12MonthIndividual = dpdMatch12MonthIndividual;
	}

	public Boolean getDpdMatch6MonthIndividual() {
		return dpdMatch6MonthIndividual;
	}

	public void setDpdMatch6MonthIndividual(Boolean dpdMatch6MonthIndividual) {
		this.dpdMatch6MonthIndividual = dpdMatch6MonthIndividual;
	}

	public Boolean getDpdMatch3MonthIndividual() {
		return dpdMatch3MonthIndividual;
	}

	public void setDpdMatch3MonthIndividual(Boolean dpdMatch3MonthIndividual) {
		this.dpdMatch3MonthIndividual = dpdMatch3MonthIndividual;
	}

	public String getDpd6MonthFlag() {
		return dpd6MonthFlag;
	}

	public void setDpd6MonthFlag(String dpd6MonthFlag) {
		this.dpd6MonthFlag = dpd6MonthFlag;
	}

	public Double getDpd6Month() {
		return dpd6Month;
	}

	public void setDpd6Month(Double dpd6Month) {
		this.dpd6Month = dpd6Month;
	}
}
