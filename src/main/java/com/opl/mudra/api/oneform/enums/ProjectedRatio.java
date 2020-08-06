package com.opl.mudra.api.oneform.enums;

public enum ProjectedRatio {
	DSCR_GRETERTHEN_THREE(1,"Average DSCR >3 and annual DSCR > 2 in all years while debt is outstanding","Average DSCR >3 and annual DSCR > 2 in all years while debt is outstanding"),
	DSCR_GRETERTHEN_TWO(2,"Average DSCR > 2 and annual DSCR > 1.75 in all years when debt is outstanding","Average DSCR > 2 and annual DSCR > 1.75 in all years when debt is outstanding"),
	DSCR_GRETERTHEN_ONEHALF(3,"Average DSCR > 1.5 and annual DSCR > 1.25 in all years when debt is outstanding","Average DSCR > 1.5 and annual DSCR > 1.25 in all years when debt is outstanding"),
	OTHE_CASES(4,"All other cases","All other cases");
	
		private final Integer id;
		private final String value;
		private final String description;
		ProjectedRatio(Integer id, String value, String description) {
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
		public static ProjectedRatio getById(Integer id) {
		switch (id) {
		case 1:
			return DSCR_GRETERTHEN_THREE;
		case 2:
			return DSCR_GRETERTHEN_TWO;
		case 3:
			return DSCR_GRETERTHEN_ONEHALF;
		case 4:
			return OTHE_CASES;
		default:
			return null;
		}
	}
		public static ProjectedRatio[] getAll() {
			return ProjectedRatio.values();

		}
}