package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum Occupation {
			DOCTOR(2,"Doctor","Doctor"),
		CA_CS(4,"CA/CS","CA/CS"),
		LAWYER(5,"Lawyer","Lawyer"),
		ARCHITECT(6,"Architect","Architect"),
		ENGINEER(7,"Engineer","Engineer"),
		CONSULTANT(8,"Consultant","Consultant"),
		AGRICULTURIST(9,"Agriculturist","Agriculturist"),
		OTHERS(10,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		Occupation(Integer id, String value, String description) {
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
		public static Occupation getById(Integer id) {
		switch (id) {
		case 2:
			return DOCTOR;
		case 4:
			return CA_CS;
		case 5:
			return LAWYER;
		case 6:
			return ARCHITECT;
		case 7:
			return ENGINEER;
		case 8:
			return CONSULTANT;
		case 9:
			return AGRICULTURIST;
		case 10:
			return OTHERS;
		default:
			return null;
		}
	}
		public static Occupation[] getAll() {
			return Occupation.values();

		}
}