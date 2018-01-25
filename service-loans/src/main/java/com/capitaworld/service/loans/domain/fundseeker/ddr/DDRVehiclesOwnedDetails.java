package com.capitaworld.service.loans.domain.fundseeker.ddr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by : harshit
 * The persistent class for the fs_ddr_vehicles_owned_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_vehicles_owned_details")
@NamedQuery(name="DDRVehiclesOwnedDetails.findAll", query="SELECT a FROM DDRVehiclesOwnedDetails a")
public class DDRVehiclesOwnedDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;

	@Column(name="bank_name")
	private String bankName;
	
	@Column(name ="vehicles_owned")
	private Double vehiclesOwned;

	@Column(name ="reference_no")
	private String referenceNo;
	
	@Column(name = "is_active")
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
		return "DDRVehiclesOwnedDetails [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", vehiclesOwned=" + vehiclesOwned + ", referenceNo=" + referenceNo + ", isActive=" + isActive + "]";
	}
	
	

}
