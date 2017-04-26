package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_credit_rating_organization_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_credit_rating_organization_details")
public class CreditRatingOrganizationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Column(name="application_id")
	private Long applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="credit_rating_fund_id")
	private Integer creditRatingFundId;

	@Column(name="credit_rating_option_id")
	private Integer creditRatingOptionId;

	@Column(name="credit_rating_term_id")
	private Integer creditRatingTermId;

	@Lob
	@Column(name="facility_name")
	private String facilityName;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_Date;

	@Column(name="rating_agency_id")
	private Integer ratingAgencyId;

	public CreditRatingOrganizationDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
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

	public Integer getCreditRatingFundId() {
		return this.creditRatingFundId;
	}

	public void setCreditRatingFundId(Integer creditRatingFundId) {
		this.creditRatingFundId = creditRatingFundId;
	}

	public Integer getCreditRatingOptionId() {
		return this.creditRatingOptionId;
	}

	public void setCreditRatingOptionId(Integer creditRatingOptionId) {
		this.creditRatingOptionId = creditRatingOptionId;
	}

	public Integer getCreditRatingTermId() {
		return this.creditRatingTermId;
	}

	public void setCreditRatingTermId(Integer creditRatingTermId) {
		this.creditRatingTermId = creditRatingTermId;
	}

	public String getFacilityName() {
		return this.facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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

	public Date getModified_Date() {
		return this.modified_Date;
	}

	public void setModified_Date(Date modified_Date) {
		this.modified_Date = modified_Date;
	}

	public Integer getRatingAgencyId() {
		return this.ratingAgencyId;
	}

	public void setRatingAgencyId(Integer ratingAgencyId) {
		this.ratingAgencyId = ratingAgencyId;
	}

}