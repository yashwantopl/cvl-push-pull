package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6263829991337267320L;
	private Long applicationId;
	private String trxnId;
	private String nameOfEntity;
	private Address address;
	private String typeOfPayment;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date appointmentDate;
	private String appointmentTime;
	private String emailAddress;
	private String mobileNumber;
	private Double paymentAmount;
	private String purposeCode;
	private String responseParams;
	private String status;
	private String panNo;
	private Boolean isAcceptConsent;

	public PaymentRequest() {

	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrxnId() {
		return trxnId;
	}

	public void setTrxnId(String trxnId) {
		this.trxnId = trxnId;
	}
	

	public Boolean getIsAcceptConsent() {
		return isAcceptConsent;
	}

	public void setIsAcceptConsent(Boolean isAcceptConsent) {
		this.isAcceptConsent = isAcceptConsent;
	}
	

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public String getResponseParams() {
		return responseParams;
	}

	public void setResponseParams(String responseParams) {
		this.responseParams = responseParams;
	}
	
	

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	@Override
	public String toString() {
		return "PaymentRequest [applicationId=" + applicationId + ", trxnId=" + trxnId + ", nameOfEntity="
				+ nameOfEntity + ", address=" + address + ", typeOfPayment=" + typeOfPayment + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", emailAddress=" + emailAddress
				+ ", mobileNumber=" + mobileNumber + ", paymentAmount=" + paymentAmount + ", purposeCode=" + purposeCode
				+ ", responseParams=" + responseParams + ", status=" + status + ", panNo=" + panNo
				+ ", isAcceptConsent=" + isAcceptConsent + "]";
	}

	
}
