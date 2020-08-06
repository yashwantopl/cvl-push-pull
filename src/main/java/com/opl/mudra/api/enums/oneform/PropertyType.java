package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum PropertyType {
			COMMERCIAL(1,"Commercial","Commercial"),
		RESIDENTIAL(2,"Residential","Residential"),
		INDUSTRIAL_PLOT(3,"Industrial Plot","Industrial Plot"),
		OTHERS(4,"Others","Others");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		PropertyType(Integer id, String value, String description) {
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
		public static PropertyType getById(Integer id) {
		switch (id) {
		case 1:
			return COMMERCIAL;
		case 2:
			return RESIDENTIAL;
		case 3:
			return INDUSTRIAL_PLOT;
		case 4:
			return OTHERS;
		default:
			return null;
		}
	}
		public static PropertyType[] getAll() {
			return PropertyType.values();

		}
}