package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum FundseekerType {
			CORPORATE(1,"Corporate","Corporate"),
		RETAIL(2,"Retail","Retail");
	
		private final Integer id;
		private final String value;
		private final String description;
		FundseekerType(Integer id, String value, String description) {
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
		public static FundseekerType getById(Integer id) {
		switch (id) {
		case 1:
			return CORPORATE;
		case 2:
			return RETAIL;
		default:
			return null;
		}
	}
		public static FundseekerType[] getAll() {
			return FundseekerType.values();

		}
}