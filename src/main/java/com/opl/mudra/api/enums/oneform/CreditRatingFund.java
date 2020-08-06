package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum CreditRatingFund {
	
	FUND(2,"Fund Based","Fund Based"),
	NON_FUND(3,"Non-Fund Based","Non-Fund Based");

	private final Integer id;
	private final String value;
	private final String description;

	CreditRatingFund(Integer id, String value, String description) {
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
	
	public static CreditRatingFund getById(Integer id) {
		switch (id) {
		case 2:
			return FUND;

		case 3:
			return NON_FUND;

		default:
			return null;
		}
	}

	public static CreditRatingFund[] getAll() {
		return CreditRatingFund.values();

	}

}
