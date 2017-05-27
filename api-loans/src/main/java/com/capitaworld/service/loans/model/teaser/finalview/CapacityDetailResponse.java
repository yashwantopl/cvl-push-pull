package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class CapacityDetailResponse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentInstalledCapacity;

	private String currentOperatingLevel;

	private String futureCapacity;

	private String measurementForCurrentInstalledCapacity;

	private String measurementForFutureCapacity;
	
	private String productName;
	
	
	

	public CapacityDetailResponse(String currentInstalledCapacity, String currentOperatingLevel, String futureCapacity,
			String measurementForCurrentInstalledCapacity, String measurementForFutureCapacity, String productName) {
		super();
		this.currentInstalledCapacity = currentInstalledCapacity;
		this.currentOperatingLevel = currentOperatingLevel;
		this.futureCapacity = futureCapacity;
		this.measurementForCurrentInstalledCapacity = measurementForCurrentInstalledCapacity;
		this.measurementForFutureCapacity = measurementForFutureCapacity;
		this.productName = productName;
	}
	
	

	public CapacityDetailResponse() {
		super();
	}



	public String getCurrentInstalledCapacity() {
		return currentInstalledCapacity;
	}

	public void setCurrentInstalledCapacity(String currentInstalledCapacity) {
		this.currentInstalledCapacity = currentInstalledCapacity;
	}

	public String getCurrentOperatingLevel() {
		return currentOperatingLevel;
	}

	public void setCurrentOperatingLevel(String currentOperatingLevel) {
		this.currentOperatingLevel = currentOperatingLevel;
	}

	public String getFutureCapacity() {
		return futureCapacity;
	}

	public void setFutureCapacity(String futureCapacity) {
		this.futureCapacity = futureCapacity;
	}

	public String getMeasurementForCurrentInstalledCapacity() {
		return measurementForCurrentInstalledCapacity;
	}

	public void setMeasurementForCurrentInstalledCapacity(String measurementForCurrentInstalledCapacity) {
		this.measurementForCurrentInstalledCapacity = measurementForCurrentInstalledCapacity;
	}

	public String getMeasurementForFutureCapacity() {
		return measurementForFutureCapacity;
	}

	public void setMeasurementForFutureCapacity(String measurementForFutureCapacity) {
		this.measurementForFutureCapacity = measurementForFutureCapacity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	

}
