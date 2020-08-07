package com.opl.mudra.api.oneform.enums;

public enum EmploymentStatusNTB {
    PERMANENT(1,"Permanent","Permanent"),
    CONTRACTUAL(2,"Contractual","Contractual");

    private final Integer id;
    private final String value;
    private final String description;

    EmploymentStatusNTB(Integer id, String value, String description) {
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

    public static EmploymentStatusNTB getById(Integer id) {
        switch (id) {
            case 1:
                return PERMANENT;
            case 2:
                return CONTRACTUAL;
            default:
                return null;
        }
    }
    public static EmploymentStatusNTB[] getAll() {
        return EmploymentStatusNTB.values();

    }
}
