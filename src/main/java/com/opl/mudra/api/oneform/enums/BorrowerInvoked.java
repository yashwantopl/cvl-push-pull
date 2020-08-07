package com.opl.mudra.api.oneform.enums;

public enum BorrowerInvoked {
	None(1,"None ","None"),
	ONE(2,"1","1"),
	TWO_TO_FIVE(3,"From 2 to 5","From 2 to 5"),
	GRETERTHEN_FIVE(4,"Greater than 5","Greater than 5");
	
		private final Integer id;
		private final String value;
		private final String description;
		BorrowerInvoked(Integer id, String value, String description) {
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
		public static BorrowerInvoked getById(Integer id) {
		switch (id) {
		case 1:
			return None;
		case 2:
			return ONE;
		case 3:
			return TWO_TO_FIVE;
		case 4:
			return GRETERTHEN_FIVE;
		default:
			return null;
		}
	}
		public static BorrowerInvoked[] getAll() {
			return BorrowerInvoked.values();

		}
}