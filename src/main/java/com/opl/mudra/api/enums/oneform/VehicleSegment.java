package com.opl.mudra.api.enums.oneform;

public enum VehicleSegment {
	
	
	ELECTRIC_NON_CONVENTIONAL_TWO_WHEELER_LOAN(1,"Electric two wheeler Loan","Electric two wheeler Loan"),
	SMALL(3,"Small","Small"),
	MID(4,"Mid","Mid"), 
	LUXURY(5,"Luxury","Luxury"), 
	SUV_MUV(6,"SUV/MUV","SUV/MUV"), 
//	IMPORTED_CAR(7,"Imported Car","Imported Car"), 
//	INDIAN_CAR(8,"Indian Car","Indian Car"), 
//	ELECTRIC_NON_CONVENTIONAL_CAR(9,"Electric Car","Electric Car"), 
	OTHERS(2,"Others","Others"); 
	
	
	private final Integer id;
	private final String value;
	private final String description;

	VehicleSegment(Integer id, String value, String description) {
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
	
	
	public static VehicleSegment getById(Integer id) {
		switch (id) {
		case 1:
			return ELECTRIC_NON_CONVENTIONAL_TWO_WHEELER_LOAN;

		case 2:
			return OTHERS;
			
		case 3:
			return SMALL;
			
		case 4:
			return MID;
			
		case 5:
			return LUXURY;
			
		case 6:
			return SUV_MUV;
			
//		case 7:
//			return IMPORTED_CAR;
			
//		case 8:
//			return INDIAN_CAR;
//			
//		case 9:
//			return ELECTRIC_NON_CONVENTIONAL_CAR;

		default:
			return null;
		}
	}

	
	public static VehicleSegment[] getAll() {
		return VehicleSegment.values();
	}
	
}
