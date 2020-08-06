package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum RepairType {
			EXTENDING_A_ROOM(2,"Extending a room","Extending a room"),
		MAKING_A_NEW_BALCONY(3,"Making a new balcony","Making a new balcony"),
		PAINTING(4,"Painting","Painting"),
		FLOORING_WORK(5,"Flooring work","Flooring work"),
		ALL_KINDS_OF_PLUMBING(6,"All kinds of plumbing","All kinds of plumbing"),
		EXTERIOR_ELEVATION_WORKS(7,"Exterior elevation works","Exterior elevation works"),
		OTHER(8,"Other","Other");
	
		private final Integer id;
		private final String value;
		private final String description;
		RepairType(Integer id, String value, String description) {
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
		public static RepairType getById(Integer id) {
		switch (id) {
		case 2:
			return EXTENDING_A_ROOM;
		case 3:
			return MAKING_A_NEW_BALCONY;
		case 4:
			return PAINTING;
		case 5:
			return FLOORING_WORK;
		case 6:
			return ALL_KINDS_OF_PLUMBING;
		case 7:
			return EXTERIOR_ELEVATION_WORKS;
		case 8:
			return OTHER;
		default:
			return null;
		}
	}
		public static RepairType[] getAll() {
			return RepairType.values();

		}
}