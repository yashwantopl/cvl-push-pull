/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Current {
	
	
private String gstin;
	

	
	@JsonProperty("filing_status")
	private FilingStatus[] filingStatus;
	
	@JsonProperty("transaction_summary")
	private TransactionSummary transactionSummary;
	
	@JsonProperty("gstr1")
	private Gstr1 gstr1;
	
	@JsonProperty("Top3Cus")
	private Top3Cus[] top3Cus;

	@JsonProperty("Averages")
	private Averages averages;

	@JsonProperty("gstr3b")
	private Gstr3b gstr3b;

	@JsonProperty("gstr2a")
	private Gstr2A gstr2a;

	@JsonProperty("financial_year")
	private String financialYear;

	@JsonProperty("itc_credit")
	private ItcCredit itcCredit;

	@JsonProperty("month_wise_summary")
	private MonthWiseSummary[] monthWiseSummary;




	/**
	 * @return the gstr2a
	 */
	public Gstr2A getGstr2a() {
		return gstr2a;
	}

	/**
	 * @param gstr2a the gstr2a to set
	 */
	public void setGstr2a(Gstr2A gstr2a) {
		this.gstr2a = gstr2a;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	/**
	 * @return the filingStatus
	 */
	public FilingStatus[] getFilingStatus() {
		return filingStatus;
	}

	/**
	 * @param filingStatus the filingStatus to set
	 */
	public void setFilingStatus(FilingStatus[] filingStatus) {
		this.filingStatus = filingStatus;
	}

	/**
	 * @return the transactionSummary
	 */
	public TransactionSummary getTransactionSummary() {
		return transactionSummary;
	}

	/**
	 * @param transactionSummary the transactionSummary to set
	 */
	public void setTransactionSummary(TransactionSummary transactionSummary) {
		this.transactionSummary = transactionSummary;
	}

	/**
	 * @return the gstr1
	 */
	public Gstr1 getGstr1() {
		return gstr1;
	}

	/**
	 * @param gstr1 the gstr1 to set
	 */
	public void setGstr1(Gstr1 gstr1) {
		this.gstr1 = gstr1;
	}

	/**
	 * @return the top3Cus
	 */
	public Top3Cus[] getTop3Cus() {
		return top3Cus;
	}

	/**
	 * @param top3Cus the top3Cus to set
	 */
	public void setTop3Cus(Top3Cus[] top3Cus) {
		this.top3Cus = top3Cus;
	}

	/**
	 * @return the averages
	 */
	public Averages getAverages() {
		return averages;
	}

	/**
	 * @param averages the averages to set
	 */
	public void setAverages(Averages averages) {
		this.averages = averages;
	}

	/**
	 * @return the gstr3b
	 */
	public Gstr3b getGstr3b() {
		return gstr3b;
	}

	/**
	 * @param gstr3b the gstr3b to set
	 */
	public void setGstr3b(Gstr3b gstr3b) {
		this.gstr3b = gstr3b;
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
	 * @return the itcCredit
	 */
	public ItcCredit getItcCredit() {
		return itcCredit;
	}

	/**
	 * @param itcCredit the itcCredit to set
	 */
	public void setItcCredit(ItcCredit itcCredit) {
		this.itcCredit = itcCredit;
	}

	/**
	 * @return the monthWiseSummary
	 */
	public MonthWiseSummary[] getMonthWiseSummary() {
		return monthWiseSummary;
	}

	/**
	 * @param monthWiseSummary the monthWiseSummary to set
	 */
	public void setMonthWiseSummary(MonthWiseSummary[] monthWiseSummary) {
		this.monthWiseSummary = monthWiseSummary;
	}
	
	
	

}
