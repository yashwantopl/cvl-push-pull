package com.opl.mudra.api.enums.oneform;

public enum LoanTypeNatureFacility {
	TERM_LOAN(1,"Term Loan","Term Loan"),
	WORKING_CAPITAL_CC(2,"Working Capital - CC","Working Capital - CC"),
	WORKING_CAPITAL_OD(3,"Working Capital - OD","Working Capital - OD"),
	UNSECURED_LOAN(4,"Unsecured Loan","Unsecured Loan"),
	DEMAND_LOAN(5,"Demand Loan","Demand Loan"),
	BANK_GUARANTEE(6,"Bank Guarantee","Bank Guarantee"),
	LETTER_OF_CREDIT(7,"Letter of Credit","Letter of Credit"),
	EXPORT_CREDIT(8,"Export Credit","Export Credit"),
	PACKING_CREDIT(9,"Packing Credit","Packing Credit"),
	BILL_DISCOUNTING(10,"Bill Discounting","Bill Discounting");
	
		private final Integer id;
		private final String value;
		private final String description;
		LoanTypeNatureFacility(Integer id, String value, String description) {
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
		public static LoanTypeNatureFacility getById(Integer id) {
		switch (id) {
		case 1:
			return LoanTypeNatureFacility.TERM_LOAN;
		case 2:
			return LoanTypeNatureFacility.WORKING_CAPITAL_CC;
		case 3:
			return LoanTypeNatureFacility.WORKING_CAPITAL_OD;
		case 4:
			return LoanTypeNatureFacility.UNSECURED_LOAN;
		case 5:
			return LoanTypeNatureFacility.DEMAND_LOAN;
		case 6:
			return LoanTypeNatureFacility.BANK_GUARANTEE;
		case 7:
			return LoanTypeNatureFacility.LETTER_OF_CREDIT;
		case 8:
			return LoanTypeNatureFacility.EXPORT_CREDIT;
		case 9:
			return LoanTypeNatureFacility.PACKING_CREDIT;
		case 10:
			return LoanTypeNatureFacility.BILL_DISCOUNTING;
		default:
			return null;
		}
	}
		public static LoanTypeNatureFacility[] getAll() {
			return LoanTypeNatureFacility.values();

		}
}