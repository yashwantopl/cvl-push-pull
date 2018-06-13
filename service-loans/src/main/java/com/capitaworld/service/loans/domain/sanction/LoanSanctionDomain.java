package com.capitaworld.service.loans.domain.sanction;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

@Entity
@Table(name="sanction_detail")
public class LoanSanctionDomain extends AuditActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY )
	private Long id ;
	
	@Column(name="account_no")
	private String accountNo;
	                 
	@Column(name="sanction_amount")
	private Double sanctionAmount;
	
	private Double roi;
	
	@Column(name="transaction_no")
	private String transactionNo;
	
	@Column(name="sanction_date")
	private Date sanctionDate;
	
	private Long branch;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="reference_no")
	private Long referenceNo; 
	
	private Double tenure;
	
	@Column(name="sanction_authority")
	private String sanctionAuthority;
	
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public String getTransectionNo() {
		return transactionNo;
	}

	public void setTransectionNo(String transectionNo) {
		this.transactionNo = transectionNo;
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

	public String getRemarks() {
		return remark;
	}

	public void setRemarks(String remarks) {
		this.remark = remarks;
	}

	@Override
	public String toString() {
		return "LoanSanctionDomain [id=" + id + ", accountNo=" + accountNo + ", sanctionAmount=" + sanctionAmount
				+ ", roi=" + roi + ", transectionNo=" + transactionNo + ", sanctionDate=" + sanctionDate + ", branch="
				+ branch + ", applicationId=" + applicationId + ", referenceNo=" + referenceNo + ", tenure=" + tenure
				+ ", sanctionAuthority=" + sanctionAuthority + ", remarks=" + remark + "]";
	}



	
}
