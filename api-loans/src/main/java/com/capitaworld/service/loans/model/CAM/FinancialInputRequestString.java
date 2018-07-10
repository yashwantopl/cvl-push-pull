package com.capitaworld.service.loans.model.CAM;

import java.io.Serializable;

public class FinancialInputRequestString implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String companyName;

    private String industryName;

    private String adjustedTotalDebtEquity;
    private String assetInTransit;
    private String assetTurnOver;
    private String bookValue;
    private String capitalWorkInProgress;
    private String cashAndBank;
    private String cashFromOperating;
    private String cashInterestCover;
    private String cfoMargine;
    private String contingentLiablities;
    private String creditorsTurnover;
    private String curruntRatioX;
    private String debtEbitad;
    private String debtorsTurnover;
    private String deferredTaxLiablities;
    private String depriciation;
    private String dividendPayOut;
    private String earningPerShare;
    private String ebitadPercentage;
    private String ebitda;
    private String employeeCost;
    private String equityDividend;
    private String exceptionalIncome;
    private String freeReserveEquity;
    private String generalAndAdminExpe;
    private String grossBlock;
    private String grossSales;
    private String growthCfoMargine;
    private String growthDebtEquity;
    private String impairmentofAsset;
    private String increaseDecreaseStock;
    private String increaseWorkingCapital;
    private String intengibleAssets;
    private String interest;
    private String interestPaid;
    private String inventories;
    private String inventoryTurnOver;
    private String investmentInSubsidiaries;
    private String lessAccumulatedDepre;
    private String lessExciseDuity;
    private String lessExpeCapita;
    private String longTermLoansAndAdva;
    private String longTermProvision;
    private String minorityInterest;
    private String miscelExpe;
    private String netBlock;
    private String netSale;
    private String netSalesGrowthCapital;
    private String noOfMonth;
    private String operatingProfitEbitadOi;
    private String operatingProfitExclOi;
    private String otheNonCurruntAsset;
    private String otherBorrowing;
    private String otherCurruntAsset;
    private String otherCurruntLiablities;
    private String otherIncome;
    private String otherInvestment;
    private String otherLongTermLiablities;
    private String otherReserveAndSurplus;
    private String patGrowthCapital;
    private String patm;
    private String pbdt;
    private String powerAndFuelCost;
    private String preOperativeExpe;
    private String profitAfterTax;
    private String profitBeforeTax;
    private String profitBeforeTaxation;
    private String provisionForTax;
    private String quickRatioX;
    private String rawMaterialConsumed;
    private String revalationReserve;
    private String roce;
    private String salesWorkingCapital;
    private String securedLoans;
    private String sellingAndDistriExpe;
    private String shareCapital;
    private String shareFaceValue;
    private String shareHolderFunds;
    private String shareWarrantOutstandings;
    private String shortTermLoansAdvances;
    private String shortTermProvision;
    private String sundryDebtors;
    private String taxPaid;
    private String totalAsset;
    private String totalCurruntAsset;
    private String totalCurruntLiablities;
    private String totalExpenditure;
    private String totalLiablities;
    private String totalNonCurruntAsset;
    private String totalNonCurruntLiablities;
    private String tradePayables;
    private String unsecuredLoansOthers;
    private String unsecuredLoansPromoters;
    private String ratioAnalysisFullDate;
    private String otherIncomeNeedTocCheckAsset;
	private String otherIncomeNeedTocCheckOp;
	private String otherIncomeNeedTocCheckLia;
    private Integer status;
    private String message;
    
	public FinancialInputRequestString() {
        this.adjustedTotalDebtEquity = "0.0";
        this.assetInTransit = "0.0";
        this.assetTurnOver = "0.0";
        this.bookValue = "0.0";
        this.capitalWorkInProgress = "0.0";
        this.cashAndBank = "0.0";
        this.cashFromOperating = "0.0";
        this.cashInterestCover = "0.0";
        this.cfoMargine = "0.0";
        this.contingentLiablities = "0.0";
        this.creditorsTurnover = "0.0";
        this.curruntRatioX = "0.0";
        this.debtEbitad = "0.0";
        this.debtorsTurnover = "0.0";
        this.deferredTaxLiablities = "0.0";
        this.depriciation = "0.0";
        this.dividendPayOut = "0.0";
        this.earningPerShare = "0.0";
        this.ebitadPercentage = "0.0";
        this.ebitda = "0.0";
        this.employeeCost = "0.0";
        this.equityDividend = "0.0";
        this.exceptionalIncome = "0.0";
        this.freeReserveEquity = "0.0";
        this.generalAndAdminExpe = "0.0";
        this.grossBlock = "0.0";
        this.grossSales = "0.0";
        this.growthCfoMargine = "0.0";
        this.growthDebtEquity = "0.0";
        this.impairmentofAsset = "0.0";
        this.increaseDecreaseStock = "0.0";
        this.increaseWorkingCapital = "0.0";
        this.intengibleAssets = "0.0";
        this.interest = "0.0";
        this.interestPaid = "0.0";
        this.inventories = "0.0";
        this.inventoryTurnOver = "0.0";
        this.investmentInSubsidiaries = "0.0";
        this.lessAccumulatedDepre = "0.0";
        this.lessExciseDuity = "0.0";
        this.lessExpeCapita = "0.0";
        this.longTermLoansAndAdva = "0.0";
        this.longTermProvision = "0.0";
        this.minorityInterest = "0.0";
        this.miscelExpe = "0.0";
        this.netBlock = "0.0";
        this.netSale = "0.0";
        this.netSalesGrowthCapital = "0.0";
        this.noOfMonth = "0.0";
        this.operatingProfitEbitadOi = "0.0";
        this.operatingProfitExclOi = "0.0";
        this.otheNonCurruntAsset = "0.0";
        this.otherBorrowing = "0.0";
        this.otherCurruntAsset = "0.0";
        this.otherCurruntLiablities = "0.0";
        this.otherIncome = "0.0";
        this.otherInvestment = "0.0";
        this.otherLongTermLiablities = "0.0";
        this.otherReserveAndSurplus = "0.0";
        this.patGrowthCapital = "0.0";
        this.patm = "0.0";
        this.pbdt = "0.0";
        this.powerAndFuelCost = "0.0";
        this.preOperativeExpe = "0.0";
        this.profitAfterTax = "0.0";
        this.profitBeforeTax = "0.0";
        this.profitBeforeTaxation = "0.0";
        this.provisionForTax = "0.0";
        this.quickRatioX = "0.0";
        this.rawMaterialConsumed = "0.0";
        this.revalationReserve = "0.0";
        this.roce = "0.0";
        this.salesWorkingCapital = "0.0";
        this.securedLoans = "0.0";
        this.sellingAndDistriExpe = "0.0";
        this.shareCapital = "0.0";
        this.shareFaceValue = "0.0";
        this.shareHolderFunds = "0.0";
        this.shareWarrantOutstandings = "0.0";
        this.shortTermLoansAdvances = "0.0";
        this.shortTermProvision = "0.0";
        this.sundryDebtors = "0.0";
        this.taxPaid = "0.0";
        this.totalAsset = "0.0";
        this.totalCurruntAsset = "0.0";
        this.totalCurruntLiablities = "0.0";
        this.totalExpenditure = "0.0";
        this.totalLiablities = "0.0";
        this.totalNonCurruntAsset = "0.0";
        this.totalNonCurruntLiablities = "0.0";
        this.tradePayables = "0.0";
        this.unsecuredLoansOthers = "0.0";
        this.unsecuredLoansPromoters = "0.0";
        this.ratioAnalysisFullDate = "0.0";
        this.otherIncomeNeedTocCheckAsset ="0.0";
        this.otherIncomeNeedTocCheckLia ="0.0";
        this.otherIncomeNeedTocCheckOp = "0.0";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getAdjustedTotalDebtEquity() {
		return adjustedTotalDebtEquity;
	}
	public void setAdjustedTotalDebtEquity(String adjustedTotalDebtEquity) {
		this.adjustedTotalDebtEquity = adjustedTotalDebtEquity;
	}
	public String getAssetInTransit() {
		return assetInTransit;
	}
	public void setAssetInTransit(String assetInTransit) {
		this.assetInTransit = assetInTransit;
	}
	public String getAssetTurnOver() {
		return assetTurnOver;
	}
	public void setAssetTurnOver(String assetTurnOver) {
		this.assetTurnOver = assetTurnOver;
	}
	public String getBookValue() {
		return bookValue;
	}
	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}
	public String getCapitalWorkInProgress() {
		return capitalWorkInProgress;
	}
	public void setCapitalWorkInProgress(String capitalWorkInProgress) {
		this.capitalWorkInProgress = capitalWorkInProgress;
	}
	public String getCashAndBank() {
		return cashAndBank;
	}
	public void setCashAndBank(String cashAndBank) {
		this.cashAndBank = cashAndBank;
	}
	public String getCashFromOperating() {
		return cashFromOperating;
	}
	public void setCashFromOperating(String cashFromOperating) {
		this.cashFromOperating = cashFromOperating;
	}
	public String getCashInterestCover() {
		return cashInterestCover;
	}
	public void setCashInterestCover(String cashInterestCover) {
		this.cashInterestCover = cashInterestCover;
	}
	public String getCfoMargine() {
		return cfoMargine;
	}
	public void setCfoMargine(String cfoMargine) {
		this.cfoMargine = cfoMargine;
	}
	public String getContingentLiablities() {
		return contingentLiablities;
	}
	public void setContingentLiablities(String contingentLiablities) {
		this.contingentLiablities = contingentLiablities;
	}
	public String getCreditorsTurnover() {
		return creditorsTurnover;
	}
	public void setCreditorsTurnover(String creditorsTurnover) {
		this.creditorsTurnover = creditorsTurnover;
	}
	public String getCurruntRatioX() {
		return curruntRatioX;
	}
	public void setCurruntRatioX(String curruntRatioX) {
		this.curruntRatioX = curruntRatioX;
	}
	public String getDebtEbitad() {
		return debtEbitad;
	}
	public void setDebtEbitad(String debtEbitad) {
		this.debtEbitad = debtEbitad;
	}
	public String getDebtorsTurnover() {
		return debtorsTurnover;
	}
	public void setDebtorsTurnover(String debtorsTurnover) {
		this.debtorsTurnover = debtorsTurnover;
	}
	public String getDeferredTaxLiablities() {
		return deferredTaxLiablities;
	}
	public void setDeferredTaxLiablities(String deferredTaxLiablities) {
		this.deferredTaxLiablities = deferredTaxLiablities;
	}
	public String getDepriciation() {
		return depriciation;
	}
	public void setDepriciation(String depriciation) {
		this.depriciation = depriciation;
	}
	public String getDividendPayOut() {
		return dividendPayOut;
	}
	public void setDividendPayOut(String dividendPayOut) {
		this.dividendPayOut = dividendPayOut;
	}
	public String getEarningPerShare() {
		return earningPerShare;
	}
	public void setEarningPerShare(String earningPerShare) {
		this.earningPerShare = earningPerShare;
	}
	public String getEbitadPercentage() {
		return ebitadPercentage;
	}
	public void setEbitadPercentage(String ebitadPercentage) {
		this.ebitadPercentage = ebitadPercentage;
	}
	public String getEbitda() {
		return ebitda;
	}
	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}
	public String getEmployeeCost() {
		return employeeCost;
	}
	public void setEmployeeCost(String employeeCost) {
		this.employeeCost = employeeCost;
	}
	public String getEquityDividend() {
		return equityDividend;
	}
	public void setEquityDividend(String equityDividend) {
		this.equityDividend = equityDividend;
	}
	public String getExceptionalIncome() {
		return exceptionalIncome;
	}
	public void setExceptionalIncome(String exceptionalIncome) {
		this.exceptionalIncome = exceptionalIncome;
	}
	public String getFreeReserveEquity() {
		return freeReserveEquity;
	}
	public void setFreeReserveEquity(String freeReserveEquity) {
		this.freeReserveEquity = freeReserveEquity;
	}
	public String getGeneralAndAdminExpe() {
		return generalAndAdminExpe;
	}
	public void setGeneralAndAdminExpe(String generalAndAdminExpe) {
		this.generalAndAdminExpe = generalAndAdminExpe;
	}
	public String getGrossBlock() {
		return grossBlock;
	}
	public void setGrossBlock(String grossBlock) {
		this.grossBlock = grossBlock;
	}
	public String getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(String grossSales) {
		this.grossSales = grossSales;
	}
	public String getGrowthCfoMargine() {
		return growthCfoMargine;
	}
	public void setGrowthCfoMargine(String growthCfoMargine) {
		this.growthCfoMargine = growthCfoMargine;
	}
	public String getGrowthDebtEquity() {
		return growthDebtEquity;
	}
	public void setGrowthDebtEquity(String growthDebtEquity) {
		this.growthDebtEquity = growthDebtEquity;
	}
	public String getImpairmentofAsset() {
		return impairmentofAsset;
	}
	public void setImpairmentofAsset(String impairmentofAsset) {
		this.impairmentofAsset = impairmentofAsset;
	}
	public String getIncreaseDecreaseStock() {
		return increaseDecreaseStock;
	}
	public void setIncreaseDecreaseStock(String increaseDecreaseStock) {
		this.increaseDecreaseStock = increaseDecreaseStock;
	}
	public String getIncreaseWorkingCapital() {
		return increaseWorkingCapital;
	}
	public void setIncreaseWorkingCapital(String increaseWorkingCapital) {
		this.increaseWorkingCapital = increaseWorkingCapital;
	}
	public String getIntengibleAssets() {
		return intengibleAssets;
	}
	public void setIntengibleAssets(String intengibleAssets) {
		this.intengibleAssets = intengibleAssets;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(String interestPaid) {
		this.interestPaid = interestPaid;
	}
	public String getInventories() {
		return inventories;
	}
	public void setInventories(String inventories) {
		this.inventories = inventories;
	}
	public String getInventoryTurnOver() {
		return inventoryTurnOver;
	}
	public void setInventoryTurnOver(String inventoryTurnOver) {
		this.inventoryTurnOver = inventoryTurnOver;
	}
	public String getInvestmentInSubsidiaries() {
		return investmentInSubsidiaries;
	}
	public void setInvestmentInSubsidiaries(String investmentInSubsidiaries) {
		this.investmentInSubsidiaries = investmentInSubsidiaries;
	}
	public String getLessAccumulatedDepre() {
		return lessAccumulatedDepre;
	}
	public void setLessAccumulatedDepre(String lessAccumulatedDepre) {
		this.lessAccumulatedDepre = lessAccumulatedDepre;
	}
	public String getLessExciseDuity() {
		return lessExciseDuity;
	}
	public void setLessExciseDuity(String lessExciseDuity) {
		this.lessExciseDuity = lessExciseDuity;
	}
	public String getLessExpeCapita() {
		return lessExpeCapita;
	}
	public void setLessExpeCapita(String lessExpeCapita) {
		this.lessExpeCapita = lessExpeCapita;
	}
	public String getLongTermLoansAndAdva() {
		return longTermLoansAndAdva;
	}
	public void setLongTermLoansAndAdva(String longTermLoansAndAdva) {
		this.longTermLoansAndAdva = longTermLoansAndAdva;
	}
	public String getLongTermProvision() {
		return longTermProvision;
	}
	public void setLongTermProvision(String longTermProvision) {
		this.longTermProvision = longTermProvision;
	}
	public String getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(String minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	public String getMiscelExpe() {
		return miscelExpe;
	}
	public void setMiscelExpe(String miscelExpe) {
		this.miscelExpe = miscelExpe;
	}
	public String getNetBlock() {
		return netBlock;
	}
	public void setNetBlock(String netBlock) {
		this.netBlock = netBlock;
	}
	public String getNetSale() {
		return netSale;
	}
	public void setNetSale(String netSale) {
		this.netSale = netSale;
	}
	public String getNetSalesGrowthCapital() {
		return netSalesGrowthCapital;
	}
	public void setNetSalesGrowthCapital(String netSalesGrowthCapital) {
		this.netSalesGrowthCapital = netSalesGrowthCapital;
	}
	public String getNoOfMonth() {
		return noOfMonth;
	}
	public void setNoOfMonth(String noOfMonth) {
		this.noOfMonth = noOfMonth;
	}
	public String getOperatingProfitEbitadOi() {
		return operatingProfitEbitadOi;
	}
	public void setOperatingProfitEbitadOi(String operatingProfitEbitadOi) {
		this.operatingProfitEbitadOi = operatingProfitEbitadOi;
	}
	public String getOperatingProfitExclOi() {
		return operatingProfitExclOi;
	}
	public void setOperatingProfitExclOi(String operatingProfitExclOi) {
		this.operatingProfitExclOi = operatingProfitExclOi;
	}
	public String getOtheNonCurruntAsset() {
		return otheNonCurruntAsset;
	}
	public void setOtheNonCurruntAsset(String otheNonCurruntAsset) {
		this.otheNonCurruntAsset = otheNonCurruntAsset;
	}
	public String getOtherBorrowing() {
		return otherBorrowing;
	}
	public void setOtherBorrowing(String otherBorrowing) {
		this.otherBorrowing = otherBorrowing;
	}
	public String getOtherCurruntAsset() {
		return otherCurruntAsset;
	}
	public void setOtherCurruntAsset(String otherCurruntAsset) {
		this.otherCurruntAsset = otherCurruntAsset;
	}
	public String getOtherCurruntLiablities() {
		return otherCurruntLiablities;
	}
	public void setOtherCurruntLiablities(String otherCurruntLiablities) {
		this.otherCurruntLiablities = otherCurruntLiablities;
	}
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getOtherInvestment() {
		return otherInvestment;
	}
	public void setOtherInvestment(String otherInvestment) {
		this.otherInvestment = otherInvestment;
	}
	public String getOtherLongTermLiablities() {
		return otherLongTermLiablities;
	}
	public void setOtherLongTermLiablities(String otherLongTermLiablities) {
		this.otherLongTermLiablities = otherLongTermLiablities;
	}
	public String getOtherReserveAndSurplus() {
		return otherReserveAndSurplus;
	}
	public void setOtherReserveAndSurplus(String otherReserveAndSurplus) {
		this.otherReserveAndSurplus = otherReserveAndSurplus;
	}
	public String getPatGrowthCapital() {
		return patGrowthCapital;
	}
	public void setPatGrowthCapital(String patGrowthCapital) {
		this.patGrowthCapital = patGrowthCapital;
	}
	public String getPatm() {
		return patm;
	}
	public void setPatm(String patm) {
		this.patm = patm;
	}
	public String getPbdt() {
		return pbdt;
	}
	public void setPbdt(String pbdt) {
		this.pbdt = pbdt;
	}
	public String getPowerAndFuelCost() {
		return powerAndFuelCost;
	}
	public void setPowerAndFuelCost(String powerAndFuelCost) {
		this.powerAndFuelCost = powerAndFuelCost;
	}
	public String getPreOperativeExpe() {
		return preOperativeExpe;
	}
	public void setPreOperativeExpe(String preOperativeExpe) {
		this.preOperativeExpe = preOperativeExpe;
	}
	public String getProfitAfterTax() {
		return profitAfterTax;
	}
	public void setProfitAfterTax(String profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}
	public String getProfitBeforeTax() {
		return profitBeforeTax;
	}
	public void setProfitBeforeTax(String profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}
	public String getProfitBeforeTaxation() {
		return profitBeforeTaxation;
	}
	public void setProfitBeforeTaxation(String profitBeforeTaxation) {
		this.profitBeforeTaxation = profitBeforeTaxation;
	}
	public String getProvisionForTax() {
		return provisionForTax;
	}
	public void setProvisionForTax(String provisionForTax) {
		this.provisionForTax = provisionForTax;
	}
	public String getQuickRatioX() {
		return quickRatioX;
	}
	public void setQuickRatioX(String quickRatioX) {
		this.quickRatioX = quickRatioX;
	}
	public String getRawMaterialConsumed() {
		return rawMaterialConsumed;
	}
	public void setRawMaterialConsumed(String rawMaterialConsumed) {
		this.rawMaterialConsumed = rawMaterialConsumed;
	}
	public String getRevalationReserve() {
		return revalationReserve;
	}
	public void setRevalationReserve(String revalationReserve) {
		this.revalationReserve = revalationReserve;
	}
	public String getRoce() {
		return roce;
	}
	public void setRoce(String roce) {
		this.roce = roce;
	}
	public String getSalesWorkingCapital() {
		return salesWorkingCapital;
	}
	public void setSalesWorkingCapital(String salesWorkingCapital) {
		this.salesWorkingCapital = salesWorkingCapital;
	}
	public String getSecuredLoans() {
		return securedLoans;
	}
	public void setSecuredLoans(String securedLoans) {
		this.securedLoans = securedLoans;
	}
	public String getSellingAndDistriExpe() {
		return sellingAndDistriExpe;
	}
	public void setSellingAndDistriExpe(String sellingAndDistriExpe) {
		this.sellingAndDistriExpe = sellingAndDistriExpe;
	}
	public String getShareCapital() {
		return shareCapital;
	}
	public void setShareCapital(String shareCapital) {
		this.shareCapital = shareCapital;
	}
	public String getShareFaceValue() {
		return shareFaceValue;
	}
	public void setShareFaceValue(String shareFaceValue) {
		this.shareFaceValue = shareFaceValue;
	}
	public String getShareHolderFunds() {
		return shareHolderFunds;
	}
	public void setShareHolderFunds(String shareHolderFunds) {
		this.shareHolderFunds = shareHolderFunds;
	}
	public String getShareWarrantOutstandings() {
		return shareWarrantOutstandings;
	}
	public void setShareWarrantOutstandings(String shareWarrantOutstandings) {
		this.shareWarrantOutstandings = shareWarrantOutstandings;
	}
	public String getShortTermLoansAdvances() {
		return shortTermLoansAdvances;
	}
	public void setShortTermLoansAdvances(String shortTermLoansAdvances) {
		this.shortTermLoansAdvances = shortTermLoansAdvances;
	}
	public String getShortTermProvision() {
		return shortTermProvision;
	}
	public void setShortTermProvision(String shortTermProvision) {
		this.shortTermProvision = shortTermProvision;
	}
	public String getSundryDebtors() {
		return sundryDebtors;
	}
	public void setSundryDebtors(String sundryDebtors) {
		this.sundryDebtors = sundryDebtors;
	}
	public String getTaxPaid() {
		return taxPaid;
	}
	public void setTaxPaid(String taxPaid) {
		this.taxPaid = taxPaid;
	}
	public String getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(String totalAsset) {
		this.totalAsset = totalAsset;
	}
	public String getTotalCurruntAsset() {
		return totalCurruntAsset;
	}
	public void setTotalCurruntAsset(String totalCurruntAsset) {
		this.totalCurruntAsset = totalCurruntAsset;
	}
	public String getTotalCurruntLiablities() {
		return totalCurruntLiablities;
	}
	public void setTotalCurruntLiablities(String totalCurruntLiablities) {
		this.totalCurruntLiablities = totalCurruntLiablities;
	}
	public String getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(String totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public String getTotalLiablities() {
		return totalLiablities;
	}
	public void setTotalLiablities(String totalLiablities) {
		this.totalLiablities = totalLiablities;
	}
	public String getTotalNonCurruntAsset() {
		return totalNonCurruntAsset;
	}
	public void setTotalNonCurruntAsset(String totalNonCurruntAsset) {
		this.totalNonCurruntAsset = totalNonCurruntAsset;
	}
	public String getTotalNonCurruntLiablities() {
		return totalNonCurruntLiablities;
	}
	public void setTotalNonCurruntLiablities(String totalNonCurruntLiablities) {
		this.totalNonCurruntLiablities = totalNonCurruntLiablities;
	}
	public String getTradePayables() {
		return tradePayables;
	}
	public void setTradePayables(String tradePayables) {
		this.tradePayables = tradePayables;
	}
	public String getUnsecuredLoansOthers() {
		return unsecuredLoansOthers;
	}
	public void setUnsecuredLoansOthers(String unsecuredLoansOthers) {
		this.unsecuredLoansOthers = unsecuredLoansOthers;
	}
	public String getUnsecuredLoansPromoters() {
		return unsecuredLoansPromoters;
	}
	public void setUnsecuredLoansPromoters(String unsecuredLoansPromoters) {
		this.unsecuredLoansPromoters = unsecuredLoansPromoters;
	}
	public String getRatioAnalysisFullDate() {
		return ratioAnalysisFullDate;
	}
	public void setRatioAnalysisFullDate(String ratioAnalysisFullDate) {
		this.ratioAnalysisFullDate = ratioAnalysisFullDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOtherIncomeNeedTocCheckAsset() {
		return otherIncomeNeedTocCheckAsset;
	}
	public void setOtherIncomeNeedTocCheckAsset(String otherIncomeNeedTocCheckAsset) {
		this.otherIncomeNeedTocCheckAsset = otherIncomeNeedTocCheckAsset;
	}
	public String getOtherIncomeNeedTocCheckOp() {
		return otherIncomeNeedTocCheckOp;
	}
	public void setOtherIncomeNeedTocCheckOp(String otherIncomeNeedTocCheckOp) {
		this.otherIncomeNeedTocCheckOp = otherIncomeNeedTocCheckOp;
	}
	public String getOtherIncomeNeedTocCheckLia() {
		return otherIncomeNeedTocCheckLia;
	}
	public void setOtherIncomeNeedTocCheckLia(String otherIncomeNeedTocCheckLia) {
		this.otherIncomeNeedTocCheckLia = otherIncomeNeedTocCheckLia;
	}
	
    
    
    
}
