package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * The persistent class for the industry_sector_details database table.
 * 
 */

public class IndustrySector implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long industryId;

	private Long sectorId;

	private String name;

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}