package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum EmployeeWith {
	PARTNERSHIP(2, "Partnership firm", "Partnership firm"), MNC(3, "MNC", "MNC"), PRIVATE_LTD(4, "Private Ltd",
			"Private Ltd"), PROPRIETORSHIP(5, "Proprietorship", "Proprietorship"), PUBLIC_LTD(6, "Public Ltd",
					"Public Ltd"), PUBLIC_SECTOR(7, "Public Sector", "Public Sector"), LIMITED_LIABILITY_PARTNERSHIP(9, "Limited Liability Partnership", "Limited Liability Partnership"), OTHERS(8, "Others", "Others");

	private final Integer id;
	private final String value;
	private final String description;

	EmployeeWith(Integer id, String value, String description) {
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

	public static EmployeeWith getById(Integer id) {
		switch (id) {
		case 2:
			return PARTNERSHIP;
		case 3:
			return MNC;
		case 4:
			return PRIVATE_LTD;
		case 5:
			return PROPRIETORSHIP;
		case 6:
			return PUBLIC_LTD;
		case 7:
			return PUBLIC_SECTOR;
		case 8:
			return OTHERS;
		case 9:
			return LIMITED_LIABILITY_PARTNERSHIP;
		default:
			return null;
		}
	}

	public static EmployeeWith[] getAll() {
		return EmployeeWith.values();

	}
}