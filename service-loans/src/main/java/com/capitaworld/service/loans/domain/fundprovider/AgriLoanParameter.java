package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@Entity
@Table(name = "fp_agri_loan_details")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class AgriLoanParameter extends ProductMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer currency;
	@Column(name = "farm_maintenance_per")
	private Double farmMaintenancePer;
	@Column(name = "farm_maintenance_max")
	private Double farmMaintenanceMax;
	@Column(name = "family_maintenance_per")
	private Double familyMaintenancePer;
	@Column(name = "family_maintenance_max")
	private Double familyMaintenanceMax;
	@Column(name = "is_district_display")
	private Boolean isDistrictDisplay = false;
	@Column(name = "is_district_mandatory")
	private Boolean isDistrictMandatory = false;
	@Column(name = "is_crop_display")
	private Boolean isCropDisplay = false;
	@Column(name = "is_crop_mandatory")
	private Boolean isCropMandatory = false;
	@Column(name = "is_irri_unirri_display")
	private Boolean isIrriUnirriDisplay = false;
	@Column(name = "is_irri_unirri_mandatory")
	private Boolean isIrriUnirriMandatory = false;
	
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
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
}