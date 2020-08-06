package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum IncomeDetails {
			INCOME_FROM_AGRICULTURE(1,"Income from Agriculture","Income from Agriculture"),
		OTHER_INCOME(2,"Other Income","Other Income");
	
		private final Integer id;
		private final String value;
		private final String description;
		
		IncomeDetails(Integer id, String value, String description) {
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
		public static IncomeDetails getById(Integer id) {
		switch (id) {
		case 1:
			return INCOME_FROM_AGRICULTURE;
		case 2:
			return OTHER_INCOME;
		default:
			return null;
		}
	}
		public static IncomeDetails[] getAll() {
			return IncomeDetails.values();

		}
}