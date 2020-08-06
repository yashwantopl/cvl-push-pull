package com.opl.mudra.api.oneform.enums;

public enum AccountTypeRetailEnum {


    AUTO_LOAN_PERSONAL("01","Auto Loan (Personal)"),
    CONSUMER_LOAN("06","Consumer Loan"),
    CREDIT_CARD("10","Credit Card"),
    EDUCATION_LOAN("08","Education Loan"),
    HOUSING_LOAN("02","Housing Loan"),
    LOAN_AGAINST_SHARES_SECURITIES("04","Loan Against SHARES/Securities"),
    PERSONAL_LOAN("05","Personal Loan"),
    PROPERTY_LOAN("03","Property Loan"),
    OTHER("00","Other");

    private String id;
    private String value;

    private AccountTypeRetailEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getId() {
        return this.id;
    }

    public static AccountTypeRetailEnum fromValue(String v) {
        for (AccountTypeRetailEnum c : AccountTypeRetailEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AccountTypeRetailEnum fromId(String v) {
        for (AccountTypeRetailEnum c : AccountTypeRetailEnum.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AccountTypeRetailEnum[] getAll() {
        return AccountTypeRetailEnum.values();
    }
}
