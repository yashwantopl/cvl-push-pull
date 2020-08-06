package com.opl.mudra.api.enums.oneform;

public enum CollateralType {

	LAND(1,"Land"),
	SELF_OCCUPIED_HOUSE(2,"Self-occupied House"),
	RENTED_HOME(3,"Owned Home (Given on Rent / Vacant)"),
	FACTORY_PREMISES(4,"Factory Premises"),
	OTHER(5,"Other");
	
	private final Integer id;
	private final String value;
	
	CollateralType(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static CollateralType[] getAll() {
		return CollateralType.values();
	}
	
}
