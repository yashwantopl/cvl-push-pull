package com.opl.mudra.api.sidbi.enums;

public enum MeansOfFinanceParticulars {
	
	ADDITIONAL_CAPITAL(1, "additional_capital","Additional Capital (Share Capital / Partners Capital / Proprietors Capital)"),
	INTERNAL_CASH_ACCRUALS(2, "internal_Cash_accruals","Internal Cash Accruals"),
	INTEREST_FREE_UNSECURED_LOANS(3, "interest_free_unsecured_loans","Interest Free Unsecured Loans"),
	TERM_LOAN_PROPOSED_FROM_SIDBI(4, "tl_proposed_from_SIDBI","Term Loan proposed from SIDBI");
	
	private final Integer id;
	private final String value;
	private final String description;
	
	private MeansOfFinanceParticulars(Integer id, String value,String description) {
		this.id = id;
		this.value = value;
		this.description=description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	public static MeansOfFinanceParticulars[] getAll() {
		return MeansOfFinanceParticulars.values();

	}
	
	public static MeansOfFinanceParticulars getById(Integer id) {
		for (MeansOfFinanceParticulars corpo : MeansOfFinanceParticulars.getAll()) {
			if(corpo.id == id) {
				return corpo;
			} 
		}
		 throw new IllegalArgumentException("Error when getting enum data");
	}

	public String getDescription() {
		return description;
	}
	

}
