package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum CreditCardTypesRetail {
			PRIMARY(1,"Primary","Primary"),
		SUPPLEMENTARY(2,"Supplementary","Supplementary");
	
		private final Integer id;
		private final String value;
		private final String description;
		CreditCardTypesRetail(Integer id, String value, String description) {
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
		public static CreditCardTypesRetail getById(Integer id) {
		switch (id) {
		case 1:
			return PRIMARY;
		case 2:
			return SUPPLEMENTARY;
		default:
			return null;
		}
	}
		public static CreditCardTypesRetail[] getAll() {
			return CreditCardTypesRetail.values();

		}
}