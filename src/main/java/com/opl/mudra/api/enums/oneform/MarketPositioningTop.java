package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum MarketPositioningTop {
			TOP_5(1,"Top 5","Top 5"),
		TOP_20(2,"Top 20","Top 20"),
		OTHER(3,"Other","Other");
	
		private final Integer id;
		private final String value;
		private final String description;
		MarketPositioningTop(Integer id, String value, String description) {
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
		public static MarketPositioningTop getById(Integer id) {
		switch (id) {
		case 1:
			return TOP_5;
		case 2:
			return TOP_20;
		case 3:
			return OTHER;
		default:
			return null;
		}
	}
		public static MarketPositioningTop[] getAll() {
			return MarketPositioningTop.values();

		}
}