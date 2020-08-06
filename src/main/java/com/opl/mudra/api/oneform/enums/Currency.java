package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Currency {
	
	EUROS(1,"Euros","Euros"),
	RUPEES(2,"Rupees","Rupees"),
	POUNDS(3,"Pounds","Pounds"),
	DIRHAMS(4,"Dirhams","Dirhams"),
	DOLLARS(5,"Dollars","Dollars");
	
	private final Integer id;
	private final String value;
	private final String description;

	Currency(Integer id, String value, String description) {
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
	
	public static Currency getById(Integer id) {
		switch (id) {
		case 1:
			return EUROS;

		case 2:
			return RUPEES;

		case 3:
			return POUNDS;

		case 4:
			return DIRHAMS;
			
		case 5:
			return DOLLARS;

		default:
			return null;
		}
	}

	public static Currency[] getAll() {
		return Currency.values();

	}

}
