package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.LoanApplicationRequest;

public class LiabilitiesDetailsRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double advancePaymentsFromCustomers;

	private Long createdBy;

	private Date createdDate;

	private Double debentures;

	private Double deferredPaymentsCredits;

	private Double deferredTaxLiability;

	private Double depositsOrInstalmentsOfTermLoans;

	private Double dividendPayable;

	private String financialYearlyStatement;
	
	private Double fromApplicationBank;

	private Double fromOtherBanks;

	private Double generalReserve;

	private Boolean isActive;

	private Long modifiedBy;

	private Date modifiedDate;

	private Double netWorth;

	private Double ordinarySharesCapital;

	private Double otherCurrentLiability;

	private Double otherReservse;

	private Double otherStatutoryLiability;

	private Double otherTermLiabilies;

	private Double others;

	private Double preferencesShares;

	private Double provisionalForTaxation;

	private Double revaluationReservse;

	private Double shortTermBorrowingFromOthers;

	private Double subTotalA;

	private Double subTotalB;

	private Double sundryCreditors;

	private Double surplusOrDeficit;

	private Double termDeposits;

	private Double termLoans;

	private Double totalCurrentLiabilities;

	private Double totalLiability;

	private Double totalOutsideLiabilities;

	private Double totalTermLiabilities;

	private Double whichBpAndBd;
	
	private Double termLiabilitiesSecured;
	
	private Double shareWarrentsOutstanding;
	
	private Double minorityInterest;
	
	private Double otherNclUnsecuredLoansFromOther;
	
	private Double otherNclLongTermProvisions;
	
	private Double otherNclOthers;
	
	private Double otherNclUnsecuredLoansFromPromoters;
	
	private Double otherNcl;
	
	
	public LiabilitiesDetailsRequest() {
		super();
		this.advancePaymentsFromCustomers = 0.0;
		this.debentures = 0.0;
		this.deferredPaymentsCredits  = 0.0;
		this.deferredTaxLiability  = 0.0;
		this.depositsOrInstalmentsOfTermLoans  = 0.0;
		this.dividendPayable = 0.0;
		this.fromApplicationBank  = 0.0;
		this.fromOtherBanks  = 0.0;
		this.generalReserve  = 0.0;
		this.netWorth  = 0.0;
		this.ordinarySharesCapital = 0.0;
		this.otherCurrentLiability = 0.0;
		this.otherReservse  = 0.0;
		this.otherStatutoryLiability = 0.0;
		this.otherTermLiabilies = 0.0;
		this.others = 0.0;
		this.preferencesShares  = 0.0;
		this.provisionalForTaxation = 0.0;
		this.revaluationReservse  = 0.0;
		this.shortTermBorrowingFromOthers  = 0.0;
		this.subTotalA  = 0.0;
		this.subTotalB  = 0.0;
		this.sundryCreditors  = 0.0;
		this.surplusOrDeficit  = 0.0;
		this.termDeposits  = 0.0;
		this.termLoans  = 0.0;
		this.totalCurrentLiabilities  = 0.0;
		this.totalLiability  = 0.0;
		this.totalOutsideLiabilities = 0.0;
		this.totalTermLiabilities = 0.0;
		this.whichBpAndBd  = 0.0;
		this.termLiabilitiesSecured  = 0.0;
		this.shareWarrentsOutstanding  = 0.0;
		this.minorityInterest  = 0.0;
		this.otherNclUnsecuredLoansFromOther  = 0.0;
		this.otherNclLongTermProvisions = 0.0;
		this.otherNclOthers  = 0.0;
		this.otherNclUnsecuredLoansFromPromoters = 0.0;
		this.otherNcl  = 0.0;
		this.termLiabilitiesUnsecured = 0.0;
	}

	public Double getOtherNcl() {
		return otherNcl;
	}

	public void setOtherNcl(Double otherNcl) {
		this.otherNcl = otherNcl;
	}

	public Double getOtherNclUnsecuredLoansFromPromoters() {
		return otherNclUnsecuredLoansFromPromoters;
	}

	public void setOtherNclUnsecuredLoansFromPromoters(Double otherNclUnsecuredLoansFromPromoters) {
		this.otherNclUnsecuredLoansFromPromoters = otherNclUnsecuredLoansFromPromoters;
	}

	public Double getTermLiabilitiesSecured() {
		return termLiabilitiesSecured;
	}

	public Double getShareWarrentsOutstanding() {
		return shareWarrentsOutstanding;
	}

	public Double getMinorityInterest() {
		return minorityInterest;
	}

	public Double getOtherNclUnsecuredLoansFromOther() {
		return otherNclUnsecuredLoansFromOther;
	}

	public Double getOtherNclLongTermProvisions() {
		return otherNclLongTermProvisions;
	}

	public Double getOtherNclOthers() {
		return otherNclOthers;
	}

	public Double getTermLiabilitiesUnsecured() {
		return termLiabilitiesUnsecured;
	}

	public void setTermLiabilitiesSecured(Double termLiabilitiesSecured) {
		this.termLiabilitiesSecured = termLiabilitiesSecured;
	}

	public void setShareWarrentsOutstanding(Double shareWarrentsOutstanding) {
		this.shareWarrentsOutstanding = shareWarrentsOutstanding;
	}

	public void setMinorityInterest(Double minorityInterest) {
		this.minorityInterest = minorityInterest;
	}

	public void setOtherNclUnsecuredLoansFromOther(Double otherNclUnsecuredLoansFromOther) {
		this.otherNclUnsecuredLoansFromOther = otherNclUnsecuredLoansFromOther;
	}

	public void setOtherNclLongTermProvisions(Double otherNclLongTermProvisions) {
		this.otherNclLongTermProvisions = otherNclLongTermProvisions;
	}

	public void setOtherNclOthers(Double otherNclOthers) {
		this.otherNclOthers = otherNclOthers;
	}

	public void setTermLiabilitiesUnsecured(Double termLiabilitiesUnsecured) {
		this.termLiabilitiesUnsecured = termLiabilitiesUnsecured;
	}

	private Double termLiabilitiesUnsecured;

	private String year;
	
	private Long storageDetailsId;

	private Long applicationId;
	
	
	public Long getStorageDetailsId() {
		return storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAdvancePaymentsFromCustomers() {
		return this.advancePaymentsFromCustomers;
	}

	public void setAdvancePaymentsFromCustomers(Double advancePaymentsFromCustomers) {
		this.advancePaymentsFromCustomers = advancePaymentsFromCustomers;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getDebentures() {
		return this.debentures;
	}

	public void setDebentures(Double debentures) {
		this.debentures = debentures;
	}

	public Double getDeferredPaymentsCredits() {
		return this.deferredPaymentsCredits;
	}

	public void setDeferredPaymentsCredits(Double deferredPaymentsCredits) {
		this.deferredPaymentsCredits = deferredPaymentsCredits;
	}

	public Double getDeferredTaxLiability() {
		return this.deferredTaxLiability;
	}

	public void setDeferredTaxLiability(Double deferredTaxLiability) {
		this.deferredTaxLiability = deferredTaxLiability;
	}

	public Double getDepositsOrInstalmentsOfTermLoans() {
		return this.depositsOrInstalmentsOfTermLoans;
	}

	public void setDepositsOrInstalmentsOfTermLoans(Double depositsOrInstalmentsOfTermLoans) {
		this.depositsOrInstalmentsOfTermLoans = depositsOrInstalmentsOfTermLoans;
	}

	public Double getDividendPayable() {
		return this.dividendPayable;
	}

	public void setDividendPayable(Double dividendPayable) {
		this.dividendPayable = dividendPayable;
	}

	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}

	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
	}

	public Double getFromApplicationBank() {
		return this.fromApplicationBank;
	}

	public void setFromApplicationBank(Double fromApplicationBank) {
		this.fromApplicationBank = fromApplicationBank;
	}

	public Double getFromOtherBanks() {
		return this.fromOtherBanks;
	}

	public void setFromOtherBanks(Double fromOtherBanks) {
		this.fromOtherBanks = fromOtherBanks;
	}

	public Double getGeneralReserve() {
		return this.generalReserve;
	}

	public void setGeneralReserve(Double generalReserve) {
		this.generalReserve = generalReserve;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getNetWorth() {
		return this.netWorth;
	}

	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}

	public Double getOrdinarySharesCapital() {
		return this.ordinarySharesCapital;
	}

	public void setOrdinarySharesCapital(Double ordinarySharesCapital) {
		this.ordinarySharesCapital = ordinarySharesCapital;
	}

	public Double getOtherCurrentLiability() {
		return this.otherCurrentLiability;
	}

	public void setOtherCurrentLiability(Double otherCurrentLiability) {
		this.otherCurrentLiability = otherCurrentLiability;
	}

	public Double getOtherReservse() {
		return this.otherReservse;
	}

	public void setOtherReservse(Double otherReservse) {
		this.otherReservse = otherReservse;
	}

	public Double getOtherStatutoryLiability() {
		return this.otherStatutoryLiability;
	}

	public void setOtherStatutoryLiability(Double otherStatutoryLiability) {
		this.otherStatutoryLiability = otherStatutoryLiability;
	}

	public Double getOtherTermLiabilies() {
		return this.otherTermLiabilies;
	}

	public void setOtherTermLiabilies(Double otherTermLiabilies) {
		this.otherTermLiabilies = otherTermLiabilies;
	}

	public Double getOthers() {
		return this.others;
	}

	public void setOthers(Double others) {
		this.others = others;
	}

	public Double getPreferencesShares() {
		return this.preferencesShares;
	}

	public void setPreferencesShares(Double preferencesShares) {
		this.preferencesShares = preferencesShares;
	}

	public Double getProvisionalForTaxation() {
		return this.provisionalForTaxation;
	}

	public void setProvisionalForTaxation(Double provisionalForTaxation) {
		this.provisionalForTaxation = provisionalForTaxation;
	}

	public Double getRevaluationReservse() {
		return this.revaluationReservse;
	}

	public void setRevaluationReservse(Double revaluationReservse) {
		this.revaluationReservse = revaluationReservse;
	}

	public Double getShortTermBorrowingFromOthers() {
		return this.shortTermBorrowingFromOthers;
	}

	public void setShortTermBorrowingFromOthers(Double shortTermBorrowingFromOthers) {
		this.shortTermBorrowingFromOthers = shortTermBorrowingFromOthers;
	}

	public Double getSubTotalA() {
		return this.subTotalA;
	}

	public void setSubTotalA(Double subTotalA) {
		this.subTotalA = subTotalA;
	}

	public Double getSubTotalB() {
		return this.subTotalB;
	}

	public void setSubTotalB(Double subTotalB) {
		this.subTotalB = subTotalB;
	}

	public Double getSundryCreditors() {
		return this.sundryCreditors;
	}

	public void setSundryCreditors(Double sundryCreditors) {
		this.sundryCreditors = sundryCreditors;
	}

	public Double getSurplusOrDeficit() {
		return this.surplusOrDeficit;
	}

	public void setSurplusOrDeficit(Double surplusOrDeficit) {
		this.surplusOrDeficit = surplusOrDeficit;
	}

	public Double getTermDeposits() {
		return this.termDeposits;
	}

	public void setTermDeposits(Double termDeposits) {
		this.termDeposits = termDeposits;
	}

	public Double getTermLoans() {
		return this.termLoans;
	}

	public void setTermLoans(Double termLoans) {
		this.termLoans = termLoans;
	}

	public Double getTotalCurrentLiabilities() {
		return this.totalCurrentLiabilities;
	}

	public void setTotalCurrentLiabilities(Double totalCurrentLiabilities) {
		this.totalCurrentLiabilities = totalCurrentLiabilities;
	}

	public Double getTotalLiability() {
		return this.totalLiability;
	}

	public void setTotalLiability(Double totalLiability) {
		this.totalLiability = totalLiability;
	}

	public Double getTotalOutsideLiabilities() {
		return this.totalOutsideLiabilities;
	}

	public void setTotalOutsideLiabilities(Double totalOutsideLiabilities) {
		this.totalOutsideLiabilities = totalOutsideLiabilities;
	}

	public Double getTotalTermLiabilities() {
		return this.totalTermLiabilities;
	}

	public void setTotalTermLiabilities(Double totalTermLiabilities) {
		this.totalTermLiabilities = totalTermLiabilities;
	}

	public Double getWhichBpAndBd() {
		return this.whichBpAndBd;
	}

	public void setWhichBpAndBd(Double whichBpAndBd) {
		this.whichBpAndBd = whichBpAndBd;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "LiabilitiesDetailsRequest [id=" + id + ", advancePaymentsFromCustomers=" + advancePaymentsFromCustomers
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", debentures=" + debentures
				+ ", deferredPaymentsCredits=" + deferredPaymentsCredits + ", deferredTaxLiability="
				+ deferredTaxLiability + ", depositsOrInstalmentsOfTermLoans=" + depositsOrInstalmentsOfTermLoans
				+ ", dividendPayable=" + dividendPayable + ", financialYearlyStatement=" + financialYearlyStatement
				+ ", fromApplicationBank=" + fromApplicationBank + ", fromOtherBanks=" + fromOtherBanks
				+ ", generalReserve=" + generalReserve + ", isActive=" + isActive + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", netWorth=" + netWorth + ", ordinarySharesCapital="
				+ ordinarySharesCapital + ", otherCurrentLiability=" + otherCurrentLiability + ", otherReservse="
				+ otherReservse + ", otherStatutoryLiability=" + otherStatutoryLiability + ", otherTermLiabilies="
				+ otherTermLiabilies + ", others=" + others + ", preferencesShares=" + preferencesShares
				+ ", provisionalForTaxation=" + provisionalForTaxation + ", revaluationReservse=" + revaluationReservse
				+ ", shortTermBorrowingFromOthers=" + shortTermBorrowingFromOthers + ", subTotalA=" + subTotalA
				+ ", subTotalB=" + subTotalB + ", sundryCreditors=" + sundryCreditors + ", surplusOrDeficit="
				+ surplusOrDeficit + ", termDeposits=" + termDeposits + ", termLoans=" + termLoans
				+ ", totalCurrentLiabilities=" + totalCurrentLiabilities + ", totalLiability=" + totalLiability
				+ ", totalOutsideLiabilities=" + totalOutsideLiabilities + ", totalTermLiabilities="
				+ totalTermLiabilities + ", whichBpAndBd=" + whichBpAndBd + ", termLiabilitiesSecured="
				+ termLiabilitiesSecured + ", shareWarrentsOutstanding=" + shareWarrentsOutstanding
				+ ", minorityInterest=" + minorityInterest + ", otherNclUnsecuredLoansFromOther="
				+ otherNclUnsecuredLoansFromOther + ", otherNclLongTermProvisions=" + otherNclLongTermProvisions
				+ ", otherNclOthers=" + otherNclOthers + ", otherNclUnsecuredLoansFromPromoters="
				+ otherNclUnsecuredLoansFromPromoters + ", otherNcl=" + otherNcl + ", termLiabilitiesUnsecured="
				+ termLiabilitiesUnsecured + ", year=" + year + ", storageDetailsId=" + storageDetailsId
				+ ", applicationId=" + applicationId + "]";
	}

	

}
