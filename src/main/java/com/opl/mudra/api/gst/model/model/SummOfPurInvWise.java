package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummOfPurInvWise implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Double cess;

	private Double rec;

	private Double cgst;

	private Date createdDate;

	private String gstinOfSupplier;

	private Double igst;

	private String invoiceType;

	private Double invoiceValue;

	private byte isActive;

	private Date modifiedDate;

	private Double perOfTotalPurchase;

	private String placeOfSupply;

	private Double sgst;

	private Double taxableValue;

	private Double totalInvoice;

	private String retPeriod;

	private GstCalcMappingTable gstCalcMappingTable;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getGstinOfSupplier() {
		return this.gstinOfSupplier;
	}

	public void setGstinOfSupplier(String gstinOfSupplier) {
		this.gstinOfSupplier = gstinOfSupplier;
	}

	public Double getIgst() {
		return this.igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Double getInvoiceValue() {
		return invoiceValue != null ? invoiceValue : 0.0;
	}

	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getPerOfTotalPurchase() {
		return perOfTotalPurchase != null ? perOfTotalPurchase : 0.0;
	}

	public void setPerOfTotalPurchase(Double perOfTotalPurchase) {
		this.perOfTotalPurchase = perOfTotalPurchase;
	}

	public String getPlaceOfSupply() {
		return this.placeOfSupply;
	}

	public void setPlaceOfSupply(String placeOfSupply) {
		this.placeOfSupply = placeOfSupply;
	}

	public Double getSgst() {
		return this.sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getTaxableValue() {
		return taxableValue != null ? taxableValue : 0.0;
	}

	public void setTaxableValue(Double taxableValue) {
		this.taxableValue = taxableValue;
	}

	public Double getTotalInvoice() {
		return totalInvoice != null ? totalInvoice : 0.0;
	}

	public void setTotalInvoice(Double totalInvoice) {
		this.totalInvoice = totalInvoice;
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

	public Double getRec() {
		return rec != null ? rec : 0.0;
	}

	public void setRec(Double rec) {
		this.rec = rec;
	}

	public SummOfPurInvWise() {
		this.id = "";
		this.cess = 0.0;
		this.rec = 0.0;
		this.cgst = 0.0;
		this.createdDate = new Date();
		this.gstinOfSupplier = "";
		this.igst = 0.0;
		this.invoiceType = "";
		this.invoiceValue = 0.0;

		this.modifiedDate = new Date();
		this.perOfTotalPurchase = 0.0;
		this.placeOfSupply ="";
		this.sgst = 0.0;
		this.taxableValue = 0.0;
		this.totalInvoice = 0.0;
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}