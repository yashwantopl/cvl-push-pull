package com.opl.mudra.api.oneform.enums;

public enum CompetitionMst_SBI {

    LOW(1,"Low","Low"),
    MEDIUM(2,"Medium","Medium"),
    HIGH(3,"High","High");

    private final Integer id;
    private final String value;
    private final String description;

    CompetitionMst_SBI(Integer id, String value, String description) {
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

    public static CompetitionMst_SBI getById(Integer id) {
        switch (id) {
            case 1:
                return LOW;
            case 2:
                return MEDIUM;
            case 3:
                return HIGH;
            default:
                return null;
        }
    }
    public static CompetitionMst_SBI[] getAll() {
        return CompetitionMst_SBI.values();

    }
}
