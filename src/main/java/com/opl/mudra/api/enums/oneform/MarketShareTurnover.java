package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum MarketShareTurnover {
			LESS_THAN_5(1,"less than 5%","less than 5%"),
		IN_5_TO_15(2,"5% to 15%","5% to 15%"),
		IN_15_TO_30(3,"15% to 30%","15% to 30%"),
		MORE_THAN_30(4,"more than 30%","more than 30%");
	
		private final Integer id;
		private final String value;
		private final String description;
		MarketShareTurnover(Integer id, String value, String description) {
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
		public static MarketShareTurnover getById(Integer id) {
		switch (id) {
		case 1:
			return LESS_THAN_5;
		case 2:
			return IN_5_TO_15;
		case 3:
			return IN_15_TO_30;
		case 4:
			return MORE_THAN_30;
		default:
			return null;
		}
	}
		public static MarketShareTurnover[] getAll() {
			return MarketShareTurnover.values();

		}
}