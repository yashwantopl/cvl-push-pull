package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum AutoLoanPurposeType {
	USE_OF_VEHICLE(1,"Use of Vehicle","Use of Vehicle" , null , null),
	NEW_FOUR_WHEELER_LOAN(2,"New Four Wheeler Loan","New Four Wheeler Loan" , 1, 2),
	NEW_TWO_WHEELER_LOAN(3,"New Two Wheeler Loan","New Two Wheeler Loan", 1 , 1),
	SECOND_HAND_FOUR_WHEELER_LOAN(4,"Used Four Wheeler Loan","Used Four Wheeler Loan", 2 , 2),
	SECOND_HAND_TWO_WHEELER_LOAN(5,"Used Two Wheeler Loan","Used Two Wheeler Loan", 2, 1);

	private final Integer id;
	private final String value;
	private final String description;
	private final Integer vehicleType;
	private final Integer vehicleCategory;
	
	AutoLoanPurposeType(Integer id, String value, String description, Integer vehicleType , Integer vehicleCategory) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.vehicleType = vehicleType;
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
	
	public Integer getVehicleType() {
		return vehicleType;
	}

	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public static AutoLoanPurposeType getById(Integer id) {
		switch (id) {
		case 1:
			return USE_OF_VEHICLE;
		case 2:
			return NEW_FOUR_WHEELER_LOAN;
		case 3:
			return NEW_TWO_WHEELER_LOAN;
		case 4:
			return SECOND_HAND_FOUR_WHEELER_LOAN;
		case 5:
			return SECOND_HAND_TWO_WHEELER_LOAN;
		default:
			return null;
		}
	}
	
	public static AutoLoanPurposeType[] getFsList() {
		AutoLoanPurposeType[] list = new AutoLoanPurposeType[4];
		list[0] = NEW_FOUR_WHEELER_LOAN;
		list[1] = NEW_TWO_WHEELER_LOAN;
		list[2] = SECOND_HAND_FOUR_WHEELER_LOAN;
		list[3] = SECOND_HAND_TWO_WHEELER_LOAN;
		return list;
	}
	
	public static AutoLoanPurposeType[] getAll() {
		return AutoLoanPurposeType.values();

	}
}