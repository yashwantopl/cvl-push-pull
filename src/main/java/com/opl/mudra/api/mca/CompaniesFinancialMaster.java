package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesFinancialMaster {
	
	@JsonProperty("as-on-date")
	private String asOnDate;
	
	@JsonProperty("financial-statements-from")
	private String financialStatementsFrom;
	
	@JsonProperty("financial-statements-to")
	private String financialStatementsTo;

	@JsonProperty("financial-year")
	private String financialYear;

	public String getAsOnDate() {
		return asOnDate;
	}

	public String getFinancialStatementsFrom() {
		return financialStatementsFrom;
	}

	public String getFinancialStatementsTo() {
		return financialStatementsTo;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	public void setFinancialStatementsFrom(String financialStatementsFrom) {
		this.financialStatementsFrom = financialStatementsFrom;
	}

	public void setFinancialStatementsTo(String financialStatementsTo) {
		this.financialStatementsTo = financialStatementsTo;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	
}
