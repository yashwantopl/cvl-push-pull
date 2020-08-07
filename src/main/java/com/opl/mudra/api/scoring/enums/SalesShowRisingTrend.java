package com.opl.mudra.api.scoring.enums;

public enum SalesShowRisingTrend {
	RISING_TREND_IN_LAST_THREE_YEAR(1,"Sales showing a rising trend in last 3 years","Sales showing a rising trend in last 3 years"),
	RISING_TREND_IN_LAST_TWO_YEAR(2,"Sales showing a rising trend in last 2 years","Sales showing a rising trend in last 2 years"),
	RISING_TREND_IN_LAST_YEAR(3,"Sales showing a rising trend in last 1 years","Sales showing a rising trend in last 1 years"),
	NO_RISING_TREND(4,"No rising trend","No rising trend");

	private final Integer id;
	private final String value;
	private final String description;


	SalesShowRisingTrend(Integer id, String value, String description) {
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
	public static SalesShowRisingTrend getById(Integer id) {
		switch (id) {
			case 1:
				return RISING_TREND_IN_LAST_THREE_YEAR;
			case 2:
				return RISING_TREND_IN_LAST_TWO_YEAR;
			case 3:
				return RISING_TREND_IN_LAST_YEAR;
			case 4:
				return NO_RISING_TREND;
			default:
				return null;
		}
	}
	public static SalesShowRisingTrend[] getAll() {
		return SalesShowRisingTrend.values();

	}
}