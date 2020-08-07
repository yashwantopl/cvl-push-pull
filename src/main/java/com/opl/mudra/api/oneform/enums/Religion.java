package com.opl.mudra.api.oneform.enums;

public enum Religion {
    HINDU(1, "Hindu", "Hindu"),
    CHRISTIAN(2, "Christian", "Christian"),
    MUSLIM(3, "Muslim", "Muslim"),
    SIKH(4, "Sikh", "Sikh"),
    OTHERS(5, "Others", "Others");

    private final Integer id;
    private final String value;
    private final String description;

    Religion(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public static Religion[] getAll() {
        return Religion.values();
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

    public static Religion getById(Integer id) {
        switch (id) {
            case 1:
                return HINDU;
            case 2:
                return CHRISTIAN;
            case 3:
                return MUSLIM;
            case 4:
                return SIKH;
            case 5:
                return OTHERS;
            default:
                return null;
        }
    }
}
