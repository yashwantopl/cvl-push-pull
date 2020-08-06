package com.opl.mudra.api.oneform.enums;

public enum EnvironmentalImpact {
	Face_pollution_future(1,"Unlikely to face pollution related problems in future","Unlikely to face pollution related problems in future"),
	LIMITED_pollution_future(2,"Limited likelihood of facing pollution related problems in future","Limited likelihood of facing pollution related problems in future"),
	POLLUTING_CURRUNT_NORMS(3,"Polluting product but complies with current norms","Polluting product but complies with current norms"),
	NOT_POLLUTING_PRODUCT(4,"Polluting product and does not comply with current norms","Polluting product and does not comply with current norms");
	
		private final Integer id;
		private final String value;
		private final String description;
		EnvironmentalImpact(Integer id, String value, String description) {
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
		public static EnvironmentalImpact getById(Integer id) {
		switch (id) {
		case 1:
			return Face_pollution_future;
		case 2:
			return LIMITED_pollution_future;
		case 3:
			return POLLUTING_CURRUNT_NORMS;
		case 4:
			return NOT_POLLUTING_PRODUCT;
		default:
			return null;
		}
	}
		public static EnvironmentalImpact[] getAll() {
			return EnvironmentalImpact.values();

		}
}