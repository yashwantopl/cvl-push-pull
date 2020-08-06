package com.opl.mudra.api.oneform.enums;

public enum ChequesReturned {
	NONE(1,"None ","None "),
	LESSTHAN_SEVEN(2,"Less than 7 times ","Less than 7 times "),
	LESSTHAN_SEVEN_TO_TWELVE(3,"7 to 12 times ","7 to 12 times "),
	GRETERTHAN_TWELVE(4,"Greater than 12 times ","Greater than 12 times ");
	
		private final Integer id;
		private final String value;
		private final String description;
		ChequesReturned(Integer id, String value, String description) {
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
		public static ChequesReturned getById(Integer id) {
		switch (id) {
		case 1:
			return NONE;
		case 2:
			return LESSTHAN_SEVEN;
		case 3:
			return LESSTHAN_SEVEN_TO_TWELVE;
		case 4:
			return GRETERTHAN_TWELVE;
		default:
			return null;
		}
	}
		public static ChequesReturned[] getAll() {
			return ChequesReturned.values();

		}
}