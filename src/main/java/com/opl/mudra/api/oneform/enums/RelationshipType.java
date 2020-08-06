package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum RelationshipType {
			FATHER(1,"Father","Father"),
		MOTHER(2,"Mother","Mother"),
		BROTHER(3,"Brother","Brother"),
		SISTER(4,"Sister","Sister"),
		SPOUSE(5,"Spouse","Spouse");
	
		private final Integer id;
		private final String value;
		private final String description;
		RelationshipType(Integer id, String value, String description) {
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
		public static RelationshipType getById(Integer id) {
		switch (id) {
		case 1:
			return FATHER;
		case 2:
			return MOTHER;
		case 3:
			return BROTHER;
		case 4:
			return SISTER;
		case 5:
			return SPOUSE;
		default:
			return null;
		}
	}
		public static RelationshipType[] getAll() {
			return RelationshipType.values();

		}
}