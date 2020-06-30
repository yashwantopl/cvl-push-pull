package com.capitaworld.service.loans.model.corporate;

public class MachineDetailMudraLoanRequestResponse {

	private Long id; 
	
	private String typeOfMachine;
	
	private String purposeOfMachine;
	
	private String nameOfSupplier;
	
	private Double costOfMachinery;
	
	private String costOfMachineryString;
	
	private Long applicationId;

	public String getCostOfMachineryString() {
		return costOfMachineryString;
	}

	public void setCostOfMachineryString(String costOfMachineryString) {
		this.costOfMachineryString = costOfMachineryString;
	}

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
