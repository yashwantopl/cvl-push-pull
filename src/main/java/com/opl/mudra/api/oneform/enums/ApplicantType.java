package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum ApplicantType {
	
	CO_APPLICANT(2,"Co-Applicant","CO_APPLICANT"),
	GUARANTOR(3,"Guarantor","GUARANTOR");
	
	private final Integer id;
	private final String value;
	private final String description;

	ApplicantType(Integer id, String value, String description) {
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
	
	public static ApplicantType getById(Integer id) {
		switch (id) {
		case 2:
			return CO_APPLICANT;

		case 3:
			return GUARANTOR;

		default:
			return null;
		}
	}

	public static ApplicantType[] getAll() {
		return ApplicantType.values();

	}

}
