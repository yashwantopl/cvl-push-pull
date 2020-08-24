package com.opl.mudra.api.scoring.v4.enums;

public enum ITRType {
	AUDITED(1,"Audited ITR"),
	NO_ITR(2,"No ITR");

	private final Integer id;
	private final String value;

	ITRType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static ITRType getById(Integer id) {
		for(ITRType type : ITRType.values()) {
			if(type.getId() == id) {
				return type;
			}
		}
		return null;
	}

	public static ITRType[] getAll() {
		return ITRType.values();

	}
}
