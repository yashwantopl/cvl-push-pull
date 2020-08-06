package com.opl.mudra.api.oneform.enums;

public enum SubmissionReports {
	DUE_DATE(1,"Within due date","Within due date"),
	FIFTEEN_DAYS(2,"Within 15 days","Within 15 days"),
	THIRTY_DAYS(3,"Within 30 days","Within 30 days"),
	RECEIVED_THIRTYDAYS(4,"Generally received 30 days after due date","Generally received 30 days after due date");
	
		private final Integer id;
		private final String value;
		private final String description;
		SubmissionReports(Integer id, String value, String description) {
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
		public static SubmissionReports getById(Integer id) {
		switch (id) {
		case 1:
			return DUE_DATE;
		case 2:
			return FIFTEEN_DAYS;
		case 3:
			return THIRTY_DAYS;
		case 4:
			return RECEIVED_THIRTYDAYS;
		default:
			return null;
		}
	}
		public static SubmissionReports[] getAll() {
			return SubmissionReports.values();

		}
}