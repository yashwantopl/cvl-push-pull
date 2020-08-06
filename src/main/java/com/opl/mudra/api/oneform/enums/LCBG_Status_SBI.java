package com.opl.mudra.api.oneform.enums;

public enum LCBG_Status_SBI {

    YES(1,"Yes","Yes"),
    NO(2,"No","No");

    private final Integer id;
    private final String value;
    private final String description;

    LCBG_Status_SBI(Integer id, String value, String description) {
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

    public static LCBG_Status_SBI getById(Integer id) {
        switch (id) {
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                return null;
        }
    }
    public static LCBG_Status_SBI[] getAll() {
        return LCBG_Status_SBI.values();

    }
}
