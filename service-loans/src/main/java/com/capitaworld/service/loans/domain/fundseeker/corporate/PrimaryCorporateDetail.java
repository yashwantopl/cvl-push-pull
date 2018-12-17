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


    @Column(name = "have_collateral_security")
    private Boolean haveCollateralSecurity;

    @Column(name = "collateral_security_amt")
    private Double collateralSecurityAmount;

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

    /*
     * Promoter Contribution Logic
     * BY Ravina
     * */

    @Column(name = "total_amt_percentage")
    private Double totalAmtPercentage;

    //Dhaval
    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "prop_cost")
    private Double proposedCost;

    @Column(name = "incremental_margin")
    private Double incrementalMargin;
    
    
    //By Akshay for OnePager Eligibility
    private Double actualSalesFinYearCurrent;
    
    private Double estimatedSalesFinYearNext;

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

    public Double getTotalAmtPercentage() {
        return totalAmtPercentage;
    }

    public void setTotalAmtPercentage(Double totalAmtPercentage) {
        this.totalAmtPercentage = totalAmtPercentage;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Double getProposedCost() {
        return proposedCost;
    }

    public void setProposedCost(Double proposedCost) {
        this.proposedCost = proposedCost;
    }

    public Double getIncrementalMargin() {
        return incrementalMargin;
    }

    public void setIncrementalMargin(Double incrementalMargin) {
        this.incrementalMargin = incrementalMargin;
    }

	public Double getActualSalesFinYearCurrent() {
		return actualSalesFinYearCurrent;
	}

	public void setActualSalesFinYearCurrent(Double actualSalesFinYearCurrent) {
		this.actualSalesFinYearCurrent = actualSalesFinYearCurrent;
	}

	public Double getEstimatedSalesFinYearNext() {
		return estimatedSalesFinYearNext;
	}

	public void setEstimatedSalesFinYearNext(Double estimatedSalesFinYearNext) {
		this.estimatedSalesFinYearNext = estimatedSalesFinYearNext;
	}

	@Override
	public String toString() {
		return "PrimaryCorporateDetail [applicationId=" + applicationId + ", loanAmount=" + loanAmount
				+ ", haveCollateralSecurity=" + haveCollateralSecurity + ", collateralSecurityAmount="
				+ collateralSecurityAmount + ", purposeOfLoanId=" + purposeOfLoanId + ", tenureRequired="
				+ tenureRequired + ", proposedDetailsOfUnit=" + proposedDetailsOfUnit + ", costOfProject="
				+ costOfProject + ", costOfMachinery=" + costOfMachinery + ", incrementalTurnover="
				+ incrementalTurnover + ", proposedOperationDate=" + proposedOperationDate
				+ ", proposedConstitutionOfUnit=" + proposedConstitutionOfUnit + ", promoterContribution="
				+ promoterContribution + ", assessmentId=" + assessmentId + ", commercialOperationDate="
				+ commercialOperationDate + ", factoryPremise=" + factoryPremise + ", knowHow=" + knowHow
				+ ", competition=" + competition + ", totalAmtPercentage=" + totalAmtPercentage + ", stateId=" + stateId
				+ ", cityId=" + cityId + ", proposedCost=" + proposedCost + ", incrementalMargin=" + incrementalMargin
				+ ", actualSalesFinYearCurrent=" + actualSalesFinYearCurrent + ", estimatedSalesFinYearNext="
				+ estimatedSalesFinYearNext + "]";
	}
}
