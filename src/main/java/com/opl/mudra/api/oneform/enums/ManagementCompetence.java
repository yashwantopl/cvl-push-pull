package com.opl.mudra.api.oneform.enums;

public enum ManagementCompetence {
	EXCELLENT_MANAGEMENT(1,"Excellent management team which has the necessary operating skills required in the business","Excellent management team which has the necessary operating skills required in the business"),
	REASONABLY_EXPERIENCED(2,"A reasonably experienced management team which has the necessary operating skills required in the business.","A reasonably experienced management team which has the necessary operating skills required in the business."),
	LIMITED_MANAGERIAL_SKILL(3,"Limited managerial skill in the line of business","Limited managerial skill in the line of business"),
	WEAK_MANAGERIAL_SKILL(4,"Weak managerial skill in the line of business","Weak managerial skill in the line of business");
	
		private final Integer id;
		private final String value;
		private final String description;
		ManagementCompetence(Integer id, String value, String description) {
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
		public static ManagementCompetence getById(Integer id) {
		switch (id) {
		case 1:
			return EXCELLENT_MANAGEMENT;
		case 2:
			return REASONABLY_EXPERIENCED;
		case 3:
			return LIMITED_MANAGERIAL_SKILL;
		case 4:
			return WEAK_MANAGERIAL_SKILL;
		default:
			return null;
		}
	}
		public static ManagementCompetence[] getAll() {
			return ManagementCompetence.values();

		}
}