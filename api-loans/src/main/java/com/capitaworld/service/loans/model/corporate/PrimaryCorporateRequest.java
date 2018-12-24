package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryCorporateRequest extends LoanApplicationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double loanAmount;
    private Boolean haveCollateralSecurity;
    private Double collateralSecurityAmount;
    private Integer purposeOfLoanId;
    private Double tenureRequired;
    private Integer proposedDetailsOfUnit;
    private Double costOfProject;
    private Date proposedOperationDate;
    private Integer proposedConstitutionOfUnit;
    private Double promoterContribution;
    private Integer assessmentId;
    private Long stateId;
    private Long cityId;
    private Double proposedCost;

    /*
     * SBI MSME Integration related fields
     * By Ravina
     * */
    private Date commercialOperationDate;
    private Integer factoryPremise;
    private Integer knowHow;
    private Integer competition;

    /*
     * Promoter Contribution Logic
     * BY Ravina
     * */
    private Double totalAmtPercentage;
    /*For Eligibility Hybrid-payback method*/
    private Double incrementalMargin;
    
    
    //Start By Akshay for OnePager Eligibility
    private Double turnOverPrevFinYear;
    
    private Double turnOverCurrFinYearTillMonth;
    
    private Double projectedTurnOverCurrFinYear;
    
    private Double profitCurrFinYear;
    
    private Double projectedProfitCurrFinYear;
    
  //End By Akshay for OnePager Eligibility


    public PrimaryCorporateRequest() {
        // Do nothing because of X and Y.
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

	public Double getTurnOverPrevFinYear() {
		return turnOverPrevFinYear;
	}

	public void setTurnOverPrevFinYear(Double turnOverPrevFinYear) {
		this.turnOverPrevFinYear = turnOverPrevFinYear;
	}

	public Double getTurnOverCurrFinYearTillMonth() {
		return turnOverCurrFinYearTillMonth;
	}

	public void setTurnOverCurrFinYearTillMonth(Double turnOverCurrFinYearTillMonth) {
		this.turnOverCurrFinYearTillMonth = turnOverCurrFinYearTillMonth;
	}

	public Double getProjectedTurnOverCurrFinYear() {
		return projectedTurnOverCurrFinYear;
	}

	public void setProjectedTurnOverCurrFinYear(Double projectedTurnOverCurrFinYear) {
		this.projectedTurnOverCurrFinYear = projectedTurnOverCurrFinYear;
	}

	public Double getProfitCurrFinYear() {
		return profitCurrFinYear;
	}

	public void setProfitCurrFinYear(Double profitCurrFinYear) {
		this.profitCurrFinYear = profitCurrFinYear;
	}

	public Double getProjectedProfitCurrFinYear() {
		return projectedProfitCurrFinYear;
	}

	public void setProjectedProfitCurrFinYear(Double projectedProfitCurrFinYear) {
		this.projectedProfitCurrFinYear = projectedProfitCurrFinYear;
	}
    
}
