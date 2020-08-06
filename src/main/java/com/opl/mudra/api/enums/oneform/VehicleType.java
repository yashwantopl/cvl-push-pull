package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum VehicleType {
	
	NEW(1,"New","New"),
	SECOND_HAND(2,"Second Hand","Second Hand");
	
	private final Integer id;
	private final String value;
	private final String description;

	VehicleType(Integer id, String value, String description) {
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
	
	public static VehicleType getById(Integer id) {
		switch (id) {
		case 1:
			return NEW;

		case 2:
			return SECOND_HAND;

		default:
			return null;
		}
	}

	public static VehicleType[] getAll() {
		return VehicleType.values();

	}


}
