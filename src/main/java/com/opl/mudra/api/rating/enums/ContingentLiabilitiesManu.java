package com.opl.mudra.api.rating.enums;

/**
 * @author Hiren
 *
 */
public enum ContingentLiabilitiesManu {
	LESSTHAN25(5, "Less than 25 percent"), 
	BETWEEN25TO50(6, "25 to 50 percent "),
	BETWEEN50TO75(7, "50 to 75 percent "),
	GREATER75(8, "Greater than 75 percent");

	private final Integer id;
	private final String value;

	ContingentLiabilitiesManu(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static ContingentLiabilitiesManu getById(Integer id) {
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
