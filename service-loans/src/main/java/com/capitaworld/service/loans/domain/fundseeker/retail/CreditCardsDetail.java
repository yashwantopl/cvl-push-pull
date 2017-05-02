package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import java.util.Date;


/**
 * The persistent class for the fs_retail_credit_cards_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_credit_cards_details")
public class CreditCardsDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="applicantion_id")
	private LoanApplicationMaster applicantionId;

	@Column(name="card_number")
	private String cardNumber;

	@ManyToOne
	@JoinColumn(name="co_applicant_detail_id")
	private CoApplicantDetail coApplicantDetailId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="credit_card_types_id")
	private int creditCardTypesId;

	@ManyToOne
	@JoinColumn(name="guarantor_detail_id")
	private GuarantorDetails guarantorDetailId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="issuer_name")
	private String issuerName;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="outstanding_balance")
	private Double outstandingBalance;

	public CreditCardsDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicantionId() {
		return this.applicantionId;
	}

	public void setApplicantionId(LoanApplicationMaster applicantionId) {
		this.applicantionId = applicantionId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public int getCreditCardTypesId() {
		return this.creditCardTypesId;
	}

	public void setCreditCardTypesId(int creditCardTypesId) {
		this.creditCardTypesId = creditCardTypesId;
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

	public String getIssuerName() {
		return this.issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
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

	public Double getOutstandingBalance() {
		return this.outstandingBalance;
	}

	public void setOutstandingBalance(Double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

}