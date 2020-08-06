package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum RelationshipTypeHL {
	FATHER(1, "Father", "Father"), 
	MOTHER(2, "Mother", "Mother"), 
	BROTHER(3, "Brother", "Brother"),
	SISTER(4, "Sister", "Sister"),
	SPOUSE(5, "Spouse", "Spouse"),
	BROTHER_IN_LAW(6, "Brother-in-Law", "Brother-in-Law"),
	DAUGHTER(7, "Daughter", "Daughter"),
	DAUGHTER_IN_LAW(8, "Daughter-in-Law", "Daughter-in-Law"),
	FATHER_IN_LAW(9, "Father-in-Law", "Father-in-Law"),
	GRAND_DAUGHTER(10, "Grand-Daughter", "Grand-Daughter"),
	GRAND_FATHER(11, "Grand-Father", "Grand-Father"),
	GRAND_MOTHER(12, "Grand-Mother", "Grand-Mother"),
	GRAND_SON(13, "Grand-Son", "Grand-Son"),
	MOTHER_IN_LAW(14, "Mother-in-Law", "Mother-in-Law"),
	SISTER_IN_LAW(15, "Sister-in-Law", "Sister-in-Law"),
	SON(16, "Son", "Son"),
	SON_IN_LAW(17, "Son-in-Law", "Son-in-Law"),
	OTHERS(18, "Others", "Others");

	private final Integer id;
	private final String value;
	private final String description;

	RelationshipTypeHL(Integer id, String value, String description) {
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

	public static RelationshipTypeHL getById(Integer id) {
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
		case 6:
			return BROTHER_IN_LAW;
		case 7:
			return DAUGHTER;
		case 8:
			return DAUGHTER_IN_LAW;
		case 9:
			return FATHER_IN_LAW;
		case 10:
			return GRAND_DAUGHTER;
		case 11:
			return GRAND_FATHER;
		case 12:
			return GRAND_MOTHER;
		case 13:
			return GRAND_SON;
		case 14:
			return MOTHER_IN_LAW;
		case 15:
			return SISTER_IN_LAW;
		case 16:
			return SON;
		case 17:
			return SON_IN_LAW;
		case 18:
			return OTHERS;
		default:
			return null;
		}
	}

	public static RelationshipTypeHL[] getAll() {
		return RelationshipTypeHL.values();

	}
}