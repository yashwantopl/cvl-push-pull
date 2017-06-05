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

	@Column(name="car_model_name")
	private String carModelName;

	@Column(name="car_type")
	private Integer carType;

	@Column(name="car_varient")
	private String carVarient;

	@Column(name="certified_dealer")
	private Boolean certifiedDealer;

	@Column(name="dealer_name")
	private String dealerName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_date")
	private Date deliveryDate;

	@Column(name="down_payment")
	private Double downPayment;

	@Column(name="manufacturer_name")
	private String manufacturerName;

	@Column(name="new_car_purchase_type")
	private Integer newCarPurchaseType;

	@Column(name="on_road_car_price")
	private Double onRoadCarPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchase_preowned_date")
	private Date purchasePreownedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchase_reimbursment_date")
	private Date purchaseReimbursmentDate;

	public PrimaryCarLoanDetail() {
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
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