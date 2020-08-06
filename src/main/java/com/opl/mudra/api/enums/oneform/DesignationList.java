package com.opl.mudra.api.enums.oneform;

public enum DesignationList {
	SENIOR_MANAGEMENT(1,"Senior Management"),
	MIDDLE_MANAGEMENT(2,"Middle Management"),
	JUNIOR_CLERICAL(3,"Junior"),
	SELF_EMPLOYED(4,"Self Employed"),
	PENSIONERS(5,"Pensioners"),
	OTHERS(6,"Others");
	
	private final Integer id;
	private final String value;
	
	DesignationList(Integer id, String value) {
	  this.id = id;
	  this.value = value;
	}

	public Integer getId() {
	  return id;
	}

	public String getValue() {
	  return value; 
	}
	
	public static DesignationList getById(Integer id) {
		switch (id) {
		case 1:
			return SENIOR_MANAGEMENT;
		case 2:
			return MIDDLE_MANAGEMENT;
		case 3:
			return JUNIOR_CLERICAL;
		case 4:
			return SELF_EMPLOYED;
		case 5:
			return PENSIONERS;
		case 6:
			return OTHERS;
		default:
			return null;
		}
	}
	
	public static DesignationList[] getAll() {
	  return DesignationList.values();
	}
}
