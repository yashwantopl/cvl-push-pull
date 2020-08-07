package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum CarPurchaseType {
	
	PURCHASED(1,"To be purchased","PURCHASED"),
	REIMBURSMENT(2,"Reimbursment for already purchased Car","REIMBURSMENT");
	
	private final Integer id;
	private final String value;
	private final String description;

	CarPurchaseType(Integer id, String value, String description) {
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
	
	
	public static CarPurchaseType getById(Integer id) {
		switch (id) {
		case 1:
			return PURCHASED;

		case 2:
			return REIMBURSMENT;

		default:
			return null;
		}
	}

	public static CarPurchaseType[] getAll() {
		return CarPurchaseType.values();

	}

}
