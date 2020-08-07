package com.opl.mudra.api.analyzer.model.yodlee;

import java.util.List;

public class AdditionalDataSet {
	private String name;

	private List<Attribute> attribute = null;

	private String updateEligibility;
	private String lastUpdated;
	private String additionalStatus;
	private String lastUpdateAttempt;
	
	

	public String getUpdateEligibility() {
		return updateEligibility;
	}

	public void setUpdateEligibility(String updateEligibility) {
		this.updateEligibility = updateEligibility;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getAdditionalStatus() {
		return additionalStatus;
	}

	public void setAdditionalStatus(String additionalStatus) {
		this.additionalStatus = additionalStatus;
	}

	public String getLastUpdateAttempt() {
		return lastUpdateAttempt;
	}

	public void setLastUpdateAttempt(String lastUpdateAttempt) {
		this.lastUpdateAttempt = lastUpdateAttempt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}

}
