package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_corporate_credit_rating_organization_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditRatingOrganizationDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private Long applicationId;

	private Integer creditRatingFundId;

	private Integer creditRatingOptionId;

	private Integer creditRatingTermId;

	private String facilityName;

	private Integer ratingAgencyId;
	
	private Boolean isActive = true;
	
	private String ratingValue;
	
	private String entityName;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date ratingDate;
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public CreditRatingOrganizationDetailRequest() {
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


	public Integer getRatingAgencyId() {
		return this.ratingAgencyId;
	}

	public void setRatingAgencyId(Integer ratingAgencyId) {
		this.ratingAgencyId = ratingAgencyId;
	}

	public String getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(String ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
	
	

}