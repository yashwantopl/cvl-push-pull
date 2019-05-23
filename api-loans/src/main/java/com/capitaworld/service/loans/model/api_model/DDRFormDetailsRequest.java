package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFormDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long applicationId;
	private String referenceNo;
	private Long userId;
	private String godownStockyard;
	private String companySuccessPlan;

	private String fallInSales;
	private String reasonForSalesDecline;
	private String negativeProfit;
	private String fallInProfit;
	private String reasonForProfitDecline;
	private String chequeBouncesDuringLast6months;
	private String summaryOfDebtorsAgeingComment;
	private Integer businessWhetherSsiOrNot;
	private String majorClients;
	private String creditPeriodEnjoyedFromSuppliers;
	private String creditPeriodExtendedToBuyers;
	private String otherSourceOfIncome;
	private String otherBusinessInFamilyName;
	private String operatingAddComment;
	private String businessDetailsComment;
	private Double summaryOfDebtorsAgeing0_30Days;
	private Double summaryOfDebtorsAgeing31_60Days;
	private Double summaryOfDebtorsAgeing61_90Days;
	private Double summaryOfDebtorsAgeing91_180Days;
	private Double summaryOfDebtorsAgeingGreater180Days;
	private Double summaryOfDebtorsAgeingTotal;
	private Double outsideLoans;
	private Double loansFromFamilyMembersRelative;
	private String isAnySeasonalPatternInSales;
	private String registeredOfficeAddressDetails;
	private String operatingOfficeAddressDetails;

	List<DDRAuthorizedSignDetailsRequest> dDRAuthSignDetailsList = new ArrayList<DDRAuthorizedSignDetailsRequest>();
	List<DDRCreditCardDetailsRequest> dDRCreditCardDetailsList = new ArrayList<DDRCreditCardDetailsRequest>();
	List<DDRCreditorsDetailsRequest> dDRCreditorsDetailsList = new ArrayList<DDRCreditorsDetailsRequest>();
	List<DDROtherBankLoanDetailsRequest> dDROtherBankLoanDetailsList = new ArrayList<DDROtherBankLoanDetailsRequest>();
	List<DDRVehiclesOwnedDetailsRequest> dDRVehiclesOwnedDetailsList = new ArrayList<DDRVehiclesOwnedDetailsRequest>();
	List<DDRFinancialSummaryRequest> dDRFinancialSummaryList = new ArrayList<DDRFinancialSummaryRequest>();
	List<DDRExistingBankerDetailRequest> existingBankerDetailList = new ArrayList<DDRExistingBankerDetailRequest>();
	List<DDRFamilyDirectorsDetailsRequest> dDRFamilyDirectorsList = new ArrayList<DDRFamilyDirectorsDetailsRequest>();

	public String getRegisteredOfficeAddressDetails() {
		return registeredOfficeAddressDetails;
	}

	public void setRegisteredOfficeAddressDetails(String registeredOfficeAddressDetails) {
		this.registeredOfficeAddressDetails = registeredOfficeAddressDetails;
	}

	public String getOperatingOfficeAddressDetails() {
		return operatingOfficeAddressDetails;
	}

	public void setOperatingOfficeAddressDetails(String operatingOfficeAddressDetails) {
		this.operatingOfficeAddressDetails = operatingOfficeAddressDetails;
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

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public Integer getBusinessWhetherSsiOrNot() {
		return businessWhetherSsiOrNot;
	}

	public void setBusinessWhetherSsiOrNot(Integer businessWhetherSsiOrNot) {
		this.businessWhetherSsiOrNot = businessWhetherSsiOrNot;
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

	public String getBusinessDetailsComment() {
		return businessDetailsComment;
	}

	public void setBusinessDetailsComment(String businessDetailsComment) {
		this.businessDetailsComment = businessDetailsComment;
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

	public String getIsAnySeasonalPatternInSales() {
		return isAnySeasonalPatternInSales;
	}

	public void setIsAnySeasonalPatternInSales(String isAnySeasonalPatternInSales) {
		this.isAnySeasonalPatternInSales = isAnySeasonalPatternInSales;
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

	public static void printFields(Object obj) throws Exception {
		Field[] fields = DDRFormDetailsRequest.class.getDeclaredFields();
		System.out.println("length : " + fields.length);
		for (Field field : fields) {
			Object value = field.get(obj);
			if (value instanceof String) {
				String a = value.toString().replaceAll("&", "&amp;");
				value = a;
				field.set(obj, value);
			}
		}
	}

}
