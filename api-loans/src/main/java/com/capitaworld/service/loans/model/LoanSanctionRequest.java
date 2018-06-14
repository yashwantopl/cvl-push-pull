package com.capitaworld.service.loans.model;

import java.io.Serializable;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanSanctionRequest  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNo;
	private Double sanctionAmount;
	private String transactionNo;
	private Double roi;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date sanctionDate;
	private Long branch;
	private Long applicationId;
	private Long referenceNo;
	private Double tenure;
	private String sanctionAuthority;
	private String remark;
	private String userName;
	private String password;
	private String actionBy;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(Double sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Date getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public String getSanctionAuthority() {
		return sanctionAuthority;
	}

	public void setSanctionAuthority(String sanctionAuthority) {
		this.sanctionAuthority = sanctionAuthority;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remarks) {
		this.remark = remarks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	@Override
	public String toString() {
		return "LoanSanctionRequest [accountNo=" + accountNo + ", sanctionAmount=" + sanctionAmount + ", transactionNo="
				+ transactionNo + ", roi=" + roi + ", sanctionDate=" + sanctionDate + ", branch=" + branch
				+ ", applicationId=" + applicationId + ", referenceNo=" + referenceNo + ", tenure=" + tenure
				+ ", sanctionAuthority=" + sanctionAuthority + ", remark=" + remark + ", userName=" + userName
				+ ", password=" + password + ", actionBy=" + actionBy + "]";
	}

}
