package com.capitaworld.service.loans.model.CAM;

import java.io.Serializable;

public class FinancialInputRequestDbl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String companyName;
	
	private String industryName;

	private Double adjustedTotalDebtEquity;
	private Double assetInTransit;
	private Double assetTurnOver;
	private Double bookValue;
	private Double capitalWorkInProgress;
	private Double cashAndBank;
	private Double cashFromOperating;
	private Double cashInterestCover;
	private Double cfoMargine;
	private Double contingentLiablities;
	private Double creditorsTurnover;
	private Double curruntRatioX;
	private Double debtEbitad;
	private Double debtorsTurnover;
	private Double deferredTaxLiablities;
	private Double depriciation;
	private Double dividendPayOut;
	private Double earningPerShare;
	private Double ebitadPercentage;
	private Double ebitda;
	private Double employeeCost;
	private Double equityDividend;
	private Double exceptionalIncome;
	private Double freeReserveEquity;
	private Double generalAndAdminExpe;
	private Double grossBlock;
	private Double grossSales;
	private Double growthCfoMargine;
	private Double growthDebtEquity;
	private Double impairmentofAsset;
	private Double increaseDecreaseStock;
	private Double increaseWorkingCapital;
	private Double intengibleAssets;
	private Double interest;
	private Double interestPaid;
	private Double inventories;
	private Double inventoryTurnOver;
	private Double investmentInSubsidiaries;
	private Double lessAccumulatedDepre;
	private Double lessExciseDuity;
	private Double lessExpeCapita;
	private Double longTermLoansAndAdva;
	private Double longTermProvision;
	private Double minorityInterest;
	private Double miscelExpe;
	private Double netBlock;
	private Double netSale;
	private Double netSalesGrowthCapital;
	private Double noOfMonth;
	private Double operatingProfitEbitadOi;
	private Double operatingProfitExclOi;
	private Double otheNonCurruntAsset;
	private Double otherBorrowing;
	private Double otherCurruntAsset;
	private Double otherCurruntLiablities;
	private Double otherIncome;
	private Double otherInvestment;
	private Double otherLongTermLiablities;
	private Double otherReserveAndSurplus;
	private Double patGrowthCapital;
	private Double patm;
	private Double pbdt;
	private Double powerAndFuelCost;
	private Double preOperativeExpe;
	private Double profitAfterTax;
	private Double profitBeforeTax;
	private Double profitBeforeTaxation;
	private Double provisionForTax;
	private Double quickRatioX;
	private Double rawMaterialConsumed;
	private Double revalationReserve;
	private Double roce;
	private Double salesWorkingCapital;
	private Double securedLoans;
	private Double sellingAndDistriExpe;
	private Double shareCapital;
	private Double shareFaceValue;
	private Double shareHolderFunds;
	private Double shareWarrantOutstandings;
	private Double shortTermLoansAdvances;
	private Double shortTermProvision;
	private Double sundryDebtors;
	private Double taxPaid;
	private Double totalAsset;
	private Double totalCurruntAsset;
	private Double totalCurruntLiablities;
	private Double totalExpenditure;
	private Double totalLiablities;
	private Double totalNonCurruntAsset;
	private Double totalNonCurruntLiablities;
	private Double tradePayables;
	private Double unsecuredLoansOthers;
	private Double unsecuredLoansPromoters;
	private String ratioAnalysisFullDate;
	private Double otherIncomeNeedTocCheckAsset;
	private Double otherIncomeNeedTocCheckOp;
	private Double otherIncomeNeedTocCheckLia;
	private Integer status;
	private String message;
	private Object data;
	
	
	public FinancialInputRequestDbl() {
        this.adjustedTotalDebtEquity = 0.0;
        this.assetInTransit = 0.0;
        this.assetTurnOver = 0.0;
        this.bookValue = 0.0;
        this.capitalWorkInProgress = 0.0;
        this.cashAndBank = 0.0;
        this.cashFromOperating = 0.0;
        this.cashInterestCover = 0.0;
        this.cfoMargine = 0.0;
        this.contingentLiablities = 0.0;
        this.creditorsTurnover = 0.0;
        this.curruntRatioX = 0.0;
        this.debtEbitad = 0.0;
        this.debtorsTurnover = 0.0;
        this.deferredTaxLiablities = 0.0;
        this.depriciation = 0.0;
        this.dividendPayOut = 0.0;
        this.earningPerShare = 0.0;
        this.ebitadPercentage = 0.0;
        this.ebitda = 0.0;
        this.employeeCost = 0.0;
        this.equityDividend = 0.0;
        this.exceptionalIncome = 0.0;
        this.freeReserveEquity = 0.0;
        this.generalAndAdminExpe = 0.0;
        this.grossBlock = 0.0;
        this.grossSales = 0.0;
        this.growthCfoMargine = 0.0;
        this.growthDebtEquity = 0.0;
        this.impairmentofAsset = 0.0;
        this.increaseDecreaseStock = 0.0;
        this.increaseWorkingCapital = 0.0;
        this.intengibleAssets = 0.0;
        this.interest = 0.0;
        this.interestPaid = 0.0;
        this.inventories = 0.0;
        this.inventoryTurnOver = 0.0;
        this.investmentInSubsidiaries = 0.0;
        this.lessAccumulatedDepre = 0.0;
        this.lessExciseDuity = 0.0;
        this.lessExpeCapita = 0.0;
        this.longTermLoansAndAdva = 0.0;
        this.longTermProvision = 0.0;
        this.minorityInterest = 0.0;
        this.miscelExpe = 0.0;
        this.netBlock = 0.0;
        this.netSale = 0.0;
        this.netSalesGrowthCapital = 0.0;
        this.noOfMonth = 0.0;
        this.operatingProfitEbitadOi = 0.0;
        this.operatingProfitExclOi = 0.0;
        this.otheNonCurruntAsset = 0.0;
        this.otherBorrowing = 0.0;
        this.otherCurruntAsset = 0.0;
        this.otherCurruntLiablities = 0.0;
        this.otherIncome = 0.0;
        this.otherInvestment = 0.0;
        this.otherLongTermLiablities = 0.0;
        this.otherReserveAndSurplus = 0.0;
        this.patGrowthCapital = 0.0;
        this.patm = 0.0;
        this.pbdt = 0.0;
        this.powerAndFuelCost = 0.0;
        this.preOperativeExpe = 0.0;
        this.profitAfterTax = 0.0;
        this.profitBeforeTax = 0.0;
        this.profitBeforeTaxation = 0.0;
        this.provisionForTax = 0.0;
        this.quickRatioX = 0.0;
        this.rawMaterialConsumed = 0.0;
        this.revalationReserve = 0.0;
        this.roce = 0.0;
        this.salesWorkingCapital = 0.0;
        this.securedLoans = 0.0;
        this.sellingAndDistriExpe = 0.0;
        this.shareCapital = 0.0;
        this.shareFaceValue = 0.0;
        this.shareHolderFunds = 0.0;
        this.shareWarrantOutstandings = 0.0;
        this.shortTermLoansAdvances = 0.0;
        this.shortTermProvision = 0.0;
        this.sundryDebtors = 0.0;
        this.taxPaid = 0.0;
        this.totalAsset = 0.0;
        this.totalCurruntAsset = 0.0;
        this.totalCurruntLiablities = 0.0;
        this.totalExpenditure = 0.0;
        this.totalLiablities = 0.0;
        this.totalNonCurruntAsset = 0.0;
        this.totalNonCurruntLiablities = 0.0;
        this.tradePayables = 0.0;
        this.unsecuredLoansOthers = 0.0;
        this.unsecuredLoansPromoters = 0.0;
        this.otherIncomeNeedTocCheckAsset =0.0;
        this.otherIncomeNeedTocCheckLia =0.0;
        this.otherIncomeNeedTocCheckOp = 0.0;
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
	public Double getAdjustedTotalDebtEquity() {
		return adjustedTotalDebtEquity;
	}
	public void setAdjustedTotalDebtEquity(Double adjustedTotalDebtEquity) {
		this.adjustedTotalDebtEquity = adjustedTotalDebtEquity;
	}
	public Double getAssetInTransit() {
		return assetInTransit;
	}
	public void setAssetInTransit(Double assetInTransit) {
		this.assetInTransit = assetInTransit;
	}
	public Double getAssetTurnOver() {
		return assetTurnOver;
	}
	public void setAssetTurnOver(Double assetTurnOver) {
		this.assetTurnOver = assetTurnOver;
	}
	public Double getBookValue() {
		return bookValue;
	}
	public void setBookValue(Double bookValue) {
		this.bookValue = bookValue;
	}
	public Double getCapitalWorkInProgress() {
		return capitalWorkInProgress;
	}
	public void setCapitalWorkInProgress(Double capitalWorkInProgress) {
		this.capitalWorkInProgress = capitalWorkInProgress;
	}
	public Double getCashAndBank() {
		return cashAndBank;
	}
	public void setCashAndBank(Double cashAndBank) {
		this.cashAndBank = cashAndBank;
	}
	public Double getCashFromOperating() {
		return cashFromOperating;
	}
	public void setCashFromOperating(Double cashFromOperating) {
		this.cashFromOperating = cashFromOperating;
	}
	public Double getCashInterestCover() {
		return cashInterestCover;
	}
	public void setCashInterestCover(Double cashInterestCover) {
		this.cashInterestCover = cashInterestCover;
	}
	public Double getCfoMargine() {
		return cfoMargine;
	}
	public void setCfoMargine(Double cfoMargine) {
		this.cfoMargine = cfoMargine;
	}
	public Double getContingentLiablities() {
		return contingentLiablities;
	}
	public void setContingentLiablities(Double contingentLiablities) {
		this.contingentLiablities = contingentLiablities;
	}
	public Double getCreditorsTurnover() {
		return creditorsTurnover;
	}
	public void setCreditorsTurnover(Double creditorsTurnover) {
		this.creditorsTurnover = creditorsTurnover;
	}
	public Double getCurruntRatioX() {
		return curruntRatioX;
	}
	public void setCurruntRatioX(Double curruntRatioX) {
		this.curruntRatioX = curruntRatioX;
	}
	public Double getDebtEbitad() {
		return debtEbitad;
	}
	public void setDebtEbitad(Double debtEbitad) {
		this.debtEbitad = debtEbitad;
	}
	public Double getDebtorsTurnover() {
		return debtorsTurnover;
	}
	public void setDebtorsTurnover(Double debtorsTurnover) {
		this.debtorsTurnover = debtorsTurnover;
	}
	public Double getDeferredTaxLiablities() {
		return deferredTaxLiablities;
	}
	public void setDeferredTaxLiablities(Double deferredTaxLiablities) {
		this.deferredTaxLiablities = deferredTaxLiablities;
	}
	public Double getDepriciation() {
		return depriciation;
	}
	public void setDepriciation(Double depriciation) {
		this.depriciation = depriciation;
	}
	public Double getDividendPayOut() {
		return dividendPayOut;
	}
	public void setDividendPayOut(Double dividendPayOut) {
		this.dividendPayOut = dividendPayOut;
	}
	public Double getEarningPerShare() {
		return earningPerShare;
	}
	public void setEarningPerShare(Double earningPerShare) {
		this.earningPerShare = earningPerShare;
	}
	public Double getEbitadPercentage() {
		return ebitadPercentage;
	}
	public void setEbitadPercentage(Double ebitadPercentage) {
		this.ebitadPercentage = ebitadPercentage;
	}
	public Double getEbitda() {
		return ebitda;
	}
	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}
	public Double getEmployeeCost() {
		return employeeCost;
	}
	public void setEmployeeCost(Double employeeCost) {
		this.employeeCost = employeeCost;
	}
	public Double getEquityDividend() {
		return equityDividend;
	}
	public void setEquityDividend(Double equityDividend) {
		this.equityDividend = equityDividend;
	}
	public Double getExceptionalIncome() {
		return exceptionalIncome;
	}
	public void setExceptionalIncome(Double exceptionalIncome) {
		this.exceptionalIncome = exceptionalIncome;
	}
	public Double getFreeReserveEquity() {
		return freeReserveEquity;
	}
	public void setFreeReserveEquity(Double freeReserveEquity) {
		this.freeReserveEquity = freeReserveEquity;
	}
	public Double getGeneralAndAdminExpe() {
		return generalAndAdminExpe;
	}
	public void setGeneralAndAdminExpe(Double generalAndAdminExpe) {
		this.generalAndAdminExpe = generalAndAdminExpe;
	}
	public Double getGrossBlock() {
		return grossBlock;
	}
	public void setGrossBlock(Double grossBlock) {
		this.grossBlock = grossBlock;
	}
	public Double getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(Double grossSales) {
		this.grossSales = grossSales;
	}
	public Double getGrowthCfoMargine() {
		return growthCfoMargine;
	}
	public void setGrowthCfoMargine(Double growthCfoMargine) {
		this.growthCfoMargine = growthCfoMargine;
	}
	public Double getGrowthDebtEquity() {
		return growthDebtEquity;
	}
	public void setGrowthDebtEquity(Double growthDebtEquity) {
		this.growthDebtEquity = growthDebtEquity;
	}
	public Double getImpairmentofAsset() {
		return impairmentofAsset;
	}
	public void setImpairmentofAsset(Double impairmentofAsset) {
		this.impairmentofAsset = impairmentofAsset;
	}
	public Double getIncreaseDecreaseStock() {
		return increaseDecreaseStock;
	}
	public void setIncreaseDecreaseStock(Double increaseDecreaseStock) {
		this.increaseDecreaseStock = increaseDecreaseStock;
	}
	public Double getIncreaseWorkingCapital() {
		return increaseWorkingCapital;
	}
	public void setIncreaseWorkingCapital(Double increaseWorkingCapital) {
		this.increaseWorkingCapital = increaseWorkingCapital;
	}
	public Double getIntengibleAssets() {
		return intengibleAssets;
	}
	public void setIntengibleAssets(Double intengibleAssets) {
		this.intengibleAssets = intengibleAssets;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Double getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(Double interestPaid) {
		this.interestPaid = interestPaid;
	}
	public Double getInventories() {
		return inventories;
	}
	public void setInventories(Double inventories) {
		this.inventories = inventories;
	}
	public Double getInventoryTurnOver() {
		return inventoryTurnOver;
	}
	public void setInventoryTurnOver(Double inventoryTurnOver) {
		this.inventoryTurnOver = inventoryTurnOver;
	}
	public Double getInvestmentInSubsidiaries() {
		return investmentInSubsidiaries;
	}
	public void setInvestmentInSubsidiaries(Double investmentInSubsidiaries) {
		this.investmentInSubsidiaries = investmentInSubsidiaries;
	}
	public Double getLessAccumulatedDepre() {
		return lessAccumulatedDepre;
	}
	public void setLessAccumulatedDepre(Double lessAccumulatedDepre) {
		this.lessAccumulatedDepre = lessAccumulatedDepre;
	}
	public Double getLessExciseDuity() {
		return lessExciseDuity;
	}
	public void setLessExciseDuity(Double lessExciseDuity) {
		this.lessExciseDuity = lessExciseDuity;
	}
	public Double getLessExpeCapita() {
		return lessExpeCapita;
	}
	public void setLessExpeCapita(Double lessExpeCapita) {
		this.lessExpeCapita = lessExpeCapita;
	}
	public Double getLongTermLoansAndAdva() {
		return longTermLoansAndAdva;
	}
	public void setLongTermLoansAndAdva(Double longTermLoansAndAdva) {
		this.longTermLoansAndAdva = longTermLoansAndAdva;
	}
	public Double getLongTermProvision() {
		return longTermProvision;
	}
	public void setLongTermProvision(Double longTermProvision) {
		this.longTermProvision = longTermProvision;
	}
	public Double getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(Double minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	public Double getMiscelExpe() {
		return miscelExpe;
	}
	public void setMiscelExpe(Double miscelExpe) {
		this.miscelExpe = miscelExpe;
	}
	public Double getNetBlock() {
		return netBlock;
	}
	public void setNetBlock(Double netBlock) {
		this.netBlock = netBlock;
	}
	public Double getNetSale() {
		return netSale;
	}
	public void setNetSale(Double netSale) {
		this.netSale = netSale;
	}
	public Double getNetSalesGrowthCapital() {
		return netSalesGrowthCapital;
	}
	public void setNetSalesGrowthCapital(Double netSalesGrowthCapital) {
		this.netSalesGrowthCapital = netSalesGrowthCapital;
	}
	public Double getNoOfMonth() {
		return noOfMonth;
	}
	public void setNoOfMonth(Double noOfMonth) {
		this.noOfMonth = noOfMonth;
	}
	public Double getOperatingProfitEbitadOi() {
		return operatingProfitEbitadOi;
	}
	public void setOperatingProfitEbitadOi(Double operatingProfitEbitadOi) {
		this.operatingProfitEbitadOi = operatingProfitEbitadOi;
	}
	public Double getOperatingProfitExclOi() {
		return operatingProfitExclOi;
	}
	public void setOperatingProfitExclOi(Double operatingProfitExclOi) {
		this.operatingProfitExclOi = operatingProfitExclOi;
	}
	public Double getOtheNonCurruntAsset() {
		return otheNonCurruntAsset;
	}
	public void setOtheNonCurruntAsset(Double otheNonCurruntAsset) {
		this.otheNonCurruntAsset = otheNonCurruntAsset;
	}
	public Double getOtherBorrowing() {
		return otherBorrowing;
	}
	public void setOtherBorrowing(Double otherBorrowing) {
		this.otherBorrowing = otherBorrowing;
	}
	public Double getOtherCurruntAsset() {
		return otherCurruntAsset;
	}
	public void setOtherCurruntAsset(Double otherCurruntAsset) {
		this.otherCurruntAsset = otherCurruntAsset;
	}
	public Double getOtherCurruntLiablities() {
		return otherCurruntLiablities;
	}
	public void setOtherCurruntLiablities(Double otherCurruntLiablities) {
		this.otherCurruntLiablities = otherCurruntLiablities;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getOtherInvestment() {
		return otherInvestment;
	}
	public void setOtherInvestment(Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}
	public Double getOtherLongTermLiablities() {
		return otherLongTermLiablities;
	}
	public void setOtherLongTermLiablities(Double otherLongTermLiablities) {
		this.otherLongTermLiablities = otherLongTermLiablities;
	}
	public Double getOtherReserveAndSurplus() {
		return otherReserveAndSurplus;
	}
	public void setOtherReserveAndSurplus(Double otherReserveAndSurplus) {
		this.otherReserveAndSurplus = otherReserveAndSurplus;
	}
	public Double getPatGrowthCapital() {
		return patGrowthCapital;
	}
	public void setPatGrowthCapital(Double patGrowthCapital) {
		this.patGrowthCapital = patGrowthCapital;
	}
	public Double getPatm() {
		return patm;
	}
	public void setPatm(Double patm) {
		this.patm = patm;
	}
	public Double getPbdt() {
		return pbdt;
	}
	public void setPbdt(Double pbdt) {
		this.pbdt = pbdt;
	}
	public Double getPowerAndFuelCost() {
		return powerAndFuelCost;
	}
	public void setPowerAndFuelCost(Double powerAndFuelCost) {
		this.powerAndFuelCost = powerAndFuelCost;
	}
	public Double getPreOperativeExpe() {
		return preOperativeExpe;
	}
	public void setPreOperativeExpe(Double preOperativeExpe) {
		this.preOperativeExpe = preOperativeExpe;
	}
	public Double getProfitAfterTax() {
		return profitAfterTax;
	}
	public void setProfitAfterTax(Double profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}
	public Double getProfitBeforeTax() {
		return profitBeforeTax;
	}
	public void setProfitBeforeTax(Double profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}
	public Double getProfitBeforeTaxation() {
		return profitBeforeTaxation;
	}
	public void setProfitBeforeTaxation(Double profitBeforeTaxation) {
		this.profitBeforeTaxation = profitBeforeTaxation;
	}
	public Double getProvisionForTax() {
		return provisionForTax;
	}
	public void setProvisionForTax(Double provisionForTax) {
		this.provisionForTax = provisionForTax;
	}
	public Double getQuickRatioX() {
		return quickRatioX;
	}
	public void setQuickRatioX(Double quickRatioX) {
		this.quickRatioX = quickRatioX;
	}
	public Double getRawMaterialConsumed() {
		return rawMaterialConsumed;
	}
	public void setRawMaterialConsumed(Double rawMaterialConsumed) {
		this.rawMaterialConsumed = rawMaterialConsumed;
	}
	public Double getRevalationReserve() {
		return revalationReserve;
	}
	public void setRevalationReserve(Double revalationReserve) {
		this.revalationReserve = revalationReserve;
	}
	public Double getRoce() {
		return roce;
	}
	public void setRoce(Double roce) {
		this.roce = roce;
	}
	public Double getSalesWorkingCapital() {
		return salesWorkingCapital;
	}
	public void setSalesWorkingCapital(Double salesWorkingCapital) {
		this.salesWorkingCapital = salesWorkingCapital;
	}
	public Double getSecuredLoans() {
		return securedLoans;
	}
	public void setSecuredLoans(Double securedLoans) {
		this.securedLoans = securedLoans;
	}
	public Double getSellingAndDistriExpe() {
		return sellingAndDistriExpe;
	}
	public void setSellingAndDistriExpe(Double sellingAndDistriExpe) {
		this.sellingAndDistriExpe = sellingAndDistriExpe;
	}
	public Double getShareCapital() {
		return shareCapital;
	}
	public void setShareCapital(Double shareCapital) {
		this.shareCapital = shareCapital;
	}
	public Double getShareFaceValue() {
		return shareFaceValue;
	}
	public void setShareFaceValue(Double shareFaceValue) {
		this.shareFaceValue = shareFaceValue;
	}
	public Double getShareHolderFunds() {
		return shareHolderFunds;
	}
	public void setShareHolderFunds(Double shareHolderFunds) {
		this.shareHolderFunds = shareHolderFunds;
	}
	public Double getShareWarrantOutstandings() {
		return shareWarrantOutstandings;
	}
	public void setShareWarrantOutstandings(Double shareWarrantOutstandings) {
		this.shareWarrantOutstandings = shareWarrantOutstandings;
	}
	public Double getShortTermLoansAdvances() {
		return shortTermLoansAdvances;
	}
	public void setShortTermLoansAdvances(Double shortTermLoansAdvances) {
		this.shortTermLoansAdvances = shortTermLoansAdvances;
	}
	public Double getShortTermProvision() {
		return shortTermProvision;
	}
	public void setShortTermProvision(Double shortTermProvision) {
		this.shortTermProvision = shortTermProvision;
	}
	public Double getSundryDebtors() {
		return sundryDebtors;
	}
	public void setSundryDebtors(Double sundryDebtors) {
		this.sundryDebtors = sundryDebtors;
	}
	public Double getTaxPaid() {
		return taxPaid;
	}
	public void setTaxPaid(Double taxPaid) {
		this.taxPaid = taxPaid;
	}
	public Double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public Double getTotalCurruntAsset() {
		return totalCurruntAsset;
	}
	public void setTotalCurruntAsset(Double totalCurruntAsset) {
		this.totalCurruntAsset = totalCurruntAsset;
	}
	public Double getTotalCurruntLiablities() {
		return totalCurruntLiablities;
	}
	public void setTotalCurruntLiablities(Double totalCurruntLiablities) {
		this.totalCurruntLiablities = totalCurruntLiablities;
	}
	public Double getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(Double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public Double getTotalLiablities() {
		return totalLiablities;
	}
	public void setTotalLiablities(Double totalLiablities) {
		this.totalLiablities = totalLiablities;
	}
	public Double getTotalNonCurruntAsset() {
		return totalNonCurruntAsset;
	}
	public void setTotalNonCurruntAsset(Double totalNonCurruntAsset) {
		this.totalNonCurruntAsset = totalNonCurruntAsset;
	}
	public Double getTotalNonCurruntLiablities() {
		return totalNonCurruntLiablities;
	}
	public void setTotalNonCurruntLiablities(Double totalNonCurruntLiablities) {
		this.totalNonCurruntLiablities = totalNonCurruntLiablities;
	}
	public Double getTradePayables() {
		return tradePayables;
	}
	public void setTradePayables(Double tradePayables) {
		this.tradePayables = tradePayables;
	}
	public Double getUnsecuredLoansOthers() {
		return unsecuredLoansOthers;
	}
	public void setUnsecuredLoansOthers(Double unsecuredLoansOthers) {
		this.unsecuredLoansOthers = unsecuredLoansOthers;
	}
	public Double getUnsecuredLoansPromoters() {
		return unsecuredLoansPromoters;
	}
	public void setUnsecuredLoansPromoters(Double unsecuredLoansPromoters) {
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Double getOtherIncomeNeedTocCheckAsset() {
		return otherIncomeNeedTocCheckAsset;
	}

	public void setOtherIncomeNeedTocCheckAsset(Double otherIncomeNeedTocCheckAsset) {
		this.otherIncomeNeedTocCheckAsset = otherIncomeNeedTocCheckAsset;
	}

	public Double getOtherIncomeNeedTocCheckOp() {
		return otherIncomeNeedTocCheckOp;
	}

	public void setOtherIncomeNeedTocCheckOp(Double otherIncomeNeedTocCheckOp) {
		this.otherIncomeNeedTocCheckOp = otherIncomeNeedTocCheckOp;
	}

	public Double getOtherIncomeNeedTocCheckLia() {
		return otherIncomeNeedTocCheckLia;
	}

	public void setOtherIncomeNeedTocCheckLia(Double otherIncomeNeedTocCheckLia) {
		this.otherIncomeNeedTocCheckLia = otherIncomeNeedTocCheckLia;
	}

	
	
	
}
