package com.opl.mudra.api.gst.model;

import java.sql.Timestamp;

public class GstNotApplicableResponse {

	private Long id;

	private Long applicationId;

	private String pan;

	private String data;

	private Timestamp createdDate;


	private Boolean isActive;

	private Long modifiedBy;

	private Long stateId;

	private Long cityId;
	
	private Double totalPurchaseFromIm;
	
	private Double projectedPurchaseFromIm;
	
	private Double highestSalesPerticularfromIM;

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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Double getTotalPurchaseFromIm() {
		return totalPurchaseFromIm;
	}

	public void setTotalPurchaseFromIm(Double totalPurchaseFromIm) {
		this.totalPurchaseFromIm = totalPurchaseFromIm;
	}

	public Double getProjectedPurchaseFromIm() {
		return projectedPurchaseFromIm;
	}

	public void setProjectedPurchaseFromIm(Double projectedPurchaseFromIm) {
		this.projectedPurchaseFromIm = projectedPurchaseFromIm;
	}

	public Double getHighestSalesPerticularfromIM() {
		return highestSalesPerticularfromIM;
	}

	public void setHighestSalesPerticularfromIM(Double highestSalesPerticularfromIM) {
		this.highestSalesPerticularfromIM = highestSalesPerticularfromIM;
	}
	
}
