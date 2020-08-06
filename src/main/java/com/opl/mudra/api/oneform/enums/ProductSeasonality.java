package com.opl.mudra.api.oneform.enums;

public enum ProductSeasonality {
	NO_SEASONALITY(1,"Product does not face any seasonality either from demand or supply side","Product does not face any seasonality either from demand or supply side"),
	SEASONAL(2,"Product is seasonal from supply side but not from demand side","Product is seasonal from supply side but not from demand side"),
	PRODUCT_DEMAND_SIDE (3,"Product is seasonal from demand side but not from supply side","Product is seasonal from demand side but not from supply side"),
	PRODUCT_IS_SEASONAL(4,"Product is seasonal from both demand and supply side","Product is seasonal from both demand and supply side");
	
		private final Integer id;
		private final String value;
		private final String description;
		ProductSeasonality(Integer id, String value, String description) {
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
		public static ProductSeasonality getById(Integer id) {
		switch (id) {
		case 1:
			return NO_SEASONALITY;
		case 2:
			return SEASONAL;
		case 3:
			return PRODUCT_DEMAND_SIDE;
		case 4:
			return PRODUCT_IS_SEASONAL;
		default:
			return null;
		}
	}
		public static ProductSeasonality[] getAll() {
			return ProductSeasonality.values();

		}
}