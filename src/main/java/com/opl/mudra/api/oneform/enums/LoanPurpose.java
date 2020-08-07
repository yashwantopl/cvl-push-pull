package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum LoanPurpose {
			CLOSURE_OF_LOANS_FACILITIES(1,"Closure of Loans/Facilities","Closure of Loans/Facilities"),
		BUSINESS(2,"Business","Business"),
		PERSONAL_REQUIREMENT(3,"Personal Requirement","Personal Requirement"),
		PROPERTY_PURCHASE_RENOVATION(4,"Property Purchase/Renovation","Property Purchase/Renovation"),
		OTHERS(5,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		
		LoanPurpose(Integer id, String value, String description) {
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
		public static LoanPurpose getById(Integer id) {
		switch (id) {
		case 1:
			return CLOSURE_OF_LOANS_FACILITIES;
		case 2:
			return BUSINESS;
		case 3:
			return PERSONAL_REQUIREMENT;
		case 4:
			return PROPERTY_PURCHASE_RENOVATION;
		case 5:
			return OTHERS;
		default:
			return null;
		}
	}
		public static LoanPurpose[] getAll() {
			return LoanPurpose.values();

		}
}