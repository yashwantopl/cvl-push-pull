package com.opl.mudra.api.enums.oneform.scoring;

public enum ContinuousNetProfit {
	LAST_THREE_YEAR(1,"Last 3 year","Last 3 year"),
	LAST_TWO_YEAR(2,"Last 2 year","Last 2 year"),
	LAST_YEAR(3,"Last year","Last year"),
	NO_PROFIT(4,"No profit","No profit");

	private final Integer id;
	private final String value;
	private final String description;


	ContinuousNetProfit(Integer id, String value, String description) {
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
	public static ContinuousNetProfit getById(Integer id) {
		switch (id) {
			case 1:
				return LAST_THREE_YEAR;
			case 2:
				return LAST_TWO_YEAR;
			case 3:
				return LAST_YEAR;
			case 4:
				return NO_PROFIT;
			default:
				return null;
		}
	}
	public static ContinuousNetProfit[] getAll() {
		return ContinuousNetProfit.values();

	}
}