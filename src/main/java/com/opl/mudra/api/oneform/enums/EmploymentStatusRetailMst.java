package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum EmploymentStatusRetailMst {
			REGULAR(2,"Regular","Regular"),
		PROBATIONARY(3,"Probationary","Probationary"),
		CONTRACTUAL(4,"Contractual","Contractual");
	
		private final Integer id;
		private final String value;
		private final String description;
		EmploymentStatusRetailMst(Integer id, String value, String description) {
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
		public static EmploymentStatusRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return REGULAR;
		case 3:
			return PROBATIONARY;
		case 4:
			return CONTRACTUAL;
		default:
			return null;
		}
	}
		public static EmploymentStatusRetailMst[] getAll() {
			return EmploymentStatusRetailMst.values();

		}
}