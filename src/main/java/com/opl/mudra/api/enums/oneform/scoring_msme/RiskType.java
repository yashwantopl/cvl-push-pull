package com.opl.mudra.api.enums.oneform.scoring_msme;

public enum RiskType {
	MANAGEMENT_RISK(1, "MANAGEMENT RISK", "MANAGEMENT RISK"),
	FINANCIAL_RISK(2, "FINANCIAL RISK", "FINANCIAL RISK"),
	BUSINESS_RISK(3, "BUSINESS RISK", "BUSINESS RISK");

	private final Integer id;
	private final String value;
	private final String description;

	RiskType(Integer id, String value, String description) {
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

	public static RiskType getById(Integer id) {
		switch (id) {
		case 1:
			return MANAGEMENT_RISK;
		case 2:
			return FINANCIAL_RISK;
		case 3:
			return BUSINESS_RISK;
		default:
			return null;
		}
	}

	public static RiskType[] getAll() {
		return RiskType.values();

	}
}