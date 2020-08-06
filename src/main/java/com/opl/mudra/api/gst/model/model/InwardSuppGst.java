package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;

public class InwardSuppGst implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private String interStateSupp;

	private Boolean isActive;

	private Date modifiedDate;

	private Double toalTaxableValue;
	
	private String retPeriod;

	
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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getInterStateSupp() {
		return this.interStateSupp;
	}

	public void setInterStateSupp(String interStateSupp) {
		this.interStateSupp = interStateSupp;
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

	public InwardSuppGst() {
		this.id = 0L;
		this.createdDate = new Date();
		this.interStateSupp = "";
		this.isActive = false;
		this.modifiedDate = new Date();
		this.toalTaxableValue = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}