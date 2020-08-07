package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class SummOfPaymentOfTax implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private Double interest;

	private Boolean isActive;

	private Double lateFee;

	private Date modifiedDate;
	
	private String retPeriod;

	private Double paidThroughItc;

	private Double taxCessPaidInCash;

	private Double taxPayable;

	
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

	public Double getInterest() {
		return this.interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLateFee() {
		return this.lateFee;
	}

	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public Double getPaidThroughItc() {
		return this.paidThroughItc;
	}

	public void setPaidThroughItc(Double paidThroughItc) {
		this.paidThroughItc = paidThroughItc;
	}

	public Double getTaxCessPaidInCash() {
		return this.taxCessPaidInCash;
	}

	public void setTaxCessPaidInCash(Double taxCessPaidInCash) {
		this.taxCessPaidInCash = taxCessPaidInCash;
	}

	public Double getTaxPayable() {
		return this.taxPayable;
	}

	public void setTaxPayable(Double taxPayable) {
		this.taxPayable = taxPayable;
	}

	public SummOfPaymentOfTax() {
		this.id = 0L;
		this.createdDate = new Date();
		this.interest = 0.0;
		this.isActive = false;
		this.lateFee = 0.0;
		this.modifiedDate = new Date();
		this.retPeriod = null;
		this.paidThroughItc = 0.0;
		this.taxCessPaidInCash = 0.0;
		this.taxPayable = 0.0;
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}