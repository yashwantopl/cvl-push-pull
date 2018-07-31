package com.capitaworld.service.loans.domain.fundprovider;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="fp_ntb_tl_details")
@PrimaryKeyJoinColumn(name="fp_product_id",referencedColumnName="fp_product_id")
public class NTBParameter extends ProductMaster implements Serializable {
    private static final long serialVersionUID = 1L;


    @OneToOne
    @JoinColumn(name="fp_product_id")
    private ProductMaster fpProductId;

    private Integer currency;

    private Integer denomination;

    @Column(name="min_tenure")
    private BigDecimal minTenure;

    @Column(name="max_tenure")
    private BigDecimal maxTenure;

    @Column(name="is_tenure_display")
    private Boolean isTenureDisplay=false;

    @Column(name="is_tenure_mandatory")
    private Boolean isTenureMandatory=false;

    @Column(name="is_geographical_display")
    private Boolean isGeographicalDisplay=false;

    @Column(name="is_geographical_mandatory")
    private Boolean isGeographicalMandatory=false;

    @Column(name="is_industry_sector_display")
    private Boolean isIndustrySectorDisplay=false;

    @Column(name="is_industry_sector_mandatory")
    private Boolean isIndustrySectorMandatory=false;

    @Column(name="min_avrg_age")
    private BigDecimal minAvrgAge;

    @Column(name="max_avrg_age")
    private BigDecimal maxAvrgAge;

    @Column(name="is_avrg_age_display")
    private Boolean isAvrgAgeDisplay=false;

    @Column(name="is_avrg_age_mandatory")
    private Boolean isAvrgAgeMandatory=false;

    @Column(name="min_invest_size")
    private BigDecimal minInvestSize;

    @Column(name="max_invest_size")
    private BigDecimal maxInvestSize;

    @Column(name="is_investment_size_display")
    private Boolean isInvestmentSizeDisplay=false;

    @Column(name="is_investment_size_mandatory")
    private Boolean isInvestmentSizeMandatory=false;


    @Column(name="min_avrg_yearly_income")
    private BigDecimal minAvrgYearlyIncome;

    @Column(name="max_avrg_yearly_income")
    private BigDecimal maxAvrgYearlyIncome;

    @Column(name="is_avrg_yearly_income_display")
    private Boolean isAvrgYearlyIncomeDisplay=false;

    @Column(name="is_avrg_yearly_income_mandatory")
    private Boolean isAvrgYearlyIncomeMandatory=false;


    @Column(name="min_loan_asset_value")
    private BigDecimal minLoanAssetValue;

    @Column(name="max_loan_asset_value")
    private BigDecimal maxLoanAssetValue;

    @Column(name="is_loan_to_asset_val_display")
    private Boolean isLoanToAssetValDisplay=false;

    @Column(name="is_loan_to_asset_val_mandatory")
    private Boolean isLoanToAsset_val_mandatory=false;


    @Column(name="avrg_cibil_score")
    private Integer avrgCibilScore;

    @Column(name="is_avrg_cibil_score_display")
    private Boolean isAvrgCibilScoreDisplay=false;

    @Column(name="is_avrg_cibil_score_mandatory")
    private Boolean isAvrgCibilScoreMandatory=false;


    @Column(name="min_current_foir")
    private BigDecimal minCurrentFoir;

    @Column(name="max_current_foir")
    private BigDecimal maxCurrentFoir;

    @Column(name="is_current_foir_display")
    private Boolean isCurrentFoirDisplay=false;

    @Column(name="is_current_foir_mandatory")
    private Boolean isCurrentFoirMandatory=false;


    @Column(name="min_risk_score_model")
    private Integer minRiskScoreModel;

    @Column(name="max_risk_score_model")
    private Integer maxRiskScoreModel;

    @Column(name="is_risk_score_model_display")
    private Boolean isRiskScoreModelDisplay=false;

    @Column(name="is_risk_score_model_mandatory")
    private Boolean isRiskScoreModelMandatory=false;


    @Column(name="min_avrg_work_exp")
    private BigDecimal minAvrgWorkExp;

    @Column(name="max_avrg_work_exp")
    private BigDecimal maxAvrgWorkExp;

    @Column(name="is_avrg_work_exp_display")
    private Boolean isAvrgWorkExpDisplay=false;

    @Column(name="is_avrg_work_exp_mandatory")
    private Boolean isAvrgWorkExpMandatory=false;


    @Column(name="created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    
    @Column(name ="job_id")
	private Long jobId;


	public ProductMaster getFpProductId() {
		return fpProductId;
	}


	public void setFpProductId(ProductMaster fpProductId) {
		this.fpProductId = fpProductId;
	}


	public Integer getCurrency() {
		return currency;
	}


	public void setCurrency(Integer currency) {
		this.currency = currency;
	}


	public Integer getDenomination() {
		return denomination;
	}


	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}


	public BigDecimal getMinTenure() {
		return minTenure;
	}


	public void setMinTenure(BigDecimal minTenure) {
		this.minTenure = minTenure;
	}


	public BigDecimal getMaxTenure() {
		return maxTenure;
	}


	public void setMaxTenure(BigDecimal maxTenure) {
		this.maxTenure = maxTenure;
	}


	public Boolean getIsTenureDisplay() {
		return isTenureDisplay;
	}


	public void setIsTenureDisplay(Boolean isTenureDisplay) {
		this.isTenureDisplay = isTenureDisplay;
	}


	public Boolean getIsTenureMandatory() {
		return isTenureMandatory;
	}


	public void setIsTenureMandatory(Boolean isTenureMandatory) {
		this.isTenureMandatory = isTenureMandatory;
	}


	public Boolean getIsGeographicalDisplay() {
		return isGeographicalDisplay;
	}


	public void setIsGeographicalDisplay(Boolean isGeographicalDisplay) {
		this.isGeographicalDisplay = isGeographicalDisplay;
	}


	public Boolean getIsGeographicalMandatory() {
		return isGeographicalMandatory;
	}


	public void setIsGeographicalMandatory(Boolean isGeographicalMandatory) {
		this.isGeographicalMandatory = isGeographicalMandatory;
	}


	public Boolean getIsIndustrySectorDisplay() {
		return isIndustrySectorDisplay;
	}


	public void setIsIndustrySectorDisplay(Boolean isIndustrySectorDisplay) {
		this.isIndustrySectorDisplay = isIndustrySectorDisplay;
	}


	public Boolean getIsIndustrySectorMandatory() {
		return isIndustrySectorMandatory;
	}


	public void setIsIndustrySectorMandatory(Boolean isIndustrySectorMandatory) {
		this.isIndustrySectorMandatory = isIndustrySectorMandatory;
	}


	public BigDecimal getMinAvrgAge() {
		return minAvrgAge;
	}


	public void setMinAvrgAge(BigDecimal minAvrgAge) {
		this.minAvrgAge = minAvrgAge;
	}


	public BigDecimal getMaxAvrgAge() {
		return maxAvrgAge;
	}


	public void setMaxAvrgAge(BigDecimal maxAvrgAge) {
		this.maxAvrgAge = maxAvrgAge;
	}


	public Boolean getIsAvrgAgeDisplay() {
		return isAvrgAgeDisplay;
	}


	public void setIsAvrgAgeDisplay(Boolean isAvrgAgeDisplay) {
		this.isAvrgAgeDisplay = isAvrgAgeDisplay;
	}


	public Boolean getIsAvrgAgeMandatory() {
		return isAvrgAgeMandatory;
	}


	public void setIsAvrgAgeMandatory(Boolean isAvrgAgeMandatory) {
		this.isAvrgAgeMandatory = isAvrgAgeMandatory;
	}


	public BigDecimal getMinInvestSize() {
		return minInvestSize;
	}


	public void setMinInvestSize(BigDecimal minInvestSize) {
		this.minInvestSize = minInvestSize;
	}


	public BigDecimal getMaxInvestSize() {
		return maxInvestSize;
	}


	public void setMaxInvestSize(BigDecimal maxInvestSize) {
		this.maxInvestSize = maxInvestSize;
	}


	public Boolean getIsInvestmentSizeDisplay() {
		return isInvestmentSizeDisplay;
	}


	public void setIsInvestmentSizeDisplay(Boolean isInvestmentSizeDisplay) {
		this.isInvestmentSizeDisplay = isInvestmentSizeDisplay;
	}


	public Boolean getIsInvestmentSizeMandatory() {
		return isInvestmentSizeMandatory;
	}


	public void setIsInvestmentSizeMandatory(Boolean isInvestmentSizeMandatory) {
		this.isInvestmentSizeMandatory = isInvestmentSizeMandatory;
	}


	public BigDecimal getMinAvrgYearlyIncome() {
		return minAvrgYearlyIncome;
	}


	public void setMinAvrgYearlyIncome(BigDecimal minAvrgYearlyIncome) {
		this.minAvrgYearlyIncome = minAvrgYearlyIncome;
	}


	public BigDecimal getMaxAvrgYearlyIncome() {
		return maxAvrgYearlyIncome;
	}


	public void setMaxAvrgYearlyIncome(BigDecimal maxAvrgYearlyIncome) {
		this.maxAvrgYearlyIncome = maxAvrgYearlyIncome;
	}


	public Boolean getIsAvrgYearlyIncomeDisplay() {
		return isAvrgYearlyIncomeDisplay;
	}


	public void setIsAvrgYearlyIncomeDisplay(Boolean isAvrgYearlyIncomeDisplay) {
		this.isAvrgYearlyIncomeDisplay = isAvrgYearlyIncomeDisplay;
	}


	public Boolean getIsAvrgYearlyIncomeMandatory() {
		return isAvrgYearlyIncomeMandatory;
	}


	public void setIsAvrgYearlyIncomeMandatory(Boolean isAvrgYearlyIncomeMandatory) {
		this.isAvrgYearlyIncomeMandatory = isAvrgYearlyIncomeMandatory;
	}


	public BigDecimal getMinLoanAssetValue() {
		return minLoanAssetValue;
	}


	public void setMinLoanAssetValue(BigDecimal minLoanAssetValue) {
		this.minLoanAssetValue = minLoanAssetValue;
	}


	public BigDecimal getMaxLoanAssetValue() {
		return maxLoanAssetValue;
	}


	public void setMaxLoanAssetValue(BigDecimal maxLoanAssetValue) {
		this.maxLoanAssetValue = maxLoanAssetValue;
	}


	public Boolean getIsLoanToAssetValDisplay() {
		return isLoanToAssetValDisplay;
	}


	public void setIsLoanToAssetValDisplay(Boolean isLoanToAssetValDisplay) {
		this.isLoanToAssetValDisplay = isLoanToAssetValDisplay;
	}


	public Boolean getIsLoanToAsset_val_mandatory() {
		return isLoanToAsset_val_mandatory;
	}


	public void setIsLoanToAsset_val_mandatory(Boolean isLoanToAsset_val_mandatory) {
		this.isLoanToAsset_val_mandatory = isLoanToAsset_val_mandatory;
	}


	public Integer getAvrgCibilScore() {
		return avrgCibilScore;
	}


	public void setAvrgCibilScore(Integer avrgCibilScore) {
		this.avrgCibilScore = avrgCibilScore;
	}


	public Boolean getIsAvrgCibilScoreDisplay() {
		return isAvrgCibilScoreDisplay;
	}


	public void setIsAvrgCibilScoreDisplay(Boolean isAvrgCibilScoreDisplay) {
		this.isAvrgCibilScoreDisplay = isAvrgCibilScoreDisplay;
	}


	public Boolean getIsAvrgCibilScoreMandatory() {
		return isAvrgCibilScoreMandatory;
	}


	public void setIsAvrgCibilScoreMandatory(Boolean isAvrgCibilScoreMandatory) {
		this.isAvrgCibilScoreMandatory = isAvrgCibilScoreMandatory;
	}


	public BigDecimal getMinCurrentFoir() {
		return minCurrentFoir;
	}


	public void setMinCurrentFoir(BigDecimal minCurrentFoir) {
		this.minCurrentFoir = minCurrentFoir;
	}


	public BigDecimal getMaxCurrentFoir() {
		return maxCurrentFoir;
	}


	public void setMaxCurrentFoir(BigDecimal maxCurrentFoir) {
		this.maxCurrentFoir = maxCurrentFoir;
	}


	public Boolean getIsCurrentFoirDisplay() {
		return isCurrentFoirDisplay;
	}


	public void setIsCurrentFoirDisplay(Boolean isCurrentFoirDisplay) {
		this.isCurrentFoirDisplay = isCurrentFoirDisplay;
	}


	public Boolean getIsCurrentFoirMandatory() {
		return isCurrentFoirMandatory;
	}


	public void setIsCurrentFoirMandatory(Boolean isCurrentFoirMandatory) {
		this.isCurrentFoirMandatory = isCurrentFoirMandatory;
	}


	public Integer getMinRiskScoreModel() {
		return minRiskScoreModel;
	}


	public void setMinRiskScoreModel(Integer minRiskScoreModel) {
		this.minRiskScoreModel = minRiskScoreModel;
	}


	public Integer getMaxRiskScoreModel() {
		return maxRiskScoreModel;
	}


	public void setMaxRiskScoreModel(Integer maxRiskScoreModel) {
		this.maxRiskScoreModel = maxRiskScoreModel;
	}


	public Boolean getIsRiskScoreModelDisplay() {
		return isRiskScoreModelDisplay;
	}


	public void setIsRiskScoreModelDisplay(Boolean isRiskScoreModelDisplay) {
		this.isRiskScoreModelDisplay = isRiskScoreModelDisplay;
	}


	public Boolean getIsRiskScoreModelMandatory() {
		return isRiskScoreModelMandatory;
	}


	public void setIsRiskScoreModelMandatory(Boolean isRiskScoreModelMandatory) {
		this.isRiskScoreModelMandatory = isRiskScoreModelMandatory;
	}


	public BigDecimal getMinAvrgWorkExp() {
		return minAvrgWorkExp;
	}


	public void setMinAvrgWorkExp(BigDecimal minAvrgWorkExp) {
		this.minAvrgWorkExp = minAvrgWorkExp;
	}


	public BigDecimal getMaxAvrgWorkExp() {
		return maxAvrgWorkExp;
	}


	public void setMaxAvrgWorkExp(BigDecimal maxAvrgWorkExp) {
		this.maxAvrgWorkExp = maxAvrgWorkExp;
	}


	public Boolean getIsAvrgWorkExpDisplay() {
		return isAvrgWorkExpDisplay;
	}


	public void setIsAvrgWorkExpDisplay(Boolean isAvrgWorkExpDisplay) {
		this.isAvrgWorkExpDisplay = isAvrgWorkExpDisplay;
	}


	public Boolean getIsAvrgWorkExpMandatory() {
		return isAvrgWorkExpMandatory;
	}


	public void setIsAvrgWorkExpMandatory(Boolean isAvrgWorkExpMandatory) {
		this.isAvrgWorkExpMandatory = isAvrgWorkExpMandatory;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public Long getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public Long getJobId() {
		return jobId;
	}


	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
    
    

}
