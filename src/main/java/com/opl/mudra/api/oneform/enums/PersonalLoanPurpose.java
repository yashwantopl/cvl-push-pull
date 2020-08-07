package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum PersonalLoanPurpose {
		BUSINESS(1,"Business","Business"),
		EDUCATION(2,"Education","Education"),
		MARRIAGE(3,"Marriage","Marriage"),
		ASSET_ACQUISITION(4,"Asset acquisition","Asset acquisition"),
		DEBT_CONSOLIDATION(5,"Debt Consolidation","Debt Consolidation"),
		AGRICULTURE(6,"Agriculture","Agriculture"),
		OTHERS(7,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		PersonalLoanPurpose(Integer id, String value, String description) {
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
		public static PersonalLoanPurpose getById(Integer id) {
		switch (id) {
		case 1:
			return BUSINESS;
		case 2:
			return EDUCATION;
		case 3:
			return MARRIAGE;
		case 4:
			return ASSET_ACQUISITION;
		case 5:
			return DEBT_CONSOLIDATION;
		case 6:
			return AGRICULTURE;
		case 7:
			return OTHERS;
		default:
			return null;
		}
	}
		public static PersonalLoanPurpose[] getAll() {
			return PersonalLoanPurpose.values();

		}
}