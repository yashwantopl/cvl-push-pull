package com.opl.mudra.api.enums.oneform;

public enum KnowHowMst {

    CK(1,"Common Knowledge","Common Knowledge"),
    SKH(2,"Specialised Know How","Specialised Know How");

    private final Integer id;
    private final String value;
    private final String description;

    KnowHowMst(Integer id, String value, String description) {
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

    public static KnowHowMst getById(Integer id) {
        switch (id) {
            case 1:
                return CK;
            case 2:
                return SKH;
            default:
                return null;
        }
    }
    public static KnowHowMst[] getAll() {
        return KnowHowMst.values();

    }
}
