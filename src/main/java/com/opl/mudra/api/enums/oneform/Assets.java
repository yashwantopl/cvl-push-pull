package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum Assets {
	
	BONDS(1,"Bonds","BONDS"),
	SHARES(2,"Shares","SHARES"),
	MUTUAL_FUNDS(3,"Mutual funds","MUTUAL_FUNDS"),
	OTHER_INVESTMENTS(4,"Other investments","OTHER_INVESTMENTS"),
	PRECIOUS(5,"Precious metals/gold/jewelry","Precious"),
	IMMOVABLE_PROPERTY(6,"Immovable property","IMMOVABLE_PROPERTY"),
	PF_BAL(7,"Current PF balance(Your share)","CURRENT_PF_BALANCE"),
	LIC(8,"LIC / Postal life, etc.","LIC"),
	CAPITAL(9,"Capital in various firms (your share)","CAPITAL"),
	OTHERS(10,"Others","Others");
	
	private final Integer id;
	private final String value;
	private final String description;

	Assets(Integer id, String value, String description) {
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

	public static Assets getById(Integer id) {
		switch (id) {
		case 1:
			return BONDS;

		case 2:
			return SHARES;

		case 3:
			return MUTUAL_FUNDS;
			
		case 4:
			return OTHER_INVESTMENTS;
			
		case 5:
			return PRECIOUS;
			
		case 6:
			return IMMOVABLE_PROPERTY;
			
		case 7:
			return PF_BAL;
			
		case 8:
			return LIC;
			
		case 9:
			return CAPITAL;
			
		case 10:
			return OTHERS;

		default:
			return null;
		}
	}

	public static Assets[] getAll() {
		return Assets.values();

	}
}
