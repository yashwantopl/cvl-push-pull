package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fs_machine_details_mudra_loan")
@NamedQuery(name = "MachineDetailMudraLoan.findAll", query = "SELECT p FROM MachineDetailMudraLoan p")
public class MachineDetailMudraLoan implements Serializable {
	
	
	private static final long serialVersionUID = 1L;	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column (name = "type_of_machine")
	private String typeOfMachine;
	
	@Column (name = "purpose_of_machine")
	private String purposeOfMachine;
	
	@Column (name = "name_of_supplier")
	private String nameOfSupplier;
	
	@Column (name = "cost_of_machine")
	private Double costOfMachinery;
	
	@Column (name = "application_id")
	private Long applicationId;
	
	@Column (name = "is_active")
	private Boolean isActive;
	
	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private Long createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeOfMachine() {
		return typeOfMachine;
	}

	public void setTypeOfMachine(String typeOfMachine) {
		this.typeOfMachine = typeOfMachine;
	}

	public String getPurposeOfMachine() {
		return purposeOfMachine;
	}

	public void setPurposeOfMachine(String purposeOfMachine) {
		this.purposeOfMachine = purposeOfMachine;
	}

	public String getNameOfSupplier() {
		return nameOfSupplier;
	}

	public void setNameOfSupplier(String nameOfSupplier) {
		this.nameOfSupplier = nameOfSupplier;
	}

	public Double getCostOfMachinery() {
		return costOfMachinery;
	}

	public void setCostOfMachinery(Double costOfMachinery) {
		this.costOfMachinery = costOfMachinery;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
}
