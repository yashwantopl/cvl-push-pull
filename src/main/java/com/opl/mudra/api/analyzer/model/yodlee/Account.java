package com.opl.mudra.api.analyzer.model.yodlee;

import java.util.List;

public class Account {
	private Double interestRate;
	private Boolean includeInNetWorth;
	private String accountName;
	private CurrentBalance currentBalance;
	private String accountType;
	private Boolean isManual;
	private String displayedName;
	private String accountNumber;
	private String accountStatus;
	private String lastUpdated;
	private Boolean isAsset;
	private String createdDate;
	private Balance balance;
	private String aggregationSource;
	private String providerId;
	private String maturityDate;
	private Integer providerAccountId;
	private String cONTAINER;
	private Integer id;
	private List<AdditionalDataSet> dataset = null;
	private String providerName;
	private AvailableBalance availableBalance;
	private AvailableCash availableCash;
	private AvailableCredit availableCredit;
	private TotalCashLimit totalCashLimit;
	private TotalCreditLine totalCreditLine;
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public Boolean getIncludeInNetWorth() {
		return includeInNetWorth;
	}
	public void setIncludeInNetWorth(Boolean includeInNetWorth) {
		this.includeInNetWorth = includeInNetWorth;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public CurrentBalance getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(CurrentBalance currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Boolean getIsManual() {
		return isManual;
	}
	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}
	public String getDisplayedName() {
		return displayedName;
	}
	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Boolean getIsAsset() {
		return isAsset;
	}
	public void setIsAsset(Boolean isAsset) {
		this.isAsset = isAsset;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Balance getBalance() {
		return balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	public String getAggregationSource() {
		return aggregationSource;
	}
	public void setAggregationSource(String aggregationSource) {
		this.aggregationSource = aggregationSource;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Integer getProviderAccountId() {
		return providerAccountId;
	}
	public void setProviderAccountId(Integer providerAccountId) {
		this.providerAccountId = providerAccountId;
	}
	public String getcONTAINER() {
		return cONTAINER;
	}
	public void setcONTAINER(String cONTAINER) {
		this.cONTAINER = cONTAINER;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<AdditionalDataSet> getDataset() {
		return dataset;
	}
	public void setDataset(List<AdditionalDataSet> dataset) {
		this.dataset = dataset;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public AvailableBalance getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(AvailableBalance availableBalance) {
		this.availableBalance = availableBalance;
	}
	public AvailableCash getAvailableCash() {
		return availableCash;
	}
	public void setAvailableCash(AvailableCash availableCash) {
		this.availableCash = availableCash;
	}
	public AvailableCredit getAvailableCredit() {
		return availableCredit;
	}
	public void setAvailableCredit(AvailableCredit availableCredit) {
		this.availableCredit = availableCredit;
	}
	public TotalCashLimit getTotalCashLimit() {
		return totalCashLimit;
	}
	public void setTotalCashLimit(TotalCashLimit totalCashLimit) {
		this.totalCashLimit = totalCashLimit;
	}
	public TotalCreditLine getTotalCreditLine() {
		return totalCreditLine;
	}
	public void setTotalCreditLine(TotalCreditLine totalCreditLine) {
		this.totalCreditLine = totalCreditLine;
	}
	
	
}
