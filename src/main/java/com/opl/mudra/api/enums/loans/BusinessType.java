package com.opl.mudra.api.enums.loans;

/**
 * @author jaimin.darji
 */
public enum BusinessType {

    NEW_TO_BUSINESS(2, "New to Business"), EXISTING_BUSINESS(1, "Existing Business"),
    RETAIL_PERSONAL_LOAN(3, "Retail Personal Loan"),
    ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS(4, "One Pager Eligibility For Existing Business"),
    RETAIL_HOME_LOAN(5, "Retail Home Loan"), MFI(6, "Micro FInance Institute"), AGRICULTURE(7, "Agriculture"),
    RETAIL_AUTO_LOAN(8, "Retail Auto Loan"), CO_ORIGINATION(9 , "Co Origination"),
    MUDRA_LOAN(10 ,"Mudra Loan"),DFS_LOAN(21 ,"Dfs Loan"),
    ODOP_LOAN(22 ,"Odop Loan");

    private Integer id;
    private String value;

    private BusinessType(Integer id) {
        this.id = id;
    }

    private BusinessType(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    public static BusinessType fromValue(String v) {
        for (BusinessType c : BusinessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static BusinessType fromId(Integer v) {
        for (BusinessType c : BusinessType.values()) {
            if (c.id.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());

    }

}
