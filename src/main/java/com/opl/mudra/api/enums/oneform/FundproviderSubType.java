package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum FundproviderSubType {
			EQUITY(1,"Equity","Equity"),
		DEBT(2,"Debt","Debt"),
		EQUITY_DEBT(3,"Equity & Debt","Equity & Debt");
			
			
		private final Integer id;
		private final String value;
		private final String description;
		FundproviderSubType(Integer id, String value, String description) {
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
		public static FundproviderSubType getById(Integer id) {
		switch (id) {
		case 1:
			return EQUITY;
		case 2:
			return DEBT;
		case 3:
			return EQUITY_DEBT;
		default:
			return null;
		}
	}
		public static FundproviderSubType[] getAll() {
			return FundproviderSubType.values();

		}
}