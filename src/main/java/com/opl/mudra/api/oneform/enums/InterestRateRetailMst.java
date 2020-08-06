package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum InterestRateRetailMst {
			FIXED_RATE(1,"Fixed Rate","Fixed Rate"),
		FLOATING_RATE(2,"Floating Rate","Floating Rate");
	
		private final Integer id;
		private final String value;
		private final String description;
		InterestRateRetailMst(Integer id, String value, String description) {
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
		public static InterestRateRetailMst getById(Integer id) {
		switch (id) {
		case 1:
			return FIXED_RATE;
		case 2:
			return FLOATING_RATE;
		default:
			return null;
		}
	}
		public static InterestRateRetailMst[] getAll() {
			return InterestRateRetailMst.values();

		}
}