package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

public class LongitudeLatitudeRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private Double longitude;
	private Double latitude;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
}
