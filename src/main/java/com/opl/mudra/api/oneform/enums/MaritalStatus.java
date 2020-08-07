package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum MaritalStatus {
			MARRIED(2,"Married","Married"),
		SINGLE(3,"Single","Single"),
		WIDOWED(4,"Widowed","Widowed"),
		DIVORCED(5,"Divorced","Divorced"),
		SEPARATED(6,"Separated","Separated");
		private final Integer id;
		private final String value;
		private final String description;
		MaritalStatus(Integer id, String value, String description) {
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
		public static MaritalStatus getById(Integer id) {
		switch (id) {
		case 2:
			return MARRIED;
		case 3:
			return SINGLE;
		case 4:
			return WIDOWED;
		case 5:
			return DIVORCED;
		case 6:
			return SEPARATED;
		default:
			return null;
		}
	}
		public static MaritalStatus[] getAll() {
			return MaritalStatus.values();

		}
}