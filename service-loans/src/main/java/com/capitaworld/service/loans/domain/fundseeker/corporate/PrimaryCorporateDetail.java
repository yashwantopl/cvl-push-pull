package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_corporate_primary_details")
@NamedQuery(name = "PrimaryCorporateDetail.findAll", query = "SELECT p FROM PrimaryCorporateDetail p")
public class PrimaryCorporateDetail extends LoanApplicationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "application_id")
    private LoanApplicationMaster applicationId;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "business_asset_amt")
    private Double businessAssetAmount;

    @Column(name = "wc_amt")
    private Double wcAmount;

    @Column(name = "other_amt")
    private Double otherAmt;

    @Column(name = "have_collateral_security")
    private Boolean haveCollateralSecurity;

    @Column(name = "collateral_security_amt")
    private Double collateralSecurityAmount;

    /*@Column(name = "is_business_asset_checked")
    private Boolean isBusinessAssetChecked;

    @Column(name = "is_working_capital_checked")
    private Boolean isWorkingCapitalChecked;

    @Column(name = "is_other_general_checked")
    private Boolean isOtherGeneralChecked;*/

    @Column(name = "purpose_of_loan_id")
    private Integer purposeOfLoanId;

    @Column(name = "tenure_required")
    private Double tenureRequired;

    @Column(name = "proposed_details_of_unit")
    private Integer proposedDetailsOfUnit;

    @Column(name = "cost_of_project")
    private Double costOfProject;

    @Column(name = "cost_of_machinery")
    private Double costOfMachinery;

    @Column(name = "incremental_turnover")
    private Double incrementalTurnover;

    @Column(name = "proposed_operation_date")
    private Date proposedOperationDate;

    @Column(name = "proposed_const_of_unit")
    private Integer proposedConstitutionOfUnit;

    @Column(name = "promoter_contribution")
    private Double promoterContribution;

    @Column(name = "assessment_id")
    private Integer assessmentId;

    /*
     * SBI MSME Integration related fields
     * By Ravina
     * */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "com_op_date")
    private Date commercialOperationDate;

    @Column(name = "factory_premise")
    private Integer factoryPremise;

    @Column(name = "know_how")
    private Integer knowHow;

    @Column(name = "competition")
    private Integer competition;

   /* @Column(name="created_by")
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
    private Date modifiedDate;*/

    public PrimaryCorporateDetail() {
    }

    public LoanApplicationMaster getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(LoanApplicationMaster applicationId) {
        this.applicationId = applicationId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getBusinessAssetAmount() {
        return businessAssetAmount;
    }

    public void setBusinessAssetAmount(Double businessAssetAmount) {
        this.businessAssetAmount = businessAssetAmount;
    }

    public Double getWcAmount() {
        return wcAmount;
    }

    public void setWcAmount(Double wcAmount) {
        this.wcAmount = wcAmount;
    }

    public Double getOtherAmt() {
        return otherAmt;
    }

    public void setOtherAmt(Double otherAmt) {
        this.otherAmt = otherAmt;
    }


    public Boolean getHaveCollateralSecurity() {
        return haveCollateralSecurity;
    }

    public void setHaveCollateralSecurity(Boolean haveCollateralSecurity) {
        this.haveCollateralSecurity = haveCollateralSecurity;
    }

    public Double getCollateralSecurityAmount() {
        return collateralSecurityAmount;
    }

    public void setCollateralSecurityAmount(Double collateralSecurityAmount) {
        this.collateralSecurityAmount = collateralSecurityAmount;
    }

    public Integer getPurposeOfLoanId() {
        return purposeOfLoanId;
    }

    public void setPurposeOfLoanId(Integer purposeOfLoanId) {
        this.purposeOfLoanId = purposeOfLoanId;
    }

    public Double getTenureRequired() {
        return tenureRequired;
    }

    public void setTenureRequired(Double tenureRequired) {
        this.tenureRequired = tenureRequired;
    }

    public Integer getProposedDetailsOfUnit() {
        return proposedDetailsOfUnit;
    }

    public void setProposedDetailsOfUnit(Integer proposedDetailsOfUnit) {
        this.proposedDetailsOfUnit = proposedDetailsOfUnit;
    }

    public Double getCostOfProject() {
        return costOfProject;
    }

    public void setCostOfProject(Double costOfProject) {
        this.costOfProject = costOfProject;
    }

    public Double getCostOfMachinery() {
        return costOfMachinery;
    }

    public void setCostOfMachinery(Double costOfMachinery) {
        this.costOfMachinery = costOfMachinery;
    }

    public Double getIncrementalTurnover() {
        return incrementalTurnover;
    }

    public void setIncrementalTurnover(Double incrementalTurnover) {
        this.incrementalTurnover = incrementalTurnover;
    }
    
    public Date getProposedOperationDate() {
        return proposedOperationDate;
    }

    public void setProposedOperationDate(Date proposedOperationDate) {
        this.proposedOperationDate = proposedOperationDate;
    }

    public Integer getProposedConstitutionOfUnit() {
        return proposedConstitutionOfUnit;
    }

    public void setProposedConstitutionOfUnit(Integer proposedConstitutionOfUnit) {
        this.proposedConstitutionOfUnit = proposedConstitutionOfUnit;
    }

    public Double getPromoterContribution() {
        return promoterContribution;
    }

    public void setPromoterContribution(Double promoterContribution) {
        this.promoterContribution = promoterContribution;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Date getCommercialOperationDate() {
        return commercialOperationDate;
    }

    public void setCommercialOperationDate(Date commercialOperationDate) {
        this.commercialOperationDate = commercialOperationDate;
    }

    public Integer getFactoryPremise() {
        return factoryPremise;
    }

    public void setFactoryPremise(Integer factoryPremise) {
        this.factoryPremise = factoryPremise;
    }

    public Integer getKnowHow() {
        return knowHow;
    }

    public void setKnowHow(Integer knowHow) {
        this.knowHow = knowHow;
    }

    public Integer getCompetition() {
        return competition;
    }

    public void setCompetition(Integer competition) {
        this.competition = competition;
    }

    /* @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public Long getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }*/
}
