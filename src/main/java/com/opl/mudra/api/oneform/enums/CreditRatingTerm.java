package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum CreditRatingTerm {

	LONG(1, "Long Term", "Long Term"), 
	SHORT(2, "Short Term", "Short Term"), 
	SME(3, "SME", "SME"), 
	CCR(4, "Corporate Credit rating(CCR)", "Corporate Credit rating(CCR)");

	private final Integer id;
	private final String value;
	private final String description;

	CreditRatingTerm(Integer id, String value, String description) {
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

	public static CreditRatingTerm getById(Integer id) {
		switch (id) {
		case 1:
			return LONG;

		case 2:
			return SHORT;

		case 3:
			return SME;

		case 4:
			return CCR;

		default:
			return null;
		}
	}

	public static CreditRatingTerm[] getAll() {
		return CreditRatingTerm.values();

	}

}
