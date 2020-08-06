package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 *
 */
public enum WcFinancialType {
		PRESUMPTIVE(4,"Presumptive","Presumptive"),
		AUDITED2OR3(3,"Audited 3 years","Auditedt(3 Years)"),
		AUDITED1(1,"Audited Less than 3 years","Audited 1 Year");
			
			
		private final Integer id;
		private final String value;
		private final String description;
		WcFinancialType(Integer id, String value, String description) {
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
		public static WcFinancialType getById(Integer id) {
		switch (id) {
		case 4:
			return PRESUMPTIVE;
		case 3:
			return AUDITED2OR3;
		case 1:
			return AUDITED1;
		default:
			return null;
		}
	}
		public static WcFinancialType[] getAll() {
			return WcFinancialType.values();

		}
}