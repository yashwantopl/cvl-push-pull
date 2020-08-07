/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ForexEarningExpenditureHistories {
	
	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	@JsonProperty("earning-on-export-of-goods-calculated-on-fob-basis")
	private String earningOnExportOfGoodsCalculatedOnFobBasis;

	@JsonProperty("earning-on-interest-and-dividend")
	private String earningOnInterestAndDividend;

	@JsonProperty("earning-on-royalty")
	private String earningOnRoyalty;

	@JsonProperty("earning-on-know-how")
	private String eariningOnKnowHow;

	@JsonProperty("earning-on-professional-and-consultation-fees")
	private String earningOnProfessionalAndConsultationFees;

	@JsonProperty("earning-on-other-income")
	private String earningOnOtherIncome;

	@JsonProperty("total-earning-in-foreign-exchange")
	private String totalEarningInForeignExchange;

	@JsonProperty("imported-raw-material")
	private String importedRawMaterial;

	@JsonProperty("imported-component-and-spare-parts")
	private String importedComponentAndSpareParts;

	@JsonProperty("imported-capital-goods")
	private String importedCapitalGoods;

	@JsonProperty("expenditure-on-royalty")
	private String expanditureOnRoyalty;

	@JsonProperty("expenditure-on-know-how")
	private String expenditureOnKnoeHow;

	@JsonProperty("expenditure-on-professional-and-consultation-fees")
	private String expenditureOnProfessionalAndConsultationFees;

	@JsonProperty("expenditure-on-interest")
	private String expanditureOnInterest;

	@JsonProperty("expenditure-on-other-matters")
	private String expanditureOnOtherMatters;

	@JsonProperty("expenditure-on-dividend-paid")
	private String expanditureOndividedPaid;

	@JsonProperty("total-expenditure-in-foreign-exchange")
	private String totalExpenditureInForeignExchange;

	@JsonProperty("paidup-capital-held-by-foreign-company")
	private String paidupCapitalHeldByForeignCompany;

	@JsonProperty("percentage-of-shares-held-by-foreign-national-other-than-non-resident-indian")
	private String percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian;

	@JsonProperty("paid-up-capital-held-by-foreign-holding-company-and-or-through-its-subsidiary-company")
	private String paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany;

	@JsonProperty("borrowing-form-foreign-institutional-agencies")
	private String borrowingFormForeignInstitutionalAgencies;

	@JsonProperty("borrowing-from-foreign-companies")
	private String borrowingFromForeignCompanies;

	@JsonProperty("unhedged-foreign-exchange-exposure")
	private String unhedgedForeignExchangeExposure;

	@JsonProperty("as-on-date-str")
	private String asOnDateStr;

	@JsonProperty("earning-on-export-of-goods-calculated-on-fob-basis-str")
	private String earningOnExportOfGoodsCalculatedOnFobBasisStr;

	@JsonProperty("earning-on-interest-and-dividend-str")
	private String earningOnInterestAndDividendStr;

	@JsonProperty("earning-on-royalty-str")
	private String earningOnRoyaltyStr;

	@JsonProperty("earning-on-know-how-str")
	private String earningOnKnowHowStr;

	@JsonProperty("earning-on-professional-and-consultation-fees-str")
	private String earningOnProfessionalAndConsultationFeesStr;

	@JsonProperty("earning-on-other-income-str")
	private String earningOnOtherIncomeStr;

	@JsonProperty("total-earning-in-foreign-exchange-str")
	private String totalEarningInForeignExchangeStr;

	@JsonProperty("imported-raw-material-str")
	private String importedRawMaterialStr;

	@JsonProperty("imported-component-and-spare-parts-str")
	private String importedComponentAndSparePartsStr;

	@JsonProperty("imported-capital-goods-str")
	private String importedCapitalGoodsStr;

	@JsonProperty("expenditure-on-royalty-str")
	private String expenditureOnRoyaltyStr;

	@JsonProperty("expenditure-on-know-how-str")
	private String expenditureOnKnowHowStr;

	@JsonProperty("expenditure-on-professional-and-consultation-fees-str")
	private String expenditureOnProfessionalAndConsultationFeesStr;

	@JsonProperty("expenditure-on-interest-str")
	private String expenditureOnInterestStr;

	@JsonProperty("expenditure-on-other-matters-str")
	private String expenditureOnOtherMattersStr;

	@JsonProperty("expenditure-on-dividend-paid-str")
	private String expenditureOnDividendPaidStr;

	@JsonProperty("total-expenditure-in-foreign-exchange-str")
	private String totalExpenditureInForeignExchangeStr;

	@JsonProperty("paidup-capital-held-by-foreign-company-str")
	private String paidupCapitalHeldByForeignCompanyStr;

	@JsonProperty("percentage-of-shares-held-by-foreign-national-other-than-non-resident-indian-str")
	private String percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr;

	@JsonProperty("paid-up-capital-held-by-foreign-holding-company-and-or-through-its-subsidiary-company-str")
	private String paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr;

	@JsonProperty("borrowing-form-foreign-institutional-agencies-str")
	private String borrowingFormForeignInstitutionalAgenciesStr;

	@JsonProperty("borrowing-from-foreign-companies-str")
	private String borrowingFromForeignCompaniesStr;

	@JsonProperty("unhedged-foreign-exchange-exposure-str")
	private String unhedgedForeignExchangeExposureStr;

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	/**
	 * @return the financialYear
	 */
	public String getFinancialYear() {
		return financialYear;
	}

	/**
	 * @param financialYear
	 *            the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return the earningOnExportOfGoodsCalculatedOnFobBasis
	 */
	public String getEarningOnExportOfGoodsCalculatedOnFobBasis() {
		return earningOnExportOfGoodsCalculatedOnFobBasis;
	}

	/**
	 * @param earningOnExportOfGoodsCalculatedOnFobBasis
	 *            the earningOnExportOfGoodsCalculatedOnFobBasis to set
	 */
	public void setEarningOnExportOfGoodsCalculatedOnFobBasis(String earningOnExportOfGoodsCalculatedOnFobBasis) {
		this.earningOnExportOfGoodsCalculatedOnFobBasis = earningOnExportOfGoodsCalculatedOnFobBasis;
	}

	/**
	 * @return the earningOnInterestAndDividend
	 */
	public String getEarningOnInterestAndDividend() {
		return earningOnInterestAndDividend;
	}

	/**
	 * @param earningOnInterestAndDividend
	 *            the earningOnInterestAndDividend to set
	 */
	public void setEarningOnInterestAndDividend(String earningOnInterestAndDividend) {
		this.earningOnInterestAndDividend = earningOnInterestAndDividend;
	}

	/**
	 * @return the earningOnRoyalty
	 */
	public String getEarningOnRoyalty() {
		return earningOnRoyalty;
	}

	/**
	 * @param earningOnRoyalty
	 *            the earningOnRoyalty to set
	 */
	public void setEarningOnRoyalty(String earningOnRoyalty) {
		this.earningOnRoyalty = earningOnRoyalty;
	}

	/**
	 * @return the eariningOnKnowHow
	 */
	public String getEariningOnKnowHow() {
		return eariningOnKnowHow;
	}

	/**
	 * @param eariningOnKnowHow
	 *            the eariningOnKnowHow to set
	 */
	public void setEariningOnKnowHow(String eariningOnKnowHow) {
		this.eariningOnKnowHow = eariningOnKnowHow;
	}

	/**
	 * @return the earningOnProfessionalAndConsultationFees
	 */
	public String getEarningOnProfessionalAndConsultationFees() {
		return earningOnProfessionalAndConsultationFees;
	}

	/**
	 * @param earningOnProfessionalAndConsultationFees
	 *            the earningOnProfessionalAndConsultationFees to set
	 */
	public void setEarningOnProfessionalAndConsultationFees(String earningOnProfessionalAndConsultationFees) {
		this.earningOnProfessionalAndConsultationFees = earningOnProfessionalAndConsultationFees;
	}

	/**
	 * @return the earningOnOtherIncome
	 */
	public String getEarningOnOtherIncome() {
		return earningOnOtherIncome;
	}

	/**
	 * @param earningOnOtherIncome
	 *            the earningOnOtherIncome to set
	 */
	public void setEarningOnOtherIncome(String earningOnOtherIncome) {
		this.earningOnOtherIncome = earningOnOtherIncome;
	}

	/**
	 * @return the totalEarningInForeignExchange
	 */
	public String getTotalEarningInForeignExchange() {
		return totalEarningInForeignExchange;
	}

	/**
	 * @param totalEarningInForeignExchange
	 *            the totalEarningInForeignExchange to set
	 */
	public void setTotalEarningInForeignExchange(String totalEarningInForeignExchange) {
		this.totalEarningInForeignExchange = totalEarningInForeignExchange;
	}

	/**
	 * @return the importedRawMaterial
	 */
	public String getImportedRawMaterial() {
		return importedRawMaterial;
	}

	/**
	 * @param importedRawMaterial
	 *            the importedRawMaterial to set
	 */
	public void setImportedRawMaterial(String importedRawMaterial) {
		this.importedRawMaterial = importedRawMaterial;
	}

	/**
	 * @return the importedComponentAndSpareParts
	 */
	public String getImportedComponentAndSpareParts() {
		return importedComponentAndSpareParts;
	}

	/**
	 * @param importedComponentAndSpareParts
	 *            the importedComponentAndSpareParts to set
	 */
	public void setImportedComponentAndSpareParts(String importedComponentAndSpareParts) {
		this.importedComponentAndSpareParts = importedComponentAndSpareParts;
	}

	/**
	 * @return the importedCapitalGoods
	 */
	public String getImportedCapitalGoods() {
		return importedCapitalGoods;
	}

	/**
	 * @param importedCapitalGoods
	 *            the importedCapitalGoods to set
	 */
	public void setImportedCapitalGoods(String importedCapitalGoods) {
		this.importedCapitalGoods = importedCapitalGoods;
	}

	/**
	 * @return the expanditureOnRoyalty
	 */
	public String getExpanditureOnRoyalty() {
		return expanditureOnRoyalty;
	}

	/**
	 * @param expanditureOnRoyalty
	 *            the expanditureOnRoyalty to set
	 */
	public void setExpanditureOnRoyalty(String expanditureOnRoyalty) {
		this.expanditureOnRoyalty = expanditureOnRoyalty;
	}

	/**
	 * @return the expenditureOnKnoeHow
	 */
	public String getExpenditureOnKnoeHow() {
		return expenditureOnKnoeHow;
	}

	/**
	 * @param expenditureOnKnoeHow
	 *            the expenditureOnKnoeHow to set
	 */
	public void setExpenditureOnKnoeHow(String expenditureOnKnoeHow) {
		this.expenditureOnKnoeHow = expenditureOnKnoeHow;
	}

	/**
	 * @return the expenditureOnProfessionalAndConsultationFees
	 */
	public String getExpenditureOnProfessionalAndConsultationFees() {
		return expenditureOnProfessionalAndConsultationFees;
	}

	/**
	 * @param expenditureOnProfessionalAndConsultationFees
	 *            the expenditureOnProfessionalAndConsultationFees to set
	 */
	public void setExpenditureOnProfessionalAndConsultationFees(String expenditureOnProfessionalAndConsultationFees) {
		this.expenditureOnProfessionalAndConsultationFees = expenditureOnProfessionalAndConsultationFees;
	}

	/**
	 * @return the expanditureOnInterest
	 */
	public String getExpanditureOnInterest() {
		return expanditureOnInterest;
	}

	/**
	 * @param expanditureOnInterest
	 *            the expanditureOnInterest to set
	 */
	public void setExpanditureOnInterest(String expanditureOnInterest) {
		this.expanditureOnInterest = expanditureOnInterest;
	}

	/**
	 * @return the expanditureOnOtherMatters
	 */
	public String getExpanditureOnOtherMatters() {
		return expanditureOnOtherMatters;
	}

	/**
	 * @param expanditureOnOtherMatters
	 *            the expanditureOnOtherMatters to set
	 */
	public void setExpanditureOnOtherMatters(String expanditureOnOtherMatters) {
		this.expanditureOnOtherMatters = expanditureOnOtherMatters;
	}

	/**
	 * @return the expanditureOndividedPaid
	 */
	public String getExpanditureOndividedPaid() {
		return expanditureOndividedPaid;
	}

	/**
	 * @param expanditureOndividedPaid
	 *            the expanditureOndividedPaid to set
	 */
	public void setExpanditureOndividedPaid(String expanditureOndividedPaid) {
		this.expanditureOndividedPaid = expanditureOndividedPaid;
	}

	/**
	 * @return the totalExpenditureInForeignExchange
	 */
	public String getTotalExpenditureInForeignExchange() {
		return totalExpenditureInForeignExchange;
	}

	/**
	 * @param totalExpenditureInForeignExchange
	 *            the totalExpenditureInForeignExchange to set
	 */
	public void setTotalExpenditureInForeignExchange(String totalExpenditureInForeignExchange) {
		this.totalExpenditureInForeignExchange = totalExpenditureInForeignExchange;
	}

	/**
	 * @return the paidupCapitalHeldByForeignCompany
	 */
	public String getPaidupCapitalHeldByForeignCompany() {
		return paidupCapitalHeldByForeignCompany;
	}

	/**
	 * @param paidupCapitalHeldByForeignCompany
	 *            the paidupCapitalHeldByForeignCompany to set
	 */
	public void setPaidupCapitalHeldByForeignCompany(String paidupCapitalHeldByForeignCompany) {
		this.paidupCapitalHeldByForeignCompany = paidupCapitalHeldByForeignCompany;
	}

	/**
	 * @return the percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian
	 */
	public String getPercentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian() {
		return percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian;
	}

	/**
	 * @param percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian
	 *            the
	 *            percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian
	 *            to set
	 */
	public void setPercentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian(
			String percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian) {
		this.percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian = percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndian;
	}

	/**
	 * @return the
	 *         paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany
	 */
	public String getPaidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany() {
		return paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany;
	}

	/**
	 * @param paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany
	 *            the
	 *            paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany
	 *            to set
	 */
	public void setPaidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany(
			String paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany) {
		this.paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany = paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompany;
	}

	/**
	 * @return the borrowingFormForeignInstitutionalAgencies
	 */
	public String getBorrowingFormForeignInstitutionalAgencies() {
		return borrowingFormForeignInstitutionalAgencies;
	}

	/**
	 * @param borrowingFormForeignInstitutionalAgencies
	 *            the borrowingFormForeignInstitutionalAgencies to set
	 */
	public void setBorrowingFormForeignInstitutionalAgencies(String borrowingFormForeignInstitutionalAgencies) {
		this.borrowingFormForeignInstitutionalAgencies = borrowingFormForeignInstitutionalAgencies;
	}

	/**
	 * @return the borrowingFromForeignCompanies
	 */
	public String getBorrowingFromForeignCompanies() {
		return borrowingFromForeignCompanies;
	}

	/**
	 * @param borrowingFromForeignCompanies
	 *            the borrowingFromForeignCompanies to set
	 */
	public void setBorrowingFromForeignCompanies(String borrowingFromForeignCompanies) {
		this.borrowingFromForeignCompanies = borrowingFromForeignCompanies;
	}

	/**
	 * @return the unhedgedForeignExchangeExposure
	 */
	public String getUnhedgedForeignExchangeExposure() {
		return unhedgedForeignExchangeExposure;
	}

	/**
	 * @param unhedgedForeignExchangeExposure
	 *            the unhedgedForeignExchangeExposure to set
	 */
	public void setUnhedgedForeignExchangeExposure(String unhedgedForeignExchangeExposure) {
		this.unhedgedForeignExchangeExposure = unhedgedForeignExchangeExposure;
	}

	/**
	 * @return the asOnDateStr
	 */
	public String getAsOnDateStr() {
		return asOnDateStr;
	}

	/**
	 * @param asOnDateStr
	 *            the asOnDateStr to set
	 */
	public void setAsOnDateStr(String asOnDateStr) {
		this.asOnDateStr = asOnDateStr;
	}

	/**
	 * @return the earningOnExportOfGoodsCalculatedOnFobBasisStr
	 */
	public String getEarningOnExportOfGoodsCalculatedOnFobBasisStr() {
		return earningOnExportOfGoodsCalculatedOnFobBasisStr;
	}

	/**
	 * @param earningOnExportOfGoodsCalculatedOnFobBasisStr
	 *            the earningOnExportOfGoodsCalculatedOnFobBasisStr to set
	 */
	public void setEarningOnExportOfGoodsCalculatedOnFobBasisStr(String earningOnExportOfGoodsCalculatedOnFobBasisStr) {
		this.earningOnExportOfGoodsCalculatedOnFobBasisStr = earningOnExportOfGoodsCalculatedOnFobBasisStr;
	}

	/**
	 * @return the earningOnInterestAndDividendStr
	 */
	public String getEarningOnInterestAndDividendStr() {
		return earningOnInterestAndDividendStr;
	}

	/**
	 * @param earningOnInterestAndDividendStr
	 *            the earningOnInterestAndDividendStr to set
	 */
	public void setEarningOnInterestAndDividendStr(String earningOnInterestAndDividendStr) {
		this.earningOnInterestAndDividendStr = earningOnInterestAndDividendStr;
	}

	/**
	 * @return the earningOnRoyaltyStr
	 */
	public String getEarningOnRoyaltyStr() {
		return earningOnRoyaltyStr;
	}

	/**
	 * @param earningOnRoyaltyStr
	 *            the earningOnRoyaltyStr to set
	 */
	public void setEarningOnRoyaltyStr(String earningOnRoyaltyStr) {
		this.earningOnRoyaltyStr = earningOnRoyaltyStr;
	}

	/**
	 * @return the earningOnKnowHowStr
	 */
	public String getEarningOnKnowHowStr() {
		return earningOnKnowHowStr;
	}

	/**
	 * @param earningOnKnowHowStr
	 *            the earningOnKnowHowStr to set
	 */
	public void setEarningOnKnowHowStr(String earningOnKnowHowStr) {
		this.earningOnKnowHowStr = earningOnKnowHowStr;
	}

	/**
	 * @return the earningOnProfessionalAndConsultationFeesStr
	 */
	public String getEarningOnProfessionalAndConsultationFeesStr() {
		return earningOnProfessionalAndConsultationFeesStr;
	}

	/**
	 * @param earningOnProfessionalAndConsultationFeesStr
	 *            the earningOnProfessionalAndConsultationFeesStr to set
	 */
	public void setEarningOnProfessionalAndConsultationFeesStr(String earningOnProfessionalAndConsultationFeesStr) {
		this.earningOnProfessionalAndConsultationFeesStr = earningOnProfessionalAndConsultationFeesStr;
	}

	/**
	 * @return the earningOnOtherIncomeStr
	 */
	public String getEarningOnOtherIncomeStr() {
		return earningOnOtherIncomeStr;
	}

	/**
	 * @param earningOnOtherIncomeStr
	 *            the earningOnOtherIncomeStr to set
	 */
	public void setEarningOnOtherIncomeStr(String earningOnOtherIncomeStr) {
		this.earningOnOtherIncomeStr = earningOnOtherIncomeStr;
	}

	/**
	 * @return the totalEarningInForeignExchangeStr
	 */
	public String getTotalEarningInForeignExchangeStr() {
		return totalEarningInForeignExchangeStr;
	}

	/**
	 * @param totalEarningInForeignExchangeStr
	 *            the totalEarningInForeignExchangeStr to set
	 */
	public void setTotalEarningInForeignExchangeStr(String totalEarningInForeignExchangeStr) {
		this.totalEarningInForeignExchangeStr = totalEarningInForeignExchangeStr;
	}

	/**
	 * @return the importedRawMaterialStr
	 */
	public String getImportedRawMaterialStr() {
		return importedRawMaterialStr;
	}

	/**
	 * @param importedRawMaterialStr
	 *            the importedRawMaterialStr to set
	 */
	public void setImportedRawMaterialStr(String importedRawMaterialStr) {
		this.importedRawMaterialStr = importedRawMaterialStr;
	}

	/**
	 * @return the importedComponentAndSparePartsStr
	 */
	public String getImportedComponentAndSparePartsStr() {
		return importedComponentAndSparePartsStr;
	}

	/**
	 * @param importedComponentAndSparePartsStr
	 *            the importedComponentAndSparePartsStr to set
	 */
	public void setImportedComponentAndSparePartsStr(String importedComponentAndSparePartsStr) {
		this.importedComponentAndSparePartsStr = importedComponentAndSparePartsStr;
	}

	/**
	 * @return the importedCapitalGoodsStr
	 */
	public String getImportedCapitalGoodsStr() {
		return importedCapitalGoodsStr;
	}

	/**
	 * @param importedCapitalGoodsStr
	 *            the importedCapitalGoodsStr to set
	 */
	public void setImportedCapitalGoodsStr(String importedCapitalGoodsStr) {
		this.importedCapitalGoodsStr = importedCapitalGoodsStr;
	}

	/**
	 * @return the expenditureOnRoyaltyStr
	 */
	public String getExpenditureOnRoyaltyStr() {
		return expenditureOnRoyaltyStr;
	}

	/**
	 * @param expenditureOnRoyaltyStr
	 *            the expenditureOnRoyaltyStr to set
	 */
	public void setExpenditureOnRoyaltyStr(String expenditureOnRoyaltyStr) {
		this.expenditureOnRoyaltyStr = expenditureOnRoyaltyStr;
	}

	/**
	 * @return the expenditureOnKnowHowStr
	 */
	public String getExpenditureOnKnowHowStr() {
		return expenditureOnKnowHowStr;
	}

	/**
	 * @param expenditureOnKnowHowStr
	 *            the expenditureOnKnowHowStr to set
	 */
	public void setExpenditureOnKnowHowStr(String expenditureOnKnowHowStr) {
		this.expenditureOnKnowHowStr = expenditureOnKnowHowStr;
	}

	/**
	 * @return the expenditureOnProfessionalAndConsultationFeesStr
	 */
	public String getExpenditureOnProfessionalAndConsultationFeesStr() {
		return expenditureOnProfessionalAndConsultationFeesStr;
	}

	/**
	 * @param expenditureOnProfessionalAndConsultationFeesStr
	 *            the expenditureOnProfessionalAndConsultationFeesStr to set
	 */
	public void setExpenditureOnProfessionalAndConsultationFeesStr(
			String expenditureOnProfessionalAndConsultationFeesStr) {
		this.expenditureOnProfessionalAndConsultationFeesStr = expenditureOnProfessionalAndConsultationFeesStr;
	}

	/**
	 * @return the expenditureOnInterestStr
	 */
	public String getExpenditureOnInterestStr() {
		return expenditureOnInterestStr;
	}

	/**
	 * @param expenditureOnInterestStr
	 *            the expenditureOnInterestStr to set
	 */
	public void setExpenditureOnInterestStr(String expenditureOnInterestStr) {
		this.expenditureOnInterestStr = expenditureOnInterestStr;
	}

	/**
	 * @return the expenditureOnOtherMattersStr
	 */
	public String getExpenditureOnOtherMattersStr() {
		return expenditureOnOtherMattersStr;
	}

	/**
	 * @param expenditureOnOtherMattersStr
	 *            the expenditureOnOtherMattersStr to set
	 */
	public void setExpenditureOnOtherMattersStr(String expenditureOnOtherMattersStr) {
		this.expenditureOnOtherMattersStr = expenditureOnOtherMattersStr;
	}

	/**
	 * @return the expenditureOnDividendPaidStr
	 */
	public String getExpenditureOnDividendPaidStr() {
		return expenditureOnDividendPaidStr;
	}

	/**
	 * @param expenditureOnDividendPaidStr
	 *            the expenditureOnDividendPaidStr to set
	 */
	public void setExpenditureOnDividendPaidStr(String expenditureOnDividendPaidStr) {
		this.expenditureOnDividendPaidStr = expenditureOnDividendPaidStr;
	}

	/**
	 * @return the totalExpenditureInForeignExchangeStr
	 */
	public String getTotalExpenditureInForeignExchangeStr() {
		return totalExpenditureInForeignExchangeStr;
	}

	/**
	 * @param totalExpenditureInForeignExchangeStr
	 *            the totalExpenditureInForeignExchangeStr to set
	 */
	public void setTotalExpenditureInForeignExchangeStr(String totalExpenditureInForeignExchangeStr) {
		this.totalExpenditureInForeignExchangeStr = totalExpenditureInForeignExchangeStr;
	}

	/**
	 * @return the paidupCapitalHeldByForeignCompanyStr
	 */
	public String getPaidupCapitalHeldByForeignCompanyStr() {
		return paidupCapitalHeldByForeignCompanyStr;
	}

	/**
	 * @param paidupCapitalHeldByForeignCompanyStr
	 *            the paidupCapitalHeldByForeignCompanyStr to set
	 */
	public void setPaidupCapitalHeldByForeignCompanyStr(String paidupCapitalHeldByForeignCompanyStr) {
		this.paidupCapitalHeldByForeignCompanyStr = paidupCapitalHeldByForeignCompanyStr;
	}

	/**
	 * @return the
	 *         percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr
	 */
	public String getPercentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr() {
		return percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr;
	}

	/**
	 * @param percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr
	 *            the
	 *            percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr
	 *            to set
	 */
	public void setPercentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr(
			String percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr) {
		this.percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr = percentageOfSharesHeldByForeignNationalOtherThanNonResidentIndianStr;
	}

	/**
	 * @return the
	 *         paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr
	 */
	public String getPaidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr() {
		return paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr;
	}

	/**
	 * @param paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr
	 *            the
	 *            paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr
	 *            to set
	 */
	public void setPaidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr(
			String paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr) {
		this.paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr = paidUpCapitalHeldByForeignHoldingCompanyAndOrThroughItsSubsidiaryCompanyStr;
	}

	/**
	 * @return the borrowingFormForeignInstitutionalAgenciesStr
	 */
	public String getBorrowingFormForeignInstitutionalAgenciesStr() {
		return borrowingFormForeignInstitutionalAgenciesStr;
	}

	/**
	 * @param borrowingFormForeignInstitutionalAgenciesStr
	 *            the borrowingFormForeignInstitutionalAgenciesStr to set
	 */
	public void setBorrowingFormForeignInstitutionalAgenciesStr(String borrowingFormForeignInstitutionalAgenciesStr) {
		this.borrowingFormForeignInstitutionalAgenciesStr = borrowingFormForeignInstitutionalAgenciesStr;
	}

	/**
	 * @return the borrowingFromForeignCompaniesStr
	 */
	public String getBorrowingFromForeignCompaniesStr() {
		return borrowingFromForeignCompaniesStr;
	}

	/**
	 * @param borrowingFromForeignCompaniesStr
	 *            the borrowingFromForeignCompaniesStr to set
	 */
	public void setBorrowingFromForeignCompaniesStr(String borrowingFromForeignCompaniesStr) {
		this.borrowingFromForeignCompaniesStr = borrowingFromForeignCompaniesStr;
	}

	/**
	 * @return the unhedgedForeignExchangeExposureStr
	 */
	public String getUnhedgedForeignExchangeExposureStr() {
		return unhedgedForeignExchangeExposureStr;
	}

	/**
	 * @param unhedgedForeignExchangeExposureStr
	 *            the unhedgedForeignExchangeExposureStr to set
	 */
	public void setUnhedgedForeignExchangeExposureStr(String unhedgedForeignExchangeExposureStr) {
		this.unhedgedForeignExchangeExposureStr = unhedgedForeignExchangeExposureStr;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
