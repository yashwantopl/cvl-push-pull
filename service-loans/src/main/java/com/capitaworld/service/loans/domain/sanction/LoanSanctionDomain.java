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
	
	@Column(name="transection_no")
	private String transectionNo;
	
	@Column(name="sanction_date")
	private Date sanctionDate;
	
	private String branch;
	
	@Column(name="application_id")
	private Long applicationId;
	
	private Double tenure;
	
	@Column(name="sanction_authority")
	private String sanctionAuthority;


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

	public Date getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
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
		return "LoanSanctionDomain [id=" + id + ", accountNo=" + accountNo + ", sanctionAmount=" + sanctionAmount
				+ ", roi=" + roi + ", sanctionDate=" + sanctionDate + ", branch=" + branch + ", applicationId="
				+ applicationId + ", tenure=" + tenure + ", sanctionAuthority=" + sanctionAuthority + "]";
	}

	
}
