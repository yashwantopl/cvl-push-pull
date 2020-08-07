package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum OccupationStatus {
			SELF_OCCUPIED(1,"Self Occupied","Self Occupied"),
		RENTED(2,"Rented","Rented"),
		VACANT(3,"Vacant","Vacant"),
		UNDER_CONSTRUCTION(4,"Under Construction","Under Construction"),
		OTHERS(5,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		OccupationStatus(Integer id, String value, String description) {
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
		public static OccupationStatus getById(Integer id) {
		switch (id) {
		case 1:
			return SELF_OCCUPIED;
		case 2:
			return RENTED;
		case 3:
			return VACANT;
		case 4:
			return UNDER_CONSTRUCTION;
		case 5:
			return OTHERS;
		default:
			return null;
		}
	}
		public static OccupationStatus[] getAll() {
			return OccupationStatus.values();

		}
}