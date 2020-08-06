package com.opl.mudra.api.oneform.enums;

public enum StatusClearances {
	MAJOR_CLEARANCES(1,"All major clearances obtained and no problem envisaged in obtaining remaining clearances","All major clearances obtained and no problem envisaged in obtaining remaining clearances"),
	MOST_CLEARANCES(2,"Most clearances obtained and not much problems envisaged in obtaining remaining clearances","Most clearances obtained and not much problems envisaged in obtaining remaining clearances"),
	A_FEW_CLEARANCES(3,"A few clearances obtained as on date, and there could be some hurdles in obtaining remaining clearances","A few clearances obtained as on date, and there could be some hurdles in obtaining remaining clearances"),
	KEY_CLEARANCES(4,"A few key clearances are yet to be obtained","A few key clearances are yet to be obtained"),
	NA(5,"NA","NA");
	
		private final Integer id;
		private final String value;
		private final String description;
		StatusClearances(Integer id, String value, String description) {
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
		public static StatusClearances getById(Integer id) {
		switch (id) {
		case 1:
			return MAJOR_CLEARANCES;
		case 2:
			return MOST_CLEARANCES;
		case 3:
			return A_FEW_CLEARANCES;
		case 4:
			return KEY_CLEARANCES;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static StatusClearances[] getAll() {
			return StatusClearances.values();

		}
}