package com.opl.mudra.api.enums.oneform;

public enum PurposeOfLoanMFI {
    BUSINESS(1, "Business (Manufacturing/Shop)", "Business (Manufacturing/Shop)"),
    SERVICES(2, "Services", "Services");

    private final Integer id;
    private final String value;
    private final String description;

    PurposeOfLoanMFI(Integer id, String value, String description) {
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

    public static PurposeOfLoanMFI getById(Integer id) {
        switch (id) {
            case 1:
                return BUSINESS;
            case 2:
                return SERVICES;
            default:
                return null;
        }
    }

    public static PurposeOfLoanMFI[] getAll() {
        return PurposeOfLoanMFI.values();

    }
}