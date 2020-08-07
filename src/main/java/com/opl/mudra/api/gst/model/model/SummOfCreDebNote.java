package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SummOfCreDebNote implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private double cess;

	private double cgst;

	private Date createdDate;

	private String debitCreditDate;

	private double debitCreditNum;

	private double igst;

	private String invoiceDate;

	private double invoiceNum;

	private byte isActive;

	private Date modifiedDate;

	private double noteValue;

	private String preGstRegimeDrCrNote;

	private String reasIssuDrCrNote;

	private double sgst;

	private double taxRate;

	private double taxableValue;

	private String typeOfNote;

	private String retPeriod;

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

	public String getDebitCreditDate() {
		return this.debitCreditDate;
	}

	public void setDebitCreditDate(String debitCreditDate) {
		this.debitCreditDate = debitCreditDate;
	}

	public double getDebitCreditNum() {
		return this.debitCreditNum;
	}

	public void setDebitCreditNum(double debitCreditNum) {
		this.debitCreditNum = debitCreditNum;
	}

	public double getIgst() {
		return this.igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public String getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public double getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(double invoiceNum) {
		this.invoiceNum = invoiceNum;
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

	public double getNoteValue() {
		return this.noteValue;
	}

	public void setNoteValue(double noteValue) {
		this.noteValue = noteValue;
	}

	public String getPreGstRegimeDrCrNote() {
		return this.preGstRegimeDrCrNote;
	}

	public void setPreGstRegimeDrCrNote(String preGstRegimeDrCrNote) {
		this.preGstRegimeDrCrNote = preGstRegimeDrCrNote;
	}

	public String getReasIssuDrCrNote() {
		return this.reasIssuDrCrNote;
	}

	public void setReasIssuDrCrNote(String reasIssuDrCrNote) {
		this.reasIssuDrCrNote = reasIssuDrCrNote;
	}

	public double getSgst() {
		return this.sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getTaxableValue() {
		return this.taxableValue;
	}

	public void setTaxableValue(double taxableValue) {
		this.taxableValue = taxableValue;
	}

	public String getTypeOfNote() {
		return this.typeOfNote;
	}

	public void setTypeOfNote(String typeOfNote) {
		this.typeOfNote = typeOfNote;
	}

	public GstCalcMappingTable getGstCalcMappingTable() {
		return this.gstCalcMappingTable;
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public void setGstCalcMappingTable(GstCalcMappingTable gstCalcMappingTable) {
		this.gstCalcMappingTable = gstCalcMappingTable;
	}

	public SummOfCreDebNote() {
		this.id = "";
		this.cess = 0.0;
		this.cgst = 0.0;
		this.createdDate = new Date();
		this.debitCreditDate = "";
		this.debitCreditNum = 0.0;
		this.igst = 0.0;
		this.invoiceDate = "";
		this.invoiceNum = 0.0;

		this.modifiedDate = new Date();
		this.noteValue = 0.0;
		this.preGstRegimeDrCrNote = "";
		this.reasIssuDrCrNote ="";
		this.sgst = 0.0;
		this.taxRate = 0.0;
		this.taxableValue = 0.0;
		this.typeOfNote = "";
		this.retPeriod = null;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}