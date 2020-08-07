package com.opl.mudra.api.oneform.enums;

public enum AssessedForITMst {

    ASSESSED(1,"Assessed","Assessed"),
    NOT_ASSESSED(2,"Not Assessed","Not Assessed");

    private final Integer id;
    private final String value;
    private final String description;

    AssessedForITMst(Integer id, String value, String description) {
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

    public static AssessedForITMst getById(Integer id) {
        switch (id) {
            case 1:
                return ASSESSED;
            case 2:
                return NOT_ASSESSED;
            default:
                return null;
        }
    }
    public static AssessedForITMst[] getAll() {
        return AssessedForITMst.values();

    }
}
