package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import java.util.Date;


/**
 * The persistent class for the fs_retail_other_income_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_other_income_details")
public class OtherIncomeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@ManyToOne
	@JoinColumn(name="co_applicant_detail_id")
	private CoApplicantDetail coApplicantDetailId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="gross_income")
	private Double grossIncome;

	@ManyToOne
	@JoinColumn(name="guarantor_detail_id")
	private GuarantorDetails guarantorDetailId;

	@Column(name="income_details_id")
	private Integer incomeDetailsId;

	@Lob
	@Column(name="income_head")
	private String incomeHead;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_income")
	private Double netIncome;

	public OtherIncomeDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
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

	public Double getGrossIncome() {
		return this.grossIncome;
	}

	public void setGrossIncome(Double grossIncome) {
		this.grossIncome = grossIncome;
	}

	public GuarantorDetails getGuarantorDetailId() {
		return this.guarantorDetailId;
	}

	public void setGuarantorDetailId(GuarantorDetails guarantorDetailId) {
		this.guarantorDetailId = guarantorDetailId;
	}

	public Integer getIncomeDetailsId() {
		return this.incomeDetailsId;
	}

	public void setIncomeDetailsId(Integer incomeDetailsId) {
		this.incomeDetailsId = incomeDetailsId;
	}

	public String getIncomeHead() {
		return this.incomeHead;
	}

	public void setIncomeHead(String incomeHead) {
		this.incomeHead = incomeHead;
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

	public Double getNetIncome() {
		return this.netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

}