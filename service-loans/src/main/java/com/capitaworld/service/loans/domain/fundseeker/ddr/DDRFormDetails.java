package com.capitaworld.service.loans.domain.fundseeker.ddr;


/**
 * Created by : harshit
 * The persistent class for the fs_ddr_form_details database table.
 * 
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fs_ddr_form_details")
@NamedQuery(name="DDRFormDetails.findAll", query="SELECT a FROM DDRFormDetails a")
public class DDRFormDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "godown_stockyard")
	private String godownStockyard;
	
	@Column(name = "company_success_plan")
	private String companySuccessPlan;
	
	@Column(name = "details_of_banking_arrangement")
	private String detailsOfBankingArrangement;
	
	@Column(name = "sales_of_automobile_division")
	private Double salesOfAutomobileDivision;
	
	@Column(name = "others_sales")
	private Double othersSales;
	
	@Column(name = "total_consolidated_sales")
	private Double totalConsolidatedSales;
	
	@Column(name = "corporate_sale")
	private Double corporateSale;
	
	@Column(name = "corporate_sale_to_total_sale")
	private Double corporateSaleToTotalSale;
	
	@Column(name = "corporate_sale_to_automobile_div_sale")
	private Double corporateSaleToAutomobileDivSale;
	
	@Column(name = "outside_loans")
	private Double outsideLoans;
	
	@Column(name = "loans_from_family_members_relative")
	private Double loansFromFamilyMembersRelative;
	
	@Column(name = "fall_in_sales")
	private String fallInSales;
	
	@Column(name = "reason_for_sales_decline")
	private String reasonForSalesDecline;
	
	@Column(name = "negative_profit")
	private String negativeProfit;
	
	@Column(name = "fall_in_profit")
	private String fallInProfit;
	
	@Column(name = "reason_for_profit_decline")
	private String reasonForProfitDecline;
	
	@Column(name = "provisional_sales_figure")
	private String provisionalSalesFigure;
	
	@Column(name = "sales_breakup_by_product")
	private String salesBreakupByProduct;
	
	@Column(name = "is_any_seasonal_pattern_in_sales")
	private String isAnySeasonalPatternInSales;
	
	@Column(name = "outstanding_dues_amount")
	private String outstandingDuesAmount;
	
	@Column(name = "outstanding_dues_age")
	private String outstandingDuesAge;
	
	@Column(name = "outstanding_dues_comment")
	private String outstandingDuesComment;
	
	@Column(name = "summary_of_debtors_ageing_0_30_days")
	private Double  summaryOfDebtorsAgeing0_30Days;
	
	@Column(name = "summary_of_debtors_ageing_31_60_days")
	private Double summaryOfDebtorsAgeing31_60Days;
	
	@Column(name = "summary_of_debtors_ageing_61_90_days")
	private Double summaryOfDebtorsAgeing61_90Days;
	
	@Column(name = "summary_of_debtors_ageing_91_180_days")
	private Double summaryOfDebtorsAgeing91_180Days;
	
	@Column(name = "summary_of_debtors_ageing_greater_180_days")
	private Double summaryOfDebtorsAgeingGreater180Days;
	
	@Column(name = "summary_of_debtors_ageing_total")
	private Double summaryOfDebtorsAgeingTotal;
	
	@Column(name = "avg_debtor_turnover_period")
	private String avgDebtorTurnoverPeriod;
	
	@Column(name = "cheque_bounces_during_last_6months")
	private String chequeBouncesDuringLast6months;
	
	@Column(name = "summary_of_debtors_ageing_comment")
	private String summaryOfDebtorsAgeingComment;
	
	@Column(name = "avg_turnover_period")
	private String avgTurnoverPeriod;
	
	@Column(name = "creditors_comment")
	private String creditorsComment;
	
	@Column(name = "business_whether_ssi_or_not")
	private Integer businessWhetherSsiOrNot;
	
	@Column(name = "investment_in_plant_and_machinery")
	private Double investmentInPlantAndMachinery;
	
	@Column(name = "major_clients")
	private String majorClients;
	
	@Column(name = "credit_period_enjoyed_from_suppliers")
	private String creditPeriodEnjoyedFromSuppliers;
	
	@Column(name = "credit_period_extended_to_buyers")
	private String creditPeriodExtendedToBuyers;
	
	@Column(name = "other_source_of_income")
	private String otherSourceOfIncome;
	
	@Column(name = "other_business_in_family_name")
	private String otherBusinessInFamilyName;
	
	@Column(name = "operating_add_comment")
	private String operatingAddComment;
	
	@Column(name = "others_details_comment")
	private String othersDetailsComment;
	
	@Column(name = "creadit_card_held_by_cust_comment")
	private String creaditCardHeldByCustComment;
	
	@Column(name = "field_audit_report")
	private Integer fieldAuditReport;
	
	@Column(name = "audited_financials_for_last_3years")
	private Integer auditedFinancialsForLast3years;
	
	@Column(name = "provisional_financials_for_current_year")
	private Integer provisionalFinancialsForCurrentYear;
	
	@Column(name = "itr_for_last_3years")
	private Integer itrForLast3years;
	
	@Column(name = "sanction_letter")
	private Integer sanctionLetter;
	
	@Column(name = "bank_statement_of_last_12months")
	private Integer bankStatementOfLast12months;
	
	@Column(name = "debtors_list")
	private Integer debtorsList;
	
	@Column(name = "financial_figures")
	private Integer financialFigures;
	
	@Column(name = "moa_of_the_company")
	private Integer moaOfTheCompany;
	
	@Column(name = "pan_card_of_the_company")
	private Integer panCardOfTheCompany;
	
	@Column(name = "resolution_and_form_32for_addition_of_director")
	private Integer resolutionAndForm32forAdditionOfDirector;
	
	@Column(name = "central_sales_tax_registration_of_company")
	private Integer centralSalesTaxRegistrationOfCompany;
	
	@Column(name = "central_excise_registration_of_company")
	private Integer centralExciseRegistrationOfCompany;
	
	@Column(name = "vat_registration_of_company")
	private Integer vatRegistrationOfCompany;
	
	@Column(name = "letter_of_Intent_from_fund_providers")
	private Integer letterOfIntentFromFundProviders;
	
	@Column(name = "pan_card_and_residence_add_proof_of_directors")
	private Integer panCardAndResidenceAddProofOfDirectors;
	
	@Column(name = "ca_certified_networth_Statement")
	private Integer caCertifiedNetworthStatement;
	
	@Column(name = "irr_of_all_directors_for_last_2years")
	private Integer irrOfAllDirectorsForLast2years;
	
	@Column(name = "list_of_directors")
	private Integer listOfDirectors;
	
	@Column(name = "list_of_shareholders_and_share_holding_patter")
	private Integer listOfShareholdersAndShareHoldingPatter;
	
	@Column(name = "summary_of_bservations")
	private String summaryOfBservations;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name = "modify_by")
	private Long modifyBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "is_active")
	private Boolean isActive;

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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRFormDetails [id=" + id + ", applicationId=" + applicationId + ", userId=" + userId
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
				+ ", summaryOfBservations=" + summaryOfBservations + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", isActive=" + isActive
				+ "]";
	}


	
	
}
