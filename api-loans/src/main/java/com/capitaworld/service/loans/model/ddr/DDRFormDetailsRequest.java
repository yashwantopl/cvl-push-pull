package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFormDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long applicationId;
	private Long userId;
	private String godownStockyard;
	private String companySuccessPlan;
	private String detailsOfBankingArrangement;
	private Double salesOfAutomobileDivision;
	private Double othersSales;
	private Double totalConsolidatedSales;
	private Double corporateSale;
	private Double corporateSaleToTotalSale;
	private Double corporateSaleToAutomobileDivSale;
	private Double outsideLoans;
	private Double loansFromFamilyMembersRelative;
	private String fallInSales;
	private String reasonForSalesDecline;
	private String negativeProfit;
	private String fallInProfit;
	private String reasonForProfitDecline;
	private String provisionalSalesFigure;
	private String salesBreakupByProduct;
	private String isAnySeasonalPatternInSales;
	private String outstandingDuesAmount;
	private String outstandingDuesAge;
	private String outstandingDuesComment;
	private Double  summaryOfDebtorsAgeing0_30Days;
	private Double summaryOfDebtorsAgeing31_60Days;
	private Double summaryOfDebtorsAgeing61_90Days;
	private Double summaryOfDebtorsAgeing91_180Days;
	private Double summaryOfDebtorsAgeingGreater180Days;
	private Double summaryOfDebtorsAgeingTotal;
	private String avgDebtorTurnoverPeriod;
	private String chequeBouncesDuringLast6months;
	private String summaryOfDebtorsAgeingComment;
	private String avgTurnoverPeriod;
	private String creditorsComment;
	private Integer businessWhetherSsiOrNot;
	private Double investmentInPlantAndMachinery;
	private String majorClients;
	private String creditPeriodEnjoyedFromSuppliers;
	private String creditPeriodExtendedToBuyers;
	private String otherSourceOfIncome;
	private String otherBusinessInFamilyName;
	private String operatingAddComment;
	private String othersDetailsComment;
	private String creaditCardHeldByCustComment;
	private Integer fieldAuditReport;
	private Integer auditedFinancialsForLast3years;
	private Integer provisionalFinancialsForCurrentYear;
	private Integer itrForLast3years;
	private Integer sanctionLetter;
	private Integer bankStatementOfLast12months;
	private Integer debtorsList;
	private Integer financialFigures;
	private Integer moaOfTheCompany;
	private Integer panCardOfTheCompany;
	private Integer resolutionAndForm32forAdditionOfDirector;
	private Integer centralSalesTaxRegistrationOfCompany;
	private Integer centralExciseRegistrationOfCompany;
	private Integer vatRegistrationOfCompany;
	private Integer letterOfIntentFromFundProviders;
	private Integer panCardAndResidenceAddProofOfDirectors;
	private Integer caCertifiedNetworthStatement;
	private Integer irrOfAllDirectorsForLast2years;
	private Integer listOfDirectors;
	private Integer listOfShareholdersAndShareHoldingPatter;
	private String summaryOfBservations;
	private Boolean isActive;
	
	List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList = new ArrayList<DDRAuthorizedSignDetailsRequest>();
	List<DDRCreditCardDetailsRequest> dDRCreditCardDetailsList = new ArrayList<DDRCreditCardDetailsRequest>();
	List<DDRCreditorsDetailsRequest> dDRCreditorsDetailsList = new ArrayList<DDRCreditorsDetailsRequest>();
	List<DDROfficeDetailsRequest> dDRRegisteredOfficeList = new ArrayList<DDROfficeDetailsRequest>();
	List<DDROfficeDetailsRequest> dDROperatingOfficeList = new ArrayList<DDROfficeDetailsRequest>();
	List<DDROtherBankLoanDetailsRequest> dDROtherBankLoanDetailsList = new ArrayList<DDROtherBankLoanDetailsRequest>();
	List<DDRRelWithDbsDetailsRequest> dDRRelWithDbsDetailsList = new ArrayList<DDRRelWithDbsDetailsRequest>();
	List<DDRVehiclesOwnedDetailsRequest> dDRVehiclesOwnedDetailsList = new ArrayList<DDRVehiclesOwnedDetailsRequest>();
	List<DDRFinancialSummaryRequest> dDRFinancialSummaryList = new ArrayList<DDRFinancialSummaryRequest>();
	List<DDRFamilyDirectorsDetailsRequest> dDRFamilyDirectorsList = new ArrayList<DDRFamilyDirectorsDetailsRequest>();
	
	
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getGodownStockyard() {
		return godownStockyard;
	}
	public void setGodownStockyard(String godownStockyard) {
		this.godownStockyard = godownStockyard;
	}
	public String getCompanySuccessPlan() {
		return companySuccessPlan;
	}
	public void setCompanySuccessPlan(String companySuccessPlan) {
		this.companySuccessPlan = companySuccessPlan;
	}
	public String getDetailsOfBankingArrangement() {
		return detailsOfBankingArrangement;
	}
	public void setDetailsOfBankingArrangement(String detailsOfBankingArrangement) {
		this.detailsOfBankingArrangement = detailsOfBankingArrangement;
	}
	public Double getSalesOfAutomobileDivision() {
		return salesOfAutomobileDivision;
	}
	public void setSalesOfAutomobileDivision(Double salesOfAutomobileDivision) {
		this.salesOfAutomobileDivision = salesOfAutomobileDivision;
	}
	public Double getOthersSales() {
		return othersSales;
	}
	public void setOthersSales(Double othersSales) {
		this.othersSales = othersSales;
	}
	public Double getTotalConsolidatedSales() {
		return totalConsolidatedSales;
	}
	public void setTotalConsolidatedSales(Double totalConsolidatedSales) {
		this.totalConsolidatedSales = totalConsolidatedSales;
	}
	public Double getCorporateSale() {
		return corporateSale;
	}
	public void setCorporateSale(Double corporateSale) {
		this.corporateSale = corporateSale;
	}
	public Double getCorporateSaleToTotalSale() {
		return corporateSaleToTotalSale;
	}
	public void setCorporateSaleToTotalSale(Double corporateSaleToTotalSale) {
		this.corporateSaleToTotalSale = corporateSaleToTotalSale;
	}
	public Double getCorporateSaleToAutomobileDivSale() {
		return corporateSaleToAutomobileDivSale;
	}
	public void setCorporateSaleToAutomobileDivSale(Double corporateSaleToAutomobileDivSale) {
		this.corporateSaleToAutomobileDivSale = corporateSaleToAutomobileDivSale;
	}
	public Double getOutsideLoans() {
		return outsideLoans;
	}
	public void setOutsideLoans(Double outsideLoans) {
		this.outsideLoans = outsideLoans;
	}
	public Double getLoansFromFamilyMembersRelative() {
		return loansFromFamilyMembersRelative;
	}
	public void setLoansFromFamilyMembersRelative(Double loansFromFamilyMembersRelative) {
		this.loansFromFamilyMembersRelative = loansFromFamilyMembersRelative;
	}
	public String getFallInSales() {
		return fallInSales;
	}
	public void setFallInSales(String fallInSales) {
		this.fallInSales = fallInSales;
	}
	public String getReasonForSalesDecline() {
		return reasonForSalesDecline;
	}
	public void setReasonForSalesDecline(String reasonForSalesDecline) {
		this.reasonForSalesDecline = reasonForSalesDecline;
	}
	public String getNegativeProfit() {
		return negativeProfit;
	}
	public void setNegativeProfit(String negativeProfit) {
		this.negativeProfit = negativeProfit;
	}
	public String getFallInProfit() {
		return fallInProfit;
	}
	public void setFallInProfit(String fallInProfit) {
		this.fallInProfit = fallInProfit;
	}
	public String getReasonForProfitDecline() {
		return reasonForProfitDecline;
	}
	public void setReasonForProfitDecline(String reasonForProfitDecline) {
		this.reasonForProfitDecline = reasonForProfitDecline;
	}
	public String getProvisionalSalesFigure() {
		return provisionalSalesFigure;
	}
	public void setProvisionalSalesFigure(String provisionalSalesFigure) {
		this.provisionalSalesFigure = provisionalSalesFigure;
	}
	public String getSalesBreakupByProduct() {
		return salesBreakupByProduct;
	}
	public void setSalesBreakupByProduct(String salesBreakupByProduct) {
		this.salesBreakupByProduct = salesBreakupByProduct;
	}
	public String getIsAnySeasonalPatternInSales() {
		return isAnySeasonalPatternInSales;
	}
	public void setIsAnySeasonalPatternInSales(String isAnySeasonalPatternInSales) {
		this.isAnySeasonalPatternInSales = isAnySeasonalPatternInSales;
	}
	public String getOutstandingDuesAmount() {
		return outstandingDuesAmount;
	}
	public void setOutstandingDuesAmount(String outstandingDuesAmount) {
		this.outstandingDuesAmount = outstandingDuesAmount;
	}
	public String getOutstandingDuesAge() {
		return outstandingDuesAge;
	}
	public void setOutstandingDuesAge(String outstandingDuesAge) {
		this.outstandingDuesAge = outstandingDuesAge;
	}
	public String getOutstandingDuesComment() {
		return outstandingDuesComment;
	}
	public void setOutstandingDuesComment(String outstandingDuesComment) {
		this.outstandingDuesComment = outstandingDuesComment;
	}
	public Double getSummaryOfDebtorsAgeing0_30Days() {
		return summaryOfDebtorsAgeing0_30Days;
	}
	public void setSummaryOfDebtorsAgeing0_30Days(Double summaryOfDebtorsAgeing0_30Days) {
		this.summaryOfDebtorsAgeing0_30Days = summaryOfDebtorsAgeing0_30Days;
	}
	public Double getSummaryOfDebtorsAgeing31_60Days() {
		return summaryOfDebtorsAgeing31_60Days;
	}
	public void setSummaryOfDebtorsAgeing31_60Days(Double summaryOfDebtorsAgeing31_60Days) {
		this.summaryOfDebtorsAgeing31_60Days = summaryOfDebtorsAgeing31_60Days;
	}
	public Double getSummaryOfDebtorsAgeing61_90Days() {
		return summaryOfDebtorsAgeing61_90Days;
	}
	public void setSummaryOfDebtorsAgeing61_90Days(Double summaryOfDebtorsAgeing61_90Days) {
		this.summaryOfDebtorsAgeing61_90Days = summaryOfDebtorsAgeing61_90Days;
	}
	public Double getSummaryOfDebtorsAgeing91_180Days() {
		return summaryOfDebtorsAgeing91_180Days;
	}
	public void setSummaryOfDebtorsAgeing91_180Days(Double summaryOfDebtorsAgeing91_180Days) {
		this.summaryOfDebtorsAgeing91_180Days = summaryOfDebtorsAgeing91_180Days;
	}
	public Double getSummaryOfDebtorsAgeingGreater180Days() {
		return summaryOfDebtorsAgeingGreater180Days;
	}
	public void setSummaryOfDebtorsAgeingGreater180Days(Double summaryOfDebtorsAgeingGreater180Days) {
		this.summaryOfDebtorsAgeingGreater180Days = summaryOfDebtorsAgeingGreater180Days;
	}
	public Double getSummaryOfDebtorsAgeingTotal() {
		return summaryOfDebtorsAgeingTotal;
	}
	public void setSummaryOfDebtorsAgeingTotal(Double summaryOfDebtorsAgeingTotal) {
		this.summaryOfDebtorsAgeingTotal = summaryOfDebtorsAgeingTotal;
	}
	public String getAvgDebtorTurnoverPeriod() {
		return avgDebtorTurnoverPeriod;
	}
	public void setAvgDebtorTurnoverPeriod(String avgDebtorTurnoverPeriod) {
		this.avgDebtorTurnoverPeriod = avgDebtorTurnoverPeriod;
	}
	public String getChequeBouncesDuringLast6months() {
		return chequeBouncesDuringLast6months;
	}
	public void setChequeBouncesDuringLast6months(String chequeBouncesDuringLast6months) {
		this.chequeBouncesDuringLast6months = chequeBouncesDuringLast6months;
	}
	public String getSummaryOfDebtorsAgeingComment() {
		return summaryOfDebtorsAgeingComment;
	}
	public void setSummaryOfDebtorsAgeingComment(String summaryOfDebtorsAgeingComment) {
		this.summaryOfDebtorsAgeingComment = summaryOfDebtorsAgeingComment;
	}
	public String getAvgTurnoverPeriod() {
		return avgTurnoverPeriod;
	}
	public void setAvgTurnoverPeriod(String avgTurnoverPeriod) {
		this.avgTurnoverPeriod = avgTurnoverPeriod;
	}
	public String getCreditorsComment() {
		return creditorsComment;
	}
	public void setCreditorsComment(String creditorsComment) {
		this.creditorsComment = creditorsComment;
	}
	public Integer getBusinessWhetherSsiOrNot() {
		return businessWhetherSsiOrNot;
	}
	public void setBusinessWhetherSsiOrNot(Integer businessWhetherSsiOrNot) {
		this.businessWhetherSsiOrNot = businessWhetherSsiOrNot;
	}
	public Double getInvestmentInPlantAndMachinery() {
		return investmentInPlantAndMachinery;
	}
	public void setInvestmentInPlantAndMachinery(Double investmentInPlantAndMachinery) {
		this.investmentInPlantAndMachinery = investmentInPlantAndMachinery;
	}
	public String getMajorClients() {
		return majorClients;
	}
	public void setMajorClients(String majorClients) {
		this.majorClients = majorClients;
	}
	public String getCreditPeriodEnjoyedFromSuppliers() {
		return creditPeriodEnjoyedFromSuppliers;
	}
	public void setCreditPeriodEnjoyedFromSuppliers(String creditPeriodEnjoyedFromSuppliers) {
		this.creditPeriodEnjoyedFromSuppliers = creditPeriodEnjoyedFromSuppliers;
	}
	public String getCreditPeriodExtendedToBuyers() {
		return creditPeriodExtendedToBuyers;
	}
	public void setCreditPeriodExtendedToBuyers(String creditPeriodExtendedToBuyers) {
		this.creditPeriodExtendedToBuyers = creditPeriodExtendedToBuyers;
	}
	public String getOtherSourceOfIncome() {
		return otherSourceOfIncome;
	}
	public void setOtherSourceOfIncome(String otherSourceOfIncome) {
		this.otherSourceOfIncome = otherSourceOfIncome;
	}
	public String getOtherBusinessInFamilyName() {
		return otherBusinessInFamilyName;
	}
	public void setOtherBusinessInFamilyName(String otherBusinessInFamilyName) {
		this.otherBusinessInFamilyName = otherBusinessInFamilyName;
	}
	public String getOperatingAddComment() {
		return operatingAddComment;
	}
	public void setOperatingAddComment(String operatingAddComment) {
		this.operatingAddComment = operatingAddComment;
	}
	public String getOthersDetailsComment() {
		return othersDetailsComment;
	}
	public void setOthersDetailsComment(String othersDetailsComment) {
		this.othersDetailsComment = othersDetailsComment;
	}
	public String getCreaditCardHeldByCustComment() {
		return creaditCardHeldByCustComment;
	}
	public void setCreaditCardHeldByCustComment(String creaditCardHeldByCustComment) {
		this.creaditCardHeldByCustComment = creaditCardHeldByCustComment;
	}
	public Integer getFieldAuditReport() {
		return fieldAuditReport;
	}
	public void setFieldAuditReport(Integer fieldAuditReport) {
		this.fieldAuditReport = fieldAuditReport;
	}
	public Integer getAuditedFinancialsForLast3years() {
		return auditedFinancialsForLast3years;
	}
	public void setAuditedFinancialsForLast3years(Integer auditedFinancialsForLast3years) {
		this.auditedFinancialsForLast3years = auditedFinancialsForLast3years;
	}
	public Integer getProvisionalFinancialsForCurrentYear() {
		return provisionalFinancialsForCurrentYear;
	}
	public void setProvisionalFinancialsForCurrentYear(Integer provisionalFinancialsForCurrentYear) {
		this.provisionalFinancialsForCurrentYear = provisionalFinancialsForCurrentYear;
	}
	public Integer getItrForLast3years() {
		return itrForLast3years;
	}
	public void setItrForLast3years(Integer itrForLast3years) {
		this.itrForLast3years = itrForLast3years;
	}
	public Integer getSanctionLetter() {
		return sanctionLetter;
	}
	public void setSanctionLetter(Integer sanctionLetter) {
		this.sanctionLetter = sanctionLetter;
	}
	public Integer getBankStatementOfLast12months() {
		return bankStatementOfLast12months;
	}
	public void setBankStatementOfLast12months(Integer bankStatementOfLast12months) {
		this.bankStatementOfLast12months = bankStatementOfLast12months;
	}
	public Integer getDebtorsList() {
		return debtorsList;
	}
	public void setDebtorsList(Integer debtorsList) {
		this.debtorsList = debtorsList;
	}
	public Integer getFinancialFigures() {
		return financialFigures;
	}
	public void setFinancialFigures(Integer financialFigures) {
		this.financialFigures = financialFigures;
	}
	public Integer getMoaOfTheCompany() {
		return moaOfTheCompany;
	}
	public void setMoaOfTheCompany(Integer moaOfTheCompany) {
		this.moaOfTheCompany = moaOfTheCompany;
	}
	public Integer getPanCardOfTheCompany() {
		return panCardOfTheCompany;
	}
	public void setPanCardOfTheCompany(Integer panCardOfTheCompany) {
		this.panCardOfTheCompany = panCardOfTheCompany;
	}
	public Integer getResolutionAndForm32forAdditionOfDirector() {
		return resolutionAndForm32forAdditionOfDirector;
	}
	public void setResolutionAndForm32forAdditionOfDirector(Integer resolutionAndForm32forAdditionOfDirector) {
		this.resolutionAndForm32forAdditionOfDirector = resolutionAndForm32forAdditionOfDirector;
	}
	public Integer getCentralSalesTaxRegistrationOfCompany() {
		return centralSalesTaxRegistrationOfCompany;
	}
	public void setCentralSalesTaxRegistrationOfCompany(Integer centralSalesTaxRegistrationOfCompany) {
		this.centralSalesTaxRegistrationOfCompany = centralSalesTaxRegistrationOfCompany;
	}
	public Integer getCentralExciseRegistrationOfCompany() {
		return centralExciseRegistrationOfCompany;
	}
	public void setCentralExciseRegistrationOfCompany(Integer centralExciseRegistrationOfCompany) {
		this.centralExciseRegistrationOfCompany = centralExciseRegistrationOfCompany;
	}
	public Integer getVatRegistrationOfCompany() {
		return vatRegistrationOfCompany;
	}
	public void setVatRegistrationOfCompany(Integer vatRegistrationOfCompany) {
		this.vatRegistrationOfCompany = vatRegistrationOfCompany;
	}
	public Integer getLetterOfIntentFromFundProviders() {
		return letterOfIntentFromFundProviders;
	}
	public void setLetterOfIntentFromFundProviders(Integer letterOfIntentFromFundProviders) {
		this.letterOfIntentFromFundProviders = letterOfIntentFromFundProviders;
	}
	public Integer getPanCardAndResidenceAddProofOfDirectors() {
		return panCardAndResidenceAddProofOfDirectors;
	}
	public void setPanCardAndResidenceAddProofOfDirectors(Integer panCardAndResidenceAddProofOfDirectors) {
		this.panCardAndResidenceAddProofOfDirectors = panCardAndResidenceAddProofOfDirectors;
	}
	public Integer getCaCertifiedNetworthStatement() {
		return caCertifiedNetworthStatement;
	}
	public void setCaCertifiedNetworthStatement(Integer caCertifiedNetworthStatement) {
		this.caCertifiedNetworthStatement = caCertifiedNetworthStatement;
	}
	public Integer getIrrOfAllDirectorsForLast2years() {
		return irrOfAllDirectorsForLast2years;
	}
	public void setIrrOfAllDirectorsForLast2years(Integer irrOfAllDirectorsForLast2years) {
		this.irrOfAllDirectorsForLast2years = irrOfAllDirectorsForLast2years;
	}
	public Integer getListOfDirectors() {
		return listOfDirectors;
	}
	public void setListOfDirectors(Integer listOfDirectors) {
		this.listOfDirectors = listOfDirectors;
	}
	public Integer getListOfShareholdersAndShareHoldingPatter() {
		return listOfShareholdersAndShareHoldingPatter;
	}
	public void setListOfShareholdersAndShareHoldingPatter(Integer listOfShareholdersAndShareHoldingPatter) {
		this.listOfShareholdersAndShareHoldingPatter = listOfShareholdersAndShareHoldingPatter;
	}
	public String getSummaryOfBservations() {
		return summaryOfBservations;
	}
	public void setSummaryOfBservations(String summaryOfBservations) {
		this.summaryOfBservations = summaryOfBservations;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<DDRAuthorizedSignDetailsRequest> getdDRAuthSignDetailsList() {
		return dDRAuthSignDetailsList;
	}
	public void setdDRAuthSignDetailsList(List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList) {
		this.dDRAuthSignDetailsList = dDRAuthSignDetailsList;
	}
	public List<DDRCreditCardDetailsRequest> getdDRCreditCardDetailsList() {
		return dDRCreditCardDetailsList;
	}
	public void setdDRCreditCardDetailsList(List<DDRCreditCardDetailsRequest> dDRCreditCardDetailsList) {
		this.dDRCreditCardDetailsList = dDRCreditCardDetailsList;
	}
	public List<DDRCreditorsDetailsRequest> getdDRCreditorsDetailsList() {
		return dDRCreditorsDetailsList;
	}
	public void setdDRCreditorsDetailsList(List<DDRCreditorsDetailsRequest> dDRCreditorsDetailsList) {
		this.dDRCreditorsDetailsList = dDRCreditorsDetailsList;
	}
	
	public List<DDROtherBankLoanDetailsRequest> getdDROtherBankLoanDetailsList() {
		return dDROtherBankLoanDetailsList;
	}
	public void setdDROtherBankLoanDetailsList(List<DDROtherBankLoanDetailsRequest> dDROtherBankLoanDetailsList) {
		this.dDROtherBankLoanDetailsList = dDROtherBankLoanDetailsList;
	}
	public List<DDRRelWithDbsDetailsRequest> getdDRRelWithDbsDetailsList() {
		return dDRRelWithDbsDetailsList;
	}
	public void setdDRRelWithDbsDetailsList(List<DDRRelWithDbsDetailsRequest> dDRRelWithDbsDetailsList) {
		this.dDRRelWithDbsDetailsList = dDRRelWithDbsDetailsList;
	}
	public List<DDRVehiclesOwnedDetailsRequest> getdDRVehiclesOwnedDetailsRequest() {
		return dDRVehiclesOwnedDetailsList;
	}
	public void setdDRVehiclesOwnedDetailsRequest(List<DDRVehiclesOwnedDetailsRequest> dDRVehiclesOwnedDetailsRequest) {
		this.dDRVehiclesOwnedDetailsList = dDRVehiclesOwnedDetailsRequest;
	}
	
	public List<DDROfficeDetailsRequest> getdDRRegisteredOfficeList() {
		return dDRRegisteredOfficeList;
	}
	public void setdDRRegisteredOfficeList(List<DDROfficeDetailsRequest> dDRRegisteredOfficeList) {
		this.dDRRegisteredOfficeList = dDRRegisteredOfficeList;
	}
	public List<DDROfficeDetailsRequest> getdDROperatingOfficeList() {
		return dDROperatingOfficeList;
	}
	public void setdDROperatingOfficeList(List<DDROfficeDetailsRequest> dDROperatingOfficeList) {
		this.dDROperatingOfficeList = dDROperatingOfficeList;
	}
	public List<DDRVehiclesOwnedDetailsRequest> getdDRVehiclesOwnedDetailsList() {
		return dDRVehiclesOwnedDetailsList;
	}
	public void setdDRVehiclesOwnedDetailsList(List<DDRVehiclesOwnedDetailsRequest> dDRVehiclesOwnedDetailsList) {
		this.dDRVehiclesOwnedDetailsList = dDRVehiclesOwnedDetailsList;
	}
	public List<DDRFinancialSummaryRequest> getdDRFinancialSummaryList() {
		return dDRFinancialSummaryList;
	}
	public void setdDRFinancialSummaryList(List<DDRFinancialSummaryRequest> dDRFinancialSummaryList) {
		this.dDRFinancialSummaryList = dDRFinancialSummaryList;
	}
	
	
	public List<DDRFamilyDirectorsDetailsRequest> getdDRFamilyDirectorsList() {
		return dDRFamilyDirectorsList;
	}
	public void setdDRFamilyDirectorsList(List<DDRFamilyDirectorsDetailsRequest> dDRFamilyDirectorsList) {
		this.dDRFamilyDirectorsList = dDRFamilyDirectorsList;
	}
	@Override
	public String toString() {
		return "DDRFormDetailsRequest [id=" + id + ", applicationId=" + applicationId + ", userId=" + userId
				+ ", godownStockyard=" + godownStockyard + ", companySuccessPlan=" + companySuccessPlan
				+ ", detailsOfBankingArrangement=" + detailsOfBankingArrangement + ", salesOfAutomobileDivision="
				+ salesOfAutomobileDivision + ", othersSales=" + othersSales + ", totalConsolidatedSales="
				+ totalConsolidatedSales + ", corporateSale=" + corporateSale + ", corporateSaleToTotalSale="
				+ corporateSaleToTotalSale + ", corporateSaleToAutomobileDivSale=" + corporateSaleToAutomobileDivSale
				+ ", outsideLoans=" + outsideLoans + ", loansFromFamilyMembersRelative="
				+ loansFromFamilyMembersRelative + ", fallInSales=" + fallInSales + ", reasonForSalesDecline="
				+ reasonForSalesDecline + ", negativeProfit=" + negativeProfit + ", fallInProfit=" + fallInProfit
				+ ", reasonForProfitDecline=" + reasonForProfitDecline + ", provisionalSalesFigure="
				+ provisionalSalesFigure + ", salesBreakupByProduct=" + salesBreakupByProduct
				+ ", isAnySeasonalPatternInSales=" + isAnySeasonalPatternInSales + ", outstandingDuesAmount="
				+ outstandingDuesAmount + ", outstandingDuesAge=" + outstandingDuesAge + ", outstandingDuesComment="
				+ outstandingDuesComment + ", summaryOfDebtorsAgeing0_30Days=" + summaryOfDebtorsAgeing0_30Days
				+ ", summaryOfDebtorsAgeing31_60Days=" + summaryOfDebtorsAgeing31_60Days
				+ ", summaryOfDebtorsAgeing61_90Days=" + summaryOfDebtorsAgeing61_90Days
				+ ", summaryOfDebtorsAgeing91_180Days=" + summaryOfDebtorsAgeing91_180Days
				+ ", summaryOfDebtorsAgeingGreater180Days=" + summaryOfDebtorsAgeingGreater180Days
				+ ", summaryOfDebtorsAgeingTotal=" + summaryOfDebtorsAgeingTotal + ", avgDebtorTurnoverPeriod="
				+ avgDebtorTurnoverPeriod + ", chequeBouncesDuringLast6months=" + chequeBouncesDuringLast6months
				+ ", summaryOfDebtorsAgeingComment=" + summaryOfDebtorsAgeingComment + ", avgTurnoverPeriod="
				+ avgTurnoverPeriod + ", creditorsComment=" + creditorsComment + ", businessWhetherSsiOrNot="
				+ businessWhetherSsiOrNot + ", investmentInPlantAndMachinery=" + investmentInPlantAndMachinery
				+ ", majorClients=" + majorClients + ", creditPeriodEnjoyedFromSuppliers="
				+ creditPeriodEnjoyedFromSuppliers + ", creditPeriodExtendedToBuyers=" + creditPeriodExtendedToBuyers
				+ ", otherSourceOfIncome=" + otherSourceOfIncome + ", otherBusinessInFamilyName="
				+ otherBusinessInFamilyName + ", operatingAddComment=" + operatingAddComment + ", othersDetailsComment="
				+ othersDetailsComment + ", creaditCardHeldByCustComment=" + creaditCardHeldByCustComment
				+ ", fieldAuditReport=" + fieldAuditReport + ", auditedFinancialsForLast3years="
				+ auditedFinancialsForLast3years + ", provisionalFinancialsForCurrentYear="
				+ provisionalFinancialsForCurrentYear + ", itrForLast3years=" + itrForLast3years + ", sanctionLetter="
				+ sanctionLetter + ", bankStatementOfLast12months=" + bankStatementOfLast12months + ", debtorsList="
				+ debtorsList + ", financialFigures=" + financialFigures + ", moaOfTheCompany=" + moaOfTheCompany
				+ ", panCardOfTheCompany=" + panCardOfTheCompany + ", resolutionAndForm32forAdditionOfDirector="
				+ resolutionAndForm32forAdditionOfDirector + ", centralSalesTaxRegistrationOfCompany="
				+ centralSalesTaxRegistrationOfCompany + ", centralExciseRegistrationOfCompany="
				+ centralExciseRegistrationOfCompany + ", vatRegistrationOfCompany=" + vatRegistrationOfCompany
				+ ", letterOfIntentFromFundProviders=" + letterOfIntentFromFundProviders
				+ ", panCardAndResidenceAddProofOfDirectors=" + panCardAndResidenceAddProofOfDirectors
				+ ", caCertifiedNetworthStatement=" + caCertifiedNetworthStatement + ", irrOfAllDirectorsForLast2years="
				+ irrOfAllDirectorsForLast2years + ", listOfDirectors=" + listOfDirectors
				+ ", listOfShareholdersAndShareHoldingPatter=" + listOfShareholdersAndShareHoldingPatter
				+ ", summaryOfBservations=" + summaryOfBservations + ", isActive=" + isActive
				+ "]";
	}
	
	


}
