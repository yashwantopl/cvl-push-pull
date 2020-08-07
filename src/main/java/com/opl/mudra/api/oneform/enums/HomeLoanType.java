package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum HomeLoanType {
			NEW(1,"New","New"),
			TAKE_OVER_OF_EXISTING(2,"Take Over Of Existing","Take Over Of Existing");
		private final Integer id;
		private final String value;
		private final String description;
		HomeLoanType(Integer id, String value, String description) {
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
		public static HomeLoanType getById(Integer id) {
		switch (id) {
		case 1:
			return NEW;
		case 2:
			return TAKE_OVER_OF_EXISTING;
		default:
			return null;
		}
	}
		public static HomeLoanType[] getAll() {
			return HomeLoanType.values();

		}
}