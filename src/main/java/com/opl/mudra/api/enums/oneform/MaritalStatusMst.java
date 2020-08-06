package com.opl.mudra.api.enums.oneform;

public enum MaritalStatusMst {

    MARRIED(1,"Married","Married"),
    UNMARRIED(2,"Unmarried","Unmarried"),
    OTHER(3,"Other","Other");

    private final Integer id;
    private final String value;
    private final String description;

    MaritalStatusMst(Integer id, String value, String description) {
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

    public static MaritalStatusMst getById(Integer id) {
        switch (id) {
            case 1:
                return MARRIED;
            case 2:
                return UNMARRIED;
            case 3:
                return OTHER;
            default:
                return null;
        }
    }
    public static MaritalStatusMst[] getAll() {
        return MaritalStatusMst.values();

    }
}
