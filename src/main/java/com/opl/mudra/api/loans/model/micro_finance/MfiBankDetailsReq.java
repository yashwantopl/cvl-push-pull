package com.opl.mudra.api.loans.model.micro_finance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MfiBankDetailsReq implements Serializable {

	private Long id;
	private Long applicationId;

	private Long bankId;
	private Long userId;
	private String branchName;
	private String bankName;
	private String accountHolderName;
	private String accountNo;
	private String ifscCode;
	private Integer accountType;

	private byte[] passbookImg;
	private Boolean isBankDetailsFilled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public byte[] getPassbookImg() {
		return passbookImg;
	}

	public void setPassbookImg(byte[] passbookImg) {
		this.passbookImg = passbookImg;
	}

	public Boolean getIsBankDetailsFilled() {
		return isBankDetailsFilled;
	}

	public void setIsBankDetailsFilled(Boolean isBankDetailsFilled) {
		this.isBankDetailsFilled = isBankDetailsFilled;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
