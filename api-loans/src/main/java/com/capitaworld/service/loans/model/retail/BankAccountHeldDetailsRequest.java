package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class BankAccountHeldDetailsRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String accountHeldFor;

	private String accountNumber;

	private String accountType;

	private String bankNameAndBranch;

	private Boolean isActive = true;
	
	private Long applicantId;
	
	

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHeldFor() {
		return accountHeldFor;
	}

	public void setAccountHeldFor(String accountHeldFor) {
		this.accountHeldFor = accountHeldFor;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankNameAndBranch() {
		return bankNameAndBranch;
	}

	public void setBankNameAndBranch(String bankNameAndBranch) {
		this.bankNameAndBranch = bankNameAndBranch;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



}
