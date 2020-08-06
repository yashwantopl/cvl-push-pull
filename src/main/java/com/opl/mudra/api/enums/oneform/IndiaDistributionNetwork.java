package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum IndiaDistributionNetwork {
			ONE_STATE(1,"1 State","1 State"),
		TWENTYSIX_STATES(2,"2-6 States","2-6 States"),
		GREATERSIX_STATES(3,">6 States",">6 States");
	
		private final Integer id;
		private final String value;
		private final String description;
		IndiaDistributionNetwork(Integer id, String value, String description) {
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
		public static IndiaDistributionNetwork getById(Integer id) {
		switch (id) {
		case 1:
			return ONE_STATE;
		case 2:
			return TWENTYSIX_STATES;
		case 3:
			return GREATERSIX_STATES;
		default:
			return null;
		}
	}
		public static IndiaDistributionNetwork[] getAll() {
			return IndiaDistributionNetwork.values();

		}
}