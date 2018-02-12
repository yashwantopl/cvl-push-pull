package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

@Entity
@Table(name="fs_corporate_cma_liabilities_details")
public class LiabilitiesDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="advance_payments_from_customers")
	private Double advancePaymentsFromCustomers;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private Double debentures;

	@Column(name="deferred_payments_credits")
	private Double deferredPaymentsCredits;

	@Column(name="deferred_tax_liability")
	private Double deferredTaxLiability;

	@Column(name="deposits_or_instalments_of_term_loans")
	private Double depositsOrInstalmentsOfTermLoans;

	@Column(name="dividend_payable")
	private Double dividendPayable;

	@Column(name="from_application_bank")
	private Double fromApplicationBank;

	@Column(name="from_other_banks")
	private Double fromOtherBanks;

	@Column(name="general_reserve")
	private Double generalReserve;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_worth")
	private Double netWorth;

	@Column(name="ordinary_shares_capital")
	private Double ordinarySharesCapital;

	@Column(name="other_current_liability")
	private Double otherCurrentLiability;

	@Column(name="other_reservse")
	private Double otherReservse;

	@Column(name="other_statutory_liability")
	private Double otherStatutoryLiability;

	@Column(name="other_term_liabilies")
	private Double otherTermLiabilies;

	private Double others;

	@Column(name="preferences_shares")
	private Double preferencesShares;

	@Column(name="provisional_for_taxation")
	private Double provisionalForTaxation;

	@Column(name="revaluation_reservse")
	private Double revaluationReservse;

	@Column(name="short_term_borrowing_from_others")
	private Double shortTermBorrowingFromOthers;

	@Column(name="sub_total_a")
	private Double subTotalA;

	@Column(name="sub_total_b")
	private Double subTotalB;

	@Column(name="sundry_creditors")
	private Double sundryCreditors;

	@Column(name="surplus_or_deficit")
	private Double surplusOrDeficit;

	@Column(name="term_deposits")
	private Double termDeposits;

	@Column(name="term_loans")
	private Double termLoans;

	@Column(name="total_current_liabilities")
	private Double totalCurrentLiabilities;

	@Column(name="total_liability")
	private Double totalLiability;

	@Column(name="total_outside_liabilities")
	private Double totalOutsideLiabilities;

	@Column(name="total_term_liabilities")
	private Double totalTermLiabilities;

	@Column(name="which_bp_and_bd")
	private Double whichBpAndBd;
	
	@Column(name="term_liabilities_secured")
	private Double termLiabilitiesSecured;
	
	
	@Column(name="share_warrents_outstanding")
	private Double shareWarrentsOutstanding;
	
	@Column(name="minority_interest")
	private Double minorityInterest;
	
	@Column(name="other_ncl_unsecured_loans_from_other")
	private Double otherNclUnsecuredLoansFromOther;
	
	@Column(name="other_ncl_long_term_provisions")
	private Double otherNclLongTermProvisions;
	
	@Column(name="other_ncl_others")
	private Double otherNclOthers;
	
	@Column(name="other_ncl_unsecured_loans_from_promoters")
	private Double otherNclUnsecuredLoansFromPromoters;
	
	@Column(name="other_ncl")
	private Double otherNcl;
	
	
	public LiabilitiesDetails() {
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

	@Column(name="term_liabilities_unsecured")
	private Double termLiabilitiesUnsecured;

	private String year;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;

	//bi-directional many-to-one association to FsLoanApplicationMaster
	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster fsLoanApplicationMaster;
	
	
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

	public LoanApplicationMaster getFsLoanApplicationMaster() {
		return this.fsLoanApplicationMaster;
	}

	public void setFsLoanApplicationMaster(LoanApplicationMaster fsLoanApplicationMaster) {
		this.fsLoanApplicationMaster = fsLoanApplicationMaster;
	}


}
