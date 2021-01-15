package com.opl.cvl.enums.cvl;

public enum VehicleCarryType {
	PERSON(1,"Person"),
	LOAD(2,"Load");
	
	private Integer id;
	private String value;
	
	private VehicleCarryType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	public static VehicleCarryType getById(Integer id) {
		for(VehicleCarryType vehicleCarryType : VehicleCarryType.values()) {
			if(vehicleCarryType.getId() == id) {
				return vehicleCarryType;
			}
		}
		return null;
	}
	
	public static VehicleCarryType[] getAll() {
		return VehicleCarryType.values();
	}
}
