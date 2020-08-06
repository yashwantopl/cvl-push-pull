package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GuardianDetails {

	@JsonProperty("GuardianName")
	private String guardianName;
		
	@JsonProperty("GuardianType")
	private String guardianType;

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianType() {
		return guardianType;
	}

	public void setGuardianType(String guardianType) {
		this.guardianType = guardianType;
	}
	
	
}
