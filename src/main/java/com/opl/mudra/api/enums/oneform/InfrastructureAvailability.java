package com.opl.mudra.api.enums.oneform;

public enum InfrastructureAvailability {
	INFRASTRUCTURE_AVAILABLE(1,"All the critical infrastructure required for the project is already available","All the critical infrastructure required for the project is already available"),
	LIKELY_AVAILABLE(2,"Critical infrastructure required for the project is likely to be available in a short while and is not likely to delay execution of the project","Critical infrastructure required for the project is likely to be available in a short while and is not likely to delay execution of the project"),
	DELAY_EXECUTION(3,"Critical infrastructure required for the project is likely to be available in the medium term but could marginally delay execution of the project","Critical infrastructure required for the project is likely to be available in the medium term but could marginally delay execution of the project"),
	LIKELY_TO_DELAY(4,"Critical infrastructure required for the project is currently not  available and is likely to delay the project considerably","Critical infrastructure required for the project is currently not  available and is likely to delay the project considerably"),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		InfrastructureAvailability(Integer id, String value, String description) {
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
		public static InfrastructureAvailability getById(Integer id) {
		switch (id) {
		case 1:
			return INFRASTRUCTURE_AVAILABLE;
		case 2:
			return LIKELY_AVAILABLE;
		case 3:
			return DELAY_EXECUTION;
		case 4:
			return LIKELY_TO_DELAY;
		case 5:
				return NA;
		default:
			return null;
		}
	}
		public static InfrastructureAvailability[] getAll() {
			return InfrastructureAvailability.values();

		}
}