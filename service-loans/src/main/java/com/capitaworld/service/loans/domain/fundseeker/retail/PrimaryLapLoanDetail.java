package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_lap_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_lap_loan_details")
public class PrimaryLapLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="address_city")
	private Integer addressCity;

	@Column(name="address_country")
	private Integer addressCountry;

	@Column(name="address_landmark")
	private String addressLandmark;

	@Column(name="address_premise")
	private String addressPremise;

	@Column(name="address_state")
	private Integer addressState;

	@Column(name="address_street")
	private String addressStreet;

	@Column(name="built_up_area")
	private Double builtUpArea;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_active")
	private Boolean isActive=true;

	@Column(name="land_area")
	private Double landArea;

	@Column(name="loan_purpose")
	private Integer loanPurpose;

	@Column(name="loan_purpose_other")
	private String loanPurposeOther;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="occupation_status")
	private Integer occupationStatus;

	@Column(name="occupation_status_other")
	private String occupationStatusOther;

	@Column(name="owner_name")
	private String ownerName;

	private Integer pincode;

	@Column(name="property_age_in_month")
	private Integer propertyAgeInMonth;

	@Column(name="property_age_in_year")
	private Integer propertyAgeInYear;

	@Column(name="property_type")
	private Integer propertyType;

	@Column(name="property_type_other")
	private String propertyTypeOther;

	@Column(name="property_value")
	private Double propertyValue;

	public PrimaryLapLoanDetail() {
	}

	
	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}


	public Integer getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(Integer addressCity) {
		this.addressCity = addressCity;
	}

	public Integer getAddressCountry() {
		return this.addressCountry;
	}

	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressLandmark() {
		return this.addressLandmark;
	}

	public void setAddressLandmark(String addressLandmark) {
		this.addressLandmark = addressLandmark;
	}

	public String getAddressPremise() {
		return this.addressPremise;
	}

	public void setAddressPremise(String addressPremise) {
		this.addressPremise = addressPremise;
	}

	public Integer getAddressState() {
		return this.addressState;
	}

	public void setAddressState(Integer addressState) {
		this.addressState = addressState;
	}

	public String getAddressStreet() {
		return this.addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public Double getBuiltUpArea() {
		return this.builtUpArea;
	}

	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLandArea() {
		return this.landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	public Integer getLoanPurpose() {
		return this.loanPurpose;
	}

	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeOther() {
		return this.loanPurposeOther;
	}

	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
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

	public Integer getOccupationStatus() {
		return this.occupationStatus;
	}

	public void setOccupationStatus(Integer occupationStatus) {
		this.occupationStatus = occupationStatus;
	}

	public String getOccupationStatusOther() {
		return this.occupationStatusOther;
	}

	public void setOccupationStatusOther(String occupationStatusOther) {
		this.occupationStatusOther = occupationStatusOther;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getPincode() {
		return this.pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Integer getPropertyAgeInMonth() {
		return this.propertyAgeInMonth;
	}

	public void setPropertyAgeInMonth(Integer propertyAgeInMonth) {
		this.propertyAgeInMonth = propertyAgeInMonth;
	}

	public Integer getPropertyAgeInYear() {
		return this.propertyAgeInYear;
	}

	public void setPropertyAgeInYear(Integer propertyAgeInYear) {
		this.propertyAgeInYear = propertyAgeInYear;
	}

	public Integer getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyTypeOther() {
		return this.propertyTypeOther;
	}

	public void setPropertyTypeOther(String propertyTypeOther) {
		this.propertyTypeOther = propertyTypeOther;
	}

	public Double getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

}