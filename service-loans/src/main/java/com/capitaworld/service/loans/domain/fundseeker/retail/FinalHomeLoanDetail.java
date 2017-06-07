package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_final_home_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_final_home_loan_details")
public class FinalHomeLoanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="built_up_area")
	private Double builtUpArea;

	@Column(name="carpet_area")
	private Double carpetArea;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="estimated_rental_income")
	private Double estimatedRentalIncome;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="project_city_state")
	private String projectCityState;

	@Column(name="property_address_city")
	private Integer propertyAddressCity;

	@Column(name="property_address_country")
	private Integer propertyAddressCountry;

	@Column(name="property_address_landmark")
	private String propertyAddressLandmark;

	@Column(name="property_address_pincode")
	private String propertyAddressPincode;

	@Column(name="property_address_premise")
	private String propertyAddressPremise;

	@Column(name="property_address_state")
	private Integer propertyAddressState;

	@Column(name="property_address_street")
	private String propertyAddressStreet;

	@Column(name="property_used")
	private Integer propertyUsed;

	@Column(name="seller_name")
	private String sellerName;

	@Column(name="sellers_address_city")
	private Integer sellersAddressCity;

	@Column(name="sellers_address_country")
	private Integer sellersAddressCountry;

	@Column(name="sellers_address_landmark")
	private String sellersAddressLandmark;

	@Column(name="sellers_address_pincode")
	private String sellersAddressPincode;

	@Column(name="sellers_address_premise")
	private String sellersAddressPremise;

	@Column(name="sellers_address_state")
	private Integer sellersAddressState;

	@Column(name="sellers_address_street")
	private String sellersAddressStreet;

	@Column(name="super_built_up_area")
	private Double superBuiltUpArea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Double getCarpetArea() {
		return carpetArea;
	}

	public void setCarpetArea(Double carpetArea) {
		this.carpetArea = carpetArea;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getEstimatedRentalIncome() {
		return estimatedRentalIncome;
	}

	public void setEstimatedRentalIncome(Double estimatedRentalIncome) {
		this.estimatedRentalIncome = estimatedRentalIncome;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getProjectCityState() {
		return projectCityState;
	}

	public void setProjectCityState(String projectCityState) {
		this.projectCityState = projectCityState;
	}

	public Integer getPropertyAddressCity() {
		return propertyAddressCity;
	}

	public void setPropertyAddressCity(Integer propertyAddressCity) {
		this.propertyAddressCity = propertyAddressCity;
	}

	public Integer getPropertyAddressCountry() {
		return propertyAddressCountry;
	}

	public void setPropertyAddressCountry(Integer propertyAddressCountry) {
		this.propertyAddressCountry = propertyAddressCountry;
	}

	public String getPropertyAddressLandmark() {
		return propertyAddressLandmark;
	}

	public void setPropertyAddressLandmark(String propertyAddressLandmark) {
		this.propertyAddressLandmark = propertyAddressLandmark;
	}

	public String getPropertyAddressPincode() {
		return propertyAddressPincode;
	}

	public void setPropertyAddressPincode(String propertyAddressPincode) {
		this.propertyAddressPincode = propertyAddressPincode;
	}

	public String getPropertyAddressPremise() {
		return propertyAddressPremise;
	}

	public void setPropertyAddressPremise(String propertyAddressPremise) {
		this.propertyAddressPremise = propertyAddressPremise;
	}

	public Integer getPropertyAddressState() {
		return propertyAddressState;
	}

	public void setPropertyAddressState(Integer propertyAddressState) {
		this.propertyAddressState = propertyAddressState;
	}

	public String getPropertyAddressStreet() {
		return propertyAddressStreet;
	}

	public void setPropertyAddressStreet(String propertyAddressStreet) {
		this.propertyAddressStreet = propertyAddressStreet;
	}


	public Integer getPropertyUsed() {
		return propertyUsed;
	}

	public void setPropertyUsed(Integer propertyUsed) {
		this.propertyUsed = propertyUsed;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getSellersAddressCity() {
		return sellersAddressCity;
	}

	public void setSellersAddressCity(Integer sellersAddressCity) {
		this.sellersAddressCity = sellersAddressCity;
	}

	public Integer getSellersAddressCountry() {
		return sellersAddressCountry;
	}

	public void setSellersAddressCountry(Integer sellersAddressCountry) {
		this.sellersAddressCountry = sellersAddressCountry;
	}

	public String getSellersAddressLandmark() {
		return sellersAddressLandmark;
	}

	public void setSellersAddressLandmark(String sellersAddressLandmark) {
		this.sellersAddressLandmark = sellersAddressLandmark;
	}

	public String getSellersAddressPincode() {
		return sellersAddressPincode;
	}

	public void setSellersAddressPincode(String sellersAddressPincode) {
		this.sellersAddressPincode = sellersAddressPincode;
	}

	public String getSellersAddressPremise() {
		return sellersAddressPremise;
	}

	public void setSellersAddressPremise(String sellersAddressPremise) {
		this.sellersAddressPremise = sellersAddressPremise;
	}

	public Integer getSellersAddressState() {
		return sellersAddressState;
	}

	public void setSellersAddressState(Integer sellersAddressState) {
		this.sellersAddressState = sellersAddressState;
	}

	public String getSellersAddressStreet() {
		return sellersAddressStreet;
	}

	public void setSellersAddressStreet(String sellersAddressStreet) {
		this.sellersAddressStreet = sellersAddressStreet;
	}

	public Double getSuperBuiltUpArea() {
		return superBuiltUpArea;
	}

	public void setSuperBuiltUpArea(Double superBuiltUpArea) {
		this.superBuiltUpArea = superBuiltUpArea;
	}

	
}