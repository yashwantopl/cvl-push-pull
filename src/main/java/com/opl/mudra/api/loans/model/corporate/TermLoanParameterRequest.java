package com.opl.mudra.api.loans.model.corporate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.model.DataRequest;
import com.opl.mudra.api.loans.model.ProductMasterRequest;
import com.opl.mudra.api.loans.model.common.FPParameterMappingRequest;

/**
 * The persistent class for the fp_term_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermLoanParameterRequest extends ProductMasterRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer currency;

	private Integer denomination;

	private Boolean isCollateralDisplay = false;

	private Boolean isCollateralMandatory = false;

	private Boolean isCreditRatingDisplay = false;

	private Boolean isCreditRatingMandatory = false;

	private Boolean isDebtEquityDisplay = false;

	private Boolean isDebtEquityMandatory = false;

	private Boolean isEstablishmentDisplay = false;

	private Boolean isEstablishmentMandatory = false;

	private Boolean isGeographicalDisplay = false;

	private Boolean isGeographicalMandatory = false;

	private Boolean isIndustrySectorDisplay = false;

	private Boolean isIndustrySectorMandatory = false;

	private Boolean isInvestmentSizeDisplay = false;

	private Boolean isInvestmentSizeMandatory = false;

	private Boolean isNetworthDisplay = false;

	private Boolean isNetworthMandatory = false;

	private Boolean isPastYearTurnoverDisplay = false;

	private Boolean isPastYearTurnoverMandatory = false;

	private Boolean isProfitabilityHistoryDisplay = false;

	private Boolean isProfitabilityHistoryMandatory = false;

	private Boolean isTenureDisplay = false;

	private Boolean isTenureMandatory = false;

	private Boolean isUnInterestedIndustryDisplay = false;

	private Boolean isUnInterestedIndustryMandatory = false;

	private Integer LongTermCreditRating;

	private Integer maxAgeEstablishment;

	private BigDecimal maxCollateral;

	private BigDecimal maxDebtEquity;

	private BigDecimal maxInvestSize;

	private BigDecimal maxNetworth;

	private BigDecimal maxPastTurnover;

	private BigDecimal maxTenure;

	private Integer minAgeEstablishment;

	private BigDecimal minCollateral;

	private BigDecimal minDebtEquity;

	private BigDecimal minInvestSize;

	private BigDecimal minNetworth;

	private BigDecimal minPastTurnover;

	private BigDecimal minTenure;

	private Integer profitabilityHistory;

	private Integer shortTermCreditRating;

	private Long uninterestedIndustry;

	private BigDecimal minCurrentRatio;

	private BigDecimal maxCurrentRatio;

	private Boolean isCurrentRatioDisplay = false;

	private Boolean isCurrentRatioMandatory = false;

	private BigDecimal minInterestCoverage;

	private BigDecimal maxInterestCoverage;

	private Boolean isInterestCoverageDisplay = false;

	private Boolean isInterestCoverageMandatory = false;

	private BigDecimal minTolTnw;

	private BigDecimal maxTolTnw;

	private Boolean isTolTnwDisplay = false;

	private Boolean isTolTnwMandatory = false;

	private BigDecimal minTurnoverRatio;

	private BigDecimal maxTurnoverRatio;

	private Boolean isTurnoverRatioDisplay = false;

	private Boolean isTurnoverRatioMandatory = false;

	private BigDecimal minGrossCashAccuralsRatio;

	private BigDecimal maxGrossCashAccuralsRatio;

	private Boolean isGrossCashAccuralsRatioDisplay = false;

	private Boolean isGrossCashAccuralsRatioMandatory = false;

	private BigDecimal minCustomerConcentration;

	private BigDecimal maxCustomerConcentration;

	private Boolean isCustomerConcentrationDisplay = false;

	private Boolean isCustomerConcentrationMandatory = false;

	private Integer minRiskModelScore;

	private Integer maxRiskModelScore;

	private Boolean isRiskModelScoreDisplay = false;

	private Boolean isRiskModelScoreMandatory = false;

	private Integer netWorth;

	private Integer minChequeBounced;

	private Integer maxChequeBounced;

	private Boolean isChequeBouncedDisplay = false;

	private Boolean isChequeBouncedMandatory = false;

	private Integer minChequeBouncedLastSixMonths;

	private Integer maxChequeBouncedLastSixMonths;

	private Boolean isChequeBouncedLastSixMonthsDisplay = false;

	private Boolean isChequeBouncedLastSixMonthsMandatory = false;

	private Integer ddrFlow;

	private Integer individualCibil;

	private Boolean isIndividualCibilDisplay = false;

	private Boolean isIndividualCibilMandatory = false;
	
	private Integer individualCibilDpd;

	private Boolean isIndividualCibilDpdDisplay = false;

	private Boolean isIndividualCibilDpdMandatory = false;

	private Integer commercialCibil;

	private Boolean isCommercialCibilDisplay = false;

	private Boolean isCommercialCibilMandatory = false;

	private Long userOrgId;

	private Integer appstage;

	private Object workflowData;

	// -----------------------added eligibility method for product
	private Integer assessmentMethodId;

	private BigDecimal minCgtmseCoverage;
	private BigDecimal maxCgtmseCoverage;
	private Boolean isCgtmseCoverageDisplay = false;
	private Boolean isCgtmseCoverageMandatory = false;
	private Boolean isMsmeFundingDisplay = false;
	private Boolean isMsmeFundingMandatory = false;
	private Boolean isMsmeRankingDisplay = false;
	private Boolean isMsmeRankingMandatory = false;
	private Integer msmeRanking;
	private Boolean isNoMsmeRanking = false;
	private List<Integer> msmeFundingIds;

	private BigDecimal minTenureNtb;

	private BigDecimal maxTenureNtb;

	private Integer cgtmseCoverage;

	private Integer cashMargin;

	private BigDecimal dscr;

	private Integer paybackPeriod;

	private BigDecimal newTolTnw;

	private BigDecimal newCollateralCoverage;

	private Boolean isNewCollateralCoverageCheck = false;

	private Boolean isNewTolTnwCheck = false;
	/**
	 * @return the workflowData
	 */

	/* ntb */

	private BigDecimal minAvrgAge;

	private BigDecimal maxAvrgAge;

	private BigDecimal minAvgYearlyIncome;

	private BigDecimal minAvrgYearlyIncome;

	private BigDecimal maxAvrgYearlyIncome;

	private BigDecimal mincgtmse;

	private BigDecimal maxcgtmse;

	private BigDecimal minLoanToAsset;

	private BigDecimal maxLoanToAsset;

	private BigDecimal minAvgWorkExp;

	private BigDecimal maxAvgWorkExp;

	private BigDecimal minCurrentFoir;

	private BigDecimal maxCurrentFoir;

	private Boolean isAvrgAgeDisplay = false;

	private Boolean isAvrgAgeMandatory = false;

	private Boolean isAvrgYearlyIncomeDisplay = false;

	private Boolean isAvrgYearlyIncomeMandatory = false;

	private Boolean iscgtmseDisplay = false;

	private Boolean iscgtmseMandatory = false;

	private Boolean isLoanToAssetDisplay = false;

	private Boolean isLoanToAssetMandatory = false;

	private Boolean isAvgWorkExpDisplay = false;

	private Boolean isAvgWorkExpMandatory = false;

	private Boolean isCurrentFoirDisplay = false;

	private Boolean isCurrentFoirMandatory = false;

	private BigDecimal maxDropInTurnover;

	private Boolean isMaxDropInTurnoverDisplay = false;

	private Boolean isMaxDropInTurnoverMandatory = false;

	private BigDecimal minUtilisationPercentage;

	private BigDecimal maxUtilisationPercentage;

	private Boolean isUtilisationPercentageDisplay = false;

	private Boolean isUtilisationPercentageMandatory = false;

	private BigDecimal minCreditSummation;

	private BigDecimal maxCreditSummation;

	private Boolean isCreditSummationDisplay = false;

	private Boolean isCreditSummationMandatory = false;

	private BigDecimal minCollateralCoverage;

	private BigDecimal maxCollateralCoverage;

	private Boolean isCollateralCoverageDisplay = false;

	private Boolean isCollateralCoverageMandatory = false;

	private Integer kotakSubParameter;

	private Boolean wcReqCheck = false;
	private Boolean isNewDscrCheck = false;

	private BigDecimal newDscrCheck;

	// projected sales
	private Boolean isNewHistoricSales = false;
	private BigDecimal newHistoricSales;

	// total limits
	private List<Integer> loanArrangementIds;

	private BigDecimal minAdditionalLoan;

	private BigDecimal maxAdditionalLoan;

	private BigDecimal minTotalLoan;

	private BigDecimal maxTotalLoan;

	private Boolean isHistoricGrowth = false;
	private BigDecimal newHistoricGrowth;

	private Boolean isConservativeRatioDisplay = false;

	private Boolean isConservativeRatioMandatory = false;

	private BigDecimal maxConservativeRatio;

	private BigDecimal minConservativeRatio;
	
	private Integer mainIndividualCibil;

	private Boolean isMainIndividualCibilDisplay = false;

	private Boolean isMainIndividualCibilMandatory = false;

    private Integer commercialCibilFor3Month;

    private Boolean isCommercialCibilFor3MonthDisplay = false;

    private Boolean isCommercialCibilFor3MonthMandatory = false;
    
    private Integer individualCibilFor3Month;

	private Boolean isIndividualCibilFor3MonthDisplay = false;

	private Boolean isIndividualCibilFor3MonthMandatory = false;
    
	private BigDecimal manufacturing;
	private BigDecimal service;
	private BigDecimal trading;
	private BigDecimal pcWomen;
	private BigDecimal pcMinorityCommunity;
	
	private BigDecimal promotorContri;
	
	//co-origination new parameter
		private Boolean isMinNoCreditTransactionDisplay=false;

		private Boolean isMinNoCreditTransactionMandatory=false;
		
		private BigDecimal maxNoCreditTransaction;
		
		private BigDecimal minNoCreditTransaction;
		

		private Boolean isMinNoOverallTransactionDisplay=false;

		private Boolean isMinNoOverallTransactionMandatory=false;
		
		private BigDecimal maxNoOverallTransaction;
		
		private BigDecimal minNoOverallTransaction;
		
		
		
		private Boolean isMinSalesGrowthDisplay=false;

		private Boolean isMinSalesGrowthMandatory=false;
		
		private BigDecimal maxSalesGrowth;
		
		private BigDecimal minSalesGrowth ;
		
		
		
		
		private Boolean isMinCashProfitGrowthDisplay=false;

		private Boolean isMinCashProfitGrowthMandatory=false;
		
		private BigDecimal maxCashProfitGrowth;
		
		private BigDecimal minCashProfitGrowth ;
		
		
		
		private Boolean isMinMonthlyAvgBalDisplay =false;

		private Boolean isMinMonthlyAvgBalMandatory=false;
		
		private BigDecimal maxMonthlyAvgBal;
		
		private BigDecimal minMonthlyAvgBal ;
		
		
		private Boolean isMinNoDebitTransactionDisplay =false;

		private Boolean isMinNoDebitTransactionMandatory=false;
		
		private BigDecimal maxNoDebitTransaction;
		
		private BigDecimal minNoDebitTransaction ;
		
		private List<Integer> constitutionIds;
		
		private Boolean isConstitutionDisplay = false;

		private Boolean isConstitutionMandatory = false;
		
		private BigDecimal minCgtmseCoveragePerc;
		
		private BigDecimal cgtmseCoveragePerc;
		
		private Boolean isCgtmseCoveragePercDisplay = false;
		
		private Boolean isCgtmseCoveragePercMandatory =false;
		
		
		
		
		public BigDecimal getMinCgtmseCoveragePerc() {
			return minCgtmseCoveragePerc;
		}

		public void setMinCgtmseCoveragePerc(BigDecimal minCgtmseCoveragePerc) {
			this.minCgtmseCoveragePerc = minCgtmseCoveragePerc;
		}

		public BigDecimal getCgtmseCoveragePerc() {
			return cgtmseCoveragePerc;
		}

		public void setCgtmseCoveragePerc(BigDecimal cgtmseCoveragePerc) {
			this.cgtmseCoveragePerc = cgtmseCoveragePerc;
		}

		public Boolean getIsCgtmseCoveragePercDisplay() {
			return isCgtmseCoveragePercDisplay;
		}

		public void setIsCgtmseCoveragePercDisplay(Boolean isCgtmseCoveragePercDisplay) {
			this.isCgtmseCoveragePercDisplay = isCgtmseCoveragePercDisplay;
		}

		public Boolean getIsCgtmseCoveragePercMandatory() {
			return isCgtmseCoveragePercMandatory;
		}

		public void setIsCgtmseCoveragePercMandatory(Boolean isCgtmseCoveragePercMandatory) {
			this.isCgtmseCoveragePercMandatory = isCgtmseCoveragePercMandatory;
		}

		private List<Integer> bureauScoreIds = Collections.emptyList();
		
		private List<Integer> mainDirBureauScoreIds = Collections.emptyList();
		
		private List<FPParameterMappingRequest> riskLoanAmountList = Collections.emptyList();
		
	

	public BigDecimal getPromotorContri() {
		return promotorContri;
	}

	public void setPromotorContri(BigDecimal promotorContri) {
		this.promotorContri = promotorContri;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	/**
	 * @param workflowData
	 *            the workflowData to set
	 */
	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
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

	public void setIsIndividualCibilDisplay(Boolean individualCibilDisplay) {
		isIndividualCibilDisplay = individualCibilDisplay;
	}

	public Boolean getIsIndividualCibilMandatory() {
		return isIndividualCibilMandatory;
	}

	public void setIsIndividualCibilMandatory(Boolean individualCibilMandatory) {
		isIndividualCibilMandatory = individualCibilMandatory;
	}

	public Integer getCommercialCibil() {
		return commercialCibil;
	}

	public void setCommercialCibil(Integer commercialCibil) {
		this.commercialCibil = commercialCibil;
	}

	public Boolean getIsCommercialCibilDisplay() {
		return isCommercialCibilDisplay;
	}

	public void setIsCommercialCibilDisplay(Boolean commercialCibilDisplay) {
		isCommercialCibilDisplay = commercialCibilDisplay;
	}

	public Boolean getIsCommercialCibilMandatory() {
		return isCommercialCibilMandatory;
	}

	public void setIsCommercialCibilMandatory(Boolean commercialCibilMandatory) {
		isCommercialCibilMandatory = commercialCibilMandatory;
	}

	public Integer getDdrFlow() {
		return ddrFlow;
	}

	public void setDdrFlow(Integer ddrFlow) {
		this.ddrFlow = ddrFlow;
	}

	public Integer getMinChequeBounced() {
		return minChequeBounced;
	}

	public void setMinChequeBounced(Integer minChequeBounced) {
		this.minChequeBounced = minChequeBounced;
	}

	public Integer getMaxChequeBounced() {
		return maxChequeBounced;
	}

	public void setMaxChequeBounced(Integer maxChequeBounced) {
		this.maxChequeBounced = maxChequeBounced;
	}

	public Boolean getIsChequeBouncedDisplay() {
		return isChequeBouncedDisplay;
	}

	public void setIsChequeBouncedDisplay(Boolean chequeBouncedDisplay) {
		isChequeBouncedDisplay = chequeBouncedDisplay;
	}

	public Boolean getIsChequeBouncedMandatory() {
		return isChequeBouncedMandatory;
	}

	public void setIsChequeBouncedMandatory(Boolean chequeBouncedMandatory) {
		isChequeBouncedMandatory = chequeBouncedMandatory;
	}

	public Integer getMinChequeBouncedLastSixMonths() {
		return minChequeBouncedLastSixMonths;
	}

	public void setMinChequeBouncedLastSixMonths(Integer minChequeBouncedLastSixMonths) {
		this.minChequeBouncedLastSixMonths = minChequeBouncedLastSixMonths;
	}

	public Integer getMaxChequeBouncedLastSixMonths() {
		return maxChequeBouncedLastSixMonths;
	}

	public void setMaxChequeBouncedLastSixMonths(Integer maxChequeBouncedLastSixMonths) {
		this.maxChequeBouncedLastSixMonths = maxChequeBouncedLastSixMonths;
	}

	public Boolean getIsChequeBouncedLastSixMonthsDisplay() {
		return isChequeBouncedLastSixMonthsDisplay;
	}

	public void setIsChequeBouncedLastSixMonthsDisplay(Boolean chequeBouncedLastSixMonthsDisplay) {
		isChequeBouncedLastSixMonthsDisplay = chequeBouncedLastSixMonthsDisplay;
	}

	public Boolean getIsChequeBouncedLastSixMonthsMandatory() {
		return isChequeBouncedLastSixMonthsMandatory;
	}

	public void setIsChequeBouncedLastSixMonthsMandatory(Boolean chequeBouncedLastSixMonthsMandatory) {
		isChequeBouncedLastSixMonthsMandatory = chequeBouncedLastSixMonthsMandatory;
	}

	public Boolean getIsCurrentRatioDisplay() {
		return isCurrentRatioDisplay;
	}

	public void setIsCurrentRatioDisplay(Boolean currentRatioDisplay) {
		isCurrentRatioDisplay = currentRatioDisplay;
	}

	public Boolean getIsCurrentRatioMandatory() {
		return isCurrentRatioMandatory;
	}

	public void setIsCurrentRatioMandatory(Boolean currentRatioMandatory) {
		isCurrentRatioMandatory = currentRatioMandatory;
	}

	public Boolean getIsInterestCoverageDisplay() {
		return isInterestCoverageDisplay;
	}

	public void setIsInterestCoverageDisplay(Boolean interestCoverageDisplay) {
		isInterestCoverageDisplay = interestCoverageDisplay;
	}

	public Boolean getIsInterestCoverageMandatory() {
		return isInterestCoverageMandatory;
	}

	public void setIsInterestCoverageMandatory(Boolean interestCoverageMandatory) {
		isInterestCoverageMandatory = interestCoverageMandatory;
	}

	public Boolean getIsTolTnwDisplay() {
		return isTolTnwDisplay;
	}

	public void setIsTolTnwDisplay(Boolean tolTnwDisplay) {
		isTolTnwDisplay = tolTnwDisplay;
	}

	public Boolean getIsTolTnwMandatory() {
		return isTolTnwMandatory;
	}

	public void setIsTolTnwMandatory(Boolean tolTnwMandatory) {
		isTolTnwMandatory = tolTnwMandatory;
	}

	public Boolean getIsTurnoverRatioDisplay() {
		return isTurnoverRatioDisplay;
	}

	public void setIsTurnoverRatioDisplay(Boolean turnoverRatioDisplay) {
		isTurnoverRatioDisplay = turnoverRatioDisplay;
	}

	public Boolean getIsTurnoverRatioMandatory() {
		return isTurnoverRatioMandatory;
	}

	public void setIsTurnoverRatioMandatory(Boolean turnoverRatioMandatory) {
		isTurnoverRatioMandatory = turnoverRatioMandatory;
	}

	public Boolean getIsGrossCashAccuralsRatioDisplay() {
		return isGrossCashAccuralsRatioDisplay;
	}

	public void setIsGrossCashAccuralsRatioDisplay(Boolean grossCashAccuralsRatioDisplay) {
		isGrossCashAccuralsRatioDisplay = grossCashAccuralsRatioDisplay;
	}

	public Boolean getIsGrossCashAccuralsRatioMandatory() {
		return isGrossCashAccuralsRatioMandatory;
	}

	public void setIsGrossCashAccuralsRatioMandatory(Boolean grossCashAccuralsRatioMandatory) {
		isGrossCashAccuralsRatioMandatory = grossCashAccuralsRatioMandatory;
	}

	public Boolean getIsCustomerConcentrationDisplay() {
		return isCustomerConcentrationDisplay;
	}

	public void setIsCustomerConcentrationDisplay(Boolean customerConcentrationDisplay) {
		isCustomerConcentrationDisplay = customerConcentrationDisplay;
	}

	public Boolean getIsCustomerConcentrationMandatory() {
		return isCustomerConcentrationMandatory;
	}

	public void setIsCustomerConcentrationMandatory(Boolean customerConcentrationMandatory) {
		isCustomerConcentrationMandatory = customerConcentrationMandatory;
	}

	public Boolean getIsRiskModelScoreDisplay() {
		return isRiskModelScoreDisplay;
	}

	public void setIsRiskModelScoreDisplay(Boolean riskModelScoreDisplay) {
		isRiskModelScoreDisplay = riskModelScoreDisplay;
	}

	public Boolean getIsRiskModelScoreMandatory() {
		return isRiskModelScoreMandatory;
	}

	public void setIsRiskModelScoreMandatory(Boolean riskModelScoreMandatory) {
		isRiskModelScoreMandatory = riskModelScoreMandatory;
	}

	public BigDecimal getMinCurrentRatio() {
		return minCurrentRatio;
	}

	public void setMinCurrentRatio(BigDecimal minCurrentRatio) {
		this.minCurrentRatio = minCurrentRatio;
	}

	public BigDecimal getMaxCurrentRatio() {
		return maxCurrentRatio;
	}

	public void setMaxCurrentRatio(BigDecimal maxCurrentRatio) {
		this.maxCurrentRatio = maxCurrentRatio;
	}

	public BigDecimal getMinInterestCoverage() {
		return minInterestCoverage;
	}

	public void setMinInterestCoverage(BigDecimal minInterestCoverage) {
		this.minInterestCoverage = minInterestCoverage;
	}

	public BigDecimal getMaxInterestCoverage() {
		return maxInterestCoverage;
	}

	public void setMaxInterestCoverage(BigDecimal maxInterestCoverage) {
		this.maxInterestCoverage = maxInterestCoverage;
	}

	public BigDecimal getMinTolTnw() {
		return minTolTnw;
	}

	public void setMinTolTnw(BigDecimal minTolTnw) {
		this.minTolTnw = minTolTnw;
	}

	public BigDecimal getMaxTolTnw() {
		return maxTolTnw;
	}

	public void setMaxTolTnw(BigDecimal maxTolTnw) {
		this.maxTolTnw = maxTolTnw;
	}

	public BigDecimal getMinTurnoverRatio() {
		return minTurnoverRatio;
	}

	public void setMinTurnoverRatio(BigDecimal minTurnoverRatio) {
		this.minTurnoverRatio = minTurnoverRatio;
	}

	public BigDecimal getMaxTurnoverRatio() {
		return maxTurnoverRatio;
	}

	public void setMaxTurnoverRatio(BigDecimal maxTurnoverRatio) {
		this.maxTurnoverRatio = maxTurnoverRatio;
	}

	public BigDecimal getMinGrossCashAccuralsRatio() {
		return minGrossCashAccuralsRatio;
	}

	public void setMinGrossCashAccuralsRatio(BigDecimal minGrossCashAccuralsRatio) {
		this.minGrossCashAccuralsRatio = minGrossCashAccuralsRatio;
	}

	public BigDecimal getMaxGrossCashAccuralsRatio() {
		return maxGrossCashAccuralsRatio;
	}

	public void setMaxGrossCashAccuralsRatio(BigDecimal maxGrossCashAccuralsRatio) {
		this.maxGrossCashAccuralsRatio = maxGrossCashAccuralsRatio;
	}

	public BigDecimal getMinCustomerConcentration() {
		return minCustomerConcentration;
	}

	public void setMinCustomerConcentration(BigDecimal minCustomerConcentration) {
		this.minCustomerConcentration = minCustomerConcentration;
	}

	public BigDecimal getMaxCustomerConcentration() {
		return maxCustomerConcentration;
	}

	public void setMaxCustomerConcentration(BigDecimal maxCustomerConcentration) {
		this.maxCustomerConcentration = maxCustomerConcentration;
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

	public Integer getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(Integer netWorth) {
		this.netWorth = netWorth;
	}

	private List<DataRequest> industrylist = Collections.emptyList();

	private List<DataRequest> sectorlist = Collections.emptyList();

	private List<DataRequest> countryList = Collections.emptyList();

	private List<DataRequest> stateList = Collections.emptyList();

	private List<DataRequest> cityList = Collections.emptyList();

	private List<DataRequest> unInterestedIndustrylist = Collections.emptyList();
	
	private List<DataRequest> nbfcRatioMasterList = Collections.emptyList();
	
	private List<Long> nbfcRatioSelectedList = Collections.emptyList();
	
	private List<Long> nbfcRatioIds;

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

	public Boolean getIsCollateralDisplay() {
		return isCollateralDisplay;
	}

	public void setIsCollateralDisplay(Boolean isCollateralDisplay) {
		this.isCollateralDisplay = isCollateralDisplay;
	}

	public Boolean getIsCollateralMandatory() {
		return isCollateralMandatory;
	}

	public void setIsCollateralMandatory(Boolean isCollateralMandatory) {
		this.isCollateralMandatory = isCollateralMandatory;
	}

	public Boolean getIsCreditRatingDisplay() {
		return isCreditRatingDisplay;
	}

	public void setIsCreditRatingDisplay(Boolean isCreditRatingDisplay) {
		this.isCreditRatingDisplay = isCreditRatingDisplay;
	}

	public Boolean getIsCreditRatingMandatory() {
		return isCreditRatingMandatory;
	}

	public void setIsCreditRatingMandatory(Boolean isCreditRatingMandatory) {
		this.isCreditRatingMandatory = isCreditRatingMandatory;
	}

	public Boolean getIsDebtEquityDisplay() {
		return isDebtEquityDisplay;
	}

	public void setIsDebtEquityDisplay(Boolean isDebtEquityDisplay) {
		this.isDebtEquityDisplay = isDebtEquityDisplay;
	}

	public Boolean getIsDebtEquityMandatory() {
		return isDebtEquityMandatory;
	}

	public void setIsDebtEquityMandatory(Boolean isDebtEquityMandatory) {
		this.isDebtEquityMandatory = isDebtEquityMandatory;
	}

	public Boolean getIsEstablishmentDisplay() {
		return isEstablishmentDisplay;
	}

	public void setIsEstablishmentDisplay(Boolean isEstablishmentDisplay) {
		this.isEstablishmentDisplay = isEstablishmentDisplay;
	}

	public Boolean getIsEstablishmentMandatory() {
		return isEstablishmentMandatory;
	}

	public void setIsEstablishmentMandatory(Boolean isEstablishmentMandatory) {
		this.isEstablishmentMandatory = isEstablishmentMandatory;
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

	public Boolean getIsNetworthDisplay() {
		return isNetworthDisplay;
	}

	public void setIsNetworthDisplay(Boolean isNetworthDisplay) {
		this.isNetworthDisplay = isNetworthDisplay;
	}

	public Boolean getIsNetworthMandatory() {
		return isNetworthMandatory;
	}

	public void setIsNetworthMandatory(Boolean isNetworthMandatory) {
		this.isNetworthMandatory = isNetworthMandatory;
	}

	public Boolean getIsPastYearTurnoverDisplay() {
		return isPastYearTurnoverDisplay;
	}

	public void setIsPastYearTurnoverDisplay(Boolean isPastYearTurnoverDisplay) {
		this.isPastYearTurnoverDisplay = isPastYearTurnoverDisplay;
	}

	public Boolean getIsPastYearTurnoverMandatory() {
		return isPastYearTurnoverMandatory;
	}

	public void setIsPastYearTurnoverMandatory(Boolean isPastYearTurnoverMandatory) {
		this.isPastYearTurnoverMandatory = isPastYearTurnoverMandatory;
	}

	public Boolean getIsProfitabilityHistoryDisplay() {
		return isProfitabilityHistoryDisplay;
	}

	public void setIsProfitabilityHistoryDisplay(Boolean isProfitabilityHistoryDisplay) {
		this.isProfitabilityHistoryDisplay = isProfitabilityHistoryDisplay;
	}

	public Boolean getIsProfitabilityHistoryMandatory() {
		return isProfitabilityHistoryMandatory;
	}

	public void setIsProfitabilityHistoryMandatory(Boolean isProfitabilityHistoryMandatory) {
		this.isProfitabilityHistoryMandatory = isProfitabilityHistoryMandatory;
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

	public Integer getLongTermCreditRating() {
		return LongTermCreditRating;
	}

	public void setLongTermCreditRating(Integer longTermCreditRating) {
		LongTermCreditRating = longTermCreditRating;
	}

	public Integer getMaxAgeEstablishment() {
		return maxAgeEstablishment;
	}

	public void setMaxAgeEstablishment(Integer maxAgeEstablishment) {
		this.maxAgeEstablishment = maxAgeEstablishment;
	}

	public BigDecimal getMaxCollateral() {
		return maxCollateral;
	}

	public void setMaxCollateral(BigDecimal maxCollateral) {
		this.maxCollateral = maxCollateral;
	}

	public BigDecimal getMinCollateral() {
		return minCollateral;
	}

	public void setMinCollateral(BigDecimal minCollateral) {
		this.minCollateral = minCollateral;
	}

	public BigDecimal getMaxDebtEquity() {
		return maxDebtEquity;
	}

	public void setMaxDebtEquity(BigDecimal maxDebtEquity) {
		this.maxDebtEquity = maxDebtEquity;
	}

	public void setMinDebtEquity(BigDecimal minDebtEquity) {
		this.minDebtEquity = minDebtEquity;
	}

	public BigDecimal getMaxInvestSize() {
		return maxInvestSize;
	}

	public void setMaxInvestSize(BigDecimal maxInvestSize) {
		this.maxInvestSize = maxInvestSize;
	}

	public BigDecimal getMaxNetworth() {
		return maxNetworth;
	}

	public void setMaxNetworth(BigDecimal maxNetworth) {
		this.maxNetworth = maxNetworth;
	}

	public BigDecimal getMaxPastTurnover() {
		return maxPastTurnover;
	}

	public void setMaxPastTurnover(BigDecimal maxPastTurnover) {
		this.maxPastTurnover = maxPastTurnover;
	}

	public BigDecimal getMaxTenure() {
		return maxTenure;
	}

	public void setMaxTenure(BigDecimal maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Integer getMinAgeEstablishment() {
		return minAgeEstablishment;
	}

	public void setMinAgeEstablishment(Integer minAgeEstablishment) {
		this.minAgeEstablishment = minAgeEstablishment;
	}

	public BigDecimal getMinDebtEquity() {
		return minDebtEquity;
	}

	public BigDecimal getMinInvestSize() {
		return minInvestSize;
	}

	public void setMinInvestSize(BigDecimal minInvestSize) {
		this.minInvestSize = minInvestSize;
	}

	public BigDecimal getMinNetworth() {
		return minNetworth;
	}

	public void setMinNetworth(BigDecimal minNetworth) {
		this.minNetworth = minNetworth;
	}

	public BigDecimal getMinPastTurnover() {
		return minPastTurnover;
	}

	public void setMinPastTurnover(BigDecimal minPastTurnover) {
		this.minPastTurnover = minPastTurnover;
	}

	public BigDecimal getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(BigDecimal minTenure) {
		this.minTenure = minTenure;
	}

	public Integer getProfitabilityHistory() {
		return profitabilityHistory;
	}

	public void setProfitabilityHistory(Integer profitabilityHistory) {
		this.profitabilityHistory = profitabilityHistory;
	}

	public Integer getShortTermCreditRating() {
		return shortTermCreditRating;
	}

	public void setShortTermCreditRating(Integer shortTermCreditRating) {
		this.shortTermCreditRating = shortTermCreditRating;
	}

	public Long getUninterestedIndustry() {
		return uninterestedIndustry;
	}

	public void setUninterestedIndustry(Long uninterestedIndustry) {
		this.uninterestedIndustry = uninterestedIndustry;
	}

	public List<DataRequest> getIndustrylist() {
		return industrylist;
	}

	public void setIndustrylist(List<DataRequest> industrylist) {
		this.industrylist = industrylist;
	}

	public List<DataRequest> getSectorlist() {
		return sectorlist;
	}

	public void setSectorlist(List<DataRequest> sectorlist) {
		this.sectorlist = sectorlist;
	}

	public List<DataRequest> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<DataRequest> countryList) {
		this.countryList = countryList;
	}

	public List<DataRequest> getUnInterestedIndustrylist() {
		return unInterestedIndustrylist;
	}

	public void setUnInterestedIndustrylist(List<DataRequest> unInterestedIndustrylist) {
		this.unInterestedIndustrylist = unInterestedIndustrylist;
	}

	public List<DataRequest> getStateList() {
		return stateList;
	}

	public void setStateList(List<DataRequest> stateList) {
		this.stateList = stateList;
	}

	public List<DataRequest> getCityList() {
		return cityList;
	}

	public void setCityList(List<DataRequest> cityList) {
		this.cityList = cityList;
	}

	public List<DataRequest> getNegativeIndustryList() {
		return unInterestedIndustrylist;
	}

	public void setNegativeIndustryList(List<DataRequest> unInterestedIndustrylist) {
		this.unInterestedIndustrylist = unInterestedIndustrylist;
	}

	public Boolean getIsUnInterestedIndustryDisplay() {
		return isUnInterestedIndustryDisplay;
	}

	public void setIsUnInterestedIndustryDisplay(Boolean isUnInterestedIndustryDisplay) {
		this.isUnInterestedIndustryDisplay = isUnInterestedIndustryDisplay;
	}

	public Boolean getIsUnInterestedIndustryMandatory() {
		return isUnInterestedIndustryMandatory;
	}

	public void setIsUnInterestedIndustryMandatory(Boolean isUnInterestedIndustryMandatory) {
		this.isUnInterestedIndustryMandatory = isUnInterestedIndustryMandatory;
	}

	public Integer getAssessmentMethodId() {
		return assessmentMethodId;
	}

	public void setAssessmentMethodId(Integer assessmentMethodId) {
		this.assessmentMethodId = assessmentMethodId;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Integer getAppstage() {
		return appstage;
	}

	public void setAppstage(Integer appstage) {
		this.appstage = appstage;
	}

	public BigDecimal getMinCgtmseCoverage() {
		return minCgtmseCoverage;
	}

	public void setMinCgtmseCoverage(BigDecimal minCgtmseCoverage) {
		this.minCgtmseCoverage = minCgtmseCoverage;
	}

	public BigDecimal getMaxCgtmseCoverage() {
		return maxCgtmseCoverage;
	}

	public void setMaxCgtmseCoverage(BigDecimal maxCgtmseCoverage) {
		this.maxCgtmseCoverage = maxCgtmseCoverage;
	}

	public List<Integer> getMsmeFundingIds() {
		return msmeFundingIds;
	}

	public void setMsmeFundingIds(List<Integer> msmeFundingIds) {
		this.msmeFundingIds = msmeFundingIds;
	}

	public Boolean getIsCgtmseCoverageDisplay() {
		return isCgtmseCoverageDisplay;
	}

	public void setIsCgtmseCoverageDisplay(Boolean isCgtmseCoverageDisplay) {
		this.isCgtmseCoverageDisplay = isCgtmseCoverageDisplay;
	}

	public Boolean getIsCgtmseCoverageMandatory() {
		return isCgtmseCoverageMandatory;
	}

	public void setIsCgtmseCoverageMandatory(Boolean isCgtmseCoverageMandatory) {
		this.isCgtmseCoverageMandatory = isCgtmseCoverageMandatory;
	}

	public Boolean getIsMsmeFundingDisplay() {
		return isMsmeFundingDisplay;
	}

	public void setIsMsmeFundingDisplay(Boolean isMsmeFundingDisplay) {
		this.isMsmeFundingDisplay = isMsmeFundingDisplay;
	}

	public Boolean getIsMsmeFundingMandatory() {
		return isMsmeFundingMandatory;
	}

	public void setIsMsmeFundingMandatory(Boolean isMsmeFundingMandatory) {
		this.isMsmeFundingMandatory = isMsmeFundingMandatory;
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

	public BigDecimal getMinAvgYearlyIncome() {
		return minAvgYearlyIncome;
	}

	public void setMinAvgYearlyIncome(BigDecimal minAvgYearlyIncome) {
		this.minAvgYearlyIncome = minAvgYearlyIncome;
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

	public BigDecimal getMinTenureNtb() {
		return minTenureNtb;
	}

	public void setMinTenureNtb(BigDecimal minTenureNtb) {
		this.minTenureNtb = minTenureNtb;
	}

	public BigDecimal getMaxTenureNtb() {
		return maxTenureNtb;
	}

	public void setMaxTenureNtb(BigDecimal maxTenureNtb) {
		this.maxTenureNtb = maxTenureNtb;
	}

	public Integer getCgtmseCoverage() {
		return cgtmseCoverage;
	}

	public void setCgtmseCoverage(Integer cgtmseCoverage) {
		this.cgtmseCoverage = cgtmseCoverage;
	}

	public Integer getCashMargin() {
		return cashMargin;
	}

	public void setCashMargin(Integer cashMargin) {
		this.cashMargin = cashMargin;
	}

	public BigDecimal getDscr() {
		return dscr;
	}

	public void setDscr(BigDecimal dscr) {
		this.dscr = dscr;
	}

	public Integer getPaybackPeriod() {
		return paybackPeriod;
	}

	public void setPaybackPeriod(Integer paybackPeriod) {
		this.paybackPeriod = paybackPeriod;
	}

	public BigDecimal getMaxDropInTurnover() {
		return maxDropInTurnover;
	}

	public void setMaxDropInTurnover(BigDecimal maxDropInTurnover) {
		this.maxDropInTurnover = maxDropInTurnover;
	}

	public Boolean getIsMaxDropInTurnoverDisplay() {
		return isMaxDropInTurnoverDisplay;
	}

	public void setIsMaxDropInTurnoverDisplay(Boolean isMaxDropInTurnoverDisplay) {
		this.isMaxDropInTurnoverDisplay = isMaxDropInTurnoverDisplay;
	}

	public Boolean getIsMaxDropInTurnoverMandatory() {
		return isMaxDropInTurnoverMandatory;
	}

	public void setIsMaxDropInTurnoverMandatory(Boolean isMaxDropInTurnoverMandatory) {
		this.isMaxDropInTurnoverMandatory = isMaxDropInTurnoverMandatory;
	}

	public BigDecimal getMinUtilisationPercentage() {
		return minUtilisationPercentage;
	}

	public void setMinUtilisationPercentage(BigDecimal minUtilisationPercentage) {
		this.minUtilisationPercentage = minUtilisationPercentage;
	}

	public BigDecimal getMaxUtilisationPercentage() {
		return maxUtilisationPercentage;
	}

	public void setMaxUtilisationPercentage(BigDecimal maxUtilisationPercentage) {
		this.maxUtilisationPercentage = maxUtilisationPercentage;
	}

	public Boolean getIsUtilisationPercentageDisplay() {
		return isUtilisationPercentageDisplay;
	}

	public void setIsUtilisationPercentageDisplay(Boolean isUtilisationPercentageDisplay) {
		this.isUtilisationPercentageDisplay = isUtilisationPercentageDisplay;
	}

	public Boolean getIsUtilisationPercentageMandatory() {
		return isUtilisationPercentageMandatory;
	}

	public void setIsUtilisationPercentageMandatory(Boolean isUtilisationPercentageMandatory) {
		this.isUtilisationPercentageMandatory = isUtilisationPercentageMandatory;
	}

	public BigDecimal getMinCreditSummation() {
		return minCreditSummation;
	}

	public void setMinCreditSummation(BigDecimal minCreditSummation) {
		this.minCreditSummation = minCreditSummation;
	}

	public BigDecimal getMaxCreditSummation() {
		return maxCreditSummation;
	}

	public void setMaxCreditSummation(BigDecimal maxCreditSummation) {
		this.maxCreditSummation = maxCreditSummation;
	}

	public Boolean getIsCreditSummationDisplay() {
		return isCreditSummationDisplay;
	}

	public void setIsCreditSummationDisplay(Boolean isCreditSummationDisplay) {
		this.isCreditSummationDisplay = isCreditSummationDisplay;
	}

	public Boolean getIsCreditSummationMandatory() {
		return isCreditSummationMandatory;
	}

	public void setIsCreditSummationMandatory(Boolean isCreditSummationMandatory) {
		this.isCreditSummationMandatory = isCreditSummationMandatory;
	}

	public BigDecimal getMinCollateralCoverage() {
		return minCollateralCoverage;
	}

	public void setMinCollateralCoverage(BigDecimal minCollateralCoverage) {
		this.minCollateralCoverage = minCollateralCoverage;
	}

	public BigDecimal getMaxCollateralCoverage() {
		return maxCollateralCoverage;
	}

	public void setMaxCollateralCoverage(BigDecimal maxCollateralCoverage) {
		this.maxCollateralCoverage = maxCollateralCoverage;
	}

	public Boolean getIsCollateralCoverageDisplay() {
		return isCollateralCoverageDisplay;
	}

	public void setIsCollateralCoverageDisplay(Boolean isCollateralCoverageDisplay) {
		this.isCollateralCoverageDisplay = isCollateralCoverageDisplay;
	}

	public Boolean getIsCollateralCoverageMandatory() {
		return isCollateralCoverageMandatory;
	}

	public void setIsCollateralCoverageMandatory(Boolean isCollateralCoverageMandatory) {
		this.isCollateralCoverageMandatory = isCollateralCoverageMandatory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getNewTolTnw() {
		return newTolTnw;
	}

	public void setNewTolTnw(BigDecimal newTolTnw) {
		this.newTolTnw = newTolTnw;
	}

	public BigDecimal getNewCollateralCoverage() {
		return newCollateralCoverage;
	}

	public void setNewCollateralCoverage(BigDecimal newCollateralCoverage) {
		this.newCollateralCoverage = newCollateralCoverage;
	}

	public Boolean getIsNewCollateralCoverageCheck() {
		return isNewCollateralCoverageCheck;
	}

	public void setIsNewCollateralCoverageCheck(Boolean isNewCollateralCoverageCheck) {
		this.isNewCollateralCoverageCheck = isNewCollateralCoverageCheck;
	}

	public Boolean getIsNewTolTnwCheck() {
		return isNewTolTnwCheck;
	}

	public void setIsNewTolTnwCheck(Boolean isNewTolTnwCheck) {
		this.isNewTolTnwCheck = isNewTolTnwCheck;
	}

	public Integer getKotakSubParameter() {
		return kotakSubParameter;
	}

	public void setKotakSubParameter(Integer kotakSubParameter) {
		this.kotakSubParameter = kotakSubParameter;
	}

	public Boolean getWcReqCheck() {
		return wcReqCheck;
	}

	public Boolean getIsNewDscrCheck() {
		return isNewDscrCheck;
	}

	public void setWcReqCheck(Boolean wcReqCheck) {
		this.wcReqCheck = wcReqCheck;
	}

	public BigDecimal getNewDscrCheck() {
		return newDscrCheck;
	}

	public void setIsNewDscrCheck(Boolean isNewDscrCheck) {
		this.isNewDscrCheck = isNewDscrCheck;
	}

	public void setNewDscrCheck(BigDecimal newDscrCheck) {
		this.newDscrCheck = newDscrCheck;
	}

	public Boolean getIsNewHistoricSales() {
		return isNewHistoricSales;
	}

	public void setIsNewHistoricSales(Boolean isNewHistoricSales) {
		this.isNewHistoricSales = isNewHistoricSales;
	}

	public BigDecimal getNewHistoricSales() {
		return newHistoricSales;
	}

	public void setNewHistoricSales(BigDecimal newHistoricSales) {
		this.newHistoricSales = newHistoricSales;
	}

	public Boolean getIsHistoricGrowth() {
		return isHistoricGrowth;
	}

	public void setIsHistoricGrowth(Boolean isHistoricGrowth) {
		this.isHistoricGrowth = isHistoricGrowth;
	}

	public BigDecimal getNewHistoricGrowth() {
		return newHistoricGrowth;
	}

	public void setNewHistoricGrowth(BigDecimal newHistoricGrowth) {
		this.newHistoricGrowth = newHistoricGrowth;
	}

	public List<Integer> getLoanArrangementIds() {
		return loanArrangementIds;
	}

	public void setLoanArrangementIds(List<Integer> loanArrangementIds) {
		this.loanArrangementIds = loanArrangementIds;
	}

	public BigDecimal getMinAdditionalLoan() {
		return minAdditionalLoan;
	}

	public void setMinAdditionalLoan(BigDecimal minAdditionalLoan) {
		this.minAdditionalLoan = minAdditionalLoan;
	}

	public BigDecimal getMaxAdditionalLoan() {
		return maxAdditionalLoan;
	}

	public void setMaxAdditionalLoan(BigDecimal maxAdditionalLoan) {
		this.maxAdditionalLoan = maxAdditionalLoan;
	}

	public BigDecimal getMinTotalLoan() {
		return minTotalLoan;
	}

	public void setMinTotalLoan(BigDecimal minTotalLoan) {
		this.minTotalLoan = minTotalLoan;
	}

	public BigDecimal getMaxTotalLoan() {
		return maxTotalLoan;
	}

	public void setMaxTotalLoan(BigDecimal maxTotalLoan) {
		this.maxTotalLoan = maxTotalLoan;
	}

	public Boolean getIsConservativeRatioDisplay() {
		return isConservativeRatioDisplay;
	}

	public void setIsConservativeRatioDisplay(Boolean isConservativeRatioDisplay) {
		this.isConservativeRatioDisplay = isConservativeRatioDisplay;
	}

	public Boolean getIsConservativeRatioMandatory() {
		return isConservativeRatioMandatory;
	}

	public void setIsConservativeRatioMandatory(Boolean isConservativeRatioMandatory) {
		this.isConservativeRatioMandatory = isConservativeRatioMandatory;
	}

	public BigDecimal getMaxConservativeRatio() {
		return maxConservativeRatio;
	}

	public void setMaxConservativeRatio(BigDecimal maxConservativeRatio) {
		this.maxConservativeRatio = maxConservativeRatio;
	}

	public BigDecimal getMinConservativeRatio() {
		return minConservativeRatio;
	}

	public void setMinConservativeRatio(BigDecimal minConservativeRatio) {
		this.minConservativeRatio = minConservativeRatio;
	}

	public Integer getMainIndividualCibil() {
		return mainIndividualCibil;
	}

	public void setMainIndividualCibil(Integer mainIndividualCibil) {
		this.mainIndividualCibil = mainIndividualCibil;
	}

	public Boolean getIsMainIndividualCibilDisplay() {
		return isMainIndividualCibilDisplay;
	}

	public void setIsMainIndividualCibilDisplay(Boolean isMainIndividualCibilDisplay) {
		this.isMainIndividualCibilDisplay = isMainIndividualCibilDisplay;
	}

	public Boolean getIsMainIndividualCibilMandatory() {
		return isMainIndividualCibilMandatory;
	}

	public void setIsMainIndividualCibilMandatory(Boolean isMainIndividualCibilMandatory) {
		this.isMainIndividualCibilMandatory = isMainIndividualCibilMandatory;
	}

	public Integer getCommercialCibilFor3Month() {
		return commercialCibilFor3Month;
	}

	public void setCommercialCibilFor3Month(Integer commercialCibilFor3Month) {
		this.commercialCibilFor3Month = commercialCibilFor3Month;
	}

	public Boolean getIsCommercialCibilFor3MonthDisplay() {
		return isCommercialCibilFor3MonthDisplay;
	}

	public void setIsCommercialCibilFor3MonthDisplay(Boolean isCommercialCibilFor3MonthDisplay) {
		this.isCommercialCibilFor3MonthDisplay = isCommercialCibilFor3MonthDisplay;
	}

	public Boolean getIsCommercialCibilFor3MonthMandatory() {
		return isCommercialCibilFor3MonthMandatory;
	}

	public void setIsCommercialCibilFor3MonthMandatory(Boolean isCommercialCibilFor3MonthMandatory) {
		this.isCommercialCibilFor3MonthMandatory = isCommercialCibilFor3MonthMandatory;
	}

	public List<DataRequest> getNbfcRatioMasterList() {
		return nbfcRatioMasterList;
	}

	public void setNbfcRatioMasterList(List<DataRequest> nbfcRatioMasterList) {
		this.nbfcRatioMasterList = nbfcRatioMasterList;
	}

	public List<Long> getNbfcRatioSelectedList() {
		return nbfcRatioSelectedList;
	}

	public void setNbfcRatioSelectedList(List<Long> nbfcRatioSelectedList) {
		this.nbfcRatioSelectedList = nbfcRatioSelectedList;
	}

	public List<Long> getNbfcRatioIds() {
		return nbfcRatioIds;
	}

	public void setNbfcRatioIds(List<Long> nbfcRatioIds) {
		this.nbfcRatioIds = nbfcRatioIds;
	}

	public BigDecimal getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(BigDecimal manufacturing) {
		this.manufacturing = manufacturing;
	}

	public BigDecimal getService() {
		return service;
	}

	public void setService(BigDecimal service) {
		this.service = service;
	}

	public BigDecimal getTrading() {
		return trading;
	}

	public void setTrading(BigDecimal trading) {
		this.trading = trading;
	}

	public Boolean getIsMinNoCreditTransactionDisplay() {
		return isMinNoCreditTransactionDisplay;
	}

	public void setIsMinNoCreditTransactionDisplay(Boolean isMinNoCreditTransactionDisplay) {
		this.isMinNoCreditTransactionDisplay = isMinNoCreditTransactionDisplay;
	}

	public Boolean getIsMinNoCreditTransactionMandatory() {
		return isMinNoCreditTransactionMandatory;
	}

	public void setIsMinNoCreditTransactionMandatory(Boolean isMinNoCreditTransactionMandatory) {
		this.isMinNoCreditTransactionMandatory = isMinNoCreditTransactionMandatory;
	}

	public BigDecimal getMaxNoCreditTransaction() {
		return maxNoCreditTransaction;
	}

	public void setMaxNoCreditTransaction(BigDecimal maxNoCreditTransaction) {
		this.maxNoCreditTransaction = maxNoCreditTransaction;
	}

	public BigDecimal getMinNoCreditTransaction() {
		return minNoCreditTransaction;
	}

	public void setMinNoCreditTransaction(BigDecimal minNoCreditTransaction) {
		this.minNoCreditTransaction = minNoCreditTransaction;
	}

	public Boolean getIsMinNoOverallTransactionDisplay() {
		return isMinNoOverallTransactionDisplay;
	}

	public void setIsMinNoOverallTransactionDisplay(Boolean isMinNoOverallTransactionDisplay) {
		this.isMinNoOverallTransactionDisplay = isMinNoOverallTransactionDisplay;
	}

	public Boolean getIsMinNoOverallTransactionMandatory() {
		return isMinNoOverallTransactionMandatory;
	}

	public void setIsMinNoOverallTransactionMandatory(Boolean isMinNoOverallTransactionMandatory) {
		this.isMinNoOverallTransactionMandatory = isMinNoOverallTransactionMandatory;
	}

	public BigDecimal getMaxNoOverallTransaction() {
		return maxNoOverallTransaction;
	}

	public void setMaxNoOverallTransaction(BigDecimal maxNoOverallTransaction) {
		this.maxNoOverallTransaction = maxNoOverallTransaction;
	}

	public BigDecimal getMinNoOverallTransaction() {
		return minNoOverallTransaction;
	}

	public void setMinNoOverallTransaction(BigDecimal minNoOverallTransaction) {
		this.minNoOverallTransaction = minNoOverallTransaction;
	}

	public Boolean getIsMinSalesGrowthDisplay() {
		return isMinSalesGrowthDisplay;
	}

	public void setIsMinSalesGrowthDisplay(Boolean isMinSalesGrowthDisplay) {
		this.isMinSalesGrowthDisplay = isMinSalesGrowthDisplay;
	}

	public Boolean getIsMinSalesGrowthMandatory() {
		return isMinSalesGrowthMandatory;
	}

	public void setIsMinSalesGrowthMandatory(Boolean isMinSalesGrowthMandatory) {
		this.isMinSalesGrowthMandatory = isMinSalesGrowthMandatory;
	}

	public BigDecimal getMaxSalesGrowth() {
		return maxSalesGrowth;
	}

	public void setMaxSalesGrowth(BigDecimal maxSalesGrowth) {
		this.maxSalesGrowth = maxSalesGrowth;
	}

	public BigDecimal getMinSalesGrowth() {
		return minSalesGrowth;
	}

	public void setMinSalesGrowth(BigDecimal minSalesGrowth) {
		this.minSalesGrowth = minSalesGrowth;
	}

	public Boolean getIsMinCashProfitGrowthDisplay() {
		return isMinCashProfitGrowthDisplay;
	}

	public void setIsMinCashProfitGrowthDisplay(Boolean isMinCashProfitGrowthDisplay) {
		this.isMinCashProfitGrowthDisplay = isMinCashProfitGrowthDisplay;
	}

	public Boolean getIsMinCashProfitGrowthMandatory() {
		return isMinCashProfitGrowthMandatory;
	}

	public void setIsMinCashProfitGrowthMandatory(Boolean isMinCashProfitGrowthMandatory) {
		this.isMinCashProfitGrowthMandatory = isMinCashProfitGrowthMandatory;
	}

	public BigDecimal getMaxCashProfitGrowth() {
		return maxCashProfitGrowth;
	}

	public void setMaxCashProfitGrowth(BigDecimal maxCashProfitGrowth) {
		this.maxCashProfitGrowth = maxCashProfitGrowth;
	}

	public BigDecimal getMinCashProfitGrowth() {
		return minCashProfitGrowth;
	}

	public void setMinCashProfitGrowth(BigDecimal minCashProfitGrowth) {
		this.minCashProfitGrowth = minCashProfitGrowth;
	}

	public Boolean getIsMinMonthlyAvgBalDisplay() {
		return isMinMonthlyAvgBalDisplay;
	}

	public void setIsMinMonthlyAvgBalDisplay(Boolean isMinMonthlyAvgBalDisplay) {
		this.isMinMonthlyAvgBalDisplay = isMinMonthlyAvgBalDisplay;
	}

	public Boolean getIsMinMonthlyAvgBalMandatory() {
		return isMinMonthlyAvgBalMandatory;
	}

	public void setIsMinMonthlyAvgBalMandatory(Boolean isMinMonthlyAvgBalMandatory) {
		this.isMinMonthlyAvgBalMandatory = isMinMonthlyAvgBalMandatory;
	}

	public BigDecimal getMaxMonthlyAvgBal() {
		return maxMonthlyAvgBal;
	}

	public void setMaxMonthlyAvgBal(BigDecimal maxMonthlyAvgBal) {
		this.maxMonthlyAvgBal = maxMonthlyAvgBal;
	}

	public BigDecimal getMinMonthlyAvgBal() {
		return minMonthlyAvgBal;
	}

	public void setMinMonthlyAvgBal(BigDecimal minMonthlyAvgBal) {
		this.minMonthlyAvgBal = minMonthlyAvgBal;
	}

	public Boolean getIsMinNoDebitTransactionDisplay() {
		return isMinNoDebitTransactionDisplay;
	}

	public void setIsMinNoDebitTransactionDisplay(Boolean isMinNoDebitTransactionDisplay) {
		this.isMinNoDebitTransactionDisplay = isMinNoDebitTransactionDisplay;
	}

	public Boolean getIsMinNoDebitTransactionMandatory() {
		return isMinNoDebitTransactionMandatory;
	}

	public void setIsMinNoDebitTransactionMandatory(Boolean isMinNoDebitTransactionMandatory) {
		this.isMinNoDebitTransactionMandatory = isMinNoDebitTransactionMandatory;
	}

	public BigDecimal getMaxNoDebitTransaction() {
		return maxNoDebitTransaction;
	}

	public void setMaxNoDebitTransaction(BigDecimal maxNoDebitTransaction) {
		this.maxNoDebitTransaction = maxNoDebitTransaction;
	}

	public BigDecimal getMinNoDebitTransaction() {
		return minNoDebitTransaction;
	}

	public void setMinNoDebitTransaction(BigDecimal minNoDebitTransaction) {
		this.minNoDebitTransaction = minNoDebitTransaction;
	}

	public List<Integer> getConstitutionIds() {
		return constitutionIds;
	}

	public void setConstitutionIds(List<Integer> constitutionIds) {
		this.constitutionIds = constitutionIds;
	}

	public Boolean getIsConstitutionDisplay() {
		return isConstitutionDisplay;
	}

	public void setIsConstitutionDisplay(Boolean isConstitutionDisplay) {
		this.isConstitutionDisplay = isConstitutionDisplay;
	}

	public Boolean getIsConstitutionMandatory() {
		return isConstitutionMandatory;
	}

	public void setIsConstitutionMandatory(Boolean isConstitutionMandatory) {
		this.isConstitutionMandatory = isConstitutionMandatory;
	}

	public Boolean getIsMsmeRankingDisplay() {
		return isMsmeRankingDisplay;
	}

	public void setIsMsmeRankingDisplay(Boolean isMsmeRankingDisplay) {
		this.isMsmeRankingDisplay = isMsmeRankingDisplay;
	}

	public Boolean getIsMsmeRankingMandatory() {
		return isMsmeRankingMandatory;
	}

	public void setIsMsmeRankingMandatory(Boolean isMsmeRankingMandatory) {
		this.isMsmeRankingMandatory = isMsmeRankingMandatory;
	}

	public Integer getMsmeRanking() {
		return msmeRanking;
	}

	public void setMsmeRanking(Integer msmeRanking) {
		this.msmeRanking = msmeRanking;
	}

	public Boolean getIsNoMsmeRanking() {
		return isNoMsmeRanking;
	}

	public void setIsNoMsmeRanking(Boolean isNoMsmeRanking) {
		this.isNoMsmeRanking = isNoMsmeRanking;
	}

	public Integer getIndividualCibilFor3Month() {
		return individualCibilFor3Month;
	}

	public void setIndividualCibilFor3Month(Integer individualCibilFor3Month) {
		this.individualCibilFor3Month = individualCibilFor3Month;
	}

	public Boolean getIsIndividualCibilFor3MonthDisplay() {
		return isIndividualCibilFor3MonthDisplay;
	}

	public void setIsIndividualCibilFor3MonthDisplay(Boolean isIndividualCibilFor3MonthDisplay) {
		this.isIndividualCibilFor3MonthDisplay = isIndividualCibilFor3MonthDisplay;
	}

	public Boolean getIsIndividualCibilFor3MonthMandatory() {
		return isIndividualCibilFor3MonthMandatory;
	}

	public void setIsIndividualCibilFor3MonthMandatory(Boolean isIndividualCibilFor3MonthMandatory) {
		this.isIndividualCibilFor3MonthMandatory = isIndividualCibilFor3MonthMandatory;
	}

	public Integer getIndividualCibilDpd() {
		return individualCibilDpd;
	}

	public void setIndividualCibilDpd(Integer individualCibilDpd) {
		this.individualCibilDpd = individualCibilDpd;
	}

	public Boolean getIsIndividualCibilDpdDisplay() {
		return isIndividualCibilDpdDisplay;
	}

	public void setIsIndividualCibilDpdDisplay(Boolean isIndividualCibilDpdDisplay) {
		this.isIndividualCibilDpdDisplay = isIndividualCibilDpdDisplay;
	}

	public Boolean getIsIndividualCibilDpdMandatory() {
		return isIndividualCibilDpdMandatory;
	}

	public void setIsIndividualCibilDpdMandatory(Boolean isIndividualCibilDpdMandatory) {
		this.isIndividualCibilDpdMandatory = isIndividualCibilDpdMandatory;
	}

	public List<Integer> getBureauScoreIds() {
		return bureauScoreIds;
	}

	public void setBureauScoreIds(List<Integer> bureauScoreIds) {
		this.bureauScoreIds = bureauScoreIds;

	}

	public List<Integer> getMainDirBureauScoreIds() {
		return mainDirBureauScoreIds;
	}

	public void setMainDirBureauScoreIds(List<Integer> mainDirBureauScoreIds) {
		this.mainDirBureauScoreIds = mainDirBureauScoreIds;
	}

	public List<FPParameterMappingRequest> getRiskLoanAmountList() {
		return riskLoanAmountList;
	}

	public void setRiskLoanAmountList(List<FPParameterMappingRequest> riskLoanAmountList) {
		this.riskLoanAmountList = riskLoanAmountList;
	}

	public BigDecimal getPcWomen() {
		return pcWomen;
	}

	public void setPcWomen(BigDecimal pcWomen) {
		this.pcWomen = pcWomen;
	}

	public BigDecimal getPcMinorityCommunity() {
		return pcMinorityCommunity;
	}

	public void setPcMinorityCommunity(BigDecimal pcMinorityCommunity) {
		this.pcMinorityCommunity = pcMinorityCommunity;
	}
	
	
}
