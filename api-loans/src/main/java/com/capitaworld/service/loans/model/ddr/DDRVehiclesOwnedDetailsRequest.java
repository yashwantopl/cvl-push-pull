package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRVehiclesOwnedDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String bankName;
	
	private Double vehiclesOwned;

	private String referenceNo;
	
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getVehiclesOwned() {
		return vehiclesOwned;
	}

	public void setVehiclesOwned(Double vehiclesOwned) {
		this.vehiclesOwned = vehiclesOwned;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRVehiclesOwnedDetailsReqeust [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", vehiclesOwned=" + vehiclesOwned + ", referenceNo=" + referenceNo + ", isActive=" + isActive + "]";
	}
	
	

}
