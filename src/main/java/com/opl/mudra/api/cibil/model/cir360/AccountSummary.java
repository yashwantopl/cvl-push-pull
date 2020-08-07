package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountSummary {

	@JsonProperty("NoOfAccounts")
	private String noOfAccounts;
	
	@JsonProperty("NoOfActiveAccounts")
	private String noOfActiveAccounts;
	
	@JsonProperty("NoOfWriteOffs")
	private String noOfWriteOffs;
	
	@JsonProperty("TotalPastDue")
	private String totalPastDue;
	
	@JsonProperty("MostSevereStatusWithIn24Months")
	private String mostSevereStatusWithIn24Months;
	
	@JsonProperty("SingleHighestCredit")
	private String singleHighestCredit;
	
	@JsonProperty("SingleHighestSanctionAmount")
	private String singleHighestSanctionAmount;
	
	@JsonProperty("TotalHighCredit")
	private String totalHighCredit;
	
	@JsonProperty("AverageOpenBalance")
	private String averageOpenBalance;
	
	@JsonProperty("SingleHighestBalance")
	private String singleHighestBalance;
	
	@JsonProperty("NoOfPastDueAccounts")
	private String noOfPastDueAccounts;
	
	@JsonProperty("NoOfZeroBalanceAccounts")
	private String noOfZeroBalanceAccounts;
	
	@JsonProperty("RecentAccount")
	private String recentAccount;
	
	@JsonProperty("OldestAccount")
	private String oldestAccount;
	
	@JsonProperty("TotalBalanceAmount")
	private String totalBalanceAmount;
	
	@JsonProperty("TotalSanctionAmount")
	private String totalSanctionAmount;
	
	@JsonProperty("TotalCreditLimit")
	private String totalCreditLimit;
	
	@JsonProperty("TotalMonthlyPaymentAmount")
	private String totalMonthlyPaymentAmount;
	
	@JsonProperty("TotalWrittenOffAmount")
	private String totalWrittenOffAmount;
	
	@JsonProperty("NumberOfOpenAccounts")
	private String numberOfOpenAccounts;

	
	public String getNumberOfOpenAccounts() {
		return numberOfOpenAccounts;
	}

	public void setNumberOfOpenAccounts(String numberOfOpenAccounts) {
		this.numberOfOpenAccounts = numberOfOpenAccounts;
	}

	public String getNoOfAccounts() {
		return noOfAccounts;
	}

	public void setNoOfAccounts(String noOfAccounts) {
		this.noOfAccounts = noOfAccounts;
	}

	public String getNoOfActiveAccounts() {
		return noOfActiveAccounts;
	}

	public void setNoOfActiveAccounts(String noOfActiveAccounts) {
		this.noOfActiveAccounts = noOfActiveAccounts;
	}

	public String getNoOfWriteOffs() {
		return noOfWriteOffs;
	}

	public void setNoOfWriteOffs(String noOfWriteOffs) {
		this.noOfWriteOffs = noOfWriteOffs;
	}

	public String getTotalPastDue() {
		return totalPastDue;
	}

	public void setTotalPastDue(String totalPastDue) {
		this.totalPastDue = totalPastDue;
	}

	public String getMostSevereStatusWithIn24Months() {
		return mostSevereStatusWithIn24Months;
	}

	public void setMostSevereStatusWithIn24Months(String mostSevereStatusWithIn24Months) {
		this.mostSevereStatusWithIn24Months = mostSevereStatusWithIn24Months;
	}

	public String getSingleHighestCredit() {
		return singleHighestCredit;
	}

	public void setSingleHighestCredit(String singleHighestCredit) {
		this.singleHighestCredit = singleHighestCredit;
	}

	public String getSingleHighestSanctionAmount() {
		return singleHighestSanctionAmount;
	}

	public void setSingleHighestSanctionAmount(String singleHighestSanctionAmount) {
		this.singleHighestSanctionAmount = singleHighestSanctionAmount;
	}

	public String getTotalHighCredit() {
		return totalHighCredit;
	}

	public void setTotalHighCredit(String totalHighCredit) {
		this.totalHighCredit = totalHighCredit;
	}

	public String getAverageOpenBalance() {
		return averageOpenBalance;
	}

	public void setAverageOpenBalance(String averageOpenBalance) {
		this.averageOpenBalance = averageOpenBalance;
	}

	public String getSingleHighestBalance() {
		return singleHighestBalance;
	}

	public void setSingleHighestBalance(String singleHighestBalance) {
		this.singleHighestBalance = singleHighestBalance;
	}

	public String getNoOfPastDueAccounts() {
		return noOfPastDueAccounts;
	}

	public void setNoOfPastDueAccounts(String noOfPastDueAccounts) {
		this.noOfPastDueAccounts = noOfPastDueAccounts;
	}

	public String getNoOfZeroBalanceAccounts() {
		return noOfZeroBalanceAccounts;
	}

	public void setNoOfZeroBalanceAccounts(String noOfZeroBalanceAccounts) {
		this.noOfZeroBalanceAccounts = noOfZeroBalanceAccounts;
	}

	public String getRecentAccount() {
		return recentAccount;
	}

	public void setRecentAccount(String recentAccount) {
		this.recentAccount = recentAccount;
	}

	public String getOldestAccount() {
		return oldestAccount;
	}

	public void setOldestAccount(String oldestAccount) {
		this.oldestAccount = oldestAccount;
	}

	public String getTotalBalanceAmount() {
		return totalBalanceAmount;
	}

	public void setTotalBalanceAmount(String totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}

	public String getTotalSanctionAmount() {
		return totalSanctionAmount;
	}

	public void setTotalSanctionAmount(String totalSanctionAmount) {
		this.totalSanctionAmount = totalSanctionAmount;
	}

	public String getTotalCreditLimit() {
		return totalCreditLimit;
	}

	public void setTotalCreditLimit(String totalCreditLimit) {
		this.totalCreditLimit = totalCreditLimit;
	}

	public String getTotalMonthlyPaymentAmount() {
		return totalMonthlyPaymentAmount;
	}

	public void setTotalMonthlyPaymentAmount(String totalMonthlyPaymentAmount) {
		this.totalMonthlyPaymentAmount = totalMonthlyPaymentAmount;
	}

	public String getTotalWrittenOffAmount() {
		return totalWrittenOffAmount;
	}

	public void setTotalWrittenOffAmount(String totalWrittenOffAmount) {
		this.totalWrittenOffAmount = totalWrittenOffAmount;
	}
	
}
