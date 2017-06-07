package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;

public class CarLoanFinalViewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String color;
	private String supplier;
	private String registrationNumber;
	private String vehicleCost;
	private String insuaranceCost;
	private String accessoriesCost;
	private String roadTaxCost;
	private String otherCost;
	private String totalCost;
	
	private CarLoanPrimaryViewResponse carLoanPrimaryViewResponse;
	private RetailFinalViewResponse finalViewResponse;
	
	public CarLoanPrimaryViewResponse getCarLoanPrimaryViewResponse() {
		return carLoanPrimaryViewResponse;
	}
	public void setCarLoanPrimaryViewResponse(CarLoanPrimaryViewResponse carLoanPrimaryViewResponse) {
		this.carLoanPrimaryViewResponse = carLoanPrimaryViewResponse;
	}
	public RetailFinalViewResponse getFinalViewResponse() {
		return finalViewResponse;
	}
	public void setFinalViewResponse(RetailFinalViewResponse finalViewResponse) {
		this.finalViewResponse = finalViewResponse;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getVehicleCost() {
		return vehicleCost;
	}
	public void setVehicleCost(String vehicleCost) {
		this.vehicleCost = vehicleCost;
	}
	public String getInsuaranceCost() {
		return insuaranceCost;
	}
	public void setInsuaranceCost(String insuaranceCost) {
		this.insuaranceCost = insuaranceCost;
	}
	public String getAccessoriesCost() {
		return accessoriesCost;
	}
	public void setAccessoriesCost(String accessoriesCost) {
		this.accessoriesCost = accessoriesCost;
	}
	public String getRoadTaxCost() {
		return roadTaxCost;
	}
	public void setRoadTaxCost(String roadTaxCost) {
		this.roadTaxCost = roadTaxCost;
	}
	public String getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
