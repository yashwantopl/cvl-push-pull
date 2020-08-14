package com.opl.mudra.api.scoring.v4.enums;

public enum NumberofYear {
	ONE(1, "1", "ONE"),
	TWO(2, "2", "TWO"),
	THREE(3, "3", "THREE");

	private final Integer id;
	private final String value;
	private final String description;

	NumberofYear(Integer id, String value, String description) {
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

	public static NumberofYear getById(Integer id) {
		switch (id) {
		case 1:
			return ONE;
		case 2:
			return TWO;
		case 3:
			return THREE;
		default:
			return null;
		}
	}

	public static NumberofYear[] getAll() {
		return NumberofYear.values();

	}
}