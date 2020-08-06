package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Competence {
	
	LATE(1,"Projects running late","LATE"),
	BEFORE_SECH(2,"Operational before Scheduled timeline","BEFORE_SECH"),
	NA(3,"NA","NA");
	
	private final Integer id;
	private final String value;
	private final String description;

	Competence(Integer id, String value, String description) {
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
	
	public static Competence getById(Integer id) {
		switch (id) {
		case 1:
			return LATE;

		case 2:
			return BEFORE_SECH;
			
		case 3:
			return NA;

		default:
			return null;
		}
	}

	public static Competence[] getAll() {
		return Competence.values();

	}



}
