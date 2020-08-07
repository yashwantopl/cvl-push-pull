package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RecentActivities {

	@JsonProperty("AccountsDeliquent")
	private String accountsDeliquent;
	
	@JsonProperty("AccountsOpened")
	private String accountsOpened;
	
	@JsonProperty("TotalInquiries")
	private String totalInquiries;
	
	@JsonProperty("AccountsUpdated")
	private String accountsUpdated;

	public String getAccountsDeliquent() {
		return accountsDeliquent;
	}

	public void setAccountsDeliquent(String accountsDeliquent) {
		this.accountsDeliquent = accountsDeliquent;
	}

	public String getAccountsOpened() {
		return accountsOpened;
	}

	public void setAccountsOpened(String accountsOpened) {
		this.accountsOpened = accountsOpened;
	}

	public String getTotalInquiries() {
		return totalInquiries;
	}

	public void setTotalInquiries(String totalInquiries) {
		this.totalInquiries = totalInquiries;
	}

	public String getAccountsUpdated() {
		return accountsUpdated;
	}

	public void setAccountsUpdated(String accountsUpdated) {
		this.accountsUpdated = accountsUpdated;
	}
	
	
}
