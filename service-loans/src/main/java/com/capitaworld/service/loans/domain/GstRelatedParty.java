package com.capitaworld.service.loans.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
  *@auther : Maaz Shaikh
  */
@Entity
@Table(name="gst_related_party")
public class GstRelatedParty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="gst_party_name")
	private String gstPartyName;
	
	@Column(name="sales")
	private Double sales;
	
	@Column(name="purchase")
	private Double purchase;
	
	@Column(name="gstin")
	private String gstin;

	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="pan")
	private String pan;
	
	@Column(name="invoice_value")
	private Double invoiceValue;

	@Column(name="created_by")
	private Long createdBy;
	
	@Column(name="modified_by")
	private Long modifiedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="per_of_invoice_value")
	private Long perOfInvoiceValue;
	
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

	public Long getPerOfInvoiceValue() {
		return perOfInvoiceValue;
	}

	public void setPerOfInvoiceValue(Long perOfInvoiceValue) {
		this.perOfInvoiceValue = perOfInvoiceValue;
	}

	@Override
	public String toString() {
		return "GstRelatedParty [id=" + id + ", applicationId=" + applicationId
				+ ", gstPartyName=" + gstPartyName + ", sales=" + sales
				+ ", purchase=" + purchase + ", gstin=" + gstin
				+ ", transactionType=" + transactionType + ", pan=" + pan
				+ ", invoiceValue=" + invoiceValue + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", isActive=" + isActive
				+ ", perOfInvoiceValue=" + perOfInvoiceValue + "]";
	}

	
}
