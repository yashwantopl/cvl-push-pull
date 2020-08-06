package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum LenderType {
			BANK(1,"Bank","Bank"),
		NBFC(2,"NBFC","NBFC"),
		FINANCIALINSTITUTION(3,"Financial Institution","Financial Institution");
		
	
		private final Integer id;
		private final String value;
		private final String description;
		LenderType(Integer id, String value, String description) {
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
		public static LenderType getById(Integer id) {
		switch (id) {
		case 1:
			return BANK;
		case 2:
			return NBFC;
		case 3:
			return FINANCIALINSTITUTION;
		default:
			return null;
		}
	}
		public static LenderType[] getAll() {
			return LenderType.values();

		}
}