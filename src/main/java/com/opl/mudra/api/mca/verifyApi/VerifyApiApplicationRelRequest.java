package com.opl.mudra.api.mca.verifyApi;

import java.sql.Timestamp;

import com.opl.mudra.api.mca.McaCommonUtils;

/**
  * @auther : Maaz Shaikh
  * @Time : 02-Aug-2019 - 4:33:54 PM
  */
public class VerifyApiApplicationRelRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicantId;

	private Long clientId;

	private String dinName;
	
	private String panNumber;
	
	private Long createdBy;

	private Timestamp createdDate;

	private Boolean isActive;

	private Long modifiedBy;

	private Timestamp modifiedDate;

	private String referenceId;

	private Long userId;
	
	private Boolean isPlaceOrder;
	
	private Boolean isPlaceOrderComplete;
	
	private String requestKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getDinName() {
		return dinName;
	}

	public void setDinName(String dinName) {
		this.dinName = dinName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsPlaceOrder() {
		return isPlaceOrder;
	}

	public void setIsPlaceOrder(Boolean isPlaceOrder) {
		this.isPlaceOrder = isPlaceOrder;
	}

	public Boolean getIsPlaceOrderComplete() {
		return isPlaceOrderComplete;
	}

	public void setIsPlaceOrderComplete(Boolean isPlaceOrderComplete) {
		this.isPlaceOrderComplete = isPlaceOrderComplete;
	}

	public String getRequestKey() {
		return requestKey;
	}

	public void setRequestKey(String requestKey) {
		this.requestKey = requestKey;
	}

	@Override
	public String toString() {
		return McaCommonUtils.convertObjectToString(this);
	}
	
	
	

}
