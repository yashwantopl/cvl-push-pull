package com.opl.mudra.api.oneform.enums;

public enum DisabilityType {
    NO_DISABILITY(1, "No Disability", "No Disability"),
    PHYSICALLY_RETARDED(2, "Physically Retarded", "Physically Retarded"),
    VISUALLY_IMPAIRED(3, "Visually Impaired", "Visually Impaired"),
    OTHER_DISABILITY(4, "Other Disability", "Other Disability");

    private final Integer id;
    private final String value;
    private final String description;

    DisabilityType(Integer id, String value, String description) {
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

    public static DisabilityType getById(Integer id) {
        switch (id) {
            case 1:
                return NO_DISABILITY;
            case 2:
                return PHYSICALLY_RETARDED;
            case 3:
                return VISUALLY_IMPAIRED;
            case 4:
                return OTHER_DISABILITY;
            default:
                return null;
        }
    }

    public static DisabilityType[] getAll() {
        return DisabilityType.values();

    }

}
