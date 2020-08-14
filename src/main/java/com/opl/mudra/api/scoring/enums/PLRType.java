package com.opl.mudra.api.scoring.enums;

public enum PLRType {
	MCLR(1, "MCLR", "Mclr Rate"), EBLR(2, "EBLR", "Eblr Rate"), REPO(3, "REPO", "Repo Rate");

	private final Integer id;
	private final String value;
	private final String description;

	PLRType(Integer id, String value, String description) {
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
	
	public static PLRType getById(Integer id) {
		for(PLRType plrType : PLRType.values()) {
			if(plrType.getId().equals(id)) {
				return plrType;
			}
		}
		return null;
	}
}