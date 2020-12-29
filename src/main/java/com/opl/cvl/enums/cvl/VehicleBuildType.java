package com.opl.cvl.enums.cvl;

public enum VehicleBuildType {
	
	FULLY(1, "Fully built"),
	ASSEMBLED(2,"To be assembled");
	
	private Integer id;
	private String value;
	
	private VehicleBuildType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	public static VehicleBuildType[] getAll() {
		return VehicleBuildType.values();
	}

}
