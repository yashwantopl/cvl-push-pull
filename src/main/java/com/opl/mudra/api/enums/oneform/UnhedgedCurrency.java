package com.opl.mudra.api.enums.oneform;

public enum UnhedgedCurrency {
	FOREX_EXPOSURE(1,"<25% ","<25% "),
	LESSTHAN_TWENTYFIVE(2,">=25% to <50%",">=25% to <50%"),
	GRETERTHAN_FIFTY(3,"<=50% to <75%","<=50% to <75%"),
	LESSTHAN_SEVENTYFIVE(4,">=75%",">=75%"),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		UnhedgedCurrency(Integer id, String value, String description) {
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
		public static UnhedgedCurrency getById(Integer id) {
		switch (id) {
		case 1:
			return FOREX_EXPOSURE;
		case 2:
			return LESSTHAN_TWENTYFIVE;
		case 3:
			return GRETERTHAN_FIFTY;
		case 4:
			return LESSTHAN_SEVENTYFIVE;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static UnhedgedCurrency[] getAll() {
			return UnhedgedCurrency.values();

		}
}