package com.opl.mudra.api.enums.oneform;

public enum CompanyConflicts {
	RELATIONSHIP_EXCELLENT(1,"The relationship among promoters/directors/group companies are excellent, with little possibility of conflict","The relationship among promoters/directors/group companies are excellent, with little possibility of conflict"),
	GOOD_UNDERSTANDING(2,"The promoters/directors/group companies enjoy a mutually good understanding, although they may have personal differences","The promoters/directors/group companies enjoy a mutually good understanding, although they may have personal differences"),
	COMPANY_MEDIUM_TERM(3,"There have been instances of conflicts between promoters/directors which could impact the performance of the company in the medium term","There have been instances of conflicts between promoters/directors which could impact the performance of the company in the medium term"),
	MAJOR_DIFFERENCES(4,"Major differences between promoters/directors which is adversely affecting the performance of the company","Major differences between promoters/directors which is adversely affecting the performance of the company"),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		CompanyConflicts(Integer id, String value, String description) {
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
		public static CompanyConflicts getById(Integer id) {
		switch (id) {
		case 1:
			return RELATIONSHIP_EXCELLENT;
		case 2:
			return GOOD_UNDERSTANDING;
		case 3:
			return COMPANY_MEDIUM_TERM;
		case 4:
			return MAJOR_DIFFERENCES;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static CompanyConflicts[] getAll() {
			return CompanyConflicts.values();

		}
}