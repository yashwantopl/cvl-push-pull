package com.opl.mudra.api.gst.model;

public class MomSales {
	
	private String month;
	
	private String value;
	
	private Boolean isManualEntry;
	
	
	

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

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
