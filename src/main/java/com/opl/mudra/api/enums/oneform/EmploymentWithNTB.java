package com.opl.mudra.api.enums.oneform;

public enum EmploymentWithNTB {

    PARTNERSHIP(2, "Partnership firm", "Partnership firm"),
    MNC(3, "MNC", "MNC"), PRIVATE_LTD(4, "Private Ltd", "Private Ltd"),
    PROPRIETORSHIP(5, "Proprietorship", "Proprietorship"),
    PUBLIC_LTD(6, "Public Ltd", "Public Ltd"),
    PUBLIC_SECTOR(7, "Public Sector", "Public Sector"),
    OTHERS(8, "Others", "Others");

    private final Integer id;
    private final String value;
    private final String description;

    EmploymentWithNTB(Integer id, String value, String description) {
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

    public static EmploymentWithNTB getById(Integer id) {
        switch (id) {
            case 2:
                return PARTNERSHIP;
            case 3:
                return MNC;
            case 4:
                return PRIVATE_LTD;
            case 5:
                return PROPRIETORSHIP;
            case 6:
                return PUBLIC_LTD;
            case 7:
                return PUBLIC_SECTOR;
            case 8:
                return OTHERS;
            default:
                return null;
        }
    }

    public static EmploymentWithNTB[] getAll() {
        return EmploymentWithNTB.values();

    }
}
