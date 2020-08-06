package com.opl.mudra.api.enums.oneform;

public enum HaveLIMst {

    YES(1,"Yes","Yes"),
    NO(2,"No","No");

    private final Integer id;
    private final String value;
    private final String description;

    HaveLIMst(Integer id, String value, String description) {
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

    public static HaveLIMst getById(Integer id) {
        switch (id) {
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                return null;
        }
    }
    public static HaveLIMst[] getAll() {
        return HaveLIMst.values();

    }
}
