package com.opl.mudra.api.payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GatewayRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6256889837661145480L;
	private Long id;
	private Long applicationId;
	private Long userId;
	private String txnId;
	private Double amount;
	private String productInfo;
	private String firstName;
	private String email;
	private String phone;
	private String status;

	private Date updatedOn;
	private Long clientId;
	private Integer stateId;
	
	private Date createdOn;
	private Long createdBy;
	private Long updatedBy;
	private Boolean result;
	private Integer businessTypeId;
	private Integer downloadType;
	private List<Long> applicationIds;

	private Boolean isActive;
	private Map<String, Object> clientInfo;
	private Boolean isSbiSpecific;
	private String content;
	
	// newly added fields

	public Map<String, Object> getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(Map<String, Object> clientInfo) {
		this.clientInfo = clientInfo;
	}

	private String purposeCode;
	private String paymentType;
	private String responseParams;
	private String streetName;
	private String landmark;
	private String premiseNumber;
	private String gatewayType;
	private String requestType;

	/////////////// payment req

	private String nameOfEntity;
	private Address address;
	private String cityName;
	private String stateName;
	private String pinCode;
	private String countryName;
	private String typeOfPayment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date appointmentDate;
	private String appointmentTime;
	private String emailAddress;
	private String mobileNumber;
	private Double paymentAmount;

	private String txnType;
	private String txnReferenceNo;
	private String gstIn;
	private Boolean sameProductIdSamePan;
	private String skipType;
	private String specificSkipType;
	private Boolean skipPayment;
	private String additionalSkipType;
	private Integer loanTypeId;
	private String subject;
	private String errorLog;
	private String reason;
	
	// for Response purpose
	private Map<String, Object> invoiceData;
	private String paymentStatus;
	private String validationMessage;
	private Integer PaymentTypeId;
	
	
	public GatewayRequest() {
		super();
	}
	public GatewayRequest(Long applicationId) {
		super();
		this.applicationId=applicationId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdditionalSkipType() {
		return additionalSkipType;
	}

	public void setAdditionalSkipType(String additionalSkipType) {
		this.additionalSkipType = additionalSkipType;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getSpecificSkipType() {
		return specificSkipType;
	}

	public void setSpecificSkipType(String specificSkipType) {
		this.specificSkipType = specificSkipType;
	}
	
	public Integer getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Boolean getSkipPayment() {
		return skipPayment;
	}

	public void setSkipPayment(Boolean skipPayment) {
		this.skipPayment = skipPayment;
	}

	public String getSkipType() {
		return skipType;
	}

	public void setSkipType(String skipType) {
		this.skipType = skipType;
	}

	public Boolean getIsSbiSpecific() {
		return isSbiSpecific;
	}

	public void setIsSbiSpecific(Boolean isSbiSpecific) {
		this.isSbiSpecific = isSbiSpecific;
	}

	public Boolean getSameProductIdSamePan() {
		return sameProductIdSamePan;
	}

	public void setSameProductIdSamePan(Boolean sameProductIdSamePan) {
		this.sameProductIdSamePan = sameProductIdSamePan;
	}

	public String getTxnReferenceNo() {
		return txnReferenceNo;
	}

	public void setTxnReferenceNo(String txnReferenceNo) {
		this.txnReferenceNo = txnReferenceNo;
	}

	/**
	 * private String purposeCode; private String responseParams;
	private String status; */
	private String panNo;
	private Boolean isAcceptConsent;

	/** private String gatewayType; 
	* private Long userId; 
	*
	* private String requestType; private Integer businessTypeId;
	*/
	private Long onlinePaymentId;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getResponseParams() {
		return responseParams;
	}

	public void setResponseParams(String responseParams) {
		this.responseParams = responseParams;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getGatewayType() {
		return gatewayType;
	}

	public void setGatewayType(String gatewayType) {
		this.gatewayType = gatewayType;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	// from payment req

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
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

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Boolean getIsAcceptConsent() {
		return isAcceptConsent;
	}

	public void setIsAcceptConsent(Boolean isAcceptConsent) {
		this.isAcceptConsent = isAcceptConsent;
	}

	public Long getOnlinePaymentId() {
		return onlinePaymentId;
	}

	public void setOnlinePaymentId(Long onlinePaymentId) {
		this.onlinePaymentId = onlinePaymentId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	
	public Integer getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(Integer downloadType) {
		this.downloadType = downloadType;
	}
	
	public List<Long> getApplicationIds() {
		return applicationIds;
	}

	public void setApplicationIds(List<Long> applicationIds) {
		this.applicationIds = applicationIds;
	}

	public Map<String, Object> getInvoiceData() {
		return invoiceData;
	}

	public void setInvoiceData(Map<String, Object> invoiceData) {
		this.invoiceData = invoiceData;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public Integer getPaymentTypeId() {
		return PaymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		PaymentTypeId = paymentTypeId;
	}

	@Override
	public String toString() {
		return "GatewayRequest [id=" + id + ", applicationId=" + applicationId + ", userId=" + userId + ", txnId="
				+ txnId + ", amount=" + amount + ", productInfo=" + productInfo + ", firstName=" + firstName
				+ ", email=" + email + ", phone=" + phone + ", status=" + status + ", updatedOn=" + updatedOn
				+ ", clientId=" + clientId + ", stateId=" + stateId + ", createdOn=" + createdOn + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", result=" + result + ", businessTypeId=" + businessTypeId
				+ ", downloadType=" + downloadType + ", applicationIds=" + applicationIds + ", isActive=" + isActive
				+ ", clientInfo=" + clientInfo + ", isSbiSpecific=" + isSbiSpecific + ", content=" + content
				+ ", purposeCode=" + purposeCode + ", paymentType=" + paymentType + ", responseParams=" + responseParams
				+ ", streetName=" + streetName + ", landmark=" + landmark + ", premiseNumber=" + premiseNumber
				+ ", gatewayType=" + gatewayType + ", requestType=" + requestType + ", nameOfEntity=" + nameOfEntity
				+ ", address=" + address + ", cityName=" + cityName + ", stateName=" + stateName + ", pinCode="
				+ pinCode + ", countryName=" + countryName + ", typeOfPayment=" + typeOfPayment + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", emailAddress=" + emailAddress
				+ ", mobileNumber=" + mobileNumber + ", paymentAmount=" + paymentAmount + ", txnType=" + txnType
				+ ", txnReferenceNo=" + txnReferenceNo + ", gstIn=" + gstIn + ", sameProductIdSamePan="
				+ sameProductIdSamePan + ", skipType=" + skipType + ", specificSkipType=" + specificSkipType
				+ ", skipPayment=" + skipPayment + ", additionalSkipType=" + additionalSkipType + ", loanTypeId="
				+ loanTypeId + ", subject=" + subject + ", errorLog=" + errorLog + ", reason=" + reason
				+ ", invoiceData=" + invoiceData + ", paymentStatus=" + paymentStatus + ", validationMessage="
				+ validationMessage + ", PaymentTypeId=" + PaymentTypeId + ", panNo=" + panNo + ", isAcceptConsent="
				+ isAcceptConsent + ", onlinePaymentId=" + onlinePaymentId + "]";
	}

	

}
