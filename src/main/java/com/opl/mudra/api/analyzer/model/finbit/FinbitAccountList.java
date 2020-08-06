package com.opl.mudra.api.analyzer.model.finbit;

public class FinbitAccountList {

    private String accountDetails;
    private String accountUID;
    private String parseStatus;
    private String parseMessage;
    private String invalidBank;
	public String getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}
	public String getAccountUID() {
		return accountUID;
	}
	public void setAccountUID(String accountUID) {
		this.accountUID = accountUID;
	}
	public String getParseStatus() {
		return parseStatus;
	}
	public void setParseStatus(String parseStatus) {
		this.parseStatus = parseStatus;
	}
	public String getParseMessage() {
		return parseMessage;
	}
	public void setParseMessage(String parseMessage) {
		this.parseMessage = parseMessage;
	}

	public String getInvalidBank() {
		return invalidBank;
	}

	public void setInvalidBank(String invalidBank) {
		this.invalidBank = invalidBank;
	}

	@Override
	public String toString() {
		return "FinbitAccountList{" +
				"accountDetails='" + accountDetails + '\'' +
				", accountUID='" + accountUID + '\'' +
				", parseStatus='" + parseStatus + '\'' +
				", parseMessage='" + parseMessage + '\'' +
				", invalidBank='" + invalidBank + '\'' +
				'}';
	}
}
