package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_primary_car_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_primary_car_loan_details")
public class PrimaryCarLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="accessories_cost")
	private Double accessoriesCost;

	@Column(name="car_colour")
	private String carColour;

	@Column(name="car_registration_number")
	private String carRegistrationNumber;

	@Column(name="car_supplier")
	private String carSupplier;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_date")
	private Date deliveryDate;

	@Column(name="insurance_cost")
	private Double insuranceCost;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="loan_total_cost")
	private Double loanTotalCost;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="others_cost")
	private Double othersCost;

	@Column(name="road_tax")
	private Double roadTax;

	@Column(name="vehicle_cost")
	private Double vehicleCost;

	public PrimaryCarLoanDetail() {
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Double getAccessoriesCost() {
		return this.accessoriesCost;
	}

	public void setAccessoriesCost(Double accessoriesCost) {
		this.accessoriesCost = accessoriesCost;
	}

	public String getCarColour() {
		return this.carColour;
	}

	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}

	public String getCarRegistrationNumber() {
		return this.carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getCarSupplier() {
		return this.carSupplier;
	}

	public void setCarSupplier(String carSupplier) {
		this.carSupplier = carSupplier;
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

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getInsuranceCost() {
		return this.insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLoanTotalCost() {
		return this.loanTotalCost;
	}

	public void setLoanTotalCost(Double loanTotalCost) {
		this.loanTotalCost = loanTotalCost;
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

	public Double getOthersCost() {
		return this.othersCost;
	}

	public void setOthersCost(Double othersCost) {
		this.othersCost = othersCost;
	}

	public Double getRoadTax() {
		return this.roadTax;
	}

	public void setRoadTax(Double roadTax) {
		this.roadTax = roadTax;
	}

	public Double getVehicleCost() {
		return this.vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

}