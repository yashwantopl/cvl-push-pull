package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OverallAccountSummary {
	
	@JsonProperty("NumberOfOpenAccounts")
	private String numberOfOpenAccounts;
	
	@JsonProperty("NumberOfPastDueAccounts")
	private String numberOfPastDueAccounts;
	
	@JsonProperty("TotalOutstandingBalance")
	private String totalOutstandingBalance;

	public String getNumberOfOpenAccounts() {
		return numberOfOpenAccounts;
	}

	public void setNumberOfOpenAccounts(String numberOfOpenAccounts) {
		this.numberOfOpenAccounts = numberOfOpenAccounts;
	}

	public String getNumberOfPastDueAccounts() {
		return numberOfPastDueAccounts;
	}

	public void setNumberOfPastDueAccounts(String numberOfPastDueAccounts) {
		this.numberOfPastDueAccounts = numberOfPastDueAccounts;
	}

	public String getTotalOutstandingBalance() {
		return totalOutstandingBalance;
	}

	public void setTotalOutstandingBalance(String totalOutstandingBalance) {
		this.totalOutstandingBalance = totalOutstandingBalance;
	}
	
	
}
