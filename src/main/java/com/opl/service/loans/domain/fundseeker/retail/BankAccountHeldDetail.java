package com.opl.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_bank_account_held_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_bank_account_held_details")
public class BankAccountHeldDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="account_held_for")
	private String accountHeldFor;

	@Column(name="account_number")
	private String accountNumber;

	@Column(name="account_type")
	private Integer accountType;

	@ManyToOne
	@JoinColumn(name="applicant_id")
	private LoanApplicationMaster applicantId;
	
	@ManyToOne
    @JoinColumn(name = "proposal_mapping_id")
    private ApplicationProposalMapping applicationProposalMapping;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="branch_name")
	private String branchName;

	@ManyToOne
	@JoinColumn(name="co_applicant_detail_id")
	private CoApplicantDetail coApplicantDetailId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name="guarantor_detail_id")
	private GuarantorDetails guarantorDetailId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	public BankAccountHeldDetail() {
		// Do nothing because of X and Y.
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHeldFor() {
		return this.accountHeldFor;
	}

	public void setAccountHeldFor(String accountHeldFor) {
		this.accountHeldFor = accountHeldFor;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public LoanApplicationMaster getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(LoanApplicationMaster applicantId) {
		this.applicantId = applicantId;
	}



	public CoApplicantDetail getCoApplicantDetailId() {
		return this.coApplicantDetailId;
	}

	public void setCoApplicantDetailId(CoApplicantDetail coApplicantDetailId) {
		this.coApplicantDetailId = coApplicantDetailId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public GuarantorDetails getGuarantorDetailId() {
		return this.guarantorDetailId;
	}

	public void setGuarantorDetailId(GuarantorDetails guarantorDetailId) {
		this.guarantorDetailId = guarantorDetailId;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}
	
	
}