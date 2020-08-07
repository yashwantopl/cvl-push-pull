package com.opl.mudra.api.oneform.enums;

public enum OccupationNatureNTB {

    SALARIED(2,"Salaried","Salaried"),
    SELF_EMPLOYED_NON_PROFESSIONAL(4,"Self Employed - Businessman","Self Employed - Non-Professional(Businessman)"),
    SELF_EMPLOYED_PROFESSIONAL(5,"Self Employed - Professional","Self Employed - Professional"),
    AGRICULTURIST(6,"Agriculturist","Agriculturist"),
    PENSIONER(7,"Pensioner","Pensioner"),
    OTHERS(3,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    OccupationNatureNTB(Integer id, String value, String description) {
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

    public static OccupationNatureNTB getById(Integer id) {
        switch (id) {
            case 2:
                return SALARIED;
            case 3:
                return OTHERS;
            case 4:
                return SELF_EMPLOYED_NON_PROFESSIONAL;
            case 5:
                return SELF_EMPLOYED_PROFESSIONAL;
            case 6:
                return AGRICULTURIST;
            case 7:
                return PENSIONER;
            default:
                return null;
        }
    }

    public static OccupationNatureNTB[] getAll() {
        return OccupationNatureNTB.values();

    }
}
