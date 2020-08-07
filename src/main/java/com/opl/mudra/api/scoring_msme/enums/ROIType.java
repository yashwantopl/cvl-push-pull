package com.opl.mudra.api.scoring_msme.enums;

public enum ROIType {
	MCLR(1, "MCLR", "MCLR"),
	EBLR(2, "EBLR", "EBLR");

	private final Integer id;
	private final String value;
	private final String description;

	ROIType(Integer id, String value, String description) {
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

	public static ROIType getById(Integer id) {
		switch (id) {
		case 1:
			return MCLR;
		case 2:
			return EBLR;
		default:
			return null;
		}
	}

	public static ROIType[] getAll() {
		return ROIType.values();

	}
}