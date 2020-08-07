package com.opl.mudra.api.oneform.enums;
/**
 * @author Ravina
 *
 */
public enum PurposeOfLoan {

    PURCHASE_OF_BUSINESS_ASSET(1,"Asset Acquisition","Asset Acquisition"),
    FOR_WORKING_CAPITAL(2,"Working Capital","Working Capital");
//    OTHER_GENERAL(3,"Other General","Other General");

    private final Integer id;
    private final String value;
    private final String description;

    PurposeOfLoan(Integer id, String value, String description) {
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

    public static PurposeOfLoan getById(Integer id) {
        switch (id) {
            case 1:
                return PURCHASE_OF_BUSINESS_ASSET;

            case 2:
                return FOR_WORKING_CAPITAL;

            /*case 3:
                return OTHER_GENERAL;*/

            default:
                return null;
        }
    }

    public static PurposeOfLoan[] getAll() {
        return PurposeOfLoan.values();

    }



}
