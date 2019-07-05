package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.List;


public class CMADetailResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 //asset	
		private Double receivableOtherThanDefferred; //D16
		private Double exportReceivables; //D18
		private Double inventory; //D22
		private Double advanceToSupplierRawMaterials; //D37
		//liabilities
		private Double sundryCreditors; //D19
		private Double advancePaymentsFromCustomers;//D21
		
		private Double domesticSales; //D8
		private Double exportSales; //D9
		private Double netProfitOrLoss; //D78
		private Double depreciation; //D34
		private Double interest;  //D62
		private Double provisionForDeferredTax; //D77
		private Double grossBlock; //D45
		private Double totalCurrentAssets ; //D43 Assets
		private Double subTotalA;  // D15 Liabilities
		private Double totalCurrentLiabilities; // D37 Liabilities

		private Double totalOutsideLiabilities; // D63 Liabilities
		private Double tangibleNetWorth ; // Assessts D91 same as Total Net-worth

		private Double opProfitBeforeIntrest; 	// D60 operating stmt.
		
		// FOR SBI RELATED SPEACIFIC CMA DETAILS 
		private Double sgAndACosts;
		private Double opmAndOPNS;
		private Double pbtNetSales;
		private Double cashAccurals;
		private Double pbDIT;
		private Double interestCovrageRatio;
		private Double dscr;
		private Double roce;
		// Step first Ends
		private Double netSalesTotalTangible;
		private Double pbtToTotalTangilbeAssets;
		private Double operatingCostToSales;
		private Double bankFinanceToCurrentAssests;
		private Double inventoryAndNetSales;
		private Double interestCostsOfSales;
		private Double bfGrossSales;
		
		//Movement of TNW
		private Double increaseInEquality;
		private Double addSubstractChange;

		//Synopsis of Balance Sheet:
		private Double tlInsRepayable;
		private Double provisionOtherCl;
		private Double termLoanOthers;
		private Double unsecuredLoans;
		private Double otherTermLibilities; 
		private Double reservesSurplus;
		private Double receivables;
		private Double otherCurrentAssests;
		private Double netBlock;
		private Double receivablesSixMonths;
		private Double others;
		private Double cLoans;
		private Double longTermUses;
		private Double decreaseInTermLibilities;
		//Fund Flow Cash_Flow	
		private Double increaseAndDecreaseInSundry;
		private Double increaseAndDecreaseInInventories;
		private Double increaseAndDecreaseOtherCurrentAssessts;
		private Double increaseAndDecreaseLoansAndAdvan;
		private Double increaseAndDecreaseTradeCreaditors;
		
		//Mapping - ITR - Schedule IT - Total Taxes Paid
		private Double purchaseOfFixedAssests;
		private Double salesOfFixedAssests;
		private Double increaseAndDecreaseInCapital;
		private Double increaseAndDecreaseInSecuredLoans;
		private Double increaseAndDecreaseInUnsecuredLoans;
		private Double increaseAndDecreaseSecuredWorking;
		private Double increaseInShareCapital;
		
       //Assessment of Limits	
        private Double estimatedSalesCurrentYear;
        private Double estimatedAverageReceivable;
        private Double lessOtherSourcesLike;
        
        //Assessed Bank Finance:
        
        private Double inventoryNetSales;
        private Double netSalesToTotalTangibleAssessts;
        
        private Double wcGap;
        private Double NWC;
        private Double nwcAndTCA;
        private Double crAndTCA;
        private Double bfTCA;
        private Double oclTCA;
        private Double ocaAndTCA;
        
        private Double netIncreaseInCash;
        
        List<ItrAdditionalFieldDatas> itrAdditionalFieldsDatas;
        
        // ITR Additional Fileds
        private Double dividendsFromInvestments;
    	private Double profitLossOnSaleOfFixedAssets;
    	private Double profitLossOnForexFluctuations;
    	private Double badDebtsWrittenOff;
    	
		public Double getDividendsFromInvestments() {
			return dividendsFromInvestments;
		}
		public void setDividendsFromInvestments(Double dividendsFromInvestments) {
			this.dividendsFromInvestments = dividendsFromInvestments;
		}
		public Double getProfitLossOnSaleOfFixedAssets() {
			return profitLossOnSaleOfFixedAssets;
		}
		public void setProfitLossOnSaleOfFixedAssets(Double profitLossOnSaleOfFixedAssets) {
			this.profitLossOnSaleOfFixedAssets = profitLossOnSaleOfFixedAssets;
		}
		public Double getProfitLossOnForexFluctuations() {
			return profitLossOnForexFluctuations;
		}
		public void setProfitLossOnForexFluctuations(Double profitLossOnForexFluctuations) {
			this.profitLossOnForexFluctuations = profitLossOnForexFluctuations;
		}
		public Double getBadDebtsWrittenOff() {
			return badDebtsWrittenOff;
		}
		public void setBadDebtsWrittenOff(Double badDebtsWrittenOff) {
			this.badDebtsWrittenOff = badDebtsWrittenOff;
		}
		public List<ItrAdditionalFieldDatas> getItrAdditionalFieldsDatas() {
			return itrAdditionalFieldsDatas;
		}
		public void setItrAdditionalFieldsDatas(List<ItrAdditionalFieldDatas> itrAdditionalFieldsDatas) {
			this.itrAdditionalFieldsDatas = itrAdditionalFieldsDatas;
		}
		public Double getNetIncreaseInCash() {
			return netIncreaseInCash;
		}
		public void setNetIncreaseInCash(Double netIncreaseInCash) {
			this.netIncreaseInCash = netIncreaseInCash;
		}
		public Double getReceivableOtherThanDefferred() {
			return receivableOtherThanDefferred;
		}
		public void setReceivableOtherThanDefferred(Double receivableOtherThanDefferred) {
			this.receivableOtherThanDefferred = receivableOtherThanDefferred;
		}
		public Double getExportReceivables() {
			return exportReceivables;
		}
		public void setExportReceivables(Double exportReceivables) {
			this.exportReceivables = exportReceivables;
		}
		public Double getInventory() {
			return inventory;
		}
		public void setInventory(Double inventory) {
			this.inventory = inventory;
		}
		public Double getAdvanceToSupplierRawMaterials() {
			return advanceToSupplierRawMaterials;
		}
		public void setAdvanceToSupplierRawMaterials(Double advanceToSupplierRawMaterials) {
			this.advanceToSupplierRawMaterials = advanceToSupplierRawMaterials;
		}
		public Double getSundryCreditors() {
			return sundryCreditors;
		}
		public void setSundryCreditors(Double sundryCreditors) {
			this.sundryCreditors = sundryCreditors;
		}
		public Double getAdvancePaymentsFromCustomers() {
			return advancePaymentsFromCustomers;
		}
		public void setAdvancePaymentsFromCustomers(Double advancePaymentsFromCustomers) {
			this.advancePaymentsFromCustomers = advancePaymentsFromCustomers;
		}
		public Double getDomesticSales() {
			return domesticSales;
		}
		public void setDomesticSales(Double domesticSales) {
			this.domesticSales = domesticSales;
		}
		public Double getExportSales() {
			return exportSales;
		}
		public void setExportSales(Double exportSales) {
			this.exportSales = exportSales;
		}
		public Double getNetProfitOrLoss() {
			return netProfitOrLoss;
		}
		public void setNetProfitOrLoss(Double netProfitOrLoss) {
			this.netProfitOrLoss = netProfitOrLoss;
		}
		public Double getDepreciation() {
			return depreciation;
		}
		public void setDepreciation(Double depreciation) {
			this.depreciation = depreciation;
		}
		
		public Double getInterest() {
			return interest;
		}
		public void setInterest(Double interest) {
			this.interest = interest;
		}
		
		public Double getProvisionForDeferredTax() {
			return provisionForDeferredTax;
		}
		public void setProvisionForDeferredTax(Double provisionForDeferredTax) {
			this.provisionForDeferredTax = provisionForDeferredTax;
		}
		
		public Double getGrossBlock() {
			return grossBlock;
		}
		public void setGrossBlock(Double grossBlock) {
			this.grossBlock = grossBlock;
		}
		
		public Double getTotalCurrentAssets() {
			return totalCurrentAssets;
		}
		public void setTotalCurrentAssets(Double totalCurrentAssets) {
			this.totalCurrentAssets = totalCurrentAssets;
		}
		public Double getSubTotalA() {
			return subTotalA;
		}
		public void setSubTotalA(Double subTotalA) {
			this.subTotalA = subTotalA;
		}
		
		public Double getTotalCurrentLiabilities() {
			return totalCurrentLiabilities;
		}
		public void setTotalCurrentLiabilities(Double totalCurrentLiabilities) {
			this.totalCurrentLiabilities = totalCurrentLiabilities;
		}

		public Double getTotalOutsideLiabilities() {
			return totalOutsideLiabilities;
		}
		public void setTotalOutsideLiabilities(Double totalOutsideLiabilities) {
			this.totalOutsideLiabilities = totalOutsideLiabilities;
		}
		public Double getTangibleNetWorth() {
			return tangibleNetWorth;
		}
		public void setTangibleNetWorth(Double tangibleNetWorth) {
			this.tangibleNetWorth = tangibleNetWorth;
		}

		public Double getOpProfitBeforeIntrest() {
			return opProfitBeforeIntrest;
		}
		public void setOpProfitBeforeIntrest(Double opProfitBeforeIntrest) {
			this.opProfitBeforeIntrest = opProfitBeforeIntrest;
		}
		public Double getSgAndACosts() {
			return sgAndACosts;
		}
		public void setSgAndACosts(Double sgAndACosts) {
			this.sgAndACosts = sgAndACosts;
		}
		public Double getOpmAndOPNS() {
			return opmAndOPNS;
		}
		public void setOpmAndOPNS(Double opmAndOPNS) {
			this.opmAndOPNS = opmAndOPNS;
		}
		public Double getPbtNetSales() {
			return pbtNetSales;
		}
		public void setPbtNetSales(Double pbtNetSales) {
			this.pbtNetSales = pbtNetSales;
		}
		public Double getCashAccurals() {
			return cashAccurals;
		}
		public void setCashAccurals(Double cashAccurals) {
			this.cashAccurals = cashAccurals;
		}
		public Double getPbDIT() {
			return pbDIT;
		}
		public void setPbDIT(Double pbDIT) {
			this.pbDIT = pbDIT;
		}
		public Double getInterestCovrageRatio() {
			return interestCovrageRatio;
		}
		public void setInterestCovrageRatio(Double interestCovrageRatio) {
			this.interestCovrageRatio = interestCovrageRatio;
		}
		public Double getDscr() {
			return dscr;
		}
		public void setDscr(Double dscr) {
			this.dscr = dscr;
		}
		public Double getRoce() {
			return roce;
		}
		public void setRoce(Double roce) {
			this.roce = roce;
		}
		public Double getNetSalesTotalTangible() {
			return netSalesTotalTangible;
		}
		public void setNetSalesTotalTangible(Double netSalesTotalTangible) {
			this.netSalesTotalTangible = netSalesTotalTangible;
		}
		public Double getPbtToTotalTangilbeAssets() {
			return pbtToTotalTangilbeAssets;
		}
		public void setPbtToTotalTangilbeAssets(Double pbtToTotalTangilbeAssets) {
			this.pbtToTotalTangilbeAssets = pbtToTotalTangilbeAssets;
		}
		public Double getOperatingCostToSales() {
			return operatingCostToSales;
		}
		public void setOperatingCostToSales(Double operatingCostToSales) {
			this.operatingCostToSales = operatingCostToSales;
		}
		public Double getBankFinanceToCurrentAssests() {
			return bankFinanceToCurrentAssests;
		}
		public void setBankFinanceToCurrentAssests(Double bankFinanceToCurrentAssests) {
			this.bankFinanceToCurrentAssests = bankFinanceToCurrentAssests;
		}
		public Double getInventoryAndNetSales() {
			return inventoryAndNetSales;
		}
		public void setInventoryAndNetSales(Double inventoryAndNetSales) {
			this.inventoryAndNetSales = inventoryAndNetSales;
		}
		public Double getInterestCostsOfSales() {
			return interestCostsOfSales;
		}
		public void setInterestCostsOfSales(Double interestCostsOfSales) {
			this.interestCostsOfSales = interestCostsOfSales;
		}
		public Double getBfGrossSales() {
			return bfGrossSales;
		}
		public void setBfGrossSales(Double bfGrossSales) {
			this.bfGrossSales = bfGrossSales;
		}
		public Double getIncreaseInEquality() {
			return increaseInEquality;
		}
		public void setIncreaseInEquality(Double increaseInEquality) {
			this.increaseInEquality = increaseInEquality;
		}
		public Double getAddSubstractChange() {
			return addSubstractChange;
		}
		public void setAddSubstractChange(Double addSubstractChange) {
			this.addSubstractChange = addSubstractChange;
		}
		public Double getTlInsRepayable() {
			return tlInsRepayable;
		}
		public void setTlInsRepayable(Double tlInsRepayable) {
			this.tlInsRepayable = tlInsRepayable;
		}
		public Double getProvisionOtherCl() {
			return provisionOtherCl;
		}
		public void setProvisionOtherCl(Double provisionOtherCl) {
			this.provisionOtherCl = provisionOtherCl;
		}
		public Double getTermLoanOthers() {
			return termLoanOthers;
		}
		public void setTermLoanOthers(Double termLoanOthers) {
			this.termLoanOthers = termLoanOthers;
		}
		public Double getUnsecuredLoans() {
			return unsecuredLoans;
		}
		public void setUnsecuredLoans(Double unsecuredLoans) {
			this.unsecuredLoans = unsecuredLoans;
		}
		public Double getOtherTermLibilities() {
			return otherTermLibilities;
		}
		public void setOtherTermLibilities(Double otherTermLibilities) {
			this.otherTermLibilities = otherTermLibilities;
		}
		public Double getReservesSurplus() {
			return reservesSurplus;
		}
		public void setReservesSurplus(Double reservesSurplus) {
			this.reservesSurplus = reservesSurplus;
		}
		public Double getReceivables() {
			return receivables;
		}
		public void setReceivables(Double receivables) {
			this.receivables = receivables;
		}
		public Double getOtherCurrentAssests() {
			return otherCurrentAssests;
		}
		public void setOtherCurrentAssests(Double otherCurrentAssests) {
			this.otherCurrentAssests = otherCurrentAssests;
		}
		public Double getNetBlock() {
			return netBlock;
		}
		public void setNetBlock(Double netBlock) {
			this.netBlock = netBlock;
		}
		public Double getReceivablesSixMonths() {
			return receivablesSixMonths;
		}
		public void setReceivablesSixMonths(Double receivablesSixMonths) {
			this.receivablesSixMonths = receivablesSixMonths;
		}
		public Double getOthers() {
			return others;
		}
		public void setOthers(Double others) {
			this.others = others;
		}
		public Double getcLoans() {
			return cLoans;
		}
		public void setcLoans(Double cLoans) {
			this.cLoans = cLoans;
		}
		public Double getLongTermUses() {
			return longTermUses;
		}
		public void setLongTermUses(Double longTermUses) {
			this.longTermUses = longTermUses;
		}
		public Double getDecreaseInTermLibilities() {
			return decreaseInTermLibilities;
		}
		public void setDecreaseInTermLibilities(Double decreaseInTermLibilities) {
			this.decreaseInTermLibilities = decreaseInTermLibilities;
		}
		public Double getIncreaseAndDecreaseInSundry() {
			return increaseAndDecreaseInSundry;
		}
		public void setIncreaseAndDecreaseInSundry(Double increaseAndDecreaseInSundry) {
			this.increaseAndDecreaseInSundry = increaseAndDecreaseInSundry;
		}
		public Double getIncreaseAndDecreaseInInventories() {
			return increaseAndDecreaseInInventories;
		}
		public void setIncreaseAndDecreaseInInventories(Double increaseAndDecreaseInInventories) {
			this.increaseAndDecreaseInInventories = increaseAndDecreaseInInventories;
		}
		public Double getIncreaseAndDecreaseOtherCurrentAssessts() {
			return increaseAndDecreaseOtherCurrentAssessts;
		}
		public void setIncreaseAndDecreaseOtherCurrentAssessts(Double increaseAndDecreaseOtherCurrentAssessts) {
			this.increaseAndDecreaseOtherCurrentAssessts = increaseAndDecreaseOtherCurrentAssessts;
		}
		public Double getIncreaseAndDecreaseLoansAndAdvan() {
			return increaseAndDecreaseLoansAndAdvan;
		}
		public void setIncreaseAndDecreaseLoansAndAdvan(Double increaseAndDecreaseLoansAndAdvan) {
			this.increaseAndDecreaseLoansAndAdvan = increaseAndDecreaseLoansAndAdvan;
		}
		public Double getIncreaseAndDecreaseTradeCreaditors() {
			return increaseAndDecreaseTradeCreaditors;
		}
		public void setIncreaseAndDecreaseTradeCreaditors(Double increaseAndDecreaseTradeCreaditors) {
			this.increaseAndDecreaseTradeCreaditors = increaseAndDecreaseTradeCreaditors;
		}
		public Double getPurchaseOfFixedAssests() {
			return purchaseOfFixedAssests;
		}
		public void setPurchaseOfFixedAssests(Double purchaseOfFixedAssests) {
			this.purchaseOfFixedAssests = purchaseOfFixedAssests;
		}
		public Double getSalesOfFixedAssests() {
			return salesOfFixedAssests;
		}
		public void setSalesOfFixedAssests(Double salesOfFixedAssests) {
			this.salesOfFixedAssests = salesOfFixedAssests;
		}
		public Double getIncreaseAndDecreaseInCapital() {
			return increaseAndDecreaseInCapital;
		}
		public void setIncreaseAndDecreaseInCapital(Double increaseAndDecreaseInCapital) {
			this.increaseAndDecreaseInCapital = increaseAndDecreaseInCapital;
		}
		public Double getIncreaseAndDecreaseInSecuredLoans() {
			return increaseAndDecreaseInSecuredLoans;
		}
		public void setIncreaseAndDecreaseInSecuredLoans(Double increaseAndDecreaseInSecuredLoans) {
			this.increaseAndDecreaseInSecuredLoans = increaseAndDecreaseInSecuredLoans;
		}
		public Double getIncreaseAndDecreaseInUnsecuredLoans() {
			return increaseAndDecreaseInUnsecuredLoans;
		}
		public void setIncreaseAndDecreaseInUnsecuredLoans(Double increaseAndDecreaseInUnsecuredLoans) {
			this.increaseAndDecreaseInUnsecuredLoans = increaseAndDecreaseInUnsecuredLoans;
		}
		public Double getIncreaseAndDecreaseSecuredWorking() {
			return increaseAndDecreaseSecuredWorking;
		}
		public void setIncreaseAndDecreaseSecuredWorking(Double increaseAndDecreaseSecuredWorking) {
			this.increaseAndDecreaseSecuredWorking = increaseAndDecreaseSecuredWorking;
		}
		public Double getIncreaseInShareCapital() {
			return increaseInShareCapital;
		}
		public void setIncreaseInShareCapital(Double increaseInShareCapital) {
			this.increaseInShareCapital = increaseInShareCapital;
		}
		public Double getEstimatedSalesCurrentYear() {
			return estimatedSalesCurrentYear;
		}
		public void setEstimatedSalesCurrentYear(Double estimatedSalesCurrentYear) {
			this.estimatedSalesCurrentYear = estimatedSalesCurrentYear;
		}
		public Double getEstimatedAverageReceivable() {
			return estimatedAverageReceivable;
		}
		public void setEstimatedAverageReceivable(Double estimatedAverageReceivable) {
			this.estimatedAverageReceivable = estimatedAverageReceivable;
		}
		public Double getLessOtherSourcesLike() {
			return lessOtherSourcesLike;
		}
		public void setLessOtherSourcesLike(Double lessOtherSourcesLike) {
			this.lessOtherSourcesLike = lessOtherSourcesLike;
		}
		public Double getInventoryNetSales() {
			return inventoryNetSales;
		}
		public void setInventoryNetSales(Double inventoryNetSales) {
			this.inventoryNetSales = inventoryNetSales;
		}
		public Double getNetSalesToTotalTangibleAssessts() {
			return netSalesToTotalTangibleAssessts;
		}
		public void setNetSalesToTotalTangibleAssessts(Double netSalesToTotalTangibleAssessts) {
			this.netSalesToTotalTangibleAssessts = netSalesToTotalTangibleAssessts;
		}
		public Double getWcGap() {
			return wcGap;
		}
		public void setWcGap(Double wcGap) {
			this.wcGap = wcGap;
		}
		public Double getNWC() {
			return NWC;
		}
		public void setNWC(Double nWC) {
			NWC = nWC;
		}
		public Double getNwcAndTCA() {
			return nwcAndTCA;
		}
		public void setNwcAndTCA(Double nwcAndTCA) {
			this.nwcAndTCA = nwcAndTCA;
		}
		public Double getCrAndTCA() {
			return crAndTCA;
		}
		public void setCrAndTCA(Double crAndTCA) {
			this.crAndTCA = crAndTCA;
		}
		public Double getBfTCA() {
			return bfTCA;
		}
		public void setBfTCA(Double bfTCA) {
			this.bfTCA = bfTCA;
		}
		public Double getOclTCA() {
			return oclTCA;
		}
		public void setOclTCA(Double oclTCA) {
			this.oclTCA = oclTCA;
		}
		public Double getOcaAndTCA() {
			return ocaAndTCA;
		}
		public void setOcaAndTCA(Double ocaAndTCA) {
			this.ocaAndTCA = ocaAndTCA;
		}
		
}
