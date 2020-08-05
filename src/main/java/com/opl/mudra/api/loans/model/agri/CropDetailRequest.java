package com.opl.mudra.api.loans.model.agri;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.model.common.AuditActivityRequest;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CropDetailRequest extends AuditActivityRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String year;
	
	private Long applicationId;
	
	private Integer seasonId;
	
	private Integer cropId;
	
	private String mspQuintal;
	
	private Double landSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getCropId() {
		return cropId;
	}

	public void setCropId(Integer cropId) {
		this.cropId = cropId;
	}

	public String getMspQuintal() {
		return mspQuintal;
	}

	public void setMspQuintal(String mspQuintal) {
		this.mspQuintal = mspQuintal;
	}

	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}
}

