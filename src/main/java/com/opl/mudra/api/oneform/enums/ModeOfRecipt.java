package com.opl.mudra.api.oneform.enums;

public enum ModeOfRecipt {
	CASH(1, "Cash", "Cash"), BANK(2, "Bank", "Bank");

	private final Integer id;
	private final String value;
	private final String description;

	ModeOfRecipt(Integer id, String value, String description) {
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

	public static ModeOfRecipt getById(Integer id) {
		switch (id) {
		case 1:
			return CASH;
		case 2:
			return BANK;
		default:
			return null;
		}
	}

	public static ModeOfRecipt[] getAll() {
		return ModeOfRecipt.values();

	}
}
