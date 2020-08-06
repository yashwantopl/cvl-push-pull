package com.opl.mudra.api.enums.oneform;

public enum FinancialSupport {
	FINANCIALLY_STRONG(1,"Promoter/group is financially very strong and atleast one group company enjoys a 'AA' rating or higher ","Promoter/group is financially very strong and atleast one group company enjoys a 'AA' rating or higher "),
	GOOD_FINANCIAL_STRENGTH (2,"Promoter/group has good financial strength although external ratings are not available","Promoter/group has good financial strength although external ratings are not available"),
	REASONABLE_FINANCIAL_STRENGTH(3,"Promoter/Group has reasonable financial strength; however, there are other entities in the group competing for financial support.","Promoter/Group has reasonable financial strength; however, there are other entities in the group competing for financial support."),
	WEAK_FINANCIAL_POSITION(4,"Promoter/Group has a weak financial position or other group entities may have higher claim. ","Promoter/Group has a weak financial position or other group entities may have higher claim. "),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		FinancialSupport(Integer id, String value, String description) {
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
		public static FinancialSupport getById(Integer id) {
		switch (id) {
		case 1:
			return FINANCIALLY_STRONG;
		case 2:
			return GOOD_FINANCIAL_STRENGTH;
		case 3:
			return REASONABLE_FINANCIAL_STRENGTH;
		case 4:
			return WEAK_FINANCIAL_POSITION;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static FinancialSupport[] getAll() {
			return FinancialSupport.values();

		}
}