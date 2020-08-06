package com.opl.mudra.api.enums.oneform;

public enum ResidenceTypeHomeLoan {
	
	OWNED(1, "Owned", "Owned"), 
	PARENT_OWNED_SAME_PLACE(2, "Parent Owned- Same place", "Parent Owned- Same place"), 
	SPOUSE_OWNED(3, "Spouse Owned", "Spouse Owned"), 
	RENTED_HOUSE_FOR_LESS_THAN_5_YEARS(4, "Rented house for less than 5 Years", "Rented house for less than 5 Years"),
	RENTED_HOUSE_FOR_MORE_THAN_5_YEARS(5, "Rented house for more than 5 Years", "Rented house for more than 5 Years"),
	PARENT_OWNED_OTHER_PLACE(6, "Parent owned-other place", "Parent owned-other place");

	private final Integer id;
	private final String value;
	private final String description;

	ResidenceTypeHomeLoan(Integer id, String value, String description) {
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

	public static ResidenceTypeHomeLoan getById(Integer id) {
		switch (id) {
		case 1:
			return OWNED;
		case 2:
			return PARENT_OWNED_SAME_PLACE;
		case 3:
			return SPOUSE_OWNED;
		case 4:
			return RENTED_HOUSE_FOR_LESS_THAN_5_YEARS;
		case 5:
			return RENTED_HOUSE_FOR_MORE_THAN_5_YEARS;
		case 6:
			return PARENT_OWNED_OTHER_PLACE;
		default:
			return null;
		}
	}

	public static ResidenceTypeHomeLoan[] getAll() {
		return ResidenceTypeHomeLoan.values();

	}
}
