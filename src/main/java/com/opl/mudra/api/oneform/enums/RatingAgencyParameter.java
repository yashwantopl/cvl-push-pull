package com.opl.mudra.api.oneform.enums;

public enum RatingAgencyParameter {
	ACUITE_PART(1,"Acuite Part","Acuite Part"),
	BRICKWORK(2,"Brickwork","Brickwork"),
	CARE(3,"CARE","CARE"),
	CRISIL(4,"CRISIL","CRISIL"),
	ICRA(5,"ICRA","ICRA");

	private final Integer id;
	private final String value;
	private final String description;
	RatingAgencyParameter(Integer id, String value, String description) {
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
	public static RatingAgencyParameter getById(Integer id) {
	switch (id) {
	case 1:
		return ACUITE_PART;
	case 2:
		return BRICKWORK;
	case 3:
		return CARE;
	case 4:
		return CRISIL;
	case 5:
		return ICRA;
	default:
		return null;
	}
}
	public static RatingAgencyParameter[] getAll() {
		return RatingAgencyParameter.values();

	}
}