package com.opl.mudra.api.enums.oneform;

public enum LoanPurposePL {
    CHILDREN_MARRIAGE(1, "Children's Marriage", "Children's Marriage"),
    EDUCATION_OF_CHILDREN(2, "Education of children", "Education of children"),
    MEDICAL(3, "Medical", "Medical"),
    TRAVEL(4, "Travel", "Travel"),
    PURCHASE_OF_CONSUMER_DURABLE(5, "Purchase of Consumer Durable", "Purchase of Consumer Durable"),
    OTHERS(6, "Others", "Others");

    private final Integer id;
    private final String value;
    private final String description;

    LoanPurposePL(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public static LoanPurposePL[] getAll() {
        return LoanPurposePL.values();

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

    public static LoanPurposePL getById(Integer id) {
        switch (id) {
            case 1:
                return CHILDREN_MARRIAGE;
            case 2:
                return EDUCATION_OF_CHILDREN;
            case 3:
                return MEDICAL;
            case 4:
                return TRAVEL;
            case 5:
                return PURCHASE_OF_CONSUMER_DURABLE;
            case 6:
                return OTHERS;
            default:
                return null;
        }
    }
}
