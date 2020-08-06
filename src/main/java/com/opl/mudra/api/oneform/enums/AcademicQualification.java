package com.opl.mudra.api.oneform.enums;

public enum AcademicQualification {
    MATRICULATE_AND_ABOVE(1, "Matriculate & above", "Matriculate & above"),
    SECONDARY(2, "Secondary (7th standard)", "Secondary (7th standard)"),
    LLITERATE(3, "lliterate", "lliterate");

    private final Integer id;
    private final String value;
    private final String description;

    AcademicQualification(Integer id, String value, String description) {
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

    public static AcademicQualification getById(Integer id) {
        switch (id) {
            case 1:
                return MATRICULATE_AND_ABOVE;
            case 2:
                return SECONDARY;
            case 3:
                return LLITERATE;
            default:
                return null;
        }
    }

    public static AcademicQualification[] getAll() {
        return AcademicQualification.values();

    }
}