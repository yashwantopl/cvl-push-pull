package com.capitaworld.service.loans.model.teaser.primaryview;

import com.capitaworld.service.loans.model.AddressResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dhaval on 23-May-17.
 */

public class CarLoanResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String purposeOfLoan;

    private AddressResponse permanentAddress;

    private AddressResponse officeAddress;

    private String dateOfProposal;

    private String tenure;

    private String loanType;

    private Double loanAmount;

    private String carModelName;

    private String carType;

    private String carVarient;

    private String certifiedDealer;

    private String dealerName;

    private String deliveryDate;

    private Double downPayment;

    private String manufacturerName;

    private String newCarPurchaseType;

    private Double onRoadCarPrice;

    private String purchasePreownedDate;

    private String purchaseReimbursmentDate;

    private List<Object> applicantProfilePicture;

    private String currency;

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public AddressResponse getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressResponse permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressResponse getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(AddressResponse officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getDateOfProposal() {
        return dateOfProposal;
    }

    public void setDateOfProposal(String dateOfProposal) {
        this.dateOfProposal = dateOfProposal;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarVarient() {
        return carVarient;
    }

    public void setCarVarient(String carVarient) {
        this.carVarient = carVarient;
    }

    public String getCertifiedDealer() {
        return certifiedDealer;
    }

    public void setCertifiedDealer(String certifiedDealer) {
        this.certifiedDealer = certifiedDealer;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
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

    public String getNewCarPurchaseType() {
        return newCarPurchaseType;
    }

    public void setNewCarPurchaseType(String newCarPurchaseType) {
        this.newCarPurchaseType = newCarPurchaseType;
    }

    public Double getOnRoadCarPrice() {
        return onRoadCarPrice;
    }

    public void setOnRoadCarPrice(Double onRoadCarPrice) {
        this.onRoadCarPrice = onRoadCarPrice;
    }

    public String getPurchasePreownedDate() {
        return purchasePreownedDate;
    }

    public void setPurchasePreownedDate(String purchasePreownedDate) {
        this.purchasePreownedDate = purchasePreownedDate;
    }

    public String getPurchaseReimbursmentDate() {
        return purchaseReimbursmentDate;
    }

    public void setPurchaseReimbursmentDate(String purchaseReimbursmentDate) {
        this.purchaseReimbursmentDate = purchaseReimbursmentDate;
    }

    public List<Object> getApplicantProfilePicture() {
        return applicantProfilePicture;
    }

    public void setApplicantProfilePicture(List<Object> applicantProfilePicture) {
        this.applicantProfilePicture = applicantProfilePicture;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
