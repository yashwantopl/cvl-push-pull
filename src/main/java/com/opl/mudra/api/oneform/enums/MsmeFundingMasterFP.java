package com.opl.mudra.api.oneform.enums;

public enum MsmeFundingMasterFP {

    MICRO(1,"Micro","Micro"),
    SMALL(2,"Small","Small"),
    MEDIUM(3,"Medium","Medium");

    private final Integer id;
    private final String value;
    private final String description;

    MsmeFundingMasterFP(Integer id, String value, String description) {
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

    public static MsmeFundingMasterFP getById(Integer id) {
        switch (id) {
            case 1:
                return MICRO;
            case 2:
                return SMALL;
            case 3:
                return MEDIUM;
            default:
                return null;
        }
    }
    public static MsmeFundingMasterFP[] getAll() {
        return MsmeFundingMasterFP.values();

    }
}
