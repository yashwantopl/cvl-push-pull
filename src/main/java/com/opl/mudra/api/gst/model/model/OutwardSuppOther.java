package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class OutwardSuppOther implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double centralTax;

	private Double cess;

	private Date createdDate;

	private Double integratedTax;

	private Boolean isActive;

	private Date modifiedDate;

	private Double stateUiTax;

	private Double toalTaxableValue;
	
	private String retPeriod;
	
	

	/**
	 * @return the retPeriod
	 */
	public String getRetPeriod() {
		return retPeriod;
	}

	/**
	 * @param retPeriod the retPeriod to set
	 */
	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	private GstCalcMappingTable gstCalcMappingTable;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCentralTax() {
		return this.centralTax;
	}

	public void setCentralTax(Double centralTax) {
		this.centralTax = centralTax;
	}

	public Double getCess() {
		return this.cess;
	}

	public void setCess(Double cess) {
		this.cess = cess;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getIntegratedTax() {
		return this.integratedTax;
	}

	public void setIntegratedTax(Double integratedTax) {
		this.integratedTax = integratedTax;
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

	public Double getStateUiTax() {
		return this.stateUiTax;
	}

	public void setStateUiTax(Double stateUiTax) {
		this.stateUiTax = stateUiTax;
	}

	public Double getToalTaxableValue() {
		return this.toalTaxableValue;
	}

	public void setToalTaxableValue(Double toalTaxableValue) {
		this.toalTaxableValue = toalTaxableValue;
	}

	public GstCalcMappingTable getGstCalcMappingTable() {
		return this.gstCalcMappingTable;
	}

	public void setGstCalcMappingTable(GstCalcMappingTable gstCalcMappingTable) {
		this.gstCalcMappingTable = gstCalcMappingTable;
	}

	public OutwardSuppOther() {
		this.id = 0L;
		this.centralTax = 0.0;
		this.cess = 0.0;
		this.createdDate = new Date();
		this.integratedTax = 0.0;
		this.isActive = false;
		this.modifiedDate = new Date();
		this.stateUiTax = 0.0;
		this.toalTaxableValue = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}