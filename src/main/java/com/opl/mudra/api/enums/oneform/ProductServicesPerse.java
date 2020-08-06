package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum ProductServicesPerse {
			FORM_SUBSTANTIAL_PART_OF_VALUE_CHAIN(2,"Form Substantial part of value Chain","Form Substantial part of value Chain"),
		KEY_CRITICAL_PART_OF_VALUE_CHAIN(3,"Key/Critical part of value chain","Key/Critical part of value chain"),
		SMALL_PART_OF_VALUE_CHAIN(4,"Small part of value chain","Small part of value chain");
	
		private final Integer id;
		private final String value;
		private final String description;
		ProductServicesPerse(Integer id, String value, String description) {
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
		public static ProductServicesPerse getById(Integer id) {
		switch (id) {
		case 2:
			return FORM_SUBSTANTIAL_PART_OF_VALUE_CHAIN;
		case 3:
			return KEY_CRITICAL_PART_OF_VALUE_CHAIN;
		case 4:
			return SMALL_PART_OF_VALUE_CHAIN;
		default:
			return null;
		}
	}
		public static ProductServicesPerse[] getAll() {
			return ProductServicesPerse.values();

		}
}