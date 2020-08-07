package com.opl.mudra.api.scoring.enums;

public enum EnvironmentCategory {
	MANUFACTURING_RED(1,"Manufacturing Red","Manufacturing Red"),
	MANUFACTURING_ORANGE(2,"Manufacturing Orange","Manufacturing Orange"),
	MANUFACTURING_GREEN(3,"Manufacturing Green","Manufacturing Green"),
	MANUFACTURING_WHITE(4,"Manufacturing White","Manufacturing White"),
	SERVICE(5,"Service","Service"),
	TRADING(6,"Trading","Trading");

		private final Integer id;
		private final String value;
		private final String description;
		EnvironmentCategory(Integer id, String value, String description) {
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
		public static EnvironmentCategory getById(Integer id) {
		switch (id) {
		case 1:
			return MANUFACTURING_RED;
		case 2:
			return MANUFACTURING_ORANGE;
		case 3:
			return MANUFACTURING_GREEN;
		case 4:
			return MANUFACTURING_WHITE;
		case 5:
			return SERVICE;
		case 6:
			return TRADING;
		default:
			return null;
		}
	}
		public static EnvironmentCategory[] getAll() {
			return EnvironmentCategory.values();

		}
}