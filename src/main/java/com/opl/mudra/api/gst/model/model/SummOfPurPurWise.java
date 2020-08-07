package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SummOfPurPurWise implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private double cess;

	private double cgst;

	private Date createdDate;

	private double igst;

	private String invoiceType;

	private double invoiceValue;

	private byte isActive;

	private Date modifiedDate;

	private double perOfTotalPurchase;

	private double sgst;

	private double taxableValue;

	private double totalInvoice;

	private GstCalcMappingTable gstCalcMappingTable;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCess() {
		return this.cess;
	}

	public void setCess(double cess) {
		this.cess = cess;
	}

	public double getCgst() {
		return this.cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getIgst() {
		return this.igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public double getInvoiceValue() {
		return this.invoiceValue;
	}

	public void setInvoiceValue(double invoiceValue) {
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

	public double getPerOfTotalPurchase() {
		return this.perOfTotalPurchase;
	}

	public void setPerOfTotalPurchase(double perOfTotalPurchase) {
		this.perOfTotalPurchase = perOfTotalPurchase;
	}

	public double getSgst() {
		return this.sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getTaxableValue() {
		return this.taxableValue;
	}

	public void setTaxableValue(double taxableValue) {
		this.taxableValue = taxableValue;
	}

	public double getTotalInvoice() {
		return this.totalInvoice;
	}

	public void setTotalInvoice(double totalInvoice) {
		this.totalInvoice = totalInvoice;
	}

	public GstCalcMappingTable getGstCalcMappingTable() {
		return this.gstCalcMappingTable;
	}

	public void setGstCalcMappingTable(GstCalcMappingTable gstCalcMappingTable) {
		this.gstCalcMappingTable = gstCalcMappingTable;
	}

	public SummOfPurPurWise() {
		this.id = "";
		this.cess = 0.0;
		this.cgst = 0.0;
		this.createdDate = new Date();
		this.igst = 0.0;
		this.invoiceType = "";
		this.invoiceValue = 0.0;

		this.modifiedDate = new Date();
		this.perOfTotalPurchase = 0.0;
		this.sgst = 0.0;
		this.taxableValue = 0.0;
		this.totalInvoice = 0.0;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}