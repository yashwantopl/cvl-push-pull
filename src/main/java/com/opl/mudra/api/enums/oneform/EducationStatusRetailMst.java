package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum EducationStatusRetailMst {
			UNDERGRADUATE(2,"Undergraduate","Undergraduate"),
		GRADUATE(3,"Graduate","Graduate"),
		POST_GRADUATE(4,"Post Graduate","Post Graduate"),
		PROFESSIONAL(5,"Professional","Professional"),
		OTHERS(6,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		EducationStatusRetailMst(Integer id, String value, String description) {
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
		public static EducationStatusRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return UNDERGRADUATE;
		case 3:
			return GRADUATE;
		case 4:
			return POST_GRADUATE;
		case 5:
			return PROFESSIONAL;
		case 6:
			return OTHERS;
		default:
			return null;
		}
	}
		public static EducationStatusRetailMst[] getAll() {
			return EducationStatusRetailMst.values();

		}
}