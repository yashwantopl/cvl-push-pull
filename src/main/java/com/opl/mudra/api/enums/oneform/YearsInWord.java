package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum YearsInWord {
			OF_0_YEARS(1,"0 Years","0 Years"),
			OF_1_YEARS(2,"1 Years","1 Years"),
			OF_2_YEARS(3,"2 Years","2 Years"),
			OF_3_YEARS(4,"3 Years","3 Years");
	
		private final Integer id;
		private final String value;
		private final String description;
		YearsInWord(Integer id, String value, String description) {
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
		public static YearsInWord getById(Integer id) {
		switch (id) {
		case 1:
			return OF_0_YEARS;
		case 2:
			return OF_1_YEARS;
		case 3:
			return OF_2_YEARS;
		case 4:
			return OF_3_YEARS;
		default:
			return null;
		}
	}
		public static YearsInWord[] getAll() {
			return YearsInWord.values();

		}
}