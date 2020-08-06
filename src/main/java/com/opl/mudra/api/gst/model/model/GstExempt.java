package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;


public class GstExempt implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createdDate;

	private Double interStateSupp;

	private Double intraStateSupp;

	private Boolean isActive;

	private Boolean isgst;
	
	private String retPeriod;
	
	
	

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

	private Date modifiedDate;
	
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

	public Double getInterStateSupp() {
		return this.interStateSupp;
	}

	public void setInterStateSupp(Double interStateSupp) {
		this.interStateSupp = interStateSupp;
	}

	public Double getIntraStateSupp() {
		return this.intraStateSupp;
	}

	public void setIntraStateSupp(Double intraStateSupp) {
		this.intraStateSupp = intraStateSupp;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsgst() {
		return this.isgst;
	}

	public void setIsgst(Boolean isgst) {
		this.isgst = isgst;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public GstExempt() {
		this.id = -1L;
		this.createdDate = new Date();
		this.interStateSupp = 0.0;
		this.intraStateSupp = 0.0;
		this.isActive = false;
		this.isgst = false;
		this.retPeriod = null;
		this.modifiedDate = new Date();
		this.gstCalcMappingTable = new GstCalcMappingTable();
	}
}