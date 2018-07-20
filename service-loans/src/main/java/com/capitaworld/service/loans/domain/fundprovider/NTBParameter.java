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


}
