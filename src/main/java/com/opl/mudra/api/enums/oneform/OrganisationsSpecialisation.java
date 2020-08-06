package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum OrganisationsSpecialisation {
			VALUATION(2,"Valuation","Valuation"),
		DUE_DILIGENCE(3,"Due Diligence","Due Diligence"),
		RATING_ASSISTANCE(4,"Rating Assistance","Rating Assistance"),
		TAXATION(5,"Taxation","Taxation"),
		AUDITING(9,"Auditing","Auditing"),
		LEGAL_SERVICES(10,"Legal Services","Legal Services"),
		DOCUMENTATION(12,"Documentation","Documentation"),
		OTHER_SERVICE_RELATED_TO_FUNDING(13,"Other service related to funding","Other service related to funding"),
		ADVISORY(14,"Advisory","Advisory"),
		ACCOUNTING(15,"Accounting","Accounting"),
		FINANCIAL_PLANNERS(16,"Financial Planners","Financial Planners"),
		COST_ACCOUNTING(17,"Cost Accounting","Cost Accounting"),
		COMPANY_SECRETARY(18,"Company Secretary","Company Secretary"),
		OTHER(19,"Other","Other");
	
		private final Integer id;
		private final String value;
		private final String description;
		OrganisationsSpecialisation(Integer id, String value, String description) {
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
		public static OrganisationsSpecialisation getById(Integer id) {
		switch (id) {
		case 2:
			return VALUATION;
		case 3:
			return DUE_DILIGENCE;
		case 4:
			return RATING_ASSISTANCE;
		case 5:
			return TAXATION;
		case 9:
			return AUDITING;
		case 10:
			return LEGAL_SERVICES;
		case 12:
			return DOCUMENTATION;
		case 13:
			return OTHER_SERVICE_RELATED_TO_FUNDING;
		case 14:
			return ADVISORY;
		case 15:
			return ACCOUNTING;
		case 16:
			return FINANCIAL_PLANNERS;
		case 17:
			return COST_ACCOUNTING;
		case 18:
			return COMPANY_SECRETARY;
		case 19:
			return OTHER;
		default:
			return null;
		}
	}
		public static OrganisationsSpecialisation[] getAll() {
			return OrganisationsSpecialisation.values();

		}
}