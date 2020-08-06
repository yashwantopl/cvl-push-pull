package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum OverseasNetwork {
			ASIA_(1,"Asia ","Asia "),
		AFRICA(2,"Africa","Africa"),
		ANTARCTICA(3,"Antarctica","Antarctica"),
		AUSTRALIA(4,"Australia","Australia"),
		EUROPE(5,"Europe","Europe"),
		NORTH_AMERICA(6,"North America","North America"),
		SOUTH_AMERICA(7,"South America","South America");
	
		private final Integer id;
		private final String value;
		private final String description;
		OverseasNetwork(Integer id, String value, String description) {
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
		public static OverseasNetwork getById(Integer id) {
		switch (id) {
		case 1:
			return ASIA_;
		case 2:
			return AFRICA;
		case 3:
			return ANTARCTICA;
		case 4:
			return AUSTRALIA;
		case 5:
			return EUROPE;
		case 6:
			return NORTH_AMERICA;
		case 7:
			return SOUTH_AMERICA;
		default:
			return null;
		}
	}
		public static OverseasNetwork[] getAll() {
			return OverseasNetwork.values();

		}
}