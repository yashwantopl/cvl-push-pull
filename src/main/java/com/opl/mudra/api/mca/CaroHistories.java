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
public class CaroHistories {
	
	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	@JsonProperty("fixed-assets")
	private String fixedAssets;

	@JsonProperty("inventories")
	private String inventories;

	@JsonProperty("loans-given-by-the-company")
	private String loansGivenByTheCompany;

	@JsonProperty("acceptance-of-public-deposits")
	private String acceptanceOfPublicDeposits;

	@JsonProperty("maintenance-of-cost-records")
	private String maintenanceOfCostRecords;

	@JsonProperty("statutory-dues")
	private String statutoryDues;

	@JsonProperty("term-loans")
	private String termLoans;

	@JsonProperty("fraud-noticed")
	private String frudNoticed;

	@JsonProperty("others")
	private String others;

	@JsonProperty("disclosure-in-aud-report-relating-to-managerial-remuneration")
	private String disclosureInAudReportRelatingToManagerialRemuneration;

	@JsonProperty("disclosure-in-aud-report-relating-to-nidhi-company")
	private String disclosureInAudReportRelatingToNidhiCompany;

	@JsonProperty("disclosure-in-aud-report-relating-to-transactions-with-related-parties")
	private String disclosureInAudReportRelatingToTransactionsWithRelatedParties;

	@JsonProperty("disclosure-in-aud-report-relating-to-pref-allot-or-pvt-placement-of-shares-or-convert-deb")
	private String disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb;

	@JsonProperty("disclosure-in-aud-report-relating-to-non-cash-transactions-with-dir")
	private String disclosureInAudReportRelatingToNonCashTransactionsWithDir;

	@JsonProperty("disclosure-in-aud-report-relating-to-registration-under-section-45ia-of-rbi-act-1934")
	private String disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934;

	@JsonProperty("disclosure-in-aud-report-relating-to-internal-control-system")
	private String disclosureInAudReportRelatingToInternalControlSystem;

	@JsonProperty("disclosure-in-aud-report-relating-to-accumulated-losses")
	private String disclosureInAudReportRelatingToAccumulatedLosses;

	@JsonProperty("disclosure-in-aud-report-relating-to-guarantee-given")
	private String disclosureInAudReportRelatingToGuaranteeGiven;

	@JsonProperty("disclosure-in-aud-report-relating-to-default-in-repayment-of-financial-dues")
	private String disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues;

	@JsonProperty("disclosure-relating-amount-required-to-transferred-to-investor-education-protection-fund")
	private String disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund;

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate the asOnDate to set
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
	 * @param financialYear the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return the fixedAssets
	 */
	public String getFixedAssets() {
		return fixedAssets;
	}

	/**
	 * @param fixedAssets the fixedAssets to set
	 */
	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	/**
	 * @return the inventories
	 */
	public String getInventories() {
		return inventories;
	}

	/**
	 * @param inventories the inventories to set
	 */
	public void setInventories(String inventories) {
		this.inventories = inventories;
	}

	/**
	 * @return the loansGivenByTheCompany
	 */
	public String getLoansGivenByTheCompany() {
		return loansGivenByTheCompany;
	}

	/**
	 * @param loansGivenByTheCompany the loansGivenByTheCompany to set
	 */
	public void setLoansGivenByTheCompany(String loansGivenByTheCompany) {
		this.loansGivenByTheCompany = loansGivenByTheCompany;
	}

	/**
	 * @return the acceptanceOfPublicDeposits
	 */
	public String getAcceptanceOfPublicDeposits() {
		return acceptanceOfPublicDeposits;
	}

	/**
	 * @param acceptanceOfPublicDeposits the acceptanceOfPublicDeposits to set
	 */
	public void setAcceptanceOfPublicDeposits(String acceptanceOfPublicDeposits) {
		this.acceptanceOfPublicDeposits = acceptanceOfPublicDeposits;
	}

	/**
	 * @return the maintenanceOfCostRecords
	 */
	public String getMaintenanceOfCostRecords() {
		return maintenanceOfCostRecords;
	}

	/**
	 * @param maintenanceOfCostRecords the maintenanceOfCostRecords to set
	 */
	public void setMaintenanceOfCostRecords(String maintenanceOfCostRecords) {
		this.maintenanceOfCostRecords = maintenanceOfCostRecords;
	}

	/**
	 * @return the statutoryDues
	 */
	public String getStatutoryDues() {
		return statutoryDues;
	}

	/**
	 * @param statutoryDues the statutoryDues to set
	 */
	public void setStatutoryDues(String statutoryDues) {
		this.statutoryDues = statutoryDues;
	}

	/**
	 * @return the termLoans
	 */
	public String getTermLoans() {
		return termLoans;
	}

	/**
	 * @param termLoans the termLoans to set
	 */
	public void setTermLoans(String termLoans) {
		this.termLoans = termLoans;
	}

	/**
	 * @return the frudNoticed
	 */
	public String getFrudNoticed() {
		return frudNoticed;
	}

	/**
	 * @param frudNoticed the frudNoticed to set
	 */
	public void setFrudNoticed(String frudNoticed) {
		this.frudNoticed = frudNoticed;
	}

	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}

	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}

	/**
	 * @return the disclosureInAudReportRelatingToManagerialRemuneration
	 */
	public String getDisclosureInAudReportRelatingToManagerialRemuneration() {
		return disclosureInAudReportRelatingToManagerialRemuneration;
	}

	/**
	 * @param disclosureInAudReportRelatingToManagerialRemuneration the disclosureInAudReportRelatingToManagerialRemuneration to set
	 */
	public void setDisclosureInAudReportRelatingToManagerialRemuneration(
			String disclosureInAudReportRelatingToManagerialRemuneration) {
		this.disclosureInAudReportRelatingToManagerialRemuneration = disclosureInAudReportRelatingToManagerialRemuneration;
	}

	/**
	 * @return the disclosureInAudReportRelatingToNidhiCompany
	 */
	public String getDisclosureInAudReportRelatingToNidhiCompany() {
		return disclosureInAudReportRelatingToNidhiCompany;
	}

	/**
	 * @param disclosureInAudReportRelatingToNidhiCompany the disclosureInAudReportRelatingToNidhiCompany to set
	 */
	public void setDisclosureInAudReportRelatingToNidhiCompany(String disclosureInAudReportRelatingToNidhiCompany) {
		this.disclosureInAudReportRelatingToNidhiCompany = disclosureInAudReportRelatingToNidhiCompany;
	}

	/**
	 * @return the disclosureInAudReportRelatingToTransactionsWithRelatedParties
	 */
	public String getDisclosureInAudReportRelatingToTransactionsWithRelatedParties() {
		return disclosureInAudReportRelatingToTransactionsWithRelatedParties;
	}

	/**
	 * @param disclosureInAudReportRelatingToTransactionsWithRelatedParties the disclosureInAudReportRelatingToTransactionsWithRelatedParties to set
	 */
	public void setDisclosureInAudReportRelatingToTransactionsWithRelatedParties(
			String disclosureInAudReportRelatingToTransactionsWithRelatedParties) {
		this.disclosureInAudReportRelatingToTransactionsWithRelatedParties = disclosureInAudReportRelatingToTransactionsWithRelatedParties;
	}

	/**
	 * @return the disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb
	 */
	public String getDisclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb() {
		return disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb;
	}

	/**
	 * @param disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb the disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb to set
	 */
	public void setDisclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb(
			String disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb) {
		this.disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb = disclosureInAudReportRelatingToPrefAllotOrPvtPlacementOfSharesOrConvertDeb;
	}

	/**
	 * @return the disclosureInAudReportRelatingToNonCashTransactionsWithDir
	 */
	public String getDisclosureInAudReportRelatingToNonCashTransactionsWithDir() {
		return disclosureInAudReportRelatingToNonCashTransactionsWithDir;
	}

	/**
	 * @param disclosureInAudReportRelatingToNonCashTransactionsWithDir the disclosureInAudReportRelatingToNonCashTransactionsWithDir to set
	 */
	public void setDisclosureInAudReportRelatingToNonCashTransactionsWithDir(
			String disclosureInAudReportRelatingToNonCashTransactionsWithDir) {
		this.disclosureInAudReportRelatingToNonCashTransactionsWithDir = disclosureInAudReportRelatingToNonCashTransactionsWithDir;
	}

	/**
	 * @return the disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934
	 */
	public String getDisclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934() {
		return disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934;
	}

	/**
	 * @param disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934 the disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934 to set
	 */
	public void setDisclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934(
			String disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934) {
		this.disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934 = disclosureInAudReportRelatingToRegistrationUnderSection45iaOfRbiAct1934;
	}

	/**
	 * @return the disclosureInAudReportRelatingToInternalControlSystem
	 */
	public String getDisclosureInAudReportRelatingToInternalControlSystem() {
		return disclosureInAudReportRelatingToInternalControlSystem;
	}

	/**
	 * @param disclosureInAudReportRelatingToInternalControlSystem the disclosureInAudReportRelatingToInternalControlSystem to set
	 */
	public void setDisclosureInAudReportRelatingToInternalControlSystem(
			String disclosureInAudReportRelatingToInternalControlSystem) {
		this.disclosureInAudReportRelatingToInternalControlSystem = disclosureInAudReportRelatingToInternalControlSystem;
	}

	/**
	 * @return the disclosureInAudReportRelatingToAccumulatedLosses
	 */
	public String getDisclosureInAudReportRelatingToAccumulatedLosses() {
		return disclosureInAudReportRelatingToAccumulatedLosses;
	}

	/**
	 * @param disclosureInAudReportRelatingToAccumulatedLosses the disclosureInAudReportRelatingToAccumulatedLosses to set
	 */
	public void setDisclosureInAudReportRelatingToAccumulatedLosses(
			String disclosureInAudReportRelatingToAccumulatedLosses) {
		this.disclosureInAudReportRelatingToAccumulatedLosses = disclosureInAudReportRelatingToAccumulatedLosses;
	}

	/**
	 * @return the disclosureInAudReportRelatingToGuaranteeGiven
	 */
	public String getDisclosureInAudReportRelatingToGuaranteeGiven() {
		return disclosureInAudReportRelatingToGuaranteeGiven;
	}

	/**
	 * @param disclosureInAudReportRelatingToGuaranteeGiven the disclosureInAudReportRelatingToGuaranteeGiven to set
	 */
	public void setDisclosureInAudReportRelatingToGuaranteeGiven(String disclosureInAudReportRelatingToGuaranteeGiven) {
		this.disclosureInAudReportRelatingToGuaranteeGiven = disclosureInAudReportRelatingToGuaranteeGiven;
	}

	/**
	 * @return the disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues
	 */
	public String getDisclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues() {
		return disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues;
	}

	/**
	 * @param disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues the disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues to set
	 */
	public void setDisclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues(
			String disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues) {
		this.disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues = disclosureInAudReportRelatingToDefaultInRepaymentOfFinancialDues;
	}

	/**
	 * @return the disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund
	 */
	public String getDisclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund() {
		return disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund;
	}

	/**
	 * @param disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund the disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund to set
	 */
	public void setDisclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund(
			String disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund) {
		this.disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund = disclosureRelatingAmountRequiredToTransferredToInvestorEducationProtectionFund;
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
