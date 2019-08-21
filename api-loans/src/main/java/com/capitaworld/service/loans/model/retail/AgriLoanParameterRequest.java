package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
/**
 * @author akshay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgriLoanParameterRequest extends ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double farmMaintenancePer;
	private Double farmMaintenanceMax;
	private Double familyMaintenancePer;
	private Double familyMaintenanceMax;
	
	private List<Integer> districtIds;
	private Boolean isDistrictDisplay = false;
	private Boolean isDistrictMandatory = false;
	private List<Integer> cropIds;
	private Boolean isCropDisplay = false;
	private Boolean isCropMandatory = false;
	private List<Integer> irriUnirriIds;
	private Boolean isIrriUnirriDisplay = false;
	private Boolean isIrriUnirriMandatory = false;
	private Object workflowData;
	
	public Double getFarmMaintenancePer() {
		return farmMaintenancePer;
	}
	public void setFarmMaintenancePer(Double farmMaintenancePer) {
		this.farmMaintenancePer = farmMaintenancePer;
	}
	public Double getFarmMaintenanceMax() {
		return farmMaintenanceMax;
	}
	public void setFarmMaintenanceMax(Double farmMaintenanceMax) {
		this.farmMaintenanceMax = farmMaintenanceMax;
	}
	public Double getFamilyMaintenancePer() {
		return familyMaintenancePer;
	}
	public void setFamilyMaintenancePer(Double familyMaintenancePer) {
		this.familyMaintenancePer = familyMaintenancePer;
	}
	public Double getFamilyMaintenanceMax() {
		return familyMaintenanceMax;
	}
	public void setFamilyMaintenanceMax(Double familyMaintenanceMax) {
		this.familyMaintenanceMax = familyMaintenanceMax;
	}
	public List<Integer> getDistrictIds() {
		return districtIds;
	}
	public void setDistrictIds(List<Integer> districtIds) {
		this.districtIds = districtIds;
	}
	public Boolean getIsDistrictDisplay() {
		return isDistrictDisplay;
	}
	public void setIsDistrictDisplay(Boolean isDistrictDisplay) {
		this.isDistrictDisplay = isDistrictDisplay;
	}
	public Boolean getIsDistrictMandatory() {
		return isDistrictMandatory;
	}
	public void setIsDistrictMandatory(Boolean isDistrictMandatory) {
		this.isDistrictMandatory = isDistrictMandatory;
	}
	public List<Integer> getCropIds() {
		return cropIds;
	}
	public void setCropIds(List<Integer> cropIds) {
		this.cropIds = cropIds;
	}
	public Boolean getIsCropDisplay() {
		return isCropDisplay;
	}
	public void setIsCropDisplay(Boolean isCropDisplay) {
		this.isCropDisplay = isCropDisplay;
	}
	public Boolean getIsCropMandatory() {
		return isCropMandatory;
	}
	public void setIsCropMandatory(Boolean isCropMandatory) {
		this.isCropMandatory = isCropMandatory;
	}
	public List<Integer> getIrriUnirriIds() {
		return irriUnirriIds;
	}
	public void setIrriUnirriIds(List<Integer> irriUnirriIds) {
		this.irriUnirriIds = irriUnirriIds;
	}
	public Boolean getIsIrriUnirriDisplay() {
		return isIrriUnirriDisplay;
	}
	public void setIsIrriUnirriDisplay(Boolean isIrriUnirriDisplay) {
		this.isIrriUnirriDisplay = isIrriUnirriDisplay;
	}
	public Boolean getIsIrriUnirriMandatory() {
		return isIrriUnirriMandatory;
	}
	public void setIsIrriUnirriMandatory(Boolean isIrriUnirriMandatory) {
		this.isIrriUnirriMandatory = isIrriUnirriMandatory;
	}
	public Object getWorkflowData() {
		return workflowData;
	}
	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}
}