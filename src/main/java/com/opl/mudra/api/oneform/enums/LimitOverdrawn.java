package com.opl.mudra.api.oneform.enums;

public enum LimitOverdrawn {
	LESSTHAN_SEVEN(1,"Less than 7","Less than 7"),
	SEVEN_TO_ELEVEN(2,"7  to 11 times ","7  to 11 times "),
	TWELVE_TO_FIFTEEN(3,"12 to 15 times ","12 to 15 times "),
	GRETERTHAN_FIFTEEN(4,"Greater than 15 times","Greater than 15 times");
	
		private final Integer id;
		private final String value;
		private final String description;
		LimitOverdrawn(Integer id, String value, String description) {
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
		public static LimitOverdrawn getById(Integer id) {
		switch (id) {
		case 1:
			return LESSTHAN_SEVEN;
		case 2:
			return SEVEN_TO_ELEVEN;
		case 3:
			return TWELVE_TO_FIFTEEN;
		case 4:
			return GRETERTHAN_FIFTEEN;
		default:
			return null;
		}
	}
		public static LimitOverdrawn[] getAll() {
			return LimitOverdrawn.values();

		}
}