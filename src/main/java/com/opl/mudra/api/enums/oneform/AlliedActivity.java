package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum AlliedActivity {

	POULTRY(2, "Poultry", "POULTRY"), 
	DAIRY(3, "Dairy", "DAIRY"), 
	OTHERS(4, "Others", "OTHERS"), 
	NONE(5, "None",	"NONE");

	private final Integer id;
	private final String value;
	private final String description;

	AlliedActivity(Integer id, String value, String description) {
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

	public static AlliedActivity getById(Integer id) {
		switch (id) {
		case 2:
			return POULTRY;

		case 3:
			return DAIRY;

		case 4:
			return OTHERS;
			
		case 5:
			return NONE;

		default:
			return null;
		}
	}

	public static AlliedActivity[] getAll() {
		return AlliedActivity.values();

	}

}
