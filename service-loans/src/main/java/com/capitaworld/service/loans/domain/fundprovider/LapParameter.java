package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the fp_loan_against_property_details database table.
 * 
 */
@Entity
@Table(name="fp_loan_against_property_details")
public class LapParameter extends ProductMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="fp_product_id")
	private ProductMaster fpProductId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private Integer currency;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="is_age_display")
	private Boolean isAgeDisplay;

	@Column(name="is_age_mandatory")
	private Boolean isAgeMandatory;

	@Column(name="is_asset_value_display")
	private Boolean isAssetValueDisplay;

	@Column(name="is_asset_value_mandatory")
	private Boolean isAssetValueMandatory;

	@Column(name="is_geographical_display")
	private Boolean isGeographicalDisplay;

	@Column(name="is_geographical_mandatory")
	private Boolean isGeographicalMandatory;

	@Column(name="is_loan_amount_display")
	private Boolean isLoanAmountDisplay;

	@Column(name="is_loan_amount_mandatory")
	private Boolean isLoanAmountMandatory;

	@Column(name="is_tenure_display")
	private Boolean isTenureDisplay;

	@Column(name="is_tenure_mandatory")
	private Boolean isTenureMandatory;

	@Column(name="is_yearly_income_range_display")
	private Boolean isYearlyIncomeRangeDisplay;

	@Column(name="is_yearly_income_range_mandatory")
	private Boolean isYearlyIncomeRangeMandatory;

	@Column(name="max_age")
	private Double maxAge;

	@Column(name="max_asset_value")
	private Double maxAssetValue;

	@Column(name="max_loan_amount")
	private Double maxLoanAmount;

	@Column(name="max_tenure")
	private Double maxTenure;

	@Column(name="max_yearly_income_range")
	private Double maxYearlyIncomeRange;

	@Column(name="min_age")
	private Double minAge;

	@Column(name="min_asset_value")
	private Double minAssetValue;

	@Column(name="min_loan_amount")
	private Double minLoanAmount;

	@Column(name="min_tenure")
	private Double minTenure;

	@Column(name="min_yearly_income_range")
	private Double minYearlyIncomeRange;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	public LapParameter() {
	}

	public ProductMaster getFpProductId() {
		return this.fpProductId;
	}

	public void setFpProductId(ProductMaster fpProductId) {
		this.fpProductId = fpProductId;
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

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsAgeDisplay() {
		return this.isAgeDisplay;
	}

	public void setIsAgeDisplay(Boolean isAgeDisplay) {
		this.isAgeDisplay = isAgeDisplay;
	}

	public Boolean getIsAgeMandatory() {
		return this.isAgeMandatory;
	}

	public void setIsAgeMandatory(Boolean isAgeMandatory) {
		this.isAgeMandatory = isAgeMandatory;
	}

	public Boolean getIsAssetValueDisplay() {
		return this.isAssetValueDisplay;
	}

	public void setIsAssetValueDisplay(Boolean isAssetValueDisplay) {
		this.isAssetValueDisplay = isAssetValueDisplay;
	}

	public Boolean getIsAssetValueMandatory() {
		return this.isAssetValueMandatory;
	}

	public void setIsAssetValueMandatory(Boolean isAssetValueMandatory) {
		this.isAssetValueMandatory = isAssetValueMandatory;
	}

	public Boolean getIsGeographicalDisplay() {
		return this.isGeographicalDisplay;
	}

	public void setIsGeographicalDisplay(Boolean isGeographicalDisplay) {
		this.isGeographicalDisplay = isGeographicalDisplay;
	}

	public Boolean getIsGeographicalMandatory() {
		return this.isGeographicalMandatory;
	}

	public void setIsGeographicalMandatory(Boolean isGeographicalMandatory) {
		this.isGeographicalMandatory = isGeographicalMandatory;
	}

	public Boolean getIsLoanAmountDisplay() {
		return this.isLoanAmountDisplay;
	}

	public void setIsLoanAmountDisplay(Boolean isLoanAmountDisplay) {
		this.isLoanAmountDisplay = isLoanAmountDisplay;
	}

	public Boolean getIsLoanAmountMandatory() {
		return this.isLoanAmountMandatory;
	}

	public void setIsLoanAmountMandatory(Boolean isLoanAmountMandatory) {
		this.isLoanAmountMandatory = isLoanAmountMandatory;
	}

	public Boolean getIsTenureDisplay() {
		return this.isTenureDisplay;
	}

	public void setIsTenureDisplay(Boolean isTenureDisplay) {
		this.isTenureDisplay = isTenureDisplay;
	}

	public Boolean getIsTenureMandatory() {
		return this.isTenureMandatory;
	}

	public void setIsTenureMandatory(Boolean isTenureMandatory) {
		this.isTenureMandatory = isTenureMandatory;
	}

	public Boolean getIsYearlyIncomeRangeDisplay() {
		return this.isYearlyIncomeRangeDisplay;
	}

	public void setIsYearlyIncomeRangeDisplay(Boolean isYearlyIncomeRangeDisplay) {
		this.isYearlyIncomeRangeDisplay = isYearlyIncomeRangeDisplay;
	}

	public Boolean getIsYearlyIncomeRangeMandatory() {
		return this.isYearlyIncomeRangeMandatory;
	}

	public void setIsYearlyIncomeRangeMandatory(Boolean isYearlyIncomeRangeMandatory) {
		this.isYearlyIncomeRangeMandatory = isYearlyIncomeRangeMandatory;
	}

	public Double getMaxAge() {
		return this.maxAge;
	}

	public void setMaxAge(Double maxAge) {
		this.maxAge = maxAge;
	}

	public Double getMaxAssetValue() {
		return this.maxAssetValue;
	}

	public void setMaxAssetValue(Double maxAssetValue) {
		this.maxAssetValue = maxAssetValue;
	}

	public Double getMaxLoanAmount() {
		return this.maxLoanAmount;
	}

	public void setMaxLoanAmount(Double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public Double getMaxTenure() {
		return this.maxTenure;
	}

	public void setMaxTenure(Double maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Double getMaxYearlyIncomeRange() {
		return this.maxYearlyIncomeRange;
	}

	public void setMaxYearlyIncomeRange(Double maxYearlyIncomeRange) {
		this.maxYearlyIncomeRange = maxYearlyIncomeRange;
	}

	public Double getMinAge() {
		return this.minAge;
	}

	public void setMinAge(Double minAge) {
		this.minAge = minAge;
	}

	public Double getMinAssetValue() {
		return this.minAssetValue;
	}

	public void setMinAssetValue(Double minAssetValue) {
		this.minAssetValue = minAssetValue;
	}

	public Double getMinLoanAmount() {
		return this.minLoanAmount;
	}

	public void setMinLoanAmount(Double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public Double getMinTenure() {
		return this.minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
	}

	public Double getMinYearlyIncomeRange() {
		return this.minYearlyIncomeRange;
	}

	public void setMinYearlyIncomeRange(Double minYearlyIncomeRange) {
		this.minYearlyIncomeRange = minYearlyIncomeRange;
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

}