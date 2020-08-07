package com.opl.mudra.api.oneform.enums;
/**
 * @author Harshit
 * Date :- 29/04/2019
 *
 */
public enum HomeLoanPurpose {
		PURCHASE(1,"Purchase","Purchase"),
		CONSTRUCTION_EXPANSION(2,"Construction/Expansion","Construction/Expansion"),
		REPAIRS_RENOVATIONS(3,"Repairs & Renovations","Repairs & Renovations"),
		OTHERS(4,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		HomeLoanPurpose(Integer id, String value, String description) {
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
		public static HomeLoanPurpose getById(Integer id) {
		switch (id) {
		case 1:
			return PURCHASE;
		case 2:
			return CONSTRUCTION_EXPANSION;
		case 3:
			return REPAIRS_RENOVATIONS;
		case 4:
			return OTHERS;
		case 7:
			return OTHERS;
		default:
			return null;
		}
	}
		public static HomeLoanPurpose[] getAll() {
			return HomeLoanPurpose.values();

		}
}