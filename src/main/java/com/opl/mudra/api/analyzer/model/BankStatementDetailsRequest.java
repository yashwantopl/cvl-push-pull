package com.opl.mudra.api.analyzer.model;

import java.io.Serializable;

public class BankStatementDetailsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String period;

	private String bankName;

	private String accNo;

	private Long noOfCredits;

	private Long creditAmount;

	private Long noOfDebits;

	private Long debitAmount;

	private Long totalTransaction;

	private Long avgDailyBalance;

	private Long avgMonthlyBalance;

	private Long totalChequeIssues;

	private Long lastMonthChequeBounced;

	private Long lastSixMonthChequeBounced;
	
	private Long applicationId;
	
	private Long userId;

	private Long bsMasterId;

	private Long profileId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public Long getNoOfCredits() {
		return noOfCredits;
	}

	public void setNoOfCredits(Long noOfCredits) {
		this.noOfCredits = noOfCredits;
	}

	public Long getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Long creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Long getNoOfDebits() {
		return noOfDebits;
	}

	public void setNoOfDebits(Long noOfDebits) {
		this.noOfDebits = noOfDebits;
	}

	public Long getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Long debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Long getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(Long totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public Long getAvgDailyBalance() {
		return avgDailyBalance;
	}

	public void setAvgDailyBalance(Long avgDailyBalance) {
		this.avgDailyBalance = avgDailyBalance;
	}

	public Long getAvgMonthlyBalance() {
		return avgMonthlyBalance;
	}

	public void setAvgMonthlyBalance(Long avgMonthlyBalance) {
		this.avgMonthlyBalance = avgMonthlyBalance;
	}

	public Long getTotalChequeIssues() {
		return totalChequeIssues;
	}

	public void setTotalChequeIssues(Long totalChequeIssues) {
		this.totalChequeIssues = totalChequeIssues;
	}

	public Long getLastMonthChequeBounced() {
		return lastMonthChequeBounced;
	}

	public void setLastMonthChequeBounced(Long lastMonthChequeBounced) {
		this.lastMonthChequeBounced = lastMonthChequeBounced;
	}

	public Long getLastSixMonthChequeBounced() {
		return lastSixMonthChequeBounced;
	}

	public void setLastSixMonthChequeBounced(Long lastSixMonthChequeBounced) {
		this.lastSixMonthChequeBounced = lastSixMonthChequeBounced;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getBsMasterId() {
		return bsMasterId;
	}

	public void setBsMasterId(Long bsMasterId) {
		this.bsMasterId = bsMasterId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
}
