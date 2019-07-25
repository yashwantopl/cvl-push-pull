package com.capitaworld.service.loans.model;

import java.util.Date;


/**
  *@auther : Maaz Shaikh
  */
public class GstRelatedPartyRequest {
	
    private Long id;
	
	private Long applicationId;
	
	private String gstPartyName;
	
	private Double sales;
	
	private Double purchase;
	
	private String gstin;

	private String transactionType;
	
	private Long perOfInvoiceValue;
	
	private String pan;
	
	private Double invoiceValue;

	private Long createdBy;
	
	private Long modifiedBy;
	
	private Date createdDate;
	
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getGstPartyName() {
		return gstPartyName;
	}

	public void setGstPartyName(String gstPartyName) {
		this.gstPartyName = gstPartyName;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Double getPurchase() {
		return purchase;
	}

	public void setPurchase(Double purchase) {
		this.purchase = purchase;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(Double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getPerOfInvoiceValue() {
		return perOfInvoiceValue;
	}

	public void setPerOfInvoiceValue(Long perOfInvoiceValue) {
		this.perOfInvoiceValue = perOfInvoiceValue;
	}

	@Override
	public String toString() {
		return "GstRelatedPartyRequest [id=" + id + ", applicationId="
				+ applicationId + ", gstPartyName=" + gstPartyName + ", sales="
				+ sales + ", purchase=" + purchase + ", gstin=" + gstin
				+ ", transactionType=" + transactionType
				+ ", perOfInvoiceValue=" + perOfInvoiceValue + ", pan=" + pan
				+ ", invoiceValue=" + invoiceValue + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
	
}
