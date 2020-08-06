package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialCreditReportSummary {

	@JsonProperty("NumberOfAccounts")
	private String numberOfAccounts;

	@JsonProperty("NumberOfOpenAccounts")
	private String numberOfOpenAccounts;

	@JsonProperty("NumberOfCloseAccounts")
	private String numberOfCloseAccounts;

	@JsonProperty("TotalPastDueAmount")
	private String totalPastDueAmount;

	@JsonProperty("NumberOfPastDueAccounts")
	private String numberOfPastDueAccounts;

	@JsonProperty("NumberOfWriteoffAccounts")
	private String numberOfWriteoffAccounts;

	@JsonProperty("TotalSanctionAmount")
	private String totalSanctionAmount;

	@JsonProperty("NumberOfZeroBalanceAccounts")
	private String numberOfZeroBalanceAccounts;

	@JsonProperty("TotalMonthlyPaymentAmount")
	private String totalMonthlyPaymentAmount;

	public String getNumberOfCloseAccounts() {
		return numberOfCloseAccounts;
	}

	public void setNumberOfCloseAccounts(String numberOfCloseAccounts) {
		this.numberOfCloseAccounts = numberOfCloseAccounts;
	}

	public String getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public void setNumberOfAccounts(String numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;
	}

	public String getNumberOfOpenAccounts() {
		return numberOfOpenAccounts;
	}

	public void setNumberOfOpenAccounts(String numberOfOpenAccounts) {
		this.numberOfOpenAccounts = numberOfOpenAccounts;
	}

	public String getTotalPastDueAmount() {
		return totalPastDueAmount;
	}

	public void setTotalPastDueAmount(String totalPastDueAmount) {
		this.totalPastDueAmount = totalPastDueAmount;
	}

	public String getNumberOfPastDueAccounts() {
		return numberOfPastDueAccounts;
	}

	public void setNumberOfPastDueAccounts(String numberOfPastDueAccounts) {
		this.numberOfPastDueAccounts = numberOfPastDueAccounts;
	}

	public String getNumberOfWriteoffAccounts() {
		return numberOfWriteoffAccounts;
	}

	public void setNumberOfWriteoffAccounts(String numberOfWriteoffAccounts) {
		this.numberOfWriteoffAccounts = numberOfWriteoffAccounts;
	}

	public String getTotalSanctionAmount() {
		return totalSanctionAmount;
	}

	public void setTotalSanctionAmount(String totalSanctionAmount) {
		this.totalSanctionAmount = totalSanctionAmount;
	}

	public String getNumberOfZeroBalanceAccounts() {
		return numberOfZeroBalanceAccounts;
	}

	public void setNumberOfZeroBalanceAccounts(String numberOfZeroBalanceAccounts) {
		this.numberOfZeroBalanceAccounts = numberOfZeroBalanceAccounts;
	}

	public String getTotalMonthlyPaymentAmount() {
		return totalMonthlyPaymentAmount;
	}

	public void setTotalMonthlyPaymentAmount(String totalMonthlyPaymentAmount) {
		this.totalMonthlyPaymentAmount = totalMonthlyPaymentAmount;
	}

}
