package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum IndustryType {
			MANUFACTURING(2,"Manufacturing","Manufacturing"),
		CONSTRUCTION(9,"Construction","Construction"),
		NBFC(10,"NBFC","NBFC"),
		NON_PROFIT_ORGANIZATION(11,"Non Profit Organization","Non Profit Organization"),
		MICRO_ENTERPRISE(12,"Micro Enterprise","Micro Enterprise"),
		TRADING(13,"Trading","Trading"),
		SERVICE(14,"Service","Service"),
		GOVERNMENT(15,"Government","Government"),
		OTHERS(16,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		
		IndustryType(Integer id, String value, String description) {
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
		public static IndustryType getById(Integer id) {
		switch (id) {
		case 2:
			return MANUFACTURING;
		case 9:
			return CONSTRUCTION;
		case 10:
			return NBFC;
		case 11:
			return NON_PROFIT_ORGANIZATION;
		case 12:
			return MICRO_ENTERPRISE;
		case 13:
			return TRADING;
		case 14:
			return SERVICE;
		case 15:
			return GOVERNMENT;
		case 16:
			return OTHERS;
		default:
			return null;
		}
	}
		public static IndustryType[] getAll() {
			return IndustryType.values();

		}
}