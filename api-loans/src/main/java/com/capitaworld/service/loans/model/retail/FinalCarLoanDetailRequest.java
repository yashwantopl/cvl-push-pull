package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_final_car_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalCarLoanDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long clientId;

	private Long applicationId;

	private Double accessoriesCost;

	private String carColour;

	private String carRegistrationNumber;

	private String carSupplier;

	private Date deliveryDate;

	private Double insuranceCost;

	private Double loanTotalCost;

	private Double othersCost;

	private Double roadTax;

	private Double vehicleCost;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getAccessoriesCost() {
		return accessoriesCost;
	}

	public void setAccessoriesCost(Double accessoriesCost) {
		this.accessoriesCost = accessoriesCost;
	}

	public String getCarColour() {
		return carColour;
	}

	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getCarSupplier() {
		return carSupplier;
	}

	public void setCarSupplier(String carSupplier) {
		this.carSupplier = carSupplier;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public Double getLoanTotalCost() {
		return loanTotalCost;
	}

	public void setLoanTotalCost(Double loanTotalCost) {
		this.loanTotalCost = loanTotalCost;
	}

	public Double getOthersCost() {
		return othersCost;
	}

	public void setOthersCost(Double othersCost) {
		this.othersCost = othersCost;
	}

	public Double getRoadTax() {
		return roadTax;
	}

	public void setRoadTax(Double roadTax) {
		this.roadTax = roadTax;
	}

	public Double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(Double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


}