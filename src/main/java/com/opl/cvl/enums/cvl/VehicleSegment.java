package com.opl.cvl.enums.cvl;

public enum VehicleSegment {
	
	MHCV(1,"MHCV"),
	LCV(2,"LCV"),
	SCV(3,"SCV"),
	ICV(4,"ICV"),
	TRACTOR(5,"Tractor"),
	TRAILOR(6,"Trailor"),
	TRIPPER(7,"Tripper");
	
	private Integer id;
	private String value;
	
	private VehicleSegment(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	public static VehicleSegment[] getAll() {
		return VehicleSegment.values();
	}
	
	public static VehicleSegment getById(Integer id) {
		for(VehicleSegment vehicleSegment : VehicleSegment.values()) {
			if(vehicleSegment.getId() == id) {
				return vehicleSegment;
			}
		}
		return null;
	}

}
