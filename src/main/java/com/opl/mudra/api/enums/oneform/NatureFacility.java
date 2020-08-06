package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum NatureFacility {
			FUND_BASED_EXPOSURE(9,"Fund Based Exposure","Fund Based Exposure"),
		NONFUND_BASED_EXPOSURE(10,"Non-Fund Based Exposure","Non-Fund Based Exposure");
	
		private final Integer id;
		private final String value;
		private final String description;
		NatureFacility(Integer id, String value, String description) {
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
		public static NatureFacility getById(Integer id) {
		switch (id) {
		case 9:
			return FUND_BASED_EXPOSURE;
		case 10:
			return NONFUND_BASED_EXPOSURE;
		default:
			return null;
		}
	}
		public static NatureFacility[] getAll() {
			return NatureFacility.values();

		}
}