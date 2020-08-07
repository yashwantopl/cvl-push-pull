/**
 * 
 */
package com.opl.mudra.api.sidbi.enums;

/**
 * @author vijay.chauhan
 *
 */
public enum ParticularOfSecurity {
	
	LAND(1, "Land", "Land"),
	BUILDING(2, "Building", "Building"),
	PLANT_MACHINERY(3, "P&M", "Plant & Machinery"),
	LISTED_SHARES(4, "Listed Shares (Demat)","Listed Shares (Demat)"),
	FD(5, "FD","Cash Collateral"),
	SBD(6, "StockBookDebts","Stock & Book Debts");

	private final Integer id;
	private final String value;
	private final String description;

	ParticularOfSecurity(Integer id, String value, String description) {
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

	public static ParticularOfSecurity getById(Integer id) {
		switch (id) {
		case 1:
			return LAND;
		case 2:
			return BUILDING;
		case 3:
			return PLANT_MACHINERY;
		case 4:
			return LISTED_SHARES;
		case 5:
			return FD;
		case 6:
			return SBD;			
		default:
			return null;
		}
	}

	public static ParticularOfSecurity[] getAll() {
		return ParticularOfSecurity.values();

	}
}
