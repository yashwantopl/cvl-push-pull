package com.opl.mudra.api.enums.oneform;

public enum OperatingMargins {
	CURRENT_BUSINESS(1,"Current business competition scenario and tax regime not expected to lead to decline in gross margins","Current business competition scenario and tax regime not expected to lead to decline in gross margins"),
	BUSINESS_LESSTHAN_TWO(2,"Current business competition scenario/ changes in tax regime may lead to marginal decline (<2%) in gross margins","Current business competition scenario/ changes in tax regime may lead to marginal decline (<2%) in gross margins"),
	BUSINESS_LESSTHAN_FIVE(3,"Current business competition scenario/ changes in tax regime may lead to decline (~5%) in gross margins","Current business competition scenario/ changes in tax regime may lead to decline (~5%) in gross margins"),
	BUSINESS_GRETERTHAN_TEN(4,"Current business competition scenario/ changes in tax regime may lead to substantial decline (>10%) in gross margins","Current business competition scenario/ changes in tax regime may lead to substantial decline (>10%) in gross margins");
	
		private final Integer id;
		private final String value;
		private final String description;
		OperatingMargins(Integer id, String value, String description) {
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
		public static OperatingMargins getById(Integer id) {
		switch (id) {
		case 1:
			return CURRENT_BUSINESS;
		case 2:
			return BUSINESS_LESSTHAN_TWO;
		case 3:
			return BUSINESS_LESSTHAN_FIVE;
		case 4:
			return BUSINESS_GRETERTHAN_TEN;
		default:
			return null;
		}
	}
		public static OperatingMargins[] getAll() {
			return OperatingMargins.values();

		}
}