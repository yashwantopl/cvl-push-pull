package com.opl.mudra.api.enums.oneform;

public enum AbilityRaiseFunds {
	VERY_HIGH_CAPABILITY(1,"Very high capability to raise funds at competitive rates for meeting contingencies","Very high capability to raise funds at competitive rates for meeting contingencies"),
	HIGH_CAPABILITY(2,"High capability to raise funds for meeting contingencies","High capability to raise funds for meeting contingencies"),
	REASONABLE_CAPABILITY(3,"Reasonable capability to raise funds for meeting contingencies, although the certainty of covering all requirements is not high","Reasonable capability to raise funds for meeting contingencies, although the certainty of covering all requirements is not high"),
	LOW_CAPABILITY(4,"Extremely low capability to raise funds for meeting contingencies","Extremely low capability to raise funds for meeting contingencies");
	
		private final Integer id;
		private final String value;
		private final String description;
		AbilityRaiseFunds(Integer id, String value, String description) {
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
		public static AbilityRaiseFunds getById(Integer id) {
		switch (id) {
		case 1:
			return VERY_HIGH_CAPABILITY;
		case 2:
			return HIGH_CAPABILITY;
		case 3:
			return REASONABLE_CAPABILITY;
		case 4:
			return LOW_CAPABILITY;
		default:
			return null;
		}
	}
		public static AbilityRaiseFunds[] getAll() {
			return AbilityRaiseFunds.values();

		}
}