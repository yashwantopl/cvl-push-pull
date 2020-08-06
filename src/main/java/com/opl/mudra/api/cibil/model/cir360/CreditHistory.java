package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreditHistory {

	private String account_number;
	private String current_balance_limit_utilized_marktomarket;
	private String assetclassification_dayspastdue;
	private String amount_overdue_limit_overdue;
	private String account_status;
	private String wilful_default_status;
	private String suit_filed_status;
	private String dt_reported_lst;
	private String yyyymm;

	public String getYyyymm() {
		return yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
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

	public String getCurrent_balance_limit_utilized_marktomarket() {
		return current_balance_limit_utilized_marktomarket;
	}

	public void setCurrent_balance_limit_utilized_marktomarket(String current_balance_limit_utilized_marktomarket) {
		this.current_balance_limit_utilized_marktomarket = current_balance_limit_utilized_marktomarket;
	}

	public String getAssetclassification_dayspastdue() {
		return assetclassification_dayspastdue;
	}

	public void setAssetclassification_dayspastdue(String assetclassification_dayspastdue) {
		this.assetclassification_dayspastdue = assetclassification_dayspastdue;
	}

	public String getAmount_overdue_limit_overdue() {
		return amount_overdue_limit_overdue;
	}

	public void setAmount_overdue_limit_overdue(String amount_overdue_limit_overdue) {
		this.amount_overdue_limit_overdue = amount_overdue_limit_overdue;
	}

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public String getWilful_default_status() {
		return wilful_default_status;
	}

	public void setWilful_default_status(String wilful_default_status) {
		this.wilful_default_status = wilful_default_status;
	}

	public String getSuit_filed_status() {
		return suit_filed_status;
	}

	public void setSuit_filed_status(String suit_filed_status) {
		this.suit_filed_status = suit_filed_status;
	}

}
