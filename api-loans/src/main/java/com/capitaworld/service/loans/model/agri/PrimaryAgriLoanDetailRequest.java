package com.capitaworld.service.loans.model.agri;

import java.io.Serializable;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryAgriLoanDetailRequest extends LoanApplicationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String surveyNo;

	private Double landSize;

	private Integer irrigratedUnirrigrated;

	private Integer seasonId;
	
	private Integer cropId;
	
	private Integer acres;
	
	private String guarantorName;
	
	private Boolean distLevelComAval;
	
	private Boolean availabilityInputs;
	
	private Boolean isScaleFinanceDltc;
	
	private Boolean landIrrFacility;
	
	private Boolean adeArrSellFarmProd;
	
	private Boolean cropSuitSoilClimatic;
	
	private Boolean cropUnderInsuranceScheme;
	
	public PrimaryAgriLoanDetailRequest() {
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
	
}
