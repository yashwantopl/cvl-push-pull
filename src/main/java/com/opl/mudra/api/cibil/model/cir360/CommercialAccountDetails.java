package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialAccountDetails {

	@JsonProperty("History48Months")
	private List<CreditHistory> creditHistory;

	@JsonProperty("GuarantorDetails")
	private List<AccountRelationDetails> guarantorDetails;

	@JsonProperty("DishonouredChequeDetails")
	private List<DishonouredChequeDetails> dishonouredChequeDetails;

	@JsonProperty("SecuritySgmnt")
	private List<SecuritySgmnt> securitySgmnt;

	private String institution;
	private String account_number;
	private String sanctiondate_loanactivation;
	private String sanctioned_amount_notional_amountofcontract;
	private String currency_code;
	private String credit_type;
	private String tenure_weighted_avg_maturityperiod;
	private String repayment_frequency;
	private String drawing_power;
	private String current_balance_limit_utilized_marktomarket;
	private String notional_amount_outstanding_restructured_contracts;
	private String loan_expiry_maturity_date;
	private String loan_renewal_date;
	private String assetclassification_dayspastdue;
	private String asset_classification_dt;
	private String amount_overdue_limit_overdue;
	private String overduebucket01_1_30;
	private String overduebucket02_31_60;
	private String overduebucket03_61_90;
	private String overduebucket04_91_180;
	private String overduebucket05_above_180;
	private String high_credit;
	private String installment_amount;
	private String last_repaid_amount;
	private String account_status;

	@JsonProperty("account_status_code")
	private String account_statusO;

	private String account_status_dt;
	private String written_off_amount;
	private String settled_amount;
	private String major_reasons_for_restructuring;
	private String amount_of_contracts_classified_npa;
	private String asset_based_security_coverage;
	private String guarantee_coverage;
	private String bank_remark_code;
	private String wilful_default_status;
	private String date_classified_as_wilful_default;
	private String suit_filed_status;
	private String suit_reference_number;
	private String suit_amount_in_rupees;
	private String date_of_suit;
	private String dispute_id_no;
	private String transaction_type_code;
	private String other_bk;
	private String ufce_amount;
	private String ufce_date;

	private String dt_reported_fst;
	private String dt_reported_lst;

	private String Type_of_Security;
	private String Value_of_Security;
	private String Security_Classification;

	public String getAccount_statusO() {
		return account_statusO;
	}

	public void setAccount_statusO(String account_statusO) {
		this.account_statusO = account_statusO;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getType_of_Security() {
		return Type_of_Security;
	}

	public void setType_of_Security(String type_of_Security) {
		Type_of_Security = type_of_Security;
	}

	public String getValue_of_Security() {
		return Value_of_Security;
	}

	public void setValue_of_Security(String value_of_Security) {
		Value_of_Security = value_of_Security;
	}

	public String getSecurity_Classification() {
		return Security_Classification;
	}

	public void setSecurity_Classification(String security_Classification) {
		Security_Classification = security_Classification;
	}

	public String getDt_reported_fst() {
		return dt_reported_fst;
	}

	public void setDt_reported_fst(String dt_reported_fst) {
		this.dt_reported_fst = dt_reported_fst;
	}

	public String getDt_reported_lst() {
		return dt_reported_lst;
	}

	public void setDt_reported_lst(String dt_reported_lst) {
		this.dt_reported_lst = dt_reported_lst;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getSanctiondate_loanactivation() {
		return sanctiondate_loanactivation;
	}

	public void setSanctiondate_loanactivation(String sanctiondate_loanactivation) {
		this.sanctiondate_loanactivation = sanctiondate_loanactivation;
	}

	public String getSanctioned_amount_notional_amountofcontract() {
		return sanctioned_amount_notional_amountofcontract;
	}

	public void setSanctioned_amount_notional_amountofcontract(String sanctioned_amount_notional_amountofcontract) {
		this.sanctioned_amount_notional_amountofcontract = sanctioned_amount_notional_amountofcontract;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getCredit_type() {
		return credit_type;
	}

	public void setCredit_type(String credit_type) {
		this.credit_type = credit_type;
	}

	public String getTenure_weighted_avg_maturityperiod() {
		return tenure_weighted_avg_maturityperiod;
	}

	public void setTenure_weighted_avg_maturityperiod(String tenure_weighted_avg_maturityperiod) {
		this.tenure_weighted_avg_maturityperiod = tenure_weighted_avg_maturityperiod;
	}

	public String getRepayment_frequency() {
		return repayment_frequency;
	}

	public void setRepayment_frequency(String repayment_frequency) {
		this.repayment_frequency = repayment_frequency;
	}

	public String getDrawing_power() {
		return drawing_power;
	}

	public void setDrawing_power(String drawing_power) {
		this.drawing_power = drawing_power;
	}

	public String getCurrent_balance_limit_utilized_marktomarket() {
		return current_balance_limit_utilized_marktomarket;
	}

	public void setCurrent_balance_limit_utilized_marktomarket(String current_balance_limit_utilized_marktomarket) {
		this.current_balance_limit_utilized_marktomarket = current_balance_limit_utilized_marktomarket;
	}

	public String getNotional_amount_outstanding_restructured_contracts() {
		return notional_amount_outstanding_restructured_contracts;
	}

	public void setNotional_amount_outstanding_restructured_contracts(
			String notional_amount_outstanding_restructured_contracts) {
		this.notional_amount_outstanding_restructured_contracts = notional_amount_outstanding_restructured_contracts;
	}

	public String getLoan_expiry_maturity_date() {
		return loan_expiry_maturity_date;
	}

	public void setLoan_expiry_maturity_date(String loan_expiry_maturity_date) {
		this.loan_expiry_maturity_date = loan_expiry_maturity_date;
	}

	public String getLoan_renewal_date() {
		return loan_renewal_date;
	}

	public void setLoan_renewal_date(String loan_renewal_date) {
		this.loan_renewal_date = loan_renewal_date;
	}

	public String getAssetclassification_dayspastdue() {
		return assetclassification_dayspastdue;
	}

	public void setAssetclassification_dayspastdue(String assetclassification_dayspastdue) {
		this.assetclassification_dayspastdue = assetclassification_dayspastdue;
	}

	public String getAsset_classification_dt() {
		return asset_classification_dt;
	}

	public void setAsset_classification_dt(String asset_classification_dt) {
		this.asset_classification_dt = asset_classification_dt;
	}

	public String getAmount_overdue_limit_overdue() {
		return amount_overdue_limit_overdue;
	}

	public void setAmount_overdue_limit_overdue(String amount_overdue_limit_overdue) {
		this.amount_overdue_limit_overdue = amount_overdue_limit_overdue;
	}

	public String getOverduebucket01_1_30() {
		return overduebucket01_1_30;
	}

	public void setOverduebucket01_1_30(String overduebucket01_1_30) {
		this.overduebucket01_1_30 = overduebucket01_1_30;
	}

	public String getOverduebucket02_31_60() {
		return overduebucket02_31_60;
	}

	public void setOverduebucket02_31_60(String overduebucket02_31_60) {
		this.overduebucket02_31_60 = overduebucket02_31_60;
	}

	public String getOverduebucket03_61_90() {
		return overduebucket03_61_90;
	}

	public void setOverduebucket03_61_90(String overduebucket03_61_90) {
		this.overduebucket03_61_90 = overduebucket03_61_90;
	}

	public String getOverduebucket04_91_180() {
		return overduebucket04_91_180;
	}

	public void setOverduebucket04_91_180(String overduebucket04_91_180) {
		this.overduebucket04_91_180 = overduebucket04_91_180;
	}

	public String getOverduebucket05_above_180() {
		return overduebucket05_above_180;
	}

	public void setOverduebucket05_above_180(String overduebucket05_above_180) {
		this.overduebucket05_above_180 = overduebucket05_above_180;
	}

	public String getHigh_credit() {
		return high_credit;
	}

	public void setHigh_credit(String high_credit) {
		this.high_credit = high_credit;
	}

	public String getInstallment_amount() {
		return installment_amount;
	}

	public void setInstallment_amount(String installment_amount) {
		this.installment_amount = installment_amount;
	}

	public String getLast_repaid_amount() {
		return last_repaid_amount;
	}

	public void setLast_repaid_amount(String last_repaid_amount) {
		this.last_repaid_amount = last_repaid_amount;
	}

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public String getAccount_status_dt() {
		return account_status_dt;
	}

	public void setAccount_status_dt(String account_status_dt) {
		this.account_status_dt = account_status_dt;
	}

	public String getWritten_off_amount() {
		return written_off_amount;
	}

	public void setWritten_off_amount(String written_off_amount) {
		this.written_off_amount = written_off_amount;
	}

	public String getSettled_amount() {
		return settled_amount;
	}

	public void setSettled_amount(String settled_amount) {
		this.settled_amount = settled_amount;
	}

	public String getMajor_reasons_for_restructuring() {
		return major_reasons_for_restructuring;
	}

	public void setMajor_reasons_for_restructuring(String major_reasons_for_restructuring) {
		this.major_reasons_for_restructuring = major_reasons_for_restructuring;
	}

	public String getAmount_of_contracts_classified_npa() {
		return amount_of_contracts_classified_npa;
	}

	public void setAmount_of_contracts_classified_npa(String amount_of_contracts_classified_npa) {
		this.amount_of_contracts_classified_npa = amount_of_contracts_classified_npa;
	}

	public String getAsset_based_security_coverage() {
		return asset_based_security_coverage;
	}

	public void setAsset_based_security_coverage(String asset_based_security_coverage) {
		this.asset_based_security_coverage = asset_based_security_coverage;
	}

	public String getGuarantee_coverage() {
		return guarantee_coverage;
	}

	public void setGuarantee_coverage(String guarantee_coverage) {
		this.guarantee_coverage = guarantee_coverage;
	}

	public String getBank_remark_code() {
		return bank_remark_code;
	}

	public void setBank_remark_code(String bank_remark_code) {
		this.bank_remark_code = bank_remark_code;
	}

	public String getWilful_default_status() {
		return wilful_default_status;
	}

	public void setWilful_default_status(String wilful_default_status) {
		this.wilful_default_status = wilful_default_status;
	}

	public String getDate_classified_as_wilful_default() {
		return date_classified_as_wilful_default;
	}

	public void setDate_classified_as_wilful_default(String date_classified_as_wilful_default) {
		this.date_classified_as_wilful_default = date_classified_as_wilful_default;
	}

	public String getSuit_filed_status() {
		return suit_filed_status;
	}

	public void setSuit_filed_status(String suit_filed_status) {
		this.suit_filed_status = suit_filed_status;
	}

	public String getSuit_reference_number() {
		return suit_reference_number;
	}

	public void setSuit_reference_number(String suit_reference_number) {
		this.suit_reference_number = suit_reference_number;
	}

	public String getSuit_amount_in_rupees() {
		return suit_amount_in_rupees;
	}

	public void setSuit_amount_in_rupees(String suit_amount_in_rupees) {
		this.suit_amount_in_rupees = suit_amount_in_rupees;
	}

	public String getDate_of_suit() {
		return date_of_suit;
	}

	public void setDate_of_suit(String date_of_suit) {
		this.date_of_suit = date_of_suit;
	}

	public String getDispute_id_no() {
		return dispute_id_no;
	}

	public void setDispute_id_no(String dispute_id_no) {
		this.dispute_id_no = dispute_id_no;
	}

	public String getTransaction_type_code() {
		return transaction_type_code;
	}

	public void setTransaction_type_code(String transaction_type_code) {
		this.transaction_type_code = transaction_type_code;
	}

	public String getOther_bk() {
		return other_bk;
	}

	public void setOther_bk(String other_bk) {
		this.other_bk = other_bk;
	}

	public String getUfce_amount() {
		return ufce_amount;
	}

	public void setUfce_amount(String ufce_amount) {
		this.ufce_amount = ufce_amount;
	}

	public String getUfce_date() {
		return ufce_date;
	}

	public void setUfce_date(String ufce_date) {
		this.ufce_date = ufce_date;
	}

	public List<AccountRelationDetails> getGuarantorDetails() {
		return guarantorDetails;
	}

	public void setGuarantorDetails(List<AccountRelationDetails> guarantorDetails) {
		this.guarantorDetails = guarantorDetails;
	}

	public List<DishonouredChequeDetails> getDishonouredChequeDetails() {
		return dishonouredChequeDetails;
	}

	public void setDishonouredChequeDetails(List<DishonouredChequeDetails> dishonouredChequeDetails) {
		this.dishonouredChequeDetails = dishonouredChequeDetails;
	}

	public List<CreditHistory> getCreditHistory() {
		return creditHistory;
	}

	public void setCreditHistory(List<CreditHistory> creditHistory) {
		this.creditHistory = creditHistory;
	}

	public List<SecuritySgmnt> getSecuritySgmnt() {
		return securitySgmnt;
	}

	public void setSecuritySgmnt(List<SecuritySgmnt> securitySgmnt) {
		this.securitySgmnt = securitySgmnt;
	}

}
