package com.opl.mudra.api.oneform.enums;

public enum ComplianceConditions {
	COMPLIED_FULLY(1,"Complied fully - Security and financial covenants","Complied fully - Security and financial covenants"),
	COMPLIED_WITH_CREATION(2,"Complied with creation of security","Complied with creation of security"),
	COMPLIED_WITH_FINANCIAL(3,"Complied with financial covenants only","Complied with financial covenants only"),
	NOT_COMPLIED(4,"The sanctioned/disbursement conditions have not been complied","The sanctioned/disbursement conditions have not been complied");
	
		private final Integer id;
		private final String value;
		private final String description;
		ComplianceConditions(Integer id, String value, String description) {
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
		public static ComplianceConditions getById(Integer id) {
		switch (id) {
		case 1:
			return COMPLIED_FULLY;
		case 2:
			return COMPLIED_WITH_CREATION;
		case 3:
			return COMPLIED_WITH_FINANCIAL;
		case 4:
			return NOT_COMPLIED;
		default:
			return null;
		}
	}
		public static ComplianceConditions[] getAll() {
			return ComplianceConditions.values();

		}
}