package com.opl.mudra.api.oneform.enums;

public enum FrequencyPaymentMstMFI {

    DAILY(1,"Daily"),
    WEEKLY(2,"Weekly"),
    FORNIGHTLY(3,"Fortnightly"),
    MONTHLY(4,"Monthly");

    private Integer id;
    private String value;

    private FrequencyPaymentMstMFI(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static FrequencyPaymentMstMFI fromValue(String v) {
        for (FrequencyPaymentMstMFI c : FrequencyPaymentMstMFI.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static FrequencyPaymentMstMFI fromId(String v) {
        for (FrequencyPaymentMstMFI c : FrequencyPaymentMstMFI.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static FrequencyPaymentMstMFI[] getAll() {
        return FrequencyPaymentMstMFI.values();
    }
    
    public static FrequencyPaymentMstMFI getById(Integer id) {
		switch (id) {
		case 1:
			return DAILY;
		case 2:
			return WEEKLY;
		case 3:
			return FORNIGHTLY;
		case 4:
			return MONTHLY;

		default:
			return null;
		}
    }
    
}
