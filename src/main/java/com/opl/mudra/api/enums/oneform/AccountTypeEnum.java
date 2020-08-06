package com.opl.mudra.api.enums.oneform;

public enum AccountTypeEnum {


	AUTO_LOAN_PERSONAL("01","Auto Loan (Personal)"),
	ACCOUNT_REVIEW_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("90","Account Review (Applicable to Enquiry Purpose only)"),
	ADVISER_LIABILITY_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("97","Adviser Liability (Applicable to Enquiry Purpose only)"),
	BUSINESS_LOAN_SECURED("50","Business Loan - Secured"),
	BUSINESS_LOAN_GENERAL("51","Business Loan & General"),
	BUSINESS_LOAN_PRIORITY_SECTOR_SMALL_BUSINESS("52","Business Loan & Priority Sector & Small Business"),
	BUSINESS_LOAN_PRIORITY_SECTOR_AGRICULTURE("53","Business Loan & Priority Sector & Agriculture"),
	BUSINESS_LOAN_PRIORITY_SECTOR_OTHERS("54","Business Loan & Priority Sector & Others"),
	BUSINESS_NON_FUNDED_CREDIT_FACILITY_GENERAL("55","Business NON_Funded Credit Facility & General"),
	BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_SMALL_BUSINESS("56","Business NON_Funded Credit Facility & Priority Sector & Small Business"),
	BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_AGRICULTURE("57","Business NON_Funded Credit Facility & Priority Sector & Agriculture"),
	BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_OTHERS("58","Business NON_Funded Credit Facility & Priority SECTOR_Others"),
	BUSINESS_LOAN_AGAINST_BANK_DEPOSITS("59","Business Loan Against Bank Deposits"),
	BUSINESS_LOAN_UNSECURED("61","Business Loan - Unsecured"),
	CREDIT_CARD("10","Credit Card"),
	CONSUMER_LOAN("06","Consumer Loan"),
	COMMERCIAL_VEHICLE_LOAN("17","Commercial Vehicle Loan"),
	CONSTRUCTION_EQUIPMENT_LOAN("33","Construction Equipment Loan"),
	CORPORATE_CREDIT_CARD("35","Corporate Credit Card"),
	EDUCATION_LOAN("08","Education Loan"),
	FLEET_CARD("16","Fleet Card"),
	GOLD_LOAN("07","Property Loan"),
	HOUSING_LOAN("02","Housing Loan"),
	KISAN_CREDIT_CARD("36","Kisan Credit Card"),
	LOAN_AGAINST_SHARES_SECURITIES("04","Loan Against SHARES/Securities"),
	LOAN_TO_PROFESSIONAL("09","Loan to Professional"),
	LEASING("11","Leasing"),
	LOAN_AGAINST_BANK_DEPOSITS("15","Loan Against Bank Deposits"),
	LOAN_ON_CREDIT_CARD("37","Loan on Credit Card"),
	LOCATE_PLUS_FOR_INSURANCE_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("88","Locate Plus for Insurance (Applicable to Enquiry Purpose only)"),
	LOCATE_PLUS_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("92","Locate Plus (Applicable to Enquiry Purpose only)"),
	MUDRA_LOANS_SHISHU_KISHOR_TARUN("39","Mudra Loans & Shishu / Kishor / Tarun"),
	MICROFINANCE_BUSINESS_LOAN("40","Microfinance & Business Loan"),
	MICROFINANCE_PERSONAL_LOAN("41","Microfinance & Personal Loan"),
	MICROFINANCE_HOUSING_LOAN("42","Microfinance & Housing Loan"),
	MICROFINANCE_OTHER("43","Microfinance & Other"),
	MICROFINANCE_DETAILED_REPORT_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("80","Microfinance Detailed Report (Applicable to Enquiry Purpose only)"),
	NON_FUNDED_CREDIT_FACILITY("14","Non Funded Credit Facility"),
	OVERDRAFT("12","Property Loan"),
	PROPERTY_LOAN("03","Property Loan"),
	PRADHAN_MANTRI_AWAS_YOJANA_CREDIT_LINK_SUBSIDY_SCHEME_MAY_CLSS("44","Pradhan Mantri Awas Yojana - Credit Link Subsidy Scheme MAY CLSS"),
	PERSONAL_LOAN("05","Personal Loan"),
	PRIME_MINISTER_JAAN_DHAN_YOJANA_OVERDRAFT("38","Prime Minister Jaan Dhan Yojana - Overdraft"),
	RETRO_ENQUIRY_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("91","Retro Enquiry (Applicable to Enquiry Purpose only)"),
	SECURED_CREDIT_CARD("31","Secured Credit Card"),
	SUMMARY_REPORT_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("81","Summary Report (Applicable to Enquiry Purpose only)"),
	SECURED_ACCOUNT_GROUP_FOR_PORTFOLIO_REVIEW_RESPONSE("98","Secured (Account Group for Portfolio Review response)"),
	TWO_WHEELER_LOAN("13","Two wheeler Loan"),
	TELCO_WIRELESS("18","Telco & Wireless"),
	TELCO_BROADBAND("19","Telco & Broadband"),
	TELCO_LANDLINE("20","Telco & Landline"),
	TRACTOR_LOAN("34","Tractor Loan"),
	USED_CAR_LOAN("32","Used Car Loan"),
	UNSECURED_ACCOUNT_GROUP_FOR_PORTFOLIO_REVIEW_RESPONSE("99","Unsecured (Account Group for Portfolio Review response)"),
	OTHER("00","Other");


	private String id;
	private String value;

	private AccountTypeEnum(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String getId() {
		return this.id;
	}

	public static AccountTypeEnum fromValue(String v) {
		for (AccountTypeEnum c : AccountTypeEnum.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	public static AccountTypeEnum fromId(String v) {
		for (AccountTypeEnum c : AccountTypeEnum.values()) {
			if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static AccountTypeEnum[] getAll() {
		return AccountTypeEnum.values();
	}

}