package com.opl.mudra.api.enums.oneform;

public enum VarianceSales {
	SALES_FIFTEEN(1,"Actuals are within 15 percent of projected sales ","Actuals are within 15 percent of projected sales "),
	SALES_THIRTY(2,"Actuals are between 15 to 30 percent of projected sales ","Actuals are between 15 to 30 percent of projected sales "),
	SALES_WITHIN_THIRTY(3,"Actuals are within 30 percent of projected sales ","Actuals are within 30 percent of projected sales "),
	WIDE_VARIANCE(4,"Wide variance - Actuals are more than 50 percent of projected figures ","Wide variance - Actuals are more than 50 percent of projected figures ");
	
		private final Integer id;
		private final String value;
		private final String description;
		VarianceSales(Integer id, String value, String description) {
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
		public static VarianceSales getById(Integer id) {
		switch (id) {
		case 1:
			return SALES_FIFTEEN;
		case 2:
			return SALES_THIRTY;
		case 3:
			return SALES_WITHIN_THIRTY;
		case 4:
			return WIDE_VARIANCE;
		default:
			return null;
		}
	}
		public static VarianceSales[] getAll() {
			return VarianceSales.values();

		}
}