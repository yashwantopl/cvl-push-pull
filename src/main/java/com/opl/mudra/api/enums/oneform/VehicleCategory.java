package com.opl.mudra.api.enums.oneform;

/**
 * @author Rohit
 *
 */
public enum VehicleCategory{
	
	TWO_WHEELER(1,"2 Wheeler","TWO_WHEELER"),
	FOUR_WHEELER(2,"4 Wheeler","FOUR_WHEELER");
	
	private final Integer id;
	private final String value;
	private final String description;

	VehicleCategory(Integer id, String value, String description) {
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
	
	public static VehicleCategory getById(Integer id) {
		switch (id) {
		case 1:
			return TWO_WHEELER;

		case 2:
			return FOUR_WHEELER;

		default:
			return null;
		}
	}

	public static VehicleCategory[] getAll() {
		return VehicleCategory.values();

	}


}
