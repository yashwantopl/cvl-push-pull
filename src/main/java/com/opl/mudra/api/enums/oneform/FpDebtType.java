package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum FpDebtType {
	CORPORATE_LOAN(1, "Corporate Loan", "Corporate Loan"), RETAIL_LOAN(2, "Retail loan", "Retail loan");

	private final Integer id;
	private final String value;
	private final String description;

	FpDebtType(Integer id, String value, String description) {
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

	public static FpDebtType getById(Integer id) {
		switch (id) {
		case 1:
			return CORPORATE_LOAN;
		case 2:
			return RETAIL_LOAN;
		default:
			return null;
		}
	}

	public static FpDebtType[] getAll() {
		return FpDebtType.values();

	}
}