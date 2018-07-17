package com.capitaworld.service.loans.domain.fundprovider;


import javax.persistence.*;
import java.io.Serializable;
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
    private Integer minTenure;

    @Column(name="max_tenure")
    private Integer maxTenure;

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

    @Column(name="min_age_individual")
    private Integer minAgeIndividual;

    @Column(name="max_age_individual")
    private Integer maxAgeIndividual;

    @Column(name="is_age_individual_display")
    private Boolean isAgeIndividualDisplay=false;

    @Column(name="is_age_individual_mandatory")
    private Boolean isAgeIndividualMandatory=false;

    @Column(name="min_loan_amount")
    private Double minLoanAmount;

    @Column(name="max_loan_amount")
    private Double maxLoanAmount;

    @Column(name="is_loan_amount_display")
    private Boolean isLoanAmountDisplay=false;

    @Column(name="is_loan_amount_mandatory")
    private Boolean isLoanAmountMandatory=false;


    @Column(name="min_yearly_income_range")
    private Double minYearlyIncomeRange;

    @Column(name="max_yearly_income_range")
    private Double maxYearlyIncomeRange;

    @Column(name="is_yearly_income_display")
    private Boolean isYearlyIncomeDisplay=false;

    @Column(name="is_yearly_income_mandatory")
    private Boolean isYearlyIncomeMandatory=false;


    @Column(name="min_loan_asset_value")
    private Double minLoanAssetValue;

    @Column(name="max_loan_asset_value")
    private Double maxLoanAssetValue;

    @Column(name="is_loan_asset_value_display")
    private Boolean isLoanAssetValueDisplay=false;

    @Column(name="is_loan_asset_value_mandatory")
    private Boolean isLoanAssetValueMandatory=false;


    @Column(name="min_cibil_score")
    private Long minCibilScore;

    @Column(name="max_cibil_score")
    private Long maxCibilScore;

    @Column(name="is_cibil_score_display")
    private Boolean isCibilScoreDisplay=false;

    @Column(name="is_cibil_score_mandatory")
    private Boolean isCibilScoreMandatory=false;


    @Column(name="min_current_foir")
    private Double minCurrentFoir;

    @Column(name="max_current_foir")
    private Double maxCurrentFoir;

    @Column(name="is_current_foir_display")
    private Boolean isCurrentFoirDisplay=false;

    @Column(name="is_current_foir_mandatory")
    private Boolean isCurrentFoirMandatory=false;


    @Column(name="min_risk_score_model")
    private Long minRiskScoreModel;

    @Column(name="max_risk_score_model")
    private Long maxRiskScoreModel;

    @Column(name="is_risk_score_model_display")
    private Boolean isRiskScoreModelDisplay=false;

    @Column(name="is_risk_score_model_mandatory")
    private Boolean isRiskScoreModelMandatory=false;

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
