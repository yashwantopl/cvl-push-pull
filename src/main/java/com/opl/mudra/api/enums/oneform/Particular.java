package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum Particular {
			LAND_AND_LAND_DEVELOPEMENT(2,"Land and Land Developement","Land and Land Developement"),
		BUILDING__OTHER_CIVIL_WORKS(3,"Building & Other Civil Works","Building & Other Civil Works"),
		PLANT__MACHINERY(4,"Plant & Machinery","Plant & Machinery"),
		PRELIMINARY_AND_PREOPERATIVE_EXPENSES(10,"Preliminary and Pre-Operative Expenses","Preliminary and Pre-Operative Expenses"),
		INTEREST_DURING_CONSTRUCTION(11,"Interest During Construction","Interest During Construction"),
		MARGIN_FOR_WORKING_CAPITAL_REQUIRED(15,"Margin for Working Capital Required","Margin for Working Capital Required"),
		PROVISION_FOR_CONTINGENCIES(17,"Provision for Contingencies","Provision for Contingencies"),
		MISCELLANEOUS_FIXED_ASSETS(19,"Miscellaneous Fixed Assets","Miscellaneous Fixed Assets"),
		INTANGIBLE_ASSETS(20,"Intangible Assets","Intangible Assets"),
		OTHERS(21,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		Particular(Integer id, String value, String description) {
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
		public static Particular getById(Integer id) {
		switch (id) {
		case 2:
			return LAND_AND_LAND_DEVELOPEMENT;
		case 3:
			return BUILDING__OTHER_CIVIL_WORKS;
		case 4:
			return PLANT__MACHINERY;
		case 10:
			return PRELIMINARY_AND_PREOPERATIVE_EXPENSES;
		case 11:
			return INTEREST_DURING_CONSTRUCTION;
		case 15:
			return MARGIN_FOR_WORKING_CAPITAL_REQUIRED;
		case 17:
			return PROVISION_FOR_CONTINGENCIES;
		case 19:
			return MISCELLANEOUS_FIXED_ASSETS;
		case 20:
			return INTANGIBLE_ASSETS;
		case 21:
			return OTHERS;
		default:
			return null;
		}
	}
		public static Particular[] getAll() {
			return Particular.values();

		}
}