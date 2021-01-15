package com.opl.mudra.api.scoring.model;

import java.util.List;

/**
 * @author sanket.devare
 *
 */
/**
 * @author sanket.devare
 *
 */
public class ScoringParameterRequest {


    private Boolean combinedNetworth_p = false;
    private Boolean customerAsscociateConcern_p= false;
    private Boolean cibilTransunionScore_p= false;
    private Boolean debtEquityRatio_p= false;
    private Boolean tolTnw_p= false;
    private Boolean avgCurrentRatio_p= false;
    private Boolean workingCapitalCycle_p= false;
    private Boolean avgAnnualGrowthGrossCash_p= false;
    private Boolean avgAnnualGrowthNetSale_p= false;
    private Boolean avgEBIDTA_p= false;
    private Boolean avgAnnualGrossCashAccuruals_p= false;
    private Boolean avgInterestCovRatio_p= false;
    private Boolean noOfCustomer_p= false;
    private Boolean concentrationCustomer_p= false;
    private Boolean experienceInTheBusiness_p= false;
    private Boolean creditSummation_p= false;

    ////// new SBI parameter

    private Boolean age_p= false;
    private Boolean noOfChildren_p= false;
    private Boolean owningHouse_p= false;
    private Boolean acadamicQualification_p= false;
    private Boolean expLineOfTrade_p= false;
    private Boolean spouseDetails_p= false;
    private Boolean assessedForIncomeTax_p= false;
    private Boolean haveLifeIncPolicy_p= false;
    private Boolean yearsInBusiness_p= false;
    private Boolean repaymentPeriod_p= false;
    private Boolean continousNetProfit_p= false;
    private Boolean qualityOfReceivable_p= false;
    private Boolean qualityOfFinishedGood_p= false;
    private Boolean knowHow_p= false;
    private Boolean lineOfActivity_p= false;
    private Boolean competition_p= false;
    private Boolean factoryPremises_p= false;
    private Boolean salesShowArisingTrend_p= false;

    // new kotak parameter

    private Boolean utilisationPercentage_p= false;
    private Boolean turnOverToLimitRatio_p= false;
    private Boolean collateralCoverage_p= false;
    private Boolean debtServiceCoverageRatio_p= false;


    //////////////

    // New parameters
        private Boolean pastYearTurnover_p= false;
        private Boolean debtEBITDA_p = false;
        private Boolean turnoverATNW_p = false;
        private Boolean chequesBouncedLastMonth_p = false;
        private Boolean chequesBouncedLastSixMonth_p = false;
    //

    private boolean patNetSalesRatio_p=false;

    private boolean statutoryCompliance_p=false;

    private boolean paymentRecordsWithLenders_p=false;
    
    
    // New Parameter Msme Scoring 
    private Boolean cmrScoreMsmeRanking_p = false;
    private Double cmrScoreMsmeRanking;
    
    private Boolean isoCertification_p = false;
    private Boolean isoCertificationVal;
    
    private Boolean totalNoOfChequeBounceLastSixMonths_p = false;
    private Double totalNoOfChequeBounceLastSixMonths;
    

    private Double otherRevenueIncomeFY;

    private Double otherRevenueIncomeSY;

    private Double otherRevenueIncomeTY;

    private Integer statutoryComplianceType;

    private Integer dpd;

    private Integer ityYearType;

    private Double assertClassification;

    private Double networthSum;

    private Double termLoanFy;

    private Double termLoanSy;

    private Double termLoanTy;

    private Double loanAmount;


    private Double customerAssociateConcern;

    private Double cibilTransuniunScore;


    private Double DebtFY;

    private Double DebtSY;

    private Double DebtTY;

    private Double equityFY;

    private Double equitySY;

    private Double equityTY;


    private Double tolFY;

    private Double tolSY;

    private Double tolTY;

    private Double tnwFY;

    private Double tnwSY;

    private Double tnwTY;


    private Double avgCurrentRatioFY;

    private Double avgCurrentRatioSY;

    private Double avgCurrentRatioTY;


    private Double debtorsDaysFY;

    private Double debtorsDaysSY;

    private Double debtorsDaysTY;

    private Double avgInventoryFY;

    private Double avgInventorySY;

    private Double avgInventoryTY;

    private Double cogsFY;

    private Double cogsSY;

    private Double cogsTY;

    private Double creditorsDaysFY;

    private Double creditorsDaysSY;

    private Double creditorsDaysTY;


    private Double netProfitOrLossFY;

    private Double netProfitOrLossSY;

    private Double netProfitOrLossTY;


    private Double depriciationFy;

    private Double depriciationSy;

    private Double depriciationTy;


    private Double interestFy;

    private Double interestSy;

    private Double interestTy;


    private Double totalSaleFy;

    private Double totalSaleSy;

    private Double totalSaleTy;


    private Double profitBeforeTaxOrLossFy;

    private Double profitBeforeTaxOrLossSy;

    private Double profitBeforeTaxOrLossTy;


    private Double totalAssetFy;

    private Double totalAssetSy;

    private Double totalAssetTy;


    private Double opProfitBeforeInterestFy;

    private Double opProfitBeforeInterestSy;

    private Double opProfitBeforeInterestTy;


    private Double noOfCustomenr;

    private Double concentrationCustomer;


    private Double experienceInTheBusiness;


    private Double totalCredit;


    private Double projectedSale;
    
    private Double creditSummation;


    ////// new SBI parameter

    private Double age;

    private Double noOfChildren;

    private Long owningHouse;

    private Long acadamicQualification;

    private Double experienceInLineOfBusiness;

    private Long spouceDetails;

    private Long assessedFOrIT;

    private Long haveLIPolicy;

    private Double repaymentPeriod;

    private Long knowHow;

    private Long lineOfActivity;

    private Long competition;

    private Long factoryPremises;

    private Double grossSaleTy;

    private Double totalCostSaleTy;

    private Double finishedGoodTy;

    private Double gstSaleCurrentYear;

    private Double netSaleFy;

    private Double netSaleSy;

    private Double netSaleTy;

    private Double yearsInBusiness;

    private Double continuousNetProfitOrLossFY;

    private Double continuousNetProfitOrLossSY;

    private Double continuousNetProfitOrLossTY;

    private Integer noOfMonths;


    // new kotak parameter

    private Double averageDailyBalance;
    private Double limitsInAccount;
    private Double turnOver;
    private Double eligibleLoanAmountCircular;

    private Double ebitdaFY;

    private Double ebitdaSY;

    private Double ebitdaTY;

    private Double existingLoanObligation;
    private Integer loanType;
    private Double roi;
    private Double tenure;
    private Double tenureFs;
    
    private Boolean tenure_p = false;

    /////////////

    //New parameters
    private Double pastYearTurnover;
    private Double exportSalesFY;

    private Double exportSalesSY;

    private Double exportSalesTY;

    private Double domesticSalesFY;

    private Double domesticSalesSY;

    private Double domesticSalesTY;

    private Double totalTermLiabilitiesFY;

    private Double totalTermLiabilitiesSY;

    private Double totalTermLiabilitiesTY;

    private Double preferenceSharesFY;

    private Double preferenceSharesSY;

    private Double preferenceSharesTY;

    private Double unsecuredLoansFromOthersFY;

    private Double unsecuredLoansFromOthersSY;

    private Double unsecuredLoansFromOthersTY;

    private Double othersFY;

    private Double othersSY;

    private Double othersTY;

    private Double minorityInterestFY;

    private Double minorityInterestSY;

    private Double minorityInterestTY;

    private Double deferredTaxLiabilityFY;

    private Double deferredTaxLiabilitySY;

    private Double deferredTaxLiabilityTY;

    private Double deferredTaxAssetsFY;

    private Double deferredTaxAssetsSY;

    private Double deferredTaxAssetsTY;

    private Double profitBeforeInterestFY;

    private Double profitBeforeInterestSY;

    private Double profitBeforeInterestTY;

    private Double depreciationFY;

    private Double depreciationSY;

    private Double depreciationTY;

    private Double liabilitiesOrdinaryShareCapitalFY;

    private Double liabilitiesOrdinaryShareCapitalSY;

    private Double liabilitiesOrdinaryShareCapitalTY;

    private Double liabilitiesGeneralReserveFY;

    private Double liabilitiesGeneralReserveSY;

    private Double liabilitiesGeneralReserveTY;

    private Double liabilitiesSurplus;

    private Double deficitInProfitANDLossAccountFY;

    private Double deficitInProfitANDLossAccountSY;

    private Double deficitInProfitANDLossAccountTY;

    private Double liabilitiesUnsecuredLoansFromPpromotersFY;

    private Double liabilitiesUnsecuredLoansFromPpromotersSY;

    private Double liabilitiesUnsecuredLoansFromPpromotersTY;

    private Double liabilitiesUnsecuredLoansFromOthersFY;

    private Double liabilitiesUnsecuredLoansFromOthersSY;

    private Double liabilitiesUnsecuredLoansFromOthersTY;

    private Double assetsInvestmentsInSubsidiaryCosaffiliatesFY;

    private Double assetsInvestmentsInSubsidiaryCosaffiliatesSY;

    private Double assetsInvestmentsInSubsidiaryCosaffiliatesTY;

    private Double noOfChequesBouncedLastMonth;
    private Double noOfChequesBouncedLastSixMonth;
    
    private Double totalNoOfInwardChequeBouncesLatSixMonths;
    
    
    // Mudra Loans Added Parameters
    
    private Boolean idProof_p = false;
    
    private Integer idProof;
    
    private Boolean noOfDependents_p = false;
    
    private Integer noOfDependents;
    
    private Integer addressYear;
    
    private Boolean addressYear_p = false;
    
    private Boolean certification_p = false;
    
    private Integer certification;
    
    private Integer mainDirectorCategory;
    
    private Boolean mainDirectorCategory_p = false;
    
    
    private Long castCategory;
    
    private Boolean castCategory_p = false;
    
    
    private Integer otherSourceOfIncome;
    
    private Boolean otherSourceOfIncome_p = false;
    
    private List<Integer> parameters;
    
    private Boolean parameters_p = false;
    
    private Boolean sales_p  = false;
    
    private Double sales;
    
    private Boolean pat_p = false;
    
    private Double pat;
    
    private Long aadhar;
    
    private Boolean aadhar_p= false;
    
    private Integer marketingArrangmentForFinishedGoods;
    
    private Boolean marketingArrangmentForFinishedGoods_p = false;
    
    private List<Integer> registrationWithGovernmentAuthorities;
    
    private Integer registrationWithGovernmentAuthoritiesCombined;
    
    private Boolean registrationWithGovernmentAuthorities_p = false;
    
    private Boolean registrationWithGovernmentAuthoritiesCombined_p = false;
    
    private Integer businessProspects;
    
    private Boolean businessProspects_p = false;
    
    
    private Integer accessInputs;
    
    private Boolean accessInputs_p = false;
    
    private Integer itReturnFiledId;
    
    private Boolean isItReturnFiled_p = false;
    
    private Long bankRelation;
    
    private Integer bankRelationCombined;
    
    private Boolean bankRelation_p = false;
    
    private Boolean bankRelationCombined_p = false;
    
    private Boolean typeOfActivity_p =false;
    
    private Integer typeOfActivity;
    
    private Integer distanceBtwWorkAndRes;
    
    private Boolean distanceBtwWorkAndRes_p = false;
    
    private Integer employmentGeneration;
    
    private Boolean employmentGeneration_p = false;
    
    private Double promotersComtributionML;
    
    private Boolean promotersComtributionML_p = false;

    private Double experienceInBusinessVal;

    private Boolean experienceInBusiness_p = false;

    private Boolean fleetStrengthOwnedbyFleetOperator_p = false;
    private Integer noOfVehicles;
    private Boolean assuredOrder_p = false;
    private Boolean assuredOrderVal = false;
    private Boolean familyMembersDirectlyVal_p = false;
    private Boolean familyMembersDirectlyVal = false;
    private Boolean loanFreeVehicle_p = false;
    private Double totalCostOfExistingVehicleVal;
    private Double totalSanctionVal;
    private Boolean depositPositionPotential_p = false;
    private Double totalDebit;
    private Integer fullMonthCount;
    private Integer profitabilityHistoryVal;
    private Boolean profitabilityHistory_p = false;
    private Double amountOfCollateral;
    private Boolean collateralSecurity_p = false;
    private Double conservativeDebtServiceCoverageVal;
    private Boolean conservativeDebtServiceCoverage_p = false;

    public Double getTotalCostOfExistingVehicleVal() {
        return totalCostOfExistingVehicleVal;
    }

    public void setTotalCostOfExistingVehicleVal(Double totalCostOfExistingVehicleVal) {
        this.totalCostOfExistingVehicleVal = totalCostOfExistingVehicleVal;
    }

    public Boolean getTypeOfActivity_p() {
		return typeOfActivity_p;
	}

	public void setTypeOfActivity_p(Boolean typeOfActivity_p) {
		this.typeOfActivity_p = typeOfActivity_p;
	}

	public Integer getTypeOfActivity() {
		return typeOfActivity;
	}

	public void setTypeOfActivity(Integer typeOfActivity) {
		this.typeOfActivity = typeOfActivity;
	}

	public Boolean getTenure_p() {
		return tenure_p;
	}

	public void setTenure_p(Boolean tenure_p) {
		this.tenure_p = tenure_p;
	}

	public Long getBankRelation() {
		return bankRelation;
	}

	public void setBankRelation(Long bankRelation) {
		this.bankRelation = bankRelation;
	}

	public Boolean getBankRelation_p() {
		return bankRelation_p;
	}

	public void setBankRelation_p(Boolean bankRelation_p) {
		this.bankRelation_p = bankRelation_p;
	}

	public Boolean getIsItReturnFiled_p() {
		return isItReturnFiled_p;
	}

	public void setIsItReturnFiled_p(Boolean isItReturnFiled_p) {
		this.isItReturnFiled_p = isItReturnFiled_p;
	}

	public Integer getAccessInputs() {
		return accessInputs;
	}

	public void setAccessInputs(Integer accessInputs) {
		this.accessInputs = accessInputs;
	}

	public Boolean getAccessInputs_p() {
		return accessInputs_p;
	}

	public void setAccessInputs_p(Boolean accessInputs_p) {
		this.accessInputs_p = accessInputs_p;
	}

	public Integer getBusinessProspects() {
		return businessProspects;
	}

	public void setBusinessProspects(Integer businessProspects) {
		this.businessProspects = businessProspects;
	}

	public Boolean getBusinessProspects_p() {
		return businessProspects_p;
	}

	public void setBusinessProspects_p(Boolean businessProspects_p) {
		this.businessProspects_p = businessProspects_p;
	}

	public List<Integer> getRegistrationWithGovernmentAuthorities() {
		return registrationWithGovernmentAuthorities;
	}

	public void setRegistrationWithGovernmentAuthorities(List<Integer> registrationWithGovernmentAuthorities) {
		this.registrationWithGovernmentAuthorities = registrationWithGovernmentAuthorities;
	}

	public Boolean getRegistrationWithGovernmentAuthorities_p() {
		return registrationWithGovernmentAuthorities_p;
	}

	public void setRegistrationWithGovernmentAuthorities_p(Boolean registrationWithGovernmentAuthorities_p) {
		this.registrationWithGovernmentAuthorities_p = registrationWithGovernmentAuthorities_p;
	}

	public Integer getMarketingArrangmentForFinishedGoods() {
		return marketingArrangmentForFinishedGoods;
	}

	public void setMarketingArrangmentForFinishedGoods(Integer marketingArrangmentForFinishedGoods) {
		this.marketingArrangmentForFinishedGoods = marketingArrangmentForFinishedGoods;
	}

	public Boolean getMarketingArrangmentForFinishedGoods_p() {
		return marketingArrangmentForFinishedGoods_p;
	}

	public void setMarketingArrangmentForFinishedGoods_p(Boolean marketingArrangmentForFinishedGoods_p) {
		this.marketingArrangmentForFinishedGoods_p = marketingArrangmentForFinishedGoods_p;
	}

	public Long getAadhar() {
		return aadhar;
	}

	public void setAadhar(Long aadhar) {
		this.aadhar = aadhar;
	}

	public Boolean getAadhar_p() {
		return aadhar_p;
	}

	public void setAadhar_p(Boolean aadhar_p) {
		this.aadhar_p = aadhar_p;
	}

	public Boolean getPat_p() {
		return pat_p;
	}

	public void setPat_p(Boolean pat_p) {
		this.pat_p = pat_p;
	}

	public Double getPat() {
		return pat;
	}

	public void setPat(Double pat) {
		this.pat = pat;
	}

	public Boolean getSales_p() {
		return sales_p;
	}

	public void setSales_p(Boolean sales_p) {
		this.sales_p = sales_p;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public List<Integer> getParameters() {
		return parameters;
	}

	public void setParameters(List<Integer> parameters) {
		this.parameters = parameters;
	}

	public Boolean getParameters_p() {
		return parameters_p;
	}

	public void setParameters_p(Boolean parameters_p) {
		this.parameters_p = parameters_p;
	}

	public Integer getOtherSourceOfIncome() {
		return otherSourceOfIncome;
	}

	public void setOtherSourceOfIncome(Integer otherSourceOfIncome) {
		this.otherSourceOfIncome = otherSourceOfIncome;
	}

	public Boolean getOtherSourceOfIncome_p() {
		return otherSourceOfIncome_p;
	}

	public void setOtherSourceOfIncome_p(Boolean otherSourceOfIncome_p) {
		this.otherSourceOfIncome_p = otherSourceOfIncome_p;
	}


	public Long getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(Long castCategory) {
		this.castCategory = castCategory;
	}

	public Boolean getCastCategory_p() {
		return castCategory_p;
	}

	public void setCastCategory_p(Boolean castCategory_p) {
		this.castCategory_p = castCategory_p;
	}

	public Integer getMainDirectorCategory() {
		return mainDirectorCategory;
	}

	public void setMainDirectorCategory(Integer mainDirectorCategory) {
		this.mainDirectorCategory = mainDirectorCategory;
	}

	public Boolean getMainDirectorCategory_p() {
		return mainDirectorCategory_p;
	}

	public void setMainDirectorCategory_p(Boolean mainDirectorCategory_p) {
		this.mainDirectorCategory_p = mainDirectorCategory_p;
	}

	public Boolean getCertification_p() {
		return certification_p;
	}

	public void setCertification_p(Boolean certification_p) {
		this.certification_p = certification_p;
	}

	public Integer getCertification() {
		return certification;
	}

	public void setCertification(Integer certification) {
		this.certification = certification;
	}

	public Integer getAddressYear() {
		return addressYear;
	}

	public void setAddressYear(Integer addressYear) {
		this.addressYear = addressYear;
	}

	public Boolean getAddressYear_p() {
		return addressYear_p;
	}

	public void setAddressYear_p(Boolean addressYear_p) {
		this.addressYear_p = addressYear_p;
	}

	public Boolean getNoOfDependents_p() {
		return noOfDependents_p;
	}

	public void setNoOfDependents_p(Boolean noOfDependents_p) {
		this.noOfDependents_p = noOfDependents_p;
	}

	public Integer getNoOfDependents() {
		return noOfDependents;
	}

	public void setNoOfDependents(Integer noOfDependents) {
		this.noOfDependents = noOfDependents;
	}

	public Boolean getIdProof_p() {
		return idProof_p;
	}

	public void setIdProof_p(Boolean idProof_p) {
		this.idProof_p = idProof_p;
	}

	public Integer getIdProof() {
		return idProof;
	}

	public void setIdProof(Integer idProof) {
		this.idProof = idProof;
	}

	/**
	 * @return the noOfChequesBouncedLastSixMonth
	 */
	public Double getNoOfChequesBouncedLastSixMonth() {
		return noOfChequesBouncedLastSixMonth;
	}

	/**
	 * @param noOfChequesBouncedLastSixMonth the noOfChequesBouncedLastSixMonth to set
	 */
	public void setNoOfChequesBouncedLastSixMonth(Double noOfChequesBouncedLastSixMonth) {
		this.noOfChequesBouncedLastSixMonth = noOfChequesBouncedLastSixMonth;
	}

	public Double getTotalNoOfInwardChequeBouncesLatSixMonths() {
		return totalNoOfInwardChequeBouncesLatSixMonths;
	}

	public void setTotalNoOfInwardChequeBouncesLatSixMonths(Double totalNoOfInwardChequeBouncesLatSixMonths) {
		this.totalNoOfInwardChequeBouncesLatSixMonths = totalNoOfInwardChequeBouncesLatSixMonths;
	}

    public Double getRoi() {
        return roi;
    }
	public void setRoi(Double roi) {
        this.roi = roi;
    }

    public Double getTenure() {
        return tenure;
    }

    public void setTenure(Double tenure) {
        this.tenure = tenure;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Boolean getDebtServiceCoverageRatio_p() {
        return debtServiceCoverageRatio_p;
    }

    public void setDebtServiceCoverageRatio_p(Boolean debtServiceCoverageRatio_p) {
        this.debtServiceCoverageRatio_p = debtServiceCoverageRatio_p;
    }


    public Double getExistingLoanObligation() {
        return existingLoanObligation;
    }

    public void setExistingLoanObligation(Double existingLoanObligation) {
        this.existingLoanObligation = existingLoanObligation;
    }

    public Boolean getCombinedNetworth_p() {
        return combinedNetworth_p;
    }

    public void setCombinedNetworth_p(Boolean combinedNetworth_p) {
        this.combinedNetworth_p = combinedNetworth_p;
    }

    public Boolean getCustomerAsscociateConcern_p() {
        return customerAsscociateConcern_p;
    }

    public void setCustomerAsscociateConcern_p(Boolean customerAsscociateConcern_p) {
        this.customerAsscociateConcern_p = customerAsscociateConcern_p;
    }

    public Boolean getCibilTransunionScore_p() {
        return cibilTransunionScore_p;
    }

    public void setCibilTransunionScore_p(Boolean cibilTransunionScore_p) {
        this.cibilTransunionScore_p = cibilTransunionScore_p;
    }

    public Boolean getDebtEquityRatio_p() {
        return debtEquityRatio_p;
    }

    public void setDebtEquityRatio_p(Boolean debtEquityRatio_p) {
        this.debtEquityRatio_p = debtEquityRatio_p;
    }

    public Boolean getTolTnw_p() {
        return tolTnw_p;
    }

    public void setTolTnw_p(Boolean tolTnw_p) {
        this.tolTnw_p = tolTnw_p;
    }

    public Boolean getAvgCurrentRatio_p() {
        return avgCurrentRatio_p;
    }

    public void setAvgCurrentRatio_p(Boolean avgCurrentRatio_p) {
        this.avgCurrentRatio_p = avgCurrentRatio_p;
    }

    public Boolean getWorkingCapitalCycle_p() {
        return workingCapitalCycle_p;
    }

    public void setWorkingCapitalCycle_p(Boolean workingCapitalCycle_p) {
        this.workingCapitalCycle_p = workingCapitalCycle_p;
    }

    public Boolean getAvgAnnualGrowthGrossCash_p() {
        return avgAnnualGrowthGrossCash_p;
    }

    public void setAvgAnnualGrowthGrossCash_p(Boolean avgAnnualGrowthGrossCash_p) {
        this.avgAnnualGrowthGrossCash_p = avgAnnualGrowthGrossCash_p;
    }

    public Boolean getAvgAnnualGrowthNetSale_p() {
        return avgAnnualGrowthNetSale_p;
    }

    public void setAvgAnnualGrowthNetSale_p(Boolean avgAnnualGrowthNetSale_p) {
        this.avgAnnualGrowthNetSale_p = avgAnnualGrowthNetSale_p;
    }

    public Boolean getAvgEBIDTA_p() {
        return avgEBIDTA_p;
    }

    public void setAvgEBIDTA_p(Boolean avgEBIDTA_p) {
        this.avgEBIDTA_p = avgEBIDTA_p;
    }

    public Boolean getAvgAnnualGrossCashAccuruals_p() {
        return avgAnnualGrossCashAccuruals_p;
    }

    public void setAvgAnnualGrossCashAccuruals_p(Boolean avgAnnualGrossCashAccuruals_p) {
        this.avgAnnualGrossCashAccuruals_p = avgAnnualGrossCashAccuruals_p;
    }

    public Boolean getAvgInterestCovRatio_p() {
        return avgInterestCovRatio_p;
    }

    public void setAvgInterestCovRatio_p(Boolean avgInterestCovRatio_p) {
        this.avgInterestCovRatio_p = avgInterestCovRatio_p;
    }

    public Boolean getNoOfCustomer_p() {
        return noOfCustomer_p;
    }

    public void setNoOfCustomer_p(Boolean noOfCustomer_p) {
        this.noOfCustomer_p = noOfCustomer_p;
    }

    public Boolean getConcentrationCustomer_p() {
        return concentrationCustomer_p;
    }

    public void setConcentrationCustomer_p(Boolean concentrationCustomer_p) {
        this.concentrationCustomer_p = concentrationCustomer_p;
    }

    public Boolean getExperienceInTheBusiness_p() {
        return experienceInTheBusiness_p;
    }

    public void setExperienceInTheBusiness_p(Boolean experienceInTheBusiness_p) {
        this.experienceInTheBusiness_p = experienceInTheBusiness_p;
    }

    public Boolean getCreditSummation_p() {
        return creditSummation_p;
    }

    public void setCreditSummation_p(Boolean creditSummation_p) {
        this.creditSummation_p = creditSummation_p;
    }

    public Boolean getAge_p() {
        return age_p;
    }

    public void setAge_p(Boolean age_p) {
        this.age_p = age_p;
    }

    public Boolean getNoOfChildren_p() {
        return noOfChildren_p;
    }

    public void setNoOfChildren_p(Boolean noOfChildren_p) {
        this.noOfChildren_p = noOfChildren_p;
    }

    public Boolean getOwningHouse_p() {
        return owningHouse_p;
    }

    public void setOwningHouse_p(Boolean owningHouse_p) {
        this.owningHouse_p = owningHouse_p;
    }

    public Boolean getAcadamicQualification_p() {
        return acadamicQualification_p;
    }

    public void setAcadamicQualification_p(Boolean acadamicQualification_p) {
        this.acadamicQualification_p = acadamicQualification_p;
    }

    public Boolean getExpLineOfTrade_p() {
        return expLineOfTrade_p;
    }

    public void setExpLineOfTrade_p(Boolean expLineOfTrade_p) {
        this.expLineOfTrade_p = expLineOfTrade_p;
    }

    public Boolean getSpouseDetails_p() {
        return spouseDetails_p;
    }

    public void setSpouseDetails_p(Boolean spouseDetails_p) {
        this.spouseDetails_p = spouseDetails_p;
    }

    public Boolean getAssessedForIncomeTax_p() {
        return assessedForIncomeTax_p;
    }

    public void setAssessedForIncomeTax_p(Boolean assessedForIncomeTax_p) {
        this.assessedForIncomeTax_p = assessedForIncomeTax_p;
    }

    public Boolean getHaveLifeIncPolicy_p() {
        return haveLifeIncPolicy_p;
    }

    public void setHaveLifeIncPolicy_p(Boolean haveLifeIncPolicy_p) {
        this.haveLifeIncPolicy_p = haveLifeIncPolicy_p;
    }

    public Boolean getYearsInBusiness_p() {
        return yearsInBusiness_p;
    }

    public void setYearsInBusiness_p(Boolean yearsInBusiness_p) {
        this.yearsInBusiness_p = yearsInBusiness_p;
    }

    public Boolean getRepaymentPeriod_p() {
        return repaymentPeriod_p;
    }

    public void setRepaymentPeriod_p(Boolean repaymentPeriod_p) {
        this.repaymentPeriod_p = repaymentPeriod_p;
    }

    public Boolean getContinousNetProfit_p() {
        return continousNetProfit_p;
    }

    public void setContinousNetProfit_p(Boolean continousNetProfit_p) {
        this.continousNetProfit_p = continousNetProfit_p;
    }

    public Boolean getQualityOfReceivable_p() {
        return qualityOfReceivable_p;
    }

    public void setQualityOfReceivable_p(Boolean qualityOfReceivable_p) {
        this.qualityOfReceivable_p = qualityOfReceivable_p;
    }

    public Boolean getQualityOfFinishedGood_p() {
        return qualityOfFinishedGood_p;
    }

    public void setQualityOfFinishedGood_p(Boolean qualityOfFinishedGood_p) {
        this.qualityOfFinishedGood_p = qualityOfFinishedGood_p;
    }

    public Boolean getKnowHow_p() {
        return knowHow_p;
    }

    public void setKnowHow_p(Boolean knowHow_p) {
        this.knowHow_p = knowHow_p;
    }

    public Boolean getLineOfActivity_p() {
        return lineOfActivity_p;
    }

    public void setLineOfActivity_p(Boolean lineOfActivity_p) {
        this.lineOfActivity_p = lineOfActivity_p;
    }

    public Boolean getCompetition_p() {
        return competition_p;
    }

    public void setCompetition_p(Boolean competition_p) {
        this.competition_p = competition_p;
    }

    public Boolean getFactoryPremises_p() {
        return factoryPremises_p;
    }

    public void setFactoryPremises_p(Boolean factoryPremises_p) {
        this.factoryPremises_p = factoryPremises_p;
    }

    public Boolean getSalesShowArisingTrend_p() {
        return salesShowArisingTrend_p;
    }

    public void setSalesShowArisingTrend_p(Boolean salesShowArisingTrend_p) {
        this.salesShowArisingTrend_p = salesShowArisingTrend_p;
    }

    public Double getNetworthSum() {
        return networthSum;
    }

    public void setNetworthSum(Double networthSum) {
        this.networthSum = networthSum;
    }

    public Double getTermLoanTy() {
        return termLoanTy;
    }

    public void setTermLoanTy(Double termLoanTy) {
        this.termLoanTy = termLoanTy;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getCustomerAssociateConcern() {
        return customerAssociateConcern;
    }

    public void setCustomerAssociateConcern(Double customerAssociateConcern) {
        this.customerAssociateConcern = customerAssociateConcern;
    }

    public Double getCibilTransuniunScore() {
        return cibilTransuniunScore;
    }

    public void setCibilTransuniunScore(Double cibilTransuniunScore) {
        this.cibilTransuniunScore = cibilTransuniunScore;
    }


    public Double getNetProfitOrLossFY() {
        return netProfitOrLossFY;
    }

    public void setNetProfitOrLossFY(Double netProfitOrLossFY) {
        this.netProfitOrLossFY = netProfitOrLossFY;
    }

    public Double getNetProfitOrLossSY() {
        return netProfitOrLossSY;
    }

    public void setNetProfitOrLossSY(Double netProfitOrLossSY) {
        this.netProfitOrLossSY = netProfitOrLossSY;
    }

    public Double getNetProfitOrLossTY() {
        return netProfitOrLossTY;
    }

    public void setNetProfitOrLossTY(Double netProfitOrLossTY) {
        this.netProfitOrLossTY = netProfitOrLossTY;
    }

    public Double getDepriciationFy() {
        return depriciationFy;
    }

    public void setDepriciationFy(Double depriciationFy) {
        this.depriciationFy = depriciationFy;
    }

    public Double getDepriciationSy() {
        return depriciationSy;
    }

    public void setDepriciationSy(Double depriciationSy) {
        this.depriciationSy = depriciationSy;
    }

    public Double getDepriciationTy() {
        return depriciationTy;
    }

    public void setDepriciationTy(Double depriciationTy) {
        this.depriciationTy = depriciationTy;
    }

    public Double getInterestFy() {
        return interestFy;
    }

    public void setInterestFy(Double interestFy) {
        this.interestFy = interestFy;
    }

    public Double getInterestSy() {
        return interestSy;
    }

    public void setInterestSy(Double interestSy) {
        this.interestSy = interestSy;
    }

    public Double getInterestTy() {
        return interestTy;
    }

    public void setInterestTy(Double interestTy) {
        this.interestTy = interestTy;
    }

    public Double getTotalSaleFy() {
        return totalSaleFy;
    }

    public void setTotalSaleFy(Double totalSaleFy) {
        this.totalSaleFy = totalSaleFy;
    }

    public Double getTotalSaleSy() {
        return totalSaleSy;
    }

    public void setTotalSaleSy(Double totalSaleSy) {
        this.totalSaleSy = totalSaleSy;
    }

    public Double getTotalSaleTy() {
        return totalSaleTy;
    }

    public void setTotalSaleTy(Double totalSaleTy) {
        this.totalSaleTy = totalSaleTy;
    }

    public Double getProfitBeforeTaxOrLossSy() {
        return profitBeforeTaxOrLossSy;
    }

    public void setProfitBeforeTaxOrLossSy(Double profitBeforeTaxOrLossSy) {
        this.profitBeforeTaxOrLossSy = profitBeforeTaxOrLossSy;
    }

    public Double getProfitBeforeTaxOrLossTy() {
        return profitBeforeTaxOrLossTy;
    }

    public void setProfitBeforeTaxOrLossTy(Double profitBeforeTaxOrLossTy) {
        this.profitBeforeTaxOrLossTy = profitBeforeTaxOrLossTy;
    }


    public Double getOpProfitBeforeInterestSy() {
        return opProfitBeforeInterestSy;
    }

    public void setOpProfitBeforeInterestSy(Double opProfitBeforeInterestSy) {
        this.opProfitBeforeInterestSy = opProfitBeforeInterestSy;
    }

    public Double getOpProfitBeforeInterestTy() {
        return opProfitBeforeInterestTy;
    }

    public void setOpProfitBeforeInterestTy(Double opProfitBeforeInterestTy) {
        this.opProfitBeforeInterestTy = opProfitBeforeInterestTy;
    }

    public Double getNoOfCustomenr() {
        return noOfCustomenr;
    }

    public void setNoOfCustomenr(Double noOfCustomenr) {
        this.noOfCustomenr = noOfCustomenr;
    }

    public Double getConcentrationCustomer() {
        return concentrationCustomer;
    }

    public void setConcentrationCustomer(Double concentrationCustomer) {
        this.concentrationCustomer = concentrationCustomer;
    }

    public Double getExperienceInTheBusiness() {
        return experienceInTheBusiness;
    }

    public void setExperienceInTheBusiness(Double experienceInTheBusiness) {
        this.experienceInTheBusiness = experienceInTheBusiness;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Double getProjectedSale() {
        return projectedSale;
    }

    public void setProjectedSale(Double projectedSale) {
        this.projectedSale = projectedSale;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(Double noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public Long getOwningHouse() {
        return owningHouse;
    }

    public void setOwningHouse(Long owningHouse) {
        this.owningHouse = owningHouse;
    }

    public Long getAcadamicQualification() {
        return acadamicQualification;
    }

    public void setAcadamicQualification(Long acadamicQualification) {
        this.acadamicQualification = acadamicQualification;
    }

    public Double getExperienceInLineOfBusiness() {
        return experienceInLineOfBusiness;
    }

    public void setExperienceInLineOfBusiness(Double experienceInLineOfBusiness) {
        this.experienceInLineOfBusiness = experienceInLineOfBusiness;
    }

    public Long getSpouceDetails() {
        return spouceDetails;
    }

    public void setSpouceDetails(Long spouceDetails) {
        this.spouceDetails = spouceDetails;
    }

    public Long getAssessedFOrIT() {
        return assessedFOrIT;
    }

    public void setAssessedFOrIT(Long assessedFOrIT) {
        this.assessedFOrIT = assessedFOrIT;
    }

    public Long getHaveLIPolicy() {
        return haveLIPolicy;
    }

    public void setHaveLIPolicy(Long haveLIPolicy) {
        this.haveLIPolicy = haveLIPolicy;
    }

    public Double getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(Double repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public Long getKnowHow() {
        return knowHow;
    }

    public void setKnowHow(Long knowHow) {
        this.knowHow = knowHow;
    }

    public Long getLineOfActivity() {
        return lineOfActivity;
    }

    public void setLineOfActivity(Long lineOfActivity) {
        this.lineOfActivity = lineOfActivity;
    }

    public Long getCompetition() {
        return competition;
    }

    public void setCompetition(Long competition) {
        this.competition = competition;
    }

    public Long getFactoryPremises() {
        return factoryPremises;
    }

    public void setFactoryPremises(Long factoryPremises) {
        this.factoryPremises = factoryPremises;
    }

    public Double getGrossSaleTy() {
        return grossSaleTy;
    }

    public void setGrossSaleTy(Double grossSaleTy) {
        this.grossSaleTy = grossSaleTy;
    }

    public Double getTotalCostSaleTy() {
        return totalCostSaleTy;
    }

    public void setTotalCostSaleTy(Double totalCostSaleTy) {
        this.totalCostSaleTy = totalCostSaleTy;
    }

    public Double getFinishedGoodTy() {
        return finishedGoodTy;
    }

    public void setFinishedGoodTy(Double finishedGoodTy) {
        this.finishedGoodTy = finishedGoodTy;
    }

    public Double getGstSaleCurrentYear() {
        return gstSaleCurrentYear;
    }

    public void setGstSaleCurrentYear(Double gstSaleCurrentYear) {
        this.gstSaleCurrentYear = gstSaleCurrentYear;
    }

    public Double getNetSaleFy() {
        return netSaleFy;
    }

    public void setNetSaleFy(Double netSaleFy) {
        this.netSaleFy = netSaleFy;
    }

    public Double getNetSaleSy() {
        return netSaleSy;
    }

    public void setNetSaleSy(Double netSaleSy) {
        this.netSaleSy = netSaleSy;
    }

    public Double getNetSaleTy() {
        return netSaleTy;
    }

    public void setNetSaleTy(Double netSaleTy) {
        this.netSaleTy = netSaleTy;
    }

    public Double getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(Double yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public Double getContinuousNetProfitOrLossFY() {
        return continuousNetProfitOrLossFY;
    }

    public void setContinuousNetProfitOrLossFY(Double continuousNetProfitOrLossFY) {
        this.continuousNetProfitOrLossFY = continuousNetProfitOrLossFY;
    }

    public Double getContinuousNetProfitOrLossSY() {
        return continuousNetProfitOrLossSY;
    }

    public void setContinuousNetProfitOrLossSY(Double continuousNetProfitOrLossSY) {
        this.continuousNetProfitOrLossSY = continuousNetProfitOrLossSY;
    }

    public Double getContinuousNetProfitOrLossTY() {
        return continuousNetProfitOrLossTY;
    }

    public void setContinuousNetProfitOrLossTY(Double continuousNetProfitOrLossTY) {
        this.continuousNetProfitOrLossTY = continuousNetProfitOrLossTY;
    }

    public Boolean getUtilisationPercentage_p() {
        return utilisationPercentage_p;
    }

    public void setUtilisationPercentage_p(Boolean utilisationPercentage_p) {
        this.utilisationPercentage_p = utilisationPercentage_p;
    }

    public Boolean getTurnOverToLimitRatio_p() {
        return turnOverToLimitRatio_p;
    }

    public void setTurnOverToLimitRatio_p(Boolean turnOverToLimitRatio_p) {
        this.turnOverToLimitRatio_p = turnOverToLimitRatio_p;
    }

    public Boolean getCollateralCoverage_p() {
        return collateralCoverage_p;
    }

    public void setCollateralCoverage_p(Boolean collateralCoverage_p) {
        this.collateralCoverage_p = collateralCoverage_p;
    }

    public Double getAverageDailyBalance() {
        return averageDailyBalance;
    }

    public void setAverageDailyBalance(Double averageDailyBalance) {
        this.averageDailyBalance = averageDailyBalance;
    }

    public Double getLimitsInAccount() {
        return limitsInAccount;
    }

    public void setLimitsInAccount(Double limitsInAccount) {
        this.limitsInAccount = limitsInAccount;
    }

    public Double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Double turnOver) {
        this.turnOver = turnOver;
    }

    public Double getEligibleLoanAmountCircular() {
        return eligibleLoanAmountCircular;
    }

    public void setEligibleLoanAmountCircular(Double eligibleLoanAmountCircular) {
        this.eligibleLoanAmountCircular = eligibleLoanAmountCircular;
    }

    public Double getAmountOfCollateral() {
        return amountOfCollateral;
    }

    public void setAmountOfCollateral(Double amountOfCollateral) {
        this.amountOfCollateral = amountOfCollateral;
    }

    public Integer getNoOfMonths() {
        return noOfMonths;
    }

    public void setNoOfMonths(Integer noOfMonths) {
        this.noOfMonths = noOfMonths;
    }

    public Boolean getPastYearTurnover_p() {
        return pastYearTurnover_p;
    }

    public void setPastYearTurnover_p(Boolean pastYearTurnover_p) {
        this.pastYearTurnover_p = pastYearTurnover_p;
    }

    public Boolean getDebtEBITDA_p() {
        return debtEBITDA_p;
    }

    public void setDebtEBITDA_p(Boolean debtEBITDA_p) {
        this.debtEBITDA_p = debtEBITDA_p;
    }

    public Boolean getTurnoverATNW_p() {
        return turnoverATNW_p;
    }

    public void setTurnoverATNW_p(Boolean turnoverATNW_p) {
        this.turnoverATNW_p = turnoverATNW_p;
    }

    public Boolean getChequesBouncedLastMonth_p() {
        return chequesBouncedLastMonth_p;
    }

    public void setChequesBouncedLastMonth_p(Boolean chequesBouncedLastMonth_p) {
        this.chequesBouncedLastMonth_p = chequesBouncedLastMonth_p;
    }

    public Boolean getChequesBouncedLastSixMonth_p() {
        return chequesBouncedLastSixMonth_p;
    }

    public void setChequesBouncedLastSixMonth_p(Boolean chequesBouncedLastSixMonth_p) {
        this.chequesBouncedLastSixMonth_p = chequesBouncedLastSixMonth_p;
    }

    public Double getPastYearTurnover() {
        return pastYearTurnover;
    }

    public void setPastYearTurnover(Double pastYearTurnover) {
        this.pastYearTurnover = pastYearTurnover;
    }


    public Double getLiabilitiesSurplus() {
        return liabilitiesSurplus;
    }

    public void setLiabilitiesSurplus(Double liabilitiesSurplus) {
        this.liabilitiesSurplus = liabilitiesSurplus;
    }


    public Double getNoOfChequesBouncedLastMonth() {
        return noOfChequesBouncedLastMonth;
    }

    public void setNoOfChequesBouncedLastMonth(Double noOfChequesBouncedLastMonth) {
        this.noOfChequesBouncedLastMonth = noOfChequesBouncedLastMonth;
    }

    public Double getDebtFY() {
        return DebtFY;
    }

    public void setDebtFY(Double debtFY) {
        DebtFY = debtFY;
    }

    public Double getDebtSY() {
        return DebtSY;
    }

    public void setDebtSY(Double debtSY) {
        DebtSY = debtSY;
    }

    public Double getDebtTY() {
        return DebtTY;
    }

    public void setDebtTY(Double debtTY) {
        DebtTY = debtTY;
    }

    public Double getEquityFY() {
        return equityFY;
    }

    public void setEquityFY(Double equityFY) {
        this.equityFY = equityFY;
    }

    public Double getEquitySY() {
        return equitySY;
    }

    public void setEquitySY(Double equitySY) {
        this.equitySY = equitySY;
    }

    public Double getEquityTY() {
        return equityTY;
    }

    public void setEquityTY(Double equityTY) {
        this.equityTY = equityTY;
    }

    public Double getTolFY() {
        return tolFY;
    }

    public void setTolFY(Double tolFY) {
        this.tolFY = tolFY;
    }

    public Double getTolSY() {
        return tolSY;
    }

    public void setTolSY(Double tolSY) {
        this.tolSY = tolSY;
    }

    public Double getTolTY() {
        return tolTY;
    }

    public void setTolTY(Double tolTY) {
        this.tolTY = tolTY;
    }

    public Double getTnwFY() {
        return tnwFY;
    }

    public void setTnwFY(Double tnwFY) {
        this.tnwFY = tnwFY;
    }

    public Double getTnwSY() {
        return tnwSY;
    }

    public void setTnwSY(Double tnwSY) {
        this.tnwSY = tnwSY;
    }

    public Double getTnwTY() {
        return tnwTY;
    }

    public void setTnwTY(Double tnwTY) {
        this.tnwTY = tnwTY;
    }

    public Double getAvgCurrentRatioFY() {
        return avgCurrentRatioFY;
    }

    public void setAvgCurrentRatioFY(Double avgCurrentRatioFY) {
        this.avgCurrentRatioFY = avgCurrentRatioFY;
    }

    public Double getAvgCurrentRatioSY() {
        return avgCurrentRatioSY;
    }

    public void setAvgCurrentRatioSY(Double avgCurrentRatioSY) {
        this.avgCurrentRatioSY = avgCurrentRatioSY;
    }

    public Double getAvgCurrentRatioTY() {
        return avgCurrentRatioTY;
    }

    public void setAvgCurrentRatioTY(Double avgCurrentRatioTY) {
        this.avgCurrentRatioTY = avgCurrentRatioTY;
    }

    public Double getEbitdaFY() {
        return ebitdaFY;
    }

    public void setEbitdaFY(Double ebitdaFY) {
        this.ebitdaFY = ebitdaFY;
    }

    public Double getEbitdaSY() {
        return ebitdaSY;
    }

    public void setEbitdaSY(Double ebitdaSY) {
        this.ebitdaSY = ebitdaSY;
    }

    public Double getEbitdaTY() {
        return ebitdaTY;
    }

    public void setEbitdaTY(Double ebitdaTY) {
        this.ebitdaTY = ebitdaTY;
    }

    public Double getDebtorsDaysFY() {
        return debtorsDaysFY;
    }

    public void setDebtorsDaysFY(Double debtorsDaysFY) {
        this.debtorsDaysFY = debtorsDaysFY;
    }

    public Double getDebtorsDaysSY() {
        return debtorsDaysSY;
    }

    public void setDebtorsDaysSY(Double debtorsDaysSY) {
        this.debtorsDaysSY = debtorsDaysSY;
    }

    public Double getDebtorsDaysTY() {
        return debtorsDaysTY;
    }

    public void setDebtorsDaysTY(Double debtorsDaysTY) {
        this.debtorsDaysTY = debtorsDaysTY;
    }

    public Double getAvgInventoryFY() {
        return avgInventoryFY;
    }

    public void setAvgInventoryFY(Double avgInventoryFY) {
        this.avgInventoryFY = avgInventoryFY;
    }

    public Double getAvgInventorySY() {
        return avgInventorySY;
    }

    public void setAvgInventorySY(Double avgInventorySY) {
        this.avgInventorySY = avgInventorySY;
    }

    public Double getAvgInventoryTY() {
        return avgInventoryTY;
    }

    public void setAvgInventoryTY(Double avgInventoryTY) {
        this.avgInventoryTY = avgInventoryTY;
    }

    public Double getCogsFY() {
        return cogsFY;
    }

    public void setCogsFY(Double cogsFY) {
        this.cogsFY = cogsFY;
    }

    public Double getCogsSY() {
        return cogsSY;
    }

    public void setCogsSY(Double cogsSY) {
        this.cogsSY = cogsSY;
    }

    public Double getCogsTY() {
        return cogsTY;
    }

    public void setCogsTY(Double cogsTY) {
        this.cogsTY = cogsTY;
    }

    public Double getCreditorsDaysFY() {
        return creditorsDaysFY;
    }

    public void setCreditorsDaysFY(Double creditorsDaysFY) {
        this.creditorsDaysFY = creditorsDaysFY;
    }

    public Double getCreditorsDaysSY() {
        return creditorsDaysSY;
    }

    public void setCreditorsDaysSY(Double creditorsDaysSY) {
        this.creditorsDaysSY = creditorsDaysSY;
    }

    public Double getCreditorsDaysTY() {
        return creditorsDaysTY;
    }

    public void setCreditorsDaysTY(Double creditorsDaysTY) {
        this.creditorsDaysTY = creditorsDaysTY;
    }

    public Double getTermLoanFy() {
        return termLoanFy;
    }

    public void setTermLoanFy(Double termLoanFy) {
        this.termLoanFy = termLoanFy;
    }

    public Double getTermLoanSy() {
        return termLoanSy;
    }

    public void setTermLoanSy(Double termLoanSy) {
        this.termLoanSy = termLoanSy;
    }

    public Double getOpProfitBeforeInterestFy() {
        return opProfitBeforeInterestFy;
    }

    public void setOpProfitBeforeInterestFy(Double opProfitBeforeInterestFy) {
        this.opProfitBeforeInterestFy = opProfitBeforeInterestFy;
    }

    public Double getTotalTermLiabilitiesFY() {
        return totalTermLiabilitiesFY;
    }

    public void setTotalTermLiabilitiesFY(Double totalTermLiabilitiesFY) {
        this.totalTermLiabilitiesFY = totalTermLiabilitiesFY;
    }

    public Double getTotalTermLiabilitiesSY() {
        return totalTermLiabilitiesSY;
    }

    public void setTotalTermLiabilitiesSY(Double totalTermLiabilitiesSY) {
        this.totalTermLiabilitiesSY = totalTermLiabilitiesSY;
    }

    public Double getTotalTermLiabilitiesTY() {
        return totalTermLiabilitiesTY;
    }

    public void setTotalTermLiabilitiesTY(Double totalTermLiabilitiesTY) {
        this.totalTermLiabilitiesTY = totalTermLiabilitiesTY;
    }

    public Double getPreferenceSharesFY() {
        return preferenceSharesFY;
    }

    public void setPreferenceSharesFY(Double preferenceSharesFY) {
        this.preferenceSharesFY = preferenceSharesFY;
    }

    public Double getPreferenceSharesSY() {
        return preferenceSharesSY;
    }

    public void setPreferenceSharesSY(Double preferenceSharesSY) {
        this.preferenceSharesSY = preferenceSharesSY;
    }

    public Double getPreferenceSharesTY() {
        return preferenceSharesTY;
    }

    public void setPreferenceSharesTY(Double preferenceSharesTY) {
        this.preferenceSharesTY = preferenceSharesTY;
    }

    public Double getOthersFY() {
        return othersFY;
    }

    public void setOthersFY(Double othersFY) {
        this.othersFY = othersFY;
    }

    public Double getOthersSY() {
        return othersSY;
    }

    public void setOthersSY(Double othersSY) {
        this.othersSY = othersSY;
    }

    public Double getOthersTY() {
        return othersTY;
    }

    public void setOthersTY(Double othersTY) {
        this.othersTY = othersTY;
    }

    public Double getMinorityInterestFY() {
        return minorityInterestFY;
    }

    public void setMinorityInterestFY(Double minorityInterestFY) {
        this.minorityInterestFY = minorityInterestFY;
    }

    public Double getMinorityInterestSY() {
        return minorityInterestSY;
    }

    public void setMinorityInterestSY(Double minorityInterestSY) {
        this.minorityInterestSY = minorityInterestSY;
    }

    public Double getMinorityInterestTY() {
        return minorityInterestTY;
    }

    public void setMinorityInterestTY(Double minorityInterestTY) {
        this.minorityInterestTY = minorityInterestTY;
    }

    public Double getDeferredTaxLiabilityFY() {
        return deferredTaxLiabilityFY;
    }

    public void setDeferredTaxLiabilityFY(Double deferredTaxLiabilityFY) {
        this.deferredTaxLiabilityFY = deferredTaxLiabilityFY;
    }

    public Double getDeferredTaxLiabilitySY() {
        return deferredTaxLiabilitySY;
    }

    public void setDeferredTaxLiabilitySY(Double deferredTaxLiabilitySY) {
        this.deferredTaxLiabilitySY = deferredTaxLiabilitySY;
    }

    public Double getDeferredTaxLiabilityTY() {
        return deferredTaxLiabilityTY;
    }

    public void setDeferredTaxLiabilityTY(Double deferredTaxLiabilityTY) {
        this.deferredTaxLiabilityTY = deferredTaxLiabilityTY;
    }

    public Double getDeferredTaxAssetsFY() {
        return deferredTaxAssetsFY;
    }

    public void setDeferredTaxAssetsFY(Double deferredTaxAssetsFY) {
        this.deferredTaxAssetsFY = deferredTaxAssetsFY;
    }

    public Double getDeferredTaxAssetsSY() {
        return deferredTaxAssetsSY;
    }

    public void setDeferredTaxAssetsSY(Double deferredTaxAssetsSY) {
        this.deferredTaxAssetsSY = deferredTaxAssetsSY;
    }

    public Double getDeferredTaxAssetsTY() {
        return deferredTaxAssetsTY;
    }

    public void setDeferredTaxAssetsTY(Double deferredTaxAssetsTY) {
        this.deferredTaxAssetsTY = deferredTaxAssetsTY;
    }

    public Double getProfitBeforeInterestFY() {
        return profitBeforeInterestFY;
    }

    public void setProfitBeforeInterestFY(Double profitBeforeInterestFY) {
        this.profitBeforeInterestFY = profitBeforeInterestFY;
    }

    public Double getProfitBeforeInterestSY() {
        return profitBeforeInterestSY;
    }

    public void setProfitBeforeInterestSY(Double profitBeforeInterestSY) {
        this.profitBeforeInterestSY = profitBeforeInterestSY;
    }

    public Double getProfitBeforeInterestTY() {
        return profitBeforeInterestTY;
    }

    public void setProfitBeforeInterestTY(Double profitBeforeInterestTY) {
        this.profitBeforeInterestTY = profitBeforeInterestTY;
    }

    public Double getDepreciationFY() {
        return depreciationFY;
    }

    public void setDepreciationFY(Double depreciationFY) {
        this.depreciationFY = depreciationFY;
    }

    public Double getDepreciationSY() {
        return depreciationSY;
    }

    public void setDepreciationSY(Double depreciationSY) {
        this.depreciationSY = depreciationSY;
    }

    public Double getDepreciationTY() {
        return depreciationTY;
    }

    public void setDepreciationTY(Double depreciationTY) {
        this.depreciationTY = depreciationTY;
    }

    public Double getLiabilitiesUnsecuredLoansFromOthersFY() {
        return liabilitiesUnsecuredLoansFromOthersFY;
    }

    public void setLiabilitiesUnsecuredLoansFromOthersFY(Double liabilitiesUnsecuredLoansFromOthersFY) {
        this.liabilitiesUnsecuredLoansFromOthersFY = liabilitiesUnsecuredLoansFromOthersFY;
    }

    public Double getLiabilitiesUnsecuredLoansFromOthersSY() {
        return liabilitiesUnsecuredLoansFromOthersSY;
    }

    public void setLiabilitiesUnsecuredLoansFromOthersSY(Double liabilitiesUnsecuredLoansFromOthersSY) {
        this.liabilitiesUnsecuredLoansFromOthersSY = liabilitiesUnsecuredLoansFromOthersSY;
    }

    public Double getLiabilitiesUnsecuredLoansFromOthersTY() {
        return liabilitiesUnsecuredLoansFromOthersTY;
    }

    public void setLiabilitiesUnsecuredLoansFromOthersTY(Double liabilitiesUnsecuredLoansFromOthersTY) {
        this.liabilitiesUnsecuredLoansFromOthersTY = liabilitiesUnsecuredLoansFromOthersTY;
    }

    public Double getExportSalesFY() {
        return exportSalesFY;
    }

    public void setExportSalesFY(Double exportSalesFY) {
        this.exportSalesFY = exportSalesFY;
    }

    public Double getExportSalesSY() {
        return exportSalesSY;
    }

    public void setExportSalesSY(Double exportSalesSY) {
        this.exportSalesSY = exportSalesSY;
    }

    public Double getExportSalesTY() {
        return exportSalesTY;
    }

    public void setExportSalesTY(Double exportSalesTY) {
        this.exportSalesTY = exportSalesTY;
    }

    public Double getDomesticSalesFY() {
        return domesticSalesFY;
    }

    public void setDomesticSalesFY(Double domesticSalesFY) {
        this.domesticSalesFY = domesticSalesFY;
    }

    public Double getDomesticSalesSY() {
        return domesticSalesSY;
    }

    public void setDomesticSalesSY(Double domesticSalesSY) {
        this.domesticSalesSY = domesticSalesSY;
    }

    public Double getDomesticSalesTY() {
        return domesticSalesTY;
    }

    public void setDomesticSalesTY(Double domesticSalesTY) {
        this.domesticSalesTY = domesticSalesTY;
    }

    public Double getLiabilitiesOrdinaryShareCapitalFY() {
        return liabilitiesOrdinaryShareCapitalFY;
    }

    public void setLiabilitiesOrdinaryShareCapitalFY(Double liabilitiesOrdinaryShareCapitalFY) {
        this.liabilitiesOrdinaryShareCapitalFY = liabilitiesOrdinaryShareCapitalFY;
    }

    public Double getLiabilitiesOrdinaryShareCapitalSY() {
        return liabilitiesOrdinaryShareCapitalSY;
    }

    public void setLiabilitiesOrdinaryShareCapitalSY(Double liabilitiesOrdinaryShareCapitalSY) {
        this.liabilitiesOrdinaryShareCapitalSY = liabilitiesOrdinaryShareCapitalSY;
    }

    public Double getLiabilitiesOrdinaryShareCapitalTY() {
        return liabilitiesOrdinaryShareCapitalTY;
    }

    public void setLiabilitiesOrdinaryShareCapitalTY(Double liabilitiesOrdinaryShareCapitalTY) {
        this.liabilitiesOrdinaryShareCapitalTY = liabilitiesOrdinaryShareCapitalTY;
    }

    public Double getLiabilitiesGeneralReserveFY() {
        return liabilitiesGeneralReserveFY;
    }

    public void setLiabilitiesGeneralReserveFY(Double liabilitiesGeneralReserveFY) {
        this.liabilitiesGeneralReserveFY = liabilitiesGeneralReserveFY;
    }

    public Double getLiabilitiesGeneralReserveSY() {
        return liabilitiesGeneralReserveSY;
    }

    public void setLiabilitiesGeneralReserveSY(Double liabilitiesGeneralReserveSY) {
        this.liabilitiesGeneralReserveSY = liabilitiesGeneralReserveSY;
    }

    public Double getLiabilitiesGeneralReserveTY() {
        return liabilitiesGeneralReserveTY;
    }

    public void setLiabilitiesGeneralReserveTY(Double liabilitiesGeneralReserveTY) {
        this.liabilitiesGeneralReserveTY = liabilitiesGeneralReserveTY;
    }

    public Double getDeficitInProfitANDLossAccountFY() {
        return deficitInProfitANDLossAccountFY;
    }

    public void setDeficitInProfitANDLossAccountFY(Double deficitInProfitANDLossAccountFY) {
        this.deficitInProfitANDLossAccountFY = deficitInProfitANDLossAccountFY;
    }

    public Double getDeficitInProfitANDLossAccountSY() {
        return deficitInProfitANDLossAccountSY;
    }

    public void setDeficitInProfitANDLossAccountSY(Double deficitInProfitANDLossAccountSY) {
        this.deficitInProfitANDLossAccountSY = deficitInProfitANDLossAccountSY;
    }

    public Double getDeficitInProfitANDLossAccountTY() {
        return deficitInProfitANDLossAccountTY;
    }

    public void setDeficitInProfitANDLossAccountTY(Double deficitInProfitANDLossAccountTY) {
        this.deficitInProfitANDLossAccountTY = deficitInProfitANDLossAccountTY;
    }

    public Double getLiabilitiesUnsecuredLoansFromPpromotersFY() {
        return liabilitiesUnsecuredLoansFromPpromotersFY;
    }

    public void setLiabilitiesUnsecuredLoansFromPpromotersFY(Double liabilitiesUnsecuredLoansFromPpromotersFY) {
        this.liabilitiesUnsecuredLoansFromPpromotersFY = liabilitiesUnsecuredLoansFromPpromotersFY;
    }

    public Double getLiabilitiesUnsecuredLoansFromPpromotersSY() {
        return liabilitiesUnsecuredLoansFromPpromotersSY;
    }

    public void setLiabilitiesUnsecuredLoansFromPpromotersSY(Double liabilitiesUnsecuredLoansFromPpromotersSY) {
        this.liabilitiesUnsecuredLoansFromPpromotersSY = liabilitiesUnsecuredLoansFromPpromotersSY;
    }

    public Double getLiabilitiesUnsecuredLoansFromPpromotersTY() {
        return liabilitiesUnsecuredLoansFromPpromotersTY;
    }

    public void setLiabilitiesUnsecuredLoansFromPpromotersTY(Double liabilitiesUnsecuredLoansFromPpromotersTY) {
        this.liabilitiesUnsecuredLoansFromPpromotersTY = liabilitiesUnsecuredLoansFromPpromotersTY;
    }

    public Double getAssetsInvestmentsInSubsidiaryCosaffiliatesFY() {
        return assetsInvestmentsInSubsidiaryCosaffiliatesFY;
    }

    public void setAssetsInvestmentsInSubsidiaryCosaffiliatesFY(Double assetsInvestmentsInSubsidiaryCosaffiliatesFY) {
        this.assetsInvestmentsInSubsidiaryCosaffiliatesFY = assetsInvestmentsInSubsidiaryCosaffiliatesFY;
    }

    public Double getAssetsInvestmentsInSubsidiaryCosaffiliatesSY() {
        return assetsInvestmentsInSubsidiaryCosaffiliatesSY;
    }

    public void setAssetsInvestmentsInSubsidiaryCosaffiliatesSY(Double assetsInvestmentsInSubsidiaryCosaffiliatesSY) {
        this.assetsInvestmentsInSubsidiaryCosaffiliatesSY = assetsInvestmentsInSubsidiaryCosaffiliatesSY;
    }

    public Double getAssetsInvestmentsInSubsidiaryCosaffiliatesTY() {
        return assetsInvestmentsInSubsidiaryCosaffiliatesTY;
    }

    public void setAssetsInvestmentsInSubsidiaryCosaffiliatesTY(Double assetsInvestmentsInSubsidiaryCosaffiliatesTY) {
        this.assetsInvestmentsInSubsidiaryCosaffiliatesTY = assetsInvestmentsInSubsidiaryCosaffiliatesTY;
    }

    public Double getTotalAssetFy() {
        return totalAssetFy;
    }

    public void setTotalAssetFy(Double totalAssetFy) {
        this.totalAssetFy = totalAssetFy;
    }

    public Double getTotalAssetSy() {
        return totalAssetSy;
    }

    public void setTotalAssetSy(Double totalAssetSy) {
        this.totalAssetSy = totalAssetSy;
    }

    public Double getTotalAssetTy() {
        return totalAssetTy;
    }

    public void setTotalAssetTy(Double totalAssetTy) {
        this.totalAssetTy = totalAssetTy;
    }

    public Double getProfitBeforeTaxOrLossFy() {
        return profitBeforeTaxOrLossFy;
    }

    public void setProfitBeforeTaxOrLossFy(Double profitBeforeTaxOrLossFy) {
        this.profitBeforeTaxOrLossFy = profitBeforeTaxOrLossFy;
    }

    public Double getUnsecuredLoansFromOthersFY() {
        return unsecuredLoansFromOthersFY;
    }

    public void setUnsecuredLoansFromOthersFY(Double unsecuredLoansFromOthersFY) {
        this.unsecuredLoansFromOthersFY = unsecuredLoansFromOthersFY;
    }

    public Double getUnsecuredLoansFromOthersSY() {
        return unsecuredLoansFromOthersSY;
    }

    public void setUnsecuredLoansFromOthersSY(Double unsecuredLoansFromOthersSY) {
        this.unsecuredLoansFromOthersSY = unsecuredLoansFromOthersSY;
    }

    public Double getUnsecuredLoansFromOthersTY() {
        return unsecuredLoansFromOthersTY;
    }

    public void setUnsecuredLoansFromOthersTY(Double unsecuredLoansFromOthersTY) {
        this.unsecuredLoansFromOthersTY = unsecuredLoansFromOthersTY;
    }


    public boolean isPatNetSalesRatio_p() {
        return patNetSalesRatio_p;
    }

    public boolean isPaymentRecordsWithLenders_p() {
        return paymentRecordsWithLenders_p;
    }

    public Double getOtherRevenueIncomeFY() {
        return otherRevenueIncomeFY;
    }

    public void setOtherRevenueIncomeFY(Double otherRevenueIncomeFY) {
        this.otherRevenueIncomeFY = otherRevenueIncomeFY;
    }

    public Double getOtherRevenueIncomeSY() {
        return otherRevenueIncomeSY;
    }

    public void setOtherRevenueIncomeSY(Double otherRevenueIncomeSY) {
        this.otherRevenueIncomeSY = otherRevenueIncomeSY;
    }

    public Double getOtherRevenueIncomeTY() {
        return otherRevenueIncomeTY;
    }

    public void setOtherRevenueIncomeTY(Double otherRevenueIncomeTY) {
        this.otherRevenueIncomeTY = otherRevenueIncomeTY;
    }

    public Integer getStatutoryComplianceType() {
        return statutoryComplianceType;
    }

    public void setStatutoryComplianceType(Integer statutoryComplianceType) {
        this.statutoryComplianceType = statutoryComplianceType;
    }

    public Integer getDpd() {
        return dpd;
    }

    public void setDpd(Integer dpd) {
        this.dpd = dpd;
    }

    public Double getAssertClassification() {
        return assertClassification;
    }

    public void setAssertClassification(Double assertClassification) {
        this.assertClassification = assertClassification;
    }

    public boolean getPatNetSalesRatio_p() {
        return patNetSalesRatio_p;
    }

    public void setPatNetSalesRatio_p(boolean patNetSalesRatio_p) {
        this.patNetSalesRatio_p = patNetSalesRatio_p;
    }

    public boolean getStatutoryCompliance_p() {
        return statutoryCompliance_p;
    }

    public void setStatutoryCompliance_p(boolean statutoryCompliance_p) {
        this.statutoryCompliance_p = statutoryCompliance_p;
    }

    public boolean getPaymentRecordsWithLenders_p() {
        return paymentRecordsWithLenders_p;
    }

    public void setPaymentRecordsWithLenders_p(boolean paymentRecordsWithLenders_p) {
        this.paymentRecordsWithLenders_p = paymentRecordsWithLenders_p;
    }

    public Integer getItyYearType() {
        return ityYearType;
    }

    public void setItyYearType(Integer ityYearType) {
        this.ityYearType = ityYearType;
    }

	/**
	 * @return the cmrScoreMsmeRanking_p
	 */
	public Boolean getCmrScoreMsmeRanking_p() {
		return cmrScoreMsmeRanking_p;
	}

	/**
	 * @return the cmrScoreMsmeRanking
	 */
	public Double getCmrScoreMsmeRanking() {
		return cmrScoreMsmeRanking;
	}

	/**
	 * @return the isoCertification_p
	 */
	public Boolean getIsoCertification_p() {
		return isoCertification_p;
	}

	/**
	 * @return the isoCertificationVal
	 */
	public Boolean getIsoCertificationVal() {
		return isoCertificationVal;
	}

	/**
	 * @return the totalNoOfChequeBounceLastSixMonths_p
	 */
	public Boolean getTotalNoOfChequeBounceLastSixMonths_p() {
		return totalNoOfChequeBounceLastSixMonths_p;
	}

	/**
	 * @return the totalNoOfChequeBounceLastSixMonths
	 */
	public Double getTotalNoOfChequeBounceLastSixMonths() {
		return totalNoOfChequeBounceLastSixMonths;
	}

	/**
	 * @param cmrScoreMsmeRanking_p the cmrScoreMsmeRanking_p to set
	 */
	public void setCmrScoreMsmeRanking_p(Boolean cmrScoreMsmeRanking_p) {
		this.cmrScoreMsmeRanking_p = cmrScoreMsmeRanking_p;
	}

	/**
	 * @param cmrScoreMsmeRanking the cmrScoreMsmeRanking to set
	 */
	public void setCmrScoreMsmeRanking(Double cmrScoreMsmeRanking) {
		this.cmrScoreMsmeRanking = cmrScoreMsmeRanking;
	}

	/**
	 * @param isoCertification_p the isoCertification_p to set
	 */
	public void setIsoCertification_p(Boolean isoCertification_p) {
		this.isoCertification_p = isoCertification_p;
	}

	/**
	 * @param isoCertificationVal the isoCertificationVal to set
	 */
	public void setIsoCertificationVal(Boolean isoCertificationVal) {
		this.isoCertificationVal = isoCertificationVal;
	}

	/**
	 * @param totalNoOfChequeBounceLastSixMonths_p the totalNoOfChequeBounceLastSixMonths_p to set
	 */
	public void setTotalNoOfChequeBounceLastSixMonths_p(Boolean totalNoOfChequeBounceLastSixMonths_p) {
		this.totalNoOfChequeBounceLastSixMonths_p = totalNoOfChequeBounceLastSixMonths_p;
	}

	/**
	 * @param totalNoOfChequeBounceLastSixMonths the totalNoOfChequeBounceLastSixMonths to set
	 */
	public void setTotalNoOfChequeBounceLastSixMonths(Double totalNoOfChequeBounceLastSixMonths) {
		this.totalNoOfChequeBounceLastSixMonths = totalNoOfChequeBounceLastSixMonths;
	}
	

	public Integer getItReturnFiledId() {
		return itReturnFiledId;
	}

	public void setItReturnFiledId(Integer itReturnFiledId) {
		this.itReturnFiledId = itReturnFiledId;
	}
	
	public Double getCreditSummation() {
		return creditSummation;
	}

	public void setCreditSummation(Double creditSummation) {
		this.creditSummation = creditSummation;
	}

	public Integer getDistanceBtwWorkAndRes() {
		return distanceBtwWorkAndRes;
	}

	public void setDistanceBtwWorkAndRes(Integer distanceBtwWorkAndRes) {
		this.distanceBtwWorkAndRes = distanceBtwWorkAndRes;
	}

	public Boolean getDistanceBtwWorkAndRes_p() {
		return distanceBtwWorkAndRes_p;
	}

	public void setDistanceBtwWorkAndRes_p(Boolean distanceBtwWorkAndRes_p) {
		this.distanceBtwWorkAndRes_p = distanceBtwWorkAndRes_p;
	}

	public Integer getEmploymentGeneration() {
		return employmentGeneration;
	}

	public void setEmploymentGeneration(Integer employmentGeneration) {
		this.employmentGeneration = employmentGeneration;
	}

	public Boolean getEmploymentGeneration_p() {
		return employmentGeneration_p;
	}

	public void setEmploymentGeneration_p(Boolean employmentGeneration_p) {
		this.employmentGeneration_p = employmentGeneration_p;
	}

	public Double getPromotersComtributionML() {
		return promotersComtributionML;
	}

	public void setPromotersComtributionML(Double promotersComtributionML) {
		this.promotersComtributionML = promotersComtributionML;
	}

	public Boolean getPromotersComtributionML_p() {
		return promotersComtributionML_p;
	}

	public void setPromotersComtributionML_p(Boolean promotersComtributionML_p) {
		this.promotersComtributionML_p = promotersComtributionML_p;
	}

	public Boolean getRegistrationWithGovernmentAuthoritiesCombined_p() {
		return registrationWithGovernmentAuthoritiesCombined_p;
	}

	public void setRegistrationWithGovernmentAuthoritiesCombined_p(
			Boolean registrationWithGovernmentAuthoritiesCombined_p) {
		this.registrationWithGovernmentAuthoritiesCombined_p = registrationWithGovernmentAuthoritiesCombined_p;
	}
	

	public Integer getRegistrationWithGovernmentAuthoritiesCombined() {
		return registrationWithGovernmentAuthoritiesCombined;
	}

	public void setRegistrationWithGovernmentAuthoritiesCombined(Integer registrationWithGovernmentAuthoritiesCombined) {
		this.registrationWithGovernmentAuthoritiesCombined = registrationWithGovernmentAuthoritiesCombined;
	}

	public Double getTenureFs() {
		return tenureFs;
	}

	public void setTenureFs(Double tenureFs) {
		this.tenureFs = tenureFs;
	}

	public Integer getBankRelationCombined() {
		return bankRelationCombined;
	}

	public void setBankRelationCombined(Integer bankRelationCombined) {
		this.bankRelationCombined = bankRelationCombined;
	}

	public Boolean getBankRelationCombined_p() {
		return bankRelationCombined_p;
	}

	public void setBankRelationCombined_p(Boolean bankRelationCombined_p) {
		this.bankRelationCombined_p = bankRelationCombined_p;
	}

    public Double getExperienceInBusinessVal() {
        return experienceInBusinessVal;
    }

    public void setExperienceInBusinessVal(Double experienceInBusinessVal) {
        this.experienceInBusinessVal = experienceInBusinessVal;
    }

    public Boolean getExperienceInBusiness_p() {
        return experienceInBusiness_p;
    }

    public void setExperienceInBusiness_p(Boolean experienceInBusiness_p) {
        this.experienceInBusiness_p = experienceInBusiness_p;
    }

    public Boolean getFleetStrengthOwnedbyFleetOperator_p() {
        return fleetStrengthOwnedbyFleetOperator_p;
    }

    public void setFleetStrengthOwnedbyFleetOperator_p(Boolean fleetStrengthOwnedbyFleetOperator_p) {
        this.fleetStrengthOwnedbyFleetOperator_p = fleetStrengthOwnedbyFleetOperator_p;
    }

    public Integer getNoOfVehicles() {
        return noOfVehicles;
    }

    public void setNoOfVehicles(Integer noOfVehicles) {
        this.noOfVehicles = noOfVehicles;
    }

    public Boolean getAssuredOrder_p() {
        return assuredOrder_p;
    }

    public void setAssuredOrder_p(Boolean assuredOrder_p) {
        this.assuredOrder_p = assuredOrder_p;
    }

    public Boolean getAssuredOrderVal() {
        return assuredOrderVal;
    }

    public void setAssuredOrderVal(Boolean assuredOrderVal) {
        this.assuredOrderVal = assuredOrderVal;
    }

    public Boolean getFamilyMembersDirectlyVal_p() {
        return familyMembersDirectlyVal_p;
    }

    public void setFamilyMembersDirectlyVal_p(Boolean familyMembersDirectlyVal_p) {
        this.familyMembersDirectlyVal_p = familyMembersDirectlyVal_p;
    }

    public Boolean getFamilyMembersDirectlyVal() {
        return familyMembersDirectlyVal;
    }

    public void setFamilyMembersDirectlyVal(Boolean familyMembersDirectlyVal) {
        this.familyMembersDirectlyVal = familyMembersDirectlyVal;
    }

    public Boolean getLoanFreeVehicle_p() {
        return loanFreeVehicle_p;
    }

    public void setLoanFreeVehicle_p(Boolean loanFreeVehicle_p) {
        this.loanFreeVehicle_p = loanFreeVehicle_p;
    }

    public Double getTotalSanctionVal() {
        return totalSanctionVal;
    }

    public void setTotalSanctionVal(Double totalSanctionVal) {
        this.totalSanctionVal = totalSanctionVal;
    }

    public Boolean getDepositPositionPotential_p() {
        return depositPositionPotential_p;
    }

    public void setDepositPositionPotential_p(Boolean depositPositionPotential_p) {
        this.depositPositionPotential_p = depositPositionPotential_p;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Integer getFullMonthCount() {
        return fullMonthCount;
    }

    public void setFullMonthCount(Integer fullMonthCount) {
        this.fullMonthCount = fullMonthCount;
    }

    public Integer getProfitabilityHistoryVal() {
        return profitabilityHistoryVal;
    }

    public void setProfitabilityHistoryVal(Integer profitabilityHistoryVal) {
        this.profitabilityHistoryVal = profitabilityHistoryVal;
    }

    public Boolean getProfitabilityHistory_p() {
        return profitabilityHistory_p;
    }

    public void setProfitabilityHistory_p(Boolean profitabilityHistory_p) {
        this.profitabilityHistory_p = profitabilityHistory_p;
    }

    public Boolean getCollateralSecurity_p() {
        return collateralSecurity_p;
    }

    public void setCollateralSecurity_p(Boolean collateralSecurity_p) {
        this.collateralSecurity_p = collateralSecurity_p;
    }

    public Double getConservativeDebtServiceCoverageVal() {
        return conservativeDebtServiceCoverageVal;
    }

    public void setConservativeDebtServiceCoverageVal(Double conservativeDebtServiceCoverageVal) {
        this.conservativeDebtServiceCoverageVal = conservativeDebtServiceCoverageVal;
    }

    public Boolean getConservativeDebtServiceCoverage_p() {
        return conservativeDebtServiceCoverage_p;
    }

    public void setConservativeDebtServiceCoverage_p(Boolean conservativeDebtServiceCoverage_p) {
        this.conservativeDebtServiceCoverage_p = conservativeDebtServiceCoverage_p;
    }

    @Override
    public String toString() {
        return "ScoringParameterRequest{" +
                "combinedNetworth_p=" + combinedNetworth_p +
                ", customerAsscociateConcern_p=" + customerAsscociateConcern_p +
                ", cibilTransunionScore_p=" + cibilTransunionScore_p +
                ", debtEquityRatio_p=" + debtEquityRatio_p +
                ", tolTnw_p=" + tolTnw_p +
                ", avgCurrentRatio_p=" + avgCurrentRatio_p +
                ", workingCapitalCycle_p=" + workingCapitalCycle_p +
                ", avgAnnualGrowthGrossCash_p=" + avgAnnualGrowthGrossCash_p +
                ", avgAnnualGrowthNetSale_p=" + avgAnnualGrowthNetSale_p +
                ", avgEBIDTA_p=" + avgEBIDTA_p +
                ", avgAnnualGrossCashAccuruals_p=" + avgAnnualGrossCashAccuruals_p +
                ", avgInterestCovRatio_p=" + avgInterestCovRatio_p +
                ", noOfCustomer_p=" + noOfCustomer_p +
                ", concentrationCustomer_p=" + concentrationCustomer_p +
                ", experienceInTheBusiness_p=" + experienceInTheBusiness_p +
                ", creditSummation_p=" + creditSummation_p +
                ", age_p=" + age_p +
                ", noOfChildren_p=" + noOfChildren_p +
                ", owningHouse_p=" + owningHouse_p +
                ", acadamicQualification_p=" + acadamicQualification_p +
                ", expLineOfTrade_p=" + expLineOfTrade_p +
                ", spouseDetails_p=" + spouseDetails_p +
                ", assessedForIncomeTax_p=" + assessedForIncomeTax_p +
                ", haveLifeIncPolicy_p=" + haveLifeIncPolicy_p +
                ", yearsInBusiness_p=" + yearsInBusiness_p +
                ", repaymentPeriod_p=" + repaymentPeriod_p +
                ", continousNetProfit_p=" + continousNetProfit_p +
                ", qualityOfReceivable_p=" + qualityOfReceivable_p +
                ", qualityOfFinishedGood_p=" + qualityOfFinishedGood_p +
                ", knowHow_p=" + knowHow_p +
                ", lineOfActivity_p=" + lineOfActivity_p +
                ", competition_p=" + competition_p +
                ", factoryPremises_p=" + factoryPremises_p +
                ", salesShowArisingTrend_p=" + salesShowArisingTrend_p +
                ", utilisationPercentage_p=" + utilisationPercentage_p +
                ", turnOverToLimitRatio_p=" + turnOverToLimitRatio_p +
                ", collateralCoverage_p=" + collateralCoverage_p +
                ", debtServiceCoverageRatio_p=" + debtServiceCoverageRatio_p +
                ", pastYearTurnover_p=" + pastYearTurnover_p +
                ", debtEBITDA_p=" + debtEBITDA_p +
                ", turnoverATNW_p=" + turnoverATNW_p +
                ", chequesBouncedLastMonth_p=" + chequesBouncedLastMonth_p +
                ", chequesBouncedLastSixMonth_p=" + chequesBouncedLastSixMonth_p +
                ", patNetSalesRatio_p=" + patNetSalesRatio_p +
                ", statutoryCompliance_p=" + statutoryCompliance_p +
                ", paymentRecordsWithLenders_p=" + paymentRecordsWithLenders_p +
                ", cmrScoreMsmeRanking_p=" + cmrScoreMsmeRanking_p +
                ", cmrScoreMsmeRanking=" + cmrScoreMsmeRanking +
                ", isoCertification_p=" + isoCertification_p +
                ", isoCertificationVal=" + isoCertificationVal +
                ", totalNoOfChequeBounceLastSixMonths_p=" + totalNoOfChequeBounceLastSixMonths_p +
                ", totalNoOfChequeBounceLastSixMonths=" + totalNoOfChequeBounceLastSixMonths +
                ", otherRevenueIncomeFY=" + otherRevenueIncomeFY +
                ", otherRevenueIncomeSY=" + otherRevenueIncomeSY +
                ", otherRevenueIncomeTY=" + otherRevenueIncomeTY +
                ", statutoryComplianceType=" + statutoryComplianceType +
                ", dpd=" + dpd +
                ", ityYearType=" + ityYearType +
                ", assertClassification=" + assertClassification +
                ", networthSum=" + networthSum +
                ", termLoanFy=" + termLoanFy +
                ", termLoanSy=" + termLoanSy +
                ", termLoanTy=" + termLoanTy +
                ", loanAmount=" + loanAmount +
                ", customerAssociateConcern=" + customerAssociateConcern +
                ", cibilTransuniunScore=" + cibilTransuniunScore +
                ", DebtFY=" + DebtFY +
                ", DebtSY=" + DebtSY +
                ", DebtTY=" + DebtTY +
                ", equityFY=" + equityFY +
                ", equitySY=" + equitySY +
                ", equityTY=" + equityTY +
                ", tolFY=" + tolFY +
                ", tolSY=" + tolSY +
                ", tolTY=" + tolTY +
                ", tnwFY=" + tnwFY +
                ", tnwSY=" + tnwSY +
                ", tnwTY=" + tnwTY +
                ", avgCurrentRatioFY=" + avgCurrentRatioFY +
                ", avgCurrentRatioSY=" + avgCurrentRatioSY +
                ", avgCurrentRatioTY=" + avgCurrentRatioTY +
                ", debtorsDaysFY=" + debtorsDaysFY +
                ", debtorsDaysSY=" + debtorsDaysSY +
                ", debtorsDaysTY=" + debtorsDaysTY +
                ", avgInventoryFY=" + avgInventoryFY +
                ", avgInventorySY=" + avgInventorySY +
                ", avgInventoryTY=" + avgInventoryTY +
                ", cogsFY=" + cogsFY +
                ", cogsSY=" + cogsSY +
                ", cogsTY=" + cogsTY +
                ", creditorsDaysFY=" + creditorsDaysFY +
                ", creditorsDaysSY=" + creditorsDaysSY +
                ", creditorsDaysTY=" + creditorsDaysTY +
                ", netProfitOrLossFY=" + netProfitOrLossFY +
                ", netProfitOrLossSY=" + netProfitOrLossSY +
                ", netProfitOrLossTY=" + netProfitOrLossTY +
                ", depriciationFy=" + depriciationFy +
                ", depriciationSy=" + depriciationSy +
                ", depriciationTy=" + depriciationTy +
                ", interestFy=" + interestFy +
                ", interestSy=" + interestSy +
                ", interestTy=" + interestTy +
                ", totalSaleFy=" + totalSaleFy +
                ", totalSaleSy=" + totalSaleSy +
                ", totalSaleTy=" + totalSaleTy +
                ", profitBeforeTaxOrLossFy=" + profitBeforeTaxOrLossFy +
                ", profitBeforeTaxOrLossSy=" + profitBeforeTaxOrLossSy +
                ", profitBeforeTaxOrLossTy=" + profitBeforeTaxOrLossTy +
                ", totalAssetFy=" + totalAssetFy +
                ", totalAssetSy=" + totalAssetSy +
                ", totalAssetTy=" + totalAssetTy +
                ", opProfitBeforeInterestFy=" + opProfitBeforeInterestFy +
                ", opProfitBeforeInterestSy=" + opProfitBeforeInterestSy +
                ", opProfitBeforeInterestTy=" + opProfitBeforeInterestTy +
                ", noOfCustomenr=" + noOfCustomenr +
                ", concentrationCustomer=" + concentrationCustomer +
                ", experienceInTheBusiness=" + experienceInTheBusiness +
                ", totalCredit=" + totalCredit +
                ", projectedSale=" + projectedSale +
                ", creditSummation=" + creditSummation +
                ", age=" + age +
                ", noOfChildren=" + noOfChildren +
                ", owningHouse=" + owningHouse +
                ", acadamicQualification=" + acadamicQualification +
                ", experienceInLineOfBusiness=" + experienceInLineOfBusiness +
                ", spouceDetails=" + spouceDetails +
                ", assessedFOrIT=" + assessedFOrIT +
                ", haveLIPolicy=" + haveLIPolicy +
                ", repaymentPeriod=" + repaymentPeriod +
                ", knowHow=" + knowHow +
                ", lineOfActivity=" + lineOfActivity +
                ", competition=" + competition +
                ", factoryPremises=" + factoryPremises +
                ", grossSaleTy=" + grossSaleTy +
                ", totalCostSaleTy=" + totalCostSaleTy +
                ", finishedGoodTy=" + finishedGoodTy +
                ", gstSaleCurrentYear=" + gstSaleCurrentYear +
                ", netSaleFy=" + netSaleFy +
                ", netSaleSy=" + netSaleSy +
                ", netSaleTy=" + netSaleTy +
                ", yearsInBusiness=" + yearsInBusiness +
                ", continuousNetProfitOrLossFY=" + continuousNetProfitOrLossFY +
                ", continuousNetProfitOrLossSY=" + continuousNetProfitOrLossSY +
                ", continuousNetProfitOrLossTY=" + continuousNetProfitOrLossTY +
                ", noOfMonths=" + noOfMonths +
                ", averageDailyBalance=" + averageDailyBalance +
                ", limitsInAccount=" + limitsInAccount +
                ", turnOver=" + turnOver +
                ", eligibleLoanAmountCircular=" + eligibleLoanAmountCircular +
                ", amountOfCollateral=" + amountOfCollateral +
                ", ebitdaFY=" + ebitdaFY +
                ", ebitdaSY=" + ebitdaSY +
                ", ebitdaTY=" + ebitdaTY +
                ", existingLoanObligation=" + existingLoanObligation +
                ", loanType=" + loanType +
                ", roi=" + roi +
                ", tenure=" + tenure +
                ", tenureFs=" + tenureFs +
                ", tenure_p=" + tenure_p +
                ", pastYearTurnover=" + pastYearTurnover +
                ", exportSalesFY=" + exportSalesFY +
                ", exportSalesSY=" + exportSalesSY +
                ", exportSalesTY=" + exportSalesTY +
                ", domesticSalesFY=" + domesticSalesFY +
                ", domesticSalesSY=" + domesticSalesSY +
                ", domesticSalesTY=" + domesticSalesTY +
                ", totalTermLiabilitiesFY=" + totalTermLiabilitiesFY +
                ", totalTermLiabilitiesSY=" + totalTermLiabilitiesSY +
                ", totalTermLiabilitiesTY=" + totalTermLiabilitiesTY +
                ", preferenceSharesFY=" + preferenceSharesFY +
                ", preferenceSharesSY=" + preferenceSharesSY +
                ", preferenceSharesTY=" + preferenceSharesTY +
                ", unsecuredLoansFromOthersFY=" + unsecuredLoansFromOthersFY +
                ", unsecuredLoansFromOthersSY=" + unsecuredLoansFromOthersSY +
                ", unsecuredLoansFromOthersTY=" + unsecuredLoansFromOthersTY +
                ", othersFY=" + othersFY +
                ", othersSY=" + othersSY +
                ", othersTY=" + othersTY +
                ", minorityInterestFY=" + minorityInterestFY +
                ", minorityInterestSY=" + minorityInterestSY +
                ", minorityInterestTY=" + minorityInterestTY +
                ", deferredTaxLiabilityFY=" + deferredTaxLiabilityFY +
                ", deferredTaxLiabilitySY=" + deferredTaxLiabilitySY +
                ", deferredTaxLiabilityTY=" + deferredTaxLiabilityTY +
                ", deferredTaxAssetsFY=" + deferredTaxAssetsFY +
                ", deferredTaxAssetsSY=" + deferredTaxAssetsSY +
                ", deferredTaxAssetsTY=" + deferredTaxAssetsTY +
                ", profitBeforeInterestFY=" + profitBeforeInterestFY +
                ", profitBeforeInterestSY=" + profitBeforeInterestSY +
                ", profitBeforeInterestTY=" + profitBeforeInterestTY +
                ", depreciationFY=" + depreciationFY +
                ", depreciationSY=" + depreciationSY +
                ", depreciationTY=" + depreciationTY +
                ", liabilitiesOrdinaryShareCapitalFY=" + liabilitiesOrdinaryShareCapitalFY +
                ", liabilitiesOrdinaryShareCapitalSY=" + liabilitiesOrdinaryShareCapitalSY +
                ", liabilitiesOrdinaryShareCapitalTY=" + liabilitiesOrdinaryShareCapitalTY +
                ", liabilitiesGeneralReserveFY=" + liabilitiesGeneralReserveFY +
                ", liabilitiesGeneralReserveSY=" + liabilitiesGeneralReserveSY +
                ", liabilitiesGeneralReserveTY=" + liabilitiesGeneralReserveTY +
                ", liabilitiesSurplus=" + liabilitiesSurplus +
                ", deficitInProfitANDLossAccountFY=" + deficitInProfitANDLossAccountFY +
                ", deficitInProfitANDLossAccountSY=" + deficitInProfitANDLossAccountSY +
                ", deficitInProfitANDLossAccountTY=" + deficitInProfitANDLossAccountTY +
                ", liabilitiesUnsecuredLoansFromPpromotersFY=" + liabilitiesUnsecuredLoansFromPpromotersFY +
                ", liabilitiesUnsecuredLoansFromPpromotersSY=" + liabilitiesUnsecuredLoansFromPpromotersSY +
                ", liabilitiesUnsecuredLoansFromPpromotersTY=" + liabilitiesUnsecuredLoansFromPpromotersTY +
                ", liabilitiesUnsecuredLoansFromOthersFY=" + liabilitiesUnsecuredLoansFromOthersFY +
                ", liabilitiesUnsecuredLoansFromOthersSY=" + liabilitiesUnsecuredLoansFromOthersSY +
                ", liabilitiesUnsecuredLoansFromOthersTY=" + liabilitiesUnsecuredLoansFromOthersTY +
                ", assetsInvestmentsInSubsidiaryCosaffiliatesFY=" + assetsInvestmentsInSubsidiaryCosaffiliatesFY +
                ", assetsInvestmentsInSubsidiaryCosaffiliatesSY=" + assetsInvestmentsInSubsidiaryCosaffiliatesSY +
                ", assetsInvestmentsInSubsidiaryCosaffiliatesTY=" + assetsInvestmentsInSubsidiaryCosaffiliatesTY +
                ", noOfChequesBouncedLastMonth=" + noOfChequesBouncedLastMonth +
                ", noOfChequesBouncedLastSixMonth=" + noOfChequesBouncedLastSixMonth +
                ", totalNoOfInwardChequeBouncesLatSixMonths=" + totalNoOfInwardChequeBouncesLatSixMonths +
                ", idProof_p=" + idProof_p +
                ", idProof=" + idProof +
                ", noOfDependents_p=" + noOfDependents_p +
                ", noOfDependents=" + noOfDependents +
                ", addressYear=" + addressYear +
                ", addressYear_p=" + addressYear_p +
                ", certification_p=" + certification_p +
                ", certification=" + certification +
                ", mainDirectorCategory=" + mainDirectorCategory +
                ", mainDirectorCategory_p=" + mainDirectorCategory_p +
                ", castCategory=" + castCategory +
                ", castCategory_p=" + castCategory_p +
                ", otherSourceOfIncome=" + otherSourceOfIncome +
                ", otherSourceOfIncome_p=" + otherSourceOfIncome_p +
                ", parameters=" + parameters +
                ", parameters_p=" + parameters_p +
                ", sales_p=" + sales_p +
                ", sales=" + sales +
                ", pat_p=" + pat_p +
                ", pat=" + pat +
                ", aadhar=" + aadhar +
                ", aadhar_p=" + aadhar_p +
                ", marketingArrangmentForFinishedGoods=" + marketingArrangmentForFinishedGoods +
                ", marketingArrangmentForFinishedGoods_p=" + marketingArrangmentForFinishedGoods_p +
                ", registrationWithGovernmentAuthorities=" + registrationWithGovernmentAuthorities +
                ", registrationWithGovernmentAuthoritiesCombined=" + registrationWithGovernmentAuthoritiesCombined +
                ", registrationWithGovernmentAuthorities_p=" + registrationWithGovernmentAuthorities_p +
                ", registrationWithGovernmentAuthoritiesCombined_p=" + registrationWithGovernmentAuthoritiesCombined_p +
                ", businessProspects=" + businessProspects +
                ", businessProspects_p=" + businessProspects_p +
                ", accessInputs=" + accessInputs +
                ", accessInputs_p=" + accessInputs_p +
                ", itReturnFiledId=" + itReturnFiledId +
                ", isItReturnFiled_p=" + isItReturnFiled_p +
                ", bankRelation=" + bankRelation +
                ", bankRelationCombined=" + bankRelationCombined +
                ", bankRelation_p=" + bankRelation_p +
                ", bankRelationCombined_p=" + bankRelationCombined_p +
                ", typeOfActivity_p=" + typeOfActivity_p +
                ", typeOfActivity=" + typeOfActivity +
                ", distanceBtwWorkAndRes=" + distanceBtwWorkAndRes +
                ", distanceBtwWorkAndRes_p=" + distanceBtwWorkAndRes_p +
                ", employmentGeneration=" + employmentGeneration +
                ", employmentGeneration_p=" + employmentGeneration_p +
                ", promotersComtributionML=" + promotersComtributionML +
                ", promotersComtributionML_p=" + promotersComtributionML_p +
                ", experienceInBusinessVal=" + experienceInBusinessVal +
                ", experienceInBusiness_p=" + experienceInBusiness_p +
                ", fleetStrengthOwnedbyFleetOperator_p=" + fleetStrengthOwnedbyFleetOperator_p +
                ", noOfVehicles=" + noOfVehicles +
                ", assuredOrder_p=" + assuredOrder_p +
                ", assuredOrderVal=" + assuredOrderVal +
                ", familyMembersDirectlyVal_p=" + familyMembersDirectlyVal_p +
                ", familyMembersDirectlyVal=" + familyMembersDirectlyVal +
                ", loanFreeVehicle_p=" + loanFreeVehicle_p +
                ", totalCostOfExistingVehicleVal=" + totalCostOfExistingVehicleVal +
                ", totalSanctionVal=" + totalSanctionVal +
                ", depositPositionPotential_p=" + depositPositionPotential_p +
                ", totalDebit=" + totalDebit +
                ", fullMonthCount=" + fullMonthCount +
                ", profitabilityHistoryVal=" + profitabilityHistoryVal +
                ", profitabilityHistory_p=" + profitabilityHistory_p +
                ", collateralSecurity_p=" + collateralSecurity_p +
                ", conservativeDebtServiceCoverageVal=" + conservativeDebtServiceCoverageVal +
                ", conservativeDebtServiceCoverage_p=" + conservativeDebtServiceCoverage_p +
                '}';
    }
}