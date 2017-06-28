package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_primary_car_loan_details database table.
 * 
 */ 
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryCarLoanDetailRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String carModelName;

	private Integer carType;

	private String carVarient;

	private Boolean certifiedDealer;

	private String dealerName;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date deliveryDate;

	private Double downPayment;

	private String manufacturerName;

	private Integer newCarPurchaseType;

	private Double onRoadCarPrice;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date purchasePreownedDate;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date purchaseReimbursmentDate;

	private Long clientId;
	
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getCarVarient() {
		return carVarient;
	}

	public void setCarVarient(String carVarient) {
		this.carVarient = carVarient;
	}

	public Boolean getCertifiedDealer() {
		return certifiedDealer;
	}

	public void setCertifiedDealer(Boolean certifiedDealer) {
		this.certifiedDealer = certifiedDealer;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(Double downPayment) {
		this.downPayment = downPayment;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Integer getNewCarPurchaseType() {
		return newCarPurchaseType;
	}

	public void setNewCarPurchaseType(Integer newCarPurchaseType) {
		this.newCarPurchaseType = newCarPurchaseType;
	}

	public Double getOnRoadCarPrice() {
		return onRoadCarPrice;
	}

	public void setOnRoadCarPrice(Double onRoadCarPrice) {
		this.onRoadCarPrice = onRoadCarPrice;
	}

	public Date getPurchasePreownedDate() {
		return purchasePreownedDate;
	}

	public void setPurchasePreownedDate(Date purchasePreownedDate) {
		this.purchasePreownedDate = purchasePreownedDate;
	}

	public Date getPurchaseReimbursmentDate() {
		return purchaseReimbursmentDate;
	}

	public void setPurchaseReimbursmentDate(Date purchaseReimbursmentDate) {
		this.purchaseReimbursmentDate = purchaseReimbursmentDate;
	}



}