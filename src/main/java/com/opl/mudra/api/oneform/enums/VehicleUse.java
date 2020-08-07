package com.opl.mudra.api.oneform.enums;

public enum VehicleUse {


	PERSONAL_USE(1,"Personal Use","PERSONAL_USE"),
	OFFICE_USE(2,"Office Use","OFFICE_USE");
	
	private final Integer id;
	private final String value;
	private final String description;
	
	
	private VehicleUse(Integer id, String value, String description) {
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
	
	public static VehicleUse getById(Integer id) {
		switch (id) {
		case 1:
			return PERSONAL_USE;

		case 2:
			return OFFICE_USE;

		default:
			return null;
		}
	}

	public static VehicleUse[] getAll() {
		return VehicleUse.values();
	}	
}
