package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDROfficeDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String areaInSqft;
	
	private Integer noEmployee;

	private String anyOtherShowroom;
	
	private Integer officeType;
	
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public String getAreaInSqft() {
		return areaInSqft;
	}

	public void setAreaInSqft(String areaInSqft) {
		this.areaInSqft = areaInSqft;
	}

	public Integer getNoEmployee() {
		return noEmployee;
	}

	public void setNoEmployee(Integer noEmployee) {
		this.noEmployee = noEmployee;
	}

	public String getAnyOtherShowroom() {
		return anyOtherShowroom;
	}

	public void setAnyOtherShowroom(String anyOtherShowroom) {
		this.anyOtherShowroom = anyOtherShowroom;
	}

	public Integer getOfficeType() {
		return officeType;
	}

	public void setOfficeType(Integer officeType) {
		this.officeType = officeType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDROfficeDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", areaInSqft=" + areaInSqft
				+ ", noEmployee=" + noEmployee + ", anyOtherShowroom=" + anyOtherShowroom + ", officeType=" + officeType
				+ ", isActive=" + isActive + "]";
	}

	
}
