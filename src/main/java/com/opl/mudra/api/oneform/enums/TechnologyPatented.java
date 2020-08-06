package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum TechnologyPatented {
			REGISTERED(1,"Registered","Registered"),
		APPLIED_FOR(2,"Applied For","Applied For"),
		NOT_PATENTED(3,"Not Patented","Not Patented");
	
		private final Integer id;
		private final String value;
		private final String description;
		TechnologyPatented(Integer id, String value, String description) {
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
		public static TechnologyPatented getById(Integer id) {
		switch (id) {
		case 1:
			return REGISTERED;
		case 2:
			return APPLIED_FOR;
		case 3:
			return NOT_PATENTED;
		default:
			return null;
		}
	}
		public static TechnologyPatented[] getAll() {
			return TechnologyPatented.values();

		}
}