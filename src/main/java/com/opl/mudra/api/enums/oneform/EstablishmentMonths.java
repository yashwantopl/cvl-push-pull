package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum EstablishmentMonths {
	JANUARY(1, "January", "January"), FEBRUARY(2, "February", "February"), MARCH(3, "March", "March"), APRIL(4, "April",
			"April"), MAY(5, "May", "May"), JUNE(6, "June", "June"), JULY(7, "July", "July"), AUGUST(8, "August",
					"August"), SEPTEMBER(9, "September", "September"), OCTOBER(10, "October",
							"October"), NOVEMBER(11, "November", "November"), DECEMBER(12, "December", "December");

	private final Integer id;
	private final String value;
	private final String description;

	EstablishmentMonths(Integer id, String value, String description) {
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

	public static EstablishmentMonths getById(Integer id) {
		switch (id) {
		case 1:
			return JANUARY;
		case 2:
			return FEBRUARY;
		case 3:
			return MARCH;
		case 4:
			return APRIL;
		case 5:
			return MAY;
		case 6:
			return JUNE;
		case 7:
			return JULY;
		case 8:
			return AUGUST;
		case 9:
			return SEPTEMBER;
		case 10:
			return OCTOBER;
		case 11:
			return NOVEMBER;
		case 12:
			return DECEMBER;
		default:
			return null;
		}
	}

	public static EstablishmentMonths[] getAll() {
		return EstablishmentMonths.values();

	}
}