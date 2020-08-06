package com.opl.mudra.api.enums.oneform;

public enum SpouseDetailMst {

    EMPLOYED(1,"Employed","Employed"),
    NOT_EMPLOYED(2,"Not Employed","Not Employed");

    private final Integer id;
    private final String value;
    private final String description;

    SpouseDetailMst(Integer id, String value, String description) {
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

    public static SpouseDetailMst getById(Integer id) {
        switch (id) {
            case 1:
                return EMPLOYED;
            case 2:
                return NOT_EMPLOYED;
            default:
                return null;
        }
    }
    public static SpouseDetailMst[] getAll() {
        return SpouseDetailMst.values();

    }
}
