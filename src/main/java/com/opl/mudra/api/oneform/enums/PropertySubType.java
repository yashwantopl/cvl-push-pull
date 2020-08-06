package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum PropertySubType {
			PURCHASE_OF_READY_FLAT_TENAMENT_ROW_HOUSE(3,"Purchase of Ready Flat/Tenament/Row House","Purchase of ready flat/tenament/row house"),
		CONSTRUCTION_OF_BUNGLOW_TENAMENT(4,"Construction of Bunglow/Tenament","Construction of bunglow/tenament"),
		REPAIRING_RENOVATION_OF_FLAT_TENAMENT(5,"Repairing/Renovation of Flat/Tenament","Repairing/Renovation of flat/tenament"),
		PURCHASE_OF_PLOT(6,"Purchase of Plot","Purchase of Plot"),
		OTHERS(7,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		PropertySubType(Integer id, String value, String description) {
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
		public static PropertySubType getById(Integer id) {
		switch (id) {
		case 3:
			return PURCHASE_OF_READY_FLAT_TENAMENT_ROW_HOUSE;
		case 4:
			return CONSTRUCTION_OF_BUNGLOW_TENAMENT;
		case 5:
			return REPAIRING_RENOVATION_OF_FLAT_TENAMENT;
		case 6:
			return PURCHASE_OF_PLOT;
		default:
			return null;
		}
	}
		public static PropertySubType[] getAll() {
			return PropertySubType.values();

		}
}