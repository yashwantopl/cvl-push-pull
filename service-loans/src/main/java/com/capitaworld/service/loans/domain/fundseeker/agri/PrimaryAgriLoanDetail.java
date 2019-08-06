package com.capitaworld.service.loans.domain.fundseeker.agri;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

@Entity
@Table(name = "fs_primary_agri_loan_details")
@NamedQuery(name = "PrimaryAgriLoanDetail.findAll", query = "SELECT p FROM PrimaryAgriLoanDetail p")
public class PrimaryAgriLoanDetail extends LoanApplicationMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "survey_no")
	private String surveyNo;

	@Column(name = "land_size")
	private Double landSize;

	@Column(name = "irrigrated_unirrigrated")
	private Integer irrigratedUnirrigrated;
	
	@Column(name = "season_id")
	private Integer seasonId;
	
	@Column(name = "crop_id")
	private Integer cropId;
	
	private Integer acres;
	
	@Column(name = "guarantor_name")
	private String guarantorName;
	
	@Column(name = "dist_level_com_aval")
	private Boolean distLevelComAval;
	
	@Column(name = "availability_inputs")
	private Boolean availabilityInputs;
	
	@Column(name = "is_scale_finance_dltc")
	private Boolean isScaleFinanceDltc;
	
	@Column(name = "land_irr_facility")
	private Boolean landIrrFacility;
	
	@Column(name = "ade_arr_sell_farm_prod")
	private Boolean adeArrSellFarmProd;
	
	@Column(name = "crop_suit_soil_climatic")
	private Boolean cropSuitSoilClimatic;
	
	@Column(name = "crop_under_insurance_scheme")
	private Boolean cropUnderInsuranceScheme;
	
	@Column(name = "property_type")
	private Integer propertyType;
	
	@Column(name = "property_sub_type")
	private Integer propertySubType;
	
	@Column(name = "land_use")
	private String landUse;
	
	@Column(name = "is_crop_insurance")
	private Boolean isCropInsurance;
	
	private Integer status;
	
	@Column(name = "org_id")
	private Integer orgId;
	

	public PrimaryAgriLoanDetail() {
		// Do nothing because of X and Y.
	}

	public String getSurveyNo() {
		return surveyNo;
	}

	public void setSurveyNo(String surveyNo) {
		this.surveyNo = surveyNo;
	}

	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}

	public Integer getIrrigratedUnirrigrated() {
		return irrigratedUnirrigrated;
	}

	public void setIrrigratedUnirrigrated(Integer irrigratedUnirrigrated) {
		this.irrigratedUnirrigrated = irrigratedUnirrigrated;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getCropId() {
		return cropId;
	}

	public void setCropId(Integer cropId) {
		this.cropId = cropId;
	}

	public Integer getAcres() {
		return acres;
	}

	public void setAcres(Integer acres) {
		this.acres = acres;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public Boolean getDistLevelComAval() {
		return distLevelComAval;
	}

	public void setDistLevelComAval(Boolean distLevelComAval) {
		this.distLevelComAval = distLevelComAval;
	}

	public Boolean getAvailabilityInputs() {
		return availabilityInputs;
	}

	public void setAvailabilityInputs(Boolean availabilityInputs) {
		this.availabilityInputs = availabilityInputs;
	}

	public Boolean getIsScaleFinanceDltc() {
		return isScaleFinanceDltc;
	}

	public void setIsScaleFinanceDltc(Boolean isScaleFinanceDltc) {
		this.isScaleFinanceDltc = isScaleFinanceDltc;
	}

	public Boolean getLandIrrFacility() {
		return landIrrFacility;
	}

	public void setLandIrrFacility(Boolean landIrrFacility) {
		this.landIrrFacility = landIrrFacility;
	}

	public Boolean getAdeArrSellFarmProd() {
		return adeArrSellFarmProd;
	}

	public void setAdeArrSellFarmProd(Boolean adeArrSellFarmProd) {
		this.adeArrSellFarmProd = adeArrSellFarmProd;
	}

	public Boolean getCropSuitSoilClimatic() {
		return cropSuitSoilClimatic;
	}

	public void setCropSuitSoilClimatic(Boolean cropSuitSoilClimatic) {
		this.cropSuitSoilClimatic = cropSuitSoilClimatic;
	}

	public Boolean getCropUnderInsuranceScheme() {
		return cropUnderInsuranceScheme;
	}

	public void setCropUnderInsuranceScheme(Boolean cropUnderInsuranceScheme) {
		this.cropUnderInsuranceScheme = cropUnderInsuranceScheme;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public Integer getPropertySubType() {
		return propertySubType;
	}

	public void setPropertySubType(Integer propertySubType) {
		this.propertySubType = propertySubType;
	}

	public String getLandUse() {
		return landUse;
	}

	public void setLandUse(String landUse) {
		this.landUse = landUse;
	}

	public Boolean getIsCropInsurance() {
		return isCropInsurance;
	}

	public void setIsCropInsurance(Boolean isCropInsurance) {
		this.isCropInsurance = isCropInsurance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}
