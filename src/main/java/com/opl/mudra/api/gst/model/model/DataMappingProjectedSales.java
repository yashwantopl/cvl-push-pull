package com.opl.mudra.api.gst.model.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DataMappingProjectedSales {
	
	private String returnPeriod;
	
	private Double values;
	
	private Boolean isManualEntry;

	/**
	 * @return the returnPeriod
	 */
	public String getReturnPeriod() {
		return returnPeriod;
	}

	/**
	 * @param returnPeriod the returnPeriod to set
	 */
	public void setReturnPeriod(String returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	/**
	 * @return the values
	 */
	public Double getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Double values) {
		this.values = values;
	}

	/**
	 * @return the isManualEntry
	 */
	public Boolean getIsManualEntry() {
		return isManualEntry;
	}

	/**
	 * @param isManualEntry the isManualEntry to set
	 */
	public void setIsManualEntry(Boolean isManualEntry) {
		this.isManualEntry = isManualEntry;
	}

	public DataMappingProjectedSales(String returnPeriod, Double values, Boolean isManualEntry) {
		super();
		this.returnPeriod = returnPeriod;
		this.values = values;
		this.isManualEntry = isManualEntry;
	}

	public DataMappingProjectedSales() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataMappingProjectedSales [returnPeriod=" + returnPeriod + ", values=" + values + ", isManualEntry="
				+ isManualEntry + "]";
	}
	
	

}
