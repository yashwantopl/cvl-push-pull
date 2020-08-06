package com.opl.mudra.api.oneform.enums;

public enum CreditType {
	CASH_CREDIT(1,"Cash credit"),
	OVERDRAFT(2,"Overdraft"),
	DEMAND_LOAN(3,"Demand loan"),
	LOAN_EXTENDED_THROUGH_CREDIT_CARDS(4,"Loan extended through credit cards"),
	MEDIUM_TERM_LOAN_PERIOD_ABOVE_1_YEAR_AND_UP_TO_3_YEARS(5,"Medium term loan (period above 1 year and up to 3 years)"),
	LONG_TERM_LOAN_PERIOD_ABOVE_3_YEARS(6,"Long term loan (period above 3 years)"),
	PACKING_CREDIT_ALL_EXPORT_PRE_SHIPMENT_FINANCE(7,"Packing credit (all export pre-shipment finance)"),
	EXPORT_BILLS_PURCHASED(8,"Export bills purchased"),
	EXPORT_BILLS_DISCOUNTED(9,"Export bills discounted"),
	EXPORT_BILLS_ADVANCED_AGAINST(10,"Export bills advanced against"),
	ADVANCES_AGAINST_EXPORT_CASH_INCENTIVES_AND_DUTY_DRAW_BACK_CLAIMS(11,"Advances against export cash incentives and duty draw-back claims"),
	INLAND_BILLS_PURCHASED(12,"Inland bills purchased"),
	INLAND_BILLS_DISCOUNTED(13,"Inland bills discounted"),
	ADVANCES_AGAINST_IMPORT_BILLS(14,"Advances against import bills"),
	FOREIGN_CURRENCY_CHEQUES_TCS_DDS_TTS_MTS_PURCHASED(15,"Foreign currency cheques TCS/DDS/TTS/MTS purchased"),
	LEASE_FINANCE(16,"Lease finance"),
	HIRE_PURCHASE(17,"Hire purchase"),
	BANK_GUARANTEE(18,"Bank guarantee"),
	DEFERRED_PAYMENT_GUARANTEE(19,"Deferred payment guarantee"),
	LETTERS_OF_CREDIT(20,"Letters of credit"),
	CORPORATE_CREDIT_CARD(21,"Corporate credit card"),
	COMMERCIAL_VEHICLE_LOAN(22,"Commercial vehicle loan"),
	EQUIPMENT_FINANCING_CONSTRUCTION_OFFICE_MEDICAL(23,"Equipment financing (construction office medical)"),
	UNSECURED_BUSINESS_LOAN(24,"Unsecured business loan"),
	SHORT_TERM_LOAN_LESS_THAN_1_YEAR(25,"Short term loan (less than 1 year)"),
	
	//Commented AS per Gauravbhai Suggested
	
//	AGGREGATION_OF_ALL_FUND_BASED_FACILITIES(26,"Aggregation of all fund based facilities"),
//	AGGREGATION_OF_ALL_NON_FUND_BASED_FACILITIES(27,"Aggregation of all non fund based facilities"),
//	FACILITIES_INTERCHANGE_BETWEEN_FUND_NON_FUND_BASED(28,"Facilities interchange between fund & non fund based"),
	DERIVATIVES(29,"Derivatives"),
	PLAIN_VANILLA_FOREX_FORWARD_CONTRACTS(30,"Plain vanilla forex forward contracts"),
	PLAIN_VANILA_INT_RATE_SWAP_ALL_INCLUDING_INR_AS_COUPON(31,"Plain vanila int rate swap(all including INR as coupon)"),
	PLAIN_VANILA_FOREIGN_CURRENCY_OPTION_INCLUDING_INR_CROSS_CURRENCY(32,"Plain vanila foreign currency option (including INR cross currency)"),
	COMPLEX_INT_RATE_DERV_WITH_OPTIONALITIES(33,"Complex int rate derv with optionalities"),
	ANY_COMPLEX_DERIVATIVE_LOAN_INVOLVING_FOREIGN_CURRENCY_WITH_OPTION(34,"Any complex derivative loan involving foreign currency with option"),
	CONTRACTS_ON_PAST_PERFORMANCE_IMPORTS(35,"Contracts on past performance – imports"),
	CONTRACTS_ON_PAST_PERFORMANCE_EXPORTS(36,"Contracts on past performance – exports"),
	AGGREGATE_OF_ALL_BORROWINGS_DUE_TO_FILING_OF_SUIT(37,"Aggregate of all borrowings due to filing of suit"),
	AUTO_LOAN(38,"Auto Loan"),
	PROPERTY_LOAN(39,"Property Loan"),
	GOLD_LOAN(40,"Gold Loan"),
	LOAN_AGAINST_SHARES_SECURITIES(41,"LOAN AGAINST SHARES/SECURITIES"),
	OTHERS(42,"Others"),
	MUDRA_LOANS_SHISHU_KISHOR_TARUN(43 , "Mudra Loans - Shishu / Kishor / Tarun"); 

	private Integer id;
	private String value;

	private CreditType(Integer id,String value) {
		this.id = id;
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public Integer getId() {
		return this.id;
	}

	public static CreditType fromValue(String v) {
		for (CreditType c : CreditType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	public static CreditType fromId(Integer v) {
		for (CreditType c : CreditType.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v.toString());

	}
	
	public static CreditType[] getAll() {
		return CreditType.values();
	}
}
