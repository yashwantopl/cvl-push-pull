package com.opl.mudra.api.oneform.enums;

public enum MrktArrFinishedGoodsList {
	
	TIE_UP_ARRANGEMENT(1,"Firm Marketing arrangement/ tie-up for output",null),
	NO_TIE_UP_ARRANGEMENT(2,"No Marketing arrangement/ tie-up, but product easily marketable",null),
	NO_ARRANGEMENT(3,"No arrangement/ tie-up",null);
	
	
	private final Integer id;
	private final String value;
	private final String description;
	
	
	private MrktArrFinishedGoodsList(Integer id, String value, String description) {
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
	
	
	public static MrktArrFinishedGoodsList fromValue(String v) {
		for (MrktArrFinishedGoodsList c : MrktArrFinishedGoodsList.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	public static MrktArrFinishedGoodsList fromId(Integer v) {
		for (MrktArrFinishedGoodsList c : MrktArrFinishedGoodsList.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v.toString());

	}
	
	public static MrktArrFinishedGoodsList[] getAll() {
		return MrktArrFinishedGoodsList.values();
	}
	
	
	

}
