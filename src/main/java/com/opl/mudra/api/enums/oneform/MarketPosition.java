package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum MarketPosition {
			MANY(1,"Many","Many"),
		FEW(2,"Few","Few"),
		MONOPOLY(3,"Monopoly","Monopoly");
	
		private final Integer id;
		private final String value;
		private final String description;
		MarketPosition(Integer id, String value, String description) {
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
		public static MarketPosition getById(Integer id) {
		switch (id) {
		case 1:
			return MANY;
		case 2:
			return FEW;
		case 3:
			return MONOPOLY;
		default:
			return null;
		}
	}
		public static MarketPosition[] getAll() {
			return MarketPosition.values();

		}
}