package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class RequirementsAndAvailabilityRawMaterialsDetailResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String availability;
	
	private String leadTime;

	private String measurementUnitQuantity;
	
	private String name;

	private String quality;

	private String sources;

	public RequirementsAndAvailabilityRawMaterialsDetailResponse() {
		super();
	}

	public RequirementsAndAvailabilityRawMaterialsDetailResponse(String availability, String leadTime,
			String measurementUnitQuantity, String name, String quality, String sources) {
		super();
		this.availability = availability;
		this.leadTime = leadTime;
		this.measurementUnitQuantity = measurementUnitQuantity;
		this.name = name;
		this.quality = quality;
		this.sources = sources;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getMeasurementUnitQuantity() {
		return measurementUnitQuantity;
	}

	public void setMeasurementUnitQuantity(String measurementUnitQuantity) {
		this.measurementUnitQuantity = measurementUnitQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}
	
	
	


}
