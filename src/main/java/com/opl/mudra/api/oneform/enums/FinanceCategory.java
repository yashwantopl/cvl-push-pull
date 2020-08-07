package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum FinanceCategory {
	EQUITY(1, "Equity", "Equity"), DEBT_LOAN(2, "Debt/Loan", "Debt/Loan"), INTERNAL_ACCRUALS(3, "Internal Accruals",
			"Internal Accruals"), OTHERS(4, "Others", "Others"), DEBT_LOAN_BY_PROMOTERS(5, "Debt/Loan by Promoters",
					"Debt/Loan by Promoters"), DEBT_LOAN_BY_OTHERS(6, "Debt/Loan by Others", "Debt/Loan by Others");

	private final Integer id;
	private final String value;
	private final String description;

	FinanceCategory(Integer id, String value, String description) {
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

	public static FinanceCategory getById(Integer id) {
		switch (id) {
		case 1:
			return EQUITY;
		case 2:
			return DEBT_LOAN;
		case 3:
			return INTERNAL_ACCRUALS;
		case 4:
			return OTHERS;
		case 5:
			return DEBT_LOAN_BY_PROMOTERS;
		case 6:
			return DEBT_LOAN_BY_OTHERS;
		default:
			return null;
		}
	}

	public static FinanceCategory[] getAll() {
		return FinanceCategory.values();

	}
}