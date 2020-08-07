package com.opl.mudra.api.oneform.enums;

public enum SensititivityAnalysis {
	NOT_DSCR_CATEGORY(1,"DSCR category does not change when either factor is stressed individually","DSCR category does not change when either factor is stressed individually"),
	DSCR_CATEGORY_CHANGES(2,"DSCR category changes by at best one notch (Averge DSCR should not be below 1.5) when either factor is stressed","DSCR category changes by at best one notch (Averge DSCR should not be below 1.5) when either factor is stressed"),
	DSCR_CATEGORY_TWONOTCHES(3,"DSCR category changes by at two notches (without coming below 1.25) when either factor is stressed","DSCR category changes by at two notches (without coming below 1.25) when either factor is stressed"),
	DSCR_CATEGORY_FALLS_BELOW(4,"Average DSCR falls below 1.5 or annual DSCR in any year falls below 1","Average DSCR falls below 1.5 or annual DSCR in any year falls below 1");
	
		private final Integer id;
		private final String value;
		private final String description;
		SensititivityAnalysis(Integer id, String value, String description) {
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
		public static SensititivityAnalysis getById(Integer id) {
		switch (id) {
		case 1:
			return NOT_DSCR_CATEGORY;
		case 2:
			return DSCR_CATEGORY_CHANGES;
		case 3:
			return DSCR_CATEGORY_TWONOTCHES;
		case 4:
			return DSCR_CATEGORY_FALLS_BELOW;
		default:
			return null;
		}
	}
		public static SensititivityAnalysis[] getAll() {
			return SensititivityAnalysis.values();

		}
}