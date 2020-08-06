package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum LoanType {
			WORKING_CAPITAL(1,"Working Capital","Working Capital"),
		TERM_LOAN(2,"Term Loan","Term Loan"),
		HOME_LOAN(3,"Home Loan","Home Loan"),
		PERSONAL_LOAN(7,"Personal Loan","Personal Loan"),
		AUTO_LOAN(12,"Auto Loan","Auto Loan"),
		LOAN_AGAINST_PROPERTY(13,"Loan Against Property","Loan Against Property"),
		LOAN_AGAINST_SHARES_AND_SECUIRITIES(14,"Loan Against Shares and Secuirities","Loan Against Shares and Secuirities"),
		UNSECURED_LOAN(15,"Unsecured Loan","Unsecured Loan"),
		WCTL_LOAN(16,"Working Capital Term Loan","Working Capital Term Loan"),
		MFI_LOAN(17,"Micro Finance Institute Loan","Micro Finance Institute Loan"),
		AGRI_LOAN(18,"Agriculture Loan","Agriculture Loan");
		private final Integer id;
		private final String value;
		private final String description;
		LoanType(Integer id, String value, String description) {
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
		public static LoanType getById(Integer id) {
		switch (id) {
		case 1:
			return WORKING_CAPITAL;
		case 2:
			return TERM_LOAN;
		case 3:
			return HOME_LOAN;
		case 7:
			return PERSONAL_LOAN;
		case 12:
			return AUTO_LOAN;
		case 13:
			return LOAN_AGAINST_PROPERTY;
		case 14:
			return LOAN_AGAINST_SHARES_AND_SECUIRITIES;
		case 15:
			return UNSECURED_LOAN;
		case 16:
			return WCTL_LOAN;
		case 17:
			return MFI_LOAN;
		default:
			return null;
		}
	}
		public static LoanType[] getAll() {
			return LoanType.values();

		}
}