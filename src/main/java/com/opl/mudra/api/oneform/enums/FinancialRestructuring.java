package com.opl.mudra.api.oneform.enums;

public enum FinancialRestructuring {
	NO_HISTORY(1,"No history of any Financial Debt restructuring ","No history of any Financial Debt restructuring "),
	DEBT_RESTRUCTURED(2,"Debt restructured more than 36 months back, consequent to which financial position has stabilised. ","Debt restructured more than 36 months back, consequent to which financial position has stabilised. 	"),
	DEBT_RESTRUCTURED_THIRTYSIX_MONTHS(3,"Debt has been restructured within the last 36 months.","Debt has been restructured within the last 36 months."),
	DEBT_RESTRUCTURED_EIGHTEEN_MONTHS(4,"Debt has been restructured within the last 18 months.","Debt has been restructured within the last 18 months.");
	
		private final Integer id;
		private final String value;
		private final String description;
		FinancialRestructuring(Integer id, String value, String description) {
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
		public static FinancialRestructuring getById(Integer id) {
		switch (id) {
		case 1:
			return NO_HISTORY;
		case 2:
			return DEBT_RESTRUCTURED;
		case 3:
			return DEBT_RESTRUCTURED_THIRTYSIX_MONTHS;
		case 4:
			return DEBT_RESTRUCTURED_EIGHTEEN_MONTHS;
		default:
			return null;
		}
	}
		public static FinancialRestructuring[] getAll() {
			return FinancialRestructuring.values();

		}
}