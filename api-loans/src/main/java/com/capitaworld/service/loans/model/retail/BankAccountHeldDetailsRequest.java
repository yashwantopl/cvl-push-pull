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

	private Integer accountType;

	private String bankName;
	private String branchName;

	private Boolean isActive = true;
	
	private Long applicantId;
	
	private String accountTypeString;

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountTypeString() {
		return accountTypeString;
	}

	public void setAccountTypeString(String accountTypeString) {
		this.accountTypeString = accountTypeString;
	}
	
}
