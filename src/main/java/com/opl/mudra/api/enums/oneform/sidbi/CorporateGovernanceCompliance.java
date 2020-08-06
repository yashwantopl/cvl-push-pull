/**
 * 
 */
package com.opl.mudra.api.enums.oneform.sidbi;

/**
 * @author mohammad.maaz
 *
 */
public enum CorporateGovernanceCompliance {
	DETAILS_OF_LIABLITYES_OF_ENTERPRISE_TOWARDS_STATUTORY_PAYMENTS_LIKE_INCOMETAX_ETC_IF_ANY(1,"Details of disputed liabilities of the enterprise towards statutory payments like Income Tax, Sales Tax, Excise, PF, ESIC, Wealth Tax, Service Tax, Dividend Tax etc, if any"),
	DETAILS_OF_ARREARS_IN_NON_DISPUTED_STATUTORY_PAYMENTS_IN_RESPECT_OF_ENTERPRISE_ASSPCIATE_CONCERN_VIZ_INCOMETAX_EXERCISE_PF_ESIC_WELTHTAX_SERVICETAX_DIVIDENTTAX_ETC_IF_ANY(2,"Details of arrears in non-disputed statutory payments in respect of the enterprise and associate concerns viz Income Tax, Excise, PF, ESIC, Wealth Tax, Service Tax, Dividend Tax etc, if any."),
	DETAILS_OF_PENDING_COURTCASE_INITIATED_BY_BANKS_FLS_OTHER_GOVT_STATUATRY_AUTHORITIES_AGAINST_APPLICANT_ENTERPRISE_ASSOCIATE_CONCERNS_PROMOTERS_DIRECTORS_PARTNERS_ETC_IF_ANY_INCLUDING_FOREX_MATERS_FEMA_AND_IPR_RELATED_MATERS_ETC(3,"Details of pending court cases initiated by banks / FIs/ other Govt / Statutory authorities against the applicant enterprise, associate concerns, promoters, directors, partners etc, if any (including forex matters / FEMA and IPR related matters etc)."),
	DETAILS_OF_LABORSDISPUTES_LITIGATION_BY_OR_AGAINST_THE_ENTERPRISE_PROMOTERS_DIRECTORS_IF_ANY(4,"Details of labour disputes / litigations by or against the enterprise / promoters / directors, if any."),
	CONNECTED_LANDING_RELATIONSHIP_OF_PROPRITERS_PARTNERS_DIRECTORS_WITH_OFFICIAL_OF_BANKS(5,"Connected Lending - Relationship of Proprietors / Partners / Directors with the officials of the Bank/ Director of the Bank: Please select (Yes/No). If Yes, please provide details");
	
	private final Integer id;
	private final String value;
	
	private CorporateGovernanceCompliance(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static CorporateGovernanceCompliance getById(Integer id) {
		for (CorporateGovernanceCompliance corpo : CorporateGovernanceCompliance.getAll()) {
			if(corpo.id == id) {
				return corpo;
			} 
		}
		 throw new IllegalArgumentException("Error when getting enum data");
	}
	
	public static CorporateGovernanceCompliance[] getAll() {
		return CorporateGovernanceCompliance.values();

	}
	
}
