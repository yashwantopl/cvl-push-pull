package com.opl.mudra.api.oneform.enums;

public enum FinancialStrength {
    FINANCIALLY_STRONG(1,"Promoter is financially very strong and has other independent sources of income which could support this business in adverse situations","Promoter is financially very strong and has other independent sources of income which could support this business in adverse situations"),
    GOOD_FINANCIAL_STRENGTH (2,"Promoter is financially strong, but may have other businesses that may not be doing well or could need funds for meeting personal expenses","Promoter is financially strong, but may have other businesses that may not be doing well or could need funds for meeting personal expenses"),
    REASONABLE_FINANCIAL_STRENGTH(3,"Promoter's financial strength is reasonable; however fund requirements from other business interests/personal needs could affect this business adversely","Promoter's financial strength is reasonable; however fund requirements from other business interests/personal needs could affect this business adversely"),
    WEAK_FINANCIAL_POSITION(4,"Promoter has poor/questionable financial strength; Or widely differing opinions obtained","Promoter has poor/questionable financial strength; Or widely differing opinions obtained");
    private final Integer id;
    private final String value;
    private final String description;
    FinancialStrength(Integer id, String value, String description) {
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
    public static FinancialStrength getById(Integer id) {
        switch (id) {
            case 1:
                return FINANCIALLY_STRONG;
            case 2:
                return GOOD_FINANCIAL_STRENGTH;
            case 3:
                return REASONABLE_FINANCIAL_STRENGTH;
            case 4:
                return WEAK_FINANCIAL_POSITION;
            default:
                return null;
        }
    }
    public static FinancialStrength[] getAll() {
        return FinancialStrength.values();

    }
}
