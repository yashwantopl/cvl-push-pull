package com.opl.mudra.api.oneform.enums;

public enum CumulativeOverdrawn {
	LESSTHAN_SEVEN(1,"Less than 7","Less than 7"),
	LESSTHAN_THIRTY(2,"Less than 30 ","Less than 30 "),
	LESSTHAN_THIRTY_TO_SIXTY(3,"From 30 to 60 days","From 30 to 60 days"),
	GRETERTHAN_SIXTY(4,"Greater than 60 days","Greater than 60 days");
	
		private final Integer id;
		private final String value;
		private final String description;
		CumulativeOverdrawn(Integer id, String value, String description) {
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
		public static CumulativeOverdrawn getById(Integer id) {
		switch (id) {
		case 1:
			return LESSTHAN_SEVEN;
		case 2:
			return LESSTHAN_THIRTY;
		case 3:
			return LESSTHAN_THIRTY_TO_SIXTY;
		case 4:
			return GRETERTHAN_SIXTY;
		default:
			return null;
		}
	}
		public static CumulativeOverdrawn[] getAll() {
			return CumulativeOverdrawn.values();

		}
}