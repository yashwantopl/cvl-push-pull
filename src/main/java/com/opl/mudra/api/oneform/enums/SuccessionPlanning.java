package com.opl.mudra.api.oneform.enums;

public enum SuccessionPlanning {
	WELLDEFINED_PLAN(1,"Well-defined succession plan with competent successors in place; business not dependent on one person","Well-defined succession plan with competent successors in place; business not dependent on one person"),
	BUSINESS_DEPENDENT (2,"Business dependent on one person at present, but reasonable depth in management to ensure business will not be disrupted","Business dependent on one person at present, but reasonable depth in management to ensure business will not be disrupted"),
	ADVERSELY_AFFECT_PERFORMANCE(3,"Succession not addressed adequately and dealing with a change in the management could adversely affect the company's performance","Succession not addressed adequately and dealing with a change in the management could adversely affect the company's performance"),
	BUSINESS_SERIOUS_SETBACK(4,"Succession has not been addressed and in case of incapacitation of key person, the business would suffer a serious setback or even closure","Succession has not been addressed and in case of incapacitation of key person, the business would suffer a serious setback or even closure");
	
		private final Integer id;
		private final String value;
		private final String description;
		SuccessionPlanning(Integer id, String value, String description) {
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
		public static SuccessionPlanning getById(Integer id) {
		switch (id) {
		case 1:
			return WELLDEFINED_PLAN;
		case 2:
			return BUSINESS_DEPENDENT;
		case 3:
			return ADVERSELY_AFFECT_PERFORMANCE;
		case 4:
			return BUSINESS_SERIOUS_SETBACK;
		default:
			return null;
		}
	}
		public static SuccessionPlanning[] getAll() {
			return SuccessionPlanning.values();

		}
}