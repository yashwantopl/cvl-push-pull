package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum OccupationHL {
	DOCTOR(1, "Doctor", "Doctor"), LAWYER(2, "Lawyer", "Lawyer"), CA(3, "CA", "CA"), CS(4, "CS", "CS"),
	ICWA(5, "ICWA", "ICWA"), ARCHITECT(6, "Architect", "Architect"), BUSINESSMAN(7, "Businessman", "Businessman"),
	AGRICULTURIST_PENSIONER_OTHERS(9, "Agriculturist / Pensioner / Others", "Agriculturist / Pensioner / Others"),OTHERS(8, "Others", "Others");

	private final Integer id;
	private final String value;
	private final String description;

	OccupationHL(Integer id, String value, String description) {
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

	public static OccupationHL getById(Integer id) {
		switch (id) {
		case 1:
			return DOCTOR;
		case 2:
			return LAWYER;
		case 3:
			return CA;
		case 4:
			return CS;
		case 5:
			return ICWA;
		case 6:
			return ARCHITECT;
		case 7:
			return BUSINESSMAN;
		case 8:
			return OTHERS;
		case 9:
			return AGRICULTURIST_PENSIONER_OTHERS;
		default:
			return null;
		}
	}

	public static OccupationHL[] getAll() {
		return OccupationHL.values();

	}
}