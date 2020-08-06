package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum FundType {
			EQUITY(1,"Equity","Equity"),
		DEBT(2,"Debt","Debt");
	
		private final Integer id;
		private final String value;
		private final String description;
		FundType(Integer id, String value, String description) {
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
		public static FundType getById(Integer id) {
		switch (id) {
		case 1:
			return EQUITY;
		case 2:
			return DEBT;
		default:
			return null;
		}
	}
		public static FundType[] getAll() {
			return FundType.values();

		}
}