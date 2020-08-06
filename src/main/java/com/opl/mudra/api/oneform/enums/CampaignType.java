package com.opl.mudra.api.oneform.enums;

public enum CampaignType {
	MarketPlace(1,"MarketPlace","MarketPlace"),
	BankSpecific(2,"BankSpecific","BankSpecific"),
	Both(3,"Both","Both");
		
		
	private final Integer id;
	private final String value;
	private final String description;
	CampaignType(Integer id, String value, String description) {
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
	public static CampaignType getById(Integer id) {
	switch (id) {
	case 1:
		return MarketPlace;
	case 2:
		return BankSpecific;
	case 3:
		return Both;
	default:
		return null;
	}
}
	public static CampaignType[] getAll() {
		return CampaignType.values();

	}
}
