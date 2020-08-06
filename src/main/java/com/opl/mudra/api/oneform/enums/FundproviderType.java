package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum FundproviderType {
			ANGEL_INVESTOR(1,"Angel Investor","Angel Investor"),
		HNI(2,"HNI","HNI"),
		VENTURE_CAPITALIST(3,"Venture Capitalist","Venture Capitalist"),
		PRIVATE_EQUITY_FIRMS(4,"Private Equity Firms","Private Equity Firms"),
		NBFC(5,"NBFC","NBFC"),
		BANK(6,"Bank","Bank"),
		ARC(7,"ARC","ARC"),
		STRATEGIC_INVESTORS(8,"Strategic Investors","Strategic Investors"),
		SOVEREIGN_WEALTH_FUNDS(9,"Sovereign Wealth Funds","Sovereign Wealth Funds"),
		FAMILY_OFFICE(10,"Family Office","Family Office"),
		SOCIAL_FUND_PROVIDERS(11,"Social Fund Providers","Social Fund Providers"),
		FDI___FII_(12,"FDI / FII ","FDI / FII "),
		INSTITUTIONAL_INVESTORS(13,"Institutional Investors","Institutional Investors"),
		MA_AND_JV_FUND_PROVIDERS_(14,"M&A AND JV FUND Providers ","M&A AND JV FUND Providers "),
		INVESTORS_FOR_MAKE_IN_INDIA_PROJECTS_(15,"Investors for Make in India Projects ","Investors for Make in India Projects "),
		OTHER(16,"Other","Other"),
		FINANCIAL_INSTITUTION(17,"Financial Institution","Financial Institution");
	
		private final Integer id;
		private final String value;
		private final String description;
		FundproviderType(Integer id, String value, String description) {
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
		public static FundproviderType getById(Integer id) {
		switch (id) {
		case 1:
			return ANGEL_INVESTOR;
		case 2:
			return HNI;
		case 3:
			return VENTURE_CAPITALIST;
		case 4:
			return PRIVATE_EQUITY_FIRMS;
		case 5:
			return NBFC;
		case 6:
			return BANK;
		case 7:
			return ARC;
		case 8:
			return STRATEGIC_INVESTORS;
		case 9:
			return SOVEREIGN_WEALTH_FUNDS;
		case 10:
			return FAMILY_OFFICE;
		case 11:
			return SOCIAL_FUND_PROVIDERS;
		case 12:
			return FDI___FII_;
		case 13:
			return INSTITUTIONAL_INVESTORS;
		case 14:
			return MA_AND_JV_FUND_PROVIDERS_;
		case 15:
			return INVESTORS_FOR_MAKE_IN_INDIA_PROJECTS_;
		case 16:
			return OTHER;
		case 17:
			return FINANCIAL_INSTITUTION;
		default:
			return null;
		}
	}
		public static FundproviderType[] getAll() {
			return FundproviderType.values();

		}
}