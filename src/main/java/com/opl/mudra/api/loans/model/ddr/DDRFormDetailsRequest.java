package com.opl.mudra.api.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFormDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long applicationId;
	private Long proposalMappingId;
//	private String referenceNo;
	private Long userId;
	private String godownStockyard;
	private String companySuccessPlan;
//	private String detailsOfBankingArrangement;
//	private Double salesOfAutomobileDivision;
//	private Double othersSales;
//	private Double totalConsolidatedSales;
//	private Double corporateSale;
//	private Double corporateSaleToTotalSale;
//	private Double corporateSaleToAutomobileDivSale;
	private Double outsideLoans;
	private Double loansFromFamilyMembersRelative;
	private String fallInSales;
	private String reasonForSalesDecline;
	private String negativeProfit;
	private String fallInProfit;
	private String reasonForProfitDecline;
//	private String provisionalSalesFigure;
//	private String salesBreakupByProduct;
//	private String isAnySeasonalPatternInSales;
//	private String outstandingDuesAmount;
//	private String outstandingDuesAge;
//	private String outstandingDuesComment;
	private Double summaryOfDebtorsAgeing0_30Days;
	private Double summaryOfDebtorsAgeing31_60Days;
	private Double summaryOfDebtorsAgeing61_90Days;
	private Double summaryOfDebtorsAgeing91_180Days;
	private Double summaryOfDebtorsAgeingGreater180Days;
	private Double summaryOfDebtorsAgeingTotal;
//	private String avgDebtorTurnoverPeriod;
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
	private String businessDetailsComment;
	private String othersDetailsComment;
	private String creaditCardHeldByCustComment;
	private String fieldAuditReport;
	private String auditedFinancialsForLast3years;
	private String provisionalFinancialsForCurrentYear;
	private String itrForLast3years;
	private String sanctionLetter;
	private String bankStatementOfLast12months;
	private String debtorsList;
	private String financialFigures;
	private String moaOfTheCompany;
	private String panCardOfTheCompany;
	private String resolutionAndForm32forAdditionOfDirector;
	private String centralSalesTaxRegistrationOfCompany;
	private String centralExciseRegistrationOfCompany;
	private String vatRegistrationOfCompany;
	private String letterOfIntentFromFundProviders;
	private String panCardAndResidenceAddProofOfDirectors;
	private String caCertifiedNetworthStatement;
	private String irrOfAllDirectorsForLast2years;
	private String listOfDirectors;
	private String listOfShareholdersAndShareHoldingPatter;
//	private String summaryOfBservations;
	private Double provisionalTotalSales;
	private Double lastYearTotalSales;
	private Double lastToLastYearTotalSales;
	private String currency;
	private Boolean isActive;
	
	private String remarkOfCompanyInfo;
//	private String remarkOfDetailBankArrangement;
	private String remarkOfFinSummary;
//	private String remarkOfTotalSales;
	private String remarkOfTotalDebt;
	private String remarkOfLatestDebtList;
	private String remarkOfLatestCredList;
	private String remarkOfBusinessDetails;
	private String remarkOfPersonalDetails;
	private String remarkOfExistingBankerDetails;
	private String remarkOfNameOfAuthSignatory;
	private String remarkOfOtherDetail;
	private String remarkOfDetailOfCredCard;
//	private String remarkOfPastPreRelationship;
	private String remarkOfAnyOtherBankLoan;
	private String remarkOfDocCheckList;
	
	private String outsideLoansString;
	private String loansFromFamilyMembersRelativeString;
	
	//FOR ONLY BOB BANK(21 Sep)
	private String customerId;
	private String customerName;
	
	
	List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList = new ArrayList<DDRAuthorizedSignDetailsRequest>();
	List<DDRCreditCardDetailsRequest> dDRCreditCardDetailsList = new ArrayList<DDRCreditCardDetailsRequest>();
	List<DDRCreditorsDetailsRequest> dDRCreditorsDetailsList = new ArrayList<DDRCreditorsDetailsRequest>();
	List<DDROfficeDetailsRequest> dDRRegisteredOfficeList = new ArrayList<DDROfficeDetailsRequest>();
	List<DDROfficeDetailsRequest> dDROperatingOfficeList = new ArrayList<DDROfficeDetailsRequest>();
	List<DDROtherBankLoanDetailsRequest> dDROtherBankLoanDetailsList = new ArrayList<DDROtherBankLoanDetailsRequest>();
//	List<DDRRelWithDbsDetailsRequest> dDRRelWithDbsDetailsList = new ArrayList<DDRRelWithDbsDetailsRequest>();
	List<DDRVehiclesOwnedDetailsRequest> dDRVehiclesOwnedDetailsList = new ArrayList<DDRVehiclesOwnedDetailsRequest>();
	List<DDRFinancialSummaryRequest> dDRFinancialSummaryList = new ArrayList<DDRFinancialSummaryRequest>();
	List<DDRExistingBankerDetailRequest> existingBankerDetailList = new ArrayList<DDRExistingBankerDetailRequest>();
	List<DDRFamilyDirectorsDetailsRequest> dDRFamilyDirectorsList = new ArrayList<DDRFamilyDirectorsDetailsRequest>();
	
	private String userName;

	private String password;
	
	public String getOutsideLoansString() {
		return outsideLoansString;
	}



	public String getLoansFromFamilyMembersRelativeString() {
		return loansFromFamilyMembersRelativeString;
	}



	public void setOutsideLoansString(String outsideLoansString) {
		this.outsideLoansString = outsideLoansString;
	}



	public void setLoansFromFamilyMembersRelativeString(String loansFromFamilyMembersRelativeString) {
		this.loansFromFamilyMembersRelativeString = loansFromFamilyMembersRelativeString;
	}



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



	public String getFieldAuditReport() {
		return fieldAuditReport;
	}



	public void setFieldAuditReport(String fieldAuditReport) {
		this.fieldAuditReport = fieldAuditReport;
	}



	public String getAuditedFinancialsForLast3years() {
		return auditedFinancialsForLast3years;
	}



	public void setAuditedFinancialsForLast3years(String auditedFinancialsForLast3years) {
		this.auditedFinancialsForLast3years = auditedFinancialsForLast3years;
	}



	public String getProvisionalFinancialsForCurrentYear() {
		return provisionalFinancialsForCurrentYear;
	}



	public void setProvisionalFinancialsForCurrentYear(String provisionalFinancialsForCurrentYear) {
		this.provisionalFinancialsForCurrentYear = provisionalFinancialsForCurrentYear;
	}



	public String getItrForLast3years() {
		return itrForLast3years;
	}



	public void setItrForLast3years(String itrForLast3years) {
		this.itrForLast3years = itrForLast3years;
	}



	public String getSanctionLetter() {
		return sanctionLetter;
	}



	public void setSanctionLetter(String sanctionLetter) {
		this.sanctionLetter = sanctionLetter;
	}



	public String getBankStatementOfLast12months() {
		return bankStatementOfLast12months;
	}



	public void setBankStatementOfLast12months(String bankStatementOfLast12months) {
		this.bankStatementOfLast12months = bankStatementOfLast12months;
	}



	public String getDebtorsList() {
		return debtorsList;
	}



	public void setDebtorsList(String debtorsList) {
		this.debtorsList = debtorsList;
	}



	public String getFinancialFigures() {
		return financialFigures;
	}



	public void setFinancialFigures(String financialFigures) {
		this.financialFigures = financialFigures;
	}



	public String getMoaOfTheCompany() {
		return moaOfTheCompany;
	}



	public void setMoaOfTheCompany(String moaOfTheCompany) {
		this.moaOfTheCompany = moaOfTheCompany;
	}



	public String getPanCardOfTheCompany() {
		return panCardOfTheCompany;
	}



	public void setPanCardOfTheCompany(String panCardOfTheCompany) {
		this.panCardOfTheCompany = panCardOfTheCompany;
	}



	public String getResolutionAndForm32forAdditionOfDirector() {
		return resolutionAndForm32forAdditionOfDirector;
	}



	public void setResolutionAndForm32forAdditionOfDirector(String resolutionAndForm32forAdditionOfDirector) {
		this.resolutionAndForm32forAdditionOfDirector = resolutionAndForm32forAdditionOfDirector;
	}



	public String getCentralSalesTaxRegistrationOfCompany() {
		return centralSalesTaxRegistrationOfCompany;
	}



	public void setCentralSalesTaxRegistrationOfCompany(String centralSalesTaxRegistrationOfCompany) {
		this.centralSalesTaxRegistrationOfCompany = centralSalesTaxRegistrationOfCompany;
	}



	public String getCentralExciseRegistrationOfCompany() {
		return centralExciseRegistrationOfCompany;
	}



	public void setCentralExciseRegistrationOfCompany(String centralExciseRegistrationOfCompany) {
		this.centralExciseRegistrationOfCompany = centralExciseRegistrationOfCompany;
	}



	public String getVatRegistrationOfCompany() {
		return vatRegistrationOfCompany;
	}



	public void setVatRegistrationOfCompany(String vatRegistrationOfCompany) {
		this.vatRegistrationOfCompany = vatRegistrationOfCompany;
	}



	public String getLetterOfIntentFromFundProviders() {
		return letterOfIntentFromFundProviders;
	}



	public void setLetterOfIntentFromFundProviders(String letterOfIntentFromFundProviders) {
		this.letterOfIntentFromFundProviders = letterOfIntentFromFundProviders;
	}



	public String getPanCardAndResidenceAddProofOfDirectors() {
		return panCardAndResidenceAddProofOfDirectors;
	}



	public void setPanCardAndResidenceAddProofOfDirectors(String panCardAndResidenceAddProofOfDirectors) {
		this.panCardAndResidenceAddProofOfDirectors = panCardAndResidenceAddProofOfDirectors;
	}



	public String getCaCertifiedNetworthStatement() {
		return caCertifiedNetworthStatement;
	}



	public void setCaCertifiedNetworthStatement(String caCertifiedNetworthStatement) {
		this.caCertifiedNetworthStatement = caCertifiedNetworthStatement;
	}



	public String getIrrOfAllDirectorsForLast2years() {
		return irrOfAllDirectorsForLast2years;
	}



	public void setIrrOfAllDirectorsForLast2years(String irrOfAllDirectorsForLast2years) {
		this.irrOfAllDirectorsForLast2years = irrOfAllDirectorsForLast2years;
	}



	public String getListOfDirectors() {
		return listOfDirectors;
	}



	public void setListOfDirectors(String listOfDirectors) {
		this.listOfDirectors = listOfDirectors;
	}



	public String getListOfShareholdersAndShareHoldingPatter() {
		return listOfShareholdersAndShareHoldingPatter;
	}



	public void setListOfShareholdersAndShareHoldingPatter(String listOfShareholdersAndShareHoldingPatter) {
		this.listOfShareholdersAndShareHoldingPatter = listOfShareholdersAndShareHoldingPatter;
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



	public List<DDROtherBankLoanDetailsRequest> getdDROtherBankLoanDetailsList() {
		return dDROtherBankLoanDetailsList;
	}



	public void setdDROtherBankLoanDetailsList(List<DDROtherBankLoanDetailsRequest> dDROtherBankLoanDetailsList) {
		this.dDROtherBankLoanDetailsList = dDROtherBankLoanDetailsList;
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

	public List<DDRExistingBankerDetailRequest> getExistingBankerDetailList() {
		return existingBankerDetailList;
	}

	public void setExistingBankerDetailList(List<DDRExistingBankerDetailRequest> existingBankerDetailList) {
		this.existingBankerDetailList = existingBankerDetailList;
	}

	public List<DDRFamilyDirectorsDetailsRequest> getdDRFamilyDirectorsList() {
		return dDRFamilyDirectorsList;
	}



	public void setdDRFamilyDirectorsList(List<DDRFamilyDirectorsDetailsRequest> dDRFamilyDirectorsList) {
		this.dDRFamilyDirectorsList = dDRFamilyDirectorsList;
	}



	public Double getProvisionalTotalSales() {
		return provisionalTotalSales;
	}



	public void setProvisionalTotalSales(Double provisionalTotalSales) {
		this.provisionalTotalSales = provisionalTotalSales;
	}



	public Double getLastYearTotalSales() {
		return lastYearTotalSales;
	}


	public void setLastYearTotalSales(Double lastYearTotalSales) {
		this.lastYearTotalSales = lastYearTotalSales;
	}
	public Double getLastToLastYearTotalSales() {
		return lastToLastYearTotalSales;
	}

	public void setLastToLastYearTotalSales(Double lastToLastYearTotalSales) {
		this.lastToLastYearTotalSales = lastToLastYearTotalSales;
	}

	public String getBusinessDetailsComment() {
		return businessDetailsComment;
	}

	public void setBusinessDetailsComment(String businessDetailsComment) {
		this.businessDetailsComment = businessDetailsComment;
	}

	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public String getRemarkOfCompanyInfo() {
		return remarkOfCompanyInfo;
	}



	public void setRemarkOfCompanyInfo(String remarkOfCompanyInfo) {
		this.remarkOfCompanyInfo = remarkOfCompanyInfo;
	}


	public String getRemarkOfFinSummary() {
		return remarkOfFinSummary;
	}



	public void setRemarkOfFinSummary(String remarkOfFinSummary) {
		this.remarkOfFinSummary = remarkOfFinSummary;
	}



	public String getRemarkOfTotalDebt() {
		return remarkOfTotalDebt;
	}



	public void setRemarkOfTotalDebt(String remarkOfTotalDebt) {
		this.remarkOfTotalDebt = remarkOfTotalDebt;
	}



	public String getRemarkOfLatestDebtList() {
		return remarkOfLatestDebtList;
	}



	public void setRemarkOfLatestDebtList(String remarkOfLatestDebtList) {
		this.remarkOfLatestDebtList = remarkOfLatestDebtList;
	}



	public String getRemarkOfLatestCredList() {
		return remarkOfLatestCredList;
	}



	public void setRemarkOfLatestCredList(String remarkOfLatestCredList) {
		this.remarkOfLatestCredList = remarkOfLatestCredList;
	}



	public String getRemarkOfBusinessDetails() {
		return remarkOfBusinessDetails;
	}



	public void setRemarkOfBusinessDetails(String remarkOfBusinessDetails) {
		this.remarkOfBusinessDetails = remarkOfBusinessDetails;
	}



	public String getRemarkOfPersonalDetails() {
		return remarkOfPersonalDetails;
	}



	public void setRemarkOfPersonalDetails(String remarkOfPersonalDetails) {
		this.remarkOfPersonalDetails = remarkOfPersonalDetails;
	}

	public String getRemarkOfExistingBankerDetails() {
		return remarkOfExistingBankerDetails;
	}

	public void setRemarkOfExistingBankerDetails(String remarkOfExistingBankerDetails) {
		this.remarkOfExistingBankerDetails = remarkOfExistingBankerDetails;
	}


	public String getRemarkOfOtherDetail() {
		return remarkOfOtherDetail;
	}



	public void setRemarkOfOtherDetail(String remarkOfOtherDetail) {
		this.remarkOfOtherDetail = remarkOfOtherDetail;
	}



	public String getRemarkOfDetailOfCredCard() {
		return remarkOfDetailOfCredCard;
	}



	public void setRemarkOfDetailOfCredCard(String remarkOfDetailOfCredCard) {
		this.remarkOfDetailOfCredCard = remarkOfDetailOfCredCard;
	}


	public String getRemarkOfNameOfAuthSignatory() {
		return remarkOfNameOfAuthSignatory;
	}

	public void setRemarkOfNameOfAuthSignatory(String remarkOfNameOfAuthSignatory) {
		this.remarkOfNameOfAuthSignatory = remarkOfNameOfAuthSignatory;
	}

	public String getRemarkOfAnyOtherBankLoan() {
		return remarkOfAnyOtherBankLoan;
	}



	public void setRemarkOfAnyOtherBankLoan(String remarkOfAnyOtherBankLoan) {
		this.remarkOfAnyOtherBankLoan = remarkOfAnyOtherBankLoan;
	}



	public String getRemarkOfDocCheckList() {
		return remarkOfDocCheckList;
	}



	public void setRemarkOfDocCheckList(String remarkOfDocCheckList) {
		this.remarkOfDocCheckList = remarkOfDocCheckList;
	}

	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

    public Long getProposalMappingId() {
        return proposalMappingId;
    }

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}

	@Override
	public String toString() {
		return "DDRFormDetailsRequest{" +
				"id=" + id +
				", applicationId=" + applicationId +
				", proposalMappingId=" + proposalMappingId +
				", userId=" + userId +
				", godownStockyard='" + godownStockyard + '\'' +
				", companySuccessPlan='" + companySuccessPlan + '\'' +
				", outsideLoans=" + outsideLoans +
				", loansFromFamilyMembersRelative=" + loansFromFamilyMembersRelative +
				", fallInSales='" + fallInSales + '\'' +
				", reasonForSalesDecline='" + reasonForSalesDecline + '\'' +
				", negativeProfit='" + negativeProfit + '\'' +
				", fallInProfit='" + fallInProfit + '\'' +
				", reasonForProfitDecline='" + reasonForProfitDecline + '\'' +
				", summaryOfDebtorsAgeing0_30Days=" + summaryOfDebtorsAgeing0_30Days +
				", summaryOfDebtorsAgeing31_60Days=" + summaryOfDebtorsAgeing31_60Days +
				", summaryOfDebtorsAgeing61_90Days=" + summaryOfDebtorsAgeing61_90Days +
				", summaryOfDebtorsAgeing91_180Days=" + summaryOfDebtorsAgeing91_180Days +
				", summaryOfDebtorsAgeingGreater180Days=" + summaryOfDebtorsAgeingGreater180Days +
				", summaryOfDebtorsAgeingTotal=" + summaryOfDebtorsAgeingTotal +
				", chequeBouncesDuringLast6months='" + chequeBouncesDuringLast6months + '\'' +
				", summaryOfDebtorsAgeingComment='" + summaryOfDebtorsAgeingComment + '\'' +
				", avgTurnoverPeriod='" + avgTurnoverPeriod + '\'' +
				", creditorsComment='" + creditorsComment + '\'' +
				", businessWhetherSsiOrNot=" + businessWhetherSsiOrNot +
				", investmentInPlantAndMachinery=" + investmentInPlantAndMachinery +
				", majorClients='" + majorClients + '\'' +
				", creditPeriodEnjoyedFromSuppliers='" + creditPeriodEnjoyedFromSuppliers + '\'' +
				", creditPeriodExtendedToBuyers='" + creditPeriodExtendedToBuyers + '\'' +
				", otherSourceOfIncome='" + otherSourceOfIncome + '\'' +
				", otherBusinessInFamilyName='" + otherBusinessInFamilyName + '\'' +
				", operatingAddComment='" + operatingAddComment + '\'' +
				", businessDetailsComment='" + businessDetailsComment + '\'' +
				", othersDetailsComment='" + othersDetailsComment + '\'' +
				", creaditCardHeldByCustComment='" + creaditCardHeldByCustComment + '\'' +
				", fieldAuditReport='" + fieldAuditReport + '\'' +
				", auditedFinancialsForLast3years='" + auditedFinancialsForLast3years + '\'' +
				", provisionalFinancialsForCurrentYear='" + provisionalFinancialsForCurrentYear + '\'' +
				", itrForLast3years='" + itrForLast3years + '\'' +
				", sanctionLetter='" + sanctionLetter + '\'' +
				", bankStatementOfLast12months='" + bankStatementOfLast12months + '\'' +
				", debtorsList='" + debtorsList + '\'' +
				", financialFigures='" + financialFigures + '\'' +
				", moaOfTheCompany='" + moaOfTheCompany + '\'' +
				", panCardOfTheCompany='" + panCardOfTheCompany + '\'' +
				", resolutionAndForm32forAdditionOfDirector='" + resolutionAndForm32forAdditionOfDirector + '\'' +
				", centralSalesTaxRegistrationOfCompany='" + centralSalesTaxRegistrationOfCompany + '\'' +
				", centralExciseRegistrationOfCompany='" + centralExciseRegistrationOfCompany + '\'' +
				", vatRegistrationOfCompany='" + vatRegistrationOfCompany + '\'' +
				", letterOfIntentFromFundProviders='" + letterOfIntentFromFundProviders + '\'' +
				", panCardAndResidenceAddProofOfDirectors='" + panCardAndResidenceAddProofOfDirectors + '\'' +
				", caCertifiedNetworthStatement='" + caCertifiedNetworthStatement + '\'' +
				", irrOfAllDirectorsForLast2years='" + irrOfAllDirectorsForLast2years + '\'' +
				", listOfDirectors='" + listOfDirectors + '\'' +
				", listOfShareholdersAndShareHoldingPatter='" + listOfShareholdersAndShareHoldingPatter + '\'' +
				", provisionalTotalSales=" + provisionalTotalSales +
				", lastYearTotalSales=" + lastYearTotalSales +
				", lastToLastYearTotalSales=" + lastToLastYearTotalSales +
				", currency='" + currency + '\'' +
				", isActive=" + isActive +
				", remarkOfCompanyInfo='" + remarkOfCompanyInfo + '\'' +
				", remarkOfFinSummary='" + remarkOfFinSummary + '\'' +
				", remarkOfTotalDebt='" + remarkOfTotalDebt + '\'' +
				", remarkOfLatestDebtList='" + remarkOfLatestDebtList + '\'' +
				", remarkOfLatestCredList='" + remarkOfLatestCredList + '\'' +
				", remarkOfBusinessDetails='" + remarkOfBusinessDetails + '\'' +
				", remarkOfPersonalDetails='" + remarkOfPersonalDetails + '\'' +
				", remarkOfExistingBankerDetails='" + remarkOfExistingBankerDetails + '\'' +
				", remarkOfNameOfAuthSignatory='" + remarkOfNameOfAuthSignatory + '\'' +
				", remarkOfOtherDetail='" + remarkOfOtherDetail + '\'' +
				", remarkOfDetailOfCredCard='" + remarkOfDetailOfCredCard + '\'' +
				", remarkOfAnyOtherBankLoan='" + remarkOfAnyOtherBankLoan + '\'' +
				", remarkOfDocCheckList='" + remarkOfDocCheckList + '\'' +
				", outsideLoansString='" + outsideLoansString + '\'' +
				", loansFromFamilyMembersRelativeString='" + loansFromFamilyMembersRelativeString + '\'' +
				", dDRAuthSignDetailsList=" + dDRAuthSignDetailsList +
				", dDRCreditCardDetailsList=" + dDRCreditCardDetailsList +
				", dDRCreditorsDetailsList=" + dDRCreditorsDetailsList +
				", dDRRegisteredOfficeList=" + dDRRegisteredOfficeList +
				", dDROperatingOfficeList=" + dDROperatingOfficeList +
				", dDROtherBankLoanDetailsList=" + dDROtherBankLoanDetailsList +
				", dDRVehiclesOwnedDetailsList=" + dDRVehiclesOwnedDetailsList +
				", dDRFinancialSummaryList=" + dDRFinancialSummaryList +
				", existingBankerDetailList=" + existingBankerDetailList +
				", dDRFamilyDirectorsList=" + dDRFamilyDirectorsList +
				", userName =" + userName +

				'}';
	}

	public static void printFields(Object obj) throws LoansException {
		try {
			Field[] fields = DDRFormDetailsRequest.class.getDeclaredFields();
			for(Field field : fields) {
				Object value = field.get(obj);
				if(value instanceof String){
					String a = value.toString().replaceAll("&", "&amp;");
					value = a;
					field.set(obj, value);
				}
			}
		}
		catch (Exception e){
			throw new LoansException(e);
		}

	}

}
