package com.opl.mudra.api.oneform.enums;

public enum AssessmentOptionForFS {

    EQUIPMENT_MACHINERY(1,"Purchase of Machinery/Equipment","Purchase of Machinery/Equipment", 2),
    OTHER(2,"Other Business Purpose","Other Business Purpose", 2);

    private final Integer id;
    private final String value;
    private final String description;
    private final Integer productId;

    AssessmentOptionForFS(Integer id, String value, String description, Integer productId) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.productId = productId;
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

    public Integer getProductId() {
        return productId;
    }
    public static AssessmentOptionForFS getById(Integer id) {
        switch (id) {
            case 1:
                return EQUIPMENT_MACHINERY;
            case 2:
                return OTHER;
            default:
                return null;
        }
    }
    public static AssessmentOptionForFS[] getAll() {
        return AssessmentOptionForFS.values();

    }
}
