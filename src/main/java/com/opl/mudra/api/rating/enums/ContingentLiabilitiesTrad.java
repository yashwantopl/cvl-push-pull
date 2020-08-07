package com.opl.mudra.api.rating.enums;

/**
 * @author Hiren
 *
 */
public enum ContingentLiabilitiesTrad {
	LESSTHAN25(237, "Less than 25 percent"), 
	BETWEEN25TO50(238, "25 to 50 percent "),
	BETWEEN50TO75(239, "50 to 75 percent "),
	GREATER75(240, "Greater than 75 percent");

	private final Integer id;
	private final String value;

	ContingentLiabilitiesTrad(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static ContingentLiabilitiesTrad getById(Integer id) {
		switch (id) {
		case 5:
			return LESSTHAN25;
			
		case 6 :
			return BETWEEN25TO50;
			
		case 7 :
			return BETWEEN50TO75;
		
			
		case 8 :
			return GREATER75;
		
		
		}
		return null;
	}
}
