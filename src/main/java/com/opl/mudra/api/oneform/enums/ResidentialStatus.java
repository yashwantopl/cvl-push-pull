package com.opl.mudra.api.oneform.enums;

/**
 * Created by ravina.panchal on 03-10-2018.
 */
public enum ResidentialStatus {
    RESIDENT(1,"Resident","Resident"),
    NON_RESIDENT(2,"Non-resident","Non-resident");

    private final Integer id;
    private final String value;
    private final String description;
    ResidentialStatus(Integer id, String value, String description) {
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
    public static ResidentialStatus getById(Integer id) {
        switch (id) {
            case 1:
                return RESIDENT;
            case 2:
                return NON_RESIDENT;
            default:
                return null;
        }
    }
    public static ResidentialStatus[] getAll() {
        return ResidentialStatus.values();

    }
}
