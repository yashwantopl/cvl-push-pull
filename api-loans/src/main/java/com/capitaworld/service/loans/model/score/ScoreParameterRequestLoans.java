package com.capitaworld.service.loans.model.score;

public class ScoreParameterRequestLoans {


    private Boolean isCombinedNetworth_p=true;
    private Boolean isCustomerAsscociateConcern_p=true;
    private Boolean isCibilTransunionScore_p=true;
    private Boolean isDebtEquityRatio_p=true;
    private Boolean isTolTnw_p=true;
    private Boolean isAvgCurrentRatio_p=true;
    private Boolean isWorkingCapitalCycle_p=true;
    private Boolean isAvgAnnualGrowthGrossCash_p=true;
    private Boolean isAvgAnnualGrowthNetSale_p=true;
    private Boolean isAvgEBIDTA_p=true;
    private Boolean isAvgAnnualGrossCashAccuruals_p=true;
    private Boolean isAvgInterestCovRatio_p=true;
    private Boolean isNoOfCustomer_p=true;
    private Boolean isConcentrationCustomer_p=true;
    private Boolean isExperienceInTheBusiness_p=true;
    private Boolean isCreditSummation_p=true;

    private Long testId;
    
	private Double networthSum;

    private Double termLoanTy;

    private Double loanAmount;


    private Double customerAssociateConcern;

    private Double cibilTransuniunScore;


    private Double Debt;

    private Double equity;


    private Double tol;

    private Double tnw;


    private Double avgCurrentRatio;


    private Double debtorsDays;

    private Double avgInventory;

    private Double cogs;

    private Double creditorsDays;


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


    private Double profitBeforeTaxOrLossSy;

    private Double profitBeforeTaxOrLossTy;


    private Double totalAsset;


    private Double opProfitBeforeInterestSy;

    private Double opProfitBeforeInterestTy;


    private Double noOfCustomenr;

    private Double concentrationCustomer;


    private Double experienceInTheBusiness;


    private Double totalCredit;


    private Double projectedSale;


    @Override
    public String toString() {
        return "ScoreParameterRequestLoans{" +
                "isCombinedNetworth_p=" + isCombinedNetworth_p +
                ", isCustomerAsscociateConcern_p=" + isCustomerAsscociateConcern_p +
                ", isCibilTransunionScore_p=" + isCibilTransunionScore_p +
                ", isDebtEquityRatio_p=" + isDebtEquityRatio_p +
                ", isTolTnw_p=" + isTolTnw_p +
                ", isAvgCurrentRatio_p=" + isAvgCurrentRatio_p +
                ", isWorkingCapitalCycle_p=" + isWorkingCapitalCycle_p +
                ", isAvgAnnualGrowthGrossCash_p=" + isAvgAnnualGrowthGrossCash_p +
                ", isAvgAnnualGrowthNetSale_p=" + isAvgAnnualGrowthNetSale_p +
                ", isAvgEBIDTA_p=" + isAvgEBIDTA_p +
                ", isAvgAnnualGrossCashAccuruals_p=" + isAvgAnnualGrossCashAccuruals_p +
                ", isAvgInterestCovRatio_p=" + isAvgInterestCovRatio_p +
                ", isNoOfCustomer_p=" + isNoOfCustomer_p +
                ", isConcentrationCustomer_p=" + isConcentrationCustomer_p +
                ", isExperienceInTheBusiness_p=" + isExperienceInTheBusiness_p +
                ", isCreditSummation_p=" + isCreditSummation_p +
                ", testId =" + testId +
                ", networthSum=" + networthSum +
                ", termLoanTy=" + termLoanTy +
                ", loanAmount=" + loanAmount +
                ", customerAssociateConcern=" + customerAssociateConcern +
                ", cibilTransuniunScore=" + cibilTransuniunScore +
                ", Debt=" + Debt +
                ", equity=" + equity +
                ", tol=" + tol +
                ", tnw=" + tnw +
                ", avgCurrentRatio=" + avgCurrentRatio +
                ", debtorsDays=" + debtorsDays +
                ", avgInventory=" + avgInventory +
                ", cogs=" + cogs +
                ", creditorsDays=" + creditorsDays +
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
                ", profitBeforeTaxOrLossSy=" + profitBeforeTaxOrLossSy +
                ", profitBeforeTaxOrLossTy=" + profitBeforeTaxOrLossTy +
                ", totalAsset=" + totalAsset +
                ", opProfitBeforeInterestSy=" + opProfitBeforeInterestSy +
                ", opProfitBeforeInterestTy=" + opProfitBeforeInterestTy +
                ", noOfCustomenr=" + noOfCustomenr +
                ", concentrationCustomer=" + concentrationCustomer +
                ", experienceInTheBusiness=" + experienceInTheBusiness +
                ", totalCredit=" + totalCredit +
                ", projectedSale=" + projectedSale +
                '}';
    }

    public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}
    public Boolean getCombinedNetworth_p() {
        return isCombinedNetworth_p;
    }

    public void setCombinedNetworth_p(Boolean combinedNetworth_p) {
        isCombinedNetworth_p = combinedNetworth_p;
    }

    public Boolean getCustomerAsscociateConcern_p() {
        return isCustomerAsscociateConcern_p;
    }

    public void setCustomerAsscociateConcern_p(Boolean customerAsscociateConcern_p) {
        isCustomerAsscociateConcern_p = customerAsscociateConcern_p;
    }

    public Boolean getCibilTransunionScore_p() {
        return isCibilTransunionScore_p;
    }

    public void setCibilTransunionScore_p(Boolean cibilTransunionScore_p) {
        isCibilTransunionScore_p = cibilTransunionScore_p;
    }

    public Boolean getDebtEquityRatio_p() {
        return isDebtEquityRatio_p;
    }

    public void setDebtEquityRatio_p(Boolean debtEquityRatio_p) {
        isDebtEquityRatio_p = debtEquityRatio_p;
    }

    public Boolean getTolTnw_p() {
        return isTolTnw_p;
    }

    public void setTolTnw_p(Boolean tolTnw_p) {
        isTolTnw_p = tolTnw_p;
    }

    public Boolean getAvgCurrentRatio_p() {
        return isAvgCurrentRatio_p;
    }

    public void setAvgCurrentRatio_p(Boolean avgCurrentRatio_p) {
        isAvgCurrentRatio_p = avgCurrentRatio_p;
    }

    public Boolean getWorkingCapitalCycle_p() {
        return isWorkingCapitalCycle_p;
    }

    public void setWorkingCapitalCycle_p(Boolean workingCapitalCycle_p) {
        isWorkingCapitalCycle_p = workingCapitalCycle_p;
    }

    public Boolean getAvgAnnualGrowthGrossCash_p() {
        return isAvgAnnualGrowthGrossCash_p;
    }

    public void setAvgAnnualGrowthGrossCash_p(Boolean avgAnnualGrowthGrossCash_p) {
        isAvgAnnualGrowthGrossCash_p = avgAnnualGrowthGrossCash_p;
    }

    public Boolean getAvgAnnualGrowthNetSale_p() {
        return isAvgAnnualGrowthNetSale_p;
    }

    public void setAvgAnnualGrowthNetSale_p(Boolean avgAnnualGrowthNetSale_p) {
        isAvgAnnualGrowthNetSale_p = avgAnnualGrowthNetSale_p;
    }

    public Boolean getAvgEBIDTA_p() {
        return isAvgEBIDTA_p;
    }

    public void setAvgEBIDTA_p(Boolean avgEBIDTA_p) {
        isAvgEBIDTA_p = avgEBIDTA_p;
    }

    public Boolean getAvgAnnualGrossCashAccuruals_p() {
        return isAvgAnnualGrossCashAccuruals_p;
    }

    public void setAvgAnnualGrossCashAccuruals_p(Boolean avgAnnualGrossCashAccuruals_p) {
        isAvgAnnualGrossCashAccuruals_p = avgAnnualGrossCashAccuruals_p;
    }

    public Boolean getAvgInterestCovRatio_p() {
        return isAvgInterestCovRatio_p;
    }

    public void setAvgInterestCovRatio_p(Boolean avgInterestCovRatio_p) {
        isAvgInterestCovRatio_p = avgInterestCovRatio_p;
    }

    public Boolean getNoOfCustomer_p() {
        return isNoOfCustomer_p;
    }

    public void setNoOfCustomer_p(Boolean noOfCustomer_p) {
        isNoOfCustomer_p = noOfCustomer_p;
    }

    public Boolean getConcentrationCustomer_p() {
        return isConcentrationCustomer_p;
    }

    public void setConcentrationCustomer_p(Boolean concentrationCustomer_p) {
        isConcentrationCustomer_p = concentrationCustomer_p;
    }

    public Boolean getExperienceInTheBusiness_p() {
        return isExperienceInTheBusiness_p;
    }

    public void setExperienceInTheBusiness_p(Boolean experienceInTheBusiness_p) {
        isExperienceInTheBusiness_p = experienceInTheBusiness_p;
    }

    public Boolean getCreditSummation_p() {
        return isCreditSummation_p;
    }

    public void setCreditSummation_p(Boolean creditSummation_p) {
        isCreditSummation_p = creditSummation_p;
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

    public Double getDebt() {
        return Debt;
    }

    public void setDebt(Double debt) {
        Debt = debt;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public Double getTol() {
        return tol;
    }

    public void setTol(Double tol) {
        this.tol = tol;
    }

    public Double getTnw() {
        return tnw;
    }

    public void setTnw(Double tnw) {
        this.tnw = tnw;
    }

    public Double getAvgCurrentRatio() {
        return avgCurrentRatio;
    }

    public void setAvgCurrentRatio(Double avgCurrentRatio) {
        this.avgCurrentRatio = avgCurrentRatio;
    }

    public Double getDebtorsDays() {
        return debtorsDays;
    }

    public void setDebtorsDays(Double debtorsDays) {
        this.debtorsDays = debtorsDays;
    }

    public Double getAvgInventory() {
        return avgInventory;
    }

    public void setAvgInventory(Double avgInventory) {
        this.avgInventory = avgInventory;
    }

    public Double getCogs() {
        return cogs;
    }

    public void setCogs(Double cogs) {
        this.cogs = cogs;
    }

    public Double getCreditorsDays() {
        return creditorsDays;
    }

    public void setCreditorsDays(Double creditorsDays) {
        this.creditorsDays = creditorsDays;
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

    public Double getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(Double totalAsset) {
        this.totalAsset = totalAsset;
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
}
