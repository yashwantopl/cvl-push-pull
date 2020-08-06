package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class OutwardSupNilRated implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private Boolean isActive;

	private Date modifiedDate;

	private Double toalTaxableValue;

	private String retPeriod;
	
	

	private GstCalcMappingTable gstCalcMappingTable;
	
	
	
	
	/**
	 * @return the gstCalcMappingTable
	 */
	public GstCalcMappingTable getGstCalcMappingTable() {
		return gstCalcMappingTable;
	}

	/**
	 * @param gstCalcMappingTable the gstCalcMappingTable to set
	 */
	public void setGstCalcMappingTable(GstCalcMappingTable gstCalcMappingTable) {
		this.gstCalcMappingTable = gstCalcMappingTable;
	}

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

	public OutwardSupNilRated() {
		this.id = 0L;
		this.createdDate = new Date();
		this.isActive = false;
		this.modifiedDate = new Date();
		this.toalTaxableValue = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}