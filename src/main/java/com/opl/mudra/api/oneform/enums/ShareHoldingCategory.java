package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum ShareHoldingCategory {
			PROMOTERS(2,"Promoters","Promoters"),
		NON_PROMOTER_GROUP(3,"Non Promoter Group","Non Promoter Group");
	
		private final Integer id;
		private final String value;
		private final String description;
		ShareHoldingCategory(Integer id, String value, String description) {
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
		public static ShareHoldingCategory getById(Integer id) {
		switch (id) {
		case 2:
			return PROMOTERS;
		case 3:
			return NON_PROMOTER_GROUP;
		default:
			return null;
		}
	}
		public static ShareHoldingCategory[] getAll() {
			return ShareHoldingCategory.values();

		}
}