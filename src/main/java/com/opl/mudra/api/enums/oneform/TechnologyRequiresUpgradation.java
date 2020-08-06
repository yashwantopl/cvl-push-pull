package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum TechnologyRequiresUpgradation {
			MAJOR(1,"Major","Major"),
		MINOR(2,"Minor","Minor"),
		NOT_REQUIRED(3,"Not Required","Not Required");
	
		private final Integer id;
		private final String value;
		private final String description;
		TechnologyRequiresUpgradation(Integer id, String value, String description) {
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
		public static TechnologyRequiresUpgradation getById(Integer id) {
		switch (id) {
		case 1:
			return MAJOR;
		case 2:
			return MINOR;
		case 3:
			return NOT_REQUIRED;
		default:
			return null;
		}
	}
		public static TechnologyRequiresUpgradation[] getAll() {
			return TechnologyRequiresUpgradation.values();

		}
}