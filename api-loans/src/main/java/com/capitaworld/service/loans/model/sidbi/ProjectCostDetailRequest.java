package com.capitaworld.service.loans.model.sidbi;

public class ProjectCostDetailRequest {
	
	private Long applicationId;
	private Long particularId;
	private String otherParticular;
	private Double alreadyIncurred;
	private Double toBeIncurred;
	private Double totalCost;
	
	
	public Long getApplicationId() {
		return applicationId;
	}
	public Long getParticularId() {
		return particularId;
	}
	public String getOtherParticular() {
		return otherParticular;
	}
	public Double getAlreadyIncurred() {
		return alreadyIncurred;
	}
	public Double getToBeIncurred() {
		return toBeIncurred;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public void setParticularId(Long particularId) {
		this.particularId = particularId;
	}
	public void setOtherParticular(String otherParticular) {
		this.otherParticular = otherParticular;
	}
	public void setAlreadyIncurred(Double alreadyIncurred) {
		this.alreadyIncurred = alreadyIncurred;
	}
	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	

}
