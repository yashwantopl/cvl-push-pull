package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountDetailsType {
	
	@JsonProperty("seq")
	private String seq;
	
	@JsonProperty("ReportedDate")
	private String reportedDate;
	
	@JsonProperty("id")
	private String id;

	@JsonProperty("typeCode")
	private String typeCode;

	
	@JsonProperty("AccountNumber")
	private String accountNumber;
	
	@JsonProperty("CurrentBalance")
	private String currentBalance;
	
	@JsonProperty("Institution")
	private String institution;
	
	@JsonProperty("AccountType")
	private String accountType;
	
	@JsonProperty("OwnershipType")
	private String ownershipType;
	
	@JsonProperty("Balance")
	private String balance;
	
	@JsonProperty("PastDueAmount")
	private String pastDueAmount;
	
	@JsonProperty("DatePastDue")
	private String datePastDue;
	
	@JsonProperty("DisbursedAmount")
	private String disbursedAmount;

	@JsonProperty("LoanCategory")
	private String loanCategory;
	
	@JsonProperty("LoanPurpose")
	private String loanPurpose;
	
	@JsonProperty("LastPayment")
	private String lastPayment;
	
	@JsonProperty("WriteOffAmount")
	private String writeOffAmount;
	
	
	@JsonProperty("Open")
	private String open;
	
	@JsonProperty("SanctionAmount")
	private String sanctionAmount;
	
	@JsonProperty("HighCredit")
	private String highCredit;
	
	@JsonProperty("LastPaymentDate")
	private String lastPaymentDate;
	
	@JsonProperty("DateReported")
	private String dateReported;
	
	@JsonProperty("DateOpened")
	private String dateOpened;

	@JsonProperty("DateClosed")
	private String dateClosed;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("DateWrittenOff")
	private String dateWrittenOff;

	@JsonProperty("LoanCycleID")
	private String loanCycleID;

	@JsonProperty("DateSanctioned")
	private String dateSanctioned;

	@JsonProperty("DateApplied")
	private String dateApplied;

	@JsonProperty("InterestRate")
	private String interestRate;

	@JsonProperty("AppliedAmount")
	private String appliedAmount;

	@JsonProperty("NoOfInstallments")
	private String noOfInstallments;
	
	@JsonProperty("RepaymentTenure")
	private String repaymentTenure;

	@JsonProperty("DisputeCode")
	private String disputeCode;

	@JsonProperty("InstallmentAmount")
	private String installmentAmount;

	@JsonProperty("KeyPerson")
	private RelationInfoType keyPerson;

	@JsonProperty("Nominee")
	private RelationInfoType nominee;

	@JsonProperty("TermFrequency")
	private String termFrequency;

	@JsonProperty("CreditLimit")
	private String creditLimit;

	@JsonProperty("CollateralValue")
	private String collateralValue;

	@JsonProperty("CollateralType")
	private String collateralType;

	
	@JsonProperty("AccountStatus")
	private String accountStatus;

	@JsonProperty("AssetClassification")
	private String assetClassification;
	
	@JsonProperty("SuitFiledStatus")
	private String suitFiledStatus;

	@JsonProperty("DaysPastDue")
	private String daysPastDue;

	@JsonProperty("TypeOfInsurance")
	private String typeOfInsurance;

	@JsonProperty("InsurancePolicyAmount")
	private String insurancePolicyAmount;

	@JsonProperty("NumberOfMeetingsHeld")
	private String numberOfMeetingsHeld;

	@JsonProperty("NumberOfMeetingsMissed")
	private String numberOfMeetingsMissed;


	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(String lastPayment) {
		this.lastPayment = lastPayment;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public String getDateReported() {
		return dateReported;
	}

	public void setDateReported(String dateReported) {
		this.dateReported = dateReported;
	}

	public String getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getRepaymentTenure() {
		return repaymentTenure;
	}

	public void setRepaymentTenure(String repaymentTenure) {
		this.repaymentTenure = repaymentTenure;
	}

	public String getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAssetClassification() {
		return assetClassification;
	}

	public void setAssetClassification(String assetClassification) {
		this.assetClassification = assetClassification;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getPastDueAmount() {
		return pastDueAmount;
	}

	public void setPastDueAmount(String pastDueAmount) {
		this.pastDueAmount = pastDueAmount;
	}

	public String getDatePastDue() {
		return datePastDue;
	}

	public void setDatePastDue(String datePastDue) {
		this.datePastDue = datePastDue;
	}

	public String getDisbursedAmount() {
		return disbursedAmount;
	}

	public void setDisbursedAmount(String disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}

	public String getLoanCategory() {
		return loanCategory;
	}

	public void setLoanCategory(String loanCategory) {
		this.loanCategory = loanCategory;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getWriteOffAmount() {
		return writeOffAmount;
	}

	public void setWriteOffAmount(String writeOffAmount) {
		this.writeOffAmount = writeOffAmount;
	}

	public String getHighCredit() {
		return highCredit;
	}

	public void setHighCredit(String highCredit) {
		this.highCredit = highCredit;
	}

	public String getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDateWrittenOff() {
		return dateWrittenOff;
	}

	public void setDateWrittenOff(String dateWrittenOff) {
		this.dateWrittenOff = dateWrittenOff;
	}

	public String getLoanCycleID() {
		return loanCycleID;
	}

	public void setLoanCycleID(String loanCycleID) {
		this.loanCycleID = loanCycleID;
	}

	public String getDateSanctioned() {
		return dateSanctioned;
	}

	public void setDateSanctioned(String dateSanctioned) {
		this.dateSanctioned = dateSanctioned;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getAppliedAmount() {
		return appliedAmount;
	}

	public void setAppliedAmount(String appliedAmount) {
		this.appliedAmount = appliedAmount;
	}

	public String getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(String noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public String getDisputeCode() {
		return disputeCode;
	}

	public void setDisputeCode(String disputeCode) {
		this.disputeCode = disputeCode;
	}

	public RelationInfoType getKeyPerson() {
		return keyPerson;
	}

	public void setKeyPerson(RelationInfoType keyPerson) {
		this.keyPerson = keyPerson;
	}

	public RelationInfoType getNominee() {
		return nominee;
	}

	public void setNominee(RelationInfoType nominee) {
		this.nominee = nominee;
	}

	public String getTermFrequency() {
		return termFrequency;
	}

	public void setTermFrequency(String termFrequency) {
		this.termFrequency = termFrequency;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCollateralValue() {
		return collateralValue;
	}

	public void setCollateralValue(String collateralValue) {
		this.collateralValue = collateralValue;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public String getSuitFiledStatus() {
		return suitFiledStatus;
	}

	public void setSuitFiledStatus(String suitFiledStatus) {
		this.suitFiledStatus = suitFiledStatus;
	}

	public String getDaysPastDue() {
		return daysPastDue;
	}

	public void setDaysPastDue(String daysPastDue) {
		this.daysPastDue = daysPastDue;
	}

	public String getTypeOfInsurance() {
		return typeOfInsurance;
	}

	public void setTypeOfInsurance(String typeOfInsurance) {
		this.typeOfInsurance = typeOfInsurance;
	}

	public String getInsurancePolicyAmount() {
		return insurancePolicyAmount;
	}

	public void setInsurancePolicyAmount(String insurancePolicyAmount) {
		this.insurancePolicyAmount = insurancePolicyAmount;
	}

	public String getNumberOfMeetingsHeld() {
		return numberOfMeetingsHeld;
	}

	public void setNumberOfMeetingsHeld(String numberOfMeetingsHeld) {
		this.numberOfMeetingsHeld = numberOfMeetingsHeld;
	}

	public String getNumberOfMeetingsMissed() {
		return numberOfMeetingsMissed;
	}

	public void setNumberOfMeetingsMissed(String numberOfMeetingsMissed) {
		this.numberOfMeetingsMissed = numberOfMeetingsMissed;
	}
	

}
