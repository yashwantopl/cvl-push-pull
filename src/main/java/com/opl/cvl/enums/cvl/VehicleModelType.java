package com.opl.cvl.enums.cvl;

public enum VehicleModelType {

	HATCHBACK(1,"Hatchback"),
	SEDAN(2,"Sedan");
	
	private Integer id;
	private String value;
	
	private VehicleModelType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
	public static VehicleModelType[] getAll() {
		return VehicleModelType.values();
	}
	
	
	
}
