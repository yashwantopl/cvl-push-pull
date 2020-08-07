package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


public class SummOfEligItcNetAvail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double centralTax;

	private Double cess;

	private Date createdDate;

	private String details;

	private BigInteger gstCalcMappingTableId;

	private Double integratedTax;

	private Boolean isActive;

	private Date modifiedDate;

	private String retPeriod;

	private Double stateTax;

	private Double total;

	private String typeOfSumm;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCentralTax() {
		return this.centralTax;
	}

	public void setCentralTax(Double centralTax) {
		this.centralTax = centralTax;
	}

	public Double getCess() {
		return this.cess;
	}

	public void setCess(Double cess) {
		this.cess = cess;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigInteger getGstCalcMappingTableId() {
		return this.gstCalcMappingTableId;
	}

	public void setGstCalcMappingTableId(BigInteger gstCalcMappingTableId) {
		this.gstCalcMappingTableId = gstCalcMappingTableId;
	}

	public Double getIntegratedTax() {
		return this.integratedTax;
	}

	public void setIntegratedTax(Double integratedTax) {
		this.integratedTax = integratedTax;
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

	public Double getStateTax() {
		return this.stateTax;
	}

	public void setStateTax(Double stateTax) {
		this.stateTax = stateTax;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getTypeOfSumm() {
		return this.typeOfSumm;
	}

	public void setTypeOfSumm(String typeOfSumm) {
		this.typeOfSumm = typeOfSumm;
	}

	public SummOfEligItcNetAvail() {
		this.id = 0L;
		this.centralTax = 0.0;
		this.cess = 0.0;
		this.createdDate = new Date();
		this.details ="";
		this.gstCalcMappingTableId =  BigInteger.valueOf(0);
		this.integratedTax = 0.0;
		this.isActive = false;
		this.modifiedDate = new Date();
		this.retPeriod = null;
		this.stateTax = 0.0;
		this.total = 0.0;
		this.typeOfSumm="";
	}
}