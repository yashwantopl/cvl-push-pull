package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum SecurityType {
			SHARES(1,"Shares","Shares"),
		MUTUAL_FUND_UNITS(2,"Mutual Fund Units","Mutual Fund Units"),
		LIC_AND_PRIVATE_INSURANCE_POLICIES(3,"LIC and Private Insurance Policies","LIC and Private Insurance Policies"),
		FMPS_(4,"FMPs ","FMPs "),
		NABARDSS_BHAVISHYA_NIRMAN_BONDS(5,"NABARDS's Bhavishya Nirman Bonds","NABARDS's Bhavishya Nirman Bonds"),
		NSC_KVPS(6,"NSC/KVPs","NSC/KVPs"),
		GOLD_DEPOSIT_CERTIFIVATES(7,"Gold Deposit Certifivates","Gold Deposit Certifivates"),
		NCD(8,"NCD","NCD");
	
		private final Integer id;
		private final String value;
		private final String description;
		SecurityType(Integer id, String value, String description) {
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
		public static SecurityType getById(Integer id) {
		switch (id) {
		case 1:
			return SHARES;
		case 2:
			return MUTUAL_FUND_UNITS;
		case 3:
			return LIC_AND_PRIVATE_INSURANCE_POLICIES;
		case 4:
			return FMPS_;
		case 5:
			return NABARDSS_BHAVISHYA_NIRMAN_BONDS;
		case 6:
			return NSC_KVPS;
		case 7:
			return GOLD_DEPOSIT_CERTIFIVATES;
		case 8:
			return NCD;
		default:
			return null;
		}
	}
		public static SecurityType[] getAll() {
			return SecurityType.values();

		}
}