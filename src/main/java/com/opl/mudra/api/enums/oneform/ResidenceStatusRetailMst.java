package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum ResidenceStatusRetailMst {
	OWNED(1, "Owned", "Owned"),
	SPOUSE_OWNED(3, "Spouse Owned", "Spouse Owned"),
	PARENT_OWNED_SAME_PLACE(2, "Parent Owned (Staying with parents)", "PParent Owned (Staying with parents)"),
	PARENT_OWNED_OTHER_PLACE(6, "Parent owned (Other)", "Parent owned (Other)"),
	RENTED_HOUSE_FOR_MORE_THAN_5_YEARS(5, "Rented house for more than 5 Years", "Rented house for more than 5 Years"),
	RENTED_HOUSE_FOR_LESS_THAN_5_YEARS(4, "Rented house for less than 5 Years", "Rented house for less than 5 Years"),
	COMPANY_LEASED(7,"Company Leased", "Company Leased");

	
		private final Integer id;
		private final String value;
		private final String description;
		ResidenceStatusRetailMst(Integer id, String value, String description) {
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
		public static ResidenceStatusRetailMst getById(Integer id) {
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
		case 7:
			return COMPANY_LEASED;
		default:
			return null;
		}
	}
		public static ResidenceStatusRetailMst[] getAll() {
			return ResidenceStatusRetailMst.values();

		}
}