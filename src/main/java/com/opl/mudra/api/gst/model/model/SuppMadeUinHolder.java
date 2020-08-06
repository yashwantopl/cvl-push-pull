package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SuppMadeUinHolder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Date createdDate;

	private Double integratedTax;

	private Boolean isActive;

	private Date modifiedDate;

	private String placeOfSupp;

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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPlaceOfSupp() {
		return this.placeOfSupp;
	}

	public void setPlaceOfSupp(String placeOfSupp) {
		this.placeOfSupp = placeOfSupp;
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

	public SuppMadeUinHolder() {
		this.id = "";
		this.createdDate = new Date();
		this.integratedTax = 0.0;
		this.isActive = false;
		this.modifiedDate = new Date();
		this.placeOfSupp = "";
		this.toalTaxableValue = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}