package com.opl.mudra.api.oneform.enums;

public enum AutoDetailPurposeofLoan {

	
	FOUR_WHEELER_LOAN_FOR_SMALL_CAR(1,"Four Wheeler Loan for Small Car","Four Wheeler Loan for Small Car",2), 
	FOUR_WHEELER_LOAN_FOR_MID_CAR(2,"Four Wheeler Loan for Mid Car","Four Wheeler Loan for Mid Car", 2),
	FOUR_WHEELER_LOAN_FOR_LUXURY_CAR(3,"Four Wheeler Loan for Luxury Car","Four Wheeler Loan for Luxury Car",2),
	FOUR_WHEELER_LOAN_FOR_SUV_MUV(4,"Four Wheeler Loan for SUV/MUV","Four Wheeler Loan for SUV/MUV", 2),
	ELECTRIC_NON_CONVENTIONAL_CAR_LOAN(5,"Electric Car Loan","Electric Car Loan" , 2),
	TWO_WHEELER_LOAN(6,"Two Wheeler Loan","Two Wheeler Loan", 1),
	ELECTRIC_NON_CONVENTIONAL_TWO_WHEELER_LOAN(7,"Electric two wheeler Loan","Electric two wheeler Loan", 1);
	
	private final Integer id;
	private final String value;
	private final String description;
	private final Integer vehicleCategory;

	
	private AutoDetailPurposeofLoan(Integer id, String value, String description, Integer vehicleCategory) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.vehicleCategory = vehicleCategory;
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
	
	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public static AutoDetailPurposeofLoan getById(Integer id) {
		switch (id) {
		case 1:
			return FOUR_WHEELER_LOAN_FOR_SMALL_CAR;

		case 2:
			return FOUR_WHEELER_LOAN_FOR_MID_CAR;
			
		case 3:
			return FOUR_WHEELER_LOAN_FOR_LUXURY_CAR;
			
		case 4:
			return FOUR_WHEELER_LOAN_FOR_SUV_MUV;
			
		case 5:
			return ELECTRIC_NON_CONVENTIONAL_CAR_LOAN;
			
		case 6:
			return TWO_WHEELER_LOAN;
			
		case 7:
			return ELECTRIC_NON_CONVENTIONAL_TWO_WHEELER_LOAN;
		
		default:
			return null;
		}
	}

	
	public static AutoDetailPurposeofLoan[] getAll() {
		return AutoDetailPurposeofLoan.values();
	}
	
	
}
