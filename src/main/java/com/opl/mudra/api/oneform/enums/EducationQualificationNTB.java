package com.opl.mudra.api.oneform.enums;

public enum EducationQualificationNTB {

    IIT(1,"IIT","IIT"),
    IIM(2,"IIM","IIM"),
    CA(3,"CA","CA"),
    TECHNICAL(4,"Technical","Technical"),
    PROFESSIONAL(5,"Professional","Professional"),
    OTHERS(6,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;
    EducationQualificationNTB(Integer id, String value, String description) {
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
    public static EducationQualificationNTB getById(Integer id) {
        switch (id) {
            case 1:
                return IIT;
            case 2:
                return IIM;
            case 3:
                return CA;
            case 4:
                return TECHNICAL;
            case 5:
                return PROFESSIONAL;
            case 6:
                return OTHERS;
            default:
                return null;
        }
    }
    public static EducationQualificationNTB[] getAll() {
        return EducationQualificationNTB.values();

    }
}
