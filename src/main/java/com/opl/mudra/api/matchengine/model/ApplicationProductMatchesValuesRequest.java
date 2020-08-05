package com.opl.mudra.api.matchengine.model;

import java.io.Serializable;
import java.util.Date;

public class ApplicationProductMatchesValuesRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2113663537310071721L;
	private Long id;
	private Long applicationId;
	private Long fpProdId;
	private Long productId;
	private String fpTableName;
	private Integer itrYear;
	private Boolean isTolTnwMatch;
	private String tolTnwMatchMaxFS;
	private String tolTnwMatchMinFP;
	private String tolTnwMatchMaxFP;
	private Boolean isDebtEquityMatch;
	private String debtEquityValFS;
	private String debtEquityValMinFP;
	private String debtEquityValMaxFP;
	private Boolean isCustomerConcentrationMatch;
	private String customerConcentrationFS;
	private String minCustomerConcentration;
	private String maxCustomerConcentration;
	private Boolean isIndustrySectorMatch;
	private String industryFS;
	private String industryFP;
	private Boolean isGeoGraphicalFocusMatch;
	private String geoGraphicalFocusFS;
	private String geoGraphicalFocusFP;
	private Boolean isCurrentRatioMatch;
	private String currentRatioFS;
	private String currentRatioMinFP;
	private String currentRatioMaxFP;
	private Boolean isInterestCoverageRatioMatch;
	private String interestCoverageRatioFS;
	private String interestCoverageRatioMinFP;
	private String interestCoverageRatioMaxFP;
	private Boolean isPastYearTurnOverMatch;
	private String pastYearTurnOverFS;
	private String pastYearTurnOverMinFP;
	private String pastYearTurnOverMaxFP;
	private Boolean isAgeOfEstablishmentMatch;
	private String ageOfEstablishmentFS;
	private String ageOfEstablishmentMinFP;
	private String ageOfEstablishmentMaxFP;
	private Boolean isProfitablityHistoryMatch;
	private String profitablityHistoryFS;
	private String profitablityHistoryFP;
	private Boolean isNetworthMatch;
	private String networthFS;
	private String networthFP;
	private Boolean isChequeBounceLastOneMonthMatch;
	private String chequeBounceLastOneMonthFS;
	private String chequeBounceLastOneMonthMinFP;
	private String chequeBounceLastOneMonthMaxFP;
	private Boolean isChequeBounceLastSixMonthMatch;
	private String chequeBounceLastSixMonthFS;
	private String chequeBounceLastSixMonthMinFP;
	private String chequeBounceLastSixMonthMaxFP;
	private Boolean isIndividualCibilMatch;
	private String individualCibilFS;
	private String individualCibilFP;
	private Boolean isCommercialCibilMatch;
	private String commercialCibilFS;
	private String commercialCibilFP;
	private Boolean isInvestmentSizeMatch;
	private String investmentSizeFS;
	private String investmentSizeMinFP;
	private String investmentSizeMaxFP;
	private Boolean isTenureMatch;
	private String tenureFS;
	private String tenureMinFP;
	private String tenureMaxFP;
	private Boolean isScoreMatch;
	private String scoreFS;
	private String scoreMinFP;
	private String scoreMaxFP;
	private Boolean isColleteralCoverageMatch;
	private String colleteralCoverageFS;
	private String colleteralCoverageMinFP;
	private String colleteralCoverageMaxFP;
	private Boolean isCgtmseCoverageMatch;
	private String cgtmseCoverageFS;
	private String cgtmseCoverageFP;
	private Boolean isMsmeFundingMatch;
	private String msmeFundingFS;
	private String msmeFundingFP;
	private Boolean isTurnOverMatch;
	private String turnOverFS;
	private String turnOverMinFP;
	private String turnOverMaxFP;
	private Boolean isGrossCashAccuralMatch;
	private String grossCashAccuralFS;
	private String grossCashAccuralMinFP;
	private String grossCashAccuralMaxFP;
	private Boolean isIndustrySectorDisplay;
	private Boolean isIndustrySectorMandatory;
	private Boolean isInvestmentSizeDisplay;
	private Boolean isInvestmentSizeMandatory;
	private Boolean isTenureDisplay;
	private Boolean isTenureMandatory;
	private Boolean isGeographicalDisplay;
	private Boolean isGeographicalMandatory;
	private Boolean isCreditratingDisplay;
	private Boolean isCreditratingMandatory;
	private Boolean isEstablishmentDisplay;
	private Boolean isEstablishmentMandatory;
	private Boolean isProfitabilityHistoryDisplay;
	private Boolean isProfitabilityHistoryMandatory;
	private Boolean isPastYearTurnoverDisplay;
	private Boolean isPastYearTurnoverMandatory;
	private Boolean isDebtEquityDisplay;
	private Boolean isDebtEquityMandatory;
	private Boolean isCollateralDisplay;
	private Boolean isCollateralMandatory;
	private Boolean isNetworthDisplay;
	private Boolean isNetworthMandatory;
	private Boolean isCurrentratioDisplay;
	private Boolean isCurrentratioMandatory;
	private Boolean isInterestCoverageDisplay;
	private Boolean isInterestCoverageMandatory;
	private Boolean isTolTnwDisplay;
	private Boolean isTolTnwMandatory;
	private Boolean isTurnOverRatioDisplay;
	private Boolean isTurnOverRatioMandatory;
	private Boolean isGrossCashAccuralsRatioDisplay;
	private Boolean isGrossCashAccuralsRatioMandatory;
	private Boolean isCustomerConcentrationDisplay;
	private Boolean isCustomerConcentrationMandatory;
	private Boolean isRiskModelScoreDisplay;
	private Boolean isRiskModelScoreMandatory;
	private Boolean isChequeBouncedDisplay;
	private Boolean isChequeBouncedMandatory;
	private Boolean isChequeBouncedLastSixMonthsDisplay;
	private Boolean isChequeBouncedLastSixMonthsMandatory;
	private Boolean isIndividualCibilDisplay;
	private Boolean isIndividualCibilMandatory;
	private Boolean isCommercialCibilDisplay;
	private Boolean isCommercialCibilMandatory;
	private Boolean isCgtmseCoverageDisplay;
	private Boolean isCgtmseCoverageMandatory;
	private Boolean isMsmeFundingDisplay;
	private Boolean isMsmeFundingMandatory;
	private Date matchCalculatedDate;
	private Date matchDataStoredDate;

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

	public Long getFpProdId() {
		return fpProdId;
	}

	public void setFpProdId(Long fpProdId) {
		this.fpProdId = fpProdId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getFpTableName() {
		return fpTableName;
	}

	public void setFpTableName(String fpTableName) {
		this.fpTableName = fpTableName;
	}

	public Integer getItrYear() {
		return itrYear;
	}

	public void setItrYear(Integer itrYear) {
		this.itrYear = itrYear;
	}

	public Boolean getIsTolTnwMatch() {
		return isTolTnwMatch;
	}

	public void setIsTolTnwMatch(Boolean isTolTnwMatch) {
		this.isTolTnwMatch = isTolTnwMatch;
	}

	public String getTolTnwMatchMaxFS() {
		return tolTnwMatchMaxFS;
	}

	public void setTolTnwMatchMaxFS(String tolTnwMatchMaxFS) {
		this.tolTnwMatchMaxFS = tolTnwMatchMaxFS;
	}

	public String getTolTnwMatchMinFP() {
		return tolTnwMatchMinFP;
	}

	public void setTolTnwMatchMinFP(String tolTnwMatchMinFP) {
		this.tolTnwMatchMinFP = tolTnwMatchMinFP;
	}

	public String getTolTnwMatchMaxFP() {
		return tolTnwMatchMaxFP;
	}

	public void setTolTnwMatchMaxFP(String tolTnwMatchMaxFP) {
		this.tolTnwMatchMaxFP = tolTnwMatchMaxFP;
	}

	public Boolean getIsDebtEquityMatch() {
		return isDebtEquityMatch;
	}

	public void setIsDebtEquityMatch(Boolean isDebtEquityMatch) {
		this.isDebtEquityMatch = isDebtEquityMatch;
	}

	public String getDebtEquityValFS() {
		return debtEquityValFS;
	}

	public void setDebtEquityValFS(String debtEquityValFS) {
		this.debtEquityValFS = debtEquityValFS;
	}

	public String getDebtEquityValMinFP() {
		return debtEquityValMinFP;
	}

	public void setDebtEquityValMinFP(String debtEquityValMinFP) {
		this.debtEquityValMinFP = debtEquityValMinFP;
	}

	public String getDebtEquityValMaxFP() {
		return debtEquityValMaxFP;
	}

	public void setDebtEquityValMaxFP(String debtEquityValMaxFP) {
		this.debtEquityValMaxFP = debtEquityValMaxFP;
	}

	public Boolean getIsCustomerConcentrationMatch() {
		return isCustomerConcentrationMatch;
	}

	public void setIsCustomerConcentrationMatch(Boolean isCustomerConcentrationMatch) {
		this.isCustomerConcentrationMatch = isCustomerConcentrationMatch;
	}

	public String getCustomerConcentrationFS() {
		return customerConcentrationFS;
	}

	public void setCustomerConcentrationFS(String customerConcentrationFS) {
		this.customerConcentrationFS = customerConcentrationFS;
	}

	public String getMinCustomerConcentration() {
		return minCustomerConcentration;
	}

	public void setMinCustomerConcentration(String minCustomerConcentration) {
		this.minCustomerConcentration = minCustomerConcentration;
	}

	public String getMaxCustomerConcentration() {
		return maxCustomerConcentration;
	}

	public void setMaxCustomerConcentration(String maxCustomerConcentration) {
		this.maxCustomerConcentration = maxCustomerConcentration;
	}

	public Boolean getIsIndustrySectorMatch() {
		return isIndustrySectorMatch;
	}

	public void setIsIndustrySectorMatch(Boolean isIndustrySectorMatch) {
		this.isIndustrySectorMatch = isIndustrySectorMatch;
	}

	public String getIndustryFS() {
		return industryFS;
	}

	public void setIndustryFS(String industryFS) {
		this.industryFS = industryFS;
	}

	public String getIndustryFP() {
		return industryFP;
	}

	public void setIndustryFP(String industryFP) {
		this.industryFP = industryFP;
	}

	public Boolean getIsGeoGraphicalFocusMatch() {
		return isGeoGraphicalFocusMatch;
	}

	public void setIsGeoGraphicalFocusMatch(Boolean isGeoGraphicalFocusMatch) {
		this.isGeoGraphicalFocusMatch = isGeoGraphicalFocusMatch;
	}

	public String getGeoGraphicalFocusFS() {
		return geoGraphicalFocusFS;
	}

	public void setGeoGraphicalFocusFS(String geoGraphicalFocusFS) {
		this.geoGraphicalFocusFS = geoGraphicalFocusFS;
	}

	public String getGeoGraphicalFocusFP() {
		return geoGraphicalFocusFP;
	}

	public void setGeoGraphicalFocusFP(String geoGraphicalFocusFP) {
		this.geoGraphicalFocusFP = geoGraphicalFocusFP;
	}

	public Boolean getIsCurrentRatioMatch() {
		return isCurrentRatioMatch;
	}

	public void setIsCurrentRatioMatch(Boolean isCurrentRatioMatch) {
		this.isCurrentRatioMatch = isCurrentRatioMatch;
	}

	public String getCurrentRatioFS() {
		return currentRatioFS;
	}

	public void setCurrentRatioFS(String currentRatioFS) {
		this.currentRatioFS = currentRatioFS;
	}

	public String getCurrentRatioMinFP() {
		return currentRatioMinFP;
	}

	public void setCurrentRatioMinFP(String currentRatioMinFP) {
		this.currentRatioMinFP = currentRatioMinFP;
	}

	public String getCurrentRatioMaxFP() {
		return currentRatioMaxFP;
	}

	public void setCurrentRatioMaxFP(String currentRatioMaxFP) {
		this.currentRatioMaxFP = currentRatioMaxFP;
	}

	public Boolean getIsInterestCoverageRatioMatch() {
		return isInterestCoverageRatioMatch;
	}

	public void setIsInterestCoverageRatioMatch(Boolean isInterestCoverageRatioMatch) {
		this.isInterestCoverageRatioMatch = isInterestCoverageRatioMatch;
	}

	public String getInterestCoverageRatioFS() {
		return interestCoverageRatioFS;
	}

	public void setInterestCoverageRatioFS(String interestCoverageRatioFS) {
		this.interestCoverageRatioFS = interestCoverageRatioFS;
	}

	public String getInterestCoverageRatioMinFP() {
		return interestCoverageRatioMinFP;
	}

	public void setInterestCoverageRatioMinFP(String interestCoverageRatioMinFP) {
		this.interestCoverageRatioMinFP = interestCoverageRatioMinFP;
	}

	public String getInterestCoverageRatioMaxFP() {
		return interestCoverageRatioMaxFP;
	}

	public void setInterestCoverageRatioMaxFP(String interestCoverageRatioMaxFP) {
		this.interestCoverageRatioMaxFP = interestCoverageRatioMaxFP;
	}

	public Boolean getIsPastYearTurnOverMatch() {
		return isPastYearTurnOverMatch;
	}

	public void setIsPastYearTurnOverMatch(Boolean isPastYearTurnOverMatch) {
		this.isPastYearTurnOverMatch = isPastYearTurnOverMatch;
	}

	public String getPastYearTurnOverFS() {
		return pastYearTurnOverFS;
	}

	public void setPastYearTurnOverFS(String pastYearTurnOverFS) {
		this.pastYearTurnOverFS = pastYearTurnOverFS;
	}

	public String getPastYearTurnOverMinFP() {
		return pastYearTurnOverMinFP;
	}

	public void setPastYearTurnOverMinFP(String pastYearTurnOverMinFP) {
		this.pastYearTurnOverMinFP = pastYearTurnOverMinFP;
	}

	public String getPastYearTurnOverMaxFP() {
		return pastYearTurnOverMaxFP;
	}

	public void setPastYearTurnOverMaxFP(String pastYearTurnOverMaxFP) {
		this.pastYearTurnOverMaxFP = pastYearTurnOverMaxFP;
	}

	public Boolean getIsAgeOfEstablishmentMatch() {
		return isAgeOfEstablishmentMatch;
	}

	public void setIsAgeOfEstablishmentMatch(Boolean isAgeOfEstablishmentMatch) {
		this.isAgeOfEstablishmentMatch = isAgeOfEstablishmentMatch;
	}

	public String getAgeOfEstablishmentFS() {
		return ageOfEstablishmentFS;
	}

	public void setAgeOfEstablishmentFS(String ageOfEstablishmentFS) {
		this.ageOfEstablishmentFS = ageOfEstablishmentFS;
	}

	public String getAgeOfEstablishmentMinFP() {
		return ageOfEstablishmentMinFP;
	}

	public void setAgeOfEstablishmentMinFP(String ageOfEstablishmentMinFP) {
		this.ageOfEstablishmentMinFP = ageOfEstablishmentMinFP;
	}

	public String getAgeOfEstablishmentMaxFP() {
		return ageOfEstablishmentMaxFP;
	}

	public void setAgeOfEstablishmentMaxFP(String ageOfEstablishmentMaxFP) {
		this.ageOfEstablishmentMaxFP = ageOfEstablishmentMaxFP;
	}

	public Boolean getIsProfitablityHistoryMatch() {
		return isProfitablityHistoryMatch;
	}

	public void setIsProfitablityHistoryMatch(Boolean isProfitablityHistoryMatch) {
		this.isProfitablityHistoryMatch = isProfitablityHistoryMatch;
	}

	public String getProfitablityHistoryFS() {
		return profitablityHistoryFS;
	}

	public void setProfitablityHistoryFS(String profitablityHistoryFS) {
		this.profitablityHistoryFS = profitablityHistoryFS;
	}

	public String getProfitablityHistoryFP() {
		return profitablityHistoryFP;
	}

	public void setProfitablityHistoryFP(String profitablityHistoryFP) {
		this.profitablityHistoryFP = profitablityHistoryFP;
	}

	public Boolean getIsNetworthMatch() {
		return isNetworthMatch;
	}

	public void setIsNetworthMatch(Boolean isNetworthMatch) {
		this.isNetworthMatch = isNetworthMatch;
	}

	public String getNetworthFS() {
		return networthFS;
	}

	public void setNetworthFS(String networthFS) {
		this.networthFS = networthFS;
	}

	public String getNetworthFP() {
		return networthFP;
	}

	public void setNetworthFP(String networthFP) {
		this.networthFP = networthFP;
	}

	public Boolean getIsChequeBounceLastOneMonthMatch() {
		return isChequeBounceLastOneMonthMatch;
	}

	public void setIsChequeBounceLastOneMonthMatch(Boolean isChequeBounceLastOneMonthMatch) {
		this.isChequeBounceLastOneMonthMatch = isChequeBounceLastOneMonthMatch;
	}

	public String getChequeBounceLastOneMonthFS() {
		return chequeBounceLastOneMonthFS;
	}

	public void setChequeBounceLastOneMonthFS(String chequeBounceLastOneMonthFS) {
		this.chequeBounceLastOneMonthFS = chequeBounceLastOneMonthFS;
	}

	public String getChequeBounceLastOneMonthMinFP() {
		return chequeBounceLastOneMonthMinFP;
	}

	public void setChequeBounceLastOneMonthMinFP(String chequeBounceLastOneMonthMinFP) {
		this.chequeBounceLastOneMonthMinFP = chequeBounceLastOneMonthMinFP;
	}

	public String getChequeBounceLastOneMonthMaxFP() {
		return chequeBounceLastOneMonthMaxFP;
	}

	public void setChequeBounceLastOneMonthMaxFP(String chequeBounceLastOneMonthMaxFP) {
		this.chequeBounceLastOneMonthMaxFP = chequeBounceLastOneMonthMaxFP;
	}

	public Boolean getIsChequeBounceLastSixMonthMatch() {
		return isChequeBounceLastSixMonthMatch;
	}

	public void setIsChequeBounceLastSixMonthMatch(Boolean isChequeBounceLastSixMonthMatch) {
		this.isChequeBounceLastSixMonthMatch = isChequeBounceLastSixMonthMatch;
	}

	public String getChequeBounceLastSixMonthFS() {
		return chequeBounceLastSixMonthFS;
	}

	public void setChequeBounceLastSixMonthFS(String chequeBounceLastSixMonthFS) {
		this.chequeBounceLastSixMonthFS = chequeBounceLastSixMonthFS;
	}

	public String getChequeBounceLastSixMonthMinFP() {
		return chequeBounceLastSixMonthMinFP;
	}

	public void setChequeBounceLastSixMonthMinFP(String chequeBounceLastSixMonthMinFP) {
		this.chequeBounceLastSixMonthMinFP = chequeBounceLastSixMonthMinFP;
	}

	public String getChequeBounceLastSixMonthMaxFP() {
		return chequeBounceLastSixMonthMaxFP;
	}

	public void setChequeBounceLastSixMonthMaxFP(String chequeBounceLastSixMonthMaxFP) {
		this.chequeBounceLastSixMonthMaxFP = chequeBounceLastSixMonthMaxFP;
	}

	public Boolean getIsIndividualCibilMatch() {
		return isIndividualCibilMatch;
	}

	public void setIsIndividualCibilMatch(Boolean isIndividualCibilMatch) {
		this.isIndividualCibilMatch = isIndividualCibilMatch;
	}

	public String getIndividualCibilFS() {
		return individualCibilFS;
	}

	public void setIndividualCibilFS(String individualCibilFS) {
		this.individualCibilFS = individualCibilFS;
	}

	public String getIndividualCibilFP() {
		return individualCibilFP;
	}

	public void setIndividualCibilFP(String individualCibilFP) {
		this.individualCibilFP = individualCibilFP;
	}

	public Boolean getIsCommercialCibilMatch() {
		return isCommercialCibilMatch;
	}

	public void setIsCommercialCibilMatch(Boolean isCommercialCibilMatch) {
		this.isCommercialCibilMatch = isCommercialCibilMatch;
	}

	public String getCommercialCibilFS() {
		return commercialCibilFS;
	}

	public void setCommercialCibilFS(String commercialCibilFS) {
		this.commercialCibilFS = commercialCibilFS;
	}

	public String getCommercialCibilFP() {
		return commercialCibilFP;
	}

	public void setCommercialCibilFP(String commercialCibilFP) {
		this.commercialCibilFP = commercialCibilFP;
	}

	public Boolean getIsInvestmentSizeMatch() {
		return isInvestmentSizeMatch;
	}

	public void setIsInvestmentSizeMatch(Boolean isInvestmentSizeMatch) {
		this.isInvestmentSizeMatch = isInvestmentSizeMatch;
	}

	public String getInvestmentSizeFS() {
		return investmentSizeFS;
	}

	public void setInvestmentSizeFS(String investmentSizeFS) {
		this.investmentSizeFS = investmentSizeFS;
	}

	public String getInvestmentSizeMinFP() {
		return investmentSizeMinFP;
	}

	public void setInvestmentSizeMinFP(String investmentSizeMinFP) {
		this.investmentSizeMinFP = investmentSizeMinFP;
	}

	public String getInvestmentSizeMaxFP() {
		return investmentSizeMaxFP;
	}

	public void setInvestmentSizeMaxFP(String investmentSizeMaxFP) {
		this.investmentSizeMaxFP = investmentSizeMaxFP;
	}

	public Boolean getIsTenureMatch() {
		return isTenureMatch;
	}

	public void setIsTenureMatch(Boolean isTenureMatch) {
		this.isTenureMatch = isTenureMatch;
	}

	public String getTenureFS() {
		return tenureFS;
	}

	public void setTenureFS(String tenureFS) {
		this.tenureFS = tenureFS;
	}

	public String getTenureMinFP() {
		return tenureMinFP;
	}

	public void setTenureMinFP(String tenureMinFP) {
		this.tenureMinFP = tenureMinFP;
	}

	public String getTenureMaxFP() {
		return tenureMaxFP;
	}

	public void setTenureMaxFP(String tenureMaxFP) {
		this.tenureMaxFP = tenureMaxFP;
	}

	public Boolean getIsScoreMatch() {
		return isScoreMatch;
	}

	public void setIsScoreMatch(Boolean isScoreMatch) {
		this.isScoreMatch = isScoreMatch;
	}

	public String getScoreFS() {
		return scoreFS;
	}

	public void setScoreFS(String scoreFS) {
		this.scoreFS = scoreFS;
	}

	public String getScoreMinFP() {
		return scoreMinFP;
	}

	public void setScoreMinFP(String scoreMinFP) {
		this.scoreMinFP = scoreMinFP;
	}

	public String getScoreMaxFP() {
		return scoreMaxFP;
	}

	public void setScoreMaxFP(String scoreMaxFP) {
		this.scoreMaxFP = scoreMaxFP;
	}

	public Boolean getIsColleteralCoverageMatch() {
		return isColleteralCoverageMatch;
	}

	public void setIsColleteralCoverageMatch(Boolean isColleteralCoverageMatch) {
		this.isColleteralCoverageMatch = isColleteralCoverageMatch;
	}

	public String getColleteralCoverageFS() {
		return colleteralCoverageFS;
	}

	public void setColleteralCoverageFS(String colleteralCoverageFS) {
		this.colleteralCoverageFS = colleteralCoverageFS;
	}

	public String getColleteralCoverageMinFP() {
		return colleteralCoverageMinFP;
	}

	public void setColleteralCoverageMinFP(String colleteralCoverageMinFP) {
		this.colleteralCoverageMinFP = colleteralCoverageMinFP;
	}

	public String getColleteralCoverageMaxFP() {
		return colleteralCoverageMaxFP;
	}

	public void setColleteralCoverageMaxFP(String colleteralCoverageMaxFP) {
		this.colleteralCoverageMaxFP = colleteralCoverageMaxFP;
	}

	public Boolean getIsCgtmseCoverageMatch() {
		return isCgtmseCoverageMatch;
	}

	public void setIsCgtmseCoverageMatch(Boolean isCgtmseCoverageMatch) {
		this.isCgtmseCoverageMatch = isCgtmseCoverageMatch;
	}

	public String getCgtmseCoverageFS() {
		return cgtmseCoverageFS;
	}

	public void setCgtmseCoverageFS(String cgtmseCoverageFS) {
		this.cgtmseCoverageFS = cgtmseCoverageFS;
	}

	public String getCgtmseCoverageFP() {
		return cgtmseCoverageFP;
	}

	public void setCgtmseCoverageFP(String cgtmseCoverageFP) {
		this.cgtmseCoverageFP = cgtmseCoverageFP;
	}

	public Boolean getIsMsmeFundingMatch() {
		return isMsmeFundingMatch;
	}

	public void setIsMsmeFundingMatch(Boolean isMsmeFundingMatch) {
		this.isMsmeFundingMatch = isMsmeFundingMatch;
	}

	public String getMsmeFundingFS() {
		return msmeFundingFS;
	}

	public void setMsmeFundingFS(String msmeFundingFS) {
		this.msmeFundingFS = msmeFundingFS;
	}

	public String getMsmeFundingFP() {
		return msmeFundingFP;
	}

	public void setMsmeFundingFP(String msmeFundingFP) {
		this.msmeFundingFP = msmeFundingFP;
	}

	public Boolean getIsTurnOverMatch() {
		return isTurnOverMatch;
	}

	public void setIsTurnOverMatch(Boolean isTurnOverMatch) {
		this.isTurnOverMatch = isTurnOverMatch;
	}

	public String getTurnOverFS() {
		return turnOverFS;
	}

	public void setTurnOverFS(String turnOverFS) {
		this.turnOverFS = turnOverFS;
	}

	public String getTurnOverMinFP() {
		return turnOverMinFP;
	}

	public void setTurnOverMinFP(String turnOverMinFP) {
		this.turnOverMinFP = turnOverMinFP;
	}

	public String getTurnOverMaxFP() {
		return turnOverMaxFP;
	}

	public void setTurnOverMaxFP(String turnOverMaxFP) {
		this.turnOverMaxFP = turnOverMaxFP;
	}

	public Boolean getIsGrossCashAccuralMatch() {
		return isGrossCashAccuralMatch;
	}

	public void setIsGrossCashAccuralMatch(Boolean isGrossCashAccuralMatch) {
		this.isGrossCashAccuralMatch = isGrossCashAccuralMatch;
	}

	public String getGrossCashAccuralFS() {
		return grossCashAccuralFS;
	}

	public void setGrossCashAccuralFS(String grossCashAccuralFS) {
		this.grossCashAccuralFS = grossCashAccuralFS;
	}

	public String getGrossCashAccuralMinFP() {
		return grossCashAccuralMinFP;
	}

	public void setGrossCashAccuralMinFP(String grossCashAccuralMinFP) {
		this.grossCashAccuralMinFP = grossCashAccuralMinFP;
	}

	public String getGrossCashAccuralMaxFP() {
		return grossCashAccuralMaxFP;
	}

	public void setGrossCashAccuralMaxFP(String grossCashAccuralMaxFP) {
		this.grossCashAccuralMaxFP = grossCashAccuralMaxFP;
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

	public Boolean getIsCreditratingDisplay() {
		return isCreditratingDisplay;
	}

	public void setIsCreditratingDisplay(Boolean isCreditratingDisplay) {
		this.isCreditratingDisplay = isCreditratingDisplay;
	}

	public Boolean getIsCreditratingMandatory() {
		return isCreditratingMandatory;
	}

	public void setIsCreditratingMandatory(Boolean isCreditratingMandatory) {
		this.isCreditratingMandatory = isCreditratingMandatory;
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

	public Boolean getIsCurrentratioDisplay() {
		return isCurrentratioDisplay;
	}

	public void setIsCurrentratioDisplay(Boolean isCurrentratioDisplay) {
		this.isCurrentratioDisplay = isCurrentratioDisplay;
	}

	public Boolean getIsCurrentratioMandatory() {
		return isCurrentratioMandatory;
	}

	public void setIsCurrentratioMandatory(Boolean isCurrentratioMandatory) {
		this.isCurrentratioMandatory = isCurrentratioMandatory;
	}

	public Boolean getIsInterestCoverageDisplay() {
		return isInterestCoverageDisplay;
	}

	public void setIsInterestCoverageDisplay(Boolean isInterestCoverageDisplay) {
		this.isInterestCoverageDisplay = isInterestCoverageDisplay;
	}

	public Boolean getIsInterestCoverageMandatory() {
		return isInterestCoverageMandatory;
	}

	public void setIsInterestCoverageMandatory(Boolean isInterestCoverageMandatory) {
		this.isInterestCoverageMandatory = isInterestCoverageMandatory;
	}

	public Boolean getIsTolTnwDisplay() {
		return isTolTnwDisplay;
	}

	public void setIsTolTnwDisplay(Boolean isTolTnwDisplay) {
		this.isTolTnwDisplay = isTolTnwDisplay;
	}

	public Boolean getIsTolTnwMandatory() {
		return isTolTnwMandatory;
	}

	public void setIsTolTnwMandatory(Boolean isTolTnwMandatory) {
		this.isTolTnwMandatory = isTolTnwMandatory;
	}

	public Boolean getIsTurnOverRatioDisplay() {
		return isTurnOverRatioDisplay;
	}

	public void setIsTurnOverRatioDisplay(Boolean isTurnOverRatioDisplay) {
		this.isTurnOverRatioDisplay = isTurnOverRatioDisplay;
	}

	public Boolean getIsTurnOverRatioMandatory() {
		return isTurnOverRatioMandatory;
	}

	public void setIsTurnOverRatioMandatory(Boolean isTurnOverRatioMandatory) {
		this.isTurnOverRatioMandatory = isTurnOverRatioMandatory;
	}

	public Boolean getIsGrossCashAccuralsRatioDisplay() {
		return isGrossCashAccuralsRatioDisplay;
	}

	public void setIsGrossCashAccuralsRatioDisplay(Boolean isGrossCashAccuralsRatioDisplay) {
		this.isGrossCashAccuralsRatioDisplay = isGrossCashAccuralsRatioDisplay;
	}

	public Boolean getIsGrossCashAccuralsRatioMandatory() {
		return isGrossCashAccuralsRatioMandatory;
	}

	public void setIsGrossCashAccuralsRatioMandatory(Boolean isGrossCashAccuralsRatioMandatory) {
		this.isGrossCashAccuralsRatioMandatory = isGrossCashAccuralsRatioMandatory;
	}

	public Boolean getIsCustomerConcentrationDisplay() {
		return isCustomerConcentrationDisplay;
	}

	public void setIsCustomerConcentrationDisplay(Boolean isCustomerConcentrationDisplay) {
		this.isCustomerConcentrationDisplay = isCustomerConcentrationDisplay;
	}

	public Boolean getIsCustomerConcentrationMandatory() {
		return isCustomerConcentrationMandatory;
	}

	public void setIsCustomerConcentrationMandatory(Boolean isCustomerConcentrationMandatory) {
		this.isCustomerConcentrationMandatory = isCustomerConcentrationMandatory;
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

	public Boolean getIsChequeBouncedDisplay() {
		return isChequeBouncedDisplay;
	}

	public void setIsChequeBouncedDisplay(Boolean isChequeBouncedDisplay) {
		this.isChequeBouncedDisplay = isChequeBouncedDisplay;
	}

	public Boolean getIsChequeBouncedMandatory() {
		return isChequeBouncedMandatory;
	}

	public void setIsChequeBouncedMandatory(Boolean isChequeBouncedMandatory) {
		this.isChequeBouncedMandatory = isChequeBouncedMandatory;
	}

	public Boolean getIsChequeBouncedLastSixMonthsDisplay() {
		return isChequeBouncedLastSixMonthsDisplay;
	}

	public void setIsChequeBouncedLastSixMonthsDisplay(Boolean isChequeBouncedLastSixMonthsDisplay) {
		this.isChequeBouncedLastSixMonthsDisplay = isChequeBouncedLastSixMonthsDisplay;
	}

	public Boolean getIsChequeBouncedLastSixMonthsMandatory() {
		return isChequeBouncedLastSixMonthsMandatory;
	}

	public void setIsChequeBouncedLastSixMonthsMandatory(Boolean isChequeBouncedLastSixMonthsMandatory) {
		this.isChequeBouncedLastSixMonthsMandatory = isChequeBouncedLastSixMonthsMandatory;
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

	public Boolean getIsCommercialCibilDisplay() {
		return isCommercialCibilDisplay;
	}

	public void setIsCommercialCibilDisplay(Boolean isCommercialCibilDisplay) {
		this.isCommercialCibilDisplay = isCommercialCibilDisplay;
	}

	public Boolean getIsCommercialCibilMandatory() {
		return isCommercialCibilMandatory;
	}

	public void setIsCommercialCibilMandatory(Boolean isCommercialCibilMandatory) {
		this.isCommercialCibilMandatory = isCommercialCibilMandatory;
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

	public Date getMatchCalculatedDate() {
		return matchCalculatedDate;
	}

	public void setMatchCalculatedDate(Date matchCalculatedDate) {
		this.matchCalculatedDate = matchCalculatedDate;
	}

	public Date getMatchDataStoredDate() {
		return matchDataStoredDate;
	}

	public void setMatchDataStoredDate(Date matchDataStoredDate) {
		this.matchDataStoredDate = matchDataStoredDate;
	}

}
