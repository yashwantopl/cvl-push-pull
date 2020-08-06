package com.opl.mudra.api.oneform.enums;

public enum VehicleEngineVolume {

	UPTO_1800_CC(1,"Upto 1800 CC","UPTO_1800_CC"),
	MORE_THAN_1800_CC(2,"More than 1800 CC","MORE_THAN_1800_CC");
	
	private final Integer id;
	private final String value;
	private final String description;

	VehicleEngineVolume(Integer id, String value, String description) {
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
	
	public static VehicleEngineVolume getById(Integer id) {
		switch (id) {
		case 1:
			return UPTO_1800_CC;

		case 2:
			return MORE_THAN_1800_CC;

		default:
			return null;
		}
	}

	public static VehicleEngineVolume[] getAll() {
		return VehicleEngineVolume.values();
	}
}
