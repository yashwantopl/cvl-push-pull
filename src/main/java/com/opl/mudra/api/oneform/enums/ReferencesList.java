package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum ReferencesList {
			REFERENCE_1(1,"Reference 1","Reference 1"),
		REFERENCE_2(2,"Reference 2","Reference 2");
	
		private final Integer id;
		private final String value;
		private final String description;
		ReferencesList(Integer id, String value, String description) {
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
		public static ReferencesList getById(Integer id) {
		switch (id) {
		case 1:
			return REFERENCE_1;
		case 2:
			return REFERENCE_2;
		default:
			return null;
		}
	}
		public static ReferencesList[] getAll() {
			return ReferencesList.values();

		}
}