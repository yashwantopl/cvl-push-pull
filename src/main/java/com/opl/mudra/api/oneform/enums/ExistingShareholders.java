package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum ExistingShareholders {
	ANY_HIGH_PROFILE_INDIVIDUAL(1, "Any High Profile Individual",
			"Any High Profile Individual"), ANY_FUND_OR_INSTITUTION(2, "Any fund or institution",
					"Any fund or institution");
	
	private final Integer id;
	private final String value;
	private final String description;

	ExistingShareholders(Integer id, String value, String description) {
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

	public static ExistingShareholders getById(Integer id) {
		switch (id) {
		case 1:
			return ANY_HIGH_PROFILE_INDIVIDUAL;
		case 2:
			return ANY_FUND_OR_INSTITUTION;
		default:
			return null;
		}
	}

	public static ExistingShareholders[] getAll() {
		return ExistingShareholders.values();

	}
}