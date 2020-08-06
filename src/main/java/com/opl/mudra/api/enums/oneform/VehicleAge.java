package com.opl.mudra.api.enums.oneform;

public enum VehicleAge {
	
	ONE_YEAR(1,"1","1"),
	TWO_YEAR(2,"2","2"),
	THREE_YEAR(3,"3","3"),
	FOUR_YEAR(4,"4","4"),
	FIVE_YEAR(5,"5","5"),
	SIX_YEAR(6,"6","6"),
	SEVEN_YEAR(7,"7","7"),
	EIGHTH_YEAR(8,"8","8"),
	NINE_YEAR(9,"9","9"),
	TEN_YEAR(10,"10","10");
	
	private final Integer id;
	private final String value;
	private final String description;

	VehicleAge(Integer id, String value, String description) {
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
	
	public static VehicleAge getById(Integer id) {
		switch (id) {
		case 1:
			return ONE_YEAR;

		case 2:
			return TWO_YEAR;
			
		case 3:
			return THREE_YEAR;
			
		case 4:
			return FOUR_YEAR;
			
		case 5:
			return FIVE_YEAR;
			
		case 6:
			return SIX_YEAR;
			
		case 7:
			return SEVEN_YEAR;
			
		case 8:
			return EIGHTH_YEAR;
			
		case 9:
			return NINE_YEAR;
			
		case 10:
			return TEN_YEAR;
		
		default:
			return null;
		}
	}

	public static VehicleAge[] getAll() {
		return VehicleAge.values();

	}


}
