package com.opl.cvl.enums.cvl;

public enum VehicleType {
	NEW(1,"New"),
	USED(2,"Used");
	
	private Integer id;
	private String value;
	
	private VehicleType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static VehicleType getById(Integer id) {
		for(VehicleType vehicleType : VehicleType.values()) {
			if(vehicleType.getId() == id) {
				return vehicleType;
			}
		}
		return null;
	}

	public static VehicleType[] getAll() {
		return VehicleType.values();
	}

	
	
	
}
