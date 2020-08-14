package com.opl.mudra.api.user.model;

import java.io.Serializable;

public class UserLongitudeLatitudeRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer userType;
	private Double longitude;
	private Double latitude;
	private Long userId;
	
	public UserLongitudeLatitudeRequest(){
		super();
	}
	
	public UserLongitudeLatitudeRequest(Long id,Long userId, Integer userType, Double longitude, Double latitude) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
