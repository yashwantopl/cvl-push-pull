package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum PropertyUsedSubType {
			MAIN_RESIDENCE(2,"Main Residence","Main Residence"),
		BUSINESS(3,"Business","Business"),
		RENTING(4,"Renting","Renting");
	
		private final Integer id;
		private final String value;
		private final String description;
		PropertyUsedSubType(Integer id, String value, String description) {
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
		public static PropertyUsedSubType getById(Integer id) {
		switch (id) {
		case 2:
			return MAIN_RESIDENCE;
		case 3:
			return BUSINESS;
		case 4:
			return RENTING;
		default:
			return null;
		}
	}
		public static PropertyUsedSubType[] getAll() {
			return PropertyUsedSubType.values();

		}
}