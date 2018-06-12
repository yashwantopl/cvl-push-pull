package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.common.AuditActivityRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanSanctionRequest extends AuditActivityRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNo;
	private Double sanctionAmount;
	private String transectionNo;
	private Double roi;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date sanctionDate;
	private Long branch;
	private Long applicationId;
	private Double tenure;
	private String sanctionAuthority;

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

	public String getTransectionNo() {
		return transectionNo;
	}

	public void setTransectionNo(String transectionNo) {
		this.transectionNo = transectionNo;
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

	@Override
	public String toString() {
		return "LoanSanctionRequest [accountNo=" + accountNo + ", sanctionAmount=" + sanctionAmount + ", transectionNo="
				+ transectionNo + ", roi=" + roi + ", sanctionDate=" + sanctionDate + ", branch=" + branch
				+ ", applicationId=" + applicationId + ", tenure=" + tenure + ", sanctionAuthority=" + sanctionAuthority
				+ "]";
	}


	
}
