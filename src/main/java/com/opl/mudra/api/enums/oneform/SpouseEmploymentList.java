package com.opl.mudra.api.enums.oneform;

public enum SpouseEmploymentList {

	EMPLOYED(1,"Employed"),
	UNEMPLOYED(2,"Unemployed");
	
	private final Integer id;
	private final String value;

	
	SpouseEmploymentList(Integer id, String value){
		this.id = id;
		this.value = value;
	}
	 
	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static SpouseEmploymentList getById(Integer id) {
		switch (id) {
		case 1:
			return EMPLOYED;
		case 2:
			return UNEMPLOYED;
		default:
			return null;
		}
	}

	public static SpouseEmploymentList[] getAll() {
		return SpouseEmploymentList.values();

	}
	
}
