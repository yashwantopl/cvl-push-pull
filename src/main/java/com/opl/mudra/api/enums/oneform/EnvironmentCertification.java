package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum EnvironmentCertification {
			OBTAINED(1,"Obtained","Obtained"),
		IN_PROCESS_EASY_TO_GET(2,"In Process - Easy to Get","In Process - Easy to Get"),
		IN_PROCESS_DIFFICULT_TIME_CONSUMING(3,"In Process - Difficult / Time Consuming","In Process - Difficult / Time Consuming");
			
		private final Integer id;
		private final String value;
		private final String description;
		EnvironmentCertification(Integer id, String value, String description) {
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
		public static EnvironmentCertification getById(Integer id) {
		switch (id) {
		case 1:
			return OBTAINED;
		case 2:
			return IN_PROCESS_EASY_TO_GET;
		case 3:
			return IN_PROCESS_DIFFICULT_TIME_CONSUMING;
		default:
			return null;
		}
	}
		public static EnvironmentCertification[] getAll() {
			return EnvironmentCertification.values();

		}
}