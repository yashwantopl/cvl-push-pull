package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
}
