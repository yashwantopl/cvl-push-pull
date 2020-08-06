package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum MarketingPositioningNew {
			NEW(1,"New","New"),
		EXISTING(2,"Existing","Existing");
	
		private final Integer id;
		private final String value;
		private final String description;
		MarketingPositioningNew(Integer id, String value, String description) {
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
		public static MarketingPositioningNew getById(Integer id) {
		switch (id) {
		case 1:
			return NEW;
		case 2:
			return EXISTING;
		default:
			return null;
		}
	}
		public static MarketingPositioningNew[] getAll() {
			return MarketingPositioningNew.values();

		}
}