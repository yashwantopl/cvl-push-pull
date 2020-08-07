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
public class FinancialSnapshotHistories {

	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("consolidated-long-term-borrowings")
	private String consolidatedLongTermBorrowings;

	@JsonProperty("revenue-from-operations")
	private String revenueFromOperations;

	@JsonProperty("ebitda")
	private String ebitda;

	@JsonProperty("profit-or-(loss)")
	private String profitOrLoss;

	@JsonProperty("networth")
	private String networth;

	@JsonProperty("long-term-borrowings")
	private String longTermBorrowings;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the consolidatedLongTermBorrowings
	 */
	public String getConsolidatedLongTermBorrowings() {
		return consolidatedLongTermBorrowings;
	}

	/**
	 * @param consolidatedLongTermBorrowings
	 *            the consolidatedLongTermBorrowings to set
	 */
	public void setConsolidatedLongTermBorrowings(String consolidatedLongTermBorrowings) {
		this.consolidatedLongTermBorrowings = consolidatedLongTermBorrowings;
	}

	/**
	 * @return the revenueFromOperations
	 */
	public String getRevenueFromOperations() {
		return revenueFromOperations;
	}

	/**
	 * @param revenueFromOperations
	 *            the revenueFromOperations to set
	 */
	public void setRevenueFromOperations(String revenueFromOperations) {
		this.revenueFromOperations = revenueFromOperations;
	}

	/**
	 * @return the ebitda
	 */
	public String getEbitda() {
		return ebitda;
	}

	/**
	 * @param ebitda
	 *            the ebitda to set
	 */
	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}

	/**
	 * @return the profitOrLoss
	 */
	public String getProfitOrLoss() {
		return profitOrLoss;
	}

	/**
	 * @param profitOrLoss
	 *            the profitOrLoss to set
	 */
	public void setProfitOrLoss(String profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}

	/**
	 * @return the networth
	 */
	public String getNetworth() {
		return networth;
	}

	/**
	 * @param networth
	 *            the networth to set
	 */
	public void setNetworth(String networth) {
		this.networth = networth;
	}

	/**
	 * @return the longTermBorrowings
	 */
	public String getLongTermBorrowings() {
		return longTermBorrowings;
	}

	/**
	 * @param longTermBorrowings
	 *            the longTermBorrowings to set
	 */
	public void setLongTermBorrowings(String longTermBorrowings) {
		this.longTermBorrowings = longTermBorrowings;
	}

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

}
