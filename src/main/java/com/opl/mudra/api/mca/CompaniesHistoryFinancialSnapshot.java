package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryFinancialSnapshot {
	
		@JsonProperty("as-on-date")
	    private String asOnDate;
		
	    @JsonProperty("consolidated-long-term-borrowings")
	    private String consolidatedLongTermBorrowings;
	    
	    @JsonProperty("revenue-from-operations")
	    private String revenueFromOperations;
	    
	    @JsonProperty("ebita")
	    private String ebita;
	    
	    @JsonProperty("profit-or-(loss)")
	    private String profitOrLoss;
	    
	    @JsonProperty("networth")
	    private String networth;
	    
	    @JsonProperty("long-term-borrowings")
	    private String longTermBorrowings;
	    
	    @JsonProperty("financial-year")
	    private String financialYear;

		public String getAsOnDate() {
			return asOnDate;
		}

		public String getConsolidatedLongTermBorrowings() {
			return consolidatedLongTermBorrowings;
		}

		public String getRevenueFromOperations() {
			return revenueFromOperations;
		}

		public String getEbita() {
			return ebita;
		}

		public String getProfitOrLoss() {
			return profitOrLoss;
		}

		public String getNetworth() {
			return networth;
		}

		public String getLongTermBorrowings() {
			return longTermBorrowings;
		}

		public String getFinancialYear() {
			return financialYear;
		}

		public void setAsOnDate(String asOnDate) {
			this.asOnDate = asOnDate;
		}

		public void setConsolidatedLongTermBorrowings(String consolidatedLongTermBorrowings) {
			this.consolidatedLongTermBorrowings = consolidatedLongTermBorrowings;
		}

		public void setRevenueFromOperations(String revenueFromOperations) {
			this.revenueFromOperations = revenueFromOperations;
		}

		public void setEbita(String ebita) {
			this.ebita = ebita;
		}

		public void setProfitOrLoss(String profitOrLoss) {
			this.profitOrLoss = profitOrLoss;
		}

		public void setNetworth(String networth) {
			this.networth = networth;
		}

		public void setLongTermBorrowings(String longTermBorrowings) {
			this.longTermBorrowings = longTermBorrowings;
		}

		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
	    
	    
	    
	    
	    
	    

}
