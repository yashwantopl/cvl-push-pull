package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum TypeTechnology {
			PROVEN_OLD_OWNED(1,"Proven (Old) & Owned","Proven (Old) & Owned"),
		NEW_OWNED(2,"New & Owned","New & Owned"),
		NONE(3,"None","None"),
		NA(4,"NA","NA");
	
		private final Integer id;
		private final String value;
		private final String description;
		TypeTechnology(Integer id, String value, String description) {
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
		public static TypeTechnology getById(Integer id) {
		switch (id) {
		case 1:
			return PROVEN_OLD_OWNED;
		case 2:
			return NEW_OWNED;
		case 3:
			return NONE;
		case 4:
			return NA;
		default:
			return null;
		}
	}
		public static TypeTechnology[] getAll() {
			return TypeTechnology.values();

		}
}