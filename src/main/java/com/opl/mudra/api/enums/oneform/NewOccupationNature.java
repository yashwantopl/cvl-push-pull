package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum NewOccupationNature {
			SALARIED(2,"Salaried","Salaried"),
		/*BUSINESS(3,"Business","Business"),
		SELF_EMPLOYED(4,"Self Employed","Self Employed"),
		SELF_EMPLOYED_PROFESSIONAL(5,"Self Employed Professional","Self Employed Professional"),
		AGRICULTURIST(6,"Agriculturist","Agriculturist"),*/
		PENSIONER(7,"Pensioner","Pensioner");
	
		private final Integer id;
		private final String value;
		private final String description;
		NewOccupationNature(Integer id, String value, String description) {
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
		public static NewOccupationNature getById(Integer id) {
		switch (id) {
		case 2:
			return SALARIED;
		/*case 3:
			return BUSINESS;
		case 4:
			return SELF_EMPLOYED;
		case 5:
			return SELF_EMPLOYED_PROFESSIONAL;
		case 6:
			return AGRICULTURIST;*/
		case 7:
			return PENSIONER;
		default:
			return null;
		}
	}
		public static NewOccupationNature[] getAll() {
			return NewOccupationNature.values();

		}
}