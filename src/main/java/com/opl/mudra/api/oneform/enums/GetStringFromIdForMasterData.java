/**
 * 
 */
package com.opl.mudra.api.oneform.enums;

/**
 * @author nilay.darji
 *
 */
public enum GetStringFromIdForMasterData {
	
	
	CENTRAL_GOV(1,"CentralGovernment","CentralGovernment"),
	STATE_GOV(2,"StateGovernment","StateGovernment"),
	BANK(3,"HomeLoanBank","HomeLoanBank"),
	PSU(4,"Psu","Psu"),
	INSITUTE(5,"EducationalInstitute","EducationalInstitute"),
	INSURANCE_COMP(6,"HomeLoanInsurance","HomeLoanInsurance");
	
	private final Integer id;
	private final String value;
	private final String description;
	
	private GetStringFromIdForMasterData(Integer id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	
}
