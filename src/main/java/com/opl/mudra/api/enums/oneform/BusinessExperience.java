package com.opl.mudra.api.enums.oneform;

public enum BusinessExperience {
	EXCELLENT_BUSINESS(1,"Firm has been in operations for over 10 years and has excellent business acumen and experience in line of business","Firm has been in operations for over 10 years and has excellent business acumen and experience in line of business"),
	REASONABLE_BUSINESS (2,"Firm has been in operations for over 5 years and has had reasonable success in running ther business","Firm has been in operations for over 5 years and has had reasonable success in running ther business"),
	SOLID_TRACK_RECORD(3,"Firm has been in operations for less than 5 years, but the promoters have solid track record of past performance in previous organisations","Firm has been in operations for less than 5 years, but the promoters have solid track record of past performance in previous organisations"),
	GREAT_TRACK_RECORD(4,"Top management has some experience of managing a business, although no great track record of success","Top management has some experience of managing a business, although no great track record of success");
	
		private final Integer id;
		private final String value;
		private final String description;
		BusinessExperience(Integer id, String value, String description) {
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
		public static BusinessExperience getById(Integer id) {
		switch (id) {
		case 1:
			return EXCELLENT_BUSINESS;
		case 2:
			return REASONABLE_BUSINESS;
		case 3:
			return SOLID_TRACK_RECORD;
		case 4:
			return GREAT_TRACK_RECORD;
		default:
			return null;
		}
	}
		public static BusinessExperience[] getAll() {
			return BusinessExperience.values();

		}
}