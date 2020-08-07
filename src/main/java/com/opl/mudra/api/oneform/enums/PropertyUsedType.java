package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum PropertyUsedType {
			OLD(2,"Old","Old"),
		NEW(3,"New","New");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		PropertyUsedType(Integer id, String value, String description) {
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
		public static PropertyUsedType getById(Integer id) {
		switch (id) {
		case 2:
			return OLD;
		case 3:
			return NEW;
		default:
			return null;
		}
	}
		public static PropertyUsedType[] getAll() {
			return PropertyUsedType.values();

		}
}