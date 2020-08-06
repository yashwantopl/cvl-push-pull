package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SummB2esSale implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private Double igst;

	private Boolean isActive;

	private Date modifiedDate;

	private String stateCode;

	private float ttlAmtOfAllInv;

	private Double ttlInvRaised;

	private Double ttlTaxVal;

	private Double ttlIncVal;

	private String retPeriod;

	private GstCalcMappingTable gstCalcMappingTable;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getIgst() {
		return this.igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public float getTtlAmtOfAllInv() {
		return this.ttlAmtOfAllInv;
	}

	public void setTtlAmtOfAllInv(float ttlAmtOfAllInv) {
		this.ttlAmtOfAllInv = ttlAmtOfAllInv;
	}

	public Double getTtlInvRaised() {
		return this.ttlInvRaised;
	}

	public void setTtlInvRaised(Double ttlInvRaised) {
		this.ttlInvRaised = ttlInvRaised;
	}

	public Double getTtlTaxVal() {
		return this.ttlTaxVal;
	}

	public void setTtlTaxVal(Double ttlTaxVal) {
		this.ttlTaxVal = ttlTaxVal;
	}

	public GstCalcMappingTable getGstCalcMappingTable() {
		return this.gstCalcMappingTable;
	}

	public void setGstCalcMappingTable(GstCalcMappingTable gstCalcMappingTable) {
		this.gstCalcMappingTable = gstCalcMappingTable;
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public Double getTtlIncVal() {
		return ttlIncVal;
	}

	public void setTtlIncVal(Double ttlIncVal) {
		this.ttlIncVal = ttlIncVal;
	}

	public SummB2esSale() {
		this.id = 0L;
		this.createdDate = new Date();
		this.igst = 0.0;
		this.isActive = false;
		this.modifiedDate = new Date();
		this.stateCode = "";
		this.ttlAmtOfAllInv = 0L;
		this.ttlInvRaised = 0.0;
		this.ttlTaxVal = 0.0;
		this.ttlIncVal = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}