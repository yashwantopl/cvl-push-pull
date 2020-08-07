package com.opl.mudra.api.oneform.enums;

public enum CreditRecord {
	PROMOTER_NEVER_VIOLATED(1,"Promoter has never violated any terms or conditions of any of its loan agreements in the past and CIBIL score over 700","Promoter has never violated any terms or conditions of any of its loan agreements in the past and CIBIL score over 700"),
	PROMOTER_IS_KNOWN(2,"Promoter is known to have dishonoured or renegotiated some terms and conditions of its loan agreements, which are not significant breaches. Such actions can be attributed to reasons that are beyond the management's control","Promoter is known to have dishonoured or renegotiated some terms and conditions of its loan agreements, which are not significant breaches. Such actions can be attributed to reasons that are beyond the management's control"),
	PROMOTER_KNOWN_BREACH(3,"Promoter has been known to renegotiate/breach significant terms or conditions of its obligations, the reasons of which are not entirely beyond the management's control","Promoter has been known to renegotiate/breach significant terms or conditions of its obligations, the reasons of which are not entirely beyond the management's control"),
	PROMOTER_DUBIOUS(4,"Promoter has a dubious record in honuring its credit contracts for all kinds of reasons, with CIBIL score < 650","Promoter has a dubious record in honuring its credit contracts for all kinds of reasons, with CIBIL score < 650");
	
		private final Integer id;
		private final String value;
		private final String description;
		CreditRecord(Integer id, String value, String description) {
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
		public static CreditRecord getById(Integer id) {
		switch (id) {
		case 1:
			return PROMOTER_NEVER_VIOLATED;
		case 2:
			return PROMOTER_IS_KNOWN;
		case 3:
			return PROMOTER_KNOWN_BREACH;
		case 4:
			return PROMOTER_DUBIOUS;
		default:
			return null;
		}
	}
		public static CreditRecord[] getAll() {
			return CreditRecord.values();

		}
}