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
    private BigDecimal minTenureNtb;
    
    @Column(name="min_cgtmse")
	private BigDecimal mincgtmse;

	@Column(name="max_cgtmse")
	private BigDecimal maxcgtmse;

	@Column(name="is_cgtmse_display")
	private Boolean iscgtmseDisplay = false;

	@Column(name="is_cgtmse_mandatory")
	private Boolean iscgtmseMandatory = false;

    @Column(name="max_tenure")
    private BigDecimal maxTenureNtb;

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
    private BigDecimal minLoanToAsset;

    @Column(name="max_loan_asset_value")
    private BigDecimal maxLoanToAsset;

    @Column(name="is_loan_to_asset_val_display")
    private Boolean isLoanToAssetDisplay=false;

    @Column(name="is_loan_to_asset_val_mandatory")
    private Boolean isLoanToAssetMandatory=false;

    @Column(name="min_current_foir")
    private BigDecimal minCurrentFoir;

    @Column(name="max_current_foir")
    private BigDecimal maxCurrentFoir;

    @Column(name="is_current_foir_display")
    private Boolean isCurrentFoirDisplay=false;

    @Column(name="is_current_foir_mandatory")
    private Boolean isCurrentFoirMandatory=false;


    @Column(name="min_risk_score_model")
    private Integer minRiskModelScore;

    @Column(name="max_risk_score_model")
    private Integer maxRiskModelScore;

    @Column(name="is_risk_score_model_display")
    private Boolean isRiskModelScoreDisplay=false;

    @Column(name="is_risk_score_model_mandatory")
    private Boolean isRiskModelScoreMandatory=false;


    @Column(name="min_avrg_work_exp")
    private BigDecimal minAvgWorkExp;

    @Column(name="max_avrg_work_exp")
    private BigDecimal maxAvgWorkExp;

    @Column(name="is_avrg_work_exp_display")
    private Boolean isAvgWorkExpDisplay=false;

    @Column(name="is_avrg_work_exp_mandatory")
    private Boolean isAvgWorkExpMandatory=false;


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

    @Column(name="avrg_cibil_score")
	private Integer individualCibil;
    
    @Column(name="is_avrg_cibil_score_display")
	private Boolean isIndividualCibilDisplay = false;

	@Column(name="is_avrg_cibil_score_mandatory")
	private Boolean isIndividualCibilMandatory = false;

	@Column(name="assessment_method_id")
	private Integer assessmentMethodId;
	
	@Column(name="ddr_flow")
	private Integer ddrFlow;
	
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


	

	
	public BigDecimal getMinTenureNtb() {
		return minTenureNtb;
	}


	public void setMinTenureNtb(BigDecimal minTenureNtb) {
		this.minTenureNtb = minTenureNtb;
	}


	

	public BigDecimal getMincgtmse() {
		return mincgtmse;
	}


	public void setMincgtmse(BigDecimal mincgtmse) {
		this.mincgtmse = mincgtmse;
	}


	public BigDecimal getMaxcgtmse() {
		return maxcgtmse;
	}


	public void setMaxcgtmse(BigDecimal maxcgtmse) {
		this.maxcgtmse = maxcgtmse;
	}


	public Boolean getIscgtmseDisplay() {
		return iscgtmseDisplay;
	}


	public void setIscgtmseDisplay(Boolean iscgtmseDisplay) {
		this.iscgtmseDisplay = iscgtmseDisplay;
	}


	public Boolean getIscgtmseMandatory() {
		return iscgtmseMandatory;
	}


	public void setIscgtmseMandatory(Boolean iscgtmseMandatory) {
		this.iscgtmseMandatory = iscgtmseMandatory;
	}


	public BigDecimal getMaxTenureNtb() {
		return maxTenureNtb;
	}


	public void setMaxTenureNtb(BigDecimal maxTenureNtb) {
		this.maxTenureNtb = maxTenureNtb;
	}


	public BigDecimal getMinLoanToAsset() {
		return minLoanToAsset;
	}


	public void setMinLoanToAsset(BigDecimal minLoanToAsset) {
		this.minLoanToAsset = minLoanToAsset;
	}


	public BigDecimal getMaxLoanToAsset() {
		return maxLoanToAsset;
	}


	public void setMaxLoanToAsset(BigDecimal maxLoanToAsset) {
		this.maxLoanToAsset = maxLoanToAsset;
	}


	public Boolean getIsLoanToAssetDisplay() {
		return isLoanToAssetDisplay;
	}


	public void setIsLoanToAssetDisplay(Boolean isLoanToAssetDisplay) {
		this.isLoanToAssetDisplay = isLoanToAssetDisplay;
	}


	public Boolean getIsLoanToAssetMandatory() {
		return isLoanToAssetMandatory;
	}


	public void setIsLoanToAssetMandatory(Boolean isLoanToAssetMandatory) {
		this.isLoanToAssetMandatory = isLoanToAssetMandatory;
	}


	public BigDecimal getMinAvgWorkExp() {
		return minAvgWorkExp;
	}


	public void setMinAvgWorkExp(BigDecimal minAvgWorkExp) {
		this.minAvgWorkExp = minAvgWorkExp;
	}


	public BigDecimal getMaxAvgWorkExp() {
		return maxAvgWorkExp;
	}


	public void setMaxAvgWorkExp(BigDecimal maxAvgWorkExp) {
		this.maxAvgWorkExp = maxAvgWorkExp;
	}


	public Boolean getIsAvgWorkExpDisplay() {
		return isAvgWorkExpDisplay;
	}


	public void setIsAvgWorkExpDisplay(Boolean isAvgWorkExpDisplay) {
		this.isAvgWorkExpDisplay = isAvgWorkExpDisplay;
	}


	public Boolean getIsAvgWorkExpMandatory() {
		return isAvgWorkExpMandatory;
	}


	public void setIsAvgWorkExpMandatory(Boolean isAvgWorkExpMandatory) {
		this.isAvgWorkExpMandatory = isAvgWorkExpMandatory;
	}


	public Integer getIndividualCibil() {
		return individualCibil;
	}


	public void setIndividualCibil(Integer individualCibil) {
		this.individualCibil = individualCibil;
	}


	public Boolean getIsIndividualCibilDisplay() {
		return isIndividualCibilDisplay;
	}


	public void setIsIndividualCibilDisplay(Boolean isIndividualCibilDisplay) {
		this.isIndividualCibilDisplay = isIndividualCibilDisplay;
	}


	public Boolean getIsIndividualCibilMandatory() {
		return isIndividualCibilMandatory;
	}


	public void setIsIndividualCibilMandatory(Boolean isIndividualCibilMandatory) {
		this.isIndividualCibilMandatory = isIndividualCibilMandatory;
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




	


	public Integer getMinRiskModelScore() {
		return minRiskModelScore;
	}


	public void setMinRiskModelScore(Integer minRiskModelScore) {
		this.minRiskModelScore = minRiskModelScore;
	}


	public Integer getMaxRiskModelScore() {
		return maxRiskModelScore;
	}


	public void setMaxRiskModelScore(Integer maxRiskModelScore) {
		this.maxRiskModelScore = maxRiskModelScore;
	}


	public Boolean getIsRiskModelScoreDisplay() {
		return isRiskModelScoreDisplay;
	}


	public void setIsRiskModelScoreDisplay(Boolean isRiskModelScoreDisplay) {
		this.isRiskModelScoreDisplay = isRiskModelScoreDisplay;
	}


	public Boolean getIsRiskModelScoreMandatory() {
		return isRiskModelScoreMandatory;
	}


	public void setIsRiskModelScoreMandatory(Boolean isRiskModelScoreMandatory) {
		this.isRiskModelScoreMandatory = isRiskModelScoreMandatory;
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


	public Integer getAssessmentMethodId() {
		return assessmentMethodId;
	}


	public void setAssessmentMethodId(Integer assessmentMethodId) {
		this.assessmentMethodId = assessmentMethodId;
	}


	public Integer getDdrFlow() {
		return ddrFlow;
	}


	public void setDdrFlow(Integer ddrFlow) {
		this.ddrFlow = ddrFlow;
	}
    

	

}
