package com.opl.mudra.api.oneform.enums;

public enum AccountTypeMfi {
    FIXED_DEPOSITE(3,"Fixed Deposit"),
    RECURRING(1,"Recurring"),
    SAVING(2,"Savings"),
    OTHERS(4,"Others");

    private Integer id;
    private String value;

    private AccountTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static AccountTypeMfi fromValue(String v) {
        for (AccountTypeMfi c : AccountTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AccountTypeMfi fromId(String v) {
        for (AccountTypeMfi c : AccountTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AccountTypeMfi[] getAll() {
        return AccountTypeMfi.values();
    }
    
    public static AccountTypeMfi getById(Integer id) {
		switch (id) {
		case 1:
			return RECURRING;
		case 2:
			return SAVING;
		case 3:
			return FIXED_DEPOSITE;
		case 4:
			return OTHERS;	
		default:
			return null;
		}
	}
    
}
