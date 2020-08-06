package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum ReligionRetailMst {
			HINDU(2,"Hindu","Hindu"),
		MUSLIM(3,"Muslim","Muslim"),
		CHRISTIAN(4,"Christian","Christian"),
		SIKH(5,"Sikh","Sikh"),
		ZOROASTRIAN(6,"Zoroastrian","Zoroastrian"),
		JAIN(7,"Jain","Jain"),
		OTHERS(8,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		ReligionRetailMst(Integer id, String value, String description) {
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
		public static ReligionRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return HINDU;
		case 3:
			return MUSLIM;
		case 4:
			return CHRISTIAN;
		case 5:
			return SIKH;
		case 6:
			return ZOROASTRIAN;
		case 7:
			return JAIN;
		case 8:
			return OTHERS;
		default:
			return null;
		}
	}
		public static ReligionRetailMst[] getAll() {
			return ReligionRetailMst.values();

		}
}