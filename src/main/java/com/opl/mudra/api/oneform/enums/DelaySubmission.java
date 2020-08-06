package com.opl.mudra.api.oneform.enums;

public enum DelaySubmission {
	ON_TIME(1,"On time ","On time "),
	DELAY(2,"Delay of less than 1 month","Delay of less than 1 month"),
	ONE_TO_THREE(3,"From 1 to 3 months ","From 1 to 3 months "),
	GRETERTHSN_THREE(4,"Greater than 3 months ","Greater than 3 months ");
	
		private final Integer id;
		private final String value;
		private final String description;
		DelaySubmission(Integer id, String value, String description) {
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
		public static DelaySubmission getById(Integer id) {
		switch (id) {
		case 1:
			return ON_TIME;
		case 2:
			return DELAY;
		case 3:
			return ONE_TO_THREE;
		case 4:
			return GRETERTHSN_THREE;
		default:
			return null;
		}
	}
		public static DelaySubmission[] getAll() {
			return DelaySubmission.values();

		}
}