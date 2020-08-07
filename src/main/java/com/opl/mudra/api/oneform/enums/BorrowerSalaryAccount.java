package com.opl.mudra.api.oneform.enums;

/**
 * @author Akshay
 *
 */
public enum BorrowerSalaryAccount {
	MY_BANK(1, "My Bank", "My Bank"), 
	OTHER_BANK(2, "Other Bank", "Other Bank"),
	BOTH(3, "Both", "Both"),
	NOT_APPLICABLE(4, "Not-Applicable", "Not-Applicable");

	private final Integer id;
	private final String value;
	private final String description;

	BorrowerSalaryAccount(Integer id, String value, String description) {
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
	
	public static BorrowerSalaryAccount fromId(Integer v) {
		for (BorrowerSalaryAccount c : BorrowerSalaryAccount.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v != null ? v.toString() : null);
	}
	
	public static BorrowerSalaryAccount fromValue(String v) {
		for (BorrowerSalaryAccount c : BorrowerSalaryAccount.values()) {
			if (c.value.equalsIgnoreCase(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static BorrowerSalaryAccount[] getAll(){
		return BorrowerSalaryAccount.values();
		
	}

}
