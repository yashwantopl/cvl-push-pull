package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@Entity
@Table(name = "fp_agri_loan_details_temp")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class AgriLoanParameterTemp extends ProductMasterTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer currency;
	private Double farmMaintenancePer;
	private Double farmMaintenanceMax;
	private Double familyMaintenancePer;
	private Double familyMaintenanceMax;
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