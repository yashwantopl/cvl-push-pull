package com.capitaworld.service.loans.domain.fundseeker.retail;

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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_existing_loan_detail database table.
 * 
 */
/**
 * @author Sanket
 *
 */
@Entity
@Table(name="fs_retail_existing_loan_detail")
public class ExistingLoanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="applicant_id")
	private LoanApplicationMaster applicantId;

	@Column(name="bank_or_financer_name")
	private String bankOrFinancerName;

	@ManyToOne
	@JoinColumn(name="co_applicant_detail_id")
	private CoApplicantDetail coApplicantDetailId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="emi_amount")
	private Double emiAmount;

	@ManyToOne
	@JoinColumn(name="guarantor_detail_id")
	private GuarantorDetails guarantorDetailId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="loan_tenure")
	private Integer loanTenure;

	@Column(name="loan_type")
	private String loanType;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="no_of_emi_paid")
	private Integer noOfEmiPaid;

	@Column(name="outstanding_balance")
	private Double outstandingBalance;

	public ExistingLoanDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(LoanApplicationMaster applicantId) {
		this.applicantId = applicantId;
	}

	public String getBankOrFinancerName() {
		return this.bankOrFinancerName;
	}

	public void setBankOrFinancerName(String bankOrFinancerName) {
		this.bankOrFinancerName = bankOrFinancerName;
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

	public Double getEmiAmount() {
		return this.emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
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

	public Integer getLoanTenure() {
		return this.loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
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

	public Integer getNoOfEmiPaid() {
		return this.noOfEmiPaid;
	}

	public void setNoOfEmiPaid(Integer noOfEmiPaid) {
		this.noOfEmiPaid = noOfEmiPaid;
	}

	public Double getOutstandingBalance() {
		return this.outstandingBalance;
	}

	public void setOutstandingBalance(Double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

}