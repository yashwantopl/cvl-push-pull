package com.opl.mudra.api.oneform.enums;

public enum InternalReturn {
	GRETERTHAN_EIGHTEEN(1,"Greater than 18 %","Greater than 18 %"),
	TWELVE(2,"12.5% to 18 %","12.5% to 18 %"),
	TEN(3,"10% to 12.5 %","10% to 12.5 %"),
	LESSTHEN_TEN(4,"<10%","<10%");
	
		private final Integer id;
		private final String value;
		private final String description;
		InternalReturn(Integer id, String value, String description) {
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
		public static InternalReturn getById(Integer id) {
		switch (id) {
		case 1:
			return GRETERTHAN_EIGHTEEN;
		case 2:
			return TWELVE;
		case 3:
			return TEN;
		case 4:
			return LESSTHEN_TEN;
		default:
			return null;
		}
	}
		public static InternalReturn[] getAll() {
			return InternalReturn.values();

		}
}