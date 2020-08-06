package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummB2bSale implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double cess;

	private Double cgst;

	private Date createdDate;

	private String gstin;

	private Double igst;

	private Boolean isActive;

	private Date modifiedDate;

	private String nameOfCustomer;

	private Double percOfTtlSales;

	private Double sgst;

	private Double ttlAmtOfAllInv;

	private Double ttlInvVal;

	private Double ttlInvRaised;

	private Double ttlTaxVal;


	private String retPeriod;

	private GstCalcMappingTable gstCalcMappingTable;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCess() {
		return this.cess;
	}

	public void setCess(Double cess) {
		this.cess = cess;
	}

	public Double getCgst() {
		return this.cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getGstin() {
		return this.gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
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

	public String getNameOfCustomer() {
		return this.nameOfCustomer;
	}

	public void setNameOfCustomer(String nameOfCustomer) {
		this.nameOfCustomer = nameOfCustomer;
	}

	public Double getPercOfTtlSales() {
		return this.percOfTtlSales;
	}

	public void setPercOfTtlSales(Double percOfTtlSales) {
		this.percOfTtlSales = percOfTtlSales;
	}

	public Double getSgst() {
		return this.sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getTtlAmtOfAllInv() {
		return this.ttlAmtOfAllInv;
	}

	public void setTtlAmtOfAllInv(Double ttlAmtOfAllInv) {
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

	public Double getTtlInvVal() {
		return ttlInvVal;
	}

	public void setTtlInvVal(Double ttlInvVal) {
		this.ttlInvVal = ttlInvVal;
	}

	public SummB2bSale() {
		this.id = 0L;
		this.cess = 0.0;
		this.cgst = 0.0;
		this.createdDate = new Date();
		this.gstin = "";
		this.igst = 0.0;
		this.isActive = false;
		this.modifiedDate = new Date();
		this.nameOfCustomer = "";
		this.percOfTtlSales = 0.0;
		this.sgst = 0.0;
		this.ttlAmtOfAllInv = 0.0;
		this.ttlInvVal = 0.0;
		this.ttlInvRaised = 0.0;
		this.ttlTaxVal = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}