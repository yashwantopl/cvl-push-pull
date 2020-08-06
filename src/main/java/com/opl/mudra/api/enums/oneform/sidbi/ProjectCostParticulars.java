package com.opl.mudra.api.enums.oneform.sidbi;

public enum ProjectCostParticulars {
	
	LAND_INCLUDING_DEVELOPMENT(1,"land_including_devp","Land including Development"),
	BUILDING_AND_OTHER_CIVIL_WORKS(2,"building_&_other_civil_works","Building & other Civil Works"),
	PLANT_AND_MACHINERY(3,"P&M","Plant & Machinery - For Manufacturing unit"), // Not for service sector unit
	INDIGENOUS(4,"indigenous","     Indigenous"),
	IMPORTED(5,"imported","     Imported"),
	EQUIPMENT(7,"equipment","Equipment - For Service Sector Unit"),
	MISCELLANEOUS_FIXED_ASSETS(6,"misc_fixed_assets","Miscellaneous Fixed Assets - For Manufacturing Unit"),
	PRELIMINARY_AND_PRE_OPERATIVE_EXPENSES(8,"prel&pre_operative_exp","Preliminary & Pre-operative Expenses"),
	PROVISIONS_FOR_CONTINGENCIES(9,"provisions_for_contingencies","Provisions for Contingencies - (for building, P&M) and MFAs or Equipment"),
	MARGIN_FOR_WORKING_CAPITAL_REQUIRED(10,"margin_for_wc_required","Margin for working capital required");
//	ANY_OTHER(11,"any_other","Any Other");

	private final Integer id;
	private final String value;
	private final String description;
	
	private ProjectCostParticulars(Integer id, String value,String description) {
		this.id = id;
		this.value = value;
		this.description=description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static ProjectCostParticulars[] getAll() {
		return ProjectCostParticulars.values();

	}

	public String getDescription() {
		return description;
	}
	
	
}
