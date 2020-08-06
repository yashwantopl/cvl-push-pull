package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SummExemOrNilSale implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private Boolean isActive;

	private Date modifiedDate;

	private Double ttlExemptdOtwrdSupl;

	private Double ttlNilRatedOtwrdSupl;

	private Double ttlNonGstOtwrdSupl;

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

	public Double getTtlExemptdOtwrdSupl() {
		return this.ttlExemptdOtwrdSupl;
	}

	public void setTtlExemptdOtwrdSupl(Double ttlExemptdOtwrdSupl) {
		this.ttlExemptdOtwrdSupl = ttlExemptdOtwrdSupl;
	}

	public Double getTtlNilRatedOtwrdSupl() {
		return this.ttlNilRatedOtwrdSupl;
	}

	public void setTtlNilRatedOtwrdSupl(Double ttlNilRatedOtwrdSupl) {
		this.ttlNilRatedOtwrdSupl = ttlNilRatedOtwrdSupl;
	}

	public Double getTtlNonGstOtwrdSupl() {
		return this.ttlNonGstOtwrdSupl;
	}

	public void setTtlNonGstOtwrdSupl(Double ttlNonGstOtwrdSupl) {
		this.ttlNonGstOtwrdSupl = ttlNonGstOtwrdSupl;
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

	public SummExemOrNilSale() {
		this.id = 0L;
		this.createdDate = new Date();
		this.isActive = false;
		this.modifiedDate = new Date();
		this.ttlExemptdOtwrdSupl = 0.0;
		this.ttlNilRatedOtwrdSupl = 0.0;
		this.ttlNonGstOtwrdSupl = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}