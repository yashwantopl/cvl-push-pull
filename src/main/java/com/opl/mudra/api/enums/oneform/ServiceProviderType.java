package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum ServiceProviderType {
			LAWYERS(1,"Lawyers","Lawyers"),
		CHARTERED_ACCOUNTANT_CA(2,"Chartered Accountant (CA)","Chartered Accountant (CA)"),
		COMPANY_SECRETARY_CS(3,"Company Secretary (CS)","Company Secretary (CS)"),
		CHARTERED_FINANCIAL_ANALYST_CFA(4,"Chartered Financial Analyst (CFA)","Chartered Financial Analyst (CFA)"),
		FREELANCER_AND_FINANCIAL_ADVISORS(5,"Freelancer and Financial Advisors","Freelancer and Financial Advisors"),
		VALUER_OF_ASSETS(6,"Valuer of Assets","Valuer of Assets"),
		INCUBATION_CENTERS_ACCELERATORS(7,"Incubation Centers / Accelerators","Incubation Centers / Accelerators"),
		BOUTIQUE_INVESTMENT_BANKERS(8,"Boutique Investment Bankers","Boutique Investment Bankers"),
		EDUCATIONAL_INSTITUTIONAL_PLAYERS(9,"Educational Institutional Players","Educational Institutional Players"),
		RESEARCH_DEPARTMENTS_CENTERS_(10,"Research Departments/Centers ","Research Departments/Centers "),
		ASSOCIATIONS(11,"Associations","Associations");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		ServiceProviderType(Integer id, String value, String description) {
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
		public static ServiceProviderType getById(Integer id) {
		switch (id) {
		case 1:
			return LAWYERS;
		case 2:
			return CHARTERED_ACCOUNTANT_CA;
		case 3:
			return COMPANY_SECRETARY_CS;
		case 4:
			return CHARTERED_FINANCIAL_ANALYST_CFA;
		case 5:
			return FREELANCER_AND_FINANCIAL_ADVISORS;
		case 6:
			return VALUER_OF_ASSETS;
		case 7:
			return INCUBATION_CENTERS_ACCELERATORS;
		case 8:
			return BOUTIQUE_INVESTMENT_BANKERS;
		case 9:
			return EDUCATIONAL_INSTITUTIONAL_PLAYERS;
		case 10:
			return RESEARCH_DEPARTMENTS_CENTERS_;
		case 11:
			return ASSOCIATIONS;
		default:
			return null;
		}
	}
		public static ServiceProviderType[] getAll() {
			return ServiceProviderType.values();

		}
}